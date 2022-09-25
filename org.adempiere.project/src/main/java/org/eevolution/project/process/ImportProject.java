/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.project.process;

import org.adempiere.core.domains.models.I_I_Project;
import org.adempiere.core.domains.models.X_I_Project;
import org.compiere.model.MActivity;
import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCampaign;
import org.compiere.model.MCountry;
import org.compiere.model.MCurrency;
import org.compiere.model.MElementValue;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MProject;
import org.compiere.model.MProjectType;
import org.compiere.model.MRegion;
import org.compiere.model.MSalesRegion;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.MProjectCategory;
import org.eevolution.model.MProjectClass;
import org.eevolution.model.MProjectGroup;
import org.eevolution.model.MProjectStatus;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Import Project
 * https://github.com/adempiere/adempiere/issues/1518
 */
public class ImportProject extends ImportProjectAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {

        if (isDeleteOldImported())
            Arrays.stream(getProjectImportIds(true, true, null)).forEach(recordId -> {
                X_I_Project projectImport = new X_I_Project(getCtx(), recordId, null);
                projectImport.deleteEx(true);
            });

        AtomicInteger importedRecord = new AtomicInteger(0);
        AtomicInteger withErrors = new AtomicInteger(0);
        Arrays.stream(getProjectImportIds(false, false, null)).forEach(recordId -> {
            Trx.run(trxName -> {
                X_I_Project projectImport = new X_I_Project(getCtx(), recordId, trxName);
                fillIdValues(projectImport);
                if (!isValidateOnly() &&
                        ((projectImport.getI_ErrorMsg().isEmpty() || projectImport.getI_ErrorMsg() == null) && isImportOnlyNoErrors())) {
                    if (importRecord(projectImport))
                        importedRecord.updateAndGet(record -> record + 1);
                    else
                        withErrors.updateAndGet(error -> error + 1);
                }
            });
        });
        return "@C_Project_ID@ @Import@ @Records@ " + importedRecord.get() + " @Errors@ " + withErrors.get();
    }

    /**
     * Fill mandatory information
     *
     * @param projectImport
     */
    private void fillIdValues(X_I_Project projectImport) {

        StringBuilder messageError = new StringBuilder();
        projectImport.setI_ErrorMsg("");

        MProject project = null;
        if (projectImport.getC_Project_ID() > 0)
            project = new MProject(projectImport.getCtx() , projectImport.getC_Project_ID() , projectImport.get_TrxName());
        if (project == null && projectImport.getValue() != null) {
            Integer projectId = getId(MProject.Table_Name, MProject.COLUMNNAME_Value+ "=?", projectImport.getValue());
            if (projectId > 0)
                messageError.append("@C_Project_ID@  @AlreadyExists@ ");

        }

        MProjectType projectType = null;
        if (projectImport.getC_ProjectType_ID() > 0)
            projectType = new MProjectType(projectImport.getCtx(), projectImport.getC_ProjectType_ID(), projectImport.get_TrxName());
        if (projectType == null && projectImport.getProjectTypeValue() != null) {
            Integer projectTypeId = getId(MProjectType.Table_Name, MProjectType.COLUMNNAME_Name + "=?", projectImport.getProjectTypeValue());
            if (projectTypeId <= 0)
                messageError.append("@C_ProjectType_ID@  @NotFound@ ");
            else
                projectImport.setC_ProjectType_ID(projectTypeId);
        }

        MProjectCategory projectCategory = null;
        if (projectImport.getC_ProjectCategory_ID() > 0)
            projectCategory = new MProjectCategory(projectImport.getCtx(), projectImport.getC_ProjectCategory_ID(), projectImport.get_TrxName());
        if (projectCategory == null && projectImport.getProjectCategoryValue() != null) {
            Integer projectCategoryId = getId(MProjectCategory.Table_Name, MProjectCategory.COLUMNNAME_Value + "=?", projectImport.getProjectCategoryValue());
            if (projectCategoryId <= 0)
                messageError.append("@C_ProjectCategory_ID@  @NotFound@ ");
            else
                projectImport.setC_ProjectCategory_ID(projectCategoryId);
        }


        MProjectGroup projectGroup = null;
        if (projectImport.getC_ProjectGroup_ID() > 0)
            projectGroup = new MProjectGroup(projectImport.getCtx(), projectImport.getC_ProjectGroup_ID(), projectImport.get_TrxName());
        if (projectGroup == null && projectImport.getProjectGroupValue() != null) {
            Integer projectGroupId = getId(MProjectGroup.Table_Name, MProjectGroup.COLUMNNAME_Value + "=?", projectImport.getProjectGroupValue());
            if (projectGroupId <= 0)
                messageError.append("@C_ProjectGroup_ID@  @NotFound@ ");
            else
                projectImport.setC_ProjectGroup_ID(projectGroupId);
        }

        MProjectClass projectClass = null;
        if (projectImport.getC_ProjectClass_ID() > 0)
            projectClass = new MProjectClass(projectImport.getCtx(), projectImport.getC_ProjectClass_ID(), projectImport.get_TrxName());
        if (projectClass == null && projectImport.getProjectClassValue() != null) {
            Integer projectClassId = getId(MProjectClass.Table_Name, MProjectClass.COLUMNNAME_Value + "=?", projectImport.getProjectStatusValue());
            if (projectClassId <= 0)
                messageError.append("@C_ProjectClass_ID@  @NotFound@ ");
            else
                projectImport.setC_ProjectClass_ID(projectClassId);
        }

        MProjectStatus projectStatus = null;
        if (projectImport.getC_ProjectStatus_ID() > 0)
            projectStatus = new MProjectStatus(projectImport.getCtx(), projectImport.getC_ProjectStatus_ID(), projectImport.get_TrxName());
        if (projectStatus == null && projectImport.getProjectStatusValue() != null) {
            Integer projectStatusId = getId(MProjectStatus.Table_Name, MProjectStatus.COLUMNNAME_Value + "=?", projectImport.getProjectStatusValue());
            if (projectStatusId <= 0)
                messageError.append("@C_ProjectStatud_ID@  @NotFound@ ");
            else
                projectImport.setC_ProjectStatus_ID(projectStatusId);
        }

        MActivity activity = null;
        if (projectImport.getC_Activity_ID() > 0)
            activity = new MActivity(projectImport.getCtx(), projectImport.getC_Activity_ID(), projectImport.get_TrxName());
        if (activity == null && projectImport.getActivityValue() != null) {
            Integer activityId = getId(MActivity.Table_Name, MActivity.COLUMNNAME_Value + "=?", projectImport.getActivityValue());
            if (activityId <= 0)
                messageError.append("@C_Activity_ID@  @NotFound@ ");
            else
                projectImport.setC_Activity_ID(activityId);
        }

        MCampaign campaign = null;
        if (projectImport.getC_Campaign_ID() > 0)
            campaign = new MCampaign(projectImport.getCtx(), projectImport.getC_Campaign_ID(), projectImport.get_TrxName());
        if (campaign == null && projectImport.getCampaignValue() != null) {
            Integer campaingId = getId(MCampaign.Table_Name, MCampaign.COLUMNNAME_Value + "=?", projectImport.getCampaignValue());
            if (campaingId <= 0)
                messageError.append("@C_Campaign_ID@  @NotFound@ ");
            else
                projectImport.setC_Campaign_ID(campaingId);
        }

        MOrg org = null;
        if (projectImport.getAD_Org_ID() > 0)
            org = new MOrg(projectImport.getCtx(), projectImport.getAD_Org_ID(), projectImport.get_TrxName());
        if (org == null && projectImport.getOrgValue() != null) {
            Integer orgId = getId(MOrg.Table_Name, MOrg.COLUMNNAME_Value + "=?", projectImport.getOrgValue());
            if (orgId <= 0)
                messageError.append("@AD_OrgTrx_ID@  @NotFound@ ");
            else
                projectImport.setAD_Org_ID(orgId);
        }

        MOrg orgTrx = null;
        if (projectImport.getAD_OrgTrx_ID() > 0)
            orgTrx = new MOrg(projectImport.getCtx(), projectImport.getAD_OrgTrx_ID(), projectImport.get_TrxName());
        if (orgTrx == null && projectImport.getOrgTrxValue() != null) {
            Integer orgTrxId = getId(MOrg.Table_Name, MOrg.COLUMNNAME_Value + "=?", projectImport.getOrgTrxValue());
            if (orgTrxId <= 0)
                messageError.append("@AD_OrgTrx_ID@  @NotFound@ ");
            else
                projectImport.setAD_OrgTrx_ID(orgTrxId);
        }

        MSalesRegion salesRegion = null;
        if (projectImport.getC_SalesRegion_ID() > 0)
            salesRegion = new MSalesRegion(projectImport.getCtx(), projectImport.getC_SalesRegion_ID(), projectImport.get_TrxName());
        if (salesRegion == null && projectImport.getSalesRegionValue() != null) {
            Integer salesRegionId = getId(MSalesRegion.Table_Name, MSalesRegion.COLUMNNAME_Value + "=?", projectImport.getSalesRegionValue());
            if (salesRegionId <= 0)
                messageError.append("@C_SalesRegion_ID@  @NotFound@ ");
            else
                projectImport.setC_SalesRegion_ID(salesRegionId);
        }

        MElementValue user1 = null;
        if (projectImport.getUser1_ID() > 0)
            user1 = new MElementValue(projectImport.getCtx(), projectImport.getUser1_ID(), projectImport.get_TrxName());
        if (user1 == null && projectImport.getUserValue1() != null) {
            Integer user1Id = getId(MElementValue.Table_Name, MElementValue.COLUMNNAME_Value + "=?", projectImport.getUserValue1());
            if (user1Id <= 0)
                messageError.append("@User1_ID@  @NotFound@ ");
            else
                projectImport.setUser1_ID(user1Id);
        }

        MElementValue user2 = null;
        if (projectImport.getUser2_ID() > 0)
            user2 = new MElementValue(projectImport.getCtx(), projectImport.getUser2_ID(), projectImport.get_TrxName());
        if (user2 == null && projectImport.getUserValue2() != null) {
            Integer user2Id = getId(MElementValue.Table_Name, MElementValue.COLUMNNAME_Value + "=?", projectImport.getUserValue2());
            if (user2Id <= 0)
                messageError.append("@User2_ID@  @NotFound@ ");
            else
                projectImport.setUser2_ID(user2Id);
        }
        MElementValue user3 = null;
        if (projectImport.getUser3_ID() > 0)
            user3 = new MElementValue(projectImport.getCtx(), projectImport.getUser3_ID(), projectImport.get_TrxName());
        if (user3 == null && projectImport.getUserValue3() != null) {
            Integer user3Id = getId(MElementValue.Table_Name, MElementValue.COLUMNNAME_Value + "=?", projectImport.getUserValue3());
            if (user3Id <= 0)
                messageError.append("@User3_ID@  @NotFound@ ");
            else
                projectImport.setUser3_ID(user3Id);
        }

        MElementValue user4 = null;
        if (projectImport.getUser4_ID() > 0)
            user4 = new MElementValue(projectImport.getCtx(), projectImport.getUser4_ID(), projectImport.get_TrxName());
        if (user4 == null && projectImport.getUserValue4() != null) {
            Integer user4Id = getId(MElementValue.Table_Name, MElementValue.COLUMNNAME_Value + "=?", projectImport.getUserValue4());
            if (user4Id <= 0)
                messageError.append("@User4_ID@  @NotFound@ ");
            else
                projectImport.setUser4_ID(user4Id);
        }
        MCurrency currency = null;
        if (projectImport.getC_Currency_ID() > 0)
            currency = new MCurrency(projectImport.getCtx(), projectImport.getC_Currency_ID(), projectImport.get_TrxName());
        if (currency == null && projectImport.getISO_Code() != null) {
            currency = MCurrency.get(projectImport.getCtx(), projectImport.getISO_Code());
            if (currency == null || currency.get_ID() <= 0)
                messageError.append("@C_Currency_ID@  @NotFound@ ");
            else
                projectImport.setC_Currency_ID(currency.get_ID());
        }

        MUser projectManager = null;
        if (projectImport.getProjectManager_ID() > 0) {
            projectManager = new MUser(projectImport.getCtx(), projectImport.getProjectManager_ID(), projectImport.get_TrxName());
            projectManager.setIsProjectMember(true);
            projectManager.setIsProjectMember(true);
            projectManager.saveEx();
        }
        if (projectManager == null && projectImport.getContactName() != null) {
            Integer projectManagerId = getId(MUser.Table_Name, MUser.COLUMNNAME_Name + "=?", projectImport.getContactName());
            if (projectManagerId <= 0) {
                projectManager = createProjectManaget(projectImport);
                if (projectManager == null || projectManager.get_ID() <= 0)
                    messageError.append("@C_ProjectStatud_ID@  @NotFound@ ");
                else
                    projectImport.setProjectManager_ID(projectManager.get_ID());
            } else
                projectImport.setProjectManager_ID(projectManagerId);
        }

        MWarehouse warehouse = null;
        if (projectImport.getM_Warehouse_ID() > 0)
            warehouse = new MWarehouse(projectImport.getCtx(), projectImport.getM_Warehouse_ID(), projectImport.get_TrxName());
        if (warehouse == null && projectImport.getWarehouseValue() != null) {
            Integer warehouseId = getId(MWarehouse.Table_Name, MWarehouse.COLUMNNAME_Value + "=?", projectImport.getWarehouseValue());
            if (warehouseId <= 0)
                messageError.append("@M_Warehouse_ID@  @NotFound@ ");
            else
                projectImport.setM_Warehouse_ID(warehouseId);
        }

        MBPartner partner = null;
        if (projectImport.getC_BPartner_ID() > 0)
            partner = new MBPartner(projectImport.getCtx(), projectImport.getC_BPartner_ID(), projectImport.get_TrxName());
        if (partner == null && projectImport.getBPartnerValue() != null) {
            Integer partnerId = getId(MBPartner.Table_Name, MBPartner.COLUMNNAME_Value + "=?", projectImport.getBPartnerValue());
            partner = MBPartner.get(projectImport.getCtx(), partnerId);
            if (partnerId <= 0) {
                partner = createPartner(projectImport);
                if (partner == null)
                    messageError.append(", ").append("@C_BPartnerSR_ID@ @NotFound@");
                else
                    projectImport.setC_BPartner_ID(partner.getC_BPartner_ID());
            } else {
                projectImport.setC_BPartner_ID(partnerId);
            }

        }


        MBPartnerLocation partnerLocation = null;
        if (projectImport.getC_BPartner_Location_ID() > 0)
            partnerLocation = new MBPartnerLocation(projectImport.getCtx(), projectImport.getC_BPartner_Location_ID(), projectImport.get_TrxName());
        if (partnerLocation == null && projectImport.getAddress1() != null && partner != null && partner.get_ID() > 0) {
            partnerLocation = createPartnerLocation(partner, projectImport);
            if (partnerLocation.get_ID() <= 0)
                messageError.append(", ").append("@C_BPartner_Location_ID@ @NotFound@");
            else
                projectImport.setC_BPartner_Location_ID(partnerLocation.getC_Location_ID());
        } else if (partner != null && partner.get_ID() > 0) {
            Optional<MBPartnerLocation> partnerLocationOptional = Arrays
                    .stream(MBPartnerLocation.getForBPartner(projectImport.getCtx(), partner.getC_BPartner_ID(), projectImport.get_TrxName()))
                    .findFirst();
            partnerLocationOptional.ifPresent(location -> projectImport.setC_BPartner_Location_ID(location.getC_BPartner_Location_ID()));
        }

        MBPartner partnerSR = null;
        if (projectImport.getC_BPartnerSR_ID() > 0)
            partnerSR = new MBPartner(projectImport.getCtx(), projectImport.getC_BPartnerSR_ID(), projectImport.get_TrxName());
        if (partnerSR == null && projectImport.getBPValue() != null) {
            Integer partnerSRId = getId(MBPartner.Table_Name, MBPartner.COLUMNNAME_Value + "=?", projectImport.getBPValue());
            if (partnerSRId <= 0)
                messageError.append(", ").append("@C_BPartner_ID@ @NotFound@");
            else
                projectImport.setC_BPartnerSR_ID(partnerSRId);
        }

        if (messageError.length() > 0)
            setImportError(projectImport, messageError.toString());
        else
            projectImport.saveEx();
    }

    /**
     * Create Partner
     *
     * @param projectImport
     * @return
     */
    private MBPartner createPartner(X_I_Project projectImport) {
        MBPartner partner = new MBPartner(projectImport.getCtx(), 0, projectImport.get_TrxName());
        partner.setValue(projectImport.getBPartnerValue());
        partner.setName(projectImport.getBPName());
        partner.setC_BP_Group_ID(MBPGroup.getDefault(projectImport.getCtx()).get_ID());
        partner.saveEx();
        return partner;
    }

    /**
     * Create Partner Location
     *
     * @param projectImport
     * @return
     */
    private MBPartnerLocation createPartnerLocation(MBPartner partner, X_I_Project projectImport) {
        MBPartnerLocation partnerLocation = new MBPartnerLocation(partner);
        MLocation location = new MLocation(projectImport.getCtx(), 0, projectImport.get_TrxName());
        location.setAddress1(projectImport.getAddress1());
        location.setAddress1(projectImport.getAddress2());
        location.setAddress2(projectImport.getAddress3());
        location.setAddress3(projectImport.getAddress4());
        Optional<MRegion> regionOptional = Optional.ofNullable(
                new Query(projectImport.getCtx(), MRegion.Table_Name, MRegion.COLUMNNAME_Name + "=?", projectImport.get_TrxName())
                        .setClient_ID()
                        .setParameters(projectImport.getRegionName())
                        .first());
        regionOptional.ifPresent(region -> location.setRegion(region));
        location.setCity(projectImport.getCity());
        location.setCountry(MCountry.get(projectImport.getCtx(), projectImport.getCountryCode()));
        location.setPostal(projectImport.getPostal());
        location.saveEx();
        partnerLocation.setC_Location_ID(location.getC_Location_ID());
        partnerLocation.setC_SalesRegion_ID(projectImport.getC_SalesRegion_ID());
        partnerLocation.setIsBillTo(true);
        partnerLocation.setIsRemitTo(true);
        partnerLocation.setIsShipTo(true);
        partnerLocation.saveEx();
        return partnerLocation;
    }

    /**
     * create Project Manager
     *
     * @param projectImport
     * @return
     */
    private MUser createProjectManaget(X_I_Project projectImport) {
        MUser projectManager = new MUser(projectImport.getCtx(), 0, projectImport.get_TrxName());
        projectManager.setName(projectImport.getContactName());
        projectManager.setEMail(projectImport.getEMail());
        projectManager.setPhone(projectImport.getPhone());
        projectManager.setPhone2(projectImport.getPhone2());
        projectManager.setTitle(projectImport.getTitle());
        projectManager.setDescription(projectImport.getContactDescription());
        projectManager.setComments(projectImport.getComments());
        projectManager.setFax(projectImport.getFax());
        projectManager.setIsProjectMember(true);
        projectManager.setIsProjectMember(true);
        projectManager.saveEx();
        return projectManager;

    }

    /**
     * Import Record
     *
     * @param projectImport
     * @return
     */
    private boolean importRecord(X_I_Project projectImport) {
        if (projectImport.getI_ErrorMsg() != null && projectImport.getI_ErrorMsg().length() > 0) {
            projectImport.setProcessed(false);
            projectImport.setI_IsImported(false);
            projectImport.saveEx();
            return false;
        }

        MProject project = new MProject(projectImport);
        project.saveEx();


        Optional<MProjectType> projectTypeOptional = Optional.ofNullable(MProjectType.get(projectImport.getCtx(), projectImport.getC_ProjectType_ID()));
        projectTypeOptional.ifPresent(projecType -> {
            project.setProjectType(projecType);
            project.saveEx();
        });

        projectImport.setC_Project_ID(project.getC_Project_ID());
        projectImport.setI_IsImported(true);
        projectImport.setProcessed(true);
        projectImport.setI_ErrorMsg("");
        projectImport.saveEx(projectImport.get_TrxName());
        return true;
    }

    /**
     * get Id based table name and where clause
     *
     * @param tableName
     * @param whereClause
     * @param parameters
     * @return
     */
    private int getId(String tableName, String whereClause, Object... parameters) {
        return new Query(getCtx(), tableName, whereClause, get_TrxName())
                .setClient_ID()
                .setParameters(parameters)
                .firstId();
    }

    /**
     * set Import Error
     *
     * @param projectImport
     * @param error
     * @return
     */
    private X_I_Project setImportError(X_I_Project projectImport, String error) {
        projectImport.setI_ErrorMsg(Msg.parseTranslation(getCtx(), error));
        projectImport.saveEx();
        addLog(projectImport.getI_Project_ID(), projectImport.getDateDeadline(), projectImport.getProjectBalanceAmt(), projectImport.getI_ErrorMsg());
        return projectImport;
    }

    /**
     * Get payroll Import Movement
     *
     * @param isImported
     * @param isProcessed
     * @return
     */
    private int[] getProjectImportIds(boolean isImported, boolean isProcessed, String trxName) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_I_Project.COLUMNNAME_I_IsImported).append("=? AND ")
                .append(I_I_Project.COLUMNNAME_Processed).append("=?");

        return new Query(getCtx(), I_I_Project.Table_Name, whereClause.toString(), trxName)
                .setOnlyActiveRecords(true)
                .setParameters(isImported, isProcessed)
                .getIDs();
    }
}