/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.asset.process;


import org.compiere.asset.model.MAssetAddition;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProject;
import org.compiere.process.DocAction;

 
/**
 *  Open Project.
 *  Opening project will automatically create asset and asset addition
 *  
 *
 *	@author zuhri utama
 */
public class ProjectCreateAsset extends ProjectCreateAssetAbstract
{
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (translated text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		if (getProjectId() == 0) {
			return "Missing Mandatory Field Value (Project)";
		}
		
		MProject project = new MProject (getCtx(), getProjectId(), get_TrxName());
		log.info("doIt - " + project);
		
		// Goodwill
		if (!MProject.PROJECTCATEGORY_AssetProject.equals(project.getProjectCategory()))
			return "Project is not asset type";
		//
		
		MProduct product = new MProduct(getCtx(), getProjectId(), get_TrxName());
		MProductCategory pc = MProductCategory.get(getCtx(), product.getM_Product_Category_ID());
		if (pc.getA_Asset_Group_ID() == 0) {
			return "Product is not asset type";
		}
		
		MAssetAddition assetAdd = MAssetAddition.createAsset(project, product);
		assetAdd.setDateAcct(getDateTrx());
		assetAdd.setDateDoc(getDateTrx());
		assetAdd.setM_Product_ID(getProjectId());
		if(getUseLifeYears() > 0) {
			assetAdd.setDeltaUseLifeYears(getUseLifeYears());
			assetAdd.setDeltaUseLifeYears_F(getUseLifeYears());
		}
		assetAdd.saveEx();
		if (!assetAdd.processIt(DocAction.ACTION_Complete)) {
			return "Error Process Asset Addition";
		}
		assetAdd.saveEx();
		return "Asset Created. @A_Asset_Addition_ID@ - " + assetAdd;
	}	//	doIt

}	//	ProjectClose
