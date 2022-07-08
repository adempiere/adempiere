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
package org.compiere.FA.process;


import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MProductCategory;
import org.compiere.model.MProject;
import org.compiere.FA.model.MAssetAddition;
import org.compiere.model.MProduct;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

 
/**
 *  Open Project.
 *  Opening project will automatically create asset and asset addition
 *  
 *
 *	@author zuhri utama
 */
public class ProjectCreateAsset extends SvrProcess
{
	/**	Project 			*/
	private int 		m_C_Project_ID = 0;
	
	/**	Product 			*/
	private int 		m_Product_ID = 0;
	
	/**	Use Life Years 			*/
	private int 		m_UseLifeYears = 0;
	
	
	/** DateTrx for create asset	*/
	private Timestamp	m_DateTrx = null;
	
	private String message = "";
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (para[i].getParameterName().equalsIgnoreCase("C_Project_ID")) {
				m_C_Project_ID = para[i].getParameterAsInt();
			}
			else if (para[i].getParameterName().equalsIgnoreCase("M_Product_ID")) {
				m_Product_ID = para[i].getParameterAsInt();
			}
			else if (para[i].getParameterName().equalsIgnoreCase("UseLifeYears")) {
					m_UseLifeYears = para[i].getParameterAsInt();
			}
			else if (para[i].getParameterName().equalsIgnoreCase("DateTrx")) {
				m_DateTrx = (Timestamp)para[i].getParameter();
			}
			else {
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
			}
		}
		
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (translated text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		if (m_C_Project_ID == 0) {
			return "Missing Mandatory Field Value (Project)";
		}
		
		MProject project = new MProject (getCtx(), m_C_Project_ID, get_TrxName());
		log.info("doIt - " + project);
		
		// Goodwill
		if (!MProject.PROJECTCATEGORY_AssetProject.equals(project.getProjectCategory()))
			return "Project is not asset type";
		//
		
		MProduct product = new MProduct(getCtx(), m_Product_ID, get_TrxName());
		MProductCategory pc = MProductCategory.get(getCtx(), product.getM_Product_Category_ID());
		if (pc.getA_Asset_Group_ID() == 0) {
			return "Product is not asset type";
		}
		
		MAssetAddition assetAdd = MAssetAddition.createAsset(project, product);
		assetAdd.setDateAcct(m_DateTrx);
		assetAdd.setDateDoc(m_DateTrx);
		assetAdd.setM_Product_ID(m_Product_ID);
		if(m_UseLifeYears > 0) {
			assetAdd.setDeltaUseLifeYears(m_UseLifeYears);
			assetAdd.setDeltaUseLifeYears_F(m_UseLifeYears);
		}
		assetAdd.saveEx();
		if (!assetAdd.processIt(DocAction.ACTION_Complete)) {
			return "Error Process Asset Addition";
		}
		assetAdd.saveEx();
		
		message += ". @A_Asset_Addition_ID@ - " + assetAdd;
		
		return "Asset Created " + message;
	}	//	doIt

}	//	ProjectClose
