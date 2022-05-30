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

package org.eevolution.project.process;

import org.compiere.model.I_C_Project;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.eevolution.model.MProjectMember;

import java.util.List;
import java.util.Optional;


/**
 * Process for (User Selection)
 * This process allows update user data and create project members
 * Update the User data from Smart Browser
 *
 * @author victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 */
public class ProcessingUserSelection extends ProcessingUserSelectionAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        List<MUser> users = (List<MUser>) getInstancesForSelection(get_TrxName());
        if (getRecord_ID() > 0 && getSelectionKeys().size() > 0 && getProcessInfo().getTable_ID() == I_C_Project.Table_ID) {
                createProjectMembers(users);
        } else if (getRecord_ID() == 0 && getSelectionKeys().size() > 0) {
            updatingUserProperties(users);
        }
        return "";
    }

    /**
     * Updating Users properties
     *
     * @param users
     */
    private void updatingUserProperties(List<MUser> users) {
        users.stream().filter(user -> user != null)
                .forEach(user -> {
                    int columns = user.get_ColumnCount();
                    for (int index = 0; index < columns; index++) {
                        String columnName = user.get_ColumnName(index);
                        Optional.ofNullable(getSelection(user.get_ID(), getPrefixAliasForTableSelection() + columnName))
                                .ifPresent(value -> user.set_ValueOfColumn(columnName, value));
                    }
                    if (user.is_Changed())
                        user.saveEx();
                });
    }


    /**
     * create Project Mebers
     *
     * @param users
     */
    private void createProjectMembers(List<MUser> users) {
        users.stream().filter(user -> user != null)
                .forEach(user -> {
                    List<MProjectMember> projectMembers = new Query(getCtx(), MProjectMember.Table_Name, MProjectMember.COLUMNNAME_C_Project_ID + "=?", get_TrxName())
                            .setClient_ID()
                            .setParameters(getRecord_ID())
                            .list();
                    if (!projectMembers.stream().anyMatch(projectMember -> projectMember.getAD_User_ID() == user.get_ID())) {
                        MProjectMember projectMember = new MProjectMember(getCtx(), 0, get_TrxName());
                        projectMember.setC_Project_ID(getRecord_ID());
                        projectMember.setIsActive(true);
                        projectMember.setAD_User_ID(user.get_ID());
                        projectMember.setNotificationType(user.getNotificationType());
                        projectMember.saveEx();
                    }
                });
    }
}