#!/bin/bash
OUTPUT="$1" # zip file
INPUTS="" # java files
for i in $(seq 2 $#) # java file arguments
do
    FILE=${!i} # i-th command line argument
    if [[ $(head -n 1 $FILE ) == *"package"* ]]; # if the first line is a package name
    then
        tail -n +2 "$FILE" > "$FILE.tmp" && mv "$FILE.tmp" "$FILE" # remove the package name
    fi
    INPUTS="$INPUTS $FILE" # add the modified file to the list of inputs
done

zip -r -j $OUTPUT $INPUTS # zip the files