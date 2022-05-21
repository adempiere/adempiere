/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
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

package org.eevolution.crm.process;

import org.compiere.crm.model.MOpportunity;
import org.compiere.model.MProject;
import org.compiere.util.Msg;

/**
 * Generated Process for (Create Project)
 *
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 * @version Release 3.9.0
 */
public class CreateProjectFromOpportunity extends CreateProjectFromOpportunityAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        MOpportunity opportunity = new MOpportunity(getCtx(), getRecord_ID(), get_TrxName());
        if (opportunity.getC_Project_ID() > 0)
            return "";

        StringBuilder projectName = new StringBuilder();
        StringBuilder projectDescription =  new StringBuilder();
        projectName.append(opportunity.getDocumentNo());
        projectName.append(" ");
        MProject project = new MProject(getCtx(), 0, get_TrxName());
        if (opportunity.getC_Campaign_ID() > 0) {
            projectName.append(" ");
            projectName.append(opportunity.getC_Campaign().getName());
        }
        projectDescription.append(project.getDescription()).append(" *" )
                .append(Msg.parseTranslation(getCtx() , "@CreateFrom@ @C_Opportunity_ID@ "))
                .append(opportunity.getDocumentNo());
        project.setName(projectName.toString());
        project.setDescription(projectDescription.toString());
        project.setC_BPartner_ID(opportunity.getC_BPartner_ID());
        project.setAD_User_ID(opportunity.getAD_User_ID());
        project.setNote(opportunity.getComments());
        project.setC_ProjectType_ID(getProjectTypeId());
        project.setC_ProjectCategory_ID(getProjectCategoryId());
        project.setC_ProjectGroup_ID(getProjectGroupId());
        project.setC_ProjectClass_ID(getProjectClassId());
        project.setSalesRep_ID(opportunity.getSalesRep_ID());
        project.setDateContract(getDateContract());
        project.setC_Currency_ID(opportunity.getC_Currency_ID());
        project.setCommittedAmt(opportunity.getOpportunityAmt());
        project.setDateFinish(getDateFinish());
        project.saveEx();

        opportunity.setC_Project_ID(project.getC_Project_ID());
        opportunity.saveEx();

        return "@C_Project_ID@ " + project.getName();
    }
}