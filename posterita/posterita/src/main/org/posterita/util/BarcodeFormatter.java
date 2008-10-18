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
 * 04-Oct-2006 09:52:44 by praveen
 *
 */

package org.posterita.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.posterita.beans.PrintBarcodeBean;
import org.posterita.exceptions.FormattingException;
import org.posterita.formatter.Formatter;

public class BarcodeFormatter extends Formatter
{
	boolean showPrice 	= true;
	boolean showTitle 	= true;
	boolean showSubtitle = true;
	
	public Object format(Object target) throws FormattingException 
	{	
		
		PrintBarcodeBean bean = null;
		bean = (PrintBarcodeBean) target;
		
		int qty = 0;
		String title 		= null;
		String barcode = null;
		String price 		= null;
		String subtitle 	= null;
		
		title 			= bean.getTitle();
		barcode 	= bean.getBarcode();
		price 		= bean.getPrice();
		subtitle 	= bean.getSubtitle();
		qty			= bean.getQty();
		
		//setting defaults if params are null
		title 			= ( title == null ) ? "" : title;
		barcode 	= ( barcode == null ) ? "" : barcode;
		price 		= ( price == null ) ? "" : price;
		subtitle 	= ( subtitle == null ) ? "" : subtitle;
		qty			= ( qty == 0 ) ? 1 : qty;
		
		StringWriter sw = new StringWriter();
		PrintWriter w = new PrintWriter(sw);
		
		w.println("^Q24,3");
		w.println("^W75");
		w.println("^H10");
		w.println("^P1");
		w.println("^S4");
		w.println("^AD");
		w.println("^C$qty");	//<-- qty
		w.println("^R2");
		w.println("~Q+3");
		w.println("^O0");
		w.println("^D0");
		w.println("^E10");
		w.println("~R200");
		w.println("^L");
		w.println("Dy2-me-dd");
		w.println("Th:m:s");
		
		if( showTitle )
		{
			w.println("AA,22,12,1,1,0,0,$title");	//<--title1	
			w.println("AA,22,12,1,1,0,0,$title");	//<--title1	
		}
				
		w.println("BE,42,62,2,5,100,0,1,$barcode");	//<--barcode
		w.println("BE,42,62,2,5,100,0,1,$barcode");	//<--barcode
		
		if( showPrice )
		{
			w.println("AC,222,27,1,1,0,0,$price"); //<-- price
			w.println("AC,222,27,1,1,0,0,$price"); //<-- price
		}
		
		if( showSubtitle )
		{
			w.println("AA,22,32,1,1,0,0,$subtitle");	//<-- title2
			w.println("AA,22,32,1,1,0,0,$subtitle");	//<-- title2
		}
		
		w.println("E");
		w.flush();
		
		String printData = sw.getBuffer().toString();
		
		printData = printData.replace("$qty", qty+"");
		printData = printData.replace("$title", title);
		printData = printData.replace("$subtitle", subtitle);
		printData = printData.replace("$barcode", barcode);
		printData = printData.replace("$price", price);

		return printData;
	}

	
	public Object unformat(Object target) throws FormattingException 
	{		
		throw new FormattingException("Operartion not supported");
	}


	public boolean isShowPrice() {
		return showPrice;
	}


	public void setShowPrice(boolean showPrice) {
		this.showPrice = showPrice;
	}


	public boolean isShowSubtitle() {
		return showSubtitle;
	}


	public void setShowSubtitle(boolean showSubtitle) {
		this.showSubtitle = showSubtitle;
	}


	public boolean isShowTitle() {
		return showTitle;
	}


	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
	}

}
