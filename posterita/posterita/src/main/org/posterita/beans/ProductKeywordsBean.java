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
* Created on May 12, 2006 by ashley
* 
*/

package org.posterita.beans;

import org.apache.struts.upload.FormFile;

public class ProductKeywordsBean extends UDIBean
{

    public String getProductClassification() 
    {
        return productClassification;
    }
    public void setProductClassification(String productClassification) 
    {
        this.productClassification = productClassification;
    }
    public String getKeyword1()
	{
		return keyword1;
	}
	public void setKeyword1(String keyword1)
	{
		this.keyword1 = keyword1;
	}
	public String getKeyword2()
	{
		return keyword2;
	}
	public void setKeyword2(String keyword2)
	{
		this.keyword2 = keyword2;
	}
	public String getKeyword3()
	{
		return keyword3;
	}
	public void setKeyword3(String keyword3)
	{
		this.keyword3 = keyword3;
	}
	public String getKeyword4()
	{
		return keyword4;
	}
	public void setKeyword4(String keyword4)
	{
		this.keyword4 = keyword4;
	}
	public Integer[] getProductIds()
	{
		return productIds;
	}
	public void setProductIds(Integer[] productIds)
	{
		this.productIds = productIds;
	}
	
	public String getImageName()
	{
		return imageName;
	}
	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}
	public FormFile getFile()
	{
		return file;
	}
	public void setFile(FormFile file)
	{
		this.file = file;
	}
	
	public Boolean getIsSelfService()
	{
		return isSelfService;
	}

	public void setIsSelfService(Boolean isSelfService)
	{
		this.isSelfService = isSelfService;
	}	

	public String getDocumentNote()
	{
		return documentNote;
	}
	public void setDocumentNote(String documentNote)
	{
		this.documentNote = documentNote;
	}
	
	
}
