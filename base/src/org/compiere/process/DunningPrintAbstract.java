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

package org.compiere.process;



/** Generated Process for (Print Dunning Letters)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class DunningPrintAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_DunningPrint";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Print Dunning Letters";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 312;
	/**	Parameter Name for EMail PDF	*/
	public static final String EMAILPDF = "EMailPDF";
	/**	Parameter Name for Mail Template	*/
	public static final String R_MAILTEXT_ID = "R_MailText_ID";
	/**	Parameter Name for Dunning Run	*/
	public static final String C_DUNNINGRUN_ID = "C_DunningRun_ID";
	/**	Parameter Name for Only If BP has Balance	*/
	public static final String ISONLYIFBPBALANCE = "IsOnlyIfBPBalance";
	/**	Parameter Name for Print Unprocessed Entries Only	*/
	public static final String PRINTUNPROCESSEDONLY = "PrintUnprocessedOnly";
	/**	Parameter Value for EMail PDF	*/
	private boolean isEMailPDF;
	/**	Parameter Value for Mail Template	*/
	private int mailTextId;
	/**	Parameter Value for Dunning Run	*/
	private int dunningRunId;
	/**	Parameter Value for Only If BP has Balance	*/
	private boolean isOnlyIfBPBalance;
	/**	Parameter Value for Print Unprocessed Entries Only	*/
	private boolean isPrintUnprocessedOnly;

	@Override
	protected void prepare() {
		isEMailPDF = getParameterAsBoolean(EMAILPDF);
		mailTextId = getParameterAsInt(R_MAILTEXT_ID);
		dunningRunId = getParameterAsInt(C_DUNNINGRUN_ID);
		isOnlyIfBPBalance = getParameterAsBoolean(ISONLYIFBPBALANCE);
		isPrintUnprocessedOnly = getParameterAsBoolean(PRINTUNPROCESSEDONLY);
	}

	/**	 Getter Parameter Value for EMail PDF	*/
	protected boolean isEMailPDF() {
		return isEMailPDF;
	}

	/**	 Setter Parameter Value for EMail PDF	*/
	protected void setEMailPDF(boolean isEMailPDF) {
		this.isEMailPDF = isEMailPDF;
	}

	/**	 Getter Parameter Value for Mail Template	*/
	protected int getMailTextId() {
		return mailTextId;
	}

	/**	 Setter Parameter Value for Mail Template	*/
	protected void setMailTextId(int mailTextId) {
		this.mailTextId = mailTextId;
	}

	/**	 Getter Parameter Value for Dunning Run	*/
	protected int getDunningRunId() {
		return dunningRunId;
	}

	/**	 Setter Parameter Value for Dunning Run	*/
	protected void setDunningRunId(int dunningRunId) {
		this.dunningRunId = dunningRunId;
	}

	/**	 Getter Parameter Value for Only If BP has Balance	*/
	protected boolean isOnlyIfBPBalance() {
		return isOnlyIfBPBalance;
	}

	/**	 Setter Parameter Value for Only If BP has Balance	*/
	protected void setIsOnlyIfBPBalance(boolean isOnlyIfBPBalance) {
		this.isOnlyIfBPBalance = isOnlyIfBPBalance;
	}

	/**	 Getter Parameter Value for Print Unprocessed Entries Only	*/
	protected boolean isPrintUnprocessedOnly() {
		return isPrintUnprocessedOnly;
	}

	/**	 Setter Parameter Value for Print Unprocessed Entries Only	*/
	protected void setPrintUnprocessedOnly(boolean isPrintUnprocessedOnly) {
		this.isPrintUnprocessedOnly = isPrintUnprocessedOnly;
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