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
 * 12-Jul-2006 16:05:22 by praveen
 *
 */

 
package org.posterita.core;

public class EmailValidator
{
	public static boolean isValidEmail(String theEmail)
	{
	  if(theEmail == null) 
	  	return false;
	  
	  else if(theEmail.length() == 0) 
	  	return false;
	  
	  else if(theEmail.length() > 78) // email addresses must fit on one line
		return false;
	  
	  else
		{
		  // if last character is a period, remove it
		  if(theEmail.charAt(theEmail.length()-1) == '.')
				theEmail = theEmail.substring(0, theEmail.length() -1);
		  
		  //check for presence of "at" sign
		  int atIndex = theEmail.indexOf('@');
		  
		  if(atIndex < 0) return false;

		  //check for valid characters in the mailbox portion of the address
		  String mailbox = theEmail.substring(0, atIndex);
		  
		  if(mailbox == null || mailbox.length() == 0) 
		  	return false;
		  
		  else
		  {
			  int oldDotIndex = 0;
			  int dotEnd = mailbox.indexOf('.', oldDotIndex);
			  boolean done = false;
			  do
				{
				  //cannot have 2 dots in a row
				  if(dotEnd == oldDotIndex) 
				  	return false;
				  	
				  if(dotEnd < 0) 
					{
					  dotEnd = mailbox.length();
					  done = true;
					}
				  
				  for(int i = oldDotIndex; i < dotEnd; i++)
  				  {
					  char cur = mailbox.charAt(i);
					  
					  if(!Character.isLetter(cur) &&
						 !Character.isDigit(cur) &&
						 cur != '!' && cur != '#' &&
						 cur != '$' && cur != '%' &&
						 cur != '&' && cur != '\'' &&
						 cur != '*' && cur != '+' &&
						 cur != '-' && cur != '/' &&
						 cur != '=' && cur != '?' &&
						 cur != '^' && cur != '_' &&
						 cur != '`' && cur != '{' &&
						 cur != '|' && cur != '}' &&
						 cur != '~')
						return false;
				  }
				  
				  oldDotIndex = dotEnd+1;
				  dotEnd = mailbox.indexOf('.', oldDotIndex+1);
				}
			  while(!done);
			  }
	
			  //check for a valid domain name
			  String domain = theEmail.substring(atIndex+1);
			  
			  if(domain == null || domain.length() == 0) 
			  	return false;
			  
			  else
			  {
			  //domains are divided into groups separated by dots
			  int oldDotIndex = 0;
			  int dotEnd = domain.indexOf('.', oldDotIndex);
			  
			  if(dotEnd < 0) 
			  	return false;
			  
			 		  
			  do
				{
				  //cannot have 2 dots in a row
				  if(oldDotIndex == dotEnd) 
				  	return false;
				  
				  //first character must be letter or digit
				  if(!Character.isLetter(domain.charAt(oldDotIndex)) &&
				 	 !Character.isDigit(domain.charAt(oldDotIndex)))
					return false;
				  
				  //middle characters can be digits, letters, or hyphens
				  for(int i = oldDotIndex+1; i < dotEnd - 1; i++)
				  {
				  	char cur = domain.charAt(i);
				  
					if(!Character.isLetter(cur) &&
						!Character.isDigit(cur) &&
						cur != '-')
						return false;
				  }
				  
				  //last character must be a letter or digit
				  if(!Character.isLetter(domain.charAt(dotEnd - 1)) &&
				 	 !Character.isDigit(domain.charAt(dotEnd - 1)))
					return false;
				  
				  oldDotIndex = dotEnd+1;
				  dotEnd = domain.indexOf('.', oldDotIndex+1);
				}
				
			  while(dotEnd > 0);
	
			  //check the last part of the domain
			  String ext = domain.substring(oldDotIndex);
			  
			  if(ext == null || ext.length() == 0) 
			  	return false;
			  
			  else if(ext.length() < 2 || ext.length() > 4)
				return false;
			  
			  else
			  {
				  for(int i = 0; i < ext.length(); i++)
				  {
					  if(!Character.isLetter(ext.charAt(i)) &&
						 !Character.isDigit(ext.charAt(i)))
						return false;
				  }
			  }
	
			}
			}    
		  return true;
	} // end isValidEmail

}
