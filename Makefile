#!/usr/bin/make -f

SCHEME ?= tnt
DUMPFILE ?= $(CURDIR)/$(SCHEME).sql

dump:
	-cp -v $(DUMPFILE) $(DUMPFILE)_$(shell date +%s)
	mysqldump -uroot -R --skip-extended-insert -v $(SCHEME) > $(DUMPFILE)

recreate:
	-mysql -uroot -e 'drop database $(SCHEME);'
	mysql -uroot -e 'create database $(SCHEME);'
	mysql -uroot -v $(SCHEME) < $(DUMPFILE)

.PHONY: recreate

DBHOST ?= localhost
DBNAME ?= $(SCHEME)
DBPASS ?=
DBUSER ?= root

updatedb:
	mvn package
	java \
	 -Dhibernate.connection.driver_class=com.mysql.jdbc.Driver \
	 -Dhibernate.connection.url=jdbc:mysql://$(DBHOST):3306/$(DBNAME)?autoReconnect=true\&connectionCollation=utf8_spanish_ci\&characterEncoding=UTF-8\&useUnicode=true \
	 -Dhibernate.connection.username=$(DBUSER) \
	 -Dhibernate.connection.password=$(DBPASS) \
	 -Dhibernate.dialect=org.hibernate.dialect.MySQLDialect \
	 -cp target/classes:$(shell for x in target/tntconcept/WEB-INF/lib/*.jar; do echo -n $$x:; done;) \
	 org.hibernate.tool.hbm2ddl.SchemaUpdate \
	 src/main/resources/hibernate.cfg.xml \
	 $(shell for x in src/main/resources/com/autentia/intra/businessobject/config/*.hbm.xml; do echo -n $$x' '; done;)
