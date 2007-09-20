/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
	@author Praveen
 */

package org.posterita.businesslogic;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.struts.upload.FormFile;
import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.util.Env;

import org.posterita.core.UDIPropertiesManager;
import org.posterita.exceptions.AttachmentNotFoundException;
import org.posterita.exceptions.DeleteAttachmentException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.PropertiesConstant;
import org.posterita.model.UDIMAttachment;
import org.posterita.util.ListUtil;



public class AttachmentManager
{
	
	public static void attachImage(Properties ctx,FormFile file,String name) throws OperationException, FileNotFoundException
	{
		/*X_U_UDI_LOGO attach = new X_U_UDI_LOGO(ctx,0,null);
		
		attach.setName(name);
		
		UDIMAttachment udiAttachment = new UDIMAttachment(attach);
		
		try
		{
			saveLogo(ctx,file);
			udiAttachment.save();
		}
		catch (OperationException e)
		{
			throw new OperationException("Error occurred on saving file!!");
		}
		catch (FileNotFoundException e)
		{
			throw new FileNotFoundException("File to save not found!!");
		}*/
	}
	
	
	
	public static String getImage(Properties ctx,String name) throws OperationException
	{
		int orgId = Env.getAD_Org_ID(ctx);
		String path = UDIPropertiesManager.getProperty().get(ctx, PropertiesConstant.LOGO_HOME)+orgId+"/"+name;
		return path;
	}
	
	public static ArrayList<MAttachmentEntry> getAttachmentEntriesWithPrefix(Properties ctx, MAttachment attachment, String prefix, String trxName)
	{
		if(attachment == null)
			throw new IllegalArgumentException("Attachment cannot be null");
		
		
		ArrayList<MAttachmentEntry> attachmentEntryList = new ArrayList<MAttachmentEntry>();
				
		MAttachmentEntry entries[] = attachment.getEntries();
		if (prefix!=null)
			prefix = prefix.toUpperCase();
		
		for(int i = 0; i < entries.length; i++)
		{
			String attachmentName = entries[i].getName();
			attachmentName = attachmentName.toUpperCase();
			if(prefix ==null || attachmentName.startsWith(prefix))
				attachmentEntryList.add(entries[i]);
		}
		
		return attachmentEntryList;
	}
	
	public static ArrayList<MAttachmentEntry> getAttachmentEntriesWithSuffix(Properties ctx, MAttachment attachment, String suffix, String trxName)
	{
		if(attachment == null)
			throw new IllegalArgumentException("Attachment cannot be null");
		
		
		
		ArrayList<MAttachmentEntry> attachmentEntryList = new ArrayList<MAttachmentEntry>();
		
		MAttachmentEntry entries[] = attachment.getEntries();
		if (suffix!=null)
			suffix = suffix.toUpperCase();
		
		for(int i = 0; i < entries.length; i++)
		{
			String attachmentName = entries[i].getName();
			attachmentName = attachmentName.toUpperCase();
			if(suffix ==null || attachmentName.endsWith(suffix))
				attachmentEntryList.add(entries[i]);
		}
		
		return attachmentEntryList;
	}
	
	public static void addAttachment(Properties ctx, int ad_table_id, int record_id, MAttachmentEntry[] entries, String trxName) throws OperationException
    {
         MAttachment attachment;
         if(!AttachmentManager.hasAttachment(ctx, ad_table_id, record_id, trxName))
             attachment = new MAttachment(ctx,0,trxName);
         else
         {
        	 attachment = loadAttachment(ctx, ad_table_id, record_id);
        	 attachment.set_TrxName(trxName);
         }
         
         attachment.setAD_Table_ID(ad_table_id);
         attachment.setRecord_ID(record_id);
         
         for(int i = 0; i < entries.length; i++)
         {
             attachment.addEntry(entries[i]);
         }
         
         new UDIMAttachment(attachment).save();
    }
	
	public static void addAttachmentEntry(Properties ctx, MAttachment attachment, String fileName, byte data[], String trxName) throws OperationException
    {
    	MAttachmentEntry attachmentEntry = new MAttachmentEntry(fileName, data);
    	
    	addAttachmentEntries(ctx, attachment, new MAttachmentEntry[]{attachmentEntry}, trxName);
    }
	
	public static void addAttachmentEntries(Properties ctx, MAttachment attachment, MAttachmentEntry[] entries, String trxName) throws OperationException
    {
         for(int i = 0; i < entries.length; i++)
         {
             attachment.addEntry(entries[i]);
         }
         
         new UDIMAttachment(attachment).save();
    }
    
    public static void addAttachment(Properties ctx, int adTableId, int recordId, String fileName, byte data[], String trxName) throws OperationException
    {
    	MAttachmentEntry attachmentEntry = new MAttachmentEntry(fileName, data);
    	
    	addAttachment(ctx, adTableId, recordId, new MAttachmentEntry[]{attachmentEntry}, trxName);
    }
    
    public static boolean hasAttachment(Properties ctx, int ad_table_id, int record_id, String trxName)
    {
    	String whereClause = "AD_Table_ID=" + ad_table_id + " and Record_ID=" + record_id;
        int mAttachmentIDs[] = MAttachment.getAllIDs(MAttachment.Table_Name, whereClause, trxName);
        
        if(mAttachmentIDs.length == 0)
        	return false;
        return true;
    }
    
    public static boolean hasAttachment(Properties ctx, int ad_table_id, int record_id, String attachmentName, String trxName)
    {
    	String whereClause = "AD_Table_ID=" + ad_table_id + " and Record_ID=" + record_id;
        int mAttachmentIDs[] = MAttachment.getAllIDs(MAttachment.Table_Name, whereClause, trxName);
        
        if(mAttachmentIDs.length == 0)
        	return false;
        
        for(int i = 0; i < mAttachmentIDs.length; i++)
        {
        	MAttachment attachment = MAttachment.get(ctx, ad_table_id, record_id);
        	if(getAttachmentEntryIndex(ctx, attachment, attachmentName) != -1)
        		return true;
        }
        return false;
    }
    
    public static int getAttachmentEntryIndex(Properties ctx, MAttachment attachment, String attachmentName)
    {
    	int retIndex = -1;
    	
    	MAttachmentEntry entries[] = attachment.getEntries();
    	
    	for(int i = 0; i < entries.length; i++)
    	{
    		MAttachmentEntry entry = entries[i];

    		if(entry.getName().equalsIgnoreCase(attachmentName))
    		{
    			retIndex = i;
    			break;
    		}
    	}
    	return retIndex;
    }
    
    public static void addAttachmentEntry(MAttachment attachment, MAttachmentEntry entry) throws OperationException
    {
    	if(!attachment.addEntry(entry))
    	{
    		throw new OperationException("Could not add Attachment Entry with name: " + entry.getName() + 
    				" to attachment with table id: " + attachment.get_Table_ID() + " and record id: " + attachment.getRecord_ID());
    	}
    }
    
    public static void deleteAttachmentEntry(MAttachment attachment, int index) throws OperationException
    {
    	if(!attachment.deleteEntry(index))
    	{
    		throw new OperationException("Could not add Attachment Entry with index: " + index + 
    				" to attachment with table id: " + attachment.get_Table_ID() + " and record id: " + attachment.getRecord_ID());
    	}
    }
    
    public static void deleteAvailableAttachment(Properties ctx, int adTableId, int recordId, String trxName)
    {
    	if(hasAttachment(ctx, adTableId, recordId, trxName))
    	{
    		MAttachment attachment = MAttachment.get(ctx, adTableId, recordId);
    		attachment.set_TrxName(trxName);
    		attachment.delete(true);
    	}
    }
    
    public static ArrayList<MAttachmentEntry> getAllAttachmentEntries(Properties ctx, int adTableId, int recordId)
    {
    	ArrayList<MAttachmentEntry> entryList = new ArrayList<MAttachmentEntry>();

    	if(hasAttachment(ctx, adTableId, recordId, null))
    	{
    		MAttachment attachment = MAttachment.get(ctx, adTableId, recordId);
    		return ListUtil.getListFromArray(attachment.getEntries());
    	}
    	return entryList;
    }
    
    public static ArrayList<MAttachmentEntry> getAllImagesAttachmentEntries(Properties ctx, int adTableId, int recordId, String trxName)
    {
    	ArrayList<MAttachmentEntry> entryList = new ArrayList<MAttachmentEntry>();
    	
    	if(hasAttachment(ctx, adTableId, recordId, trxName))
    	{
    		MAttachment attachment = MAttachment.get(ctx, adTableId, recordId);
    		
    		MAttachmentEntry attachmentEntries[] = attachment.getEntries();
    		for(MAttachmentEntry entry : attachmentEntries)
    		{
    			if(entry.isGraphic())
    				entryList.add(entry);
    		}
    	}
    	return entryList;
    }
	
    public static MAttachment loadAttachment(Properties ctx, int adTableId, int recordId) throws AttachmentNotFoundException
    {
    	MAttachment attachment = MAttachment.get(ctx, adTableId, recordId);
    	
    	if(attachment == null)
    		throw new AttachmentNotFoundException("Could not load attachment for table with id: " + adTableId + " and record: " + recordId);
    	
    	return attachment;
    }
    
    public static MAttachment deleteAvailableAttachmentEntry(Properties ctx, int adTableId, int recordId, String attachmentName, String trxName) throws OperationException, AttachmentNotFoundException, DeleteAttachmentException
    {
    	MAttachment retAttachment = null;
    	if(hasAttachment(ctx, adTableId, recordId, trxName))
    	{
	    	MAttachment attachment = loadAttachment(ctx, adTableId, recordId);
	    	attachment.set_TrxName(trxName);
	    	
	    	int index = getAttachmentEntryIndex(ctx, attachment, attachmentName);
	    	
	    	if(index != -1)
	    	{
		    	boolean deleted = attachment.deleteEntry(index);
		    	
		    	if(!deleted)
		    		throw new DeleteAttachmentException("Could not delete attachment for Table with id: " + adTableId + ", Record ID: " + recordId + " and attachment Name" + attachmentName);
		    	
		    	UDIMAttachment udiAttachment = new UDIMAttachment(attachment);
		    	udiAttachment.save();
	    	}
	    	retAttachment = attachment;
    	}
    	return retAttachment;
    }
}
