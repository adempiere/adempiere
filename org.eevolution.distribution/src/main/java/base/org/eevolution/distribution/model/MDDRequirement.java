/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software, you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * or (at your option) any later version.
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program, if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * For the text or an alternative of this public license, you may reach us
 * or via info@adempiere.net or http://www.adempiere.net/license.html
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.distribution.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_DD_Requirement;

/**
 * Domain Model for Requirement
 * @author victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 */
public class MDDRequirement extends X_DD_Requirement {

    /**
     * Contructor Requirement
     * @param ctx
     * @param requirementId
     * @param trxName
     */
    public MDDRequirement(Properties ctx, int requirementId, String trxName) {
        super(ctx, requirementId, trxName);
    }

    /**
     * Contructor Requirement
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MDDRequirement(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
