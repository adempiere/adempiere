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
package org.compiere.db;

import javax.naming.CommunicationException;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

import org.compiere.interfaces.Status;

/**
 * 	Test EJB
 *	
 *  @author Jorg Janke
 *  @version $Id: TestEJB.java,v 1.3 2006/07/30 00:55:13 jjanke Exp $
 */
public class TestEJB
{
	/**
	 * 	TestEJB
	 */
	public TestEJB ()
	{
		CConnection cc = CConnection.get(null);
		cc.setAppsHost("dev1");
		InitialContext ic = cc.getInitialContext(false);
		/**/
		try
		{
			System.out.println(ic.getEnvironment());
			System.out.println("----------------");
			NamingEnumeration<NameClassPair> ne = ic.list("");
			while (ne.hasMore())
			{
				System.out.println(ne.next());
			}
		}
		catch (Exception e)
		{
			System.err.println("..");
			e.printStackTrace();
			System.exit(1);
		}
		/**/
		
		//
		try
		{
			Status status = (Status)ic.lookup ("Status");
			//
		}
		catch (CommunicationException ce)	//	not a "real" error
		{
			System.err.println("=ce=");
			ce.printStackTrace();
		}
		catch (Exception e)
		{
			System.err.println("=e=");
			e.printStackTrace();
		}
	}

	/**
	 * 	main
	 *	@param args
	 */
	public static void main (String[] args)
	{
		new TestEJB();
	}	//	main
	
}	//	TestEJB
