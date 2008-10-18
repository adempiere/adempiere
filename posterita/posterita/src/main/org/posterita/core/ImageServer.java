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
 * Created on Mar 14, 2006 by praveen
 *
 */
package org.posterita.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;

import org.posterita.exceptions.OperationException;
import org.posterita.util.PathInfo;

public class ImageServer 
{   
	public static final String DEFAULT_IMAGE_PATH = PathInfo.PROJECT_HOME + "/images/webstore/noimage.jpg";
	
    public static boolean sendImage(Properties ctx, int attachment_id, String size, HttpServletResponse response) throws OperationException
    {
        MAttachment attachment = new MAttachment(ctx,attachment_id,null);
        
        if(attachment == null)
        {
            return false;
        }
        
        MAttachmentEntry[] entries = attachment.getEntries();
        MAttachmentEntry entry = null;
        
        try 
        {
            for(int i=0; i<entries.length; i++)
            {
                entry = entries[i];
                
                if(entry.getContentType().startsWith("image/"))
                {
                    if(entry.getName().startsWith(size))
                    {
                        response.setContentType(entry.getContentType());                         
                        
                        BufferedInputStream bis = new BufferedInputStream(entry.getInputStream());
                        
                        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                        
                        writeResponse(bis, bos);
                        
                        bis.close();
                        bos.close();
                        
                        return true;                    
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            throw new OperationException(e);
        }
        
        return false;        
        
    }
    
    public static void sendDefaultImage(HttpServletResponse response) throws OperationException
    {
    	try
    	{
    		FileInputStream fileInStream = new FileInputStream(DEFAULT_IMAGE_PATH);
    		BufferedInputStream bufferedInStream = new BufferedInputStream(fileInStream);
    		BufferedOutputStream bufferedOutStream = new BufferedOutputStream(response.getOutputStream());
    		
    		writeResponse(bufferedInStream, bufferedOutStream);
    		
    		bufferedInStream.close();
    		bufferedOutStream.close();
    	}
    	catch(Exception ex)
    	{
    		throw new OperationException("Could not send default image", ex);
    	}
    }
    
    private static void writeResponse(InputStream inStream, OutputStream outStream) throws IOException
    {
    	 byte by[] = new byte[ 32768 ];
         int index = inStream.read( by, 0, 32768 );
         
         while ( index != -1 )
         {
           outStream.write( by, 0, index );
           index = inStream.read( by, 0, 32768 );
         }
         
         outStream.flush();
    }
    
    /*public static boolean sendDefaultImage(Properties ctx, String size, HttpServletResponse response)
    {
        
    }*/

}
