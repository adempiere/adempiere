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
 *	Spanish Amount in Words
 *	
 *  @author Jorg Janke - http://www.rgagnon.com/javadetails/java-0426.html
 *  @version $Id: AmtInWords_ES.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 */
public class AmtInWords_ES implements AmtInWords
{
	/**
	 * 	AmtInWords_ES
	 */
	public AmtInWords_ES ()
	{
		super ();
	} //	AmtInWords_ES

	private static final String[]	majorNames	= {
		"", 
		" MIL", 
		" MILLON",
		" BILLON", 
		" TRILLON", 
		" CUATRILLON", 
		" QUINTILLON"  
		};

	private static final String[]	tensNames	= { 
		"", 
		" DIEZ", 
		" VEINTE",
		" TREINTA", 
		" CUARENTA", 
		" CINCUENTA", 
		" SESENTA", 
		" SETENTA",
		" OCHENTA", 
		" NOVENTA"
		};

	private static final String[]	numNames	= { 
		"", 
		" UN",
		" DOS",
		" TRES", 
		" CUATRO", 
		" CINCO", 
		" SEIS", 
		" SIETE", 
		" OCHO", 
		" NUEVE",
		" DIEZ", 
		" ONCE", 
		" DOCE", 
		" TRECE", 
		" CATORCE", 
		" QUINCE",
		" DIECISEIS", 
		" DIECISIETE", 
		" DIECIOCHO", 
		" DIECINUEVE"
		};

	/**
	 * 	Convert Less Than One Thousand
	 *	@param number
	 *	@return amt
	 */
	private String convertLessThanOneThousand (int number)
	{
		String soFar;
		// Esta dentro de los 1os. diecinueve?? ISCAP
		if (number % 100 < 20)
		{
			soFar = numNames[number % 100];
			number /= 100;
		}
		else
		{
			soFar = numNames[number % 10];
			number /= 10;
			String s = Integer.toString (number);
			if (s.endsWith ("2") && !soFar.equals(""))
				soFar = " VEINTI" + soFar.trim ();
			else if (soFar.equals(""))
				soFar = tensNames[number % 10] + soFar;
			else
				soFar = tensNames[number % 10] + " Y" + soFar;
			number /= 10;
		}
		if (number == 0)
		//return soFar;
		// Begin e-Evolution ogi-cd 		
			return tensNames[number % 10] + soFar; // e-Evolution ogi-cd
		// End e-Evolution ogi-cd
		if (number > 1)
			soFar = "S" + soFar;
		if (number == 1 && !soFar.equals(""))
			number = 0;
		return numNames[number] + " CIENTO" + soFar;
	}	//	convertLessThanOneThousand

	/**
	 * 	Convert
	 *	@param number
	 *	@return amt
	 */
	private String convert (int number)
	{
		/* special case */
		if (number == 0)
			return "CERO";
		String prefix = "";
		if (number < 0)
		{
			number = -number;
			prefix = "MENOS";
		}
		String soFar = "";
		int place = 0;
		do
		{
			int n = number % 1000;
			if (n != 0)
			{
				String s = convertLessThanOneThousand (n);
				if (s.startsWith ("CINCO CIENTOS", 1))
				{
					s = s.replaceFirst ("CINCO CIENTOS", "QUINIENTOS");
				}
				if (s.startsWith ("SIETE CIENTOS", 1))
				{
					s = s.replaceFirst ("SIETE CIENTOS", "SETECIENTOS");
				}
				if (s.startsWith ("NUEVE CIENTOS", 1))
				{
					s = s.replaceFirst ("NUEVE CIENTOS", "NOVECIENTOS");
				}
				if (s.equals(" UNO"))
				{
					soFar = majorNames[place] + soFar;
				}
				else
					soFar = s + majorNames[place] + soFar;
			}
			place++;
			number /= 1000;
		}
		while (number > 0);
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
    //	int pos = amount.lastIndexOf ('.');    // Old
		int pos = amount.lastIndexOf (',');  		
    //  int pos2 = amount.lastIndexOf (',');   // Old		
		int pos2 = amount.lastIndexOf ('.');
		if (pos2 > pos)
			pos = pos2;
		String oldamt = amount;

    //  amount = amount.replaceAll (",", "");   // Old
		amount = amount.replaceAll( "\\.","");

	//	int newpos = amount.lastIndexOf ('.');  // Old
		int newpos = amount.lastIndexOf (',');
		int pesos = Integer.parseInt (amount.substring (0, newpos));
		sb.append (convert (pesos));
		for (int i = 0; i < oldamt.length (); i++)
		{
			if (pos == i) //	we are done
			{
				String cents = oldamt.substring (i + 1);
				sb.append (' ')
					.append (cents)
					.append ("/100");
				//	.append ("/100 PESOS");
				break;
			}
		}
		return sb.toString ();
	}	//	getAmtInWords
	
}	//	AmtInWords_ES
