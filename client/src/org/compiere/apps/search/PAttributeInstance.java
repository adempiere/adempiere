/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.apps.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.ConfirmPanel;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;


/**
 *	Display Product Attribute Instance Info
 *
 *  @author     Jorg Janke
 *  @version    $Id: PAttributeInstance.java,v 1.3 2006/07/30 00:51:27 jjanke Exp $
 *  
 *  @author		Michael McKay
 *  				<li>release/380 clean up size code
 */
public class PAttributeInstance extends CDialog 
	implements ListSelectionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3743466565716139916L;

	/**
	 * 	Constructor
	 * 	@param parent frame parent
	 * 	@param title title
	 * 	@param M_Warehouse_ID warehouse key name pair
	 * 	@param M_Locator_ID locator
	 * 	@param M_Product_ID product key name pair
	 * 	@param C_BPartner_ID bp
	 */
	public PAttributeInstance(JFrame parent, String title,
		int M_Warehouse_ID, int M_Locator_ID, int M_Product_ID, int C_BPartner_ID)
	{
		super (parent, Msg.getMsg(Env.getCtx(), "PAttributeInstance") + ": " + title, true);
		init (M_Warehouse_ID, M_Locator_ID, M_Product_ID, C_BPartner_ID);
		AEnv.showCenterWindow(parent, this);
	}
	
	/**
	 * 	Constructor
	 * 	@param parent dialog parent
	 * 	@param title title
	 * 	@param M_Warehouse_ID warehouse key name pair
	 * 	@param M_Locator_ID locator
	 * 	@param M_Product_ID product key name pair
	 * 	@param C_BPartner_ID bp
	 */
	public PAttributeInstance(JDialog parent, String title,
		int M_Warehouse_ID, int M_Locator_ID, int M_Product_ID, int C_BPartner_ID)
	{
		super (parent, Msg.getMsg(Env.getCtx(), "PAttributeInstance") + ": " + title, true);
		init (M_Warehouse_ID, M_Locator_ID, M_Product_ID, C_BPartner_ID);
		AEnv.showCenterWindow(parent, this);
	}

	/**
	 * 	Initialization
	 *	@param M_Warehouse_ID wh
	 *	@param M_Locator_ID loc
	 *	@param M_Product_ID product
	 *	@param C_BPartner_ID partner
	 */
	private void init (int M_Warehouse_ID, int M_Locator_ID, int M_Product_ID, int C_BPartner_ID)
	{
		log.info("M_Warehouse_ID=" + M_Warehouse_ID 
			+ ", M_Locator_ID=" + M_Locator_ID
			+ ", M_Product_ID=" + M_Product_ID);
		m_M_Warehouse_ID = M_Warehouse_ID;
		m_M_Locator_ID = M_Locator_ID;
		m_M_Product_ID = M_Product_ID;
		try
		{
			jbInit();
			dynInit(C_BPartner_ID);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}

	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel northPanel = new CPanel();
	private JScrollPane centerScrollPane = new JScrollPane();
	private ConfirmPanel confirmPanel = new ConfirmPanel (true);
	private CCheckBox showAll = new CCheckBox (Msg.getMsg(Env.getCtx(), "ShowAll"));
	//
	private MiniTable 			m_table = new MiniTable();
	//	Parameter
	private int			 		m_M_Warehouse_ID;
	private int			 		m_M_Locator_ID;
	private int			 		m_M_Product_ID;
	//
	private int					m_M_AttributeSetInstance_ID = -1;
	private String				m_M_AttributeSetInstanceName = null;
	private String				m_sql;
	private boolean 			m_wasCancelled;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(PAttributeInstance.class);

	/** Window Width                */
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screensize = toolkit.getScreenSize();

	protected final int        SCREEN_WIDTH = screensize.width > 1500 ? 1500 : screensize.width - 100;
	protected final int        SCREEN_HEIGHT = screensize.height > 600 ? 250 : 105;
	
	/**
	 * 	Static Init
	 * 	@throws Exception
	 */
	private void jbInit() throws Exception
	{
		mainPanel.setLayout(mainLayout);
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		//	North
		northPanel.setLayout(new ALayout());
		northPanel.add(showAll, new ALayoutConstraint(0,0));
		showAll.addActionListener(this);
		this.getContentPane().add(northPanel, BorderLayout.NORTH);
		//	Center
		mainPanel.add(centerScrollPane, BorderLayout.CENTER);
		centerScrollPane.getViewport().add(m_table, null);
		//	South
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		mainPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		confirmPanel.addActionListener(this);
	}

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
	private static String s_sqlWhereSameWarehouse = " AND (? in (0, l.M_Warehouse_ID))";

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
		m_table.setRowSelectionAllowed(true);
		m_table.setMultiSelection(false);
		m_table.addMouseListener(this);
		m_table.getSelectionModel().addListSelectionListener(this);
		//
		refresh();

		//  The minitable class overrides the Enter key if multi-selection is false
		m_table.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "doDispose");
		m_table.getActionMap().put("doDispose", doDispose);

	}

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
	}

	/**
	 *  Close the window
	 */
    private Action doDispose = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
			dispose();
        }
    };
    
	/**
	 * 	Action Listener
	 *	@param e event 
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			dispose();
			m_wasCancelled = false; 
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))	
		{
			dispose();
			m_M_AttributeSetInstance_ID = -1;
			m_M_AttributeSetInstanceName = null;
			m_wasCancelled = true; 
		}
		else if (e.getSource() == showAll)
		{
			refresh();
		}
	}
 
	/**
	 * 	Table selection changed
	 *	@param e event
	 */
	public void valueChanged (ListSelectionEvent e)	
	{
		m_M_AttributeSetInstance_ID = -1;
		m_M_AttributeSetInstanceName = null;
		m_M_Locator_ID = 0;

		int row = m_table.getSelectedRow();
		if (row > -1)
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
		log.fine("M_AttributeSetInstance_ID=" + m_M_AttributeSetInstance_ID 
			+ " - " + m_M_AttributeSetInstanceName
			+ "; M_Locator_ID=" + m_M_Locator_ID);

		enableButtons();
	}

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	private void enableButtons()
	{
		int row = m_table.getSelectedRow();
		boolean enabled = row > -1;
		confirmPanel.getOKButton().setEnabled(enabled);
	}

	/**
	 *  Mouse Clicked
	 *  @param e event
	 */
	public void mouseClicked(MouseEvent e)
	{
		//  Double click with selected row => exit
		if (e.getClickCount() > 1 && m_table.getSelectedRow() > -1)
		{
			dispose();
		}
	}


	/**
	 * 	Get Attribute Set Instance
	 *	@return M_AttributeSetInstance_ID or -1
	 */
	public int getM_AttributeSetInstance_ID()
	{
		return m_M_AttributeSetInstance_ID;
	}

	/**
	 * 	Get Instance Name
	 * 	@return Instance Name
	 */
	public String getM_AttributeSetInstanceName()
	{
		return m_M_AttributeSetInstanceName;
	}

	/**
	 * 	Get Locator
	 *	@return M_Locator_ID or 0
	 */
	public int getM_Locator_ID()
	{
		return m_M_Locator_ID;
	}

	/**
	 * 	Was Cancelled?
	 *	@return true if cancelled
	 */
	public boolean wasCancelled()
	{
		return m_wasCancelled;
	}

}