#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar gateway-1.0.0.jar