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
 * Copyright (C) 2003-2014 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.hr.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by victor.perez@e-evolution.com, e-Evolution on 04/12/13.
 */
public class MHRDepartmentProduct extends X_HR_DepartmentProduct {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5048132732295387986L;

	public MHRDepartmentProduct(Properties ctx, int HR_DepartmentProduct_ID, String trxName) {
        super(ctx, HR_DepartmentProduct_ID, trxName);
    }

    public MHRDepartmentProduct(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
