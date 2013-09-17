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
package org.compiere.test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.process.DocAction;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.compiere.util.Trx;


/**
 *	Order Test Example
 *	
 *  @author Jorg Janke
 *  @version $Id: OrderTest.java,v 1.2 2006/07/30 00:51:57 jjanke Exp $
 */
public class OrderTest implements Runnable
{
	/**
	 * 	OrderTest
	 * 	@param no thread number
	 * 	@param numberOrders number of orders to create
	 */
	public OrderTest (int no, int numberOrders, int avgLines) 
	{
		super ();
		m_no = no;
		m_numberOrders = numberOrders;
		m_maxLines = avgLines * 2;
	}	//	OrderTest
	
	int m_no = 0;
	int m_numberOrders = 0;
	int m_maxLines = 20;

	int m_errors = 0;
	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (OrderTest.class);
	
	/**
	 * 	Run
	 */
	public void run()
	{
		long time = System.currentTimeMillis();
		int count = 0;
		MBPartner bp = new MBPartner(Env.getCtx(), 117, null);
		bp.setSOCreditStatus(MBPartner.SOCREDITSTATUS_NoCreditCheck);
		bp.saveEx();
		
		//
		for (int i = 0; i < m_numberOrders; i++)
		{
			Trx trx = Trx.get(Trx.createTrxName("Test" + m_no + "_" + i),true);
			trx.start();
			//
			MOrder order = new MOrder(Env.getCtx(),0,trx.getTrxName());
			order.setDescription("#" + m_no + "_" + i);
			order.setC_DocTypeTarget_ID(135); 	//	POS
			order.setC_BPartner_ID(117);		//	C&W
			order.setSalesRep_ID(101);			//	GardenAdmin
			order.setDeliveryRule(MOrder.DELIVERYRULE_Force);
			if (!order.save())
			{
				log.warning("#" + m_no + "_" + i + ": Not saved(1)");
				m_errors++;
				continue;
			}
			Random r = new Random();
			int linesNumber = r.nextInt(m_maxLines) + 1;
			for (int j = 0; j < linesNumber; j++)
			{
				MOrderLine line = new MOrderLine(order);
				line.setM_Product_ID(123);		//	Oak Tree
				line.setQty(new BigDecimal(5));
				if (!line.save())
				{
					log.warning("#" + m_no + "_" + i + ": Line not saved");
					m_errors++;
				}
			}
			//	Process
			order.setDocAction(DocAction.ACTION_Complete);
			if (!order.processIt(DocAction.ACTION_Complete))
			{
				log.warning("#" + m_no + "_" + i + ": Not processed");
				m_errors++;
				trx.rollback();
				trx.close();
				continue;
			}
			if (!order.save())
			{
				log.warning("#" + m_no + "_" + i + ": Not saved(2)");
				m_errors++;
			}
			else
				count++;
			trx.commit();
			trx.close();
			//
			log.info(order.toString());
		}
		time = System.currentTimeMillis() - time;
		log.warning("#" + m_no + ", Errors=" + m_errors
			+ ", Count=" + count 
			+ " " + ((float)count*100/m_numberOrders)
			+ "% - " + time + "ms - ea " + ((float)time/count) + "ms");
	}	//	run
	
	/**
	 * 	Test
	 *	@param args ignored
	 */
	public static void main (String[] args)
	{
		Adempiere.startup(true);
		CLogMgt.setLoggerLevel(Level.INFO, null);
		CLogMgt.setLevel(Level.INFO);
		//
		Ini.setProperty(Ini.P_UID,"SuperUser");
		Ini.setProperty(Ini.P_PWD,"System");
		Ini.setProperty(Ini.P_ROLE,"GardenWorld Admin");
		Ini.setProperty(Ini.P_CLIENT, "GardenWorld");
		Ini.setProperty(Ini.P_ORG,"HQ");
		Ini.setProperty(Ini.P_WAREHOUSE,"HQ Warehouse");
		Ini.setProperty(Ini.P_LANGUAGE,"English");
		Login login = new Login(Env.getCtx());
		if (!login.batchLogin(null))
			System.exit(1);
		//
		CLogMgt.setLoggerLevel(Level.WARNING, null);
		CLogMgt.setLevel(Level.WARNING);

		int NO_TESTS = 2;
		int NO_ORDERS = 200;
		int NO_LINES = 20;
		
		long time = System.currentTimeMillis();
		Thread[] tests = new Thread[NO_TESTS];
		for (int i = 0; i < tests.length; i++)
		{
			tests[i] = new Thread(new OrderTest(i, NO_ORDERS, NO_LINES));
			tests[i].start();
		}
		//	Wait
		for (int i = 0; i < tests.length; i++)
		{
			try
			{
				tests[i].join();
			}
			catch (InterruptedException e)
			{
			}
		}
		time = System.currentTimeMillis() - time;

		System.out.println("Time (ms)=" + time);

	}	//	main
	
}	//	OrderTest
