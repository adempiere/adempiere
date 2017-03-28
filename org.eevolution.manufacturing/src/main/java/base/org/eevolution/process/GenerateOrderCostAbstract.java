/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
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

package org.eevolution.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
/** Generated Process for (Generate Manufacturing Order Cost)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateOrderCostAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "PP_Order GenerateOrderCost";
	/** Process Name 	*/
	private static final String NAME = "Generate Manufacturing Order Cost";
	/** Process Id 	*/
	private static final int ID = 53936;
 
	/**	Parameter Name for PP_Order_ID	*/
	public static final String PP_Order_ID = "PP_Order_ID";
	/**	Parameter Name for DateOrdered	*/
	public static final String DateOrdered = "DateOrdered";

	/**	Parameter Value for manufacturingOrderId	*/
	private int manufacturingOrderId;
	/**	Parameter Value for dateOrdered	*/
	private Timestamp dateOrdered;
	/**	Parameter Value for dateOrderedTo	*/
	private Timestamp dateOrderedTo;
 

	@Override
	protected void prepare()
	{
		manufacturingOrderId = getParameterAsInt(PP_Order_ID);
		dateOrdered = getParameterAsTimestamp(DateOrdered);
		dateOrderedTo = getParameterToAsTimestamp(DateOrdered);
	}

	/**	 Getter Parameter Value for manufacturingOrderId	*/
	protected int getManufacturingOrderId() {
		return manufacturingOrderId;
	}

	/**	 Getter Parameter Value for dateOrdered	*/
	protected Timestamp getDateOrdered() {
		return dateOrdered;
	}

	/**	 Getter Parameter Value for dateOrderedTo	*/
	protected Timestamp getDateOrderedTo() {
		return dateOrderedTo;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}
}