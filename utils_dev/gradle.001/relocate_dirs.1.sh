#!/bin/bash

# Serasoft S.r.l. - sergio.ramazzina@serasoft.it
# Easy script to adapt current ADempiere structure to Maven like schema

# Reworking Base
echo "Relocating base..."
cd ./base

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
rm ./.project ./.packaging ./.xdoclet ./.classpath

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
rm ./documentation.bat

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

rm ./documentation.bat
rm ./packages.txt

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


