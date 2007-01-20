@IF NOT EXIST %JAVA_HOME%\bin ECHO "** JAVA_HOME NOT found"

%JAVA_HOME%/bin/java -Xms64M -Xmx256M -jar ./../druid/druid.jar -proj:./druid/Project-ID-TEMPLATE.druid