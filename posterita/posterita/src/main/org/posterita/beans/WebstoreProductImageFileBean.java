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
 **/

/**
	@author ashley
 */

package org.posterita.beans;

import java.util.ArrayList;
import java.util.Iterator;

public class WebstoreProductImageFileBean
{

	private String fileName;
	private String brandDesignModelColourAttr;
	
	private ArrayList<String> sizeList;
	
	public WebstoreProductImageFileBean()
	{
		sizeList = new ArrayList<String>();
	}

	public String getBrandDesignModelColourAttr()
	{
		return brandDesignModelColourAttr;
	}

	public void setBrandDesignModelColourAttr(String brandDesignModelColourAttr)
	{
		this.brandDesignModelColourAttr = brandDesignModelColourAttr;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public ArrayList<String> getSizeList()
	{
		return sizeList;
	}

	public void setSizeList(ArrayList<String> sizeList)
	{
		this.sizeList = sizeList;
	}
	
	public void addSize(String size)
	{
		sizeList.add(size);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean equals = false;
		
		if(obj instanceof WebstoreProductImageFileBean)
		{
			if(((WebstoreProductImageFileBean)obj).brandDesignModelColourAttr.equalsIgnoreCase(this.brandDesignModelColourAttr))
			{
				WebstoreProductImageFileBean tWProductImageFileBean = (WebstoreProductImageFileBean)obj;
				Iterator<String> sizeIter = tWProductImageFileBean.getSizeList().iterator();
				while(sizeIter.hasNext())
				{
					String s = sizeIter.next();
					if(this.sizeList.contains(s))
					{
						equals = true;
						break;
					}
				}
			}
		}
		
		return equals;
	}
}
