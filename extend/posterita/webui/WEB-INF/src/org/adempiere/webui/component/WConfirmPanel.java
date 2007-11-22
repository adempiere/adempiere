/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.component;

import java.io.IOException;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * @author Andrew Kimball
 *
 */
public class WConfirmPanel extends Panel implements EventListener
{
    /** Action String OK.        */
    public static final String A_OK = "Ok";
    /** Action String Cancel.    */
    public static final String A_CANCEL = "Cancel";
    /** Action String Refresh.   */
    public static final String A_REFRESH = "Refresh";
    /** Action String Reset.     */
    public static final String A_RESET = "Reset";
    /** Action String Customize. */
    public static final String A_CUSTOMIZE = "Customize";
    /** Action String History.   */
    public static final String A_HISTORY = "History";
    /** Action String Zoom.      */
    public static final String A_ZOOM = "Zoom";

    /** Action String Process.   */
    public static final String A_PROCESS = "Process";
    /** Action String Print.     */
    public static final String A_PRINT = "Print";
    /** Action String Export.    */
    public static final String A_EXPORT = "Export";
    /** Action String Help.      */
    public static final String A_HELP = "Help";
    /** Action String Delete.    */
    public static final String A_DELETE = "Delete";
    /** Action String PAttribute.    */
    public static final String A_PATTRIBUTE = "PAttribute";
    /** Action String New.   */
    public static final String A_NEW = "New";

    /** Logger.          */
    private static CLogger log = CLogger.getCLogger(WConfirmPanel.class);


    /** Cancel action. */
    private WAppsAction m_actionCancel;
    private WAppsAction m_actionOk;

    private WAppsAction m_actionRefresh;
    private WAppsAction m_actionReset;
    private WAppsAction m_actionCustomize;
    private WAppsAction m_actionHistory;
    private WAppsAction m_actionZoom;

    /**
     *  Create Confirmation Panel with OK Button.
     */
    public WConfirmPanel()
    {
        this (false, false, false, false, false, false, true);
    }   //  ConfirmPanel


    /**
     *  Create Confirmation Panel with OK and Cancel Button.
     *  @param withCancelButton with cancel
     */
    public WConfirmPanel(boolean withCancelButton)
    {
        this(withCancelButton, false, false, false, false, false, true);
    }

    /**
     *  Create Confirmation Panel with different buttons.
     *  @param withCancelButton     with cancel
     *  @param withRefreshButton    with refresh
     *  @param withResetButton      with reset
     *  @param withCustomizeButton  with customize
     *  @param withHistoryButton    with history
     *  @param withZoomButton       with zoom
     *  @param withText             with tool tip text
     */
    public WConfirmPanel(boolean withCancelButton,
        boolean withRefreshButton,
        boolean withResetButton,
        boolean withCustomizeButton,
        boolean withHistoryButton,
        boolean withZoomButton,
        boolean withText)
    {
        Panel pnlOkCancel = new Panel();
        Button button;

        try
        {

            this.setAlign("right");

            // Cancel
            m_actionCancel = createCancelButton(withText);
            Button btnCancel = m_actionCancel.getButton();
            setCancelVisible(btnCancel, withCancelButton);
            pnlOkCancel.appendChild(btnCancel);

            // OK
            m_actionOk = createOKButton(withText);
            Button btnOk = m_actionOk.getButton();
            pnlOkCancel.appendChild(btnOk);

            this.appendChild(pnlOkCancel);

            //
            if (withRefreshButton)
            {
                m_actionRefresh = createRefreshButton(withText);
                Button btnRefresh = m_actionRefresh.getButton();
                this.appendChild(btnRefresh);
            }
            if (withResetButton)
            {
                m_actionReset = createResetButton(withText);
                Button btnReset = m_actionReset.getButton();
                this.appendChild(btnReset);
            }
            if (withCustomizeButton)
            {
                m_actionCustomize = createCustomizeButton(withText);
                Button btnCustomize = m_actionCustomize.getButton();
                this.appendChild(btnCustomize);
            }
            if (withHistoryButton)
            {
                m_actionHistory = createHistoryButton(withText);
                Button btnHistory = m_actionHistory.getButton();
                this.appendChild(btnHistory);
            }
            if (withZoomButton)
            {
                m_actionZoom = createZoomButton(withText);
                Button btnZoom = m_actionZoom.getButton();
                this.appendChild(btnZoom);
            }
        }
        catch(Exception exception)
        {
            log.log(Level.WARNING, "Failed to correctly create Confirmation Panel:"
                    + exception.getMessage());
        }
    }   //  ConfirmPanel

    /**
     *  Create OK Button with Standard text.
     *  @param withText with text
     *  @return OK Button
     */
    public static final WAppsAction createOKButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createOKButton(Msg.getMsg(Env.getCtx(), A_OK));
        }
        return createOKButton("");
    }   //  createOKButton

    /**
     *  Create OK Button with label text and F4 Shortcut.
     *  @param text text
     *  @return OK Button
     */
    public static final WAppsAction createOKButton (String text) throws IOException
    {
        final String specialKey = "#f2";
        WAppsAction aa = new WAppsAction(A_OK, specialKey, text);
        return aa;
    }   //  createOKButton

    /**
     *  Create Cancel Button wlth label text and register ESC as KeyStroke.
     *  @param text text
     *  @return Cancel Button
     */
    public static final WAppsAction createCancelButton(String text) throws IOException
    {
        WAppsAction aa = new WAppsAction(A_CANCEL, null, text);
        return aa;
    }   //  createCancelButton

    /**
     *  Create Cancel Button wlth Standard text.
     *  @param withText with text
     *  @return Button
     */
    public static final WAppsAction createCancelButton(boolean withText) throws IOException
    {
        if (withText)
        {
            return createCancelButton(Msg.getMsg(Env.getCtx(), A_CANCEL));
        }
        return createCancelButton("");
    }   //  createCancelButton



    /**
     *  Create Refresh Button wlth label text and F5.
     *  @param text text
     *  @return button
     */
    public static final WAppsAction createRefreshButton (String text) throws IOException
    {
        final String specialKey = "#f5";

        WAppsAction aa = new WAppsAction(A_REFRESH, specialKey, text);

        return aa;
    }   //  createRefreshButton

    /**
     *  Create Refresh Button wlth Standard text.
     *  @param withText with text
     *  @return Button
     */
    public static final WAppsAction createRefreshButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createRefreshButton(Msg.getMsg(Env.getCtx(), A_REFRESH));
        }
        return createRefreshButton("");
    }   //  createRefreshButton


    /**
     *  Create Reset Button wlth label text.
     *  @param text text
     *  @return button
     */
    public static final WAppsAction createResetButton (String text) throws IOException
    {
        WAppsAction aa = new WAppsAction(A_RESET, null, text);

        return aa;
    }   //  createResetButton

    /**
     *  Create Reset Button wlth Standard text.
     *  @param withText with text
     *  @return Button
     */
    public static final WAppsAction createResetButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createResetButton(Msg.getMsg(Env.getCtx(), A_RESET));
        }
        return createResetButton(null);
    }   //  createRefreshButton

    /**
     *  Create Customize Button wlth label text.
     *  @param text text
     *  @return button
     */
    public static final WAppsAction createCustomizeButton (String text) throws IOException
    {
        WAppsAction aa = new WAppsAction(A_CUSTOMIZE, null, text);

        return aa;
    //  Env.getImageIcon("Preference24.gif"));
    }   //  createCustomizeButton

    /**
     *  Create Customize Button wlth Standard text.
     *  @param withText with text
     *  @return aa
     */
    public static final WAppsAction createCustomizeButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createCustomizeButton(Msg.getMsg(Env.getCtx(), A_CUSTOMIZE));
        }
        return createCustomizeButton(null);
    }   //  createCustomizeButton


    /**
     *  Create History Button wlth label text.
     *  @param text text
     *  @return aa
     */
    public static final WAppsAction createHistoryButton (String text) throws IOException
    {
        final String specialKey = "#f9";
        WAppsAction aa = new WAppsAction(A_HISTORY, specialKey, text);

        return aa;
    }   //  createHistoryButton

    /**
     *  Create History Button wlth Standard text.
     *  @param withText with text
     *  @return aa
     */
    public static final WAppsAction createHistoryButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createHistoryButton(Msg.getMsg(Env.getCtx(), A_HISTORY));
        }
        return createHistoryButton(null);
    }   //  createHistoryButton


    /**
     *  Create Zoom Button wlth label text.
     *  @param text text
     *  @return aa
     */
    public static final WAppsAction createZoomButton (String text) throws IOException
    {
        WAppsAction aa = new WAppsAction(A_ZOOM, null, text);

        return aa;
    }   //  createZoomButton

    /**
     *  Create Zoom Button wlth Standard text.
     *  @param withText with text
     *  @return aa
     */
    public static final WAppsAction createZoomButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createZoomButton(Msg.getMsg(Env.getCtx(), A_ZOOM));
        }
        return createZoomButton(null);
    }   //  createZoomButton


    /**
     *  Create Process Button wlth label text Shift-F4.
     *  @param text text
     *  @return aa
     */
    public static final WAppsAction createProcessButton (String text) throws IOException
    {
        // Shift-F4
        final String specialKey = "$#f4";
        WAppsAction aa = new WAppsAction(A_PROCESS, specialKey, text);

        return aa;
    }   //  createProcessButton

    /**
     *  Create Process Button wlth Standard text.
     *  @param withText with text
     *  @return aa
     */
    public static final WAppsAction createProcessButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createProcessButton(Msg.getMsg(Env.getCtx(), A_PROCESS));
        }
        return createProcessButton(null);
    }   //  createProcessButton


    /**
     *  Create Print Button wlth label text.
     *  @param text text
     *  @return aa
     */
    public static final WAppsAction createPrintButton (String text) throws IOException
    {
        final String specialKey = "#f12";
        WAppsAction aa = new WAppsAction(A_PRINT, specialKey, text);

        return aa;
    }   //  createPrintButton

    /**
     *  Create Print Button wlth Standard text.
     *  @param withText with text
     *  @return aa
     */
    public static final WAppsAction createPrintButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createPrintButton(Msg.getMsg(Env.getCtx(), A_PRINT));
        }
        return createPrintButton(null);
    }   //  createPrintButton


    /**
     *  Create Help Button wlth label text.
     *  @param text text
     *  @return aa
     */
    public static final WAppsAction createHelpButton (String text) throws IOException
    {
        final String specialKey = "#f1";
        WAppsAction aa = new WAppsAction(A_HELP, specialKey, text);

        return aa;
    }   //  createHelpButton

    /**
     *  Create Help Button wlth Standard text.
     *  @param withText with text
     *  @return aa
     */
    public static final WAppsAction createHelpButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createHelpButton(Msg.getMsg(Env.getCtx(), A_HELP));
        }
        return createHelpButton(null);
    }   //  createHelpButton


    /**
     *  Create Export Button wlth label text.
     *  @param text text
     *  @return aa
     */
    public static final WAppsAction createExportButton (String text) throws IOException
    {
        WAppsAction aa = new WAppsAction(A_EXPORT, null, text);

        return aa;
    }   //  createExportButton

    /**
     *  Create Export Button wlth Standard text.
     *  @param withText with text
     *  @return aa
     */
    public static final WAppsAction createExportButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createExportButton(Msg.getMsg(Env.getCtx(), A_EXPORT));
        }
        return createExportButton(null);
    }   //  createExportButton


    /************************
     *  Create Delete Button with label text - F3.
     *  @param text text
     *  @return Delete Button
     */
    public static final WAppsAction createDeleteButton (String text) throws IOException
    {
        final String specialKey = "#f3";
        WAppsAction aa = new WAppsAction(A_DELETE, specialKey, text);

        return aa;
    }   //  createDeleteButton

    /**
     *  Create Delete Button with Standard text.
     *  @param withText with text
     *  @return Delete Button
     */
    public static final WAppsAction createDeleteButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createDeleteButton(Msg.getMsg(Env.getCtx(), A_DELETE));
        }
        return createDeleteButton(null);
    }   //  createDeleteButton


    /************************
     *  Create Product Attribute Button with Standard text.
     *  @param withText with text
     *  @return Product Attribute Button
     */
    public static final WAppsAction createPAttributeButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createPAttributeButton(Msg.getMsg(Env.getCtx(), A_PATTRIBUTE));
        }
        return createPAttributeButton(null);
    }   //  createPAttributeButton

    /**
     *  Create Product Attribute Button with label text.
     *  @param text text
     *  @return Product Attribute Button
     */
    public static final WAppsAction createPAttributeButton (String text) throws IOException
    {
        WAppsAction aa = new WAppsAction(A_PATTRIBUTE, null, text);

        return aa;
    }   //  createPAttributeButton


    /**
     *  Create New Button with Standard text.
     *  @param withText with text
     *  @return New Button
     */
    public static final WAppsAction createNewButton (boolean withText) throws IOException
    {
        if (withText)
        {
            return createNewButton(Msg.getMsg(Env.getCtx(), A_NEW));
        }
        return createNewButton(null);
    }   //  createNewButton

    /**
     *  Create New Button with label text - F2.
     *  @param text text
     *  @return Product Attribute Button
     */
    public static final WAppsAction createNewButton (String text) throws IOException
    {
        final String specialKey = "#f2";
        WAppsAction aa = new WAppsAction(A_NEW, specialKey, text);

        return aa;
    }   //  createNewButton

    /**
     *  Show Cancel button.
     *  @param value trie for visible
     */
    public void setCancelVisible (Button button, boolean value)
    {
        button.setVisible(value);
        button.setEnabled(value);
    }

    /**
     *  Add Event Listener.
     *  <code>
     *  if (e.getActionCommand().equals(ConfirmPanel.A_OK))
     *  </code>
     *  In order to respond to keystrokes, the <code>EventListener</code>
     *  should be a <code>Window</code>. If the listener is not a window
     *  the panel will only respond to <code>onClick</code> events.
     *
     *  @param listener the event listener
     */
    public void addEventListener(EventListener listener)
    {
        String ctrlKeys = "";
        try
        {
            m_actionOk.setDelegate(listener);
            ctrlKeys += m_actionOk.getCtrlKeys();
            m_actionCancel.setDelegate(listener);
            ctrlKeys += m_actionCancel.getCtrlKeys();
            //
            if (m_actionRefresh != null)
            {
                m_actionRefresh.setDelegate(listener);
                ctrlKeys += m_actionRefresh.getCtrlKeys();
            }
            if (m_actionReset != null)
            {
                m_actionReset.setDelegate(listener);
                ctrlKeys += m_actionReset.getCtrlKeys();
            }
            if (m_actionCustomize != null)
            {
                m_actionCustomize.setDelegate(listener);
                ctrlKeys += m_actionCustomize.getCtrlKeys();
            }
            if (m_actionHistory != null)
            {
                m_actionHistory.setDelegate(listener);
                ctrlKeys += m_actionHistory.getCtrlKeys();
            }
            if (m_actionZoom != null)
            {
                m_actionZoom.setDelegate(listener);
                ctrlKeys += m_actionZoom.getCtrlKeys();
            }
        }
        catch (IllegalArgumentException exception)
        {
            log.warning(exception.getMessage());
        }

        // Set OK as default Button
        // and Cancel as cancel button
        if (listener instanceof Window)
        {
            ((Window) listener).addEventListener(Events.ON_CANCEL, m_actionCancel);
            ((Window) listener).addEventListener(Events.ON_OK, m_actionOk);
            // TODO enable Ctrl Keys
            //((Window) listener).setCtrlKeys(ctrlKeys);
            //((Window) listener).addEventListener(Events.ON_CTRL_KEY, this);
        }
        else
        {
            log.warning("Functionality of the Confirmation Panel has been restricted. "
                    + " as a result of the specified listener not being a Window. "
                    + "Consequently, it is unable to respond to keystrokes");
        }
    }   //  addActionListener

    /* (non-Javadoc)
     * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
     */
    public void onEvent(Event event) throws Exception
    {
        // TODO Auto-generated method stub

    }



}
