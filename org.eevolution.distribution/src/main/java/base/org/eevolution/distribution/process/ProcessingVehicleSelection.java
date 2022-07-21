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

package org.eevolution.distribution.process;

import org.compiere.model.PO;
import org.eevolution.distribution.model.MDDRequirementAssignment;
import org.eevolution.distribution.model.MDDLicenseAssignment;
import org.eevolution.distribution.model.MDDVehicle;

import java.util.List;
import java.util.Optional;

/**
 * Process for (Vehicle Selection)
 * This process allows to Update
 * Copy Vehicle
 *
 * @author victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * @version Release 3.9.0
 */
public class ProcessingVehicleSelection extends ProcessingVehicleSelectionAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        List<MDDVehicle> vehicles = (List<MDDVehicle>) getInstancesForSelection(get_TrxName());
        if (getRecord_ID() > 0 && getSelectionKeys().size() > 0 && getTableSelectionId() == MDDVehicle.Table_ID) {
            if (getProcessInfo().getTable_ID() == MDDVehicle.Table_ID) {
                MDDVehicle vehicleFrom = vehicles.stream().findFirst().get();
                MDDVehicle vehicleTo = (MDDVehicle) getInstance(get_TrxName());
                if (vehicleFrom != null && vehicleTo != null && vehicleFrom.get_ID() != vehicleTo.get_ID())
                    CopyVehicle(vehicleFrom, vehicleTo);
            }
        } else if (getRecord_ID() == 0 && getSelectionKeys().size() > 0) {
            UpdatingVehicle(vehicles);
        }
        return "@Ok@";
    }

    /**
     * Updating Vehicle
     *
     * @param vehicles
     */
    private void UpdatingVehicle(List<MDDVehicle> vehicles) {
        vehicles.stream()
                .filter(vehicle -> vehicle != null)
                .forEach(vehicle -> {
                    int columns = vehicle.get_ColumnCount();
                    for (int index = 0; index < columns; index++) {
                        String columnName = vehicle.get_ColumnName(index);
                        Optional.ofNullable(getSelection(vehicle.get_ID(), getPrefixAliasForTableSelection() + columnName))
                                .ifPresent(value -> vehicle.set_ValueOfColumn(columnName, value));
                    }
                    vehicle.saveEx();
                });
    }

    /**
     * Copy Vehicle
     *
     * @param vehicleFrom
     * @param vehicleTo
     */
    private void CopyVehicle(MDDVehicle vehicleFrom, MDDVehicle vehicleTo) {

        PO.copyValues(vehicleFrom, vehicleTo);
        vehicleFrom.getLicenseAssignments().forEach(licenseAssignment -> {
            MDDLicenseAssignment licenseAssignmentTo = new MDDLicenseAssignment(vehicleFrom, licenseAssignment.getDD_License_ID());
            licenseAssignmentTo.saveEx();
        });

        vehicleFrom.getRequirementAssignments().forEach(requirementAssignment -> {
            org.eevolution.distribution.model.MDDRequirementAssignment requirementAssignmentTo = new MDDRequirementAssignment(vehicleFrom, requirementAssignment.getDD_Requirement_ID());
            requirementAssignmentTo.saveEx();
        });
        vehicleTo.saveEx();
    }
}