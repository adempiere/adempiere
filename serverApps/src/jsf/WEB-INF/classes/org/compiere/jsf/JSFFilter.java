/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.jsf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * JSF Filter
 * @author zbeatty
 *
 */

public class JSFFilter 
    implements Filter 
    {
    
    private FilterConfig filterConfig = null;
    /** Logging                         */
    private static CLogger log = CLogger.getCLogger(JSFFilter.class);
    
    /** GridWindow timestamp            */
    private long gridWindowTimestamp;
    
    /** Environment Variable            */
    private Properties ctx;
    
    /** ID's and Window Nums            */
    private int windowId;
    private int windowNo;
       
    /** Tab count                       */
    private int tabCount;
    
    /** GridWindow, GridTab, GridFields */
    private GridWindow gridWindow = null;
    private ArrayList<GridTab> tabList;
   
    /**
     * Init
     * @param config configuration
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException 
    {
        this.filterConfig = filterConfig; 
        ctx = Env.getCtx();
    }
    
    /**
     * Destroy
     */
    public void destroy() 
    {
        this.filterConfig = null;
    }
    
    /**
     * The filter checks to see if the requested file already exists. If it doesn't exist, then it
     * generates a new file for the page requested.  If it does exist, it compares the timestamp
     * on the file to the timestamp on the GridWindow object to make sure it is not out of date.  If 
     * it is out of date, it generates a new file for the page requested.  If it is not out of date, 
     * displays the existing, up to date file.
     * @param ServletRequest request
     * @param ServletResponse response
     * @param FilterChain chain
     * @throws IOException 
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
        throws IOException, ServletException 
    {
        // cast request into HttpServletRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = null;
        
        // get session from request.  Using boolean true should create a new session if session doesn't exist
        try 
        {
            session = httpServletRequest.getSession(true);
        }
        catch (Exception e)
        {
            log.warning("There was a problem getting the session from the HttpServletRequest");
        }
                
        // get windowNo and windowId out of the request
        try 
        {
            windowNo = (Integer.parseInt(httpServletRequest.getParameter("windowNo")));
            windowId = (Integer.parseInt(httpServletRequest.getParameter("windowId")));
        }
        catch (NumberFormatException nfe)
        {
           log.info("There was an error getting the request parameters: " + nfe);
        }
        
        HashMap<Integer, GridWindow> grids = new HashMap<Integer, GridWindow>(); // holds HashMap(WindowNo, GridWindow)
        StringBuilder generatedJSF = new StringBuilder();
        // get context and path to store file
         String realContextPath = session.getServletContext().getRealPath("/"); // holds servlet context path
        
        try 
        {
            // get "grids" out of the session to see if window exists
            // note: attribute "grids" is expected to be in the session before the filter is hit. 
            if (session.getAttribute("grids") != null)
            {
                grids = (HashMap<Integer, GridWindow>)session.getAttribute("grids");
            }
            
            // see if requested windowNo is in session
            if (grids.isEmpty() || (! grids.containsKey(windowId))) 
            {
                gridWindow = GridWindow.get(ctx, windowNo, windowId);
            }
            else
            {
                gridWindow = grids.get(windowNo);                
            }
            
            String jsfFile = realContextPath + "window/" + formatNameWithFirstLowerCase(gridWindow.getName()) + ".jsp";
            File file = new File(jsfFile);
            
            // check if file exists and up to date
            if (file.exists()) 
            {
                gridWindowTimestamp = getGridWindowTimeStamp();
                
                if (gridWindowTimestamp > file.lastModified()) 
                {
                    generateJSFPage(generatedJSF, realContextPath);
                    saveFile(file, generatedJSF.toString());
                }
                            
            } 
            else 
            {
                generateJSFPage(generatedJSF, realContextPath);
                saveFile(file, generatedJSF.toString());
            }
        } 
        catch (NullPointerException npe) 
        {
            log.info("Null pointer occured getting \"grids\" out of session: " + npe);
        }
        // load session attribute
        grids.put(windowNo, gridWindow);
        session.setAttribute("grids", grids);
        // next filter
        chain.doFilter(request, response);
    }

    // creates the window, tabs, and fields for the requested window 
    private void generateJSFPage(StringBuilder jsfString, String realContextPath) 
    {
        // There is a backing bean called menuBean that we will use for the menus of all windows
        // TODO change titles to title="#{tab_lookup[tabNo].fieldColumnName.tooltip}"
        jsfString.append(createAdempiereHeader()); 
        jsfString.append(createHeader());
        jsfString.append("<f:view>\n<f:verbatim><html></f:verbatim>\n<f:verbatim><head></f:verbatim>\n<f:verbatim><title>" + gridWindow.getName() 
            + "</title></f:verbatim>\n<f:verbatim><script type=\"text/javascript\" src=\"/javaScript/aa.js\"></script></f:verbatim>\n"
            + "<t:stylesheet path=\"/css/iconToolbar.css\" />\n<t:stylesheet path=\"/css/wideTable.css\" />\n<t:stylesheet path=\"/css/tab.css\" />\n"
            + "<t:stylesheet path=\"/css/misc.css\" />\n<f:verbatim></head></f:verbatim>\n<f:verbatim><body class=\"mainDocumentStyle\"></f:verbatim>\n"
            // jsf Messages
            + "<t:messages showDetail=\"true\" />"
            + "<t:jscookMenu layout=\"hbr\" theme=\"ThemeAdempiere\" javascriptLocation=\"/js\" styleLocation=\"/css\" imageLocation=\"/images\">\n" 
            + "<t:navigationMenuItems value=\"#{menuBean.menu}\" />\n</t:jscookMenu>\n<h:form id=\"toolbarForm\">\n<f:verbatim><table><tr><td></f:verbatim>\n"
            + "<t:panelGroup id=\"toolbar\">\n"
            
            // toolbar (Maybe extract to method)
            + "<t:commandButton image=\"/images/Ignore24.gif\" styleClass=\"iconButtonStyle\" title=\"Undo Changes\" "
            + "actionListener=\"#{actionListener_lookup.undo}\" />\n"
            
            + "<f:verbatim> &nbsp;</f:verbatim>\n<t:panelGroup>\n"
            + "<t:commandButton image=\"/images/Help24.gif\" styleClass=\"iconButtonStyle\" title=\"Help\" "
            + "actionListener=\"#{actionListener_lookup.help}\" />\n"
            
            + "<t:commandButton image=\"/images/New24.gif\" styleClass=\"iconButtonStyle\" title=\"New Record\" "
            + "actionListener=\"#{actionListener_lookup.createNew}\" />\n"       
            
            + "<t:commandButton image=\"/images/Delete24.gif\" styleClass=\"iconButtonStyle\" title=\"Delete Record\" "
            + "actionListener=\"#{actionListener_lookup.delete}\" />\n"
            
            + "<t:commandButton image=\"/images/Save24.gif\" styleClass=\"iconButtonStyle\" title=\"Save Changes\" " 
            + "actionListener=\"#{actionListener_lookup.save}\" />\n"   
            
            + "</t:panelGroup>\n<f:verbatim> &nbsp;</f:verbatim>\n<t:panelGroup>\n"
            + "<t:commandButton image=\"/images/Refresh24.gif\" styleClass=\"iconButtonStyle\" title=\"Requery\" " 
            + "actionListener=\"#{actionListener_lookup.refresh}\" />\n"
            
            + "<t:commandButton image=\"/images/Find24.gif\" styleClass=\"iconButtonStyle\" title=\"Look Up Record\" " 
            + "actionListener=\"#{actionListener_lookup.lookup}\" />\n"
                    
            + "<t:commandButton image=\"/images/Attachment24.gif\" styleClass=\"iconButtonStyle\" title=\"Attachment\" "
            + "actionListener=\"#{actionListener_lookup.attachment}\" />\n"
            
            + "<t:commandButton image=\"/images/Multi24.gif\" styleClass=\"iconButtonStyle\" title=\"Grid Toggle\" " 
            + "actionListener=\"#{uiStateBean.toggleGridView}\" rendered=\"#{!(uiStateBean.gridView)}\" /> \n"
            + "<t:commandButton image=\"/images/MultiX24.gif\" styleClass=\"iconButtonStyle\" title=\"Grid Toggle\" "
            + "actionListener=\"#{uiStateBean.toggleGridView}\" rendered=\"#{uiStateBean.gridView}\" />\n"
            
            + "</t:panelGroup>\n<f:verbatim> &nbsp;</f:verbatim>\n<t:panelGroup>\n"
            + "<t:commandButton image=\"/images/History24.gif\" styleClass=\"iconButtonStyle\" title=\"History Records\" "
            + "actionListener=\"#{actionListener_lookup.history}\" />\n"
                   
            + "<t:commandButton image=\"/images/Home24.gif\" styleClass=\"iconButtonStyle\" title=\"Menu\" "
            + "actionListener=\"#{actionListener_lookup.menu}\" />\n"
            
            + "<t:commandButton image=\"/images/Parent24.gif\" styleClass=\"iconButtonStyle\" title=\"Parent Record\" "
            + "actionListener=\"#{actionListener_lookup.parent}\" />\n"
                    
            + "<t:commandButton image=\"/images/Detail24.gif\" styleClass=\"iconButtonStyle\" title=\"Detail Record\" "
            + "actionListener=\"#{actionListener_lookup.detail}\" />\n"
            
            + "</t:panelGroup>\n<f:verbatim> &nbsp;</f:verbatim>\n<t:panelGroup>\n" 
            + "<t:commandButton image=\"/images/First24.gif\" styleClass=\"iconButtonStyle\" title=\"First Record\" "
            + "actionListener=\"#{actionListener_lookup.first}\" />\n"
            
            + "<t:commandButton image=\"/images/Previous24.gif\" styleClass=\"iconButtonStyle\" title=\"Previous Record\" "
            + "actionListener=\"#{actionListener_lookup.previous}\" />\n"
            
            + "<t:commandButton image=\"/images/Next24.gif\" styleClass=\"iconButtonStyle\" title=\"Next Record\" "
            + "actionListener=\"#{actionListener_lookup.next}\" />\n"
                    
            + "<t:commandButton image=\"/images/Last24.gif\" styleClass=\"iconButtonStyle\" title=\"Last Record\" "
            + "actionListener=\"#{actionListener_lookup.last}\" />\n"
            
            + "</t:panelGroup>\n<f:verbatim> &nbsp;</f:verbatim>\n<t:panelGroup>\n"
            + "<t:commandButton image=\"/images/Report24.gif\" styleClass=\"iconButtonStyle\" title=\"Report\" "
            + "actionListener=\"#{actionListener_lookup.report}\" />\n"
            
            + "<t:commandButton image=\"/images/Archive24.gif\" styleClass=\"iconButtonStyle\" title=\"Archived Documents/Reports\" "
            + "actionListener=\"#{actionListener_lookup.archived}\" />\n"
            
            + "<t:commandButton image=\"/images/Print24.gif\" styleClass=\"iconButtonStyle\" title=\"Print\" "
            + "actionListener=\"#{actionListener_lookup.print}\" />\n"
            
            + "</t:panelGroup>\n<f:verbatim> &nbsp;</f:verbatim>\n<t:panelGroup>\n"
            + "<t:commandButton image=\"/images/ZoomAcross24.gif\" styleClass=\"iconButtonStyle\" title=\"Zoom Across (Where Used)\" "
            + "actionListener=\"#{actionListener_lookup.zoom}\" />\n"
            
            + "<t:commandButton image=\"/images/WorkFlow24.gif\" styleClass=\"iconButtonStyle\" title=\"Active Workflows\" "
            + "actionListener=\"#{actionListener_lookup.active}\" />\n"
            
            + "<t:commandButton image=\"/images/Request24.gif\" styleClass=\"iconButtonStyle\" title=\"Check Requests\" "
            + "actionListener=\"#{actionListener_lookup.check}\" />\n"
            
            + "<t:commandButton image=\"/images/Product24.gif\" styleClass=\"iconButtonStyle\" title=\"Product Info\" "
            + "actionListener=\"#{actionListener_lookup.product}\" />\n"
            
            + "</t:panelGroup><f:verbatim> &nbsp;</f:verbatim>\n"
            + "<t:commandButton image=\"/images/End24.gif\" styleClass=\"iconButtonStyle\" title=\"Exit Window\" "
            + "actionListener=\"#{actionListener_lookup.exit}\" />\n"
            
            + "</t:panelGroup>\n"
            + "<f:verbatim></td></tr></table></f:verbatim>\n"
            
            // hidden field to store window number
            + "<t:inputHidden id=\"windowNo\" value=\"#{uiStateBean.windowNo}\" forceId=\"true\" />\n"
            + "<t:saveState value=\"uiStateBean\" />\n"
            
            + "<f:verbatim><table></f:verbatim>\n<f:verbatim><tr></f:verbatim>\n"
            + "<f:verbatim><td valign=\"top\"></f:verbatim>\n<f:verbatim><table></f:verbatim>\n");
        
        // generate tabs   
        jsfString.append(generateTabInfo());
        // end generate tabs       
        jsfString.append("<f:verbatim></table></f:verbatim>\n<f:verbatim></td></f:verbatim>\n<f:verbatim><td></f:verbatim>\n"
            + "<t:panelStack selectedPanel=\"#{uiStateBean.tabState}\">\n");
        // generate jsp pages for tabs.  Each tab has a detail page and a table page
        createDetailPage(realContextPath);
        createTablePage(realContextPath);
        int orderSubCount = 1;  // used to increment orderSub count
        for (int i = 0; i < tabCount; i++) {
            GridTab gridTab = gridWindow.getTab(i);
            String tabName = formatNameWithFirstLowerCase(gridTab.getName());
            jsfString.append("<t:div id=\"tab" + gridTab.getTabNo() + "Detail\">\n<f:subview id=\"ordersub" + (orderSubCount++) 
                    + "\">\n<jsp:include page=\"" + (tabName + "Detail.jsp") + "\"/>\n</f:subview>\n</t:div>\n"
                    + "<t:div id=\"tab" + gridTab.getTabNo() + "Table\">\n<f:subview id=\"ordersub" + (orderSubCount++) 
                    + "\">\n<jsp:include page=\"" + (tabName + "Table.jsp") + "\" />\n</f:subview>\n</t:div>\n");
        }
        jsfString.append("</t:panelStack>\n<f:verbatim></td></f:verbatim>\n<f:verbatim></tr></f:verbatim>\n<f:verbatim></table></f:verbatim>\n" 
                + "</h:form>\n<f:verbatim></body></f:verbatim>\n<f:verbatim></html></f:verbatim>\n</f:view>");
    }    

    // Generate GridTabs for JSF page
    private StringBuilder generateTabInfo() 
    {
        StringBuilder tabString = new StringBuilder();
        tabCount = gridWindow.getTabCount();
        for (int i = 0; i < tabCount; i++) 
        {
            GridTab gridTab = gridWindow.getTab(i);
            if (i == 0) 
            {
                tabString.append("<f:verbatim><tr></f:verbatim>\n<f:verbatim><td><span></f:verbatim>\n<t:commandButton id=\"");
            } 
            else 
            {
                tabString.append("<f:verbatim><tr></f:verbatim>\n<f:verbatim><td><span class=\"childTab\"></f:verbatim>\n<t:commandButton id=\"");
            }
            // GridTab name formatting
            String tabName = formatNameWithFirstLowerCase(gridTab.getName());
            tabString.append(tabName + "TabButton\" value=\"" + gridTab.getName() + "\" styleClass=\"");
            // check parent child relationship 
            // TODO this needs to be fixed for parent child relations
            if (gridTab.getTabLevel() == 0) 
            {                                                                              
                tabString.append("baseParentTabStyle unselectedTabStyle\" rendered=\"#{!(uiStateBean.tabNo eq " + gridTab.getTabNo() + ")}\""
                    + " actionListener=\"#{actionListener_lookup[" + gridTab.getTabNo() + "].focus}\" onmouseover=\"this.className='baseParentTabStyle "
                    + "unselectedMouseOverTabStyle';\" onmouseout=\"this.className='baseParentTabStyle unselectedTabStyle';\" />\n"
                    + "<t:commandButton id=\"" + tabName + "TabButton1\" value=\"" + gridTab.getName() + "\" styleClass=\"baseParentTabStyle "
                    + "selectedTabStyle\" rendered=\"#{uiStateBean.tabNo eq " + gridTab.getTabNo() + "}\" "
                    + "actionListener=\"#{actionListener_lookup[" + gridTab.getTabNo() + "].focus}\" "
                    + "onmouseover=\"this.className='baseParentTabStyle selectedMouseOverTabStyle';\" onmouseout=\"this.className='baseParentTabStyle "
                    + "selectedTabStyle';\" />\n"); 
            } 
            else if(gridTab.getTabLevel() == 1) 
            {
                tabString.append("baseIndentOneTabStyle unselectedTabStyle\" rendered=\"#{!(uiStateBean.tabNo eq " + gridTab.getTabNo() + ")}\"" 
                    + " actionListener=\"#{actionListener_lookup[" + gridTab.getTabNo() + "].focus}\" onmouseover=\"this.className='baseIndentOneTabStyle "
                    + "unselectedMouseOverTabStyle';\" onmouseout=\"this.className='baseIndentOneTabStyle unselectedTabStyle';\" />\n"
                    + "<t:commandButton id=\"" + tabName + "TabButton1\" value=\"" + gridTab.getName() + "\" styleClass=\"baseIndentOneTabStyle "
                    + "selectedTabStyle\" rendered=\"#{uiStateBean.tabNo eq " + gridTab.getTabNo() + "}\" "
                    + "actionListener=\"#{actionListener_lookup[" + gridTab.getTabNo() + "].focus}\" "
                    + "onmouseover=\"this.className='baseIndentOneTabStyle selectedMouseOverTabStyle';\" onmouseout=\"this.className='baseIndentOneTabStyle "
                    + "selectedTabStyle';\" />\n");
            }
            
            else if(gridTab.getTabLevel() == 2)
            {
                tabString.append("baseIndentTwoTabStyle unselectedTabStyle\" rendered=\"#{!(uiStateBean.tabNo eq " + gridTab.getTabNo() + ")}\"" 
                    + " actionListener=\"#{actionListener_lookup[" + gridTab.getTabNo() + "].focus}\" onmouseover=\"this.className='baseIndentTwoTabStyle "
                    + "unselectedMouseOverTabStyle';\" onmouseout=\"this.className='baseIndentTwoTabStyle unselectedTabStyle';\" />\n"
                    + "<t:commandButton id=\"" + tabName + "TabButton1\" value=\"" + gridTab.getName() + "\" styleClass=\"baseIndentTwoTabStyle "
                    + "selectedTabStyle\" rendered=\"#{uiStateBean.tabNo eq " + gridTab.getTabNo() + "}\" "
                    + "actionListener=\"#{actionListener_lookup[" + gridTab.getTabNo() + "].focus}\" "
                    + "onmouseover=\"this.className='baseIndentTwoTabStyle selectedMouseOverTabStyle';\" onmouseout=\"this.className='baseIndentTwoTabStyle "
                    + "selectedTabStyle';\" />\n");
            }
            tabString.append("<f:verbatim></span></td></f:verbatim>\n<f:verbatim></tr></f:verbatim>\n");
        }
        return tabString;
    }
    // Generate Detail jsp page
    private void createDetailPage(String realContextPath) 
    {
        File detailFile = null;
        
        for (int i = 0; i < tabCount; i ++) 
        {
            StringBuilder detailJSP = new StringBuilder();
            detailJSP.append(createAdempiereHeader());
            detailJSP.append(createHeader());
            detailJSP.append("<f:verbatim><table></f:verbatim>\n");
            
            GridTab gridTab = gridWindow.getTab(i);
            String fileName = formatNameWithFirstLowerCase(gridTab.getName()) + "Detail";
            String jsfFile = realContextPath + "window/" + fileName + ".jsp";
            detailFile = new File(jsfFile);
            gridTab.dataRefreshAll();
            
            boolean isSolo = false; // true if not same line as previous
            for (int j = 0; j < gridTab.getFieldCount(); j++) 
            {
                GridField gridField = gridTab.getField(j);
                // check if displayed
                if (gridField.isDisplayed()) 
                {
                    int displayType = gridField.getDisplayType();
                    // check display type to genereate jsf code for each specific component
                    switch(displayType) 
                    {
                        case 10:
                        case 11:
                        case 12: 
                        case 13:
                        case 14:
                        case 15: // this is a Date format.  Comes as Date + Time and needs to be chopped in Backing Bean
                        case 16: // this is a Date + Time format.
                        case 21: // complete address comma seperated ex: street, city, state, zip
                        case 22:
                        case 29:
                        case 33:
                        case 34:
                        case 35: 
                        case 36:
                        case 37:
                            // text box
                            // check if same line.  make sure that it isn't the first field.  It is possible that the 
                            // first field can have same line checked and that would cause formatting issues.
                            if(gridField.isSameLine() && j != 0) 
                            { 
                                isSolo = false;
                            } 
                            else 
                            {
                                if (isSolo) 
                                {
                                   detailJSP.append("<f:verbatim></tr></f:verbatim>\n"); 
                                }
                                detailJSP.append("<f:verbatim><tr></f:verbatim>\n");
                                isSolo = true;
                            }
                            detailJSP.append("<f:verbatim><td class=\"rightLabel\"></f:verbatim>"
                                    + "<t:outputText value = \"#{tab_lookup[" + gridTab.getTabNo() + "]." + gridField.getColumnName() + ".label}\" />\n"
                                    + "<f:verbatim></td></f:verbatim>\n<f:verbatim><td></f:verbatim><t:inputText value=\"#{tab_lookup[" + gridTab.getTabNo()
                                    + "]." + gridField.getColumnName() + ".value}\" />\n<f:verbatim></td></f:verbatim>\n");
                            // check if same line.  if yes and not first field, close table row
                            if (gridField.isSameLine() && j != 0) 
                            {
                              detailJSP.append("<f:verbatim></tr></f:verbatim>\n");  
                            }
                            break;
                        case 17:
                        case 18:
                        case 19:
                            // lookup
                            // check if same line.  make sure that it isn't the first field.  It is possible that the 
                            // first field can have same line checked and that would cause formatting issues.
                            ArrayList al = gridField.getLookup().getData(true, true, true, true);
                            if(gridField.isSameLine() && j != 0) 
                            { 
                                isSolo = false;
                            } 
                            else 
                            {
                                if (isSolo) 
                                {
                                   detailJSP.append("<f:verbatim></tr></f:verbatim>\n"); 
                                }
                                detailJSP.append("<f:verbatim><tr></f:verbatim>\n");
                                isSolo = true;
                            }
                            detailJSP.append("<f:verbatim><td class=\"rightLabel\"></f:verbatim><t:outputText value=\"#{tab_lookup[" + gridTab.getTabNo() + "]." 
                                    + gridField.getColumnName() + ".label}\" /><f:verbatim></td></f:verbatim>\n<f:verbatim><td></f:verbatim>\n"
                                    + "<t:selectOneMenu styleClass=\"fixedWidthDropDown\">\n<f:selectItems value=\"#{tab_lookup[" + gridTab.getTabNo() + "]." 
                                    + gridField.getColumnName() + ".value}\" />\n</t:selectOneMenu>\n<f:verbatim></td></f:verbatim>\n");
                                    
                            // check if same line.  if yes and not first field, close table row
                            if (gridField.isSameLine() && j != 0) 
                            {
                              detailJSP.append("<f:verbatim></tr></f:verbatim>\n");  
                            }
                            break;
                        case 20:
                            // Check box 
                            // check if same line.  make sure that it isn't the first field.  It is possible that the 
                            // first field can have same line checked and that would cause formatting issues.
                            if(gridField.isSameLine() && j != 0) 
                            { 
                                isSolo = false;
                            } 
                            else 
                            {
                                if (isSolo) 
                                {
                                   detailJSP.append("<f:verbatim></tr></f:verbatim>\n"); 
                                }
                                detailJSP.append("<f:verbatim><tr></f:verbatim>\n");
                                isSolo = true;
                            }
                            detailJSP.append("<f:verbatim><td></td><td></f:verbatim>\n"
                                    + "<t:selectBooleanCheckbox value=\"#{tab_lookup[" + gridTab.getTabNo() + "]." + gridField.getColumnName() + ".value}\" "
                                    + "title=\"#{tab_lookup[" + gridTab.getTabNo() + "]." + gridField.getColumnName() + ".label}\" />\n"
                                    + "<t:outputText value=\"#{tab_lookup[" + gridTab.getTabNo() + "]." + gridField.getColumnName() + ".label}\" />\n"
                                    + "<f:verbatim></td></f:verbatim>\n");
                            // check if same line.  if yes and not first field, close table row
                            if (gridField.isSameLine() && j != 0) 
                            {
                              detailJSP.append("<f:verbatim></tr></f:verbatim>\n");  
                            }
                            break;
                        case 23:
                        case 24:
                        case 25:
                        case 27:
                        case 28:
                            // custom component
                            // check if same line.  make sure that it isn't the first field.  It is possible that the 
                            // first field can have same line checked and that would cause formatting issues.
                            if(gridField.isSameLine() && j != 0) 
                            { 
                                isSolo = false;
                            } 
                            else 
                            {
                                if (isSolo) 
                                {
                                   detailJSP.append("<f:verbatim></tr></f:verbatim>\n"); 
                                }
                                detailJSP.append("<f:verbatim><tr></f:verbatim>\n");
                                isSolo = true;
                            }
                            detailJSP.append("<f:verbatim><td></td></f:verbatim><f:verbatim><td class=\"leftLabel\"></f:verbatim>"
                                    + "<t:commandButton " 
                                    // FIXME get toolbar tips to work
                                    // FIXME implement Action Listener
                                    + "value=\"#{tab_lookup[" + gridTab.getTabNo() + "]." + gridField.getColumnName() + ".label}\" />\n"
                                    + " \n<f:verbatim></td></f:verbatim>\n");
                            // check if same line.  if yes and not first field, close table row
                            if (gridField.isSameLine() && j != 0) 
                            {
                              detailJSP.append("<f:verbatim></tr></f:verbatim>\n");  
                            }
                            break;
                        case 30:
                            // search
                            // check if same line.  make sure that it isn't the first field.  It is possible that the 
                            // first field can have same line checked and that would cause formatting issues.
                            if(gridField.isSameLine() && j != 0) 
                            { 
                                isSolo = false;
                            } 
                            else 
                            {
                                if (isSolo) 
                                {
                                   detailJSP.append("<f:verbatim></tr></f:verbatim>\n"); 
                                }
                                detailJSP.append("<f:verbatim><tr></f:verbatim>\n");
                                isSolo = true;
                            }
                            detailJSP.append("<f:verbatim><td class=\"rightLabel\"></f:verbatim>"
                                    + "<t:outputText value = \"Search\" />\n"
                                    + "<f:verbatim></td></f:verbatim>\n<f:verbatim><td></f:verbatim><t:inputText value=\"Not Implemented\""
                                    + " />\n<f:verbatim></td></f:verbatim>\n");
                            // check if same line.  if yes and not first field, close table row
                            if (gridField.isSameLine() && j != 0) 
                            {
                              detailJSP.append("<f:verbatim></tr></f:verbatim>\n");  
                            }
                            break;
                        case 31:
                            // locator
                            // check if same line.  make sure that it isn't the first field.  It is possible that the 
                            // first field can have same line checked and that would cause formatting issues.
                            if(gridField.isSameLine() && j != 0) 
                            { 
                                isSolo = false;
                            } 
                            else 
                            {
                                if (isSolo) 
                                {
                                   detailJSP.append("<f:verbatim></tr></f:verbatim>\n"); 
                                }
                                detailJSP.append("<f:verbatim><tr></f:verbatim>\n");
                                isSolo = true;
                            }
                            detailJSP.append("<f:verbatim><td class=\"rightLabel\"></f:verbatim>"
                                    + "<t:outputText value = \"Locator\" />\n"
                                    + "<f:verbatim></td></f:verbatim>\n<f:verbatim><td></f:verbatim><t:inputText value=\"Not Implemented\""
                                    + " />\n<f:verbatim></td></f:verbatim>\n");
                            // check if same line.  if yes and not first field, close table row
                            if (gridField.isSameLine() && j != 0) 
                            {
                              detailJSP.append("<f:verbatim></tr></f:verbatim>\n");  
                            }
                            break;
                        case 32:
                            // image
                            // check if same line.  make sure that it isn't the first field.  It is possible that the 
                            // first field can have same line checked and that would cause formatting issues.
                            if(gridField.isSameLine() && j != 0) 
                            { 
                                isSolo = false;
                            } 
                            else 
                            {
                                if (isSolo) 
                                {
                                   detailJSP.append("<f:verbatim></tr></f:verbatim>\n"); 
                                }
                                detailJSP.append("<f:verbatim><tr></f:verbatim>\n");
                                isSolo = true;
                            }
                            detailJSP.append("<f:verbatim><td class=\"rightLabel\"></f:verbatim>"
                                    + "<t:outputText value = \"Image\" />\n"
                                    + "<f:verbatim></td></f:verbatim>\n<f:verbatim><td></f:verbatim><t:inputText value=\"Not Implemented\""
                                    + " />\n<f:verbatim></td></f:verbatim>\n");
                            // check if same line.  if yes and not first field, close table row
                            if (gridField.isSameLine() && j != 0) 
                            {
                              detailJSP.append("<f:verbatim></tr></f:verbatim>\n");  
                            }
                            break;
                        default:
                            log.severe("Display Type not handled for " + gridField.getColumnName() + " with display type: " + gridField.getDisplayType());
                            break;
                    }
                } 
            }
            detailJSP.append("<f:verbatim></table></f:verbatim>");
            saveFile(detailFile, detailJSP.toString());
        }
    }
    // Generate Table jsp page
    private void createTablePage(String realContextPath) 
    {
        File tableFile = null;
        for (int i = 0; i < tabCount; i ++) 
        {
            StringBuilder tableJSP = new StringBuilder();
            tableJSP.append(createAdempiereHeader());
            tableJSP.append(createHeader());
            
            GridTab gridTab = gridWindow.getTab(i);
            String fileName = formatNameWithFirstLowerCase(gridTab.getName()) + "Table";
            String jsfFile = realContextPath + "window/" + fileName + ".jsp";
            tableFile = new File(jsfFile);
            gridTab.dataRefreshAll();
            // counter for subscripts
            int subscriptCount = 0;
            
            tableJSP.append("<t:dataTable var=\"dto\" value=\"#{tab_lookup[" + gridTab.getTabNo() + "].fields}\" renderedIfEmpty=\"true\" "
                + "styleClass=\"wideTableStyle\" headerClass=\"wideTableHeaderStyle\"rowStyleClass=\"wideTableRowStyle\">\n");
            for (int j = 0; j < gridTab.getFieldCount(); j++) 
            {
                GridField gridField = gridTab.getField(j);
        
                tableJSP.append("<t:column>\n<f:facet name=\"header\"><t:outputText value=\"#{tab_lookup[" + gridTab.getTabNo() + "]." + gridField.getColumnName() 
                        + ".label}\" /></f:facet>\n");
                
                // check if displayed
                if (gridField.isDisplayed()) 
                {
                    int displayType = gridField.getDisplayType();
                    // check display type to genereate jsf code for each specific component
                    switch(displayType) 
                    {
                        case 10:
                        case 11:
                        case 12: 
                        case 13:
                        case 14:
                        case 15: // this is a Date format.  Comes as Date + Time and needs to be chopped in Backing Bean
                        case 16: // this is a Date + Time format.
                        case 21: // complete address comma seperated ex: street, city, state, zip
                        case 22:
                        case 29:
                        case 33:
                        case 34:
                        case 35: 
                        case 36:
                        case 37:
                            // text box
                            tableJSP.append("<t:inputText value=\"#{dto[" + (subscriptCount++) + "].value}\" />\n");
                            break;
                        case 17:
                        case 18:
                        case 19:
                            // lookup
                            tableJSP.append("<t:selectOneMenu styleClass=\"rightLabel\">\n<f:selectItems value=\"#{dto[" + (subscriptCount++) 
                                    + "].value}\" />\n</t:selectOneMenu>\n");
                            break;
                        case 20:
                            // check box 
                            tableJSP.append("<t:selectBooleanCheckbox value=\"#{dto[" + (subscriptCount++) + "].value}\"  />\n");
                            break;
                        case 23:
                        case 24:
                        case 25:
                        case 27:
                        case 28:
                            // custom component
                            tableJSP.append("<t:commandButton value=\"#{dto[" + (subscriptCount++) + "].value}\" />\n");
                           break;
                        case 30:
                            // search
                            tableJSP.append("<t:inputText value=\"Not Implemented\" />\n");
                            subscriptCount++;
                           break;
                        case 31:
                            // locator
                            tableJSP.append("<t:inputText value=\"Not Implemented\" />\n");
                            subscriptCount++;
                            break;
                        case 32:
                            // image
                            tableJSP.append("<t:inputText value=\"Not Implemented\" />\n");
                            subscriptCount++;
                            break;
                        default:
                            log.severe("Display Type not handled for " + gridField.getColumnName() + " with display type: " + gridField.getDisplayType());
                            subscriptCount++;
                            break;
                    }
                } 
                tableJSP.append("</t:column>\n");
            }
            tableJSP.append("</t:dataTable>");
            saveFile(tableFile, tableJSP.toString());
        }

    }
    // Save GridWindow jsf test file 
    private void saveFile(File file, String generatedJSF) 
    {
        try 
        {
            FileWriter out = new FileWriter(file);
            out.write(generatedJSF);
            out.flush();
            out.close();
        } 
        catch (IOException ioe) 
        {
            log.severe("Error " + ioe.toString() + " occurred in saveGridWindow");
        }
    }
    // format name of file or tab
    private String formatNameWithFirstLowerCase(String name) 
    {
        String[] tempNameArray = name.split(" ");
        for (int i = 0; i < tempNameArray.length; i++) 
        {
            if (i == 0) 
            {
                name = tempNameArray[i].toLowerCase();
            } 
            else 
            {
                name += tempNameArray[i];
            }
        }
        return name;
    } 
    // adempiere header
    private StringBuilder createAdempiereHeader(){
        StringBuilder adempiereHeader = new StringBuilder();
        adempiereHeader.append("<%--/*************************************************************\n"
                + " **********\n"
                + " *******\n" 
                + " * Product: Adempiere ERP & CRM Smart Business Solution\n"
                + " * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.\n"
                + " * This program is free software; you can redistribute it and/or modify it\n"
                + " * under the terms version 2 of the GNU General Public License as published\n"
                + " * by the Free Software Foundation. This program is distributed in the hope\n"
                + " * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied\n"
                + " * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.\n"
                + " * See the GNU General Public License for more details.\n"
                + " * You should have received a copy of the GNU General Public License along\n"
                + " * with this program; if not, write to the Free Software Foundation, Inc.,\n"
                + " * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.\n"
                + " * You may reach us at: ComPiere, Inc. - http://www.adempiere.org/license.html\n"
                + " * 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA or info@adempiere.org\n"
                + " **************************************************************\n"
                + " **********\n"
                + " *****/--%>\n");
        return adempiereHeader;
    }
    // page header
    private StringBuilder createHeader() 
    {
        StringBuilder headerString = new StringBuilder();
        headerString.append("<%@page contentType=\"text/html\"%>\n<%@page pageEncoding=\"UTF-8\"%>\n"
                + "<%@ taglib uri=\"http://java.sun.com/jsf/core\" prefix=\"f\"%>\n"
                + "<%@ taglib uri=\"http://java.sun.com/jsf/html\" prefix=\"h\"%>\n"
                + "<%@ taglib uri=\"http://myfaces.apache.org/tomahawk\" prefix=\"t\"%>\n");
         return headerString;
    }
    // Get GridWindow TimeStamp
    private long getGridWindowTimeStamp() 
    {
         Timestamp timeStamp = gridWindow.getModelUpdated(true);
         if(timeStamp.equals(null)) 
         {
             log.severe("Could not get GridWindow TimeStamp");
         }
         return timeStamp.getTime();
    }
}
