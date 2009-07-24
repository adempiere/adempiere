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
package org.compiere.www;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class  WStatusArrayList
{
	public WStatusArrayList(WWindowStatus ws)
	{
		ws.setStatusName(createStatusName());
        statusArray.add(ws);
	}
    private ArrayList       statusArray=new ArrayList();
	public static final String STATUSLIST="WStatusArrayList";

    public static WStatusArrayList get(HttpServletRequest request)
	{
	  HttpSession sess = request.getSession(false);
		if (sess == null)
			return null;
     WStatusArrayList wsl=(WStatusArrayList)sess.getAttribute(STATUSLIST);
	 return wsl;
	}
    public void addWSToStatusArray(WWindowStatus ws)
	{
		ws.setStatusName(createStatusName());
		statusArray.add(ws);
	}
	public WWindowStatus getWSFromStatusArray(String name)
	{
		int len=statusArray.size();
		if(len==0)
			return null;
		for(int i=0;i<len;i++)
		{
			WWindowStatus ws = (WWindowStatus)statusArray.get(i);
			if(ws.getStatusName().equals(name))
				return ws;
		}
		return null;
	}
	public void deleteFromStatusArray(String name)
	{
		int len=statusArray.size();
		if(len==0)
			return;
		for(int i=0;i<len;i++)
		{
			WWindowStatus ws = (WWindowStatus)statusArray.get(i);
			if(ws.getStatusName().equals(name))
				statusArray.remove(i);
			return;
		}
	}
	public void setStatusArrayItem(int index,WWindowStatus ws)
	{
		  ws.setStatusName("1");
          statusArray.set(index,ws);
	}
	public int size()
	{
		return statusArray.size();
	}
	private String createStatusName()
	{
		int len=statusArray.size();
		if(len==0)
		{
			return String.valueOf(len+1);

		}
		int n=0;
		for(int i=0;i<len;i++)
		{
			WWindowStatus ws = (WWindowStatus)statusArray.get(i);
			int j=Integer.parseInt(ws.getStatusName());
			if(j>n)
				n=j;
		}
		return String.valueOf(++n);
	}
}
