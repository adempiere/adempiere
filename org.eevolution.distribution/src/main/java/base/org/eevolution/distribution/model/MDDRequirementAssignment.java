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

import org.compiere.model.Query;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;


/**
 * Domain Model for Requirement Assignment
 * @author victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 */
public class MDDRequirementAssignment extends X_DD_RequirementAssignment {

    /**
     * get Requirement Assignment
     * @param driver
     * @return
     */
    static public List<MDDRequirementAssignment> getByDriver(org.eevolution.distribution.model.MDDDriver driver)
    {
        return new Query(driver.getCtx(), Table_Name , COLUMNNAME_DD_Driver_ID +  "=?", driver.get_TableName())
                .setClient_ID()
                .setParameters(driver.getDD_Driver_ID())
                .setOrderBy(MDDRequirementAssignment.COLUMNNAME_SeqNo)
                .list();
    }

    /**
     * get Requirement Assignment
     * @param vehicle
     * @return
     */
    static public List<MDDRequirementAssignment> getByVehicle(org.eevolution.distribution.model.MDDVehicle vehicle)
    {
        return new Query(vehicle.getCtx(), Table_Name , COLUMNNAME_DD_Vehicle_ID +  "=?", vehicle.get_TableName())
                .setClient_ID()
                .setParameters(vehicle.getDD_Vehicle_ID())
                .setOrderBy(MDDRequirementAssignment.COLUMNNAME_SeqNo)
                .list();
    }

    /**
     * Constructor Requirement Assignment
     * @param ctx Requirement Assignment
     * @param requirementAssignmentId
     * @param trxName
     */
    public MDDRequirementAssignment(Properties ctx, int requirementAssignmentId, String trxName) {
        super(ctx, requirementAssignmentId, trxName);
    }

    /**
     * Constructor Requirement Assignment
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MDDRequirementAssignment(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Constructor Requirement Assignment
     * @param driver
     * @param requirementId
     */
    public MDDRequirementAssignment(MDDDriver driver, int requirementId)
    {
        super(driver.getCtx() , 0 , driver.get_TrxName());
        setDD_Requirement_ID(requirementId);
        setDD_Driver_ID(driver.get_ID());
        setIsValid(false);
    }

    /**
     * Constructor Requirement Assignment
     * @param vehicle
     * @param requirementId
     */
    public MDDRequirementAssignment(MDDVehicle vehicle , int requirementId)
    {
        super(vehicle.getCtx() , 0 , vehicle.get_TrxName());
        setDD_Requirement_ID(requirementId);
        setDD_Vehicle_ID(vehicle.get_ID());
        setIsValid(false);
    }
}
