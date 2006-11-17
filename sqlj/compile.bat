@Title Compile + Jar SQLJ
@Rem	@version $Id: compile.bat,v 1.5 2005/02/04 17:23:33 jjanke Exp $
@Rem
@Rem	Note that some databases require an older Java version
@Rem	and that the Zip is uncompressed
@Rem
@Rem	Oracle: 1.4.2 - (you can use RUN_Build)
@Rem	Sybase: 1.2.2 - 
@Rem
@SET PATH=C:\jdk1.2.2\bin;%PATH%
@SET JAVA_HOME=C:\jdk1.2.2
@java -version

javac -sourcepath src -d lib src/org/adempiere/sqlj/Adempiere.java src/org/adempiere/sqlj/Product.java src/org/adempiere/sqlj/Currency.java src/org/adempiere/sqlj/BPartner.java src/org/adempiere/sqlj/Invoice.java src/org/adempiere/sqlj/Payment.java src/org/adempiere/sqlj/PaymentTerm.java src/org/adempiere/sqlj/Account.java

jar cf0 sqlj.jar -C lib org/adempiere/sqlj

pause