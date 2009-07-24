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
package org.compiere;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.MConversionRate;
import org.compiere.model.MUOMConversion;
import org.compiere.util.Env;
import org.compiere.util.Login;

/**
 *  Base Library Test Classes mainly for Optimize it
 *
 *  @author Jorg Janke
 *  @version $Id: Base.java,v 1.5 2006/09/21 20:44:54 jjanke Exp $
 */
class Base
{
	/**
	 *  Base Test
	 */
	public static void test()
	{
		System.out.println("** Before Init **"); //$NON-NLS-1$
		getMemoryUsed();
		Properties ctx = Login.initTest(false);
	//	Log.printProperties(System.getProperties(), "System", false);
		//
		System.gc();    //  cleanup Init
		//
		System.out.println("** Before Creation **");
		long start = getMemoryUsed();

		//  *******************************************************************

		//  Table=100, Shipper=142, Window=102, Reference=101
		int AD_Window_ID = 102;
		long startTime = System.currentTimeMillis();
		GridWindowVO vo = GridWindowVO.create(Env.getCtx(), 1, AD_Window_ID);
		GridWindow w = new GridWindow(vo);
		long endDef = System.currentTimeMillis();
		System.out.println("Load Definition Time in ms = " + String.valueOf(endDef-startTime));
		if (1==2)   //  optional step
		{
			w.loadCompete();
			long endDefComplete = System.currentTimeMillis();
			System.out.println("Load Definition Complete Time in ms = " + String.valueOf(endDefComplete-startTime));
		}
		w.query();
		long endData = System.currentTimeMillis();
		System.out.println("Load Data Time in ms = " + String.valueOf(endData-startTime));
		w.loadCompete();
		long endDataComplete = System.currentTimeMillis();
		System.out.println("Load Data Complete Time in ms = " + String.valueOf(endDataComplete-startTime));
		w.getTab(0).navigate(0);

		//  *******************************************************************
//		sleep();

		System.out.println("** Before Dispose **");
		getMemoryUsed();
		w.dispose();
//		sleep();
		//
		System.out.println("** Before GC **");
		getMemoryUsed();
		w = null;
		System.gc();
		System.out.println("** After GC **");
		getMemoryUsed();
		System.gc();

		System.out.println("** Final **");
		long complete = System.currentTimeMillis();
		System.out.println("Complete Time in ms = " + String.valueOf(complete-startTime));
		long end = getMemoryUsed();
		System.out.println("Memory increase in kB = End-Start=" + String.valueOf((end-start)/1024));
		listThreads();
		//
		System.out.println("API Test");
		System.out.println("64.72=" + MConversionRate.convert(ctx, new BigDecimal(100.0), 116, 100,0,0));
		System.out.println("0.647169=" + MConversionRate.getRate(116, 100, null, 0,0,0));
		System.out.println("12.5=" + MUOMConversion.convert(101, 102, new BigDecimal(100.0), true));

	}   //  Base

	/**
	 *  Get Used Memory in bytes
	 *  @return memory used
	 */
	private static long getMemoryUsed()
	{
		long free = Runtime.getRuntime().freeMemory();
		long total = Runtime.getRuntime().totalMemory();
		long used = total - free;
		//
		System.out.println("Memory used in kB = Total("
			+ String.valueOf(total/1024) + ")-Free("
			+ String.valueOf(free/1024) + ") = " + String.valueOf(used/1024));
		System.out.println("Active Threads=" + Thread.activeCount());
		return used;
	}   //  getMemoryUsed

	/**
	 *  Sleep for a second
	 */
	private static void sleep()
	{
		System.out.println(".. sleeping-ini .. -> " + Thread.activeCount());
		Thread.yield();
		try
		{
			Thread.sleep(1000);
		}
		catch (final InterruptedException ie)
		{}
		System.out.println(".. sleeping-end .. -> " + Thread.activeCount());
	}   //  sleep

	/**
	 *  List Threads
	 */
	private static void listThreads()
	{
		Thread[] list = new Thread[Thread.activeCount()];
	//	int no = Thread.currentThread().enumerate(list);
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null)
				System.out.println("Thread " + i + " - " + list[i].toString());
		}
	}   //  listThreads

	/**
	 *  Start
	 *  @param args ignored
	 */
	public static void main(String[] args)
	{
		Base.test();
		Env.exitEnv(0);
	}   //  main
}   //  Base
