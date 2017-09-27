#!/bin/bash

# Serasoft S.r.l. - sergio.ramazzina@serasoft.it
# Easy script to adapt current ADempiere structure to Maven like schema

# Reworking Base
echo "Relocating base..."
cd ../../base

mkdir -p ./src/main/java
mkdir -p ./src/main/resources
mkdir -p ./src/test/java
mkdir -p ./src/test/resources
touch ./src/main/resources/.gitkeep
touch ./src/test/java/.gitkeep
touch ./src/test/resources/.gitkeep

mv ./src/com ./src/main/java
mv ./src/org ./src/main/java

rm ./*.launch
rm ./Base.html
rm ./documentation.bat
rm ./Swingset.bat
rm ./swing.properties
rm ./packages.txt
rm ./packaging-build.xml
rm ./xdoclet-build.xml
rm ./.project ./.packaging ./.xdoclet ./.classpath ./.settings

# Reworking Client
echo "Relocating client..."

cd ../client

mkdir -p ./src/main/java
mkdir -p ./src/main/resources
mkdir -p ./src/test/java
mkdir -p ./src/test/resources
touch ./src/main/resources/.gitkeep
touch ./src/test/java/.gitkeep
touch ./src/test/resources/.gitkeep

mv ./src/de ./src/main/java
mv ./src/org ./src/main/java

rm ./.project ./.classpath
rm ./packages.txt
rm ./documentation.bat ./.settings

# Reworking org.adempiere.asset
echo "Relocating org.adempiere.asset ..."

cd ../org.adempiere.asset

mkdir -p ./src/main/resources
mkdir -p ./src/test/java
mkdir -p ./src/test/resources
touch ./src/main/resources/.gitkeep
touch ./src/test/java/.gitkeep
touch ./src/test/resources/.gitkeep

mv ./src/main/java/base/org ./src/main/java

# Reworking Tools
echo "Relocating tools..."

cd ../tools

mkdir -p ./src/main/java
mkdir -p ./src/main/resources
mkdir -p ./src/test/java
mkdir -p ./src/test/resources
touch ./src/main/resources/.gitkeep
touch ./src/test/java/.gitkeep
touch ./src/test/resources/.gitkeep

mv ./src/org ./src/main/java

rm ./.settings ./.classpath ./.project ./documentation.bat ./packages.txt

# Reworking zkwebui
echo "Relocating zkwebui..."

cd ../zkwebui

mkdir -p ./src/main/java
mkdir -p ./src/main/resources
mkdir -p ./src/main/webapp
mkdir -p ./src/test/java
mkdir -p ./src/test/resources

touch ./src/test/java/.gitkeep
touch ./src/test/resources/.gitkeep

mv ./WEB-INF/lib ./
mv ./WEB-INF/src/org ./src/main/java
mv ./WEB-INF/src/metainfo ./src/main/resources

mv ./css ./src/main/webapp
mv ./images ./src/main/webapp
mv ./images_old ./src/main/webapp
mv ./js ./src/main/webapp
mv ./theme ./src/main/webapp
mv ./zul ./src/main/webapp

rm -Rf ./WEB-INF/src
mv ./WEB-INF ./src/main/webapp

mv ./index.zul ./src/main/webapp
mv ./timeout.zul ./src/main/webapp
mv ./theme.zs ./src/main/webapp

rm ./.settings ./.classpath ./.project ./.tomcatplugin


# Cleanup things in project's root
cd ..
rm ./.settings ./.flow ./.classpath ./.project ./testTemplate.properties

# Copy updated build scripts
cd ../utils_dev/gradle.001
cp ./etc/base.build.xml ../../base/build.xml
cp ./etc/client.build.xml ../../client/build.xml
cp ./etc/org.adempiere.asset.build.xml ../../org.adempiere.asset/build.xml
cp ./etc/org.adempiere.pos.build.xml ../../org.adempiere.pos/build.xml
cp ./etc/org.eevolution.fleet.build.xml ../../org.eevolution.fleet/build.xml
cp ./etc/org.eevolution.freight.build.xml ../../org.eevolution.freight/build.xml
cp ./etc/org.eevolution.hr_and_payroll.build.xml ../../org.eevolution.hr_and_payroll/build.xml
cp ./etc/org.eevolution.manufacturing.build.xml ../../org.eevolution.manufacturing/build.xml
cp ./etc/org.eevolution.warehouse.build.xml ../../org.eevolution.warehouse/build.xml
cp ./etc/zkwebui.build.xml ../../zkwebui/build.xml
