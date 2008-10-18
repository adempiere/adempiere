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
 * Created on 26-Aug-2005 by alok
 *
 */

package org.posterita.formatter;

/*
 * Copyright (c) 2004 Kaizensoft Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Kaizensoft Ltd. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Kaizensoft.
 *
 * KAIZENSOFT MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. KAIZENSOFT SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * Created on Oct 13, 2004
 */



import org.posterita.core.JulianDate;
import org.posterita.core.SimpleCalendarDate;

public class SimpleCalendarDateFormatter extends Formatter
{

	/* (non-Javadoc)
	 * @see com.shortletting.formatter.Formatter#format(java.lang.Object)
	 */
	public Object format(Object target)
	{
		SimpleCalendarDate myDate = (SimpleCalendarDate) target;
		
		IntegerFormatter formatter = new IntegerFormatter();
		
		Integer intDay = (Integer) formatter.unformat(myDate.getDay());
		Integer intMonth = (Integer) formatter.unformat(myDate.getMonth());
		Integer intYear = (Integer) formatter.unformat(myDate.getYear());
		
		if (intDay == null || intMonth == null || intYear == null)
			return null;
		
		JulianDate date = new JulianDate(intYear.intValue(), intMonth.intValue(), intDay.intValue());
		
		JulianDateFormatter dateFormatter = new JulianDateFormatter();
		return (String) dateFormatter.format(date);
	}

	/* (non-Javadoc)
	 * @see com.shortletting.formatter.Formatter#unformat(java.lang.Object)
	 */
	public Object unformat(Object target)
	{
		
		throw new RuntimeException("Method not implemented");
		
	}

}
