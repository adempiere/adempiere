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
package org.posterita.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.compiere.util.KeyNamePair;

import org.posterita.beans.AttributeValuesPair;
import org.posterita.beans.ProductBean;
import org.posterita.exceptions.OperationException;

public class FileManager 
{
	public static final String FILE_PATH_SEPARATOR = System.getProperty("file.separator");
	
    public static void createFile(String filename) throws IOException
	{
		File newFile = new File(filename);
		
		File parentFile = newFile.getParentFile();

		if(!parentFile.exists())
		  parentFile.mkdirs();
		if (!newFile.exists())
		  newFile.createNewFile();
		
		
	}
    
    
	public static void write(String filename, String content, boolean cond) throws IOException
	{
		createFile(filename);
		
		
		//BufferedWriter bw = null;
		
		
		FileOutputStream out = new FileOutputStream(filename, cond);
		OutputStreamWriter wr = new OutputStreamWriter(out,"ISO-8859-1");

		
		wr.write(content);

		wr.flush();

		wr.close();

	}
	
	public static ArrayList read(InputStream is) throws IOException
	{	   
	    String delimiter = ",";
	    String line;
	    ProductBean bean;
	    AttributeValuesPair pair = null;
	    ArrayList<ProductBean> list = new ArrayList<ProductBean>();
	    
	    InputStreamReader isr = new InputStreamReader(is);
	    BufferedReader bf = new BufferedReader(isr);
	    line = bf.readLine();
	    String model;
	    String colour;
	    String transmission;
	    String year;
	    String strProductid;
	    String serno;
	    String engineNo;
	    while ((line = bf.readLine())!=null)
        {
	        
            bean = new ProductBean();
            ArrayList tokens = tokenize(line,delimiter);
            strProductid = tokens.get(0).toString();
            bean.setProductId(Integer.valueOf(strProductid));
            
            model = tokens.get(1).toString();
            KeyNamePair knpModel = new KeyNamePair(1,model);
            
            
            transmission = tokens.get(2).toString();
            KeyNamePair knpTransmission = new KeyNamePair(2,transmission);
            
            colour = tokens.get(3).toString();
            KeyNamePair knpColour = new KeyNamePair(3,colour);
            
            year = tokens.get(4).toString();
            KeyNamePair knpYear = new KeyNamePair(4,year);
            
            pair = new AttributeValuesPair();
            pair.setModelAttributeValue(knpModel);
            pair.setTransmissionAttributeValue(knpTransmission);
            pair.setColourAttributeValue(knpColour);
            pair.setYearAttributeValue(knpYear);
            bean.setAttributeValuesPair(pair);
            
            
            serno = tokens.get(5).toString();
            engineNo = tokens.get(6).toString();
            bean.setSerno(serno);
            bean.setEngineNo(engineNo);
            list.add(bean);
        }
        
        bf.close();
        isr.close();
        return list;
	    
	}
	
	public static ArrayList tokenize(String str, String delimiter)
	{
	    ArrayList list = Interpreter.interprete(str,delimiter);
	    
	    return list;
	}
	
	public static void write(InputStream content, String fileTarget) throws IOException
	{
		createFile(fileTarget);

		FileOutputStream streamOut = new FileOutputStream(fileTarget);

		int bytesRead  = 0;

		byte[] buffer = new byte[8192];
		while((bytesRead = content.read(buffer,0,8192))!=-1)
		{
		  streamOut.write(buffer,0,bytesRead);
		}

		streamOut.close();
		content.close();
	}

	 public static void copy(File src, File dst) throws IOException 
	 {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
    
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        
        while ((len = in.read(buf)) > 0) 
        {
            out.write(buf, 0, len);
        }
        
        in.close();
        out.close();
    }
	 
	 public static ArrayList<String> readLines(File file) throws OperationException
	 {
		 BufferedReader bufferedReader = null;
		 
		 if(file == null)
			 throw new OperationException("File cannot be null");
		 if(!file.exists())
			 throw new OperationException("File does not exist, file: " + file.getAbsolutePath());
		 if(file.isDirectory())
			 throw new OperationException("File cannot be a directory, file: " + file.getAbsolutePath());
		 
		 ArrayList<String> readLines = new ArrayList<String>();
		 
		 try
		 {
			 FileReader fileInReader = new FileReader(file);
			 bufferedReader = new BufferedReader(fileInReader);
			 String line;
			 while((line = bufferedReader.readLine()) != null)
				 readLines.add(line);
			 
			 return readLines;
		 }
		 catch(FileNotFoundException ex)
		 {
			 throw new OperationException("File not found exception, file: " + file.getAbsolutePath(), ex);
		 }
		 catch(IOException ex)
		 {
			 throw new OperationException("IOException while reading file: " + file.getAbsolutePath(), ex);
		 }
		 finally
		 {
			 if (bufferedReader != null)
			 {
				 try
				 {
					 bufferedReader.close();
				 }
				 catch(Exception e)
				 {}
			 }
		 }
		 
	 }
	 
	 public static void moveFile(File from, File to) throws OperationException
	 {
		if (from.getAbsolutePath().equals(to.getAbsolutePath()))
			return;
		
		if(!from.exists())
			throw new OperationException("From file does not exist");
		if(from.isDirectory())
			throw new OperationException("From file cannot be a directory");
		
		if(to.exists())
			throw new OperationException("To file already exists");

		try
		{
			InputStream in = new FileInputStream(from);
			OutputStream out = new FileOutputStream(to);
	
			// Transfer bytes from in to out
			byte[] buf = new byte[1024];
			int len;
	
			while ((len = in.read(buf)) > 0)
			{
				out.write(buf, 0, len);
			}
	
			in.close();
			out.close();
	
			if (!from.delete())
				throw new OperationException("Cannot delete from file: " + from.getAbsolutePath());
		}
		catch(IOException ex)
		{
			throw new OperationException("Could not move file, from: " + from.getAbsolutePath() + " and to: " + to.getAbsolutePath());
		}
	}

	 
}
