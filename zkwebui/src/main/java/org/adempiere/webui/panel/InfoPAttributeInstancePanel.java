/******************************************************************************
 * Copyright (C) 2008 Elaine Tan                                              *
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
package org.adempiere.webui.panel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.compiere.apps.search.PAttributeInstance;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;

/**
 * Display Product Attribute Instance Info
 * This class is based on org.compiere.apps.search.PAttributeInstance written by Jorg Janke
 * @author Elaine
 *
 */
public class InfoPAttributeInstancePanel extends Window implements EventListener, WTableModelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2883260173499157121L;

	/**
	 * 	Constructor
	 * 	@param parent frame parent
	 * 	@param title title
	 * 	@param M_Warehouse_ID warehouse key name pair
	 * 	@param M_Locator_ID locator
	 * 	@param M_Product_ID product key name pair
	 * 	@param C_BPartner_ID bp
	 */
	public InfoPAttributeInstancePanel(Window parent, String title,
		int M_Warehouse_ID, int M_Locator_ID, int M_Product_ID, int C_BPartner_ID)
	{
		super();
		setTitle(Msg.getMsg(Env.getCtx(), "PAttributeInstance") + ": " + title);
		
		init (M_Warehouse_ID, M_Locator_ID, M_Product_ID, C_BPartner_ID, parent);
		AEnv.showCenterWindow(parent, this);
	}	//	PAttributeInstance

	/**
	 * 	Initialization
	 *	@param M_Warehouse_ID wh
	 *	@param M_Locator_ID loc
	 *	@param M_Product_ID product
	 *	@param C_BPartner_ID partner
	 */
	private void init (int M_Warehouse_ID, int M_Locator_ID, int M_Product_ID, int C_BPartner_ID, Window window)
	{
		log.info("M_Warehouse_ID=" + M_Warehouse_ID 
			+ ", M_Locator_ID=" + M_Locator_ID
			+ ", M_Product_ID=" + M_Product_ID);
		m_M_Warehouse_ID = M_Warehouse_ID;
		m_M_Locator_ID = M_Locator_ID;
		m_M_Product_ID = M_Product_ID;
		m_window = window;
		try
		{
			jbInit();
			dynInit(C_BPartner_ID);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	// init	

	private ConfirmPanel confirmPanel = new ConfirmPanel (true);
	private Checkbox showAll = new Checkbox();
	//
	private WListbox 			m_table = new WListbox();
	//	Parameter
	private int			 		m_M_Warehouse_ID;
	private int			 		m_M_Locator_ID;
	private int			 		m_M_Product_ID;
	//
	private int					m_M_AttributeSetInstance_ID = -1;
	private String				m_M_AttributeSetInstanceName = null;
	private String				m_sql;
	private boolean 			m_wasCancelled;
	private Window				m_window;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(PAttributeInstance.class);

	/**
	 * 	Static Init
	 * 	@throws Exception
	 */
	private void jbInit() throws Exception
	{
        showAll.setText(Msg.getMsg(Env.getCtx(), "ShowAll"));
        showAll.addActionListener(this);
        showAll.setAttribute("zk_component_ID", "Lookup_Criteria_showAll");        
        m_table.setAttribute("zk_component_ID", "Lookup_Data_ASIResults");        

        
		Borderlayout borderlayout = new Borderlayout();
		
		//
		//this.doModal();
		setAttribute(Window.MODE_MODAL, Boolean.TRUE);
		//setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
		setBorder("normal");
		setClosable(true);
		this.setContentStyle("overflow: auto");
        this.setSizable(true);      
        this.setMaximizable(true);
		//
        //  As a modal window, the panel can't extend past the parent
 		this.setWidth("100%");
		this.setHeight("100%");
		this.setMaximized(true);
		//
        borderlayout.setWidth("100%");
        borderlayout.setHeight("100%");
        borderlayout.setStyle("border: none; position: relative");
        this.appendChild(borderlayout);
        
        North north = new North();
        borderlayout.appendChild(north);
        Div div = new Div();
        div.setAlign("right");
        div.appendChild(showAll);
        north.appendChild(div);
        
        Center center = new Center();
        center.setAutoscroll(true);
        center.setFlex(true);
		borderlayout.appendChild(center);
		center.appendChild(m_table);
		
		South south = new South();
		borderlayout.appendChild(south);
		south.appendChild(confirmPanel);
		
		//	ConfirmPanel
		confirmPanel.addActionListener(this);		
	}	//	jbInit

	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] 
	{
		new ColumnInfo(" ", "asi.M_AttributeSetInstance_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "asi.Description", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Lot"), "asi.Lot", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "SerNo"), "asi.SerNo", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "GuaranteeDate"), "asi.GuaranteeDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "M_Locator_ID"), "l.Value", KeyNamePair.class, "s.M_Locator_ID"),
//		new ColumnInfo(Msg.translate(Env.getCtx(), "M_Product_ID"), "p.Value", KeyNamePair.class, "p.M_Product_ID"), // @Trifon - Not sure if this need to be shown
//		new ColumnInfo(Msg.translate(Env.getCtx(), "M_AttributeSet_ID"), "st.Name", KeyNamePair.class, "st.M_AttributeSet_ID"), // @Trifon - Not sure if this need to be shown
		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "s.QtyOnHand", Double.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "s.QtyReserved", Double.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOrdered"), "s.QtyOrdered", Double.class),
		//	See RV_Storage
		new ColumnInfo(Msg.translate(Env.getCtx(), "GoodForDays"), "(daysbetween(asi.GuaranteeDate, SYSDATE))-p.GuaranteeDaysMin", Integer.class, true, true, null),
		new ColumnInfo(Msg.translate(Env.getCtx(), "ShelfLifeDays"), "daysbetween(asi.GuaranteeDate, SYSDATE)", Integer.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "ShelfLifeRemainingPct"), "CASE WHEN p.GuaranteeDays > 0 THEN TRUNC(((daysbetween(asi.GuaranteeDate, SYSDATE))/p.GuaranteeDays)*100) ELSE 0 END", Integer.class),
	};
	/**	From Clause							*/
	private static String s_sqlFrom = "M_AttributeSetInstance asi"
		+ " INNER JOIN M_AttributeSet st ON (st.M_AttributeSet_ID=asi.M_AttributeSet_ID )"
		+ " LEFT OUTER JOIN M_Storage s ON (s.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)"
		+ " LEFT OUTER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)"
		+ " LEFT OUTER JOIN M_Product p ON (s.M_Product_ID=p.M_Product_ID) "
	;
	//  To see all related Attribute Sets, add OR "
	//+                                   "(asi.M_AttributeSet_ID = p.M_AttributeSet_ID AND p.M_AttributeSetInstance_ID = 0)
	//  to the last join clause

	/** Where Clause						*/ 
	private static String s_sqlWhereWithoutWarehouse = " p.M_Product_ID=?";
	private static String s_sqlWhereSameWarehouse = " AND (l.M_Warehouse_ID=? OR 0=?)";

	private static String	s_sqlNonZero = " AND (s.QtyOnHand<>0 OR s.QtyReserved<>0 OR s.QtyOrdered<>0)";
	private static String	s_sqlMinLife = "";

	/**
	 * 	Dynamic Init
	 * 	@param C_BPartner_ID BP
	 */
	private void dynInit(int C_BPartner_ID)
	{
		log.config("C_BPartner_ID=" + C_BPartner_ID);
		if (C_BPartner_ID != 0)
		{
			int ShelfLifeMinPct = 0;
			int ShelfLifeMinDays = 0;
			String sql = "SELECT bp.ShelfLifeMinPct, bpp.ShelfLifeMinPct, bpp.ShelfLifeMinDays "
				+ "FROM C_BPartner bp "
				+ " LEFT OUTER JOIN C_BPartner_Product bpp"
				+	" ON (bp.C_BPartner_ID=bpp.C_BPartner_ID AND bpp.M_Product_ID=?) "
				+ "WHERE bp.C_BPartner_ID=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, m_M_Product_ID);
				pstmt.setInt(2, C_BPartner_ID);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					ShelfLifeMinPct = rs.getInt(1);		//	BP
					int pct = rs.getInt(2);				//	BP_P
					if (pct > 0)	//	overwrite
						ShelfLifeMinDays = pct;
					ShelfLifeMinDays = rs.getInt(3);
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
			if (ShelfLifeMinPct > 0)
			{
				s_sqlMinLife = " AND COALESCE(TRUNC(((daysbetween(asi.GuaranteeDate, SYSDATE))/p.GuaranteeDays)*100),0)>=" + ShelfLifeMinPct;
				log.config( "PAttributeInstance.dynInit - ShelfLifeMinPct=" + ShelfLifeMinPct);
			}
			if (ShelfLifeMinDays > 0)
			{
				s_sqlMinLife += " AND COALESCE((daysbetween(asi.GuaranteeDate, SYSDATE)),0)>=" + ShelfLifeMinDays;
				log.config( "PAttributeInstance.dynInit - ShelfLifeMinDays=" + ShelfLifeMinDays);
			}
		}	//	BPartner != 0

		m_sql = m_table.prepareTable (s_layout, s_sqlFrom, s_sqlWhereWithoutWarehouse, false, "asi")
		+ " ORDER BY asi.GuaranteeDate, s.QtyOnHand";	//	oldest, smallest first
		//
		m_table.setMultiSelection(false);
		m_table.getModel().addTableModelListener(this);
		//
		refresh();
	}	//	dynInit

	/**
	 * 	Refresh Query
	 */
	private void refresh()
	{
		String sql = m_sql;
		int pos = m_sql.lastIndexOf(" ORDER BY ");
		if (!showAll.isSelected())
		{
			sql = m_sql.substring(0, pos) + s_sqlWhereSameWarehouse + s_sqlNonZero;
			if (s_sqlMinLife.length() > 0)
				sql += s_sqlMinLife;
			sql += m_sql.substring(pos);
		}
		//
		log.finest(sql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_M_Product_ID);
			if ( !showAll.isSelected() ) {
				pstmt.setInt(2, m_M_Warehouse_ID);
				pstmt.setInt(3, m_M_Warehouse_ID);
			}

			rs = pstmt.executeQuery();
			m_table.loadTable(rs);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		enableButtons();
	}	//	refresh

	/**
	 * 	Action Listener
	 *	@param e event 
	 */
	public void onEvent(Event e) throws Exception 
	{
		if (e.getTarget().getId().equals(ConfirmPanel.A_OK))
		{
			dispose();
			m_wasCancelled = false;
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
			m_M_AttributeSetInstance_ID = -1;
			m_M_AttributeSetInstanceName = null;
			m_wasCancelled = true;
		}
		else if (e.getTarget() == showAll)
		{
			refresh();			
		}
	}


 
	/**
	 * 	Table selection changed
	 *	@param e event
	 */
	public void tableChanged(WTableModelEvent e) 
	{
		enableButtons();
	}

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	private void enableButtons()
	{
		m_M_AttributeSetInstance_ID = -1;
		m_M_AttributeSetInstanceName = null;
		m_M_Locator_ID = 0;
		int row = m_table.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = m_table.getSelectedRowKey();
			if (ID != null)
			{
				m_M_AttributeSetInstance_ID = ID.intValue();
				m_M_AttributeSetInstanceName = (String)m_table.getValueAt(row, 1);
				//
				Object oo = m_table.getValueAt(row, 5);
				if (oo instanceof KeyNamePair)
				{
					KeyNamePair pp = (KeyNamePair)oo;
					m_M_Locator_ID = pp.getKey();
				}
			}
		}
		confirmPanel.getOKButton().setEnabled(enabled);
		log.fine("M_AttributeSetInstance_ID=" + m_M_AttributeSetInstance_ID 
			+ " - " + m_M_AttributeSetInstanceName
			+ "; M_Locator_ID=" + m_M_Locator_ID);
	}	//	enableButtons

	/**
	 * 	Get Attribute Set Instance
	 *	@return M_AttributeSetInstance_ID or -1
	 */
	public int getM_AttributeSetInstance_ID()
	{
		return m_M_AttributeSetInstance_ID;
	}	//	getM_AttributeSetInstance_ID

	/**
	 * 	Get Instance Name
	 * 	@return Instance Name
	 */
	public String getM_AttributeSetInstanceName()
	{
		return m_M_AttributeSetInstanceName;
	}	//	getM_AttributeSetInstanceName

	/**
	 * 	Get Locator
	 *	@return M_Locator_ID or 0
	 */
	public int getM_Locator_ID()
	{
		return m_M_Locator_ID;
	}	//	getM_Locator_ID

	/**
	 * 	Was Cancelled?
	 *	@return true if cancelled
	 */
	public boolean wasCancelled()
	{
		return m_wasCancelled;
	}

}	//	PAttributeInstance
