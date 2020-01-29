#!/usr/bin/env bash

set -o allexport
source .env;
set +o allexport

activator -jvm-debug "8056" "run 9056";