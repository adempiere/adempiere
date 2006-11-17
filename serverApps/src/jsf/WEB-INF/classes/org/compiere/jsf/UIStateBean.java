package org.compiere.jsf;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.compiere.util.CLogger;

/**
 * holds state for a window; currently it tracks only the windowNo attribute, provided on initial load
 * an instance of this class should be available for any window page, as it is persisted throughout the view existence via t:saveState
 * @author rfreden
 *
 */
public class UIStateBean implements Serializable
{
    private static final long serialVersionUID=2930846164351L;

    private static final CLogger log=CLogger.getCLogger(UIStateBean.class);

    // TODO: this should be an Integer
    private String windowNo;
    
    private boolean gridView;

    private int tabNo;

    public UIStateBean()
    {
        // int and boolean default initializers put this initial state at tab0Detail
        String[] s=(String[])FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap().get("windowNo");
        if(s!=null)
        {
            windowNo=s[0];
            log.info("got windowNo "+windowNo);
        }
    }

    public void setWindowNo(String w)
    {
        log.info("setting windowNo "+w);
        windowNo=w;
    }

    public String getWindowNo()
    {
        //log.info("getting windowNo "+windowNo);
        return windowNo;
    }

    public Boolean getGridView()
    {
        return gridView;
    }
    
    public void setGridView(Boolean b)
    {
        gridView=b;
    }

    public Integer getTabNo()
    {
        return tabNo;
    }

    public void setTabNo(Integer i)
    {
        tabNo=i;
    }

    public String getTabState()
    {
        String s="tab"+String.valueOf(tabNo)+(gridView?"Table":"Detail");
        log.info("returning "+s);
        return s;
    }
    
    public void toggleGridView(ActionEvent ae)
    {
        log.info("called");
        gridView=!gridView;
    }
}
