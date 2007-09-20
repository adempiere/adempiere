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

package org.posterita.businesslogic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.compiere.model.MSequence;
import org.compiere.util.Env;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import org.posterita.core.RandomStringGenerator;
import org.posterita.exceptions.OperationException;

public class BarcodeManager 
{
	public static final String BARCODE_PREFIX = "609789";
	public static final String SEQUENCE_NAME = "Barcode Sequence";
	
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
			bean.setBarHeight(1.5d);		
			bean.setFontSize(1);
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
	
}
