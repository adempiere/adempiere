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
 * Created on Dec 26, 2006
 */

package org.posterita.doctype;

import org.compiere.model.MDocType;

public class UDIDocSubTypeSOValues 
{
    private UDIDocSubTypeSOValues()
	{
		
	}
    
    
	public static UDIDocSubTypeSO DOCTYPESO_OnCreditOrder = new OnCreditOrder();
	public static UDIDocSubTypeSO DOCTYPESO_POSOrder = new POSOrder();
	public static UDIDocSubTypeSO DOCTYPESO_PrepayOrder = new PrepayOrder();
	public static UDIDocSubTypeSO DOCTYPESO_Proposal = new Proposal();
	public static final UDIDocSubTypeSO DOCTYPESO_Quotation = new Quotation();
	public static UDIDocSubTypeSO DOCTYPESO_ReturnMaterial = new ReturnMaterial();
	public static UDIDocSubTypeSO DOCTYPESO_StandardOrder = new StandardOrder();
	public static UDIDocSubTypeSO DOCTYPESO_WarehouseOrder = new WarehouseOrder();
	
	public static class OnCreditOrder implements UDIDocSubTypeSO
	{

        public String getDocTypeSubSO() 
        {
            return MDocType.DOCSUBTYPESO_OnCreditOrder;
        }
	    
	}
	
	public static class POSOrder implements UDIDocSubTypeSO
	{

        public String getDocTypeSubSO() 
        {
            return MDocType.DOCSUBTYPESO_POSOrder;
        }
	    
	}
	
	public static class PrepayOrder implements UDIDocSubTypeSO
	{

        public String getDocTypeSubSO() 
        {
            return MDocType.DOCSUBTYPESO_PrepayOrder;
        }
	    
	}
	
	public static class Proposal implements UDIDocSubTypeSO
	{

        public String getDocTypeSubSO() 
        {
            return MDocType.DOCSUBTYPESO_Proposal;
        }
	    
	}
	
	public static class Quotation implements UDIDocSubTypeSO
	{

        public String getDocTypeSubSO() 
        {
            return MDocType.DOCSUBTYPESO_Quotation;
        }
	    
	}
	
	public static class ReturnMaterial implements UDIDocSubTypeSO
	{

        public String getDocTypeSubSO() 
        {
            return MDocType.DOCSUBTYPESO_ReturnMaterial;
        }
	    
	}
	
	public static class StandardOrder implements UDIDocSubTypeSO
	{

        public String getDocTypeSubSO() 
        {
            return MDocType.DOCSUBTYPESO_StandardOrder;
        }
	    
	}
	
	public static class WarehouseOrder implements UDIDocSubTypeSO
	{

        public String getDocTypeSubSO() 
        {
            return MDocType.DOCSUBTYPESO_WarehouseOrder;
        }
	    
	}
	
}
