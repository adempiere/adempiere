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

package org.spin.process.rpl.exp;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Test Replication Strategy)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class TestReplicationStrategyAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "AD_ReplicationStrategyTest";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Test Replication Strategy";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54421;
	/**	Parameter Name for Replication Strategy	*/
	public static final String AD_REPLICATIONSTRATEGY_ID = "AD_ReplicationStrategy_ID";
	/**	Parameter Name for UpdatedDate	*/
	public static final String UPDATEDDATE = "UpdatedDate";
	/**	Parameter Value for Replication Strategy	*/
	private int replicationStrategyId;
	/**	Parameter Value for UpdatedDate	*/
	private Timestamp updatedDate;
	/**	Parameter Value for UpdatedDate(To)	*/
	private Timestamp updatedDateTo;

	@Override
	protected void prepare() {
		replicationStrategyId = getParameterAsInt(AD_REPLICATIONSTRATEGY_ID);
		updatedDate = getParameterAsTimestamp(UPDATEDDATE);
		updatedDateTo = getParameterToAsTimestamp(UPDATEDDATE);
	}

	/**	 Getter Parameter Value for Replication Strategy	*/
	protected int getReplicationStrategyId() {
		return replicationStrategyId;
	}

	/**	 Setter Parameter Value for Replication Strategy	*/
	protected void setReplicationStrategyId(int replicationStrategyId) {
		this.replicationStrategyId = replicationStrategyId;
	}

	/**	 Getter Parameter Value for UpdatedDate	*/
	protected Timestamp getUpdatedDate() {
		return updatedDate;
	}

	/**	 Setter Parameter Value for UpdatedDate	*/
	protected void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**	 Getter Parameter Value for UpdatedDate(To)	*/
	protected Timestamp getUpdatedDateTo() {
		return updatedDateTo;
	}

	/**	 Setter Parameter Value for UpdatedDate(To)	*/
	protected void setUpdatedDateTo(Timestamp updatedDateTo) {
		this.updatedDateTo = updatedDateTo;
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