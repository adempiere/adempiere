#!/bin/bash

# Serasoft S.r.l. - sergio.ramazzina@serasoft.it
# Easy script to adapt current ADempiere structure to Maven like schema

# Reworking JasperReports
echo "Relocating JasperReports..."
cd ../../JasperReports

mkdir -p ./src/main/java
mkdir -p ./src/main/resources
mkdir -p ./src/test/java
mkdir -p ./src/test/resources
touch ./src/main/resources/.gitkeep
touch ./src/test/java/.gitkeep
touch ./src/test/resources/.gitkeep

mv ./src/org ./src/main/java

rm ./JasperReports.jardesc
rm -Rf ./.project ./.classpath ./.settings

# Reworking JasperReportsTools
echo "Relocating JasperReportsTools..."
cd ../JasperReportsTools

rm ./JasperReportsTools.jardesc
rm -Rf ./.project ./.classpath ./.settings

# Removing JasperReportsWebApp because unnecessary
rm -Rf ../JasperReportsWebApp

# Copy updated build scripts
cd ../utils_dev/gradle.001
cp ./etc/JasperReports.build.xml ../../JasperReports/build.xml
