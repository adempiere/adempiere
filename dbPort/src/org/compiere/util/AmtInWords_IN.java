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
 *	Amount in Words for Bahasa Indonesia
 *	
 *  @author Halim Englen
 *  @version $Id: AmtInWords_IN.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 */
public class AmtInWords_IN implements AmtInWords
{
	/**
	 * 	AmtInWords_IN
	 */
	public AmtInWords_IN ()
	{
		super ();
	}	//	AmtInWords_IN	

    private static final String[] basenumbers = new String[]{
        "Nol",
        "Satu",
        "Dua",
        "Tiga",
        "Empat",
        "Lima",
        "Enam",
        "Tujuh",
        "Delapan",
        "Sembilan",
        "Sepuluh",
        "Sebelas",
  };
  private static final long POWER_THREE = 1000L;
  private static final long POWER_SIX = 1000000L;
  private static final long POWER_NINE = 1000000000L;
  private static final long POWER_TWELVE = 1000000000000L;
  private static final long POWER_FIFTEEN = 1000000000000000L;

//-------------------------- STATIC METHODS --------------------------

  /**
  * Convenient method for {@link #sayNumber(StringBuffer, long)}.
  *
  * @param number number to say
  * @return said number
  */
  public static String sayNumber(long number) {
        StringBuffer result = new StringBuffer();
        sayNumber(result, number);
        return result.toString();
  }

  /**
   * Say a number. This method will append the result to the given string buffer.
   *
   * @param appendTo the string buffer
   * @param number   number to say
   * @return said number
   * @throws IllegalArgumentException if the number equals to {@link Long#MIN_VALUE}
   */
  public static String sayNumber(StringBuffer appendTo, long number)
  throws IllegalArgumentException {
	  if (number == Long.MIN_VALUE) {
		  throw new IllegalArgumentException("Out of range");
	  }
	  if (number < 0) {
		  appendTo.append("Minus ");
	  }
	  long abs = Math.abs(number);
	  if (abs < POWER_THREE) {
		  saySimpleNumber(appendTo, (int) abs);
	  } else if (abs < POWER_SIX) {
		  int thousand = (int) (abs % POWER_SIX / POWER_THREE);
		  saySimpleNumber(appendTo, thousand);
		  appendTo.append(" Ribu");
		  long remainder = abs - thousand * POWER_THREE;
		  if (remainder > 0) {
			  appendTo.append(' ');
			  sayNumber(appendTo, remainder);
		  }
	  } else if (abs < POWER_NINE) {
		  int million = (int) (abs % POWER_NINE / POWER_SIX);
		  saySimpleNumber(appendTo, million);
		  appendTo.append(" Juta");
		  long remainder = abs - million * POWER_SIX;
		  if (remainder > 0) {
			  appendTo.append(' ');
			  sayNumber(appendTo, remainder);
		  }
	  } else if (abs < POWER_TWELVE) {
		  int billion = (int) (abs % POWER_TWELVE / POWER_NINE);
		  saySimpleNumber(appendTo, billion);
		  appendTo.append(" Milyar");
		  long remainder = abs - billion * POWER_NINE;
		  if (remainder > 0) {
			  appendTo.append(' ');
			  sayNumber(appendTo, remainder);
		  }
	  } else if (abs < POWER_FIFTEEN) {
		  int trillion = (int) (abs % POWER_FIFTEEN / POWER_TWELVE);
		  saySimpleNumber(appendTo, trillion);
		  appendTo.append(" Trilyun");
		  long remainder = abs - trillion * POWER_TWELVE;
		  if (remainder > 0) {
			  appendTo.append(' ');
			  sayNumber(appendTo, remainder);
		  }
	  } else {
		  int log = (int) Math.floor(Math.log(abs) / Math.log(10));
		  
		  // we want to break the number to several billions.
		  int logWhole = log - log % 12;
		  
		  long powerWhole = (long) Math.pow(10, logWhole);
		  
		  long part = abs / powerWhole;
		  sayNumber(appendTo, part);
		  appendTo.append(" Trilyun");
		  
		  long remainder = abs - part * powerWhole;
		  if (remainder > 0) {
			  appendTo.append(' ');
			  sayNumber(appendTo, remainder);
		  }
	  }
	  return appendTo.toString();
  }

   
   
   static void saySimpleNumber(StringBuffer appendTo, int number) {
	     
		 
         assert number < 1000 && number >= 0: "Must be between 0 and 1000";
		 
         if (number < 12) {
               assert number < 12 && number >= 0: "Must be between 0 and 11";
               appendTo.append(basenumbers[number]);
         } else if (number < 20) {
               assert number >= 12 && number <= 19: "Must be between 12 and 19";
               int belasan = number % 10;
               appendTo.append(basenumbers[belasan]);
               appendTo.append(" Belas");
         } else if (number < 100) {
               assert number >= 20 && number <= 99: "Must be between 20 and 99";
               int puluhan = number / 10;
               appendTo.append(basenumbers[puluhan]);
               appendTo.append(" Puluh");
               int remainder = number - puluhan * 10;
               if (remainder > 0) {
                     appendTo.append(' ');
                     saySimpleNumber(appendTo, remainder);
               }
         } else if (number < 200) {
               assert number >= 100 && number <= 199: "Must be between 100 and 199";
               appendTo.append("Seratus");

               int remainder = number - 100;
               if (remainder > 0) {
                     appendTo.append(' ');
                     saySimpleNumber(appendTo, remainder);
               }
         } else if (number != 0) {
               assert number >= 200 && number <= 999: "Must be between 200 and 999";

               int ratusan = number % 1000 / 100;
               assert ratusan > 0 && ratusan < 10 :"1-9";
               appendTo.append(basenumbers[ratusan]);
               appendTo.append(" Ratus");
               int remainder = number - ratusan * 100;
               if (remainder > 0) {
                     appendTo.append(' ');
                     saySimpleNumber(appendTo, remainder);
               }
         }
   }
  
	
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
		amount = amount.replaceAll (",", "");		
		float numFloat = Float.parseFloat(amount);
		long number = 0L;
		number = (long)numFloat;
		return AmtInWords_IN.sayNumber(number);
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
		AmtInWords_IN aiw = new AmtInWords_IN();
		// I doubt decimal support for rupiah is still needed ;)
		aiw.print (".23");
		aiw.print ("948,776,477,778,778,778");
		aiw.print ("1.79");
	//	aiw.print ("12.345");
	//	aiw.print ("123.45");
	//	aiw.print ("1234.56");
	//	aiw.print ("12345.78");
	//	aiw.print ("123457.89");
		aiw.print ("1,234,578.90");
	}	//	main
	
}	//	AmtInWords_IN
