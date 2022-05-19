ARG JDK_RELEASE $JDK_RELEASE
FROM eclipse-temurin:$JDK_RELEASE

#Arguments for Build
ARG ADEMPIERE_APPS=apache-tomcat.tar.gz
ARG ADEMPIERE_APPS_TYPE=tomcat
ARG ADEMPIERE_BINARY=Adempiere_393LTS.tar.gz

#Environments Variables
ENV ADEMPIERE_DB_TYPE PostgreSQL
ENV ADEMPIERE_DB_HOST localhost
ENV ADEMPIERE_DB_PORT 5432
ENV ADEMPIERE_DB_NAME adempiere
ENV ADEMPIERE_DB_USER adempiere
ENV ADEMPIERE_DB_PASSWORD adempiere
ENV ADEMPIERE_DB_ADMIN_PASSWORD postgres
ENV ADEMPIERE_WEB_PORT 8888
ENV ADEMPIERE_SSL_PORT 4443
ENV ADEMPIERE_JAVA_OPTIONS -Xms128m -Xmx1024m
ENV ADEMPIERE_HOME /opt/Adempiere

#Set Workdir
WORKDIR $ADEMPIERE_HOME

#Copy Binary File
ADD $ADEMPIERE_BINARY /opt/
ADD $ADEMPIERE_APPS /opt/binaryapp/

#Copy Utilities
COPY setting.sh /opt/
COPY entrypoint.sh /opt/

RUN groupadd adempiere && \
	useradd -r -u 1001 -g adempiere adempiere && \
	chown -R adempiere $ADEMPIERE_HOME && \
	chown -R adempiere /opt/binaryapp/ && \
	/opt/setting.sh

EXPOSE $ADEMPIERE_WEB_PORT
EXPOSE $ADEMPIERE_SSL_PORT

USER adempiere
ENTRYPOINT /opt/entrypoint.sh