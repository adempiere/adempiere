package org.posterita.core.install;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.posterita.core.exception.FileOpException;

public class POSInstall 
{
	public static final boolean DEBUG = true;
	private static final String DB_TYPE_ORACLE = "oracle";
//	private static final String DB_TYPE_FIREBIRD = "firebird";
	
	private static final String DEFAULT_DB_NAME = "tmkpos";
	private static final String DB_PASSWORD = "compiere";
//	private static final String DB_SYSTEM_PASSWORD = "compiere";
	
	private static final String START_SERVER_LINK_FILE = "Start Tamak ICT POS Server.lnk";
	private static final String STOP_SERVER_LINK_FILE = "Stop Tamak ICT POS Server.lnk";
	
	private static final String INSTALLATIONSRC = "Compiere2";
	
	private static final String INSTALLATION_PATH = "c:\\";
	private static final String FILE_SRC_DIR = "bin";
	
	
	private static void writeStream(File outFile, InputStream inStream) throws FileOpException
	{
		try
		{
			FileOutputStream fileOutStream = new FileOutputStream(outFile);
			BufferedOutputStream bufferedOutStream = new BufferedOutputStream(fileOutStream);
			BufferedInputStream bufferedInStream = new BufferedInputStream(inStream);
			
			byte buffer[] = new byte[1024];
			int read = -1;
			
			while((read = bufferedInStream.read(buffer)) != -1)
				bufferedOutStream.write(buffer, 0, read);
			bufferedOutStream.flush();
			bufferedOutStream.close();
			bufferedInStream.close();
		}
		catch(IOException ex)
		{
			throw new FileOpException("Could not write file: " + outFile.getAbsolutePath(), ex);
		}
	}
	
	public static String getFileAsString(File inFile) throws FileOpException
	{
		try
		{
			FileInputStream fileInStream = new FileInputStream(inFile);
			BufferedInputStream bufferedInStream = new BufferedInputStream(fileInStream);
			ByteArrayOutputStream byteArrayOutStream = new ByteArrayOutputStream();
			
			byte buffer[] = new byte[1024];
			int read;
			
			while((read = bufferedInStream.read(buffer)) != -1)
			{
				byteArrayOutStream.write(buffer, 0, read);
			}
			
			byteArrayOutStream.flush();
			
			String data = new String(byteArrayOutStream.toByteArray());
			byteArrayOutStream.close();
			bufferedInStream.close();
			fileInStream.close();
			
			return data;
		}
		catch(IOException ex)
		{
			throw new FileOpException("Could not read file: " + inFile.getAbsolutePath(), ex);
		}
		
	}
	
	public static void install(File srcFile, String installationPath) throws FileOpException
	{
		try
		{
			ZipFile zipFile = new ZipFile(srcFile);
			
			Enumeration zipEntryEnum = zipFile.entries();
			
			while(zipEntryEnum.hasMoreElements())
			{
				ZipEntry zipEntry = (ZipEntry)zipEntryEnum.nextElement();
				
				if(zipEntry.isDirectory())
				{
					File file = new File(installationPath + zipEntry.getName());
					if(DEBUG)
						System.out.println("Creating directory: " + file.getAbsolutePath());
					if(!file.mkdirs())
						throw new FileOpException("Could not creat directory: " + file.getAbsolutePath());
				}
				else
				{
					File file = new File(installationPath + zipEntry.getName());
					if(DEBUG)
						System.out.println("Creating file: " + file.getAbsolutePath());
					InputStream inStream = zipFile.getInputStream(zipEntry);
					writeStream(file, inStream);
					inStream.close();
				}
			}
		}
		catch(IOException ex)
		{
			throw new FileOpException("Could not create installation files", ex);
		}
	}
	
	/*private static void uninstall(File path)
	{
		File files[] = path.listFiles();
		
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].isDirectory())
				uninstall(files[i]);
			else
				files[i].delete();
		}
		path.delete();
	}*/
	
	private static void changeCompiereEnvSettings(File compEnvFile, File compHomeDir, String dbName, String dbType, String webstores) throws FileOpException
	{
		if(!(compEnvFile.exists() && compEnvFile.isFile()))
			throw new FileOpException("Could not locate Compiere Environment settings, " + compEnvFile.getAbsolutePath());
		
		Properties props = new Properties();
		FileInputStream fis = null;
		FileOutputStream fileOutStream = null;
					
		try
		{
			fis = new FileInputStream(compEnvFile);
			props.load(fis);
			String hostName = EnvSettings.getHostName();
			
			props.setProperty(EnvSettings.COMPIERE_APPS_SERVER, hostName);
			props.setProperty(EnvSettings.COMPIERE_DB_SERVER, hostName);
			props.setProperty(EnvSettings.COMPIERE_MAIL_SERVER, hostName);
			props.setProperty(EnvSettings.COMPIERE_WEB_ALIAS, hostName);
			
			String jdbcConnString = "";
			if(dbType.equals(DB_TYPE_ORACLE))
				jdbcConnString = "jdbc:oracle:thin:@" + hostName + ":1521:" + dbName;
			
			props.setProperty(EnvSettings.COMPIERE_DB_URL, jdbcConnString);
			props.setProperty(EnvSettings.COMPIERE_HOME, compHomeDir.getAbsolutePath());
			props.setProperty(EnvSettings.JAVA_HOME, EnvSettings.getJDKHomeDir().getAbsolutePath());
			props.setProperty(EnvSettings.COMPIERE_DB_TYPE, dbType);
			props.setProperty(EnvSettings.COMPIERE_DB_NAME, dbName);
			
			String deployFolder = compHomeDir.getAbsolutePath() 
								+ EnvSettings.PATH_SEPARATOR + "jboss"
								+ EnvSettings.PATH_SEPARATOR + "server"
								+ EnvSettings.PATH_SEPARATOR + "compiere"
								+ EnvSettings.PATH_SEPARATOR + "deploy";
			props.setProperty(EnvSettings.COMPIERE_APPS_DEPLOY, deployFolder);
			props.setProperty(EnvSettings.COMPIERE_WEBSTORES, webstores);
			
			fileOutStream = new FileOutputStream(compEnvFile);
			String comments = "Compiere";
			
			String keystorePath = compHomeDir.getAbsolutePath() + "\\keystore\\myKeystore";
			
			props.setProperty(EnvSettings.COMPIERE_KEYSTORE, keystorePath);
			
			props.store(fileOutStream, comments);
		}
		catch(IOException ex)
		{
			throw new FileOpException("Could not read Compiere Environment settings, " + compEnvFile.getAbsolutePath(), ex);
		}
		finally
		{
			if(fis != null)
			{
				try
				{
					fis.close();
				}
				catch(Exception ex)
				{}
			}
			
			if (fileOutStream != null)
			{
				try
				{
					fileOutStream.close();
				}
				catch(Exception e)
				{}
			}
		}
	}
	
	private static void changeEnviromentBatFile(File compEnvFile, String compHome) throws FileOpException
	{
		String envTemplateBatFilePath = compHome + EnvSettings.PATH_SEPARATOR + "utils" + EnvSettings.PATH_SEPARATOR + "myEnvironmentTemplate.bat";
		String oldEnvBatFilePath = compHome + EnvSettings.PATH_SEPARATOR + "utils" + EnvSettings.PATH_SEPARATOR + "myEnvironment.bat";
		FileInputStream fis = null;
		
		try
		{
			File oldEnvBatFile = new File(oldEnvBatFilePath);
			oldEnvBatFile.delete();
			
			Properties props = new Properties();
			fis = new FileInputStream(compEnvFile);
			props.load(fis);
			
			String data = getFileAsString(new File(envTemplateBatFilePath));
			
			Enumeration keysEnum = props.keys();
			while(keysEnum.hasMoreElements())
			{
				String key = (String)keysEnum.nextElement();
				String value = (String)props.get(key);
				
				value = value.replaceAll("\\\\", "\\\\\\\\");
				
				String fileKey = "@" + key + "@";
				data = data.replaceAll(fileKey, value);
			}
			
			File newEnvBatFile = new File(oldEnvBatFilePath);
			FileWriter fileWriter = new FileWriter(newEnvBatFile);
			fileWriter.write(data);
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException ex)
		{
			throw new FileOpException("Could not create environment settings bat file", ex);
		}
		
		finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				}
				catch(Exception ex)
				{}
			}
		}
		
	}
	
	private static void changeCompiereSettings(File compPropsFile, String dbType, String dbName) throws FileOpException
	{
		Properties props = new Properties();
		String dbTypeName = "";
		String dbPort = "";
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		if(DB_TYPE_ORACLE.equals(dbType))
		{
			dbTypeName = "Oracle";
			dbPort = "1521";
		}
		else
		{
			dbTypeName = "Firebird";
			dbPort = "3050";
		}
		
		try
		{
			fis = new FileInputStream(compPropsFile);
			props.load(fis);
			
			props.setProperty(EnvSettings.TEMP_DIR, EnvSettings.getTempDir().getAbsolutePath());
			String hostName = EnvSettings.getHostName();
			String connUrl = "xyzCConnection[name="
						   + hostName + "{" + hostName + "-" + dbName + "-" + DB_PASSWORD
						   + "},AppsHost=" + hostName + ",AppsPort=1099,Profile=L,type="
						   + dbTypeName + ",DBhost=" + hostName + ",DBport=" + dbPort + ",DBname="
						   + dbName + ",BQ=false,FW=false,FWhost=,FWport=1630,UID=compiere,PWD=compiere]";
			props.setProperty(EnvSettings.CONNECTION, connUrl);
			
			fos = new FileOutputStream(compPropsFile);
			props.store(fos, "");
		}
		catch(IOException ex)
		{
			throw new FileOpException("Could not read data from properties file: " + compPropsFile.getAbsolutePath(), ex);
		}
		finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				}
				catch(Exception e)
				{}
			}
			
			if (fos != null)
			{
				try
				
				{
					fos.close();
				}
				catch(Exception e)
				{}
			}
		}
	}
	
	
	private static void changeSettings(String installedPath) throws FileOpException
	{
		File compiereEnvProperties = new File(installedPath + EnvSettings.PATH_SEPARATOR + "CompiereEnv.properties");
		File compiereEnvPropertiesSave = new File(installedPath + EnvSettings.PATH_SEPARATOR + "CompiereEnv.properties.save");
		File compiereProperties = new File(installedPath + EnvSettings.PATH_SEPARATOR + "Compiere.properties");
		File compierePropertiesSave = new File(installedPath + EnvSettings.PATH_SEPARATOR + "Compiere.properties.save");
		changeCompiereEnvSettings(compiereEnvProperties, new File(installedPath), DEFAULT_DB_NAME, DB_TYPE_ORACLE, "");
		changeCompiereEnvSettings(compiereEnvPropertiesSave, new File(installedPath), DEFAULT_DB_NAME, DB_TYPE_ORACLE, "");
		changeCompiereSettings(compiereProperties, DB_TYPE_ORACLE, DEFAULT_DB_NAME);
		changeCompiereSettings(compierePropertiesSave, DB_TYPE_ORACLE, DEFAULT_DB_NAME);
		changeEnviromentBatFile(compiereEnvProperties, installedPath);
	}
	
	private static void createShortcutFile() throws FileOpException, UnknownHostException
	{
		String desktop = EnvSettings.getUserDesktopDir().getAbsolutePath();
		String hostAddress = EnvSettings.getHostAddress();
		String shortcutFilePath = desktop + EnvSettings.PATH_SEPARATOR + "Tamak ICT POS.url";
		File outFile = new File(shortcutFilePath);
		
		try
		{
			FileWriter fileWriter = new FileWriter(outFile);
			fileWriter.write("[InternetShortcut]\r\n");
			fileWriter.write("URL=http://" + hostAddress + "/posterita/" + "\r\n");
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException ex)
		{
			throw new FileOpException("Could not write url shortcut");
		}
		
	}
	
	private static void createShortcuts(String installedPath) throws FileOpException, UnknownHostException
	{
		String desktopPath = EnvSettings.getUserDesktopDir().getAbsolutePath();
		String startSeverLink = installedPath + EnvSettings.PATH_SEPARATOR + START_SERVER_LINK_FILE;
		String deskStartServerLink = desktopPath + EnvSettings.PATH_SEPARATOR + START_SERVER_LINK_FILE;
		String stopSeverLink = installedPath + EnvSettings.PATH_SEPARATOR + STOP_SERVER_LINK_FILE;
		String deskStopServerLink = desktopPath + EnvSettings.PATH_SEPARATOR + STOP_SERVER_LINK_FILE;
		
		try
		{
			writeStream(new File(deskStartServerLink), new FileInputStream(startSeverLink));
			writeStream(new File(deskStopServerLink), new FileInputStream(stopSeverLink));
			createShortcutFile();
		}
		catch(FileNotFoundException ex)
		{
			throw new FileOpException("Could not write shortcuts", ex);
		}
	}
	
	private static void installTamakICTPOS() throws FileOpException, UnknownHostException
	{
		String workingDir = EnvSettings.getWorkingDir().getAbsolutePath();
		
		String srcFile = workingDir + EnvSettings.PATH_SEPARATOR + FILE_SRC_DIR + EnvSettings.PATH_SEPARATOR + INSTALLATIONSRC;
		String installationPath = INSTALLATION_PATH;
		install(new File(srcFile), installationPath);
		String installedPath = installationPath + INSTALLATIONSRC;
		changeSettings(installedPath);
		createShortcuts(installedPath);
	}
	
	public static void main(String args[])
	{
		try
		{
			installTamakICTPOS();
			System.out.println("Installation successfull");
		}
		catch(Exception ex)
		{
			System.out.println("Installation failed: " + ex.getMessage());
			if(DEBUG)
				ex.printStackTrace();
		}
	}
}
