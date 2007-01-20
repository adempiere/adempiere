This a template project showing how to organize and store Compiere customizations.

This is work in progress, so please be patient.

Any comments and requests for improvements are wellcome.

Any improvements and patches are very wellcome and highly appreciated.

HowTo.

http://prdownloads.sourceforge.net/comxe/_Project-ID-Example.swf?download

1) Copy "build-ID-001.[bat|sh].sample" into "build-ID-001.[bat|sh]"

2) Modify "build-ID-001.[bat|sh]" file and set proper setting for:
COMPIERE_HOME
JAVA_HOME

3) Copy "build-ID-001.properties.sample" into "build-ID-001.properties"

4) Modify "build-ID-001.properties" file and set proper setting for:
oracle.host=
oracle.port=1521
oracle.sid=
oracle.search=
oracle.username=
oracle.password=

5) Open a shell console and execute commands:
"build-ID-001.[bat|sh] createDB"
"build-ID-001.[bat|sh] alterDB"
"build-ID-001.[bat|sh] model"
"build-ID-001.[bat|sh] view"

Enjoy new functionality.
Records now should be imported into Compiere AD.
