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
 * Created on Aug 16, 2005 by praveen
 *
 */
package org.posterita.beans;

public class RoleBean extends UDIBean{
   
    public Integer[] getCheckBox() 
    {
        return checkBox;
    }
    
    public void setCheckBox(Integer[] checkBox) 
    {
        this.checkBox = checkBox;
    }
    
    public String getName() 
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }     
            
    public Boolean getIsActive() 
    {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive)
    {
        this.isActive = isActive;
    }
        
    public Integer getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(Integer roleId) 
    {
        this.roleId = roleId;
    }
        
    public Boolean getIsAccessAllOrgs() 
    {
        return isAccessAllOrgs;
        
    }
    
    public void setIsAccessAllOrgs(Boolean isAccessAllOrgs) 
    {
        this.isAccessAllOrgs = isAccessAllOrgs;
    }
    
    
    
    public Boolean getCanAlterOrder() 
    {
        return canAlterOrder;
    }
    
    public void setCanAlterOrder(Boolean canAlterOrder) 
    {
        this.canAlterOrder = canAlterOrder;
    }
    
    protected Boolean canCreateOrder;
    protected Boolean canViewOrder;
    
    
    public Boolean getCanCreateOrder() 
    {
        return canCreateOrder;
    }
    
    public void setCanCreateOrder(Boolean canCreateOrder) 
    {
        this.canCreateOrder = canCreateOrder;
    }
    
    public Boolean getCanViewOrder() {
        return canViewOrder;
    }
    public void setCanViewOrder(Boolean canViewOrder) {
        this.canViewOrder = canViewOrder;
    }
}
