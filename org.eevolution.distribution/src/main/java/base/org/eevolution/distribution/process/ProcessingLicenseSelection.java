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

import org.eevolution.distribution.model.MDDDriver;
import org.eevolution.distribution.model.MDDLicense;
import org.eevolution.distribution.model.MDDLicenseAssignment;
import org.eevolution.distribution.model.MDDVehicle;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Process for (Transport License Selection)
 * This process allows create Driver License
 * Update the License  values from Smart Browser
 * @author victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * @version Release 3.9.0
 */
public class ProcessingLicenseSelection extends ProcessingLicenseSelectionAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        List<MDDLicense> licenses = (List<MDDLicense>) getInstancesForSelection(get_TrxName());
        if (getRecord_ID() > 0 && getSelectionKeys().size() > 0 && getTableSelectionId() == MDDLicense.Table_ID) {

            if (getProcessInfo().getTable_ID() == MDDDriver.Table_ID) {
                MDDDriver driver = (MDDDriver) getInstance(get_TrxName());
                createDriverLicense(driver, licenses);
            }
            if (getProcessInfo().getTable_ID() == MDDVehicle.Table_ID) {
                MDDVehicle vehicle = (MDDVehicle) getInstance(get_TrxName());
                createVehicleLicense(vehicle, licenses);
            }

        } else if (getRecord_ID() == 0 && getSelectionKeys().size() > 0) {
            UpdatingLicense(licenses);
        }
        return "";
    }

    /**
     * Create Driver License
     * @param driver
     * @param licenses
     */
    private void createDriverLicense(MDDDriver driver, List<MDDLicense> licenses) {
        AtomicInteger lineNo = new AtomicInteger(0);
        licenses.stream()
                .filter(license -> license != null)
                .forEach(license -> {
                    MDDLicenseAssignment assignment = new MDDLicenseAssignment(driver, license.get_ID());
                    assignment.setSeqNo(lineNo.updateAndGet(no -> no + 10));
                    assignment.saveEx();
                });

    }

    /**
     * Create Vehicle License
     * @param vehicle
     * @param licenses
     */
    private void createVehicleLicense(MDDVehicle vehicle, List<MDDLicense> licenses) {
        AtomicInteger lineNo = new AtomicInteger(0);
        licenses.stream()
                .filter(license -> license != null)
                .forEach(license -> {
                    MDDLicenseAssignment assignment = new MDDLicenseAssignment(vehicle, license.get_ID());
                    assignment.setSeqNo(lineNo.updateAndGet(no -> no + 10));
                    assignment.saveEx();
                });

    }

    /**
     * Updating License
     * @param licenses
     */
    private void UpdatingLicense(List<MDDLicense> licenses) {
        licenses.stream()
                .filter(license -> license != null)
                .forEach(license -> {
                    int columns = license.get_ColumnCount();
                    for (int index = 0; index < columns; index++) {
                        String columnName = license.get_ColumnName(index);
                        Optional.ofNullable(getSelection(license.get_ID(), getPrefixAliasForTableSelection() + columnName))
                                .ifPresent(value -> license.set_ValueOfColumn(columnName, value));
                    }
                    license.saveEx();
                });

    }
}