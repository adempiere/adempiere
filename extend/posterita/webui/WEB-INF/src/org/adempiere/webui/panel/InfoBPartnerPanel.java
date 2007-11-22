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

package org.adempiere.webui.panel;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Vbox;

/**
*	Search Business Partner and return selection
*   Based on InfoBPartner written by Jorg Janke
* 	@author Sendy Yagambrum 	
*/


public class InfoBPartnerPanel extends InfoPanel implements EventListener, WTableModelListener
{
	
	private static final long serialVersionUID = 1L;	
	
	private Label lblValue ;
	private Textbox fieldValue ;
	private Label lblName;
	private Textbox fieldName ;
	private Label lblContact ;
	private Textbox fieldContact;
	private Label lblEMail ;
	private Textbox fieldEMail;
	private Label lblPostal;
	private Intbox fieldPostal;
	private Label lblPhone;
	private Intbox fieldPhone;
	private Checkbox checkAND ;
	private Checkbox checkCustomer; 
	private Checkbox checkVendor;
        		
    private int m_C_BPartner_Location_ID_index = -1;
		
	/** SalesOrder Trx          */
	private boolean 		m_isSOTrx;
		
	/**	Logger			*/
	protected CLogger log = CLogger.getCLogger(getClass());
	
	/** From Clause             */
	private static String s_partnerFROM = "C_BPartner"
		+ " LEFT OUTER JOIN C_BPartner_Location l ON (C_BPartner.C_BPartner_ID=l.C_BPartner_ID AND l.IsActive='Y')"
		+ " LEFT OUTER JOIN AD_User c ON (C_BPartner.C_BPartner_ID=c.C_BPartner_ID AND (c.C_BPartner_Location_ID IS NULL OR c.C_BPartner_Location_ID=l.C_BPartner_Location_ID) AND c.IsActive='Y')" 
		+ " LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)";
	
	/**  Array of Column Info    */
	private static ColumnInfo[] s_partnerLayout = {
		new ColumnInfo(" ", "C_BPartner.C_BPartner_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Value"), "C_BPartner.Value", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "C_BPartner.Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Contact"), "c.Name AS Contact", KeyNamePair.class, "c.AD_User_ID"),
		new ColumnInfo(Msg.translate(Env.getCtx(), "SO_CreditAvailable"), "C_BPartner.SO_CreditLimit-C_BPartner.SO_CreditUsed AS SO_CreditAvailable", BigDecimal.class, true, true, null),
		new ColumnInfo(Msg.translate(Env.getCtx(), "SO_CreditUsed"), "C_BPartner.SO_CreditUsed", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone"), "c.Phone", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Postal"), "a.Postal", KeyNamePair.class, "l.C_BPartner_Location_ID"),
		new ColumnInfo(Msg.translate(Env.getCtx(), "City"), "a.City", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "TotalOpenBalance"), "C_BPartner.TotalOpenBalance", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Revenue"), "C_BPartner.ActualLifetimeValue", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Address1"), "a.Address1", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsShipTo"), "l.IsShipTo", Boolean.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsBillTo"), "l.IsBillTo", Boolean.class)
	};

	/**
	 *	Standard Constructor
	 *  @param  queryvalue   Query value Name or Value if contains numbers
	 *  @param isSOTrx  if false, query vendors only
	 *  @param whereClause where clause
	 */
	public InfoBPartnerPanel(String queryValue,int windowNo, boolean isSOTrx,boolean multipleSelection, String whereClause)
	{
		
		super (windowNo, "C_BPartner", "C_BPartner_ID",multipleSelection, whereClause);
		m_isSOTrx = isSOTrx;
        initComponents();
        init();
		initInfo(queryValue, whereClause);
        
        int no = contentPanel.getRowCount();
        setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
        setStatusDB(Integer.toString(no));
        //
		if (queryValue != null && queryValue.length()>0)
		{
			 executeQuery();
             renderItems();
        }
			
	}
	
	private void initComponents()
	{
		lblValue = new Label();
		lblValue.setValue(Msg.translate(Env.getCtx(), "Value").substring(1));
		lblName = new Label();
		lblName.setValue(Msg.translate(Env.getCtx(), "Name").substring(1));
		lblContact = new Label();
		lblContact.setValue(Msg.translate(Env.getCtx(), "Contact"));
		lblEMail = new Label();
		lblEMail.setValue(Msg.getMsg(Env.getCtx(), "EMail"));
		lblPostal = new Label();
		lblPostal.setValue(Msg.getMsg(Env.getCtx(), "Postal"));
		lblPhone = new Label();
		lblPhone.setValue(Msg.translate(Env.getCtx(), "Phone"));
		
		fieldValue = new Textbox();
		fieldValue.setMaxlength(40);
		fieldName = new Textbox();
		fieldName.setMaxlength(40);
		fieldContact = new Textbox();
		fieldContact.setMaxlength(40);
		fieldEMail = new Textbox();
		fieldEMail.setMaxlength(40);
		fieldPostal = new Intbox();
		fieldPostal.setMaxlength(40);
		fieldPhone = new Intbox();
		fieldPhone.setMaxlength(40);
		
		checkAND = new Checkbox();
		checkAND.addEventListener(Events.ON_CHECK, this);
		checkAND.setChecked(true);
		checkCustomer = new Checkbox();
		checkCustomer.setChecked(true);
		checkCustomer.addEventListener(Events.ON_CHECK, this);
		checkVendor = new Checkbox();
		checkVendor.setChecked(true);
		checkVendor.addEventListener(Events.ON_CHECK, this);
        
		contentPanel = new WListbox();
        contentPanel.setWidth("100%");
        contentPanel.setHeight("400px");
        contentPanel.setStyle("overflow:auto");
        
	}
	
	private void init()
	{
		
		Panel pnlValue = new Panel();
		pnlValue.appendChild(lblValue);
		pnlValue.appendChild(fieldValue);		
		pnlValue.setAlign("right");
		
		Panel pnlName = new Panel();
		pnlName.appendChild(lblName);
		pnlName.appendChild(fieldName);
		pnlName.setAlign("right");
		
		Panel pnlContact = new Panel();
		pnlContact.appendChild(lblContact);
		pnlContact.appendChild(fieldContact);
		pnlContact.setAlign("right");
		
		Panel pnlEMail = new Panel();
		pnlEMail.appendChild(lblEMail);
		pnlEMail.appendChild(fieldEMail);
		pnlEMail.setAlign("right");
		
		Panel pnlPostal = new Panel();
		pnlPostal.appendChild(lblPostal);
		pnlPostal.appendChild(fieldPostal);
		pnlPostal.setAlign("right");
		
		Panel pnlPhone = new Panel();
		pnlPhone.appendChild(lblPhone);
		pnlPhone.appendChild(fieldPhone);
		pnlPhone.setAlign("right");
		
		Panel pnlCheckAND = new Panel();
		Label lblAND = new Label();
		lblAND.setValue("All/Any");
		pnlCheckAND.appendChild(checkAND);
		pnlCheckAND.appendChild(lblAND);
		pnlCheckAND.setAlign("left");
				
		Panel pnlCheckCust = new Panel();
		Label lblCheckCust = new Label();
		lblCheckCust.setValue("Customers Only");
		pnlCheckCust.appendChild(checkCustomer);
		pnlCheckCust.appendChild(lblCheckCust);
		pnlCheckCust.setAlign("right");
		
		Panel pnlCheckVendor = new Panel();
		Label lblCheckVendor = new Label();
		lblCheckVendor.setValue("Vendors Only");
		pnlCheckVendor.appendChild(checkVendor);
		pnlCheckVendor.appendChild(lblCheckVendor);
		pnlCheckVendor.setAlign("right");
						
		Vbox vbox1 = new Vbox();
		vbox1.appendChild(pnlValue);
		vbox1.appendChild(pnlName);
		
		Vbox vbox2 = new Vbox();
		vbox2.appendChild(pnlContact);
		vbox2.appendChild(pnlEMail);
		
		Vbox vbox3 = new Vbox();
		vbox3.appendChild(pnlPostal);
		vbox3.appendChild(pnlPhone);
		
		Vbox vbox4 = new Vbox();
		vbox4.appendChild(pnlCheckAND);
		
		if (m_isSOTrx)
		{
			vbox4.appendChild(pnlCheckCust);
		}
		else
		{
			vbox4.appendChild(pnlCheckVendor);
		}
		
		Hbox parameterPanel = new Hbox();
        parameterPanel.appendChild(vbox1);
        parameterPanel.appendChild(vbox2);
        parameterPanel.appendChild(vbox3);
        parameterPanel.appendChild(vbox4);
	
        Vbox mainPanel = new Vbox();
        mainPanel.appendChild(parameterPanel);
        
/*        Div div = new Div();
        div.setStyle("overflow:hidden");
        div.setWidth("1000px");
        div.appendChild(contentPanel);
        mainPanel.appendChild(div);*/

        mainPanel.setWidth("100%");
        mainPanel.appendChild(contentPanel);
        mainPanel.appendChild(confirmPanel);
        mainPanel.appendChild(statusBar);
        
        this.appendChild(mainPanel);
        
		this.setBorder("normal");
		this.setWidth("1000px");
		
	}	
	
	/**
	 *	Dynamic Init
	 *  @param value value
	 *  @param whereClause where clause
	 */
		
	private void initInfo(String value, String whereClause)
	{
			/**	From
				C_BPartner
				 LEFT OUTER JOIN C_BPartner_Location l ON (C_BPartner.C_BPartner_ID=l.C_BPartner_ID AND l.IsActive='Y') 
				 LEFT OUTER JOIN AD_User c ON (C_BPartner.C_BPartner_ID=c.C_BPartner_ID AND (c.C_BPartner_Location_ID IS NULL OR c.C_BPartner_Location_ID=l.C_BPartner_Location_ID) AND c.IsActive='Y') 
				 LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)
			**/

			//	Create Grid
			StringBuffer where = new StringBuffer();
			where.append("C_BPartner.IsSummary='N' AND C_BPartner.IsActive='Y'");
			if (whereClause != null && whereClause.length() > 0)
				where.append(" AND ").append(whereClause);
			//
                          
			prepareTable(s_partnerLayout, s_partnerFROM, where.toString(), "C_BPartner.Value");
			
//        Get indexes
            for (int i = 0; i < p_layout.length; i++)
            {
                if (p_layout[i].getKeyPairColSQL().indexOf("C_BPartner_Location_ID") != -1)
                    m_C_BPartner_Location_ID_index = i;
            }
            //  Set Value
			if (value == null)
				value = "%";
			if (!value.endsWith("%"))
				value += "%";

			//	Put query string in Name if not numeric
			if (value.equals("%"))
				fieldName.setText(value);
			//	No Numbers entered
			else if ((value.indexOf('0')+value.indexOf('1')+value.indexOf('2')+value.indexOf('3')+value.indexOf('4') +value.indexOf('5')
				+value.indexOf('6')+value.indexOf('7')+value.indexOf('8')+value.indexOf('9')) == -10)
			{
				if (value.startsWith("%"))
					fieldName.setText(value);
				else
					fieldName.setText("%" + value);
			}
			//	Number entered
			else
				fieldValue.setText(value);
	}	//	initInfo

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt pstmt
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	public void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
		//	=> Value
		String value = fieldValue.getText().toUpperCase();
		if (!(value.equals("") || value.equals("%")))
		{
			if (!value.endsWith("%"))
				value += "%";
			pstmt.setString(index++, value);
			log.fine("Value: " + value);
		}
		//	=> Name
		String name = fieldName.getText().toUpperCase();
		if (!(name.equals("") || name.equals("%")))
		{
			if (!name.endsWith("%"))
				name += "%";
			pstmt.setString(index++, name);
			log.fine("Name: " + name);
		}
		//	=> Contact
		String contact = fieldContact.getText().toUpperCase();
		if (!(contact.equals("") || contact.equals("%")))
		{
			if (!contact.endsWith("%"))
				contact += "%";
			pstmt.setString(index++, contact);
			log.fine("Contact: " + contact);
		}
		//	=> EMail
		String email = fieldEMail.getText().toUpperCase();
		if (!(email.equals("") || email.equals("%")))
		{
			if (!email.endsWith("%"))
				email += "%";
			pstmt.setString(index++, email);
			log.fine("EMail: " + email);
		}
		//	=> Phone
		String phone = fieldPhone.getText().toUpperCase();
		if (!(phone.equals("") || phone.equals("%")))
		{
			if (!phone.endsWith("%"))
				phone += "%";
			pstmt.setString(index++, phone);
			log.fine("Phone: " + phone);
		}
		//	=> Postal
		String postal = fieldPostal.getText().toUpperCase();
		if (!(postal.equals("") || postal.equals("%")))
		{
			if (!postal.endsWith("%"))
				postal += "%";
			pstmt.setString(index++, postal);
			log.fine("Postal: " + postal);
		}
	}   //  setParameters

	/*************************************************************************/
	/*************************************************************************/
	/**
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return WHERE clause
	 */
	public String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();
		//	=> Value
		String value = fieldValue.getText().toUpperCase();
		if (!(value.equals("") || value.equals("%")))
			list.add ("UPPER(C_BPartner.Value) LIKE ?");
		//	=> Name
		String name = fieldName.getText().toUpperCase();
		if (!(name.equals("") || name.equals("%")))
			list.add ("UPPER(C_BPartner.Name) LIKE ?");
		//	=> Contact
		String contact = fieldContact.getText().toUpperCase();
		if (!(contact.equals("") || contact.equals("%")))
			list.add ("UPPER(c.Name) LIKE ?");
		//	=> EMail
		String email = fieldEMail.getText().toUpperCase();
		if (!(email.equals("") || email.equals("%")))
			list.add ("UPPER(c.EMail) LIKE ?");
		//	=> Phone
		String phone = fieldPhone.getText().toUpperCase();
		if (!(phone.equals("") || phone.equals("%")))
			list.add ("UPPER(c.Phone) LIKE ?");
		//	=> Postal
		String postal = fieldPostal.getText().toUpperCase();
		if (!(postal.equals("") || postal.equals("%")))
			list.add ("UPPER(a.Postal) LIKE ?");
		StringBuffer sql = new StringBuffer();
		int size = list.size();
		//	Just one
		if (size == 1)
			sql.append(" AND ").append(list.get(0));
		else if (size > 1)
		{
			boolean AND = checkAND.isChecked();
			sql.append(" AND ");
			if (!AND)
				sql.append("(");
			for (int i = 0; i < size; i++)
			{
				if (i > 0)
					sql.append(AND ? " AND " : " OR ");
				sql.append(list.get(i));
			}
			if (!AND)
				sql.append(")");
		}
			//	Static SQL
		if (checkCustomer.isChecked())
		{
			sql.append(" AND ");
			if (m_isSOTrx)
				sql.append ("C_BPartner.IsCustomer='Y'");
			else
				sql.append ("C_BPartner.IsVendor='Y'");
		}
		return sql.toString();

	}	//	getSQLWhere
    
    /*************************************************************************/

    /**
     *  Save Selection Details
     *  Get Location/Partner Info
     */
    public void saveSelectionDetail()
    {
        int row = contentPanel.getSelectedRow();
        if (row == -1)
            return;

        int AD_User_ID = 0;
        int C_BPartner_Location_ID = 0;

        
        AD_User_ID = ((KeyNamePair)contentPanel.getValueAt(contentPanel.getSelectedIndex(), 3)).getKey();
        //AD_User_ID = contentPanel.getSelectedRowKey();
       
        if (m_C_BPartner_Location_ID_index != -1)
        {
            Object data =contentPanel.getValueAt(row, m_C_BPartner_Location_ID_index);
            if (data instanceof KeyNamePair)
                C_BPartner_Location_ID = ((KeyNamePair)data).getKey();
        }
        //  publish for Callout to read
        Integer ID = getSelectedRowKey();
        Env.setContext(Env.getCtx(), Env.WINDOW_INFO, Env.TAB_INFO, "C_BPartner_ID", ID == null ? "0" : ID.toString());
        Env.setContext(Env.getCtx(), Env.WINDOW_INFO, Env.TAB_INFO, "AD_User_ID", String.valueOf(AD_User_ID));
        Env.setContext(Env.getCtx(), Env.WINDOW_INFO, Env.TAB_INFO, "C_BPartner_Location_ID", String.valueOf(C_BPartner_Location_ID));
       
    }   //  saveSelectionDetail
		   
    public boolean isAsap()
	{
		return true;
	}

    public void tableChanged(WTableModelEvent event)
    {
        
    }
	
}
