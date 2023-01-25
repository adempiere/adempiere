/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/

package org.adempiere.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Configure Centralized ID Management)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ConfigureCentralizedIdManagementAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "ConfigureCentralizedIdManagement";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Configure Centralized ID Management";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54621;
	/**	Parameter Name for Dictionary Website	*/
	public static final String DICTIONARYWEBSITE = "DictionaryWebsite";
	/**	Parameter Name for Dictionary User	*/
	public static final String DICTIONARYUSER = "DictionaryUser";
	/**	Parameter Name for Dictionary Password	*/
	public static final String DICTIONARYPASSWORD = "DictionaryPassword";
	/**	Parameter Name for Dictionary Comments	*/
	public static final String DICTIONARYCOMMENTS = "DictionaryComments";
	/**	Parameter Name for Is Dictionary Use Centralized ID	*/
	public static final String ISDICTIONARYUSECENTRALIZEDID = "IsDictionaryUseCentralizedID";
	/**	Parameter Value for Dictionary Website	*/
	private String dictionaryWebsite;
	/**	Parameter Value for Dictionary User	*/
	private String dictionaryUser;
	/**	Parameter Value for Dictionary Password	*/
	private String dictionaryPassword;
	/**	Parameter Value for Dictionary Comments	*/
	private String dictionaryComments;
	/**	Parameter Value for Is Dictionary Use Centralized ID	*/
	private boolean isDictionaryUseCentralizedID;

	@Override
	protected void prepare() {
		dictionaryWebsite = getParameterAsString(DICTIONARYWEBSITE);
		dictionaryUser = getParameterAsString(DICTIONARYUSER);
		dictionaryPassword = getParameterAsString(DICTIONARYPASSWORD);
		dictionaryComments = getParameterAsString(DICTIONARYCOMMENTS);
		isDictionaryUseCentralizedID = getParameterAsBoolean(ISDICTIONARYUSECENTRALIZEDID);
	}

	/**	 Getter Parameter Value for Dictionary Website	*/
	protected String getDictionaryWebsite() {
		return dictionaryWebsite;
	}

	/**	 Setter Parameter Value for Dictionary Website	*/
	protected void setDictionaryWebsite(String dictionaryWebsite) {
		this.dictionaryWebsite = dictionaryWebsite;
	}

	/**	 Getter Parameter Value for Dictionary User	*/
	protected String getDictionaryUser() {
		return dictionaryUser;
	}

	/**	 Setter Parameter Value for Dictionary User	*/
	protected void setDictionaryUser(String dictionaryUser) {
		this.dictionaryUser = dictionaryUser;
	}

	/**	 Getter Parameter Value for Dictionary Password	*/
	protected String getDictionaryPassword() {
		return dictionaryPassword;
	}

	/**	 Setter Parameter Value for Dictionary Password	*/
	protected void setDictionaryPassword(String dictionaryPassword) {
		this.dictionaryPassword = dictionaryPassword;
	}

	/**	 Getter Parameter Value for Dictionary Comments	*/
	protected String getDictionaryComments() {
		return dictionaryComments;
	}

	/**	 Setter Parameter Value for Dictionary Comments	*/
	protected void setDictionaryComments(String dictionaryComments) {
		this.dictionaryComments = dictionaryComments;
	}

	/**	 Getter Parameter Value for Is Dictionary Use Centralized ID	*/
	protected boolean isDictionaryUseCentralizedID() {
		return isDictionaryUseCentralizedID;
	}

	/**	 Setter Parameter Value for Is Dictionary Use Centralized ID	*/
	protected void setIsDictionaryUseCentralizedID(boolean isDictionaryUseCentralizedID) {
		this.isDictionaryUseCentralizedID = isDictionaryUseCentralizedID;
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
