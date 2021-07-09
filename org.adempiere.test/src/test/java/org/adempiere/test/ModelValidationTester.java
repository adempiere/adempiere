/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.compiere.model.ModelValidationEngine;
import org.compiere.model.PO;
import org.compiere.process.DocAction;
import org.mockito.ArgumentCaptor;

public class ModelValidationTester {
	
	public static final boolean BEFORE_FAILS = true;
	public static final boolean BEFORE_SUCCEEDS = false;
	public static final boolean AFTER_FAILS = true;
	public static final boolean AFTER_SUCCEEDS = false;

	private int modelValidationCount;
	private boolean beforeFails;
	private boolean afterFails;
	private int beforeTiming;
	private int afterTiming;
	
	private PO model;
	private ModelValidationEngine engine;

	public ModelValidationTester() {}
	
	public ModelValidationTester(PO model, ModelValidationEngine engine) {
		
		setModel(model);
		setEngine(engine);
		
	}

	ArgumentCaptor<PO> poCaptor;
	
	public ArgumentCaptor<PO> getPOCaptor() {
		return poCaptor;
	}


	public void setPOCaptor(ArgumentCaptor<PO> poCaptor) {
		this.poCaptor = poCaptor;
	}

	ArgumentCaptor<Integer> timingCaptor;

	public ArgumentCaptor<Integer> getTimingCaptor() {
		return timingCaptor;
	}


	public void setTimingCaptor(ArgumentCaptor<Integer> timingCaptor) {
		this.timingCaptor = timingCaptor;
	}


	public ModelValidationEngine getEngine() {
		return engine;
	}


	public void setEngine(ModelValidationEngine engine) {
		this.engine = engine;
	}

	public PO getModel() {
		return model;
	}


	public void setModel(PO model) {
		this.model = model;
	}

	public void assertModelValidationResult() {
		
		if (modelValidationCount <= 0)
			fail("Called in the incorrect order.  Call one of the setup methods first.");
		
		if (beforeFails)
		{
			assertEquals("BeforeValidationError", ((DocAction) model).getProcessMsg(),
					"modelValidator should set processMsg");
		}
		else if (afterFails)
		{
			assertEquals("AfterValidationError", ((DocAction) model).getProcessMsg(),
					"modelValidator should set processMsg");
		}
	
		verify(engine, times(modelValidationCount)).fireDocValidate(poCaptor.capture(), 
				timingCaptor.capture());
		
		List<PO> poList = poCaptor.getAllValues();
		for (int i = 0; i < poList.size(); i++) {
			PO po = poList.get(i);
			assertEquals(model, po,
					"modelValidator not called with the correct PO on call " + i+1);
		}
	
		List<Integer> timingList = timingCaptor.getAllValues();
		int timing = timingList.get(0);
		assertEquals(beforeTiming, timing,
				"modelValidator not called with the correct timing");
		
		if (modelValidationCount == 2)
		{
			timing = timingList.get(1);
			assertEquals(afterTiming, timing,
					"modelValidator not called with the correct timing");
		}
		
		verifyNoMoreInteractions(engine);

	}


	public void setupBeforeModelValidationTest(int beforeTiming, 
			boolean beforeFails) {

		if (beforeTiming <= 0)
			fail("Invalid parameter for beforeTiming.  The value should be > 0");
		
		this.beforeFails = beforeFails;
		this.beforeTiming = beforeTiming;
		modelValidationCount = 1;

		if (beforeFails)
		{
			when(engine.fireDocValidate(any(PO.class), anyInt()))
				.thenReturn("BeforeValidationError");
		}
		else
		{
			when(engine.fireDocValidate(any(PO.class), anyInt()))
				.thenReturn(null);
			
		}
		
	}
	
	public void setupBeforeAndAfterModelValidationTest(int beforeTiming, 
			int afterTiming, boolean afterFails) {

		setupBeforeModelValidationTest(beforeTiming, false);
		
		if (afterTiming <= 0)
			return;
		
		this.afterFails = afterFails;
		this.afterTiming = afterTiming;
		modelValidationCount = 2;

		if (afterFails)
		{
			when(engine.fireDocValidate(any(PO.class), anyInt()))
				.thenReturn(null)
				.thenReturn("AfterValidationError");
		}
		else
		{
			when(engine.fireDocValidate(any(PO.class), anyInt()))
				.thenReturn(null)
				.thenReturn(null);
		}
		
	}



}