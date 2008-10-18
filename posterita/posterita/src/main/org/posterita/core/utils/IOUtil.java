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
 *  
 **/

/**
	@author ashley
 */

package org.posterita.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.posterita.core.exception.IOOperationException;

public class IOUtil
{

	public static byte[] getByteArray(InputStream inStream) throws IOOperationException
	{
		try
		{
			BufferedInputStream bufferedInStream = new BufferedInputStream(inStream);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			BufferedOutputStream bufferedOutStream = new BufferedOutputStream(outStream);
			byte buffer[] = new byte[1024];
			int read = 0;
			while((read = bufferedInStream.read(buffer)) != -1)
			{
				bufferedOutStream.write(buffer, 0, read);
			}
			
			bufferedOutStream.flush();
			byte retData[] = outStream.toByteArray();
			bufferedOutStream.close();
			bufferedInStream.close();
			outStream.close();
			return retData;
		}
		catch(IOException ex)
		{
			throw new IOOperationException("Could not get data from InputStream", ex);
		}
	}
	
	public static StringBuffer getContent(Reader reader, String newLineChar) throws IOOperationException
	{
		try
		{
			StringBuffer retDataBuffer = new StringBuffer(1000);
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			String line;
						
			while((line = bufferedReader.readLine()) != null)
				retDataBuffer.append(line + newLineChar);
			
			return retDataBuffer;
		}
		catch(IOException ex)
		{
			throw new IOOperationException("Could not read data from reader", ex);
		}
	
	}
}
