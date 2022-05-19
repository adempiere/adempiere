#!/bin/sh
################################################################################
#  ADempiere Setting script                                                   ##
#  Setting ADempiere Server                                                   ##
################################################################################
	
echo "Setting Binary folder" 
cd /opt/binaryapp/
for d in */ ; do [ -L "${d%/}" ] && continue; mv "/opt/binaryapp/$d"* /opt/binaryapp/ ; done
echo "ADempiere Home..." 
cd $ADEMPIERE_HOME
cp AdempiereEnvTemplate.properties AdempiereEnv.properties
sed -i "s|JAVA_HOME=/usr/lib/jvm/jdk-11|JAVA_HOME=$JAVA_HOME|g" AdempiereEnv.properties 
sed -i "s|ADEMPIERE_HOME=C.*|ADEMPIERE_HOME=$ADEMPIERE_HOME|g" AdempiereEnv.properties
sed -i "s|ADEMPIERE_APPS_PATH=/opt/tomcat|ADEMPIERE_APPS_PATH=/opt/binaryapp|g" AdempiereEnv.properties 
sed -i "s|ADEMPIERE_APPS_TYPE=tomcat|ADEMPIERE_APPS_TYPE=$ADEMPIERE_APPS_TYPE|g" AdempiereEnv.properties
