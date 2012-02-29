#!/bin/sh -e

test "$1" -eq 1 || exit 0 #whether or not this is the first install

# fix java.lang.ClassNotFoundException: org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory,
# see https://bugzilla.redhat.com/show_bug.cgi?id=217630#c7
wget https://staff.washington.edu/joshuadf/java/naming-factory-dbcp.jar -O /usr/share/tomcat6/lib/naming-factory-dbcp.jar

chown tomcat:tomcat -Rf /var/log/tntconcept /var/lib/tntconcept/upload /var/lib/tntconcept/reports

chkconfig mysqld on
service mysqld restart

mysqladmin create tnt

PASS=$(mktemp --dry-run XXXXXXXX)
mysql -e "grant all on tnt.* to 'tnt'@'%' identified by '$PASS'"
mysql -e "grant all on tnt.* to 'tnt'@'localhost' identified by '$PASS'"

(echo "set names utf8;"; cat /var/lib/tntconcept/sql/createTables.sql) | mysql -utnt -p$PASS tnt

sed -e "s,/PATH/TO/ttnconcept/configuration,/etc/tntconcept," \
    -e "s,password=\"tnt\",password=\"$PASS\"," \
    -i /etc/tntconcept/context.xml
mkdir -p /etc/tomcat6/Catalina/localhost
ln -fs ../../../tntconcept/context.xml /etc/tomcat6/Catalina/localhost/tntconcept.xml

sed -e "s,^# Unix version    - ,," \
    -i /etc/tntconcept/autentia.properties -i /etc/tntconcept/log4j.properties

ln -fs ../../java/mysql-connector-java.jar /usr/share/tomcat6/lib/mysql-connector-java.jar

chkconfig tomcat6 on
service tomcat6 restart

exit 0
