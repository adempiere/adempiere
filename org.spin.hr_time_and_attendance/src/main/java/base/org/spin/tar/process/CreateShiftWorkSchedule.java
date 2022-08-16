/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.tar.process;
import org.eevolution.hr.model.MHRWorkShift;
import org.spin.tar.model.MHRShiftSchedule;

/** Generated Process for (Create Shift Work Schedule)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public class CreateShiftWorkSchedule extends CreateShiftWorkScheduleAbstract {
	@Override
	protected String doIt() throws Exception {
		int created = 0;
		for(Integer periodId : getSelectionKeys()) {
			int workShiftId = getSelectionAsInt(periodId, "SS_HR_WorkShift_ID");
			MHRWorkShift workShift = MHRWorkShift.getById(getCtx(), workShiftId, get_TrxName());
			MHRShiftSchedule schedule = new MHRShiftSchedule(getCtx(), 0, get_TrxName());
			schedule.setHR_WorkGroup_ID(getRecord_ID());
			schedule.setHR_Period_ID(periodId);
			schedule.setHR_WorkShift_ID(workShiftId);
			schedule.setDescription(workShift.getName());
			schedule.saveEx();
			created++;
		}
		return "@Created@ " + created;
	}
}