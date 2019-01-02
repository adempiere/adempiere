/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
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
package org.adempiere.model;

/**
 * Info class for meta data of zoom across
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 2153 ] ZoomAcross for BP produces invalid sql
 * @see https://github.com/adempiere/adempiere/issues/2153
 */
public class ZoomMetaInfo {

	/**	PO Window Id	*/
	private final int pOWindowId;
	/**	SO window Id	*/
	private final int windowId;
	/**	PO Tab Id	*/
	private final int pOTabId;
	/**	SO Tab Id	*/
	private final int tabId;
	/**	Zoom PO Name	*/
	private final String pOName;
	/**	Zoom SO Name	*/
	private final String name;
	/**	Table Name	*/
	private final String targetTableName;
	
	/**
	 * Default constructor
	 * @param windowId
	 * @param pOWindowId
	 * @param tabId
	 * @param pOTabId
	 * @param name
	 * @param pOName
	 * @param targetTableName
	 */
	public ZoomMetaInfo(int windowId, int pOWindowId, int tabId, int pOTabId, String name, String pOName, String targetTableName) {
		this.windowId = windowId;
		this.pOWindowId = pOWindowId;
		this.tabId = tabId;
		this.pOTabId = pOTabId;
		this.name = name;
		this.pOName = pOName;
		this.targetTableName = targetTableName;
	}

	/**
	 * @return the targetTableName
	 */
	public final String getTargetTableName() {
		return targetTableName;
	}

	/**
	 * @return the pOWindowId
	 */
	public final int getpOWindowId() {
		return pOWindowId;
	}
	
	/**
	 * @return the windowId
	 */
	public final int getWindowId() {
		return windowId;
	}
	
	/**
	 * @return the pOTabId
	 */
	public final int getpOTabId() {
		return pOTabId;
	}
	
	/**
	 * @return the tabId
	 */
	public final int getTabId() {
		return tabId;
	}
	
	/**
	 * @return the pOName
	 */
	public final String getpOName() {
		return pOName;
	}
	
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "ZoomMetaInfo [pOWindowId=" + pOWindowId + ", windowId=" + windowId + ", pOTabId=" + pOTabId + ", tabId="
				+ tabId + ", pOName=" + pOName + ", name=" + name + "]";
	}
}
