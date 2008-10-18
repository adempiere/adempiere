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
 *  
 * Created on Apr 25, 2006 by praveen
 *
 */
 
package org.posterita.businesslogic.administration;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.ImageIcon;

import org.compiere.model.MAttachment;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import org.posterita.businesslogic.ProductImageUploader;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.OperationException;
import org.posterita.util.PathInfo;


public class ProductImageManager 
{
	private static transient byte[] backgroundImageData = null;
	public static final String backImageFileName = "/images/pos/ImageBack.jpg";
	
	public static byte[] getBackImageData() throws OperationException
	{
		if(backgroundImageData != null)
			return backgroundImageData;
		
		try
    	{
			String homePath = PathInfo.PROJECT_HOME;
			File imgFile = new File(homePath + backImageFileName);
	    	FileInputStream fileInStream = new FileInputStream(imgFile);
	    	ByteArrayOutputStream byteArrStream = new ByteArrayOutputStream();
	    	BufferedInputStream bufferedInStream = new BufferedInputStream(fileInStream);
	    	byte data[] = new byte[1024];
	    	int read = 0;
	    	while((read = bufferedInStream.read(data)) != -1)
	    	{
	    		byteArrStream.write(data, 0, read);
	    	}
	    	byteArrStream.flush();
	    	backgroundImageData = byteArrStream.toByteArray();
	    	bufferedInStream.close();
	    	byteArrStream.close();
	    	fileInStream.close();
	    	return backgroundImageData;
    	}
		catch(IOException ex)
		{
			throw new OperationException("Could not read backgroud image", ex);
		}
	}
	
	
    /**
     * Adds a border round the given image
     * @param ctx
     * @param m_product_id
     * @param borderWidth - the border width
     * @return a new Image
     * @throws OperationException 
     */
    public static byte[] addImageBorder(Properties ctx,int m_product_id,byte[] imageData,double borderWidth, String trxName) throws OperationException
    {
        String sql = "select " +
                " ATTR_MODEL," +    //1.model
                " ATTR_COLOUR," +   //2.colour
                " ATTR_DESIGN," +   //3.design
                " ATTR_SIZE" +      //4.size
                " from U_TShirt_V where M_Product_ID =" + m_product_id;
        
        PreparedStatement pstmt = null;        
        ResultSet rs = null;
        
        String design = null;
        
        try 
        {
            pstmt = DB.prepareStatement(sql,trxName);
            rs = pstmt.executeQuery();
            
            if(rs.next())
            {
                design = rs.getString(3);
            }
            else
            {
                throw new OperationException("Unable to get product's attributes!");
            }
            
            //-------------------------------------------------------------------------------------------
            
            Color borderColor = Color.DARK_GRAY;
            
            char copyrightSign = 169;           
            
            String copyright_notice =  copyrightSign + " " + Calendar.getInstance().get(Calendar.YEAR) + " Tamak";
            
            Image image = new ImageIcon(imageData).getImage();
            
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            
            double frameWidth = (double)width + 2*borderWidth;
            double frameHeight = (double)height + borderWidth;
            
            Rectangle2D nameRect = new Rectangle2D.Double(0.0,frameHeight,frameWidth,80);
            
            BufferedImage bufferedImage = new BufferedImage((int)frameWidth,(int)(frameHeight + nameRect.getHeight()),BufferedImage.TYPE_USHORT_565_RGB);        
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();     
            
            g.drawImage(image,(int)borderWidth,(int)borderWidth,null);         
            
            Rectangle2D topBorder = new Rectangle2D.Double(0.0,0.0,frameWidth,borderWidth);
            //Rectangle2D bottomBorder = new Rectangle2D.Double(0.0,frameHeight-borderWidth,frameWidth,borderWidth);
            Rectangle2D leftBorder = new Rectangle2D.Double(0.0,0.0,borderWidth,frameWidth);
            Rectangle2D rightBorder = new Rectangle2D.Double(frameWidth-borderWidth,0.0,borderWidth,frameWidth);
            
            g.setColor(borderColor);
            
            g.fill(topBorder); 
            //g.fill(bottomBorder);       
            g.fill(leftBorder);
            g.fill(rightBorder);
            
       
            g.fill(nameRect);        
            g.setColor(Color.LIGHT_GRAY);
            
            //drawing smaill squares
            double rXcoor = nameRect.getMinX();
            double rYcoor = nameRect.getMinY();
            
            double h = nameRect.getHeight() - 2*borderWidth;
            double w = h * 1.5;
            
            
            
            Rectangle2D rLeft = new Rectangle2D.Double(rXcoor + borderWidth, rYcoor + borderWidth, w,h);
            g.fill(rLeft);
            
            Rectangle2D rRight = new Rectangle2D.Double(nameRect.getMaxX() - w - borderWidth, rYcoor + borderWidth, w,h);
            g.fill(rRight);
            
            w = (rRight.getMinX() - borderWidth) - (rLeft.getMaxX() + borderWidth);
            Rectangle2D rCenter = new Rectangle2D.Double(rLeft.getMaxX() + borderWidth, rYcoor + borderWidth, w, h);
            g.fill(rCenter);               
       
            
            Font font = new Font("Arial", Font.PLAIN, 22);
            g.setFont(font);
            g.setColor(Color.WHITE);
            
            FontRenderContext fc = g.getFontRenderContext();
            Rectangle2D bounds = font.getStringBounds(design,fc);
            
            g.setFont(font);
            g.drawString(design,(float)(rCenter.getMinX() + borderWidth),(float)(rCenter.getMinY() + borderWidth + bounds.getHeight()));
             
            //put copyright notice
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            g.drawString(copyright_notice, (float)(2*borderWidth), (float)(frameHeight-borderWidth));
            
            //get a writer and set compression
            Iterator iter = ImageIO.getImageWritersByFormatName("JPG");
            
            if(iter.hasNext())
            {
                ImageWriter writer = (ImageWriter) iter.next();
                ImageWriteParam iwp = writer.getDefaultWriteParam();
                iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                iwp.setCompressionQuality(0.75f);
                
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                MemoryCacheImageOutputStream mos = new MemoryCacheImageOutputStream(bos);
                
                writer.setOutput(mos);
                
                
                
                IIOImage iIOimage = new IIOImage(bufferedImage,null,null);
                writer.write(null,iIOimage,iwp);
                
                byte retData[] = bos.toByteArray();
                mos.close();
                bos.close();
                return retData; 
            }
            
            rs.close();
            
        } 
        catch (Exception e) 
        {
            throw new OperationException(e);
        }
        finally
        {
        	try
        	{
        		pstmt.close();
        	}
        	catch(Exception e)
        	{}
            pstmt = null;
        }
        
        return null;  
    }
    
    /**
     * Removes the image's background
     * @param img
     * @param offset - range of color
     * @return
     */
     
    public static Image removeBgColor(Image img, int offset)
    {
        return null;
    }
    
        /**
     * Clips the given image
     * @param imageData
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    public static byte[] clipImage(byte[] imageData,int width,int height) throws IOException
    {
        Image image = new ImageIcon(imageData).getImage();
        
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(image,0,0,null);
        
        int actualWidth = image.getWidth(null);
        int actualHeight = image.getHeight(null);
        
        width = (actualWidth < width) ? actualWidth : width;
        height = (actualHeight < height) ? actualHeight : height;
        
        //int x = 0, y = 250, w = 400, h = 380;
        
        // Clip the central part of the image
        int x = (actualWidth - width)/2;
        int y = (actualHeight - height)/2;
        
        bufferedImage = bufferedImage.getSubimage(x,y,width,height);
        
        //get a writer and set compression
        Iterator iter = ImageIO.getImageWritersByFormatName("JPG");
        
        if(iter.hasNext())
        {
            ImageWriter writer = (ImageWriter) iter.next();
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(0.75f);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            MemoryCacheImageOutputStream mos = new MemoryCacheImageOutputStream(bos);
            
            writer.setOutput(mos);
            
            IIOImage iIOimage = new IIOImage(bufferedImage,null,null);
            writer.write(null,iIOimage,iwp);
            
            return bos.toByteArray(); 
        }
        
        return null;        
        
    }
    
    
    public static byte[] addImageSquareBorder(Properties ctx, byte[] imageData,double borderWidth, String trxName) throws OperationException
    {
        
        
        char copyrightSign = 169;           
        String copyright_notice =  copyrightSign + " " + Calendar.getInstance().get(Calendar.YEAR) + " Tamak";
        
        Image image = new ImageIcon(imageData).getImage();
        Image backImage = new ImageIcon(getBackImageData()).getImage();
        
        
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        int frameWidth = backImage.getWidth(null);
        int frameHeight = backImage.getHeight(null);
        
       
        BufferedImage bufferedImage = new BufferedImage((int)frameWidth,(int)(frameHeight),BufferedImage.TYPE_USHORT_565_RGB);        
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();     
        
        // Draw back image
        g.drawImage(backImage, 0, 0, null);   
        
        
        // Draw Image in the center of the background
        double imagePosX = (frameWidth - (double)width)/2;
        double imagePosY = (frameHeight - (double)height)/2;
        g.drawImage(image, (int)imagePosX, (int)imagePosY, null);         
        
        // Put Copyright Notice
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString(copyright_notice, 10.0F, (float)(frameHeight - 5));
        
        //get a writer and set compression
        Iterator iter = ImageIO.getImageWritersByFormatName("JPG");
        
        if(iter.hasNext())
        {
            ImageWriter writer = (ImageWriter) iter.next();
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(0.75f);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            MemoryCacheImageOutputStream mos = new MemoryCacheImageOutputStream(bos);
            
            writer.setOutput(mos);
            
            try
            {
	            IIOImage iIOimage = new IIOImage(bufferedImage,null,null);
	            writer.write(null,iIOimage,iwp);
	            
	            byte retData[] = bos.toByteArray();
	            mos.close();
	            bos.close();
	            return retData; 
            }
            catch(IOException ex)
            {
            	throw new OperationException("Could not write image", ex);
            }
        }
        
        throw new OperationException("JPG Image format is not supported!!!");
            
    }
    
    public static void reprocessAllProductImages(Properties ctx) throws OperationException
    {
    	int adClientID = Env.getAD_Client_ID(ctx);
    	
    	String sqlStmt = "Select M_Product_ID from M_Product where AD_Client_ID=" + adClientID
    	                 + " and AD_Org_ID=" + Env.getAD_Org_ID(ctx);
    	
    	PreparedStatement pstmt = null;
    	
    	try
    	{
    		pstmt = DB.prepareStatement(sqlStmt, null);
    		ResultSet rs = pstmt.executeQuery();
    		
    		while(rs.next())
    		{
    			int productId = rs.getInt(1);
//    			System.out.println(productId);
    			reprocessImage(ctx, productId);    			
    		}
    		
    		rs.close();
    	}
    	catch(SQLException ex)
    	{
    		throw new OperationException("Could not execute query for products: " + sqlStmt, ex);
    	}
    	finally
    	{
    		try
    		{
    			pstmt.close();
    		}
    		catch(Exception e)
    		{}
    		
    		pstmt = null;
    	}
    	
    }    
    
    public static void reprocessImages(Properties ctx, int productIds[], String trxName) throws OperationException
    {
    	for(int i = 0; i < productIds.length; i++)
    	{
    		reprocessImage(ctx, productIds[i]);
    	}
    }
    
    public static void reprocessImages(Properties ctx, Integer productIds[]) throws OperationException
    {
    	for(int i = 0; i < productIds.length; i++)
    	{
    		reprocessImage(ctx, productIds[i].intValue());
    	}
    }
    
    public static void reprocessImage(Properties ctx, int productId)
    {
    	Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
    	
    	
    	try
		{
    		trx.start();
			ProductImageUploader.generateAllSubImages(ctx, productId, trx.getTrxName());
			trx.commit();
		}
		catch(OperationException ex)
		{
			trx.rollback();
		}
		finally
		{
			trx.close();
		}
    }
    
    public static void clearImages(Properties ctx, int product_id, String trxName)
    {
    	MAttachment attachment = MAttachment.get(ctx,MProduct.Table_ID,product_id);
    	if(attachment != null)
    	{
    		attachment.delete(true);  		
    	}
    }
}
