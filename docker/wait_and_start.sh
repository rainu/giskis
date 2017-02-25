#!/bin/bash

###
# VARIABLES
###

###
# FUNCTIONS
###

waitFor() {
    CMD=$1
    while true; do
        sh -c "$CMD" > /dev/null 2>&1

        if [ $? -eq 0 ]; then
            break
        else
            sleep 5
        fi
    done
}

waitForMongo() {
    waitFor "curl http://$MONGO_HOST:$MONGO_PORT"
}

###
# MAIN
###

waitForMongo

java -jar /giskis.jar