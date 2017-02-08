@Title Copy Libraries from Source

@Echo Log4j		http://jakarta.apache.org/log4j
@xcopy C:\Sources\jakarta-log4j-1.2.8\dist\lib\log4j-1.2.8.jar log4j.jar /V /D


@Echo Mail
@xcopy C:\Sources\jaf-1.0.2\activation.jar /V /D
@xcopy C:\Sources\javamail-1.3\mail.jar /V /D


@Echo JSTL		http://jakarta.apache.org/taglibs
@xcopy C:\Sources\jakarta-taglibs\standard-1.0.3\lib\jstl.jar /V /D
@xcopy C:\Sources\jakarta-taglibs\standard-1.0.3\lib\standard.jar /V /D
@xcopy C:\Sources\jakarta-taglibs\standard-1.0.3\lib\saxpath.jar /V /D


@Echo OracleRowSet	ocrs12.jar


@Echo JUnit		http://www.junit.org
@xcopy C:\Sources\junit3.8.1\junit.jar /V /D

@Echo Ant in distribution		http://ant.apache.org
@xcopy C:\Sources\apache-ant-1.6.2\lib\ant.jar /V /D

@pause