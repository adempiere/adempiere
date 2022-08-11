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
 * Domain Model for License Assignment
 * @author victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 */
public class MDDLicenseAssignment extends X_DD_LicenseAssignment {

    /**
     * Get License Assignment by Driver
     * @param driver
     * @return  License Assignment List
     */
    public static List<MDDLicenseAssignment> getByDriver(org.eevolution.distribution.model.MDDDriver driver)
    {
        return new Query(driver.getCtx(), Table_Name , COLUMNNAME_DD_Driver_ID +  "=?", driver.get_TrxName())
                .setClient_ID()
                .setParameters(driver.getDD_Driver_ID())
                .setOrderBy(MDDLicenseAssignment.COLUMNNAME_SeqNo)
                .list();
    }

    /**
     * get License Assignment by Vehicle
     * @param vehicle
     * @return License Assignment List
     */
    public static List<MDDLicenseAssignment> getByVehicle(org.eevolution.distribution.model.MDDVehicle vehicle)
    {
        return new Query(vehicle.getCtx(), Table_Name , COLUMNNAME_DD_Vehicle_ID +  "=?", vehicle.get_TrxName())
                .setClient_ID()
                .setParameters(vehicle.getDD_Vehicle_ID())
                .setOrderBy(MDDLicenseAssignment.COLUMNNAME_SeqNo)
                .list();
    }

    /**
     * Constructor License Assignment
     * @param ctx
     * @param licenseAssignmentId
     * @param trxName
     */
    public MDDLicenseAssignment(Properties ctx, int licenseAssignmentId, String trxName) {
        super(ctx, licenseAssignmentId, trxName);
    }

    /**
     * Constructor License Assignment
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MDDLicenseAssignment(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * License Assignment
     * @param driver
     * @param licenseId
     */
    public MDDLicenseAssignment(MDDDriver driver , int licenseId)
    {
        super(driver.getCtx() , 0 , driver.get_TrxName());
        setDD_License_ID(licenseId);
        setDD_Driver_ID(driver.get_ID());
    }

    /**
     * License Assignment
     * @param vehicle
     * @param licenseId
     */
    public MDDLicenseAssignment(MDDVehicle vehicle , int licenseId)
    {
        super(vehicle.getCtx() , 0 , vehicle.get_TrxName());
        setDD_License_ID(licenseId);
        setDD_Driver_ID(vehicle.get_ID());
    }
}
