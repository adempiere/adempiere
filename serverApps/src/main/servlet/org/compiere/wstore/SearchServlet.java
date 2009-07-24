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
package org.compiere.wstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MOrg;
import org.compiere.model.MWarehouse;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Util;
import org.compiere.util.WebEnv;
import org.compiere.util.WebUtil;

/**
 * 	Location Servlet
 *	
 *  @author Jorg Janke
 *  @version $Id$
 *  @author Michael Judd BF [2728388]  - fix potential CSS velnerability
 */
public class SearchServlet extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3716389906162825762L;
	/**	Logging						*/
    private CLogger			log = CLogger.getCLogger(getClass());

    /**
     * 	Initialize global variables
     *  @param config servlet configuration
     *  @throws javax.servlet.ServletException
     */
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        if (!WebEnv.initWeb(config))
            throw new ServletException("SearchServlet.init");
    }	//	init

    /**
     * Get Servlet information
     * @return Info
     */
    public String getServletInfo()
    {
        return "Adempiere Search Servlet";
    }	//	getServletInfo

    /**
     * Clean up resources
     */
    public void destroy()
    {
        log.info("destroy");
    }   //  destroy

    /**
     *  Process the initial HTTP Get request.
     *  Reads the Parameter Amt and optional C_Invoice_ID
     *
     *  @param request request
     *  @param response response
     *  @throws ServletException
     *  @throws java.io.IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
        doPost(request, response);
    }   //  doGet

    /**
     *  Process the HTTP Post request.
     *
     *  @param request request
     *  @param response response
     *  @throws ServletException
     *  @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
        HttpSession session = request.getSession(true);
        Properties ctx = JSPEnv.getCtx(request);
//        MLocation loc = new MLocation (ctx, 0, null);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/xml; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        int expenseID = WebUtil.getParameterAsInt(request, "W_Expense_ID");        
        int clientID = WebUtil.getParameterAsInt(request, "clientID");
        int campaignID = WebUtil.getParameterAsInt(request, "campaignID");
        int projectID = WebUtil.getParameterAsInt(request, "projectID");
        int phaseID = WebUtil.getParameterAsInt(request, "phaseID");
        int taskID = WebUtil.getParameterAsInt(request, "taskID");
        int orgID = WebUtil.getParameterAsInt(request, "orgID");
        int warehouseID = WebUtil.getParameterAsInt(request, "warehouseID");
        int partnerID = WebUtil.getParameterAsInt(request, "partnerID");
        
        String get = Util.maskHTML(request.getParameter("get"), true);
        if(get == null)
        {
            out.println("<error>Unknown Request: NULL</error>");        	
        }else if(get.equalsIgnoreCase("clientList")){
        	outputClients(ctx, out, clientID, (expenseID == 0));
        }else if(get.equalsIgnoreCase("orgList")){
        	outputOrgs(ctx, out, clientID, orgID, (expenseID == 0));
        }else if(get.equalsIgnoreCase("campaignList")){
        	outputCampaigns(ctx, out, campaignID, (expenseID == 0));
        }else if(get.equalsIgnoreCase("projectList")){
        	outputProjects(ctx, out, projectID, (expenseID == 0));
        }else if(get.equalsIgnoreCase("phaseList")){
        	outputPhases(ctx, out, projectID, phaseID, (expenseID == 0));
        }else if(get.equalsIgnoreCase("taskList")){
        	outputTasks(ctx, out, projectID, phaseID, taskID, (expenseID == 0));
        }else if(get.equalsIgnoreCase("partnerList")){
        	outputPartners(ctx, out, partnerID, (expenseID==0));
        }else if(get.equalsIgnoreCase("warehouseList")){
        	outputWarehouses(ctx, out, orgID, warehouseID, (expenseID==0));
        }else if(get.equalsIgnoreCase("priceList")){
        	outputPriceLists(ctx, out, (expenseID == 0));
        }else{
            out.println("<error>Unknown Request: "+get+"</error>");
        }

        out.flush();
        out.close();
    }   //  doPost

    /**
     * Output XML list of Campaigns
     * 
     * @param ctx context
     * @param out printWriter
     * @param campaignID campaign ID
     * @param includeAny whether 'ANY' should be added to beginning of the list
     */
    private void outputCampaigns(Properties ctx, PrintWriter out, int campaignID, boolean includeAny)
    {
    	out.println("<campaigns>");
    	if(includeAny)
    	{
			out.println("<campaign id='-1'");
			if(campaignID == 0)
				out.println(" selected='selected'");

			out.println(">ANY</campaign>");
    	}
    	out.println("<campaign id='1'>Rose Festival</campaign>");

    	out.println("</campaigns>");
    }


    /**
     * Output XML list of Projects
     * 
     * @param ctx context
     * @param out printWriter
     * @param projectID project ID
     * @param includeAny whether 'ANY' should be added to beginning of the list
     */
    private void outputProjects(Properties ctx, PrintWriter out, int projectID, boolean includeAny)
    {
    	out.println("<projects>");
    	if(includeAny)
    	{
			out.println("<project id='-1'");
			if(projectID == 0)
				out.println(" selected='selected'");

			out.println(">ANY</project>");
    	}
    	out.println("<project id='1'>Landscaping New Office</project>");

    	out.println("</projects>");
    }

    /**
     * Output XML list of Project Phases
     * 
     * @param ctx context
     * @param out printWriter
     * @param projectID project ID
     * @param phaseID project phase ID
     * @param includeAny whether 'ANY' should be added to beginning of the list
     */
    private void outputPhases(Properties ctx, PrintWriter out, int projectID, int phaseID, boolean includeAny)
    {
    	out.println("<phases projectID='"+projectID+"'>");
    	if(includeAny)
    	{
			out.println("<phase id='-1'");
			if(phaseID == 0)
				out.println(" selected='selected'");

			out.println(">ANY</phase>");
    	}
    	
    	if(projectID == 1)
    		out.println("<phase id='1'>Planning</phase>");
    	
    	out.println("</phases>");
    }

    /**
     * Output XML list of Project Phase Tasks
     * 
     * @param ctx context
     * @param out printWriter
     * @param projectID project ID
     * @param phaseID project phase ID
     * @param taskID project phase ID
     * @param includeAny whether 'ANY' should be added to beginning of the list
     */
    private void outputTasks(Properties ctx, PrintWriter out, int projectID, int phaseID, int taskID, boolean includeAny)
    {
    	out.println("<tasks projectID='"+projectID+"' phaseID='"+ phaseID + "'>");
    	if(includeAny)
    	{
			out.println("<task id='-1'");
			if(taskID == 0)
				out.println(" selected='selected'");

			out.println(">ANY</task>");
    	}
    	
    	if((projectID == 1)&&(phaseID == 1))
    		out.println("<task id='1'>Call Owner</task>");
    	
    	out.println("</tasks>");
    }

    /**
     * Output XML list of Business Partners
     * 
     * @param ctx context
     * @param out printWriter
     * @param partnerID business partner ID
     * @param includeAny whether 'ANY' should be added to beginning of the list
     */
    private void outputPartners(Properties ctx, PrintWriter out, int partnerID, boolean includeAny)
    {
    	out.println("<partners>");
    	if(includeAny)
    	{
			out.println("<partner id='-1'");
			if(partnerID == 0)
				out.println(" selected='selected'");

			out.println(">ANY</partner>");
    	}
    	
    	for(MBPartner partner : getAllPartners(ctx))
    	{
			out.print("<partner id='" + partner.get_ID() + "'");
			if(partnerID == partner.get_ID())
				out.print(" selected='selected'");
		
			out.println(">" + Util.maskHTML(partner.getName()) + "</partner>");
    	}
    	
    	out.println("</partners>");
    }

    /**
     * Output XML list of Warehouses
     * 
     * @param ctx context
     * @param out printWriter
     * @param orgID organization ID
     * @param warehouseID warehouse ID
     * @param includeAny whether 'ANY' should be added to beginning of the list
     */
    private void outputWarehouses(Properties ctx, PrintWriter out, int orgID, int warehouseID, boolean includeAny)
    {
    	out.println("<warehouses orgID='"+ orgID + "'>");
    	if(includeAny)
    	{
			out.println("<warehouse id='-1'");
			if(warehouseID == 0)
				out.println(" selected='selected'");

			out.println(">ANY</warehouse>");
    	}
    	
    	for(MWarehouse warehouse : MWarehouse.getForOrg(ctx, orgID))
    	{
    		out.print("<warehouse id='" + warehouse.getM_Warehouse_ID() + "'");
    		if(warehouseID == warehouse.getM_Warehouse_ID())
    			out.print(" selected='selected'");
    		
    		out.println(">" + Util.maskHTML(warehouse.getName()) + "</warehouse>");
    	}
    	
    	out.println("</warehouses>");
    }

    /**
     * Output XML list of PriceLists
     * 
     * @param ctx context
     * @param out printWriter
     * @param includeAny whether 'ANY' should be added to beginning of the list
     */
    private void outputPriceLists(Properties ctx, PrintWriter out, boolean includeAny)
    {
    	out.println("<pricelists>");
    	if(includeAny)
			out.println("<pricelist id='-1' selected='selected'>ANY</pricelist>");
    	
    	out.println("<pricelist id='1'>Standard</pricelist>");
    	out.println("<pricelist id='2'>Summer Specials</pricelist>");
    	out.println("</pricelists>");
    }
    
    /**
     * Output XML list of Clients
     * 
     * @param ctx context
     * @param out printWriter
     * @param clientID client ID
     * @param includeAny whether 'ANY' should be added to beginning of the list
     */
    private void outputClients(Properties ctx, PrintWriter out, int clientID, boolean includeAny)
    {
    	out.println("<clients>");
    	if(includeAny)
    	{
			out.println("<client id='-1'");
			if(clientID == 0)
				out.println(" selected='selected'");

			out.println(">ANY</client>");
    	}
    	
    	for(MClient client : MClient.getAll(ctx))
    	{
    		out.print("<client id='" + client.getAD_Client_ID() + "'");
    		
    		if(clientID == client.getAD_Client_ID())
    			out.print(" selected='selected'");
    		
    		out.println(">" + Util.maskHTML(client.getName()) + "</client>");        		
    	}
    	
    	out.println("</clients>");
    }
    
    /**
     * Output XML list of Organizations
     * 
     * @param ctx context
     * @param out printWriter
     * @param clientID client ID
     * @param orgID organization ID
     * @param includeAny whether 'ANY' should be added to beginning of the list
     */
    private void outputOrgs(Properties ctx, PrintWriter out, int clientID, int orgID, boolean includeAny)
    {
        MClient client = MClient.get(ctx, clientID);
    	
    	out.println("<orgs clientID='"+clientID+"'>");
    	if(includeAny)
    	{
			out.println("<org id='-1'");
			if(clientID == 0)
				out.println(" selected='selected'");

			out.println(">ANY</org>");
    	}
    	
    	if(client != null)
    	{
    		for(MOrg org : MOrg.getOfClient(client))
    		{
    			out.print("<org id='" + org.getAD_Org_ID() + "'");
    			if(orgID == org.getAD_Org_ID())
    				out.print(" selected='selected'");
    		
    			out.println(">" + Util.maskHTML(org.getName()) + "</org>");
    		}
    	}
    	out.println("</orgs>");
    }

    /**
	 * 	Get all Business Partners
	 *  NOTE: I think this logic is elsewhere already, so making it private until the correct call can be made
	 *  
	 *	@param ctx context
	 *	@return partners
	 */
	private MBPartner[] getAllPartners (Properties ctx)
	{
		ArrayList<MBPartner> list = new ArrayList<MBPartner>();
		String sql = "SELECT * FROM C_BPartner";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MBPartner partner = new MBPartner (ctx, rs, null);
				//s_cache.put (new Integer (partner.getAD_Client_ID()), partner);
				list.add (partner);
			}
		}
		catch (Exception e)
		{
			//s_log.log(Level.SEVERE, sql, e);
			log.severe(e.getMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		MBPartner[] retValue = new MBPartner[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	get
}
