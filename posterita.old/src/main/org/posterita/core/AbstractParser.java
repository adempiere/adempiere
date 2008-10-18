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

import org.posterita.exceptions.OperationException;



/**
 * @author vikram
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */


public abstract class AbstractParser 
{
	protected static final String START_TAG = "<";
	protected static final String END_TAG = ">";
	protected static final String START_COMMENT = "<!--";
	protected static final String END_COMMENT = "-->";
	
	protected  String removeTag(String strData, String startTagData, String endTagData) throws OperationException
	{
		int startTag;
		int endTag;
		startTag = strData.indexOf(startTagData);
		endTag = strData.indexOf(endTagData);

		if (startTag == -1)
		{
			throw new OperationException("No startTag found");
		}

		if (endTag == -1)
		{
			throw new OperationException("No endTag found");
		}
		if (startTag > endTag)
		{
			throw new OperationException("startTag greater than endTag found");
		}
		String beginning;
		String end;

		beginning = strData.substring(0, startTag +startTagData.length()-1);
		end = strData.substring(endTag +startTagData.length());
		strData = beginning.concat(end);

		return strData;
	}

	protected  String removeTags(String str) throws OperationException
	{
		while (str.indexOf(START_TAG) != -1)
		{
			str = removeTag(str, START_TAG, END_TAG);
		}

		return str;
	}
	
	
	protected String removeComments(String str) throws OperationException
	{
		while (str.indexOf(START_COMMENT) != -1)
		{
			str = removeTag(str, START_COMMENT, END_COMMENT);
		}

		return str;
	}

}
