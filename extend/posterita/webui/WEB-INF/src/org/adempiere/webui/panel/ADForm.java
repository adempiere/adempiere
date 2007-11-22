/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.adempiere.webui.component.Window;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MForm;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

/**
 * Adempeire Web UI custom form.
 * The form is abstract, so specific types of custom form must be implemented
 * 
 * @author Andrew Kimball
 */
public abstract class ADForm extends Window implements EventListener
{
	private static final long serialVersionUID = 1L;


    /** The class' logging enabler */
    protected static final CLogger logger;

    static
    {
        logger = CLogger.getCLogger(ADForm.class);
    }
    
    /** The form's title (for display purposes) */
    private String m_title;
    /** The unique identifier of the form type */
    private int m_adFormId;
    /** The identifying number of the window in which the form is housed */ 
    protected int m_windowNo;
    
    /**
     * Constructor
     * 
     * @param ctx		the context into which the form is being placed
     * @param adFormId	the Adempiere form identifier
     */
    protected ADForm()
    {
         m_windowNo = SessionManager.getAppDesktop().registerWindow(this);
    }
    
    protected int getWindowNo()
    {
    	return m_windowNo;
    }
    
    protected int getAdFormId()
    {
    	return m_adFormId;
    }
        
    /**
     * Initialise the form 
     * 
     * @param adFormId	the Adempiere form identifier 
     * @param name		the name of the Adempiere form
     */
    
    protected void init(int adFormId, String name)
    {
        if(adFormId <= 0)
        {
	           throw new IllegalArgumentException("Form Id is invalid");
	   	}
        
        m_adFormId = adFormId;
        m_title = name;
        
        return;
    }
    
    /**
     * Accessor for the form's title
     * 
     * @return	the title of the form
     */
    public String getTitle()
    {
        return m_title;
    }


	/**
	 * Convert the rich client class name for a form to its web UI equivalent 
	 * 
	 * @param originalName	The full class path to convert
	 * @return the converted class name
	 */
	private static String translateFormClassName(String originalName)
	{
		String modifiedName;
		/* match any field containing the string ".compiere." 
		 * Usually of the form "org.compiere.apps.form.<classname>".
		 * Although there are special cases which also need handling 
		 */
		final String regex = "(.*)\\.compiere\\.(.*\\.)V(\\w*)$";
		//final String regex = "(.*)\\.compiere\\.(.*\\.)V(\\w*)$";
		/*
		 * replacement string to translate class paths to the form
		 * "org.adempiere.webui.apps.form.<classname>"
		 */
		final String replacementPackage = ".adempiere.webui.";
		/*
		 * replacement string to translate custom form class name from
		 * "V<name>" to "W<name>"
		 */
		final String replacementPrefix = "W";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(originalName);
        int group = 1;
		
		/*
		 *  If no match is found throw an exception stating that the form 
		 *  has not been implemented in the webUI
		 */		
		if (matcher.find()== false)
		{
			// What to do with those that do not have .compiere. in it
			throw new ApplicationException("The selected custom form '" + 
					originalName + 
					"' is not part of the core distribution and " +
					"has not been implemented for the web based user interface");			
		}
		
		/*
		 * reconstruct the name using the captured groups and the replacement strings
		 */
		modifiedName = matcher.group(group++) 
						+ replacementPackage 
						+ matcher.group(group++) 
						+ replacementPrefix 
						+ matcher.group(group++);
		
		return modifiedName;
	}
	
	/**
	 * Create a new form corresponding to the specified identifier
	 * 
	 * @param adFormID		The unique identifier for the form type
	 * @return The created form
	 */
	public static ADForm openForm (int adFormID)
	{
		Object obj;
		ADForm form;
		String webClassName = "";
		MForm mform = new MForm(Env.getCtx(), adFormID, null);
    	String richClassName = mform.getClassname();
    	String name = mform.getName();
    	
    	if (mform.get_ID() == 0 || richClassName == null)
    	{
			throw new ApplicationException("There is no form associated with the specified selection");
    	}
    	else
    	{
    		
    		logger.info("AD_Form_ID=" + adFormID + " - Class=" + richClassName);
    	
    		if ("org.compiere.apps.form.ArchiveViewer".equals(richClassName))
    			webClassName = "org.adempiere.webui.apps.form.WArchiveViewer"; // TEMP
    		else
    			webClassName = translateFormClassName(richClassName);
    		
    		try
    		{
    			//	Create instance w/o parameters
        		obj = Class.forName(webClassName).newInstance();
    		}
    		catch (Exception e)
    		{
    			throw new ApplicationException("The selected web user interface custom form '" + 
    					   webClassName + 
    					   "' is not accessible.");
    		}
    		
        	try
        	{
        		if (obj instanceof ADForm)
        		{
    				form = (ADForm)obj;
    				form.init(adFormID, name);
    				return form;
        		}
        		else
        		{
    				throw new ApplicationException("The web user interface custom form '" + 
    						webClassName + 
    						"' cannot be displayed in the web user interface.");
        		}
        	}
        	catch (Exception ex)
        	{
    			logger.log(Level.SEVERE, "Class=" + webClassName + ", AD_Form_ID=" + adFormID, ex);
    			throw new ApplicationException("The web user interface custom form '" + 
    					webClassName + 
    					"' failed to initialise:" + ex);
        	}
    	}		
	}	//	openForm
    
    /**
     * 
     * @return false
     */
    public boolean isAsap()
    {
        return false;
    }

    /**
     * 
     */
	public void onEvent(Event arg0) throws Exception 
    {
    	
    }
}
