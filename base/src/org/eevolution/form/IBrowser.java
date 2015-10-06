/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  *
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

package org.eevolution.form;

import org.adempiere.model.I_AD_View_Column;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.compiere.model.MQuery;
import org.compiere.process.ProcessInfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor.perez@e-evolution.com , e-Evolution on 10/12/14.
 */
public interface IBrowser {

    public void setContextWhere(MBrowse browse, String where);

    public List<MBrowseField> initBrowserData();

    public void addSQLWhere(StringBuffer sql, int index, String value);

    public int getCount();

    public abstract ArrayList<Integer> getSelectedRowKeys();

    public ProcessInfo getProcessInfo();

    public void setBrowseProcessInfo(ProcessInfo pi);

    public ProcessInfo getBrowseProcessInfo();

    public String getKeyColumn();

    public Integer getSelectedRowKey();

    public List<Integer> getSelectedKeys();

    public Object getSelectedKey();

    public int getAD_Browse_ID();

    public List<MBrowseField> getInfoColumnForAxisField(MBrowseField field);

    public MBrowseField getFieldKey();

    public boolean IsIdentifierSelection(String columnName);

    public MQuery getMQuery();
    public Object getParameterValue(Object key);

    void setParameters();

    public String  getSQLWhere(boolean refresh);

    public String getAxisSQLWhere(I_AD_View_Column viewColumn);

    public String getSQLOrderBy();

    public abstract  ArrayList<ArrayList<Object>> getDataRows();

    public void createT_Selection_Browse(int AD_PInstance_ID);
}
