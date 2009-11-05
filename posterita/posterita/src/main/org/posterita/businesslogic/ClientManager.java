/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 **/

/**
 @author ashley
 */

package org.posterita.businesslogic;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MCountry;
import org.compiere.model.MElement;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPeriodControl;
import org.compiere.model.MSetup;
import org.compiere.model.PO;
import org.compiere.print.PrintUtil;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.core.businesslogic.ImportManager;
import org.posterita.exceptions.NoClientFoundException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PathInfo;
import org.posterita.util.PoManager;

public class ClientManager 
{
	public static final String ACCOUNTING_FILE_PATH = PathInfo.PROJECT_HOME
			+ "/config/accounting/AccountingUS.csv";
	public static final String ACCOUNTS_IMPORT_FORMAT = "Accounting - Accounts";

	// public static String ACCOUNTS_IMPORT_TABLE = "I_ElementValue";

	private static MClient createClient(Properties nCtx, String clientName,
			String orgName, int currencyId, String currencyName, int countryId,
			String city, File file) throws OperationException 
	{
		Properties ctx = Env.getCtx();

		Env.setContext(ctx, "#AD_User_ID", 100); // set user as SuperUser
		Env.setContext(ctx, UdiConstants.CLIENT_ID_CTX_PARAM, "11"); // Garden
																		// World

		MCountry.get(ctx, countryId);

		MSetup setup = new MSetup(ctx, 0);

		// Step 1
		boolean ok = setup.createClient(clientName, orgName, clientName
				+ " Client User", clientName + " Org User");

		if (ok) 
		{
			// Generate Accounting
			KeyNamePair currency = new KeyNamePair(currencyId, currencyName);

			if (!setup.createAccounting(currency, true, true, false, false,
					false, file))
			{
				throw new OperationException("Could not create accounting for client");
			}

			// Generate Entities
			if (!setup.createEntities(countryId, city, 0, currency.getKey()))
			{
				throw new OperationException(
						"Could not create setup entities");
			}

			// Create Print Documents
			PrintUtil.setupPrintForm(setup.getAD_Client_ID());
		} 
		else
		{
			throw new OperationException("Could not create client");
		}
		int clientId = setup.getAD_Client_ID();

		MClient client = MClient.get(ctx, clientId);

		return client;
	}

	protected static void updateAccountingProcessor(Properties ctx,
			int adClientId, String trxName) throws OperationException 
	{
		String updStmt = "UPDATE C_AcctProcessor set FrequencyType='M' where AD_Client_ID="
				+ adClientId;

		int updates = DB.executeUpdate(updStmt, trxName);

		if (updates == -1)
			throw new OperationException(
					"Accounting Processor could not be updated");
	}

	public static void createClientDependencies(Properties ctx, MClient client,
			MOrg org, String address, int countryId, String city,
			String postalAddress, String smtpHost, File file, String trxName)
			throws OperationException {
		// Set client smtp host for sending emails
		client.setSMTPHost(smtpHost);
		// client.setIsPostImmediate(true);
		// client.setIsCostImmediate(true);
		PoManager.save(client);

		// Create Organisation location
		MLocation location = LocationManager.createLocation(ctx, org.get_ID(), address, "", postalAddress, city, 0, countryId, trxName); 

		// Create Linked Business Partner
		MBPartner bpartner = BPartnerManager.createLinkedBPartner(ctx, org.get_ID(), 0, org
				.getName(), " ", false, false, false, false, address,
				postalAddress, city, " ", countryId, trxName);
		bpartner.setAD_OrgBP_ID(org.get_ID());
		PoManager.save(bpartner);
		MOrgInfo orgInfo = org.getInfo();
		orgInfo.set_TrxName(trxName);
		orgInfo.setC_Location_ID(location.getC_Location_ID());
		PoManager.save(orgInfo);

		openPeriods(ctx, trxName);

		ImportManager.importFile(ctx, file, ACCOUNTS_IMPORT_FORMAT, trxName);
		ImportManager.importAccounting(ctx, Env.getAD_Client_ID(ctx),
				getAccoutingElementId(ctx), false, true, true, trxName);
		updateAccountingProcessor(ctx, Env.getAD_Client_ID(ctx), trxName);
	}

	public static MClient getCreateClient(Properties ctx, String clientName,
			String orgName, int currencyId, String currencyName, int countryId,
			String city, String address, String postalAddress, String smtpHost, File file)
			throws OperationException 
	{
		MClient client;
		Properties rctx = (Properties) ctx.clone();
		
		if (isClientPresent(clientName)) 
		{
			int clientId = getClientId(clientName);
			client = new MClient(ctx, clientId, null);
		} 
		else
		{
			client = createClient(rctx, clientName, orgName, currencyId,
					currencyName, countryId, city, file);
			Env.setContext(rctx, UdiConstants.CLIENT_ID_CTX_PARAM, client.get_ID());
			MOrg org = OrganisationManager.getOrgByName(rctx, orgName, null);
			createClientDependencies(rctx, client, org, address, countryId,
					city, postalAddress, smtpHost, file, null);
		}
		
		return client;
	}

	public static Properties getCtx(Properties ctx, String clientName,
			String orgName) throws OperationException 
	{
		int clientId = ClientManager.getClientId(clientName);

		// Set AD_Client_ID in the context
		Env.setContext(ctx, UdiConstants.CLIENT_ID_CTX_PARAM, clientId);

		MOrg org = OrganisationManager.getOrgByName(ctx, orgName, null);
		int orgId = org.get_ID();
		Env.setContext(ctx, UdiConstants.ORG_ID_CTX_PARAM, orgId);
		Env.setContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM, orgId);

		return ctx;
	}

	public static Properties getCtx(Properties ctx, int clientId, int orgId) {
		Env.setContext(ctx, UdiConstants.CLIENT_ID_CTX_PARAM, clientId);
		Env.setContext(ctx, UdiConstants.ORG_ID_CTX_PARAM, orgId);
		Env.setContext(ctx, UdiConstants.USER_ID_CTX_PARAM, 100);

		return ctx;
	}

	public static void openPeriods(Properties ctx, String trxName)
			throws OperationException 
	{
		int idPCs[] = PO.getAllIDs(MPeriodControl.Table_Name, " AD_CLIENT_ID ="
				+ Env.getAD_Client_ID(ctx), trxName);

		for (int i = 0; i < idPCs.length; i++)
		{
			MPeriodControl period = new MPeriodControl(ctx, idPCs[i], trxName);
			period.setPeriodStatus(MPeriodControl.PERIODSTATUS_Open);
			PoManager.save(period);

		}
	}

	public static MClient loadClient(Properties ctx, int clientId,
			String trxName) throws OperationException
	{
		MClient client = new MClient(ctx, clientId, trxName);
		if (client.get_ID() == 0)
		{
			throw new OperationException("Could not load client with id: "
			
					+ clientId);
		}
		return client;
	}

	public static int getAccoutingElementId(Properties ctx)
			throws OperationException {
		int adClientId = Env.getAD_Client_ID(ctx);

		String whereClause = "AD_Client_ID=" + adClientId;

		int elementIds[] = MElement.getAllIDs(MElement.Table_Name, whereClause,
				null);

		if (elementIds.length == 0)
			throw new OperationException(
					"No accounting element found for client with id: "
							+ adClientId);
		else if (elementIds.length > 1)
			throw new OperationException(elementIds.length
					+ " account elements found for client with id: "
					+ adClientId);
		else
			return elementIds[0];
	}

	public static void changeClientName(Properties ctx, String clientName,
			String trxName) throws OperationException {
		if (clientName == null || clientName.trim().length() == 0)
			throw new OperationException("Client Name cannot be null");

		int adClientId = Env.getAD_Client_ID(ctx);

		MClient client = loadClient(ctx, adClientId, trxName);

		if (!client.getName().equals(clientName)) {
			client.setName(clientName);

			PoManager.save(client);
		}
	}

	public static void cleanData(Properties ctx, boolean deleteAll)
			throws OperationException {
		throw new RuntimeException("This operation is no more supported");
	}

	public static int getClientId(String clientName) throws OperationException {
		String sql = "select ad_client_id from ad_client where upper(name)=upper('"
				+ clientName + "')";

		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		ResultSet rs = null;

		int clientID = 0;
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) {
				clientID = rs.getInt(1);
			}

			rs.close();
		} catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception ex) {
			}

			pstmt = null;
		}

		if (clientID == 0)
			throw new NoClientFoundException("no client found for clientName "
					+ clientName);

		return clientID;
	}

	public static boolean isClientPresent(String clientName)
			throws OperationException {
		try {
			getClientId(clientName);
		} catch (NoClientFoundException e) {
			return false;
		}

		return true;
	}

	/*
	 * Retrieve all clients in the system for which POS/Webstore transactions
	 * are valid
	 */
	public static int[] getAvailableClientIds() {
		int clientIds[] = MClient.getAllIDs(MClient.Table_Name,
				"IsActive='Y' and AD_Client_ID not in (0)", null);

		return clientIds;
	}

}
