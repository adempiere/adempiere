## Quick reference

- **Mantained by:**  [Adempiere ERP](http://adempiere.io/)
- **Where to get help:** [Gitter community ADempiere](https://gitter.im/adempiere/adempiere), [Git Project Adempiere](https://github.com/adempiere/adempiere)

## What is ADempiere?

The ADempiere project was created in September 2006 after a long running disagreement between [ComPiere Inc.](http://wiki.adempiere.io/Difference_With_Compiere), the developers of Compiere™, and the community that formed around that project. The community believed Compiere Inc. placed too much emphasis on the open source lock-in/commercial nature of the project, rather than the community sharing/enriching nature of the project, and after an impassioned discussion decided to split from Compiere™ giving birth to the ADempiere project.

## How to build?
	
** Before build: **

	- Build Adempiere Source Code
	- cd docker
	- cp ../install/build/Adempiere_393LTS.tar.gz adempiere
	- cp ../install/build/Adempiere_393LTS.tar.gz adempiere-postgres
	- tar -xzvf adempiere-postgres/Adempiere_393LTS.tar.gz -C adempiere-postgres/ Adempiere/data/Adempiere_pg.dmp
	
	

**Build a Database Image:**

	- docker build -t adempiere.db --build-arg ADEMPIERE_DB=Adempiere_pg.dmp \
									  --build-arg POSTGRES_RELEASE=14 \
									   adempiere-postgres


**Build a Application Image using Tomcat:** 

	- curl -L https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.63/bin/apache-tomcat-9.0.63.tar.gz > adempiere/apache-tomcat.tar.gz
	- docker build --build-arg ADEMPIERE_APPS=apache-tomcat.tar.gz \
					 --build-arg ADEMPIERE_BINARY=Adempiere_393LTS.tar.gz \
					 --build-arg ADEMPIERE_APPS_TYPE=tomcat \
					 --build-arg JDK_RELEASE=11-jdk \
					 -t adempiere.app adempiere


**Build a Application Image using Wildfly:**

	- curl -L https://github.com/wildfly/wildfly/releases/download/26.0.1.Final/wildfly-26.0.1.Final.tar.gz > app/wildfly.tar.gz
	- docker build --build-arg ADEMPIERE_APPS=wildfly.tar.gz \
					 --build-arg ADEMPIERE_BINARY=Adempiere_393LTS.tar.gz \
					 --build-arg ADEMPIERE_APPS_TYPE=wildfly \
					 --build-arg JDK_RELEASE=11-jdk \
					 -t adempiere.app adempiere
					 
**Build a Application Image using Jetty:**


	- curl -L https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-home/10.0.8/jetty-home-10.0.8.tar.gz > app/jetty.tar.gz
	- docker build --build-arg ADEMPIERE_APPS=jetty.tar.gz \
					 --build-arg ADEMPIERE_BINARY=Adempiere_393LTS.tar.gz \
					 --build-arg ADEMPIERE_APPS_TYPE=jetty \
					 --build-arg JDK_RELEASE=11-jdk \
					 -t adempiere.app adempiere
					 
## How to Run?

    docker-compose up -d
