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

package org.eevolution.model;

import org.adempiere.core.domains.models.X_C_ProjectMember;
import org.compiere.model.MProject;
import org.compiere.model.MUser;
import org.compiere.model.Query;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * Domain model for Project Members
 */
public class MProjectMember extends X_C_ProjectMember {

    /**
     * Get Project members
     *
     * @param project
     * @return
     */
    public static List<MProjectMember> getMembers(MProject project) {
        return new Query(project.getCtx(), MProjectMember.Table_Name, COLUMNNAME_C_Project_ID + "=?", project.get_TrxName())
                .setClient_ID()
                .setParameters(project.getC_Project_ID())
                .list();

    }

    /**
     * validate if a member exists into project
     *
     * @param project
     * @param userId
     * @return true if exists
     */
    public static Boolean memberExists(MProject project, Integer userId) {
        return MProjectMember.getMembers(project).stream().anyMatch(projectMember -> projectMember.getAD_User_ID() == userId);
    }


    /**
     * Add Member
     * @param project
     * @param userId
     * @return
     */
    public static MProjectMember addMember(MProject project, Integer userId)
    {
        MUser user = MUser.get(project.getCtx(),userId);
        MProjectMember projectMember = new MProjectMember(project.getCtx(), 0, project.get_TrxName());
        projectMember.setC_Project_ID(project.getC_Project_ID());
        projectMember.setAD_User_ID(user.get_ID());
        projectMember.setNotificationType(user.getNotificationType());
        projectMember.saveEx();
        return  projectMember;
    }

    public MProjectMember(Properties ctx, int C_ProjectMembers_ID, String trxName) {
        super(ctx, C_ProjectMembers_ID, trxName);
    }

    public MProjectMember(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
