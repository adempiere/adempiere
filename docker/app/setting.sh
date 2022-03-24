#!/bin/sh
################################################################################
#  ADempiere Setting script                                                   ##
#  Setting ADempiere Server                                                   ##
################################################################################

echo "Setting Binary folder" 
cd $ADEMPIERE_APPS_PATH
for d in */ ; do [ -L "${d%/}" ] && continue; mv "$ADEMPIERE_APPS_PATH$d"* $ADEMPIERE_APPS_PATH ; done
echo "ADempiere Home..." 
cd $ADEMPIERE_HOME
chmod -Rf 755 *.sh 
chmod -Rf 755 utils/*.sh 
cp AdempiereEnvTemplate.properties AdempiereEnv.properties
sed -i "s|JAVA_HOME=/usr/lib/jvm/jdk-11|JAVA_HOME=$JAVA_HOME|g" AdempiereEnv.properties 
sed -i "s|ADEMPIERE_HOME=C.*|ADEMPIERE_HOME=$ADEMPIERE_HOME|g" AdempiereEnv.properties
sed -i "s|ADEMPIERE_APPS_PATH=/opt/tomcat|ADEMPIERE_APPS_PATH=$ADEMPIERE_APPS_PATH|g" AdempiereEnv.properties 
sed -i "s|ADEMPIERE_APPS_TYPE=tomcat|ADEMPIERE_APPS_TYPE=$ADEMPIERE_APPS_TYPE|g" AdempiereEnv.properties