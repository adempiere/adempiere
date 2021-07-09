/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.test;

import java.util.Random;

public class TestUtilities {

	private static Random random = new Random();
	private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	private TestUtilities() {
		throw new IllegalStateException("Utility class: This class is not meant to be instantiated. "
				+ "It provides only static members.");
	}

	public static String randomString(int numberOfCharsFromOneToFifty){
		
		verifyInRangeFromOneToFifty(numberOfCharsFromOneToFifty);
		return buildRandomString(numberOfCharsFromOneToFifty);
	    
	}

	private static String buildRandomString(int numberOfCharsFromZeroToFifty) {
		StringBuilder randString = new StringBuilder();
	    for(int i = 0; i < numberOfCharsFromZeroToFifty; i++)
	    {
	        randString.append(alphabet[random.nextInt(25)]);
	    }
		return randString.toString();
	}

	private static void verifyInRangeFromOneToFifty(int numberOfChars) {
		if (numberOfChars <=0 || numberOfChars > 50)
			throw new IllegalArgumentException("0 < numberOfChars <= 50");
	}

}
