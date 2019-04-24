/*************************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.util.docs;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Abstract class for Text converter
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public interface IIndex {
	
	/**
	 * Add Group to Index
	 * @param title
	 * @param name
	 * @param margin
	 */
	public void addGroup(String title, String name, int margin);
	
	/**
	 * Add a tree for file
	 * @param maxdepth
	 * @param isnumbered
	 * @return
	 */
	public void addTreeDefinition(int maxdepth, boolean isnumbered);
	
	/**
	 * Add Index value
	 * Used for File as index
	 * @param title
	 * @param name
	 * @param folder
	 * @param margin
	 * @return
	 */
	public void addIndex(String title, String name, String folder, int margin);
}
