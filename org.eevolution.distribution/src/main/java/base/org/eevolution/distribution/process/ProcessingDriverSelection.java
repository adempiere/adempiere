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
import org.eevolution.distribution.model.MDDDriver;
import org.eevolution.distribution.model.MDDLicenseAssignment;

import java.util.List;
import java.util.Optional;

/** Process for (Driver Selection)
 *  This process allows copy a Driver to other Driver
 *  Update the Driver values from Smart Browser
 *  @author victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 *  @version Release 3.9.0
 */
public class ProcessingDriverSelection extends ProcessingDriverSelectionAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		List<MDDDriver> drivers = (List<MDDDriver>) getInstancesForSelection(get_TrxName());
		if (getRecord_ID() > 0 && getSelectionKeys().size() > 0 && getTableSelectionId() == MDDDriver.Table_ID) {
			if (getProcessInfo().getTable_ID() == MDDDriver.Table_ID) {
				MDDDriver driverFrom = drivers.stream().findFirst().get();
				MDDDriver driverTo = (MDDDriver) getInstance(get_TrxName());
				if (driverFrom != null && driverTo != null && driverFrom.get_ID() != driverTo.get_ID())
					CopyDriver(driverFrom, driverTo);
			}
		}
		else if (getRecord_ID() == 0 && getSelectionKeys().size() > 0) {
			UpdatingDriver(drivers);
		}

		return "@Ok@";
	}

	private void UpdatingDriver(List<MDDDriver> drivers) {
		drivers.stream()
				.filter(driver -> driver != null)
				.forEach(driver -> {
					int columns = driver.get_ColumnCount();
					for (int index = 0; index < columns; index++) {
						String columnName = driver.get_ColumnName(index);
						Optional.ofNullable(getSelection(driver.get_ID(), getPrefixAliasForTableSelection() + columnName))
								.ifPresent(value -> driver.set_ValueOfColumn(columnName, value));
					}
					driver.saveEx();
				});
	}

	protected void CopyDriver(MDDDriver driverFrom, MDDDriver driverTo)
	{
		PO.copyValues(driverFrom, driverTo);
		driverFrom.getLicenseAssignments().forEach(licenseAssignment -> {
			MDDLicenseAssignment licenseAssignmentTo = new MDDLicenseAssignment(driverTo, licenseAssignment.getDD_License_ID());
			licenseAssignmentTo.saveEx();
		});

		driverFrom.getRequirementAssignments().forEach(requirementAssignment -> {
			org.eevolution.distribution.model.MDDRequirementAssignment requirementAssignmentTo = new MDDRequirementAssignment(driverTo, requirementAssignment.getDD_Requirement_ID());
			requirementAssignmentTo.saveEx();
		});
		driverTo.saveEx();
	}
}