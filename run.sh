#!/bin/bash

# Target the compiled binaries directory
cd bin

# Dynamically fetch SWI-Prolog home directory using the swipl command
# This works seamlessly if swipl is in the system's environment variables (PATH)
SWIPL_BASE=$(swipl --dump-runtime-variables | grep PLBASE | cut -d '"' -f 2)

if [ -z "$SWIPL_BASE" ]; then
    echo "[ERROR] SWI-Prolog (swipl) not found in system environment variables."
    exit 1
fi

# Dynamically construct paths based on the system's active installation
JPL_JAR="$SWIPL_BASE/lib/jpl.jar"
LIB_PATH="$SWIPL_BASE/lib"

# Execute using Unix colon (:) classpath separator
java -cp ".:$JPL_JAR" -Djava.library.path="$LIB_PATH" application.Aplicacao

cd ..