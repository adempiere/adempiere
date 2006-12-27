#	My Development Environment
#
#	This script sets variable for compiling Adempiere from source
#	Ported from Windows script Marek Mosiewicz<marek.mosiewicz@jotel.com.pl>
#	If you have difficulties, compare it with the Windows version.
#	
# 	$Header: /cvsroot/adempiere/utils_dev/myDevEnvTemplate.sh,v 1.6 2003/04/27 12:34:16 marekmosiewicz Exp $
#
#  Check the following parameters:
#  -------------------------------

if [ $JAVA_HOME ]; then
  export PATH=$JAVA_HOME/bin:$PATH	
else
  echo JAVA_HOME is not set.
  echo You may not be able to build Adempiere
  echo Set JAVA_HOME to the directory of your local JDK.
fi


#Set Adempiere Source Directory - default one dir up from place where script resides

SAVED_DIR=`pwd`			#save current dir
cd `dirname $0`/..		#change dir to one up form place where script resides - doesn not work with sym links
export ADEMPIERE_SOURCE=`pwd`	#this is adempiere source
cd $SAVED_DIR			#back to the saved directory

echo ADEMPIERE_SOURCE is $ADEMPIERE_SOURCE

if [ ! -d $ADEMPIERE_SOURCE/client ] ; then
	echo "** ADEMPIERE_SOURCE NOT found"
fi  

#	Passwords for the keystore
export KEYTOOL_PASS=$KEY_PASSWORD
if [ ! $KEYTOOL_PASS ] ; then
	export KEYTOOL_PASS=myPassword
fi

#	Keystore & FTP Password
export ANT_PROPERTIES=-Dpassword=$KEYTOOL_PASS -DftpPassword=$FTP_PASSWORD

#	Ant to send email after completion - change or delete
#export ANT_PROPERTIES="$ANT_PROPERTIES -DMailLogger.mailhost=xxx -DMailLogger.from=xxxx -DMailLogger.failure.to=xxxx -DMailLogger.success.to=xxxx"

#	Automatic Installation - Where Adempiere will be unzipped
export ADEMPIERE_ROOT=/adempiere

#	Automatic Installation - Resulting Home Directory
export ADEMPIERE_HOME=$ADEMPIERE_ROOT/Adempiere

#	Automatic Installation - Share for final Installers
export ADEMPIERE_INSTALL=/adempiere/install
if [ ! -d $ADEMPIERE_INSTALL ] ; then
    mkdir -p $ADEMPIERE_INSTALL
fi  

#  ---------------------------------------------------------------
#  In most cases you don't need to change anything below this line
#  If you need to define something manually do it above this line,
#  it should work, since most variables are checked before set.
#  ---------------------------------------------------------------

export CURRENTDIR=`pwd`

#  Set Version
export ADEMPIERE_VERSION=ADempiere
export ADEMPIERE_VERSION_FILE=313
export ADEMPIERE_VENDOR=ADempiere

export ENCODING=UTF-8

#	ClassPath
if  [ ! -f $JAVA_HOME/lib/tools.jar ] ; then
	echo "** Need full Java SDK **"
fi
export CLASSPATH=$CLASSPATH:$JAVA_HOME/lib/tools.jar

if  [ ! -f $ADEMPIERE_SOURCE/tools/lib/ant.jar ] ;then
	echo "** Ant.jar NOT found **"
fi
export CLASSPATH=$CLASSPATH:$ADEMPIERE_SOURCE/tools/lib/ant.jar:$ADEMPIERE_SOURCE/tools/lib/ant-launcher.jar:$ADEMPIERE_SOURCE/tools/lib/ant-swing.jar:$ADEMPIERE_SOURCE/tools/lib/ant-commons-net.jar:$ADEMPIERE_SOURCE/tools/lib/commons-net.jar
#export CLASSPATH=$CLASSPATH:$ADEMPIERE_SOURCE/jboss/lib/xml-apis.jar

#	Set XDoclet 1.1.2 Environment
export XDOCLET_HOME=$ADEMPIERE_SOURCE/tools

#	.
#	This is the keystore for code signing.
#	Replace it with the official certificate.
#	Note that this is not the SSL certificate.
#	.

if [ ! -d $ADEMPIERE_SOURCE/keystore ] ; then
    mkdir $ADEMPIERE_SOURCE/keystore			#create dir
fi    
	    
# check 	
if  [ ! -f $ADEMPIERE_SOURCE/keystore/myKeystore ] || [ ! "keytool -list -alias adempiere -keyStore $ADEMPIERE_SOURCE/keystore/myKeystore -storepass $KEYTOOL_PASS" ] ; then		     
     # 	This is a dummy keystore for localhost SSL		     
     #	replace it with your SSL certifificate.		     
     #	Please note that a SSL certificate is 		     
     #	different from the code signing certificate.
     #	The SSL does not require an alias of adempiere and		     
     #	there should only be one certificate in the keystore
		     
    HOSTNAME=`hostname`
			
			
    echo No Keystore found, creating for $HOSTNAME ...
			    
    KEYTOOL_DNAME="CN=$HOSTNAME, OU=myName, O=AdempiereUser, L=myTown, ST=myState, C=US"

    keytool -genkey -keyalg rsa -alias adempiere -dname "$KEYTOOL_DNAME" -keypass $KEYTOOL_PASS -validity 365 -keystore $ADEMPIERE_SOURCE/keystore/myKeystore -storepass $KEYTOOL_PASS
    keytool -selfcert -alias adempiere -dname "$KEYTOOL_DNAME" -keypass $KEYTOOL_PASS -validity 180 -keystore $ADEMPIERE_SOURCE/keystore/myKeystore -storepass $KEYTOOL_PASS
fi

# Set ADEMPIERE_ENV for all other scripts.
export ADEMPIERE_ENV=Y
