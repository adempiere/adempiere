package org.posterita.struts.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.OrgBean;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.WarehouseAlreadyExistsException;
import org.posterita.form.OrgForm;
import org.posterita.struts.core.BaseDispatchAction;

public class OrganisationAction extends BaseDispatchAction 
{

	public static final String UPDATE_OR_CREATE_ORG = "updateOrCreateOrg";
	public ActionForward updateOrCreateOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, SQLException, WarehouseAlreadyExistsException
	{
		ActionForward fwd = init(mapping,form,request,response);		
		if (fwd!=null)
		{
			return fwd;
		}
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		OrgForm of = (OrgForm) form;
		OrgBean bean = (OrgBean) of.getBean();
		
		Trx trx = Trx.get(Trx.createTrxName(), true);
		
		try 
		{
			trx.start();
			OrganisationManager.updateOrCreateOrganisation(ctx, bean, trx.getTrxName());
			trx.commit();
		} 
		catch (OperationException e) 
		{
			trx.rollback();
			postGlobalError("error.process", e.getMessage(), request);
		}
		finally
		{
			trx.close();
		}
		ArrayList<OrgBean> list = OrganisationManager.getOrganisations(ctx, bean.getOrgName(), null);
		
		request.getSession().setAttribute(Constants.ALL_ORGS, list);	
		return mapping.findForward(LIST_ORGS);
	}
	
	public static final String LIST_ORGS = "listOrgs";
	public ActionForward listOrgs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, SQLException
	{
		ActionForward fwd = init(mapping,form,request,response);		
		if (fwd!=null)
		{
			return fwd;
		}
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		OrgForm of = (OrgForm) form;
		OrgBean bean = (OrgBean) of.getBean();
		
		String orgName = bean.getOrgName();
		ArrayList<OrgBean> list = OrganisationManager.getOrganisations(ctx, orgName, null);
		
		request.getSession().setAttribute(Constants.ALL_ORGS, list);		
		return mapping.findForward(LIST_ORGS);
	}
	
	public static final String ACTIVATE_ORG = "activateOrg";
	public ActionForward activateOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping,form,request,response);		
		if (fwd!=null)
		{
			return fwd;
		}
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		OrgForm of = (OrgForm) form;
		OrgBean bean = (OrgBean) of.getBean();
		
		Trx trx = Trx.get(Trx.createTrxName(), true);
		
		try 
		{
			trx.start();
			OrganisationManager.activateOrganisation(ctx, bean, trx.getTrxName());
			trx.commit();
		} 
		catch (OperationException e) 
		{
			trx.rollback();
			postGlobalError("error.process", e.getMessage(), request);
		}
		finally
		{
			trx.close();
		}
		ArrayList<OrgBean> list = new ArrayList<OrgBean>();
		list.add(OrganisationManager.getOrganisation(ctx, bean.getOrgId()));
		
		request.getSession().setAttribute(Constants.ALL_ORGS, list);
		return mapping.findForward(LIST_ORGS);
	}
	
	public static final String VIEW_ORG = "viewOrg";
	public ActionForward viewOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, SQLException
	{
		ActionForward fwd = init(mapping,form,request,response);		
		if (fwd!=null)
		{
			return fwd;
		}
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		OrgForm of = (OrgForm) form;
		
		OrgBean bean = (OrgBean) of.getBean();
		bean = OrganisationManager.getOrganisation(ctx, bean.getOrgId());
		of.populate(bean);
		
		request.getSession().setAttribute(Constants.ORG_ID,bean.getOrgId());
		return mapping.findForward(UPDATE_OR_CREATE_ORG);
	}
}
