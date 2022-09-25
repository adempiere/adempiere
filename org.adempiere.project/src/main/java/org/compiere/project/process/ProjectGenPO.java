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
package org.compiere.project.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.adempiere.core.domains.models.I_C_ProjectPhase;
import org.adempiere.core.domains.models.I_C_ProjectTask;
import org.compiere.model.MBPartner;
import org.compiere.model.MConversionRate;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProductPO;
import org.compiere.model.MProject;
import org.compiere.model.MProjectLine;
import org.compiere.model.MProjectPhase;
import org.compiere.model.MProjectTask;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.Env;

/**
 *  Generate Purchase Order from Project.
 *
 *	@author Jorg Janke
 *	@version $Id: ProjectGenPO.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class ProjectGenPO extends ProjectGenPOAbstract
{
	private ArrayList<MOrder>	orders = new ArrayList<MOrder>();

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info("doIt - C_Project_ID=" + getProjectId() + " - C_ProjectLine_ID=" + getProjectLineId() + " - Consolidate=" + isConsolidateDocument());
		if (getProjectId() != 0)
		{
			MProjectLine projectLine = new MProjectLine(getCtx(), getProjectLineId(), get_TrxName());
			MProject project = new MProject (getCtx(), projectLine.getC_Project_ID(), get_TrxName());
			createPO (project, projectLine);
		}
		else
		{
			MProject project = new MProject (getCtx(), getProjectId(), get_TrxName());
			project.getLines().stream().forEach( projectLine -> createPO (project, projectLine));
		}
		return "";
	}	//	doIt

    /**
     * Create PO from Planned Amt/Qty
     *
     * @param projectLine project line
     */
    private void createPO(MProject project, MProjectLine projectLine) {
        if (projectLine.getM_Product_ID() == 0) {
            addLog(projectLine.getLine(), null, null, "Line has no Product");
            return;
        }
        if (projectLine.getC_OrderPO_ID() != 0) {
            addLog(projectLine.getLine(), null, null, "Line was ordered previously");
            return;
        }

        //	PO Record
        List<MProductPO> productPurchaseList = MProductPO.getByPartner(getCtx(), getVendorId(), projectLine.getM_Product_ID(), get_TrxName());
        if (getVendorId() <= 0 && (productPurchaseList == null || productPurchaseList.size() == 0)) {
            addLog(projectLine.getLine(), null, null, "Product has no PO record");
            return;
        }

        MProductPO productPurchase = productPurchaseList.stream()
                .findFirst()
                .orElseGet(() -> new MProductPO(getCtx(), projectLine.getM_Product_ID(), getVendorId(), project.getC_Currency_ID(), get_TrxName())
                );

        MOrder pruchaseOrder = orders.stream()
                .filter(order -> order.getC_BPartner_ID() == productPurchase.getC_BPartner_ID())
                .findFirst()
                .orElseGet(() -> createOrder(project, projectLine, productPurchase.getC_BPartner_ID()));
        createOrderLine(pruchaseOrder, projectLine, productPurchase);
    }    //	createPOfromProjectLine

    private MOrder createOrder(MProject project, MProjectLine projectLine, Integer partnerId) {
        Optional<I_C_ProjectPhase> optionalProjectPhase = Optional.ofNullable(projectLine.getC_ProjectPhase());
        Optional<I_C_ProjectTask> optionalProjectTask = Optional.ofNullable(projectLine.getC_ProjectTask());

        //	Vendor
        MBPartner vendor = new MBPartner(getCtx(), partnerId, get_TrxName());
        //	New Order
        MOrder order = new MOrder(getCtx(), 0, get_TrxName());
        order.setClientOrg(projectLine.getAD_Client_ID(), projectLine.getAD_Org_ID());
        order.setIsSOTrx(false);
        order.setDescription(project.getName());
        optionalProjectPhase.ifPresent(projectPhase -> {
            setDimension(
                    order,
                    project.getC_Project_ID(),
                    projectPhase.getC_ProjectPhase_ID(),
                    -1,
                    projectPhase.getResponsible_ID(),
                    projectPhase.getC_Activity_ID(),
                    projectPhase.getC_Campaign_ID(),
                    projectPhase.getAD_OrgTrx_ID(),
                    projectPhase.getUser1_ID(),
                    projectPhase.getUser2_ID(),
                    projectPhase.getUser3_ID(),
                    projectPhase.getUser4_ID());
        });

        optionalProjectTask.ifPresent(projectTask -> {
            setDimension(
                    order,
                    project.getC_Project_ID(),
                    projectTask.getC_ProjectPhase_ID(),
                    projectTask.getC_ProjectTask_ID(),
                    projectTask.getResponsible_ID(),
                    projectTask.getC_Activity_ID(),
                    projectTask.getC_Campaign_ID(),
                    projectTask.getAD_OrgTrx_ID(),
                    projectTask.getUser1_ID(),
                    projectTask.getUser2_ID(),
                    projectTask.getUser3_ID(),
                    projectTask.getUser4_ID());
        });
        order.setPriorityRule(projectLine.getPriorityRule());
        order.setDateOrdered(projectLine.getDateOrdered());
        order.setDatePromised(projectLine.getDatePromised());
        //
        order.setBPartner(vendor);
        if (order.getM_PriceList_ID() <= 0) {
            MPriceList priceList = getPriceList(project.getC_Currency_ID());
            order.setM_PriceList_ID(priceList.getM_PriceList_ID());
        }

        order.setAD_User_ID(project.getAD_User_ID());
        order.setM_Warehouse_ID(project.getM_Warehouse_ID());
        order.setC_DocTypeTarget_ID();
        int orgId = projectLine.getAD_Org_ID();
        if (orgId == 0) {
            log.warning("createPOfromProjectLine - AD_Org_ID=0");
            orgId = Env.getAD_Org_ID(getCtx());
            if (orgId != 0)
                projectLine.setAD_Org_ID(orgId);
        }

        order.saveEx();
        //	optionally save for consolidation
        if (isConsolidateDocument())
            orders.add(order);

        return order;
    }

    /**
     * Set Dimension
     *
     * @param instance
     * @param responsibleId
     * @param activityId
     * @param campaignId
     * @param orgTrxId
     * @param user1Id
     * @param user2Id
     * @param user3Id
     * @param user4Id
     */
    private void setDimension(
            PO instance,
            Integer projectId,
            Integer projectPhaseId,
            Integer projectTaskId,
            Integer responsibleId,
            Integer activityId,
            Integer campaignId,
            Integer orgTrxId,
            Integer user1Id,
            Integer user2Id,
            Integer user3Id,
            Integer user4Id) {
        if (instance.get_ColumnIndex(MProject.COLUMNNAME_C_Project_ID) > 0 && projectId > 0)
            instance.set_ValueOfColumn(MProject.COLUMNNAME_C_Project_ID, projectId);
        if (instance.get_ColumnIndex(MProjectPhase.COLUMNNAME_C_ProjectPhase_ID) > 0 && projectPhaseId > 0)
            instance.set_ValueOfColumn(MProjectPhase.COLUMNNAME_C_ProjectPhase_ID, projectPhaseId);
        if (instance.get_ColumnIndex(MProjectTask.COLUMNNAME_C_ProjectTask_ID) > 0 && projectTaskId > 0)
            instance.set_ValueOfColumn(MProjectTask.COLUMNNAME_C_ProjectTask_ID, projectTaskId);
        if (instance.get_ColumnIndex(MProject.COLUMNNAME_SalesRep_ID) > 0 && responsibleId > 0)
            instance.set_ValueOfColumn(MProject.COLUMNNAME_SalesRep_ID, responsibleId);
        if (instance.get_ColumnIndex(MProject.COLUMNNAME_C_Activity_ID) > 0 && activityId > 0)
            instance.set_ValueOfColumn(MProject.COLUMNNAME_C_Activity_ID, activityId);
        if (instance.get_ColumnIndex(MProject.COLUMNNAME_C_Campaign_ID) > 0 && campaignId > 0)
            instance.set_ValueOfColumn(MProject.COLUMNNAME_C_Campaign_ID, campaignId);
        if (instance.get_ColumnIndex(MProject.COLUMNNAME_AD_OrgTrx_ID) > 0 && orgTrxId > 0)
            instance.set_ValueOfColumn(MProject.COLUMNNAME_AD_OrgTrx_ID, orgTrxId);
        if (instance.get_ColumnIndex(MProject.COLUMNNAME_User1_ID) > 0 && user1Id > 0)
            instance.set_ValueOfColumn(MProject.COLUMNNAME_User1_ID, user1Id);
        if (instance.get_ColumnIndex(MProject.COLUMNNAME_User2_ID) > 0 && user2Id > 0)
            instance.set_ValueOfColumn(MProject.COLUMNNAME_User2_ID, user2Id);
        if (instance.get_ColumnIndex(MProject.COLUMNNAME_User3_ID) > 0 && user3Id > 0)
            instance.set_ValueOfColumn(MProject.COLUMNNAME_User3_ID, user3Id);
        if (instance.get_ColumnIndex(MProject.COLUMNNAME_User4_ID) > 0 && user4Id > 0)
            instance.set_ValueOfColumn(MProject.COLUMNNAME_User4_ID, user4Id);
    }

    private MOrderLine createOrderLine(
            MOrder order,
            MProjectLine projectLine,
            MProductPO productPurchase) {
        MOrderLine orderLine = new MOrderLine(order);
        orderLine.setM_Product_ID(projectLine.getM_Product_ID(), true);
        orderLine.setQty(projectLine.getPlannedQty());
        orderLine.setDescription(projectLine.getDescription());

        //	(Vendor) PriceList Price
        orderLine.setPrice();
        if (orderLine.getPriceActual().signum() == 0) {
            BigDecimal purchasePrice = productPurchase.getPricePO();
            int currencyId = productPurchase.getC_Currency_ID();
            //	Try to find purchase price
            if (purchasePrice == null || purchasePrice.signum() == 0)
                purchasePrice = productPurchase.getPriceLastPO();
            if (purchasePrice == null || purchasePrice.signum() == 0)
                purchasePrice = productPurchase.getPriceList();
            //	We have a price
            if (purchasePrice != null && purchasePrice.signum() != 0) {
                if (order.getC_Currency_ID() != currencyId)
                    purchasePrice = MConversionRate.convert(getCtx(), purchasePrice,
                            currencyId, order.getC_Currency_ID(),
                            order.getDateAcct(), order.getC_ConversionType_ID(),
                            order.getAD_Client_ID(), order.getAD_Org_ID());
                orderLine.setPrice(purchasePrice);
            }
        }
        orderLine.setTax();
        setDimension(
                orderLine,
                projectLine.getC_Project_ID(),
                projectLine.getC_ProjectPhase_ID(),
                projectLine.getC_ProjectTask_ID(),
                order.getSalesRep_ID(),
                order.getC_Activity_ID(),
                order.getC_Campaign_ID(),
                order.getAD_OrgTrx_ID(),
                order.getUser1_ID(),
                order.getUser2_ID(),
                order.getUser3_ID(),
                order.getUser4_ID());
        orderLine.saveEx();
        //	update ProjectLine
        projectLine.setC_OrderPO_ID(order.getC_Order_ID());
        projectLine.saveEx();
        addLog(projectLine.getLine(), null, projectLine.getPlannedQty(), order.getDocumentNo());
        return orderLine;
    }

    private MPriceList getPriceList(Integer currencyId) {
        StringBuilder whereClause = new StringBuilder();
        whereClause
                .append(MPriceList.COLUMNNAME_C_Currency_ID).append("=? AND ")
                .append(MPriceList.COLUMNNAME_IsSOPriceList).append("=?");
        MPriceList priceList = new Query(getCtx(), MPriceList.Table_Name, whereClause.toString(), get_TrxName())
                .setClient_ID()
                .setParameters(currencyId, "N")
                .setOrderBy(MPriceList.COLUMNNAME_IsDefault)
                .first();

        return priceList;

    }
}	//	ProjectGenPO
