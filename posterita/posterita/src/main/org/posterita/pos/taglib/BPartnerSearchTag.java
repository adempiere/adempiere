/******************************************************************************
 *  Product: Posterita Web-Based POS and Adempiere Plugin                     *
 *  Copyright (C) 2008  Posterita Ltd                                         *
 *  This file is part of POSterita                                            *
 *                                                                            *
 *  POSterita is free software; you can redistribute it and/or modify         *
 *  it under the terms of the GNU General Public License as published by      *
 *  the Free Software Foundation; either version 2 of the License, or         *
 *  (at your option) any later version.                                       *
 *                                                                            *
 *  This program is distributed in the hope that it will be useful,           *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of            *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             *
 *  GNU General Public License for more details.                              *
 *                                                                            *
 *  You should have received a copy of the GNU General Public License along   *
 *  with this program; if not, write to the Free Software Foundation, Inc.,   *
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.               *
 *****************************************************************************/
package org.posterita.pos.taglib;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.script;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.MBPartner;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.businesslogic.ElementManager;
import org.posterita.struts.core.AjaxAction;

/**
 * @author Ashley G Ramdass
 * Apr 22, 2008
 */
public class BPartnerSearchTag extends TagSupport implements AjaxTag
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8706630531517614664L;
	private static final CLogger log = CLogger.getCLogger(BPartnerSearchTag.class);
    private static final String REQUEST_PARAM = "bpValue";
    private String  name = "";
    private String  property = "";
    private boolean disabled = false;
    private boolean isCustomer = false;
    private boolean isVendor = false;
    private boolean isEmployee = false;
    private boolean showNonActive = false;
    private int     size = 20;
    private String  queryId = "";
    private String  resultId = "";
    private String  searchTerm;
    private int     bpartnerId = 0;
    private String  bpartnerValue = "";
    
    
    public int doStartTag() throws JspException
    {
        JspWriter out = pageContext.getOut();
        bpartnerId = 0;
        bpartnerValue = "";
        try
        {
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();

            Object form = request.getAttribute(name);
            
            if (form == null)
            {
                form = request.getSession().getAttribute(name);
            }
            
            String value = (String)BeanUtils.getSimpleProperty(form, property);
            if (value != null && value.trim().length() > 0)
            {
                bpartnerId = Integer.parseInt(value);
            }
            
            if (bpartnerId > 0)
            {
                MBPartner bPartner = new MBPartner(TmkJSPEnv.getCtx((HttpServletRequest)pageContext.getRequest()), bpartnerId, null);
                bpartnerValue = bPartner.getName();
            }
        }
        catch (Exception ex)
        {
            log.log(Level.SEVERE, "Could not get value of BPartner", ex);
            bpartnerId = -1;
        }
        
        queryId = property + "Query";
        resultId = property + "SearchResult";
        
        input bpInput = new input();
        bpInput.setID(property);
        bpInput.setName(property);
        bpInput.setType(input.TYPE_HIDDEN);
        if (bpartnerId > 0)
        {
            bpInput.setValue(bpartnerId);
        }
        
        input queryInput = new input();
        queryInput.setType(input.TYPE_TEXT);
        queryInput.setID(queryId);
        queryInput.setValue(bpartnerValue);
        queryInput.setDisabled(disabled);
        queryInput.setSize(size);
        
        div resultDiv = new div();
        resultDiv.setID(resultId);
        resultDiv.setClass("autocomplete");
        
        div mainDiv = new div();
        mainDiv.addElement(bpInput);
        mainDiv.addElement(queryInput);
        mainDiv.addElement(resultDiv);
        mainDiv.addElement(getAjaxScript());
        
        try
        {
            out.write(mainDiv.toString());
        }
        catch (Exception ex)
        {
            throw new JspException(ex);
        }
        
        return SKIP_BODY;
    }
    
    public int doEndTag() throws JspException
    {
        return EVAL_PAGE;
    }
    
    private script getAjaxScript()
    {
        script ajaxScript = new script();
        
        StringBuffer src = new StringBuffer(400);
        src.append("// auto complete for customer\n");
        src.append("$('").append(queryId).append("').Autocompleter = new Ajax.Autocompleter('")
           .append(queryId).append("','").append(resultId).append("',\n");
        src.append("'").append(AJAX_ACTION).append("',{\n");
        src.append("paramName:'").append(REQUEST_PARAM).append("',\n");
        src.append("parameters: 'action=processRequest&").append(AjaxAction.PROCESS_REQUEST_PARAM).append("=BPartnerSearchTag")
            .append("&isCustomer=").append(isCustomer).append("&isVendor=").append(isVendor).append("&isEmployee=").append(isEmployee).append("',\n");
        src.append("frequency:TROTTLE_TIME,\n"); 
        src.append("onShow:function(element, update){\n"); 
        src.append("    if(!update.style.position || update.style.position=='absolute') {\n");
        src.append("        update.style.position = 'absolute';\n");
        src.append("        Position.clone(element, update, {setHeight: false, offsetTop: element.offsetHeight});\n");        
        src.append("    }\n");
        src.append("    update.style.display = 'block';\n");      
        src.append("},\n");
        src.append("afterUpdateElement:function(e1,e2) {\n");
        src.append("    var id = $('").append(property).append("');\n");
        src.append("    var text = $('").append(queryId).append("');\n");
        src.append("    if(e2.value != '-1')\n");
        src.append("    {\n");
        src.append("        if(e2.value)\n");
        src.append("        {\n");
        //src.append("           alert(e2.value);");
        src.append("          id.value = e2.value;\n");
        src.append("          text.value = e2.getAttribute('name');\n");
        src.append("        }\n");
        src.append("        else\n");
        src.append("        {\n");
        //src.append("           alert('Setting blank');");
        src.append("          id.value = '';\n");                            
        src.append("        }\n");
        src.append("    }\n");
        src.append("    else\n");
        src.append("    {\n");
        //src.append("           alert('set blank');");
        src.append("        id.value = '';\n");    
        src.append("        text.value = '';\n");
        src.append("    }//if\n");
        src.append("}\n");                     
        src.append("});\n");
        
        ajaxScript.setTagText(src.toString());
        return ajaxScript;
    }
    
    
    private ArrayList<I_C_BPartner> getBPartnerList(Properties ctx)
    {
        ArrayList<I_C_BPartner> bpartnerList = new ArrayList<I_C_BPartner>();
        
        StringBuffer sqlStmt = new StringBuffer();
        sqlStmt.append("SELECT * FROM C_BPartner WHERE AD_Client_ID=? AND IsActive='Y' AND IsCustomer=? ");
        sqlStmt.append("AND IsVendor=? AND IsEmployee=? ");
        
        if (searchTerm != null && searchTerm.trim().length() != 0)
        {
            sqlStmt.append("AND UPPER(Name) LIKE '%").append(searchTerm.toUpperCase()).append("%' ");
        }
        
        sqlStmt.append("ORDER BY Name");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
        {
            int param = 1;
            pstmt = DB.prepareStatement(sqlStmt.toString(), null);
            pstmt.setInt(param++, Env.getAD_Client_ID(ctx));
            pstmt.setString(param++, isCustomer ? "Y" : "N");
            pstmt.setString(param++, isVendor ? "Y" : "N");
            pstmt.setString(param++, isEmployee ? "Y" : "N");
            
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                MBPartner bPartner = new MBPartner(ctx, rs, null);
                bpartnerList.add(bPartner);
            }
        }
        catch (Exception ex)
        {
            log.log(Level.SEVERE, "Could not retrieve BPartners", ex);
        }
        finally
        {
            DB.close(rs, pstmt);
        }
        return bpartnerList;
    }
    
    private void writeOutput(Properties ctx, OutputStream outStream, ArrayList<I_C_BPartner> bpartnerList)
    {
        PrintWriter writer = new PrintWriter(outStream);
        
        if (bpartnerList == null || bpartnerList.size() == 0)
        {
            writer.write("<ul>");
            writer.write("<li value='-1'></li>");
            writer.write("</ul>");
            writer.write("<div class='notfound'><label><b>" + ElementManager.getMsg(ctx, "not.found").getName() + " - " + searchTerm + "</b></label></div>");
        }
        else
        {
            writer.write("<ul>");
            for (I_C_BPartner bpartner : bpartnerList)
            {
                writer.write("<li value='"+ bpartner.getC_BPartner_ID() + "' name ='" + bpartner.getName() + "'>");
                writer.write(bpartner.getName());
                writer.write("</li>");
            }
            writer.write("</ul>");
        }
        
        writer.flush();
        writer.close();
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        isCustomer = Boolean.parseBoolean(request.getParameter("isCustomer"));
        isVendor = Boolean.parseBoolean(request.getParameter("isVendor"));
        isEmployee = Boolean.parseBoolean(request.getParameter("isEmployee"));
        searchTerm = request.getParameter(REQUEST_PARAM);
        
        try
        {
            ArrayList<I_C_BPartner> bpartnerList = getBPartnerList(ctx);
            writeOutput(ctx, response.getOutputStream(), bpartnerList);
            response.getOutputStream().flush();
        }
        catch (Exception ex)
        {
            log.log(Level.SEVERE, "Could not write output", ex);
        }
    }
    
    /**
     * @return the isCustomer
     */
    public boolean getIsCustomer()
    {
        return isCustomer;
    }
    
    /**
     * @param isCustomer the isCustomer to set
     */
    public void setIsCustomer(boolean isCustomer) 
    {
        this.isCustomer = isCustomer;
    }
    
    /**
     * @return the isVendor
     */
    public boolean getIsVendor()
    {
        return isVendor;
    }
    
    /**
     * @param isVendor the isVendor to set
     */
    public void setIsVendor(boolean isVendor)
    {
        this.isVendor = isVendor;
    }
    
    /**
     * @return the isEmployee
     */
    public boolean getIsEmployee()
    {
        return isEmployee;
    }
    
    /**
     * @param isEmployee the isEmployee to set
     */
    public void setIsEmployee(boolean isEmployee)
    {
        this.isEmployee = isEmployee;
    }
    
    /**
     * @return the showNonActive
     */
    public boolean getIsShowNonActive()
    {
        return showNonActive;
    }
    
    /**
     * @param showNonActive the showNonActive to set
     */
    public void setIsShowNonActive(boolean showNonActive)
    {
        this.showNonActive = showNonActive;
    }

    /**
     * @return the id
     */
    public String getProperty()
    {
        return property;
    }

    /**
     * @param id the id to set
     */
    public void setProperty(String id)
    {
        this.property = id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the disabled
     */
    public boolean isDisabled()
    {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }

    /**
     * @return the size
     */
    public int getSize()
    {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size)
    {
        this.size = size;
    }
}
