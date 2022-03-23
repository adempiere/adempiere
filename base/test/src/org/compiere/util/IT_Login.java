/**
 * Copyright (C) 2003-2022, e-Evolution. , http://www.e-evolution.com
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

package org.compiere.util;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Try;
import org.adempiere.test.CommonGWSetup;
import org.compiere.db.CConnection;
import org.compiere.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration Test for Login
 */
public class IT_Login extends CommonGWSetup {
    @Test
    final void testGetAuthenticatedUserId() {
        Login login = new Login(ctx);
        Integer userId = login.getAuthenticatedUserId("SuperUser", "System");
        assertEquals(100, userId);
    }

    @Test
    final void testGetAuthenticatedUserIdFail() {
        Login login = new Login(ctx);
        Integer userId = login.getAuthenticatedUserId("SuperUser", "SuperUser");
        assertEquals(-1, userId);
    }

    @Test
    final void testInitLogin() {
        // testing refactor method
        // getAuthenticatedUserId(String app_user, String app_pwd)
        // getRoles (String app_user, String app_pwd, boolean force)
        // getClients (KeyNamePair role)
        // getOrgs (KeyNamePair client)
        // getWarehouses (KeyNamePair organization)
        // loadPreferences (KeyNamePair org, KeyNamePair warehouse, java.sql.Timestamp timestamp, String printerName)

        Properties ctxLogin = Login.initTest(true);
        assertTrue(ctxLogin.size() > 0);
        assertEquals(Env.getContext(ctxLogin,"#AD_Role_Name"), "System Administrator");
        assertEquals(Env.getContextAsInt(ctxLogin,"#AD_Role_ID"), 0);
        assertEquals(Env.getContext(ctxLogin,"#AD_Client_Name"), "System");
        assertEquals(Env.getContextAsInt(ctxLogin,"#AD_Client_ID"), 0);
        assertEquals(Env.getContext(ctxLogin,"#AD_Org_Name"), "*");
        assertEquals(Env.getContextAsInt(ctxLogin,"#AD_Org_ID"), 0);
        assertEquals(Env.getContext(ctxLogin,"#AD_User_Name"), "System");
        assertEquals(Env.getContextAsInt(ctxLogin,"#AD_User_ID"), 0);
    }

    @Test
    final void testGetRoles() {
        Login login = new Login(ctx);
        KeyNamePair[]  gardenAdminRolesForce = login.getRoles(CConnection.get(), "GardenAdmin", "GardenAdmin", false);
        assertEquals(gardenAdminRolesForce.length, 2);
        assertEquals(gardenAdminRolesForce[0].getName(), "GardenWorld Admin");
        assertEquals(gardenAdminRolesForce[1].getName(), "GardenWorld User");
    }

    @Test
    final void testGetRolesForce() {
        Login login = new Login(ctx);
        String value = "victor.perez";
        String name = "Victor";
        String password = "adempiere";
        MUser user = new MUser(ctx , 0 , null);
        user.setValue(value);
        user.setName(name);
        user.setPassword(password);
        user.setIsLoginUser(true);
        user.saveEx();
        List<Object> parameters = List.of(
                user.get_ID(),
                103,
                11,
                0,
                true,
                DB.getCurrentTimeFromDatabase(),
                100,
                DB.getCurrentTimeFromDatabase(),
                100,
                DB.getUUID(null),
                false);
        String insertUserRole = "INSERT INTO AD_User_Roles" +
                "(AD_User_ID, AD_Role_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, Uuid, IsDefault)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        DB.executeUpdateEx(insertUserRole , parameters.asJava().toArray() , null);
        int userId = login.getAuthenticatedUserId(value, password);
        assertEquals(user.get_ID(), userId);
        String deleteUserRole = "DELETE FROM AD_User_Roles WHERE AD_User_ID=? AND AD_Role_ID=?";
        DB.executeUpdateEx(deleteUserRole, List.of(user.get_ID(), 103).asJava().toArray(), null);
        KeyNamePair[]  gardenAdminRolesNotForce = login.getRoles(user.getValue(), user.getPassword(), true);
        assertEquals(gardenAdminRolesNotForce[0].getName(), "System Administrator");
    }

    @Test
    final void testGetRolesOrganizations() {
        Login login = new Login(ctx);
        KeyNamePair[] gardenAdminRolesForce = login.getRoles(CConnection.get(), "GardenAdmin", "GardenAdmin", false);
        for (KeyNamePair KeyNamePairRole : gardenAdminRolesForce) {
            assertEquals(KeyNamePairRole.getName(), "GardenWorld Admin");
            for (KeyNamePair KeyNamePairClient : login.getClients(KeyNamePairRole)) {
                assertEquals(KeyNamePairClient.getName(), "GardenWorld");
                for (KeyNamePair KeyNamePairOrganization : login.getOrgs(KeyNamePairClient)) {
                    assertEquals(KeyNamePairOrganization.getName(), "*");
                    for (KeyNamePair KeyNamePairWarehouse : login.getWarehouses(KeyNamePairOrganization)) {
                        assertEquals(KeyNamePairWarehouse.getName(), "*");
                        String preference = login.loadPreferences(KeyNamePairOrganization , KeyNamePairWarehouse, DB.getCurrentTimeFromDatabase() , "Adempiere");
                        assertEquals(preference, "");
                    }
                    break;
                }
                break;
            }
            break;
        }
    }

    @Test
    final void testCheckNotExistIssue() {
        int clientId = Env.getAD_Client_ID(ctx);
        Try<List<MIssue>> tryFoundIssue = Try.of(() -> {
            return List.ofAll(new Query(ctx , MIssue.Table_Name , "StackTrace IS NOT NULL" , null).list());
        });

        List<MIssue> issues = tryFoundIssue.get();
        issues.forEach( issue -> {
            System.out.println("StackTrace : " + issue.getStackTrace() + " ErrorTrace : " + issue.getErrorTrace());
        });
        assertTrue(issues.isEmpty());
    }
}
