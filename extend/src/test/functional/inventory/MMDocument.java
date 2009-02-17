/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2009 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package test.functional.inventory;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.process.DocAction;

/**
 * @author Teo Sarca, www.arhipac.ro
 */
public class MMDocument
{
	public final MMScenario scenario;
	
	public int csvLineNo = -1;
	public String DocBaseType;
	public String DocumentNo;
	public String BPValue = "junit-test-bp01";
	public String LocatorValue;
	public String LocatorValueTo;
	public String ProductValue;
	public BigDecimal Price;
	public BigDecimal Qty;
	public BigDecimal QtyOrdered;
	public BigDecimal QtyReserved;
	public Timestamp Date;
	public String ASI;
	public String PODocumentNo;
	public boolean IsReversal = false;
	//
	public DocAction document = null;
	
	public MMDocument(MMScenario scenario)
	{
		this.scenario = scenario;
	}
	
	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "MMDocument ( "
//	        + super.toString() + TAB
	        + "csvLineNo = " + this.csvLineNo + TAB
	        + "DocBaseType = " + this.DocBaseType + TAB
	        + "DocumentNo = " + this.DocumentNo + TAB
	        + "LocatorValue = " + this.LocatorValue + TAB
	        + "LocatorValueTo = " + this.LocatorValueTo + TAB
	        + "ProductValue = " + this.ProductValue + TAB
	        + "Price = " + this.Price + TAB
	        + "Qty = " + this.Qty + TAB
	        + "QtyOrdered = " + this.QtyOrdered + TAB
	        + "QtyReserved = " + this.QtyReserved + TAB
	        + "ASI = " + this.ASI + TAB
	        + "Date = " + this.Date + TAB
	        + "PODocumentNo = " + this.PODocumentNo + TAB
	        + "IsReversal = " + this.IsReversal + TAB
	        + "document = " + this.document + TAB
	        + " )";
	
	    return retValue;
	}
	
}
