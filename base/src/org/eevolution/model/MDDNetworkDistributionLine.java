/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
 * Copyright (C) 2003-2008 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.*;
import org.compiere.util.Env;

/**Network Distribution
*  @author Victor Perez,e-Evolution,SC
*  @version $Id: MDDNetworkDistributionLine.java,v 
*/
public class MDDNetworkDistributionLine extends X_DD_NetworkDistributionLine
{


    /** Standard Constructor */
    public MDDNetworkDistributionLine (Properties ctx, int DD_NetworkDistributionLine_ID, String trxName)
    {
      super (ctx, DD_NetworkDistributionLine_ID, trxName);
      if (DD_NetworkDistributionLine_ID == 0)
        {
			/*setDD_NetworkDistributionLine_ID (0);
			setDD_NetworkDistribution_ID (0);
			setM_WarehouseSource_ID (0);
			setM_Warehouse_ID (0);*/
        } 
    }

    /** Load Constructor */
    public MDDNetworkDistributionLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
}