/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 */

/**
	@author Praveen Beekoo
 */

package org.posterita.businesslogic.administration;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.model.MProduct;
import org.compiere.model.MSequence;
import org.compiere.util.Env;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import org.posterita.Constants;
import org.posterita.beans.CustomerBean;
import org.posterita.beans.ItemBean;
import org.posterita.businesslogic.CustomerCart;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.RandomStringGenerator;
import org.posterita.exceptions.OperationException;

public class BarcodeManager 
{
	public static final String BARCODE_PREFIX = "609789";
	public static final String SEQUENCE_NAME = "Barcode Sequence";
	public static boolean showPrice 	= true;
	public static boolean showTitle 	= true;
	public static boolean showSubtitle = true;
	
	public static String saveBarcode(String barcode) throws OperationException
	{
		try 
		{
			//Create the barcode bean
			Code128Bean bean = new Code128Bean();

			final int dpi = 400;

			//Configure the barcode generator
			bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar 
			                                                 //width exactly one pixel
			bean.doQuietZone(false);
			bean.setBarHeight(1.5d);		
			bean.setFontSize(1);
			bean.setFontName("Helvetica");

			//Open output file
			String filename = RandomStringGenerator.randomstring() + ".png";
			String filepath = ReportManager.getReportPath(filename);
			
			File outputFile = new File(filepath);
			OutputStream out = new FileOutputStream(outputFile);
			
			//Set up the canvas provider for monochrome JPEG output 
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(
			        out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false);

			//Generate the barcode
			bean.generateBarcode(canvas, barcode);

			//Signal end of generation
			canvas.finish();	    

			out.close();
			
			return filename;
		} 
		catch (Exception e) 
		{
			throw new OperationException(e);
		}
		
	}
	
	public static void writeBarcode(String barcode,HttpServletResponse response) throws OperationException
	{
		try 
		{
			//Create the barcode bean
			AbstractBarcodeBean bean = new Code128Bean();

			final int dpi = 400;

			//Configure the barcode generator
			bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar 
            //width exactly one pixel
			bean.doQuietZone(false);
			bean.setBarHeight(2.0d);		
			bean.setFontSize(1.0d);
			bean.setFontName("Helvetica");

			//writing the image to the response		
			OutputStream out = response.getOutputStream();
			response.setContentType("image/x-png");
			
			//Set up the canvas provider for monochrome JPEG output 
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(
			        out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false);

			//Generate the barcode
			bean.generateBarcode(canvas, barcode);

			//Signal end of generation
			canvas.finish();
			
			out.flush();
			out.close();
		} 
		catch (IOException e) 
		{
			throw new OperationException(e);
		}
		
	}
	
	public static byte[] getBarcodeAsByte(String barcode) throws OperationException
	{
		ByteArrayOutputStream baos= new ByteArrayOutputStream();
		
		try 
		{
			//Create the barcode bean
			AbstractBarcodeBean bean = new Code128Bean();

			final int dpi = 400;

			//Configure the barcode generator
			bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar 
            //width exactly one pixel
			bean.doQuietZone(false);
			bean.setBarHeight(1.5d);		
			bean.setFontSize(1);
			bean.setFontName("Helvetica");

			//writing the image to outputstream	
			
			//Set up the canvas provider for monochrome JPEG output 
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(
					baos, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false);

			//Generate the barcode
			bean.generateBarcode(canvas, barcode);

			//Signal end of generation
			canvas.finish();
			
			baos.flush();
			baos.close();
		} 
		catch (IOException e) 
		{
			throw new OperationException(e);
		}
		
		
		return baos.toByteArray();
	}
	
	public static MSequence getBarcodeSequence(Properties ctx, String trxName) throws OperationException
	{
		MSequence sequence = null;
		
		String whereClause = " name = '" + SEQUENCE_NAME + "'" +
				" and ad_client_id =" + Env.getAD_Client_ID(ctx) +
				" and ad_org_id =" + Env.getAD_Org_ID(ctx);
		
		int[]  ids = MSequence.getAllIDs(MSequence.Table_Name, whereClause, trxName);
		
		if( ( ids ==null ) || ( ids.length == 0 ) )
		{
			sequence = new MSequence(ctx, 0, trxName);
	    	
	    	sequence.setName(SEQUENCE_NAME);
	    	sequence.setPrefix(BARCODE_PREFIX);
	    	sequence.setIsAutoSequence(true);
	    	sequence.setIncrementNo(1);
	    	sequence.setIsTableID(false);
	    	sequence.setCurrentNext(1000000);
	    	
	    	boolean isSaved = sequence.save();
	    	
	    	if( !isSaved)
	    	{
	    		throw new OperationException("Unable to create barcode sequence");
	    	}
		}
		else
		{
			sequence = new MSequence(ctx, ids[0], trxName);
		}
		
		return sequence;
	}
	
	/**
	 * Print Product Barcode
	 * @param ctx
	 * @param request
	 * @param trxName
	 * @return
	 * @throws OperationException
	 */
	public static String printProductBarcode(Properties ctx, HttpServletRequest request, boolean isPrintProductName, boolean isPrintProductDescription, boolean isPrintPrices, String trxName) throws OperationException
	{
		ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.BARCODE_CART_ITEMS);
    	StringBuffer barcodeData = new StringBuffer();
    	int m_product_id = 0;
    	String productName = "";
    	BigDecimal qty = Env.ZERO;
    	BigDecimal price = Env.ZERO;
    	BigDecimal unitSalePrice = Env.ZERO;
		
    	
		for(int i=0; i<items.size(); i++)
    	{
    		ItemBean itemBean = items.get(i);
    		String barcode = "";
            String description = "";
            String title = "";
            String subtitle = "";   		
    		
    		m_product_id = itemBean.getProductId();
    		MProduct product = MProduct.get(ctx, m_product_id);
    		productName = product.getName();
    		description = product.getDescription();
    		String currSymbol = PriceListManager.getCurrency(ctx, itemBean.getPriceListId());
    		
    		qty = itemBean.getQty();
    		price = itemBean.getPrice();	
    		barcode = itemBean.getBarCode();
    		
    		if(isPrintProductName && isPrintProductDescription)
    		{
    		    description = productName + " " + description;
    		}
    		else if(isPrintProductName && !isPrintProductDescription)
    		{
    		    description = productName;
    		}
    		else
    		{
    		    //Do Nothing...
    		}
    		
    		
    		unitSalePrice = price.divide(qty);
    		
    		String str = "";
    		String[] temp;
    		String delimeter = " ";
    		temp = description.split(delimeter);
    		
    		if(description.length() >= 23)
    		{
    		
        		for(int j=0; j<temp.length; j++)
        		{
        		    str += temp[j] + " ";
        		    
        		    if(str.length() >= 23)
        		    {
        		        for(int n=0; n<j-1; n++)
        		        {
        		            title += temp[n] + " ";
        		        }
        		        
        		       subtitle = description.substring(title.length(), description.length());
        		       
        		       break;
        		    }
    
        		}
    		}
    		else
    		{
    		    title = description;
    		    subtitle = "";
    		}
    		    		 
    		barcodeData.append(BarcodeManager.getBarcodeData(title.trim(), subtitle.trim(), barcode.trim(), unitSalePrice, currSymbol, qty.intValue(), isPrintPrices));    		
    	}
    	
    	
		return barcodeData.toString();
	}
	
	/**
	 * Generate PPLB (Printer Programming Language B) for generating barcode
	 * @param title
	 * @param subtitle
	 * @param barcode
	 * @param unitSalePrice
	 * @param currSymbol
	 * @return
	 */
	public static String getBarcodeData(String title, String subtitle, String barcode, BigDecimal unitSalePrice, String currSymbol, int noOfLabels, boolean isPrintPrices)
	{	
		StringWriter sw = new StringWriter();
		PrintWriter w = new PrintWriter(sw);
		
		w.println("I8,A\n");
		w.println("ZN\n");			
		w.println("q250\n");		// Label Width to be 224 dots
		w.println("Q450,50\n");		// Form Length= 450 dots, gap dots=50
		w.println("S2\n");			// Set print speed
		w.println("OD\n");
		w.println("JF\n");
		w.println("ZT\n");			// Set print direction	
		w.println("Q40,5\n");		// Set heat density
		w.println("N\n");			// Clear Image Buffer in PPLB Printer
		w.println("R20,20\n");		// Fix Point of Origin		
		
		if(title != null)
		{
			
			w.println("A200,0,0,2,1,1,N,\"$title\"\n");		//Title (Product Name or Customer)
			w.println("A550,0,0,2,1,1,N,\"$title\"\n");
		}
		
		if(subtitle != null)
		{
			w.println("A200,20,0,2,1,1,N,\"$subtitle\"\n"); 	// Subtitle (Optional, can be used if required)
			w.println("A550,20,0,2,1,1,N,\"$subtitle\"\n");
		}
				
		w.println("B200,50,0,1,2,2,50,B,\"$barcode\"\n");		// Barcode (Product Barcode or MBPartner ID)
		w.println("B550,50,0,1,2,2,50,B,\"$barcode\"\n");
		
		if(isPrintPrices == true && unitSalePrice != null)
		{
			w.println("A200,140,0,3,1,1,N,\"$price\"\n");		// Sales Price (included vat) according to price list
			w.println("A550,140,0,3,1,1,N,\"$price\"\n");
		}
		
		w.println("A200,160,0,3,1,1,N,\"\"\n"); 
        w.println("A550,160,0,3,1,1,N,\"\"\n");
		
		// Print Label command Pp1,p2
		// p1 - Quantity of label
		// p2 - copy of label
		w.println("P" + noOfLabels + "\n");				// No of labels
		w.flush();	
		
		String printData = sw.getBuffer().toString();
		
		if(title != null)
		{
			printData = printData.replace("$title", title);
		}
		
		if(subtitle != null)
		{
			printData = printData.replace("$subtitle", subtitle);
		}
		
		printData = printData.replace("$barcode", barcode);
		
		DecimalFormat format = new DecimalFormat("#0.00");
		
		if(isPrintPrices == true && unitSalePrice != null)
		{
    		printData = printData.replace("$price", currSymbol + format.format(unitSalePrice.doubleValue()));
		}

		return printData;
		
	}
	
	/**
	 * Print Customer Barcode 
	 * @param ctx
	 * @param cart
	 * @param request
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static String printCustomerBarcode(Properties ctx, CustomerCart cart, String trxName) throws Exception
	{
		ArrayList<CustomerBean> customerList =null;
		CustomerCart customerCart = cart;
		
        if(customerCart != null)
        {
        	customerList = cart.getCustomers();    	
        	
        }
        else
        {
        	customerList = new ArrayList<CustomerBean>();
        }
        
    	StringBuffer barcodeData = new StringBuffer();
    	int bpartnerId = 0;
    	
    	
		for(int i=0; i<customerList.size(); i++)
    	{
    		CustomerBean customerBean = customerList.get(i);   		
    		bpartnerId = customerBean.getBpartnerId();
    		StringBuffer customerName = new StringBuffer();
    		
    		if(customerBean.getPartnerName() != null)
    		{
    			customerName.append(customerBean.getPartnerName()).append(" ");
    		}
    		
    		if(customerBean.getSurname() != null)
    		{
    			customerName.append(customerBean.getSurname());
    		}
    		
    		/**
    		 * Use title for name and surname
    		 * Use subtitle if you want to put address
    		 * Use barcode for ID's
    		 */
    		barcodeData.append(BarcodeManager.getBarcodeData(customerName.toString(), null, bpartnerId +"" , null, null, 1, false));
    		    		
    	}
    	
		return barcodeData.toString();
	}
	
	public static void clearBarcodecart(HttpServletRequest request)
    {
        //bug fix
        request.setAttribute("qtyTotal", null);
        request.setAttribute("grandTotal", null);
        
        request.getSession().removeAttribute(Constants.BARCODE_CART);        
        request.getSession().removeAttribute(Constants.BARCODE_CART_ITEMS);
    }
}
