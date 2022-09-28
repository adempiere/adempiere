/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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
package org.compiere.pipo;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.adempiere.core.domains.models.X_AD_Package_Exp_Detail;
import org.adempiere.pipo.IDFinder;
import org.adempiere.pipo.PackOut;
import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MPInstance;
import org.compiere.model.MPackageExp;
import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("PIPO")
@Tag("PackIn")
@Tag("PackOut")
class IT_PackOut extends CommonGWSetup {

    @Test
    void testPackOut() {

        int processId = findProcessID();
        int tableId = findTableId();

        MPackageExp packageExp = create2PackPackage();
        
        ProcessInfo processInfo = new ProcessInfo("PackOut", processId,
                tableId, packageExp.get_ID());
        
        MPInstance instance = createProcessInstance(processId, packageExp);
        processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID());

        PackOut packOut = new PackOut();
        packOut.startProcess(getCtx(), processInfo, trx);
        
        instance.load(getTrxName());
        
        assertFalse(instance.isProcessing());
        assertFalse(processInfo.isError(), "PackOut process reported error");

    }

    private MPInstance createProcessInstance(int processId,
            MPackageExp packageExp) {

        MProcess proc = new MProcess(getCtx(), processId, getTrxName());
        MPInstance instance = new MPInstance(proc, packageExp.get_ID());
        instance.saveEx();
        return instance;

    }

    private MPackageExp create2PackPackage() {

        MPackageExp packageExp = createExportPackageHeader();
        createPackDetail(packageExp);
        return packageExp;

    }

    private int findTableId() {

        int tableId = IDFinder.get_IDWithColumn("ad_table", "Name",
                "AD_Package_Exp_ID", AD_CLIENT_ID, getTrxName());
        return tableId;

    }

    private int findProcessID() {

        int processId = IDFinder.get_IDWithColumn("ad_process", "Name",
                "PackOut", AD_CLIENT_ID, getTrxName());
        return processId;

    }

    private MPackageExp createExportPackageHeader() {

        MPackageExp packageExp = new MPackageExp(getCtx(), 0, getTrxName());
        packageExp.setName("testSqlStatement2Pack");
        packageExp.setIsActive(true);
        packageExp.setDescription("Test Output Package");
        packageExp.setEMail("wgheath@gmail.com");
        packageExp.setUserName("wgheath@gmail.com");
        packageExp.setFile_Directory("packages/");
        packageExp.setInstructions("use 2pack to import this package");
        packageExp.setReleaseNo(
                X_AD_Package_Exp_Detail.RELEASENO_NoSpecificRelease);
        packageExp.setVersion("1.0");
        packageExp.setPK_Version("1.0");
        packageExp.save();
        return packageExp;

    }

    private void createPackDetail(MPackageExp packageExp) {

        X_AD_Package_Exp_Detail packDetail =
                new X_AD_Package_Exp_Detail(getCtx(), 0, getTrxName());
        packDetail.setAD_Org_ID(packageExp.getAD_Org_ID());
        packDetail.setAD_Package_Exp_ID(packageExp.get_ID());
        packageExp.setIsActive(true);
        packDetail.setType(X_AD_Package_Exp_Detail.TYPE_SQLStatement);
        packDetail.setDBType("ALL");
        packDetail.setSQLStatement("select * from ad_table");
        packDetail.setDescription("2pack test sql statement");
        packDetail.setLine(10);
        packDetail.save();

    }

}
