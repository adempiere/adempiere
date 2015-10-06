/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Cell;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Cell;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 * 
 * Append cell code copied from idempiere project.
 * See https://bitbucket.org/idempiere/idempiere/src/8d3b639fd52c/org.adempiere.ui.zk/WEB-INF/src/org/adempiere/webui/component/Row.java?at=development
 * 
 */
public class Row extends org.zkoss.zul.Row
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5996761805124218360L;
	private Cell m_lastCell;

	public boolean appendCellChild(Component child) {
		return this.appendCellChild(child, 1);
	}
	
	public boolean appendCellChild(Component child, int colspan) {
		Cell cell = new Cell();
		cell.setColspan(colspan);
		cell.setRowspan(1);
		cell.appendChild(child);
		m_lastCell = cell;
		return super.appendChild(cell);
	}

	private Group m_group;

	public Group getGroup() {
		return m_group;
	}

	public void setGroup(Group group) {
		this.m_group = group;
		if (m_group != null)
			m_group.add(this);
	}
	
	public Cell getLastCell() {
		return m_lastCell;
	}
}
