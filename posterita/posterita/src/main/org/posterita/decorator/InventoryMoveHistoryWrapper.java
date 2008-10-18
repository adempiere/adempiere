package org.posterita.decorator;

import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.displaytag.decorator.TableDecorator;
import org.posterita.Constants;
import org.posterita.beans.StockMovementBean;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;

public class InventoryMoveHistoryWrapper extends TableDecorator
{
    public String getDocStatus()
    {
        StockMovementBean bean = (StockMovementBean) this.getCurrentRowObject();
        
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
        StockMovementBean bean = (StockMovementBean) this.getCurrentRowObject();
        
        String docStatus = bean.getDocStatus();
        
        String link = "";
        
        if (!DocAction.STATUS_Completed.equals(docStatus) && !DocAction.STATUS_Closed.equals(docStatus))
        {
             link = "<a href=\"StockMovementAction.do?action=deleteInventoryMove&movementId=" + bean.getMovementId().toString() + "\">" +
              "Delete" +
              "</a>";
            
        }       
        return link;
    }
    
    public String getEdit()
    {
        StockMovementBean bean = (StockMovementBean) this.getCurrentRowObject();
        
        String docStatus = bean.getDocStatus();
        
        String link = "";
        
        if (!DocAction.STATUS_Completed.equals(docStatus))
        {
            link = "<a href=\"StockMovementAction.do?action=editInventoryMove&movementId=" + bean.getMovementId().toString() + "\">" +
            "Edit" +
            "</a>";
            
        }       
        return link;
    }
    
    public String getDocumentNo()
    {
        StockMovementBean bean = (StockMovementBean) this.getCurrentRowObject();
        
        String link = "<a href=\"StockMovementAction.do?action=viewMoveLinesHistory&movementId="+bean.getMovementId()+"\">" +
          bean.getDocumentNo() +
          "</a>";
        
        return link;
    }
}
