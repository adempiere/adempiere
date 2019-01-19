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

package org.eevolution.process;

import org.eevolution.model.MDDDriver;
import org.eevolution.model.MDDRequirement;
import org.eevolution.model.MDDRequirementAssignment;
import org.eevolution.model.MDDVehicle;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Process for (Transport Requirement Selection)
 * This process allows to Create Driver Requirement
 * Create Vehicle Requirement
 * Update Requirement values
 *
 * @author victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * @version Release 3.9.0
 */
public class ProcessingRequirementSelection extends ProcessingRequirementSelectionAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        List<MDDRequirement> requirements = (List<MDDRequirement>) getInstancesForSelection(get_TrxName());
        if (getRecord_ID() > 0 && getSelectionKeys().size() > 0 && getTableSelectionId() == MDDRequirement.Table_ID) {
            if (getProcessInfo().getTable_ID() == MDDDriver.Table_ID) {
                MDDDriver driver = (MDDDriver) getInstance(get_TrxName());
                createDriverRequirement(driver, requirements);
            }
            if (getProcessInfo().getTable_ID() == MDDVehicle.Table_ID) {
                MDDVehicle vehicle = (MDDVehicle) getInstance(get_TrxName());
                createVehicleRequirement(vehicle, requirements);
            }

        } else if (getRecord_ID() == 0 && getSelectionKeys().size() > 0) {
            UpdatingRequirement(requirements);
        }
        return "";
    }

    /**
     * Create Driver Requirement
     *
     * @param driver
     * @param requirements
     */
    private void createDriverRequirement(MDDDriver driver, List<MDDRequirement> requirements) {
        AtomicInteger lineNo = new AtomicInteger(0);
        requirements.stream()
                .filter(requirement -> requirement != null)
                .forEach(requirement -> {
                    MDDRequirementAssignment assignment = new MDDRequirementAssignment(driver, requirement.get_ID());
                    assignment.setSeqNo(lineNo.updateAndGet(no -> no + 10));
                    assignment.saveEx();
                });

    }

    /**
     * Create Vehicle Requirement
     *
     * @param vehicle
     * @param requirements
     */
    private void createVehicleRequirement(MDDVehicle vehicle, List<MDDRequirement> requirements) {
        AtomicInteger lineNo = new AtomicInteger(0);
        requirements.stream()
                .filter(requirement -> requirement != null)
                .forEach(requirement -> {
                    MDDRequirementAssignment assignment = new MDDRequirementAssignment(vehicle, requirement.get_ID());
                    assignment.setSeqNo(lineNo.updateAndGet(no -> no + 10));
                    assignment.saveEx();
                });

    }

    /**
     * Updating Requirement
     *
     * @param requirements
     */
    private void UpdatingRequirement(List<MDDRequirement> requirements) {
        requirements.stream()
                .filter(requirement -> requirement != null)
                .forEach(requirement -> {
                    int columns = requirement.get_ColumnCount();
                    for (int index = 0; index < columns; index++) {
                        String columnName = requirement.get_ColumnName(index);
                        Optional.ofNullable(getSelection(requirement.get_ID(), getPrefixAliasForTableSelection() + columnName))
                                .ifPresent(value -> requirement.set_ValueOfColumn(columnName, value));
                    }
                    requirement.saveEx();
                });

    }
}