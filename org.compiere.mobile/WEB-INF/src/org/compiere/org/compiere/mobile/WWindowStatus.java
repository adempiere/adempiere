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
package org.compiere.mobile;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.compiere.model.*;
import org.compiere.util.CLogger;
import org.compiere.mobile.WStatusArrayList;


public class WWindowStatus
{
	
	/**	Logger			*/
	protected static CLogger	log = CLogger.getCLogger(WWindowStatus.class);
	
	public static WWindowStatus get (HttpServletRequest request)
	{
		HttpSession sess = request.getSession(false);
		if (sess == null)
			return null;
		String wsName=MobileUtil.getParameter(request,"wsName");
		log.fine("main window wsName = "+wsName);
		String newWindow=MobileUtil.getParameter(request,"blankWindow");
		WWindowStatus clientWS=(WWindowStatus)sess.getAttribute(NAME);
		if((wsName==null&&WStatusArrayList.get(request)==null)||clientWS.getStatusName().equals(wsName))
		{
			log.fine("Run wsName==null 1");
			log.fine("clientWS = "+clientWS);
			return clientWS;
		}
		String AD_Menu_ID=MobileUtil.getParameter(request,"AD_Menu_ID");
		if(wsName==null&&WStatusArrayList.get(request)!=null&&newWindow==null&&AD_Menu_ID!=null)
		{     log.fine("Run if(wsName == null) 2");
			  WWindowStatus ws=(WWindowStatus)WStatusArrayList.get(request).getWSFromStatusArray("1");
			  sess.setAttribute(WWindowStatus.NAME, ws);
			  return ws;
		}
		if(wsName!=null)
		{
			log.fine("if(wsName!=null)");
		WStatusArrayList wsl=WStatusArrayList.get(request);
		
			if(wsl==null)
			{log.fine("wsName!=null && wsl == null");
			return null;
			}
       
		WWindowStatus serverWS=wsl.getWSFromStatusArray(wsName);
	   
			if(serverWS==null)
			{ log.fine("wsName!=null && serverWS == null");
				return null;
			}
		
		sess.setAttribute(WWindowStatus.NAME, serverWS); 
		log.fine("serverWS");
		return serverWS;
		
		}
	return clientWS;
	}
	public WWindowStatus (GridWindowVO mWindowVO)
	{
		mWindow = new GridWindow(mWindowVO);
		curTab = mWindow.getTab(0);
		//curTab.setSingleRow(true);
		//
		ctx = mWindowVO.ctx;
		statusName="";
	}   //  WWindowStatus
/*	public WWindowStatus (GridWindowVO mWindowVO)
	{
		mWindow = new GridWindow(mWindowVO);
		curTab = mWindow.getTab(0);
		curTab.setSingleRow(true);
		ctx = mWindowVO.ctx;
		statusName="";
	} */
	public static final String NAME	= "WWindowStatus"; 
	protected GridWindow       mWindow;
	protected GridTab          curTab;
	private String          statusName;
	private boolean			readOnly = true;
	
	public Properties    ctx = null;
	public String getStatusName()
		{
	         return statusName;
		}
		public void setStatusName(String name)
		{
			statusName=name;
		}		
		public int getWindowNo()
		{
			return mWindow.getWindowNo();
		}
	public String toString()
	{
		return "WWindowStatus[" + mWindow
			+ " - " + curTab + "]";
	}
	
	public boolean isReadOnlyView() {
		return readOnly;
	}
	
	public void setRO(boolean readOnly) {
		this.readOnly = readOnly || curTab.isReadOnly();
	}
}