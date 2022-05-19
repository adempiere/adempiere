## Postgres Database Service
ARG POSTGRES_RELEASE $POSTGRES_RELEASE
FROM postgres:$POSTGRES_RELEASE

ARG ADEMPIERE_DB=Adempiere_pg.dmp

ENV ADEMPIERE_DB $ADEMPIERE_DB
ENV DB_NAME adempiere
ENV ADEMPERE_PASSWORD adempiere

ADD --chown=postgres $ADEMPIERE_DB /tmp
COPY --chown=postgres  initdb.sh /docker-entrypoint-initdb.d

RUN chmod +x /docker-entrypoint-initdb.d/initdb.sh