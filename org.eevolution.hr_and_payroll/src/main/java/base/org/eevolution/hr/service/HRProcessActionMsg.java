/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.eevolution.hr.service;

import org.compiere.util.CLogger;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/776">
 * 		@see FR [ 776 ] Add Message control for payroll process</a>
 *
 */
public class HRProcessActionMsg {

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(HRProcessActionMsg.class);
	/**	Constants for break a run payroll	*/
	//	For process
	public static final int SCOPE_PROCESS = 1;
	//	For employee
	public static final int SCOPE_EMPLOYEE = 2;
	//	For concept
	public static final int SCOPE_CONCEPT = 3;
	/**	Persistence							*/
	//	Save
	public static final int PERSISTENCE_SAVE = 1;
	//	Ignore
	public static final int PERSISTENCE_IGNORE = 2;
	/**	Action							*/
	//	Break
	public static final int ACTION_BREAK = 1;
	//	
	/**	Scope		*/
	private int 		scope;
	/**	Persistence	*/
	private int 		persistence;
	/**	Action		*/
	private int 		action;
	
	/**
	 * Standard constructor
	 */
	public HRProcessActionMsg() {
		scope = 0;
		persistence = 0;
		action = 0;
	}
	
	/**
	 * Set Scope
	 * @param scope
	 * @return
	 */
	public HRProcessActionMsg scope(int scope) {
		this.scope = scope;
		log.fine("Set Scope to " + scope);
		//	return it
		return this;
	}
	
	/**
	 * Set Persistence
	 * @param persistence
	 * @return
	 */
	public HRProcessActionMsg persistence(int persistence) {
		this.persistence = persistence;
		log.fine("Set Persistence to " + persistence);
		//	return it
		return this;
	}
	
	/**
	 * 
	 * @param action
	 * @return
	 */
	public HRProcessActionMsg action(int action) {
		this.action = action;
		log.fine("Set Action to " + action);
		//	return it
		return this;
	}
	
	/**
	 * Break Running
	 */
	public void breakRunning() {
		action = ACTION_BREAK;
		log.fine("Set Action to " + action);
	}
	
	/**
	 * Is Employee scope
	 * @return
	 */
	public boolean isEmployeeScope() {
		return scope == SCOPE_EMPLOYEE;
	}
	
	/**
	 * Is Concept scope
	 * @return
	 */
	public boolean isConceptScope() {
		return scope == SCOPE_CONCEPT;
	}
	
	/**
	 * Is Process scope
	 * @return
	 */
	public boolean isProcessScope() {
		return scope == SCOPE_PROCESS;
	}
	
	/**
	 * Is ignored for save
	 * @return
	 */
	public boolean isIgnorePersistence() {
		return persistence == PERSISTENCE_IGNORE;
	}
	
	/**
	 * Save Persistence
	 * @return
	 */
	public boolean isSavePersistence() {
		return persistence == PERSISTENCE_SAVE;
	}
	
	/**
	 * Is Break Running
	 * @return
	 */
	public boolean isBreakRunning() {
		return action == ACTION_BREAK;
	}
	
	/**
	 * Clear scope
	 */
	public void clearScope() {
		scope = 0;
	}
	
	/**
	 * Clear Action
	 */
	public void clearAction() {
		action = 0;
	}
	
	/**
	 * Clear Persistence
	 */
	public void clearPersistence() {
		persistence = 0;
	}
}
