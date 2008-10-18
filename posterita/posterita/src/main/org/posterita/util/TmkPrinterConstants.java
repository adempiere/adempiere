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
 * Created on Aug 24, 2006
 */


package org.posterita.util;


public class TmkPrinterConstants 
{
	public static final String SLIP_PRINTER_THERMAL = "Slip - Thermal";
	public static final String SLIP_PRINTER_9PIN = "Slip - 9 Pin";
	public static final String NORMAL_PRINTER = "Normal";
	
	public static final int PRINTER_9PIN_WIDTH = 40; 
	public static final int PRINTER_SLIP_WIDTH = 64; 
	public static final int PRINTER_DEFAULT_WIDTH = PRINTER_9PIN_WIDTH;
	
	public static final String PAPER_CUT = new String(new byte[]{10,29,86,66});
	   // public static final String PAPER_CUT= new String (new byte[]{10,29,56,66});
	    public static final String PAGE_MODE = new String (new byte[]{27,76});
	    public static final String LOGO1= new String(new byte[]{28,112,1,48});
	    public static final String STANDARD_MODE = new String (new byte[]{27,83});
	    public static final String LINE_FEED= new String (new byte []{10});
	    public static final String H_TAB = new String (new byte[]{9});
	    public static final String SOUND_BUZZER=new String (new byte[]{27,30});
	    public static final String ABS_POSITION=new String (new byte[]{});
	    public static final String LEFT_ALIGN=new String (new byte[]{27,97,2});
	    public static final String CENTER_ALIGN=new String (new byte[]{27,97,1});
	    public static final String RIGHT_ALIGN=new String (new byte[]{27,97,2,80});
	    public static final String MULTIPLE_LINE_FEED= new String (new byte []{27,100,2});//5 lines
	    public static final String SETTING_LEFT_MARGIN=new String (new byte[]{29,76,5,0});
	    public static final String BIG_FONT=new String (new byte[]{27,33,8});
	    public static final String SMALL_FONT=new String (new byte[]{27,33,1});
	    public static final String ABS_POS_LOC1=new String (new byte[]{27,36,0,0});
	    public static final String ABS_POS_LOC2=new String (new byte[]{27,36,100,1});
	    public static final String ABS_POS_LOC3=new String (new byte[]{27,36,(byte)145,1});
	    public static final String ABS_POS_LOC4=new String (new byte[]{27,36,(byte)200,1});
	    public static final String ABS_POS_LOC5=new String (new byte[]{27,36,1,1});
	    public static final String CHARACTER_SPACING=new String (new byte[]{27,32,0});
	    public static final String H_FULL_LINE_TOP;
	    
	    public static final String FONT_NORMAL               = ((char)0x1b) + "!" + ((char)0x00);
	    public static final String FONT_SMALL                   = ((char)0x1b) + "!" + ((char)0x01);
	    public static final String FONT_NORMAL_BOLD    = ((char)0x1b) + "!" + ((char)0x08);
	    public static final String FONT_DOUBLE_HEIGHT = ((char)0x1b) + "!" + ((char)0x10);
	    public static final String FONT_DOUBLE_WIDTH  = ((char)0x1b) + "!" + ((char)0x20);
	    public static final String FONT_DOUBLE               = ((char)0x1b) + "!" + ((char)0x30);
	    
	    public static final String H_FULL_LINE_BOTTOM;
	    
	    static
	    {
	    	int length = 60;
	    	byte hFullLineTop[] = new byte[length];
	    	
	    	for(int i = 0; i < length; i++)
	    		hFullLineTop[i] = (byte)223;
	    	
	    	H_FULL_LINE_TOP = new String(hFullLineTop);
	    	
	    	byte hFullLineBottom[] = new byte[length];
	    	
	    	for(int i = 0; i < length; i++)
	    		hFullLineBottom[i] = (byte)220;
	    	
	    	H_FULL_LINE_BOTTOM = new String(hFullLineBottom);
	    }
    
}
