/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
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
package org.adempiere.webui.component;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.apps.AEnv;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * 
 * @author Low Heng Sin
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 			<li>BF [ 3294610] The location should allow open a google map
 * 			<li>https://sourceforge.net/tracker/?func=detail&atid=879335&aid=3294610&group_id=176962
 *
 */
public class Locationbox extends EditorBox
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9199586798474147872L;

	public Locationbox()
    {
         initComponents();
    }

     public Locationbox(String text)
     {
         super();
         setText(text);
     }
     
     protected Td tdUrl;
     protected Button bUrl;
     
     public void setButtonUrlImage(String imageSrc) {
 		bUrl.setImage(imageSrc);
 	 }
     
     
     private void initComponents() {
    	removeChild(getFirstChild());
 		Table grid = new Table();
 		appendChild(grid);
 		setWidth("100%");
 		grid.setStyle("border: none; padding: 0px; margin: 0px;");
 		grid.setDynamicProperty("width", "100%");
 		grid.setDynamicProperty("border", "0");
 		grid.setDynamicProperty("cellpadding", "0");
 		grid.setDynamicProperty("cellspacing", "0");

 		Tr tr = new Tr();
 		grid.appendChild(tr);
 		tr.setStyle("width: 100%; border: none; padding: 0px; margin: 0px; white-space:nowrap; ");
 		
 		

 		Td td = new Td();
 		tr.appendChild(td);
 		td.setStyle("border: none; padding: 0px; margin: 0px;");
 		txt = new Textbox();
 		txt.setStyle("display: inline; width: 99%;");
 		td.appendChild(txt);
 		
 		tdUrl = new Td();
 		tr.appendChild(tdUrl);
 		tdUrl.setStyle("border: none; padding: 0px; margin: 0px;");
 		tdUrl.setSclass("editor-button");
 		bUrl = new Button();
    	bUrl.setImage("/images/Online10.png");

 		bUrl.setName("bUrl");
 		bUrl.setTabindex(-1);
 		LayoutUtils.addSclass("editor-button", bUrl);
 		tdUrl.appendChild(bUrl);


 		btnColumn = new Td();
 		tr.appendChild(btnColumn);
 		btnColumn.setStyle("border: none; padding: 0px; margin: 0px;");
 		btnColumn.setSclass("editor-button");
 		btn = new Button();
 		btn.setImage("/images/Location10.png");
 		btn.setName("bLoc");
 		btn.setTabindex(-1);
 		LayoutUtils.addSclass("editor-button", btn);
 		btnColumn.appendChild(btn);

 		String style = AEnv.isFirefox2() ? "display: inline"
 				: "display: inline-block";
 		style = style
 				+ ";border: none; padding: 0px; margin: 0px; background-color: transparent;";
 		setStyle(style);
 	}
     
     public void setEnabled(boolean enabled) {
  		txt.setReadonly(!enabled);
  		btn.setEnabled(enabled);
  		btn.setVisible(enabled);
  		bUrl.setEnabled(enabled);
  		bUrl.setVisible(enabled);
  		btnColumn.setVisible(enabled);
  		if (enabled)
  		{
  			btnColumn.setSclass("editor-button");
  			tdUrl.setSclass("editor-button");
  		}
  		else
  		{
  			btnColumn.setSclass("");
  			tdUrl.setSclass("");
  		}
  	}
     
     public boolean addEventListener(String evtnm, EventListener listener) {
 		if (Events.ON_CLICK.equals(evtnm)) {
 			bUrl.addEventListener(evtnm, listener);
 			return btn.addEventListener(evtnm, listener);
 			
 		} else {
 			return txt.addEventListener(evtnm, listener);
 		}
 	}
}
