/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2014 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2014 Victor Pérez Juárez 								  *
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.grid;

import org.adempiere.model.MBrowseField;
import org.compiere.model.GridField;
import java.util.LinkedHashMap;

/**
 * Created by e-Evolution on 13/12/14.
 * @author victor.perez@www.e-evolution.com, e-Evolution
 */
public interface IBrowserRows {

    public void addBrowserField(MBrowseField field, int column);

    public MBrowseField getBrowserField(int col);

    //public void setRow(int id, ArrayList<Object> row);

    public void setValue(int row, int col, GridField value);

    public Object getValue(int id, int col);

    public LinkedHashMap<Integer, MBrowseField> getBrowserFields();

    public LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>> getRows();

    public int size();

    public void clear() ;

    public int getColumnCount();

    public Integer getNoViewColumns();

    public int getDisplayIndex(int index) ;

    public int getTableIndex(int display);

    public int getSelectedRow();

    public int getSelectedColumn();

    public Object getValueOfSelectedCell();

    public void setValueOfSelectedCell(GridField gridField);

    public Object getValueOfColumn(int col);

    public void setValueOfColumn(int row, String ColumnName, GridField gridField) ;

    public Object getValueOfColumn(int row, String ColumnName);

    public String toString();

}
