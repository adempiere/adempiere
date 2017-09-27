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
package org.adempiere.util;

import org.compiere.model.MRole;
import org.compiere.util.NamePair;


/**
 * List Item
 * @author Teo Sarca
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/990">
 * 		@see FR [ 990 ] Sort Tab is not MVC</a>
 */
public class ListElement extends NamePair {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5399675004361331697L;
	private int		m_key;
	private int		clientId;
	private int		orgId;
	/** Initial seq number */
	private int		sortNo;
	/** Initial selection flag */
	private boolean isYes;
	private boolean	updateable;
	
	/**
	 * Standard Constructor
	 * @param key
	 * @param name
	 * @param sortNo
	 * @param isYes
	 * @param clientId
	 * @param orgId
	 * @param tableId
	 */
	public ListElement(int key, String name, int sortNo, boolean isYes, int clientId, int orgId, int tableId) {
		super(name);
		this.m_key = key;
		this.clientId = clientId;
		this.orgId = orgId;
		this.sortNo = sortNo;
		this.isYes = isYes;
		this.updateable = MRole.getDefault().canUpdate(clientId, orgId, tableId, m_key, false); 
	}
	
	/**
	 * Get Key
	 * @return
	 */
	public int getKey() {
		return m_key;
	}
	
	/**
	 * Set Sort No
	 * @param sortNo
	 */
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	
	/**
	 * Get Sort No
	 * @return
	 */
	public int getSortNo() {
		return sortNo;
	}
	
	/**
	 * Set Yes
	 * @param value
	 */
	public void setIsYes(boolean value) {
		isYes = value;
	}
	
	/**
	 * Verify if is yes
	 * @return
	 */
	public boolean isYes() {
		return isYes;
	}
	
	/**
	 * Get Client ID
	 * @return
	 */
	public int getAD_Client_ID() {
		return clientId;
	}
	
	/**
	 * Get Org ID
	 * @return
	 */
	public int getAD_Org_ID() {
		return orgId;
	}
	
	/**
	 * Is Udateable
	 * @return
	 */
	public boolean isUpdateable() {
		return updateable;
	}
	
	@Override
	public String getID() {
		return m_key != -1 ? String.valueOf(m_key) : null;
	}
	
	@Override
	public int hashCode() {
		return m_key;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ListElement) {
			ListElement li = (ListElement)obj;
			return
				li.getKey() == m_key
				&& li.getName() != null
				&& li.getName().equals(getName())
				&& li.getAD_Client_ID() == clientId
				&& li.getAD_Org_ID() == orgId;
		}
		return false;
	}	//	equals
	
	@Override
	public String toString() {
		String s = super.toString();
		if (s == null || s.trim().length() == 0)
			s = "<" + getKey() + ">";
		return s;
	}
}
