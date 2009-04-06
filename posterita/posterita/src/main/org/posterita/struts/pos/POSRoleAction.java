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
 * Created on 25-Jul-2005 by alok
 *
 */

package org.posterita.struts.pos;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MRole;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.RoleBean;
import org.posterita.businesslogic.MenuManager;
import org.posterita.businesslogic.administration.RoleManager;
import org.posterita.core.MenuItem;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.NoAccessToEditObjectException;
import org.posterita.exceptions.NoCheckBoxSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.RoleAlreadyExistsException;
import org.posterita.exceptions.SystemException;
import org.posterita.form.RoleForm;
import org.posterita.struts.core.DefaultForm;

public class POSRoleAction extends POSDispatchAction
{
	public static final String CREATE_ROLE = "createRole";
	   public static final String INIT_ROLE = "initRole";
	   public static final String LIST_ROLES = "listRoles";
	   public static final String VIEW_ROLE = "viewRole";
	   public static final String DELETE_ROLE = "deleteRole";
	   
	   public ActionForward createRole(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	   {
	       ActionForward fwd = init(mapping,form,request,response);
			
			if (fwd!=null)
				return fwd;
			
			Properties ctx = TmkJSPEnv.getCtx(request);
			
			DefaultForm df = (DefaultForm) form;
			
			RoleBean bean = (RoleBean) df.getBean();
			
					
			//Create Role and assign menus		
			MRole role;
			
			Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
			
	        try 
	        {
	        	trx.start();
	            role = RoleManager.createRole(ctx, bean.getOrgId(), bean.getName(),bean.getIsAccessAllOrgs(), bean.getCheckBox(), bean.getUserDiscount(), bean.getIsOverwritePriceLimit(), bean.getIsDiscountAllowedOnTotal(), bean.getIsDiscountUptoLimitPrice(), bean.getCanCreateOrder(), bean.getCanAlterOrder(), bean.getCanViewOrder(), trx.getTrxName());
	            trx.commit();
	            ArrayList menus = MenuManager.getMenus(ctx, role.get_ID());
	    		MenuItem rootItem = MenuManager.buildMenuTree(ctx, menus);
	    		
	    		request.getSession().setAttribute(Constants.ROLE,bean);
	    		request.getSession().setAttribute(Constants.ROLE_MENUS, rootItem.getSubMenus());
	        } 
	        catch (RoleAlreadyExistsException e) 
	        {
	        	trx.rollback();
	            postGlobalError("error.role.already.exists",request);
	            return mapping.getInputForward();
	        }    
	        finally
	        {
	        	trx.close();
	        }
			
	        return mapping.findForward(CREATE_ROLE);
	       
	   }
	   
	   public ActionForward initRole(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	   {
	       ActionForward fwd = init(mapping,form,request,response);
			
			if (fwd!=null)
				return fwd;
			
			Properties ctx = TmkJSPEnv.getCtx(request);
			
			//ArrayList menuList = MenuKeyNamePair.getKeyNamePairs(ctx);
			
			//String menu = MenuItem.getMenu(ctx, null);
			
			//request.getSession().setAttribute(Constants.MENUS,menu);		
			
			ArrayList menus = MenuManager.getMenusForOrganisationType(ctx);
			MenuItem rootItem = MenuManager.buildMenuTree(ctx, menus);
			
			//request.getSession().setAttribute(Constants.ROLE,bean);
			request.getSession().setAttribute(Constants.ROLE_MENUS, rootItem.getSubMenus());
			
	        return mapping.findForward(CREATE_ROLE);
	       
	   }
	   
	   public ActionForward listRoles(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, SystemException, OperationException
	   {
	       ActionForward fwd = init(mapping,form,request,response);
			
			if (fwd!=null)
				return fwd;
			
			Properties ctx = TmkJSPEnv.getCtx(request);
			
			DefaultForm df = (DefaultForm) form;
			
			ArrayList roles = new ArrayList();
			
			if (df.getFirst() != null && df.getFirst().equals("1"))
			{
				request.getSession().setAttribute(Constants.ALL_ROLES,roles);
				return mapping.findForward(LIST_ROLES);
			}
			else
				roles = RoleManager.getRoles(ctx, df.getName());
			
			request.getSession().setAttribute(Constants.ALL_ROLES,roles);			
			
	        return mapping.findForward(LIST_ROLES);
	       
	   }
	   
	   public ActionForward deleteRole(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, SystemException, OperationException
	   {
	       ActionForward fwd = init(mapping,form,request,response);
			
			if (fwd!=null)
				return fwd;
			
			Properties ctx = TmkJSPEnv.getCtx(request);
			
			DefaultForm df = (DefaultForm) form;
			
			Integer roleId = Integer.valueOf(df.getRoleId());
			
			RoleManager.deleteRole(ctx,roleId.intValue());
			
			return mapping.findForward(DELETE_ROLE);
	   }
	   
	   
	   public ActionForward viewRole(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, SystemException, OperationException
	   {
	       ActionForward fwd = init(mapping,form,request,response);
			
			if (fwd!=null)
				return fwd;
			
			Properties ctx = TmkJSPEnv.getCtx(request);
			
			DefaultForm df = (DefaultForm) form;
			
			Integer roleId = Integer.valueOf(df.getRoleId());
			
			RoleBean bean = RoleManager.getRole(ctx,roleId.intValue());
			
			//ArrayList menus = MenuItem.getMenu(ctx,roleId.intValue());
			
			//request.getSession().setAttribute(Constants.MENUS,menus);
			
			ArrayList menus = MenuManager.getMenus(ctx, roleId.intValue());
			MenuItem rootItem = MenuManager.buildMenuTree(ctx, menus);
			
			request.getSession().setAttribute(Constants.ROLE_MENUS, rootItem.getSubMenus());
			request.getSession().setAttribute(Constants.ROLE,bean);
			
	        return mapping.findForward(VIEW_ROLE);
	       
	   }
	   
	   private static final String INIT_EDIT_ROLE = "initEditRole";
	   public ActionForward initEditRole(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, SystemException, OperationException
	   {
		   ActionForward fwd = init(mapping,form,request,response);
			
			if (fwd!=null)
				return fwd;
			
			Properties ctx = TmkJSPEnv.getCtx(request);
			
			DefaultForm df = (DefaultForm)form;
			
			Integer roleId = Integer.valueOf(df.getRoleId());
			
			RoleBean bean = RoleManager.getRole(ctx,roleId.intValue());
			MenuItem menuItem = RoleManager.getAvailableMenus(ctx, roleId);
			
			request.getSession().setAttribute(Constants.ROLE_MENUS, menuItem.getSubMenus());
			request.getSession().setAttribute(Constants.ROLE,bean);
			
			return mapping.findForward(INIT_EDIT_ROLE);
	   }

	   
	   private static final String INIT_ROLE_ORG_ACCESS = "initRoleOrgAccess";
	   public ActionForward initEditRoleOrgAccess(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, SystemException, OperationException
	   {
		   ActionForward fwd = init(mapping,form,request,response);
			
			if (fwd!=null)
				return fwd;
			
			Properties ctx = TmkJSPEnv.getCtx(request);
			
			RoleForm rf = (RoleForm) form;
			
			RoleBean bean = (RoleBean) rf.getBean();
			
			ArrayList<RoleBean> list = RoleManager.getRoleOrgAccess(ctx, bean.getRoleId());
			RoleBean myRoleBean = RoleManager.getRole(ctx, bean.getRoleId());
			
			request.getSession().setAttribute(Constants.ROLE_ORG_ACCESS_LIST, list);
			request.getSession().setAttribute(Constants.ROLE, myRoleBean);
			

			return mapping.findForward(INIT_ROLE_ORG_ACCESS);
	   }
	   
	   private static final String EDIT_ROLE_ORG_ACCESS = "editRoleOrgAccess";
	   public ActionForward editRoleOrgAccess(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, SystemException, OperationException
	   {
		   ActionForward fwd = init(mapping,form,request,response);
			
			if (fwd!=null)
				return fwd;
			
			Properties ctx = TmkJSPEnv.getCtx(request);
			
			DefaultForm df = (DefaultForm) form;
			
			//RoleBean bean = (RoleBean) rf.getBean();
			
			ArrayList<RoleBean> roleBeanList = df.getRoleOrgAccessList();
			
			Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
			trx.start();			
			
			
			try
			{			
				RoleManager.editRoleOrgAccess(ctx, roleBeanList, trx.getTrxName());
				trx.commit();
			}
			catch(OperationException ex)
			{
				trx.rollback();
				postGlobalError("error.role.org.access.edit", request);
				return mapping.getInputForward();				
			}
			finally
			{
				trx.close();
			}			

			return mapping.findForward(EDIT_ROLE_ORG_ACCESS);
	   }

	   
	   public static final String EDIT_ROLE = "editRole";
	   public ActionForward editRole(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, SystemException, OperationException
	   {
		   ActionForward fwd = init(mapping,form,request,response);
			
			if (fwd!=null)
				return fwd;
			
			Properties ctx = TmkJSPEnv.getCtx(request);
			
			DefaultForm df = (DefaultForm)form;
			
			RoleBean roleBean = (RoleBean)df.getBean();
			
			Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
			trx.start();
			try
			{
				RoleManager.editRole(ctx, roleBean.getRoleId(), roleBean.getName(), roleBean.getIsAccessAllOrgs(), roleBean.getCheckBox(), roleBean.getUserDiscount(), roleBean.getIsOverwritePriceLimit(), roleBean.getIsDiscountUptoLimitPrice(), roleBean.getIsDiscountAllowedOnTotal() ,trx.getTrxName());
				trx.commit();
			}
			catch(RoleAlreadyExistsException ex)
			{
				trx.rollback();
	            postGlobalError("error.role.already.exists",request);
	            return mapping.getInputForward();
			}
	        catch (NoAccessToEditObjectException e)
	        {
	        	trx.rollback();
	            postGlobalError("error.no.edit.access", request);
	            return mapping.getInputForward();
	        }  			
			catch(NoCheckBoxSelectedException e)
			{
				trx.rollback();
	            postGlobalError("error.no.checkbox.selected",request);
	            return mapping.getInputForward();
			}
			catch(OperationException ex)
			{
				trx.rollback();
				postGlobalError("error.role.edit", request);
				return mapping.getInputForward();				
			}
			finally
			{
				trx.close();
			}
			
			RoleBean bean = RoleManager.getRole(ctx, roleBean.getRoleId());
			ArrayList menus = MenuManager.getMenus(ctx, roleBean.getRoleId());
			MenuItem rootItem = MenuManager.buildMenuTree(ctx, menus);
			
			request.getSession().setAttribute(Constants.ROLE_MENUS, rootItem.getSubMenus());
			request.getSession().setAttribute(Constants.ROLE, bean);
			
			ArrayList<RoleBean> roles = RoleManager.getRoles(ctx, null);
			
			request.getSession().setAttribute(Constants.ALL_ROLES,roles);				
			
			return mapping.findForward(EDIT_ROLE);
	   }

}
