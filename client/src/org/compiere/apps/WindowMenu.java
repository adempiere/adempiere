/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
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
package org.compiere.apps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.compiere.swing.CFrame;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Menu component that handles the functionality expected of a standard
 * "Windows" menu for MDI or MDI-like applications.
 * 
 * @author Low Heng Sin
 * @version 2006/11/20
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1808617 ] Close Windows from "Viewer" (report) is not working
 */
public class WindowMenu extends JMenu {
    private WindowManager windowManager;
    private JFrame frame;
    private JMenuItem closeAll=new JMenuItem("Close All Windows");
    private JMenuItem closeOthers = new JMenuItem("Close Other Windows");
    
    private void setEnvText(JMenu menu, String msg) {
    	String text = Msg.getMsg(Env.getCtx(), msg);
		int pos = text.indexOf('&');
		if (pos != -1 && text.length() > pos)	//	We have a nemonic
		{
			char ch = text.toUpperCase().charAt(pos+1);
			if (ch != ' ')
			{
				text = text.substring(0, pos) + text.substring(pos+1);
				menu.setMnemonic(ch);
			}
		}
		menu.setText(text);
    }
    
    private void setEnvText(JMenuItem menu, String msg) {
    	String text = Msg.getMsg(Env.getCtx(), msg);
		int pos = text.indexOf('&');
		if (pos != -1 && text.length() > pos)	//	We have a nemonic
		{
			char ch = text.toUpperCase().charAt(pos+1);
			if (ch != ' ')
			{
				text = text.substring(0, pos) + text.substring(pos+1);
				menu.setMnemonic(ch);
			}
		}
		menu.setText(text);
    }
    
    public WindowMenu(WindowManager windowManager, JFrame frame) {
        this.windowManager = windowManager;
        this.frame = frame;
        setEnvText(this, "Window");
        closeAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WindowMenu.this.windowManager.close();
            }
        });
        setEnvText(closeAll, "CloseAllWindows");
        closeOthers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WindowMenu.this.windowManager.closeOthers((CFrame)WindowMenu.this.frame);
            }
        });
        setEnvText(closeOthers, "CloseOtherWindows");
        addMenuListener(new MenuListener() {
            public void menuCanceled (MenuEvent e) {}

            public void menuDeselected (MenuEvent e) {
                removeAll();
            }

            public void menuSelected (MenuEvent e) {
                buildChildMenus();
            }
        });
    }

    /* Sets up the children menus depending on the current desktop state */
    private void buildChildMenus() {
    	this.removeAll();
        int i;
        ChildMenuItem menu;
        CFrame[] array = windowManager.getWindows();

        if ( !(frame instanceof AMenu) )
        	add(closeOthers);
        add(closeAll);
        if (array.length > 0) { 
        	closeAll.setEnabled(true);
        	if ( array.length > 1 )
        		closeOthers.setEnabled(true);
        	else
        		closeOthers.setEnabled(false);
        	addSeparator();
        } else {
        	closeAll.setEnabled(false);
        	closeOthers.setEnabled(false);
        }
        	
        if ( !(frame instanceof AMenu) ) {
        	JFrame frame = Env.getWindow(0);
        	if (frame != null && frame instanceof AMenu) {
        		menu = new ChildMenuItem((AMenu)frame);
                menu.setState(false);
                menu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        CFrame frame = ((ChildMenuItem)ae.getSource()).getWindow();
                        AEnv.showWindow(frame);
                    }
                });
        		add(menu);
        		addSeparator();
        	}
        }
        
        for (i = 0; i < array.length; i++) {
            menu = new ChildMenuItem(array[i]);
            menu.setState(array[i].equals(frame));
            menu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    CFrame frame = ((ChildMenuItem)ae.getSource()).getWindow();
                    AEnv.showWindow(frame);
                }
            });
            //menu.setIcon(array[i].getIconImage());
            add(menu);
        }
    }

    /* This JCheckBoxMenuItem descendant is used to track the child frame that corresponds
       to a give menu. */
    class ChildMenuItem extends JCheckBoxMenuItem {
        private CFrame window;

        public ChildMenuItem(CFrame window) {
            super(window.getTitle());
            this.window=window;
        }

        public CFrame getWindow() {
            return window;
        }
    }
}