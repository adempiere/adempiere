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
package org.compiere.util;

import java.io.File;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.model.MCurrency;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MPaySelectionLine;

/**
 * 	Generic Payment Export
 *  Sample implementation of Payment Export Interface - brought here from MPaySelectionCheck
 *	
 * 	@author 	Jorg Janke
 * 
 *  Contributors:
 *    Carlos Ruiz - GlobalQSS - FR 3132033 - Make payment export class configurable per bank
 */
public class GenericPaymentExport implements PaymentExport
{
	/** Logger								*/
	static private CLogger	s_log = CLogger.getCLogger (GenericPaymentExport.class);


	/** BPartner Info Index for Value       */
	private static final int     BP_VALUE = 0;
	/** BPartner Info Index for Name        */
	private static final int     BP_NAME = 1;
	/** BPartner Info Index for Contact Name    */
	private static final int     BP_CONTACT = 2;
	/** BPartner Info Index for Address 1   */
	private static final int     BP_ADDR1 = 3;
	/** BPartner Info Index for Address 2   */
	private static final int     BP_ADDR2 = 4;
	/** BPartner Info Index for City        */
	private static final int     BP_CITY = 5;
	/** BPartner Info Index for Region      */
	private static final int     BP_REGION = 6;
	/** BPartner Info Index for Postal Code */
	private static final int     BP_POSTAL = 7;
	/** BPartner Info Index for Country     */
	private static final int     BP_COUNTRY = 8;
	/** BPartner Info Index for Reference No    */
	private static final int     BP_REFNO = 9;

	
	/**************************************************************************
	 *  Export to File
	 *  @param checks array of checks
	 *  @param file file to export checks
	 *  @return number of lines
	 */
	public int exportToFile (MPaySelectionCheck[] checks, File file, StringBuffer err)
	{
		if (checks == null || checks.length == 0)
			return 0;
		//  Must be a file
		if (file.isDirectory())
		{
			err.append("No se puede escribir, el archivo seleccionado es un directorio - " + file.getAbsolutePath());
			s_log.log(Level.SEVERE, err.toString());
			return -1;
		}
		//  delete if exists
		try
		{
			if (file.exists())
				file.delete();
		}
		catch (Exception e)
		{
			s_log.log(Level.WARNING, "Could not delete - " + file.getAbsolutePath(), e);
		}

		char x = '"';      //  ease
		int noLines = 0;
		StringBuffer line = null;
		try
		{
			FileWriter fw = new FileWriter(file);

			//  write header
			line = new StringBuffer();
			line.append(x).append("Value").append(x).append(",")
				.append(x).append("Name").append(x).append(",")
				.append(x).append("Contact").append(x).append(",")
				.append(x).append("Addr1").append(x).append(",")
				.append(x).append("Addr2").append(x).append(",")
				.append(x).append("City").append(x).append(",")
				.append(x).append("State").append(x).append(",")
				.append(x).append("ZIP").append(x).append(",")
				.append(x).append("Country").append(x).append(",")
				.append(x).append("ReferenceNo").append(x).append(",")
				.append(x).append("DocumentNo").append(x).append(",")
				.append(x).append("PayDate").append(x).append(",")
				.append(x).append("Currency").append(x).append(",")
				.append(x).append("PayAmount").append(x).append(",")
				.append(x).append("Comment").append(x)
				.append(Env.NL);
			fw.write(line.toString());
			noLines++;

			//  write lines
			for (int i = 0; i < checks.length; i++)
			{
				MPaySelectionCheck mpp = checks[i];
				if (mpp == null)
					continue;
				//  BPartner Info
				String bp[] = getBPartnerInfo(mpp.getC_BPartner_ID());

				//  Comment - list of invoice document no
				StringBuffer comment = new StringBuffer();
				MPaySelectionLine[] psls = mpp.getPaySelectionLines(false);
				for (int l = 0; l < psls.length; l++)
				{
					if (l > 0)
						comment.append(", ");
					comment.append(psls[l].getInvoice().getDocumentNo());
				}
				line = new StringBuffer();
				line.append(x).append(bp[BP_VALUE]).append(x).append(",")   // Value
					.append(x).append(bp[BP_NAME]).append(x).append(",")    // Name
					.append(x).append(bp[BP_CONTACT]).append(x).append(",") // Contact
					.append(x).append(bp[BP_ADDR1]).append(x).append(",")   // Addr1
					.append(x).append(bp[BP_ADDR2]).append(x).append(",")   // Addr2
					.append(x).append(bp[BP_CITY]).append(x).append(",")    // City
					.append(x).append(bp[BP_REGION]).append(x).append(",")  // State
					.append(x).append(bp[BP_POSTAL]).append(x).append(",")  // ZIP
					.append(x).append(bp[BP_COUNTRY]).append(x).append(",") // Country
					.append(x).append(bp[BP_REFNO]).append(x).append(",")   // ReferenceNo
					//  Payment Info
					.append(x).append(mpp.getDocumentNo()).append(x).append(",")    // DocumentNo
					.append(mpp.getParent().getPayDate()).append(",")               // PayDate
					.append(x).append(MCurrency.getISO_Code(Env.getCtx(), mpp.getParent().getC_Currency_ID())).append(x).append(",")    // Currency
					.append(mpp.getPayAmt()).append(",")                // PayAmount
					.append(x).append(comment.toString()).append(x)     // Comment
					.append(Env.NL);
				fw.write(line.toString());
				noLines++;
			}   //  write line

			fw.flush();
			fw.close();
		}
		catch (Exception e)
		{
			err.append(e.toString());
			s_log.log(Level.SEVERE, "", e);
			return -1;
		}

		return noLines;
	}   //  exportToFile

	/**
	 *  Get Customer/Vendor Info.
	 *  Based on BP_ static variables
	 *  @param C_BPartner_ID BPartner
	 *  @return info array
	 */
	private static String[] getBPartnerInfo (int C_BPartner_ID)
	{
		String[] bp = new String[10];

		String sql = "SELECT bp.Value, bp.Name, c.Name AS Contact, "
			+ "a.Address1, a.Address2, a.City, r.Name AS Region, a.Postal, "
			+ "cc.Name AS Country, bp.ReferenceNo "
			+ "FROM C_BPartner bp, AD_User c, C_BPartner_Location l, C_Location a, C_Region r, C_Country cc "
			+ "WHERE bp.C_BPartner_ID=?"        // #1
			+ " AND bp.C_BPartner_ID=c.C_BPartner_ID(+)"
			+ " AND bp.C_BPartner_ID=l.C_BPartner_ID"
			+ " AND l.C_Location_ID=a.C_Location_ID"
			+ " AND a.C_Region_ID=r.C_Region_ID(+)"
			+ " AND a.C_Country_ID=cc.C_Country_ID "
			+ "ORDER BY l.IsBillTo DESC";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			//
			if (rs.next())
			{
				bp[BP_VALUE] = rs.getString(1);
				if (bp[BP_VALUE] == null)
					bp[BP_VALUE] = "";
				bp[BP_NAME] = rs.getString(2);
				if (bp[BP_NAME] == null)
					bp[BP_NAME] = "";
				bp[BP_CONTACT] = rs.getString(3);
				if (bp[BP_CONTACT] == null)
					bp[BP_CONTACT] = "";
				bp[BP_ADDR1] = rs.getString(4);
				if (bp[BP_ADDR1] == null)
					bp[BP_ADDR1] = "";
				bp[BP_ADDR2] = rs.getString(5);
				if (bp[BP_ADDR2] == null)
					bp[BP_ADDR2] = "";
				bp[BP_CITY] = rs.getString(6);
				if (bp[BP_CITY] == null)
					bp[BP_CITY] = "";
				bp[BP_REGION] = rs.getString(7);
				if (bp[BP_REGION] == null)
					bp[BP_REGION] = "";
				bp[BP_POSTAL] = rs.getString(8);
				if (bp[BP_POSTAL] == null)
					bp[BP_POSTAL] = "";
				bp[BP_COUNTRY] = rs.getString(9);
				if (bp[BP_COUNTRY] == null)
					bp[BP_COUNTRY] = "";
				bp[BP_REFNO] = rs.getString(10);
				if (bp[BP_REFNO] == null)
					bp[BP_REFNO] = "";
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		return bp;
	}   //  getBPartnerInfo

	
}	//	PaymentExport
