package org.posterita.struts.pos;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;

public class UOMAction extends POSDispatchAction 
{
	
	public static final String CREATE_OR_UPDATE_UOM = "createOrUpdateUOM";
    public ActionForward createOrUpdateUOM(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	return null;
    }
    
    public static final String  VIEW_UOM = "viewUOM";
    public ActionForward viewUOM(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	return null;
    }
    
    public static final String LIST_UOMS = "listUOMs";
    public ActionForward listUOMs(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd=init(mapping,form,request,response);
       
    	if (fwd!=null)
        {
        	return fwd;
        }
        Properties ctx=TmkJSPEnv.getCtx(request);
    	
    	return null;
    }
	
}
