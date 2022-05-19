#!/bin/sh

################################################################################
#  ADempiere start script                                                     ##
#  Starting ADempiere Server                                                  ##
################################################################################

echo "Replace variables on ADempiere Environment File..."
cd $ADEMPIERE_HOME
echo "Building..." 
if [ ! -f Adempiere.properties ] 
then 
    sed -i "s|ADEMPIERE_KEYSTORE=C*|ADEMPIERE_KEYSTORE=\/data\/app\/Adempiere\/keystore\/myKeystore|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_DB_TYPE=PostgreSQL|ADEMPIERE_DB_TYPE=$ADEMPIERE_DB_TYPE|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_DB_SERVER=localhost|ADEMPIERE_DB_SERVER=$ADEMPIERE_DB_HOST|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_DB_PORT=5432|ADEMPIERE_DB_PORT=$ADEMPIERE_DB_PORT|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_DB_NAME=adempiere|ADEMPIERE_DB_NAME=$ADEMPIERE_DB_NAME|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_DB_USER=adempiere|ADEMPIERE_DB_USER=$ADEMPIERE_DB_USER|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_DB_PASSWORD=adempiere|ADEMPIERE_DB_PASSWORD=$ADEMPIERE_DB_PASSWORD|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_DB_SYSTEM=postgres|ADEMPIERE_DB_SYSTEM=$ADEMPIERE_DB_ADMIN_PASSWORD|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_APPS_SERVER=0.0.0.0|ADEMPIERE_APPS_SERVER=$(hostname)|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_WEB_PORT=8888|ADEMPIERE_WEB_PORT=$ADEMPIERE_WEB_PORT|g" AdempiereEnv.properties
    sed -i "s|ADEMPIERE_SSL_PORT=4444|ADEMPIERE_SSL_PORT=$ADEMPIERE_SSL_PORT|g" AdempiereEnv.properties
    sed -i "s|-Xms128m -Xmx1024m|$ADEMPIERE_JAVA_OPTIONS|g" AdempiereEnv.properties
    sh RUN_silentsetup.sh 
fi  
echo "Running..." 
utils/RUN_Server2.sh && tail -f /dev/null
exit 0
