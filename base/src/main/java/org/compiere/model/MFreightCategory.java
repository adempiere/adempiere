/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Daniel Tamm                                          *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software, you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation, either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY, without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program, if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Daniel Tamm     (usrdno@users.sourceforge.net)                   *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.notima.se)                                   *
 * - Company (http://www.cyberphoto.se)                               *
 *********************************************************************/

package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
/**
 *
 * @author Daniel Tamm
 */
public class MFreightCategory extends X_M_FreightCategory {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4790439008800915010L;


	/**
     * Default constructor 
     * 
     * @param ctx                   Context
     * @param freightCategoryId  If set to 0 a new category is created.
     * @param trxName               Name of database transaction 
     */
    public MFreightCategory(Properties ctx, int freightCategoryId, String trxName) {
        super(ctx, freightCategoryId, trxName);
    }

    /**
     * Constructor using a resultset.
     * 
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MFreightCategory(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    
    /**
     * Reads a freight category from database based on the value (key) of the category.
     * No cache is used.
     * 
     * @param ctx       Context. The context is used to match only categories within the client.
     * @param value
     * @param trxName
     * @return      If a match is found, the freight category. No match returns null.
     */
    public static MFreightCategory getByValue(Properties ctx, String value, String trxName) {

        return new Query(ctx, MFreightCategory.Table_Name, "Value=?", trxName)
                .setClient_ID()
                .setParameters(value).first();
    }
    
}
