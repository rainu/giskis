# giskis
GisKis stores the information from kismet in a database so that the user can generate graphs using SQL.

# How to build

```
 cd <project-root>
 mvn clean install
 docker build -t rainu/giskis ./ 
```

# How to execute
After building the executable:
```
 $> cd <project-root>
 $project-root> docker-compose up
```

# How to use
GisKis watches a configurable (see docker-compose.yml) directory for incoming files. Each file (recursively)
will be proceed. After finishing a file, the file will be moved to a configurable output directory.

GisKis has no command-line-interface! Based on the incoming file, GisKis will perform some actions. In
the following each file types will be explained:

## *.netxml

Netxml-Files contains the payload of kismet. These files will be:
 * stored into the database
 * converted into kml-Files (contains all networks as placemarks)
 
## merge.kml

If GisKis found a **merge.kml** file inside the input directory, a kml file based on all data inside
the database will be generated. It will be placed into the output directory named like the following 
schema: **&lt;current ISO-Date&gt;.kml** The content of the file will be ignored. 

```bash
touch <in-dir>/merge.kml
```

## essid.kml

If GisKis found a **essid.kml** file inside the input directory, a kml file included with only networks with
the given essid will be generated. It will be placed into the output directory named like the following 
schema: **&lt;ESSID&gt;-&lt;current ISO-Date&gt;.kml** The content of the file must contains the requested **essid**.

```bash
echo "ESSID" > IN_DIR/essid.kml
```
 
## all other files

All other files will be moved into the output directory without processing them.