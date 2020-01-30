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

package org.adempiere.pipo;

import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/** Generated Process for (Packout Field Process)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public class SBP_PackoutField extends SBP_PackoutFieldAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception {
		MPackageExp mPackageExp = new MPackageExp(getCtx(), getPackageExpId(), get_TrxName());

		Env.setContext(getCtx(), "#CurrentUserPackout", mPackageExp.get_ID());

		for (Integer selectionKey : getSelectionKeys()) {
			if (!new Query(getCtx(), I_AD_Package_Exp_Detail.Table_Name, I_AD_Package_Exp_Detail.COLUMNNAME_AD_Package_Exp_ID + "=? AND " + I_AD_Package_Exp_Detail.COLUMNNAME_AD_Field_ID + "=? AND " + I_AD_Package_Exp_Detail.COLUMNNAME_Type + "=?", get_TrxName())
					.setParameters(mPackageExp.get_ID(), selectionKey, X_AD_Package_Exp_Detail.TYPE_Field)
					.match()) {
				MField mField = new MField(getCtx(), selectionKey, get_TrxName());
				MPackageExpDetail mPackageExpDetail = new MPackageExpDetail(getCtx(), 0, get_TrxName());
				mPackageExpDetail.setAD_Package_Exp_ID(mPackageExp.get_ID());
				mPackageExpDetail.setAD_Field_ID(selectionKey);
				mPackageExpDetail.setAD_Tab_ID(mField.getAD_Tab_ID());
				mPackageExpDetail.setAD_Window_ID(mField.getAD_Tab().getAD_Window_ID());
				mPackageExpDetail.setType(X_AD_Package_Exp_Detail.TYPE_Field);
				mPackageExpDetail.setDescription(Msg.translate(getCtx(), "Name") + ": " + mField.getName());
				mPackageExpDetail.saveEx();
			}
		}
		return "";

	}
}