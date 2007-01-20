CVS=cvs
RM=rm
CVSROOT=:pserver:anonymous@cvs.sourceforge.net:/cvsroot/compiere
#JAVAHOME=c:\jdk1.5.0_01
JAVAHOME=c:\j2sdk1.4.2_06
JAVAC=$(JAVAHOME)\bin\javac
JAVA=$(JAVAHOME)\bin\java

#usage at de top directory (containing base, client...)
#make -f JasperReports/scripts/Makefile all
version:
	$(JAVAC)  JasperReports\scripts\Version.java -classpath lib\Compiere.jar
	
patch: version
	$(RM) -f JasperReports/patch-$(shell $(JAVA) JasperReports.scripts.Version ).diff
	-$(CVS) -d $(CVSROOT) diff -N -u base client data db dbPort extend install interfaces jboss launch looks print serverApps serverRoot utils utils_dev tools webStore>> JasperReports/patch-$(shell $(JAVA) JasperReports.scripts.Version ).diff

pack: version
	$(RM) -f compierejasper-$(shell $(JAVA) JasperReports.scripts.Version ).tar.bz2
	tar -cvjf compierejasper-$(shell $(JAVA) JasperReports.scripts.Version ).tar.bz2 --exclude=JasperReports/lib --exclude=JasperReports/build --exclude=JasperReports/needed JasperReports tools/lib/barbecue-1.1.jar tools/lib/iReport.jar tools/lib/commons-beanutils-1.7.jar tools/lib/itext-1.2.jar tools/lib/commons-collections-3.1.jar tools/lib/commons-digester-1.6.jar tools/lib/commons-logging-1.4.jar tools/lib/jasperreports-0.6.5.jar tools/lib/jfreechart-0.9.21.jar tools/lib/xercesImpl.jar tools/lib/xml-apis.jar 
	
all: patch pack