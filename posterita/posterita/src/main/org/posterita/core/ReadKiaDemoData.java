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
 **/
package org.posterita.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


public class ReadKiaDemoData 
{
    private static String DELIMITER = ",";
    public static void main(String args[]) throws IOException
    {
       ArrayList list = readNatisFile("/home/vickram/Documents/natisFile.csv");
       ArrayList models = readModels("/home/vickram/Documents/modelMatch.csv");
       list = processNatisUploads(list, models);
       write(list, "/home/vickram/Documents/processed.csv");
    }
    
    public static ArrayList<KiaNatisProductBean> readNatisFile(String filename) throws FileNotFoundException
    {
        FileInputStream fis = new FileInputStream(filename);
		
        ArrayList<KiaNatisProductBean> list = new ArrayList<KiaNatisProductBean>();
        
		String unitNo;
		String vinNo;
		String natisModelNo;
		String natisModelDesc;
		String engineNo;
		String mainColorCode;
		String noOfWheels;
		String tare;
		String enginePower;
		String engineCapacity;
		String year;
		String countryCode;
		String shippingInvoiceNo;
		String colorCodeHex;
		String colourName;
		String eta;
		String price;
	
		KiaNatisProductBean bean;
		
		InputStreamReader isr = null;
		BufferedReader bf = null;
		
		String line;
		try
		{
			isr = new InputStreamReader(fis);
			bf = new BufferedReader(isr);
			StringTokenizer tokenizer;
			
			while ((line = bf.readLine())!=null)
			{
				tokenizer = new StringTokenizer(line, ",");
				
				unitNo = tokenizer.nextToken();
				vinNo = tokenizer.nextToken();
				natisModelNo = tokenizer.nextToken();
				natisModelDesc = tokenizer.nextToken();
				engineNo = tokenizer.nextToken();
				mainColorCode = tokenizer.nextToken();
				noOfWheels = tokenizer.nextToken();
				tare = tokenizer.nextToken();
				enginePower = tokenizer.nextToken();
				engineCapacity = tokenizer.nextToken();
				year = tokenizer.nextToken();
				countryCode = tokenizer.nextToken();
				shippingInvoiceNo = tokenizer.nextToken();
				colorCodeHex = tokenizer.nextToken();
				colourName = tokenizer.nextToken();
				eta = tokenizer.nextToken();
				eta = "12252005";
				price = tokenizer.nextToken();
				
				engineNo = engineNo.replaceAll(" ", "");
				bean = new ReadKiaDemoData().new KiaNatisProductBean();
				bean.setUnitNo(unitNo);
				bean.setVinNo(vinNo);
				bean.setNatisModelNo(natisModelNo);
				bean.setNatisModelDesc(natisModelDesc);
				bean.setEngineNo(engineNo);
				bean.setMainColorCode(mainColorCode);
				bean.setNoOfWheels(noOfWheels);
				bean.setTare(tare);
				bean.setEnginePower(enginePower);
				bean.setEngineCapacity(engineCapacity);
				bean.setYear(year);
				bean.setCountryCode(countryCode);
				bean.setShippingInvoiceNo(shippingInvoiceNo);
				bean.setColorCodeHex(colorCodeHex);
				bean.setColourName(colourName);
				bean.setEta(eta);
				bean.setPrice(price);
				list.add(bean);
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if ( isr != null)
				{
					isr.close();
				}
				if ( bf != null)
				{
					bf.close();
				}
			}
			catch(Exception ex)
			{}
		}
		
		return list;
    }
    
    public static ArrayList<KiaNatisProductBean> readModels(String filename) throws FileNotFoundException
    {
        FileInputStream fis = new FileInputStream(filename);
		
        ArrayList<KiaNatisProductBean> list = new ArrayList<KiaNatisProductBean>();
        
    	String model;
    	String modelNo;
		String modelDerivative;
		
	
		KiaNatisProductBean bean;
		
		InputStreamReader isr = null;
		BufferedReader bf = null;
		
		String line;
		try
		{
			isr = new InputStreamReader(fis);
			bf = new BufferedReader(isr);
			StringTokenizer tokenizer;
			
			while ((line = bf.readLine())!=null)
			{
				tokenizer = new StringTokenizer(line, ",");
				
				model = tokenizer.nextToken();
				modelNo = tokenizer.nextToken();
				modelDerivative = tokenizer.nextToken();
				
				bean = new ReadKiaDemoData().new KiaNatisProductBean();
				bean.setModel(model);
				bean.setNatisModelNo(modelNo);
				bean.setModelDerivative(modelDerivative);
				list.add(bean);
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (fis != null)
				{
					fis.close();
				}
				if (isr != null)
				{
					isr.close();
				}
				if( bf != null)
				{
					bf.close();
				}
			}
			catch(Exception ex)
			{}
		}
		
		return list;
    }
    
    
    class KiaNatisProductBean
    {
        String unitNo;
		String vinNo;
		String natisModelNo;
		String natisModelDesc;
		String engineNo;
		String mainColorCode;
		String noOfWheels;
		String tare;
		String enginePower;
		String engineCapacity;
		String year;
		String countryCode;
		String shippingInvoiceNo;
		String colorCodeHex;
		String colourName;
		String eta;
		String price;
		String model;
		String modelDerivative;
		
      
        public String getModel() {
            return model;
        }
        public void setModel(String model) {
            this.model = model;
        }
        public String getModelDerivative() {
            return modelDerivative;
        }
        public void setModelDerivative(String modelDerivative) {
            this.modelDerivative = modelDerivative;
        }
        public String getColorCodeHex() {
            return colorCodeHex;
        }
        public void setColorCodeHex(String colorCodeHex) {
            this.colorCodeHex = colorCodeHex;
        }
        public String getColourName() {
            return colourName;
        }
        public void setColourName(String colourName) {
            this.colourName = colourName;
        }
        public String getCountryCode() {
            return countryCode;
        }
        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }
        public String getEngineCapacity() {
            return engineCapacity;
        }
        public void setEngineCapacity(String engineCapacity) {
            this.engineCapacity = engineCapacity;
        }
        public String getEngineNo() {
            return engineNo;
        }
        public void setEngineNo(String engineNo) {
            this.engineNo = engineNo;
        }
        public String getEnginePower() {
            return enginePower;
        }
        public void setEnginePower(String enginePower) {
            this.enginePower = enginePower;
        }
        public String getEta() {
            return eta;
        }
        public void setEta(String eta) {
            this.eta = eta;
        }
        public String getMainColorCode() {
            return mainColorCode;
        }
        public void setMainColorCode(String mainColorCode) {
            this.mainColorCode = mainColorCode;
        }
        public String getNatisModelDesc() {
            return natisModelDesc;
        }
        public void setNatisModelDesc(String natisModelDesc) {
            this.natisModelDesc = natisModelDesc;
        }
        public String getNatisModelNo() {
            return natisModelNo;
        }
        public void setNatisModelNo(String natisModelNo) {
            this.natisModelNo = natisModelNo;
        }
        public String getNoOfWheels() {
            return noOfWheels;
        }
        public void setNoOfWheels(String noOfWheels) {
            this.noOfWheels = noOfWheels;
        }
        public String getPrice() {
            return price;
        }
        public void setPrice(String price) {
            this.price = price;
        }
        public String getShippingInvoiceNo() {
            return shippingInvoiceNo;
        }
        public void setShippingInvoiceNo(String shippingInvoiceNo) {
            this.shippingInvoiceNo = shippingInvoiceNo;
        }
        public String getTare() {
            return tare;
        }
        public void setTare(String tare) {
            this.tare = tare;
        }
        public String getUnitNo() {
            return unitNo;
        }
        public void setUnitNo(String unitNo) {
            this.unitNo = unitNo;
        }
        public String getVinNo() {
            return vinNo;
        }
        public void setVinNo(String vinNo) {
            this.vinNo = vinNo;
        }
        public String getYear() {
            return year;
        }
        public void setYear(String year) {
            this.year = year;
        }
        
        public String toString()
        {
            String str = unitNo 			+ DELIMITER +
			            vinNo 				+ DELIMITER +
			            natisModelNo 		+ DELIMITER +
			            natisModelDesc 		+ DELIMITER +
			            engineNo 			+ DELIMITER +
			            mainColorCode 		+ DELIMITER +
			            noOfWheels 			+ DELIMITER +
			            tare 				+ DELIMITER +
			            enginePower 		+ DELIMITER +
			            engineCapacity 		+ DELIMITER +
			            year 				+ DELIMITER +
			            countryCode 		+ DELIMITER +
			            shippingInvoiceNo 	+ DELIMITER +
			            colorCodeHex 		+ DELIMITER +
			            colourName 			+ DELIMITER +
			            eta 				+ DELIMITER +
			            price 				+ DELIMITER +
			            "" 					+ DELIMITER + //natis registration status
			            "" 					+ DELIMITER + //order status
			            ""; //description
            return str;
        }
    }
    
    public static ArrayList processNatisUploads(ArrayList natisUploads, ArrayList models)
    {
       Iterator iter = natisUploads.iterator();
       
       KiaNatisProductBean bean;
       
       String match;
       while (iter.hasNext())
       {
           bean = (KiaNatisProductBean) iter.next();
           match = getModelMatch(bean, models);
           bean.setNatisModelDesc(match);
           
       }
       
       return natisUploads;
    }
    
    public static String getModelMatch(KiaNatisProductBean bean, ArrayList models)
    {
        Iterator iter = models.iterator();
    
        KiaNatisProductBean model;
        
        while (iter.hasNext())
        {
            model = (KiaNatisProductBean) iter.next();
            
            if (matchFound(bean, model))
                return model.getModel();
        }
        
        return "No match Found";
        
    }
    
    public static boolean matchFound(KiaNatisProductBean bean, KiaNatisProductBean bean2)
    {
        if (bean.getNatisModelNo().equals(bean2.getNatisModelNo()))
            if (bean.getNatisModelDesc().equals(bean2.getModelDerivative()))
                return true;
            
        return false;
    }
    
    public static void write(ArrayList natisModels, String fileName) throws IOException
    {
        Iterator iter = natisModels.iterator();
        
        StringBuffer buffer = new StringBuffer();
        KiaNatisProductBean bean;
        while (iter.hasNext())
        {
            bean = (KiaNatisProductBean) iter.next();
            buffer.append(bean.toString());
            buffer.append("\n");
            
        }
        
        FileManager.write(fileName, buffer.toString(), false);
    }
}
