#!/bin/sh

TITLE="Vaateseuranta (TIEP111, kevät 2010, vipekorh)"
CDIR=`dirname "$0"`
DOCUMENT_PATH="$CDIR/../doc/j2h"
SRC_PATH="$CDIR/../src"

if [ ! -d "$DOCUMENT_PATH" ]; then
    mkdir -p $DOCUMENT_PATH
fi

j2h -d "$DOCUMENT_PATH" -js "$SRC_PATH" -n "$TITLE"
