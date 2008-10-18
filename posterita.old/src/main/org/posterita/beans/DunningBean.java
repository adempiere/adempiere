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
 * Created on Oct 27, 2006
 */


package org.posterita.beans;


public class DunningBean extends UDIBean
{
   
   
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getDunningId() {
        return dunningId;
    }
    public void setDunningId(Integer dunningId) {
        this.dunningId = dunningId;
    }
    public String getDunningLevelName() {
        return dunningLevelName;
    }
    public void setDunningLevelName(String dunningLevelName) {
        this.dunningLevelName = dunningLevelName;
    }
    public String getDunningName() {
        return dunningName;
    }
    public void setDunningName(String dunningName) {
        this.dunningName = dunningName;
    }
    public Integer getDunningLevelId() {
        return dunningLevelId;
    }
    public void setDunningLevelId(Integer dunningLevelId) {
        this.dunningLevelId = dunningLevelId;
    }
    public String getDunningPrintNote() {
        return dunningPrintNote;
    }
    public void setDunningPrintNote(String dunningPrintNote) {
        this.dunningPrintNote = dunningPrintNote;
    }
    public String getDunningPrintText() {
        return dunningPrintText;
    }
    public void setDunningPrintText(String dunningPrintText) {
        this.dunningPrintText = dunningPrintText;
    }
    public Integer getBP_Group_ID() {
        return BP_Group_ID;
    }
    public void setBP_Group_ID(Integer group_ID) {
        BP_Group_ID = group_ID;
    }
    public Integer getBpartnerId() {
        return bpartnerId;
    }
    public void setBpartnerId(Integer bpartnerId) {
        this.bpartnerId = bpartnerId;
    }
    public Integer getCurrencyId() {
        return currencyId;
    }
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }
   



    public Integer[] getBpartnerIds() {
        return bpartnerIds;
    }
    public void setBpartnerIds(Integer[] bpartnerIds) {
        this.bpartnerIds = bpartnerIds;
    }
    
    
    
}
