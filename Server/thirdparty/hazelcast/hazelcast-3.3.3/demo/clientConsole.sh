#!/bin/sh

java -server -Djava.net.preferIPv4Stack=true -cp ../lib/hazelcast-all-3.3.3.jar com.hazelcast.client.console.ClientConsoleApp