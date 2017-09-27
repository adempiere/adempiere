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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.eevolution.grid;

import java.util.List;

import org.adempiere.controller.SmallViewController;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *	Controller for Browser Search, it allow to developer create different views from it
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 344 ] Smart Browse Search View is not MVC
 * 		@see https://github.com/adempiere/adempiere/issues/344
 * 		<li><a href="https://github.com/adempiere/adempiere/issues/556">
 * 		FR [ 556 ] Criteria Search on SB don't have a parameter like only information</a>
 *	@author Michael Mckay michael.mckay@mckayerp.com
 *		<li> BR [ <a href="https://github.com/adempiere/adempiere/issues/495">495</a> ] 
 *			Parameter Panel & SmartBrowser criteria do not set gridField value
 */
public abstract class BrowserSearch extends SmallViewController {
	
	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param p_AD_Browse_ID browser
	 *  @param columns
	 */
	public BrowserSearch(int WindowNo, int p_AD_Browse_ID, int columns) {
		//	Get parameters
		m_WindowNo = WindowNo;
		m_AD_Browse_ID = p_AD_Browse_ID;
		m_Columns = columns;
			
		//init();
		
//		if (!isLoaded())
//			dispose();

	}	//	BrowserSearch
	
	/**
	 * Standard Constructor
	 * @param WindowNo
	 * @param p_AD_Browse_ID
	 */
	public BrowserSearch(int WindowNo, int p_AD_Browse_ID) {
		this(WindowNo, p_AD_Browse_ID, COLUMNS_1);
	}

	private int			m_WindowNo;
	private int 		m_AD_Browse_ID;
	private int 		m_Columns;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(BrowserSearch.class);
	//	Constants
	/**	For one Column		*/
	public static final int COLUMNS_1 = 1;
	/**	For two Column		*/
	public static final int COLUMNS_2 = 2;
	//
				
			
	/**
	 * Get Columns
	 * @return
	 */
	public int getColumns() {
		return m_Columns;
	}
	
	/**
	 * Set Columns
	 * @param columns
	 */
	public void setColumns(int columns) {
		m_Columns = columns;
	}
	
	/**
	 *	Read Fields to display
	 *  @return true if loaded OK
	 */
	public boolean loadData() {
		log.config("");		
		//	Create Fields
		MBrowse browse = MBrowse.get(Env.getCtx(), m_AD_Browse_ID);
		//	Valid null
		if(browse == null)
			return false;
		//	
		List<MBrowseField> fields = browse.getCriteriaFields();
		if(fields == null
				|| fields.size() == 0)
			return false;
		//	Add fields
		for (MBrowseField field : fields) {
			createField (field, m_WindowNo);
		}
		//	
		return true;
	}	//	init
				
	/**
	 * Get the Window number
	 * @return
	 */
	public int getWindowNo() {
		return m_WindowNo;
	}
	
}	//	BrowserSearch
