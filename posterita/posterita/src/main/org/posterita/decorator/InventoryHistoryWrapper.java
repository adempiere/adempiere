package org.posterita.decorator;

import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.displaytag.decorator.TableDecorator;
import org.posterita.Constants;
import org.posterita.beans.InventoryBean;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;

public class InventoryHistoryWrapper extends TableDecorator
{
    public String getDocStatus()
    {
        InventoryBean bean = (InventoryBean) this.getCurrentRowObject();
        
        String docStatus = bean.getDocStatus();
        String docStatusMsg = "";
        
        if (DocAction.STATUS_Drafted.equals(docStatus))
            docStatusMsg = Constants.DOC_STATUS_DRAFTED;
        else
        if (DocAction.STATUS_InProgress.equals(docStatus))
            docStatusMsg = Constants.DOC_STATUS_INPROGRESS;
        else
        if (DocAction.STATUS_Completed.equals(docStatus))
            docStatusMsg = Constants.DOC_STATUS_COMPLETED;
        else
        if (DocAction.STATUS_Closed.equals(docStatus))
            docStatusMsg = Constants.DOC_STATUS_CLOSED;
        else
        if (DocAction.STATUS_Invalid.equals(docStatus))
            docStatusMsg = Constants.DOC_STATUS_INVALID;
        else
            if (DocAction.STATUS_Voided.equals(docStatus))
                docStatusMsg = Constants.DOC_STATUS_VOID;
        
        ElementBean msgBean = ElementManager.getMsg(Env.getCtx(), docStatusMsg);
        return msgBean.getName();
    }   
    
    
    public String getDelete()
    {
        InventoryBean bean = (InventoryBean) this.getCurrentRowObject();
        
        String docStatus = bean.getDocStatus();
        
        String link = "";
        
        if (!DocAction.STATUS_Completed.equals(docStatus) && !DocAction.STATUS_Closed.equals(docStatus))
        {
             link = "<a href=\"DeleteInventoryAction.do?action=deleteInventory&inventoryId=" + bean.getInventoryId().toString() + "\">" +
              "Delete" +
              "</a>";
            
        }       
        return link;
    }
    
    public String getSelect()
    {
        InventoryBean bean = (InventoryBean) this.getCurrentRowObject();
        
        String docStatus = bean.getDocStatus();
        
        String link = "";
        
        if (DocAction.STATUS_Drafted.equals(docStatus))
        {
             link = "<input type=\"checkbox\" value=\""+bean.getInventoryId()+"\" name=\"inventoryIds\"/>";
            
        }       
        return link;
    }
    
    
    public String getInventoryNo()
    {
        InventoryBean bean = (InventoryBean) this.getCurrentRowObject();
        
        String link = "<a href=\"ViewInventoryAction.do?action=viewInventory&inventoryId="+bean.getInventoryId()+"&description="+bean.getDescription()+ "\">" +
          bean.getInventoryNo() +
          "</a>";
        
        return link;
    }
}
