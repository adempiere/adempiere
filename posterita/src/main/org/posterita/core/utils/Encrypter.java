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
package org.posterita.core.utils;

import org.posterita.core.Range;
import org.posterita.exceptions.EncryptionException;

public class Encrypter
{

	public static String encrypt(String str, int fromFirst, int toLast) throws EncryptionException
	{
		if (toLast < fromFirst)
			throw new EncryptionException("To range cannot be less than from range!");
		
		int strLength = str.length();
		
		Range range = new Range(0, strLength);


		if (!range.isInRange(fromFirst) || !range.isInRange(toLast))
			throw new EncryptionException("Cannot do encryption for " + str + " as the characters to encode are not in the range provided: " + fromFirst + " - " + toLast);
		
		String firstUnEncryptedCharacters = str.substring(0, fromFirst);
		String lastUnEncryptedCharacters = str.substring(toLast, strLength);
		
		//String encryptedString = firstUnEncryptedCharacters;
		StringBuffer encryptedString = new StringBuffer();
		encryptedString.append(firstUnEncryptedCharacters);
		
		
		for (int i = 0; i < toLast-fromFirst; i++)
		{
			encryptedString.append("X");
		}
		
		encryptedString.append(lastUnEncryptedCharacters);
		
		
		return encryptedString.toString();
	}

}
