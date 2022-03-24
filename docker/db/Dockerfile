## Postgres Database Service
FROM postgres:11-alpine

ARG ADEMPIERE_BINARY=Adempiere_393LTS.tar.gz

ENV ADEMPIERE_BINARY $ADEMPIERE_BINARY
ENV DB_NAME adempiere
ENV ADEMPERE_PASSWORD adempiere

ADD $ADEMPIERE_BINARY /opt/
COPY --chown=1  initdb.sh /docker-entrypoint-initdb.d

RUN mv /opt/Adempiere/data/Adempiere_pg.dmp /docker-entrypoint-initdb.d/  && \
	rm -R -f /opt/Adempiere* && \
	chmod +x /docker-entrypoint-initdb.d/initdb.sh