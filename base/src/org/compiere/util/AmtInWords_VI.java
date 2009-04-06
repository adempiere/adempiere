/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.util;


/**
 *	Amount in Words for Vietnamese
 *  @author Vietsourcing-The class is based on the AmtInWords_EN.java written by jjanke
 *   NOTE: 
 *  	1)In Vietnamese, the linking word "AND" between hundreds and following numbers
 *  	only applies for numbers less than 10, eg: 
 *  		109 - a hundred and nine, but 
 *  		119 - a hundred and nineteen
 *		2)For numbers of thousands level upward, it is a common practice to pronounce 
 *		'zero hundred', eg:
 *			1020 - one thousand zero hundred twenty
 *			1010030 - one million zero hundred ten thousand zero hundred thirty
 *		3)Change of tone for tens+1 for tens >20, eg:
 *			1 - "một" but 21 -> "hai mươi mốt"
 *		4) Change of words for tens+4 (optional) and tens+5, eg:
 *			4 - "bốn" but 24 = "hai mươi bốn" or "hai mươi tư". We choose not to change
 *			the word
 *			5 - "năm" but 15 = "mười lăm"
 *			
 *  TEST RESULTS:
 *  	0.23 = Không 23/100
 *		1.23 = Một 23/100
 *		12.345 = Mười hai 345/100
 *		103.45 = Một Trăm linh Ba 45/100
 *		114.45 = Một Trăm Mười bốn 45/100
 *		123.45 = Một Trăm Hai mươi ba 45/100
 *		1023.45 = Một Nghìn  không trăm Hai mươi ba 45/100
 *		1234.56 = Một Nghìn Hai Trăm Ba mươi bốn 56/100
 *		12345.78 = Mười hai Nghìn Ba Trăm Bốn mươi lăm 78/100
 *		103457.89 = Một Trăm linh Ba Nghìn Bốn Trăm Năm mươi bẩy 89/100
 *		1,234,578.90 = Một Triệu Hai Trăm Ba mươi bốn Nghìn Năm Trăm Bẩy mươi tám 90/100
 *		1,034,578.90 = Một Triệu  không trăm Ba mươi bốn Nghìn Năm Trăm Bẩy mươi tám 90/100
 *		1,004,008.90 = Một Triệu  không trăm linh Bốn Nghìn  không trăm linh Tám 90/100
 *		1,201,034,578.90 = Một Tỉ Hai Trăm linh Một Triệu  không trăm Ba mươi bốn Nghìn Năm Trăm Bẩy mươi tám 90/100
 *  	 

 *  @version $Id: AmtInWords_VI.java,v 1.0 2009/03/30 23:03:00$
 */
public class AmtInWords_VI implements AmtInWords
{
	/**
	 * 	AmtInWords_VI
	 */
	public AmtInWords_VI ()
	{
		super ();
	}	//	AmtInWords_VI

	/** Thousands plus				*/
	private static final String[]	majorNames	= {
		"", 
		" Nghìn ", 
		" Triệu ",
		" Tỉ ", 
		" Nghìn tỉ ", 
		" Triệu tỉ ",
		" Tỉ tỉ "
	};

	/** Ten to Ninety				*///No necessary anymore since we have # to 99
/*	private static final String[]	tensNames	= { 
		"", 
		"Mười", 
		"Hai mươi",
		"Ba mươi", 
		"Bốn mươi", 
		"Năm mươi", 
		"Sáu mươi", 
		"Bảy mươi",
		"Tám mươi", 
		"Chín mươi"
	};
*/
	/** numbers to 99				*/
	private static final String[]	numNames	= { 
		"Không", 
		"Một", 
		"Hai",
		"Ba", 
		"Bốn", 
		"Năm", 
		"Sáu", 
		"Bẩy", 
		"Tám", 
		"Chín",
		"Mười", 
		"Mười một", 
		"Mười hai", 
		"Mười ba", 
		"Mười bốn", 
		"Mười lăm", //list it here so no programming is needed. See note 3,4
		"Mười sáu", 
		"Mười bẩy", 
		"Mười tám", 
		"Mười chín",
		"Hai mươi",
		"Hai mươi mốt", //list it here so no programming is needed. See note 3,4
		"Hai mươi hai",
		"Hai mươi ba",
		"Hai mươi bốn",
		"Hai mươi lăm",//list it here so no programming is needed. See note 3,4
		"Hai mươi sáu",
		"Hai mươi bẩy",
		"Hai mươi tám",
		"Hai mươi chín",
		"Ba mươi",
		"Ba mươi mốt",//list it here so no programming is needed. See note 3,4
		"Ba mươi hai",
		"Ba mươi ba",
		"Ba mươi bốn",
		"Ba mươi lăm",//list it here so no programming is needed. See note 3,4
		"Ba mươi sáu",
		"Ba mươi bẩy",
		"Ba mươi tám",
		"Ba mươi chín",
		"Bốn mươi",
		"Bốn mươi mốt",//list it here so no programming is needed. See note 3,4
		"Bốn mươi hai",
		"Bốn mươi ba",
		"Bốn mươi bốn",
		"Bốn mươi lăm",//list it here so no programming is needed. See note 3,4
		"Bốn mươi sáu",
		"Bốn mươi bẩy",
		"Bốn mươi tám",
		"Bốn mươi chín",
		"Năm mươi",
		"Năm mươi mốt",//list it here so no programming is needed. See note 3,4
		"Năm mươi hai",
		"Năm mươi ba",
		"Năm mươi bốn",
		"Năm mươi lăm",//list it here so no programming is needed. See note 3,4
		"Năm mươi sáu",
		"Năm mươi bẩy",
		"Năm mươi tám",
		"Năm mươi chín",
		"Sáu mươi",
		"Sáu mươi mốt",//list it here so no programming is needed. See note 3,4
		"Sáu mươi hai",
		"Sáu mươi ba",
		"Sáu mươi bốn",
		"Sáu mươi lăm",//list it here so no programming is needed. See note 3,4
		"Sáu mươi sáu",
		"Sáu mươi bẩy",
		"Sáu mươi tám",
		"Sáu mươi chín",
		"Bẩy mươi",
		"Bẩy mươi mốt",//list it here so no programming is needed. See note 3,4
		"Bẩy mươi hai",
		"Bẩy mươi ba",
		"Bẩy mươi bốn",
		"Bẩy mươi lăm",//list it here so no programming is needed. See note 3,4
		"Bẩy mươi sáu",
		"Bẩy mươi bẩy",
		"Bẩy mươi tám",
		"Bẩy mươi chín",
		"Tám mươi",
		"Tám mươi mốt",//list it here so no programming is needed. See note 3,4
		"Tám mươi hai",
		"Tám mươi ba",
		"Tám mươi bốn",
		"Tám mươi lăm",//list it here so no programming is needed. See note 3,4
		"Tám mươi sáu",
		"Tám mươi bẩy",
		"Tám mươi tám",
		"Tám mươi chín",
		"Chín mươi",
		"Chín mươi mốt",//list it here so no programming is needed. See note 3,4
		"Chín mươi hai",
		"Chín mươi ba",
		"Chín mươi bốn",
		"Chín mươi lăm",//list it here so no programming is needed. See note 3,4
		"Chín mươi sáu",
		"Chín mươi bẩy",
		"Chín mươi tám",
		"Chín mươi chín"
	};

	/**
	 * 	Convert Less Than One Thousand
	 *	@param number
	 *	@return amt
	 */
	
	private String convertLessThanOneThousand (int number)
	{
		String soFar = "";
		//	Below 10
		if (number % 100 < 10 && number > 100)
			soFar = "linh "; //see Note 1
			soFar = soFar + numNames[number % 100];
			number /= 100;

		if (number == 0)
			return soFar;
		
		return numNames[number] + " Trăm " + soFar;
	}	//	convertLessThanOneThousand

	/**
	 * 	Convert
	 *	@param number
	 *	@return amt
	 */
	private String convert (long number)
	{
		/* special case */
		if (number == 0)
		{
			return "Không";
		}
		String prefix = "";
		if (number < 0)
		{
			number = -number;
			prefix = "Âm ";
		}
		String soFar = "";
		
		int place = 0;
	do
		{
			long n = number % 1000;
			if (n != 0)
			{
				String s = convertLessThanOneThousand ((int)n);
				if (n <100 && number >1000) //see Note 2
				{
					if (n>10)
						s = "không trăm " + s;
					if (n<10)
								s = "không trăm linh " + s;
					
				}
				soFar = s + majorNames[place] + soFar;
			}
			place++;
			number /= 1000;
		}
		while (number > 1000);
		long m = number % 1000; //see Note 2, this rule does not apply for biggest major name 
		if (m != 0)
		{
			String t = convertLessThanOneThousand ((int)m);
			soFar = t + majorNames[place] + soFar;
		}
		return (prefix + soFar).trim ();
	}	//	convert

	
	/**************************************************************************
	 * 	Get Amount in Words
	 * 	@param amount numeric amount (352.80)
	 * 	@return amount in words (three*five*two 80/100)
	 * 	@throws Exception
	 */
	public String getAmtInWords (String amount) throws Exception
	{
		if (amount == null)
			return amount;
		//
		StringBuffer sb = new StringBuffer ();
		int pos = amount.lastIndexOf ('.');
		int pos2 = amount.lastIndexOf (',');
		if (pos2 > pos)
			pos = pos2;
		String oldamt = amount;
		amount = amount.replaceAll (",", "");
		int newpos = amount.lastIndexOf ('.');
		long dollars = Long.parseLong(amount.substring (0, newpos));
		sb.append (convert (dollars));
		for (int i = 0; i < oldamt.length (); i++)
		{
			if (pos == i) //	we are done
			{
				String cents = oldamt.substring (i + 1);
				sb.append (' ').append (cents).append ("/100");
				break;
			}
		}
		return sb.toString ();
	}	//	getAmtInWords

	/**
	 * 	Test Print
	 *	@param amt amount
	 */
	private void print (String amt)
	{
		try
		{
			System.out.println(amt + " = " + getAmtInWords(amt));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	//	print
	
	/**
	 * 	Test
	 *	@param args ignored
	 */
	public static void main (String[] args)
	{
		AmtInWords_VI aiw = new AmtInWords_VI();
	//	aiw.print (".23");	Error
		aiw.print ("0.23");
		aiw.print ("1.23");
		aiw.print ("12.345");
		aiw.print ("103.45");
		aiw.print ("114.45");
		aiw.print ("123.45");
		aiw.print ("1023.45");
		aiw.print ("1234.56");
		aiw.print ("12345.78");
		aiw.print ("103457.89");
		aiw.print ("1,234,578.90");
		aiw.print ("1,034,578.90");
		aiw.print ("1,004,008.90");
		aiw.print ("1,201,034,578.90");
	}	//	main
	
}	//	AmtInWords_VI
