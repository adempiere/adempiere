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
 * Created on Mar 14, 2006 by praveen
 *
 */
package org.posterita.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.posterita.exceptions.ThumbnailGenerationException;

public class ThumbnailGenerator 
{
	public static byte[] getThumbnail(byte[] data, double scaleFactor) throws ThumbnailGenerationException
	{
		if(data == null)
			throw new ThumbnailGenerationException("Imag data cannot be null");
		Image input = new ImageIcon(data).getImage();
		int imgWidth = input.getWidth(null);
		int imgHeight = input.getHeight(null);
		
		int scaledWidth = (int)((double)imgWidth * scaleFactor);
		int scaledHeight = (int)((double)imgHeight * scaleFactor);
		
		return getThumbnail(data, scaledWidth, scaledHeight);
	}
	
	public static byte[] getSquareThumbnail(byte[] data, int size) throws ThumbnailGenerationException
	{
		Image input = new ImageIcon(data).getImage();
		int imgWidth = input.getWidth(null);
		int imgHeight = input.getHeight(null);
		
		int thumbWidth;
		int thumbHeight;
		
		if(imgWidth > imgHeight)
		{
			double scale = ((double)size/(double)imgWidth);
			thumbWidth = size;
			thumbHeight = (int)(scale * (double)imgHeight);
		}
		else
		{
			double scale = ((double)size/(double)imgHeight);
			thumbHeight = size;
			thumbWidth = (int)(scale * (double)imgWidth);
		}
		
		return getThumbnail(data, thumbWidth, thumbHeight);
	}
	
	static String THUMBEXT = "jpg";
	// Original Code: http://blogs.cocoondev.org/mpo/archives/003584.html
	public static byte[] getThumbnail(byte data[], int maxSize) throws ThumbnailGenerationException
	{
		 Image i = new ImageIcon(data).getImage();

		 int iWidth = i.getWidth(null);
		 int iHeight = i.getHeight(null);
		 if (iWidth > iHeight)
			 return getThumbnail(data, maxSize,(maxSize*iHeight)/iWidth);
		 else
			 return getThumbnail(data, (maxSize*iWidth)/iHeight,maxSize);
	}
	
	public static byte[] getThumbnail(byte data[], int width, int height) throws ThumbnailGenerationException
	{
		try
		{
			 Image i = new ImageIcon(data).getImage();
	
			 Image resizedImage = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	
	//		  This code ensures that all the
	//		  pixels in the image are loaded.
			 Image temp = new ImageIcon(resizedImage).getImage();
	//		  Create the buffered image.
			 BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
	//		  Copy image to buffered image.
			 Graphics g = bufferedImage.createGraphics();
	//		  Clear background and paint the image.
			 g.setColor(Color.white);
			 g.fillRect(0, 0, temp.getWidth(null),temp.getHeight(null));
			 g.drawImage(temp, 0, 0, null);
			 g.dispose();
	
	//		  soften
			 float softenFactor = 0.01f;
			 float[] softenArray = {0, softenFactor, 0, softenFactor, 1-(softenFactor*4), softenFactor, 0, softenFactor, 0};
			 Kernel kernel = new Kernel(3, 3, softenArray);
			 ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			 bufferedImage = cOp.filter(bufferedImage, null);
	
			 ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			 BufferedOutputStream bufOutStream = new BufferedOutputStream(outStream);
			 ImageIO.write(bufferedImage, THUMBEXT, bufOutStream);
			 
			 bufOutStream.flush();
			 byte retData[] = outStream.toByteArray();
			 bufOutStream.close();
			 outStream.close();
			 return retData;
		}
		catch(IOException ex)
		{
			throw new ThumbnailGenerationException("Could not generate thumbnail", ex); 
		}
	}
}
