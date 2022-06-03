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

package org.adempiere.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (YearEndClosing)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class YearEndClosingAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "YearEndClosing";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "YearEndClosing";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54614;
	/**	Parameter Name for Account Element	*/
	public static final String C_ELEMENTVALUE_ID = "C_ElementValue_ID";
	/**	Parameter Name for Account Date	*/
	public static final String DATEACCT = "DateAcct";
	/**	Parameter Name for Accounting Schema	*/
	public static final String C_ACCTSCHEMA_ID = "C_AcctSchema_ID";
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for GL Category	*/
	public static final String GL_CATEGORY_ID = "GL_Category_ID";
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Value for Account Element	*/
	private int elementValueId;
	/**	Parameter Value for Account Date	*/
	private Timestamp dateAcct;
	/**	Parameter Value for Accounting Schema	*/
	private int acctSchemaId;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for GL Category	*/
	private int categoryId;
	/**	Parameter Value for Organization	*/
	private int orgId;

	@Override
	protected void prepare() {
		elementValueId = getParameterAsInt(C_ELEMENTVALUE_ID);
		dateAcct = getParameterAsTimestamp(DATEACCT);
		acctSchemaId = getParameterAsInt(C_ACCTSCHEMA_ID);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		categoryId = getParameterAsInt(GL_CATEGORY_ID);
		orgId = getParameterAsInt(AD_ORG_ID);
	}

	/**	 Getter Parameter Value for Account Element	*/
	protected int getElementValueId() {
		return elementValueId;
	}

	/**	 Setter Parameter Value for Account Element	*/
	protected void setElementValueId(int elementValueId) {
		this.elementValueId = elementValueId;
	}

	/**	 Getter Parameter Value for Account Date	*/
	protected Timestamp getDateAcct() {
		return dateAcct;
	}

	/**	 Setter Parameter Value for Account Date	*/
	protected void setDateAcct(Timestamp dateAcct) {
		this.dateAcct = dateAcct;
	}

	/**	 Getter Parameter Value for Accounting Schema	*/
	protected int getAcctSchemaId() {
		return acctSchemaId;
	}

	/**	 Setter Parameter Value for Accounting Schema	*/
	protected void setAcctSchemaId(int acctSchemaId) {
		this.acctSchemaId = acctSchemaId;
	}

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**	 Getter Parameter Value for GL Category	*/
	protected int getCategoryId() {
		return categoryId;
	}

	/**	 Setter Parameter Value for GL Category	*/
	protected void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
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