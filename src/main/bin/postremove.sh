#!/bin/sh -e

test "$1" -eq 0 || exit 0 #whether or not this is the last uninstall

rm -rf /etc/tomcat6/Catalina/localhost/tntconcept.xml

mysqladmin --force drop tnt

exit 0
