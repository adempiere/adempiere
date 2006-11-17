/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/**
 *	Model Validator
 *	
 *  @author Jorg Janke
 *  @version $Id: ModelValidator.java,v 1.2 2006/07/30 00:58:18 jjanke Exp $
 */
public interface ModelValidator
{
	/** Model Change Type New		*/
	public static final int	TYPE_NEW = 1;
	/** Model Change Type Change	*/
	public static final int	TYPE_CHANGE = 2;
	/** Model Change Type Delete	*/
	public static final int	TYPE_DELETE = 3;
	
	
	/**
	 * 	Initialize Validation
	 * 	@param engine validation engine 
	 *	@param client client
	 */
	public void initialize (ModelValidationEngine engine, MClient client);

	/**
	 * 	Get Client to be monitored
	 *	@return AD_Client_ID
	 */
	public int getAD_Client_ID();
	
	/**
	 * 	User logged in 
	 * 	Called before preferences are set
	 *	@param AD_Org_ID org
	 *	@param AD_Role_ID role
	 *	@param AD_User_ID user
	 *	@return error message or null
	 */
	public String login (int AD_Org_ID, int AD_Role_ID, int AD_User_ID);

	
    /**
     * 	Model Change of a monitored Table.
     * 	Called after PO.beforeSave/PO.beforeDelete 
     * 	when you called addModelChange for the table
     * 	@param po persistent object
     * 	@param type TYPE_
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
     */
	public String modelChange (PO po, int type) throws Exception;

	
	/**
	 * 	Validate Document.
	 * 	Called as first step of DocAction.prepareIt 
	 * 	or at the end of DocAction.completeIt
     * 	when you called addDocValidate for the table.
     * 	Note that totals, etc. may not be correct before the prepare stage.
	 *	@param po persistent object
	 *	@param timing see TIMING_ constants
     *	@return error message or null - 
     *	if not null, the pocument will be marked as Invalid.
	 */
	public String docValidate (PO po, int timing);
	
	
	/** Called before document is prepared		*/
	public static final int		TIMING_BEFORE_PREPARE = 1;
	/** Called after document is processed		*/
	public static final int		TIMING_AFTER_COMPLETE = 9;
	
}	//	ModelValidator
