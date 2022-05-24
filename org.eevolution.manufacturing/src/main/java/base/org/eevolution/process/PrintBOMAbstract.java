/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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

import org.compiere.process.SvrProcess;

/** Generated Process for (Multi Level BOM & Formula Detail)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class PrintBOMAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "PP_Multi Level BOM & Formula";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Multi Level BOM & Formula Detail";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53006;
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Implosion	*/
	public static final String IMPLOSION = "Implosion";
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Implosion	*/
	private boolean isImplosion;

	@Override
	protected void prepare() {
		productId = getParameterAsInt(M_PRODUCT_ID);
		isImplosion = getParameterAsBoolean(IMPLOSION);
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Implosion	*/
	protected boolean isImplosion() {
		return isImplosion;
	}

	/**	 Setter Parameter Value for Implosion	*/
	protected void setImplosion(boolean isImplosion) {
		this.isImplosion = isImplosion;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}