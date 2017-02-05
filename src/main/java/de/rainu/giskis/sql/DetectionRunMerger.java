package de.rainu.giskis.sql;

import de.rainu.giskis.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class merges all data in database into a one huge {@link DetectionRun}.
 */
@Component
public class DetectionRunMerger {

	@Autowired
	private DetectionRunRepository detectionRepository;

	@Autowired
	private WirelessNetworkRepository networkRepository;

	@Autowired
	private WirelessClientRepository clientRepository;

	public DetectionRun buildFullDetectionRun() {
		return buildDetectionRun(() ->
				  networkRepository.findBestWirelessNetworkIds().parallelStream()
							 .map(id -> networkRepository.findOne(id))
							 .collect(Collectors.toList())
		);
	}

	public DetectionRun buildDetectionRun(Supplier<List<WirelessNetwork>> networkSupplier) {
		final DetectionRun run = new DetectionRun();
		run.setTime(detectionRepository.getLastRunTime());
		run.setKismetVersion("COMPLETE");

		final List<WirelessNetwork> networks = networkSupplier.get();
		run.setWirelessNetworks(networks);

		for (int i = 0; i < networks.size(); i++) {
			final WirelessNetwork network = networks.get(i);
			List<WirelessClient> clients = clientRepository.findRealClientIdsForNetworkBssid(network.getBSSID()).parallelStream()
					  .map(id -> clientRepository.findOne(id))
					  .collect(Collectors.toList());
			clients = mergeAllClients(clients);

			//repair numbers
			repairNumbers(clients);

			network.setNumber(i + 1);
			network.setWirelessClients(clients);
		}

		return run;
	}

	private List<WirelessClient> mergeAllClients(List<WirelessClient> clients) {
		Map<String, List<WirelessClient>> macMap = new HashMap<>();

		for (WirelessClient client : clients) {
			if (!macMap.containsKey(client.getMac())) {
				macMap.put(client.getMac(), new ArrayList<>());
			}

			macMap.get(client.getMac()).add(client);
		}

		return macMap.values().stream()
				  .map(this::mergeClients)
				  .collect(Collectors.toList());
	}

	private WirelessClient mergeClients(Collection<WirelessClient> clients) {
		final WirelessClient client = clients.stream().findFirst().get();

		if (clients.size() == 1) {
			return client;
		}

		if (client.getTag() == null) {
			client.setTag(new ArrayList<>());
		}
		Tag tag = new Tag();
		tag.setName("MERGE_INFORMATION");
		tag.setValue(clients.size() + " Clients merged");
		client.getTag().add(tag);

		clients.stream().skip(1L).forEach(curClient -> {
			client.setMaxSeenRate(Math.max(client.getMaxSeenRate(), curClient.getMaxSeenRate()));

			if(client.getFirstTime() == null) {
				client.setFirstTime(curClient.getFirstTime());
			}else {
				client.setFirstTime(client.getFirstTime().isBefore(curClient.getFirstTime()) ? client.getFirstTime() : curClient.getFirstTime());
			}

			if(client.getLastTime() == null) {
				client.setLastTime(curClient.getLastTime());
			}else {
				client.setLastTime(client.getLastTime().isAfter(curClient.getLastTime()) ? client.getLastTime() : curClient.getLastTime());
			}

			client.setIPAddress(client.getIPAddress().isEmpty() ? curClient.getIPAddress() : client.getIPAddress());
			client.setGPSInfo(client.getGPSInfo().isEmpty() ? curClient.getGPSInfo() : client.getGPSInfo());
			client.setSSID(client.getSSID().isEmpty() ? curClient.getSSID() : client.getSSID());
			client.setCDPDevice(client.getCDPDevice() != null ? client.getCDPDevice() : curClient.getCDPDevice());
			client.setCDPPortId(client.getCDPPortId() != null ? client.getCDPPortId() : curClient.getCDPPortId());
			client.setDHCPHostname(client.getDHCPHostname() != null ? client.getDHCPHostname() : curClient.getDHCPHostname());
			client.setDHCPVendor(client.getDHCPVendor() != null ? client.getDHCPVendor() : curClient.getDHCPVendor());
			client.setChannel(Math.max(client.getChannel(), curClient.getChannel()));

			mergeLists(client.getCarrier(), curClient.getCarrier());
			mergeLists(client.getEncoding(), curClient.getEncoding());
			mergeLists(client.getFreqMHZ(), curClient.getFreqMHZ());
			mergeLists(client.getTag(), curClient.getTag());

			mergePackets(client.getPackets(), curClient.getPackets());
			mergeSNRInfo(client.getSNRInfo(), curClient.getSNRInfo());
		});

		return client;
	}

	private <T> void mergeLists(List<T> main, List<T> other) {
		Set<T> set = new HashSet<>(main);
		set.addAll(other);

		main.clear();
		main.addAll(set);
	}

	private void mergePackets(Packets main, Packets other) {
		main.setCrypt(main.getCrypt() + other.getCrypt());
		main.setData(main.getData() + other.getData());
		main.setFragments(main.getFragments() + other.getFragments());
		main.setLLC(main.getLLC() + other.getLLC());
		main.setRetries(main.getRetries() + other.getRetries());
		main.setTotal(main.getTotal() + other.getTotal());
	}

	private void mergeSNRInfo(SNRInfo main, SNRInfo other) {
		main.setMaxNoiseDbm(Math.max(main.getMaxNoiseDbm(), other.getMaxNoiseDbm()));
		main.setMaxNoiseRssi(Math.max(main.getMaxNoiseRssi(), other.getMaxNoiseRssi()));
		main.setMaxSignalDbm(Math.max(main.getMaxSignalDbm(), other.getMaxSignalDbm()));
		main.setMaxSignalRssi(Math.max(main.getMaxSignalRssi(), other.getMaxSignalRssi()));

		main.setMinNoiseDbm(Math.min(main.getMinNoiseDbm(), other.getMinNoiseDbm()));
		main.setMinNoiseRssi(Math.min(main.getMinNoiseRssi(), other.getMinNoiseRssi()));
		main.setMinSignalDbm(Math.min(main.getMinSignalDbm(), other.getMinSignalDbm()));
		main.setMinSignalRssi(Math.min(main.getMinSignalRssi(), other.getMinSignalRssi()));
	}

	private void repairNumbers(List<WirelessClient> clients) {
		for (int j = 0; j < clients.size(); j++) {
			final WirelessClient client = clients.get(j);

			client.setNumber(j + 1);
		}
	}
}
