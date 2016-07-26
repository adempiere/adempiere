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
/** Generated Process for (Generate Cost Transaction)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class GenerateCostDetailAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "M_CostDetail Generate Cost Transaction";
	/** Process Name 	*/
	private static final String NAME = "Generate Cost Transaction";
	/** Process Id 	*/
	private static final int ID = 53223;
 
	/**	Parameter Name for C_AcctSchema_ID	*/
	public static final String C_AcctSchema_ID = "C_AcctSchema_ID";
	/**	Parameter Name for M_CostType_ID	*/
	public static final String M_CostType_ID = "M_CostType_ID";
	/**	Parameter Name for M_CostElement_ID	*/
	public static final String M_CostElement_ID = "M_CostElement_ID";
	/**	Parameter Name for M_Product_ID	*/
	public static final String M_Product_ID = "M_Product_ID";
	/**	Parameter Name for DateAcct	*/
	public static final String DateAcct = "DateAcct";

	/**	Parameter Value for accountingSchemaId	*/
	private int accountingSchemaId;
	/**	Parameter Value for costTypeId	*/
	private int costTypeId;
	/**	Parameter Value for costElementId	*/
	private int costElementId;
	/**	Parameter Value for productId	*/
	private int productId;
	/**	Parameter Value for accountDate	*/
	private Timestamp accountDate;
	/**	Parameter Value for accountDateTo	*/
	private Timestamp accountDateTo;
 

	@Override
	protected void prepare()
	{
		accountingSchemaId = getParameterAsInt(C_AcctSchema_ID);
		costTypeId = getParameterAsInt(M_CostType_ID);
		costElementId = getParameterAsInt(M_CostElement_ID);
		productId = getParameterAsInt(M_Product_ID);
		accountDate = getParameterAsTimestamp(DateAcct);
		accountDateTo = getParameterToAsTimestamp(DateAcct);
	}

	/**	 Getter Parameter Value for accountingSchemaId	*/
	protected int getAccountingSchemaId() {
		return accountingSchemaId;
	}

	/**	 Getter Parameter Value for costTypeId	*/
	protected int getCostTypeId() {
		return costTypeId;
	}

	/**	 Getter Parameter Value for costElementId	*/
	protected int getCostElementId() {
		return costElementId;
	}

	/**	 Getter Parameter Value for productId	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Getter Parameter Value for accountDate	*/
	protected Timestamp getAccountDate() {
		return accountDate;
	}

	/**	 Getter Parameter Value for accountDateTo	*/
	protected Timestamp getAccountDateTo() {
		return accountDateTo;
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