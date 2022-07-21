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

package org.eevolution.distribution.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Movement Material Receipt from Distribution Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateMovementMaterialAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "M_Movement Material Receipt";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Movement Material Receipt from Distribution Order";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53736;
	/**	Parameter Name for Movement Date	*/
	private static final String MOVEMENTDATE = "MovementDate";
	/**	Parameter Value for Movement Date	*/
	private Timestamp movementDate;

	@Override
	protected void prepare() {
		movementDate = getParameterAsTimestamp(MOVEMENTDATE);
	}

	/**	 Getter Parameter Value for Movement Date	*/
	protected Timestamp getMovementDate() {
		return movementDate;
	}

	/**	 Setter Parameter Value for Movement Date	*/
	protected void setMovementDate(Timestamp movementDate) {
		this.movementDate = movementDate;
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