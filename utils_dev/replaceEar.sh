:

# This is a temporary solution meanwhile we create the corresponding ant build to create ADInterface.ear
# this is just to integrate the changes into ADInterface.ear downloaded from
# http://www.3e.pl/get.php?file=repository/adclient/ADInterface.ear
# You must download and copy ADInterface.ear here renaming it as ADInterface_original.ear

# The ideal is to create an ADInterface.ear integrated into adempiere.ear - not needing to include Adempiere.jar or other jars

# This is assuming you have an Adempiere installed in ~/makes/Root342/Adempiere
# and you already ran RUN_setup there to integrate patches.jar into Adempiere.jar

rm -rf ADInterface_new.ear
mkdir ADInterface_new.ear
cd ADInterface_new.ear
jar xvf ../ADInterface_original.ear
mv ADInterface-1.0.war ADInterface-1.0_original.war
mkdir ADInterface-1.0_new.war
cd ADInterface-1.0_new.war
jar xvf ../ADInterface-1.0_original.war

rm WEB-INF/lib/Adempiere-320.jar
cp ~/makes/Root342/Adempiere/lib/Adempiere.jar WEB-INF/lib

rm WEB-INF/lib/AdempiereCLib-320.jar
cp ~/makes/Root342/Adempiere/lib/AdempiereCLib.jar WEB-INF/lib

rm WEB-INF/lib/xbean*.jar
cp ../../../lib/xbean*.jar WEB-INF/lib

rm WEB-INF/lib/xfire*.jar
cp ../../../lib/xfire*.jar WEB-INF/lib

for i in `find ../../../bin -name "*.class" | sed -e 's:^../../../bin/::'`
do
    mkdir -p `dirname WEB-INF/classes/$i`
    cp ../../../bin/$i WEB-INF/classes/$i
done

cp ../../../bin/META-INF/xfire/services.xml WEB-INF/classes/META-INF/xfire/services.xml

# exit

cd WEB-INF/classes
rm -rf pl
rm -rf schemaorg_apache_xmlbeans
jar xvf ../../../../../lib/pl3xE.jar
cd ../..

# exit

jar cvf ../ADInterface-1.0.war *
cd ..
rm ADInterface-1.0_original.war
rm -rf ADInterface-1.0_new.war
jar cvf ../ADInterface.ear *
cd ..
cp ADInterface.ear ~/makes/Root342/Adempiere/jboss/server/adempiere/deploy/ADInterface.ear
rm -rf ADInterface_new.ear
