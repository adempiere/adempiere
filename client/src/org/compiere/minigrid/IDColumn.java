/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.minigrid;

/**
 *  ID Column for MiniGrid allows to select a column and maintains the record ID
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: IDColumn.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class IDColumn
{
	/**
	 *  ID Column constructor
	 *  @param record_ID
	 */
	public IDColumn (int record_ID)
	{
		this(new Integer(record_ID));
	}   //  IDColumn

	/**
	 *  ID Column constructor
	 *  @param record_ID
	 */
	public IDColumn(Integer record_ID)
	{
		super();
		setRecord_ID(record_ID);
		setSelected(false);
	}   //  IDColumn

	/** Is the row selected         */
	private boolean     m_selected = false;
	/** The Record_ID               */
	private Integer     m_record_ID;


	/**
	 *  Set Selection
	 *  @param selected
	 */
	public void setSelected(boolean selected)
	{
		m_selected = selected;
	}
	/**
	 *  Is Selected
	 *  @return true if selected
	 */
	public boolean isSelected()
	{
		return m_selected;
	}

	/**
	 *  Set Record_ID
	 *  @param record_ID
	 */
	public void setRecord_ID(Integer record_ID)
	{
		m_record_ID = record_ID;
	}
	/**
	 * Get Record ID
	 * @return ID
	 */
	public Integer getRecord_ID()
	{
		return m_record_ID;
	}

	/**
	 *  To String
	 *  @return String representation
	 */
	public String toString()
	{
		return "IDColumn - ID=" + m_record_ID + ", Selected=" + m_selected;
	}   //  toString

}   //  IDColumn
