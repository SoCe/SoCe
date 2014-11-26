#!/bin/bash

function start {
   cd /opt/hazelcast
   rm -f /opt/hazelcast/hazelcast.pid
   javaCmd = "/my/java/home/bin/java -server -cp hazelcast.jar:apache-log4j-2.0-beta9.jar -Dhazelcast.config=/opt/hazelcast/hazelcast.xml -Dlog4j.configurationFile=/opt/hazelcast/log4j2.xml com.hazelcast.examples.StartServer"
   cmd="nohup $javaCmd >> /opt/hazelcast/service.log 2>&1 & echo \$! >/opt/hazelcast/hazelcast.pid"
   su -c "$cmd"
   return 0; }


function stop {
   pid="$(</opt/hazelcast/hazelcast.pid)"
   kill -s KILL $pid || return 1
   return 0; }


function main {
   RETVAL=0
   case "$1" in
      start)                                               
         start
         ;;
      stop)                                                
         stop
         ;;
      *)
         echo "Usage: $0 {start|stop}"
         exit 1
         ;;
      esac
   exit $RETVAL
}


main $1