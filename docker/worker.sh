#!/bin/bash

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

waitForRedis() {
    waitFor "redis-cli -h \"$REDIS_HOST\" -p $REDIS_PORT -c \"CLIENT LIST\""
}

waitForFile() {
    while true; do
        FOUND=$(grep "</detection-run>" -r $SCAN_DIR/*.netxml 2>/dev/null | wc -l)
        if [ "$FOUND" = "0" ]; then
            sleep 5
        else
            break
        fi
    done
}

processFile() {
    FILES=($(grep "</detection-run>" -rH $SCAN_DIR/*.netxml | cut -d\: -f1))
    for FILE in "${FILES[@]}"; do
       echo "Process file $FILE"

       mv $FILE $OUTPUT_DIR/$(basename "$FILE")
    done
}

###
# MAIN
###

waitForPostgres
waitForRedis

while true; do
    echo "Wait for file(s)..."
    waitForFile
    echo "...done."

    processFile
done