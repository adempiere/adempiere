package org.adempiere.pipo.handler;

import java.io.File;
import java.sql.ResultSet;
import java.util.logging.Level;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.PackOut;
import org.compiere.model.X_AD_Package_Exp;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.util.CLogger;
import org.xml.sax.helpers.AttributesImpl;

public class FileElementHandler implements IPackOutHandler{
	
	private CLogger log = CLogger.getCLogger(FileElementHandler.class);
	
	public void packOut(PackOut packout, ResultSet header, ResultSet detail,TransformerHandler packOutDocument,TransformerHandler packageDocument,int recordId) throws Exception
	{
	    	log.log(Level.INFO,"In PackOut.java handling Code or Other 2pack module creation");
			String fullDirectory = header.getString(X_AD_Package_Exp.COLUMNNAME_File_Directory) + header.getString(X_AD_Package_Exp.COLUMNNAME_Name)+header.getString(X_AD_Package_Exp_Detail.COLUMNNAME_Target_Directory);
			log.log(Level.INFO,"fullDirectory" + fullDirectory);
			String targetDirectoryModified=null;
			char fileseperator1 = '/';
			char fileseperator2 = '\\';
			//Correct package for proper file seperator
			if (File.separator.equals("/")){			
				targetDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
			}
			else
				targetDirectoryModified = fullDirectory.replace(fileseperator1,fileseperator2);
			
			String target_File = (targetDirectoryModified);						
			fullDirectory = detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_File_Directory);
			targetDirectoryModified=null;						
			//Correct package for proper file seperator
			if (File.separator.equals("/")){			
				targetDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
			}
			else
				targetDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
			
			packout.copyCode(
					targetDirectoryModified + detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_FileName),
					target_File + detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_FileName)
					);
			
			AttributesImpl atts = new AttributesImpl();
			
			if(detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_Destination_Directory) != null){
				
				fullDirectory = detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_Destination_Directory);
				String destinationDirectoryModified=null;						
				
				//Correct package for proper file seperator
				if (File.separator.equals("/")){			
					destinationDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
				}
				else
					destinationDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);							
			
				try{
					new DistFileElementHandler(destinationDirectoryModified).doPackout(packout,null,detail,packOutDocument,null,null,0);
				}
				catch(Exception e)
				{
					System.err.println(e.toString());
				}
			}
			
			if(detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_FileName) != null){
				packageDocument.startElement("","","file",atts);
				packageDocument.characters(("File: "+detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_FileName)).toCharArray(),0,("File: "+detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_FileName)).length());
				packageDocument.endElement("","","file");
			}
			packageDocument.startElement("","","filedirectory",atts);
			packageDocument.characters(
							("Directory: " + detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_Target_Directory)).toCharArray(),
							0,
							("Directory: " + detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_Target_Directory)).length());
			packageDocument.endElement("","","filedirectory");
			
			packageDocument.startElement("","","filenotes",atts);
			packageDocument.characters(
							("Notes: " + detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_Description)).toCharArray(),
							0,
							(("Notes: " + detail.getString(X_AD_Package_Exp_Detail.COLUMNNAME_Description)).length()));
			packageDocument.endElement("","","filenotes");
		}
	}
	


