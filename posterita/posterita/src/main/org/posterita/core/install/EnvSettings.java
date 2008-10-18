package org.posterita.core.install;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.posterita.core.exception.FileOpException;

public class EnvSettings 
{
	public static boolean DEBUG = true;
	public static final double JAVA_VERSION_1_5 = 1.5;
	
	public static final String PATH_SEPARATOR = System.getProperty("file.separator");
	
	public static final String JAVA_HOME = "JAVA_HOME";
	public static final String COMPIERE_WEBSTORES = "COMPIERE_WEBSTORES";
	public static final String COMPIERE_DB_USER = "COMPIERE_DB_USER";
	public static final String COMPIERE_DB_NAME = "COMPIERE_DB_NAME";
	public static final String COMPIERE_DB_SYSTEM = "COMPIERE_DB_SYSTEM";
	public static final String COMPIERE_DB_PASSWORD = "COMPIERE_DB_PASSWORD";
	public static final String COMPIERE_HOME = "COMPIERE_HOME";
	public static final String COMPIERE_APPS_SERVER = "COMPIERE_APPS_SERVER";
	public static final String COMPIERE_WEB_ALIAS = "COMPIERE_WEB_ALIAS";
	public static final String COMPIERE_DB_TYPE = "COMPIERE_DB_TYPE";
	public static final String COMPIERE_DB_PORT = "COMPIERE_DB_PORT";
	public static final String COMPIERE_DB_SERVER = "COMPIERE_DB_SERVER";
	public static final String COMPIERE_DB_URL = "COMPIERE_DB_URL";
	public static final String COMPIERE_MAIL_SERVER = "COMPIERE_MAIL_SERVER";
	public static final String COMPIERE_APPS_DEPLOY = "COMPIERE_APPS_DEPLOY";
	public static final String COMPIERE_KEYSTORE = "COMPIERE_KEYSTORE";
	
	public static final String TEMP_DIR = "TempDir";
	public static final String CONNECTION = "Connection";
	
	public static final String OS_WINDOWS = "Windows";
	public static final String OS_LINUX = "Linux";
	
	public static File getJDKHomeDir() throws FileOpException
	{
		String javaHome = System.getProperty("java.home");
		
		if(javaHome.endsWith("jre")) // Implies running from JRE found within JDK Folder
		{
			int jInd = javaHome.lastIndexOf("jre");
			javaHome = javaHome.substring(0, jInd - 1);
		}

		int index = javaHome.lastIndexOf(PATH_SEPARATOR);
		index = index + 1;

		if(isOSWindows())
		{
			javaHome = javaHome.toLowerCase();
			int pInd = javaHome.indexOf("progra~1");
			if(pInd > 0)
				javaHome = javaHome.replaceAll("progra~1", "program files");
		}
		
		String jreFolder = javaHome.substring(index);
		String jdkFolder = jreFolder.replaceAll("jre", "jdk");
		
		File jdkDir = new File(javaHome.substring(0, index) + jdkFolder);
		if(!jdkDir.exists())
			throw new FileOpException("JDK Folder does not exists, file: " + jdkDir.getAbsolutePath());
		if(!jdkDir.isDirectory())
			throw new FileOpException("Path is not a directory, file: " + jdkDir.getAbsolutePath());
		
		return jdkDir;
	}
	
	public static File getUserDesktopDir() throws FileOpException
	{
		String userHome = System.getProperty("user.home");
		
		String desktopDirPath = userHome + PATH_SEPARATOR + "Desktop";
		
		File desktopDir = new File(desktopDirPath);
		
		if(!(desktopDir.exists() && desktopDir.isDirectory()))
			throw new FileOpException("Could not locate desktop directory");
		
		return desktopDir;
	}
	
	public static File getTempDir() throws FileOpException
	{
		String tempDirPath = System.getProperty("java.io.tmpdir");
		
		File tempDir = new File(tempDirPath);
		
		if(!(tempDir.exists() && tempDir.isDirectory()))
			throw new FileOpException("Could not locate Temporary Directory");
		
		return tempDir;
	}
	
	public static String getJavaMajorVersion()
	{
		String javaVersion = System.getProperty("java.version");
		javaVersion = javaVersion.substring(0, 3);
		
		return javaVersion;
	}
	
	public static boolean isJavaVersionOk()
	{
		boolean versionOk = false;
		
		String javaVersion = getJavaMajorVersion();
		try
		{
			double version = Double.parseDouble(javaVersion);
			if(version >= 1.5)
				versionOk = true;
		}
		catch(NumberFormatException ex)
		{
			versionOk = false;
		}
		return versionOk;
	}
	
	public static File getWorkingDir()
	{
		String workingDir = System.getProperty("user.dir");
		
		return new File(workingDir);
	}
	
	public static String getHostName() throws UnknownHostException
	{
		InetAddress iAdd = InetAddress.getLocalHost();
		return iAdd.getHostName();
	}
	
	public static String getHostAddress() throws UnknownHostException
	{
		InetAddress iAdd = InetAddress.getLocalHost();
		return iAdd.getHostAddress();
	}
	
	public static boolean isOSWindows()
	{
		String osName = System.getProperty("os.name");
		if(osName.startsWith(OS_WINDOWS))
			return true;
		else
			return false;
	}
}
