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

waitForPostgres() {
    waitFor "echo $PG_PASSWORD | psql -h \"$PG_HOST\" -p $PG_PORT -U \"$PG_USER\" -c '\l'"
}

###
# MAIN
###

waitForPostgres

java -jar /giskis.jar