## Quick reference

- **Mantained by:**  [Adempiere ERP](http://adempiere.io/)
- **Where to get help:** [Gitter community ADempiere](https://gitter.im/adempiere/adempiere), [Git Project Adempiere](https://github.com/adempiere/adempiere)

## What is ADempiere?

The ADempiere project was created in September 2006 after a long running disagreement between [ComPiere Inc.](http://wiki.adempiere.io/Difference_With_Compiere), the developers of Compiere™, and the community that formed around that project. The community believed Compiere Inc. placed too much emphasis on the open source lock-in/commercial nature of the project, rather than the community sharing/enriching nature of the project, and after an impassioned discussion decided to split from Compiere™ giving birth to the ADempiere project.

## How to build?
	
** Before build: **

	- Build Adempiere Source Code
	- cd docker
	- cp ../install/build/Adempiere_393LTS.tar.gz db
	- cp ../install/build/Adempiere_393LTS.tar.gz app
	

**Build a Database Image:**

	- docker build -t adempiere.db --build-arg ADEMPIERE_BINARY=Adempiere_393LTS.tar.gz db


**Build a Application Image using tomcat:** 

	- curl -L https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.60/bin/apache-tomcat-9.0.60.tar.gz > app/apache-tomcat-9.0.60.tar.gz
	- docker build --build-arg ADEMPIERE_APPS=apache-tomcat-9.0.60.tar.gz \
					 --build-arg ADEMPIERE_BINARY=Adempiere_393LTS.tar.gz \
					 --build-arg ADEMPIERE_APPS_TYPE=tomcat \
					 -t adempiere.app app


**Build a Application Image using Wildfly:**

	- curl -L https://github.com/wildfly/wildfly/releases/download/26.0.1.Final/wildfly-26.0.1.Final.tar.gz > app/wildfly-26.0.1.Final.tar.gz
	- docker build --build-arg ADEMPIERE_APPS=wildfly-26.0.1.Final.tar.gz \
					 --build-arg ADEMPIERE_BINARY=Adempiere_393LTS.tar.gz \
					 --build-arg ADEMPIERE_APPS_TYPE=wildfly \
					 -t adempiere.app app
					 
**Build a Application Image using Jetty:**


	- curl -L https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-home/10.0.8/jetty-home-10.0.8.tar.gz > app/jetty-home-10.0.8.tar.gz
	- docker build --build-arg ADEMPIERE_APPS=jetty-home-10.0.8.tar.gz \
					 --build-arg ADEMPIERE_BINARY=Adempiere_393LTS.tar.gz \
					 --build-arg ADEMPIERE_APPS_TYPE=jetty \
					 -t adempiere.app app
					 
## How to Run?

    docker-compose up -d
