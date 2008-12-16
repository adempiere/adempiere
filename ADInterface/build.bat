@call mvn %1 clean
@call mvn %1 compile
@call mvn %1 install war:war

rem @copy target\GCInterface-1.0.war \\xanax\temp\amber\