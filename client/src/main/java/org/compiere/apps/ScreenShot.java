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
 *****************************************************************************/
package org.compiere.apps;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.ExtensionFileFilter;
import org.compiere.util.Msg;

/**
 *	JPEG File Utility
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ScreenShot.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 * 
 *	@author		jmpiloq, evenos GmbH
 *	@version	$Id
 *	@see		BF [ 3073316 ]
 */
public class ScreenShot
{
	/**
	 * 	Create JPEG file from window
	 * 	@param window window
	 * 	@param fileName optional file name
	 * 	@return true if created
	 */
	public static boolean createJPEG (Window window, String fileName)
	{
		BufferedImage bi = null;
		
		if (window == null || fileName == null)
			new IllegalArgumentException("ScreenShot.createJPEG Window os NULL");

		//	Get File
		File file = getJPGFile (window);
		if (file == null)
			return false;
		log.config("File=" + file);
		if (file.exists())
			file.delete();

		//	Get Writer
		Iterator writers = ImageIO.getImageWritersByFormatName("jpg");
		ImageWriter writer = (ImageWriter)writers.next();
		if (writer == null)
		{
			log.log(Level.SEVERE, "no ImageWriter");
			return false;
		}

		//	Get Image
		try {
			Thread.sleep(1000);
			bi = getImage(window);
		} catch (InterruptedException ex) {
			log.log(Level.SEVERE, "ex", ex);
		}

		//	Write Image
		try
		{
			ImageOutputStream ios = ImageIO.createImageOutputStream (file);
			writer.setOutput(ios);
			writer.write(bi);
			ios.flush();
			ios.close();

		}
		catch (IOException ex)
		{
			log.log(Level.SEVERE, "ex", ex);
			return false;
		} 
		return true;
	}	//	createJPEG


	/**
	 * 	Get JPEG File
	 * 	@param parent parent
	 * 	@return file
	 */
	protected static File getJPGFile (Component parent)
	{
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new ExtensionFileFilter("jpg", Msg.getMsg(Env.getCtx(), "FileJPEG")));
		if (fc.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION)
			return null;
		File file = fc.getSelectedFile();
		if (file == null)
			return null;
		String fileName = file.getAbsolutePath();
		if (!(fileName.toUpperCase().equals(".JPG") || fileName.toUpperCase().equals(".JPEG")))
			fileName += ".jpg";
		fc.setVisible(false);
		return new File (fileName);
	}	//	getFile
	
	/**
	 * 	Get Image of Window
	 * 	@param window window
	 * 	@return image
	 */
	protected static BufferedImage getImage (Window window)
	{
		BufferedImage bi = null;
		try {
			bi = new Robot().createScreenCapture(window.getBounds());
			window.paintAll(bi.createGraphics());
		} catch (AWTException ex) {
			log.log(Level.SEVERE, "ex", ex);
		}
		return bi;
	}	//	getImage

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ScreenShot.class);
}	//	ScreenShot
