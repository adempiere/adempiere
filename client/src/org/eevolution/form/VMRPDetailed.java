/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.form;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.AWindow;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.apps.search.Info_Column;
import org.compiere.apps.search.PAttributeInstance;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPProductPlanning;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MUOM;
import org.compiere.model.MTable;
import org.compiere.model.MColumn;
import org.compiere.model.MWindow;
import org.compiere.model.MProduct;
import org.compiere.model.MResource;
import org.compiere.model.MWarehouse;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.swing.CLabel;
import org.compiere.swing.CButton;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.CLogger;
import org.compiere.util.Msg;

/**
 *	VMRPDetailed 
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: VMRPDetailed.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class VMRPDetailed extends CPanel implements FormPanel, ActionListener, VetoableChangeListener, ChangeListener, ListSelectionListener, TableModelListener, ASyncProcess
{
	   
    /** Creates new form VMRPDetailed */
    public VMRPDetailed() {
        initComponents();
    }
    
     /**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		m_WindowNo = WindowNo;
		m_frame = frame;
		Env.setContext(Env.getCtx(), m_WindowNo, "IsSOTrx", "N");

		try
		{
			//	UI
            statInit();
			fillPicks();
			jbInit();
                        //
			dynInit();
			m_frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			m_frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		}
		catch(Exception e)
		{
			Log.log(Level.SEVERE, "VMRPDetailed.init", e);
		}
		
		executeQuery();
		
	}	//	init
        
                 /**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;
    private StatusBar statusBar = new StatusBar();
    private int AD_Table_ID = MTable.getTable_ID("PP_MRP");    
    private MTable table = null;
    private int AD_Client_ID = Env.getContextAsInt(Env.getCtx(),"AD_Client_ID");
        
    private static CLogger Log = CLogger.getCLogger(VMRPDetailed.class);        
        /** Master (owning) Window  */
	protected int				p_WindowNo;
	/** Table Name              */
	private String              p_tableName = getTableName();
	/** Key Column Name         */
	protected String            p_keyColumn;
	/** Enable more than one selection  */
	protected boolean			p_multiSelection =  true;
	/** Initial WHERE Clause    */
	protected String			p_whereClause = "";

	/** Table                   */
	protected MiniTable         p_table = new MiniTable();
	/** Model Index of Key Column   */
	private int                 m_keyColumnIndex = -1;
	/** OK pressed                  */
	//private boolean			    m_ok = false;
	/** Cancel pressed - need to differentiate between OK - Cancel - Exit	*/
	private boolean			    m_cancel = false;
	/** Result IDs              */

	/** Layout of Grid          */
	protected Info_Column[]     p_layout;
	/** Main SQL Statement      */
	private String              m_sqlMain;
	/** Order By Clause         */
	private String              m_sqlAdd;

	/** Loading success indicator       */
	protected boolean	        p_loadedOK = false;
	/**	SO Zoom Window						*/
	//private int					m_SO_Window_ID = -1;
	/**	PO Zoom Window						*/
	//private int					m_PO_Window_ID = -1;


	/** Worker                  */
	private Worker              m_worker = null;

	/** Static Layout           */
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	ConfirmPanel confirmPanel = new ConfirmPanel(true, true, true, true, true, true, true);
	protected CPanel parameterPanel = new CPanel();
	private JScrollPane scrollPane = new JScrollPane();
	//
	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem calcMenu = new JMenuItem();
        
        	/** Window Width                */
	static final int        INFO_WIDTH = 800;

    private CLabel lProduct_ID = new CLabel(Msg.translate(Env.getCtx(), "M_Product_ID"));
	private VLookup fProduct_ID;
	private CLabel lAttrSetInstance_ID = new CLabel(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
	private CButton fAttrSetInstance_ID;
	private CLabel lResource_ID = new CLabel(Msg.translate(Env.getCtx(), "S_Resource_ID"));
	private VLookup fResource_ID;
	private CLabel lWarehouse_ID = new CLabel(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
	private VLookup fWarehouse_ID;
	private CLabel lPlanner_ID = new CLabel(Msg.translate(Env.getCtx(), "Planner_ID"));
	private VLookup fPlanner_ID;
        
	//
	private CLabel lDueStart = new CLabel(Msg.translate(Env.getCtx(), "DueDate"));
	
	//DueStart Field
	private VDate fDueStart = new VDate("DueStart", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom")) 
	{   			
		public void setValue(Object arg0) 
		{	   				
			super.setValue(arg0);
			executeQuery();
		};
	}; 


	private CLabel lDueEnd = new CLabel(Msg.translate(Env.getCtx(), "To"));
	//DueEnd Field
	private VDate fDueEnd = new VDate("DueEnd", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"))
	{   			
		public void setValue(Object arg0) 
		{
		   				
		   	super.setValue(arg0);
		   	executeQuery();
		};
	}; 
	

	private CLabel 	           lType = new CLabel();
	
	private CTextField         fType = new CTextField(6);
    private CLabel              lUOM = new CLabel();
	private CTextField          fUOM = new CTextField(5);
    private CLabel      lOrderPeriod = new CLabel();
	private CTextField  fOrderPeriod = new CTextField(5);
    private CLabel        lTimefence = new CLabel();
	private CTextField 	  fTimefence = new CTextField(5);
    private CLabel         lLeadtime = new CLabel();
	private CTextField     fLeadtime = new CTextField(5);
    private CLabel 	   lReplenishMin = new CLabel();
	private CTextField fReplenishMin = new CTextField(5);       
    private CLabel 			 lMinOrd = new CLabel();
	private CTextField 		 fMinOrd = new CTextField(5);
    private CLabel 			 lMaxOrd = new CLabel();
	private CTextField 		 fMaxOrd = new CTextField(5);
    private CLabel		    lOrdMult = new CLabel();
	private CTextField 		fOrdMult = new CTextField(5);
    private CLabel 		   lOrderQty = new CLabel();
	private CTextField 	   fOrderQty = new CTextField(5);
    private CLabel 			  lYield = new CLabel();
	private CTextField 		  fYield = new CTextField(5);
    private CLabel 			 lOnhand = new CLabel();
	private CTextField 		 fOnhand = new CTextField(5);
    private CLabel 		lSafetyStock = new CLabel();
	private CTextField 	fSafetyStock = new CTextField(5);
    private CLabel 			lOrdered = new CLabel();
	private CTextField 		fOrdered = new CTextField(5);
    private CLabel 		   lReserved = new CLabel();
	private CTextField 	   fReserved = new CTextField(5);
    private CLabel 		  lAvailable = new CLabel();
	private CTextField 	  fAvailable = new CTextField(5);
	
    private CLabel 		 lSupplyType = new CLabel(Msg.translate(Env.getCtx(), "TypeMRP"));
    private VLookup 	 fSupplyType = null;
    private VCheckBox 		 fMaster = new VCheckBox ("IsMPS", false, false, true, Msg.translate(Env.getCtx(), "IsMPS"), "", false);
    private VCheckBox 		 fMRPReq = new VCheckBox ("IsRequiredMRP", false, false, true, Msg.translate(Env.getCtx(), "IsRequiredMRP"), "", false);
    private VCheckBox 	 fCreatePlan = new VCheckBox ("IsCreatePlan", false, false, true, Msg.translate(Env.getCtx(), "IsCreatePlan"), "", false);
    private VCheckBox 		  fIssue = new VCheckBox ("IsIssue", false, false, true, Msg.translate(Env.getCtx(), "IsIssue"), "", false);
        
    /**  Array of Column Info    */
    private static final Info_Column[] m_layout = 
    {
		new Info_Column(" ", "PP_MRP.PP_MRP_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Value"), "(Select Value from M_Product p where p.M_Product_ID=PP_MRP.M_Product_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Name"), "(Select Name from M_Product p where p.M_Product_ID=PP_MRP.M_Product_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Resource"), "(Select Name from S_Resource sr where sr.S_Resource_ID=PP_MRP.S_Resource_ID)", String.class),	// 4L - BUG #59
		new Info_Column(Msg.translate(Env.getCtx(), "Warehouse"), "(Select Name from M_Warehouse wh where wh.M_Warehouse_ID=PP_MRP.M_Warehouse_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DatePromised"), "PP_MRP.DatePromised", Timestamp.class),
        new Info_Column(Msg.translate(Env.getCtx(), "Gross Reqs."), "(SELECT m.Qty FROM PP_MRP m WHERE m.Type='D' AND m.PP_MRP_ID=PP_MRP.PP_MRP_ID)",  BigDecimal.class),        
        new Info_Column(Msg.translate(Env.getCtx(), "Schedule Reciept."), "(SELECT m.Qty FROM PP_MRP m WHERE m.Type='S' AND m.DocStatus ='CO' AND m.PP_MRP_ID=PP_MRP.PP_MRP_ID)",  BigDecimal.class),
        new Info_Column(Msg.translate(Env.getCtx(), "Plan Orders"), "(SELECT m.Qty FROM PP_MRP m WHERE m.Type='S' AND m.DocStatus IN ('DR', 'IP') AND m.PP_MRP_ID=PP_MRP.PP_MRP_ID)",  BigDecimal.class),
        new Info_Column(Msg.translate(Env.getCtx(), "Proj QOH"), "bomQtyOnHand( PP_MRP.M_Product_ID , PP_MRP.M_Warehouse_ID, 0)",  BigDecimal.class),
        new Info_Column(Msg.translate(Env.getCtx(), "Details"), "PP_MRP.Type", String.class),
        new Info_Column(Msg.translate(Env.getCtx(), "Tipo"), "PP_MRP.TypeMRP", String.class),
        new Info_Column(Msg.translate(Env.getCtx(), "DocumentNo"), "documentNo(PP_MRP.PP_MRP_ID)", String.class),
        new Info_Column(Msg.translate(Env.getCtx(), "DocStatus"), "PP_MRP.DocStatus", String.class),
        new Info_Column(Msg.translate(Env.getCtx(), "DateStartSchedule"), "PP_MRP.DateStartSchedule", Timestamp.class),
        new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT cb.Name FROM C_BPartner cb WHERE cb.C_BPartner_ID=PP_MRP.C_BPartner_ID)", String.class)
	};

    /**
	 *	Static Setup - add fields to parameterPanel
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void statInit() throws Exception
	{
		//Respource Lookup 
		fResource_ID = new VLookup("S_Resource_ID", false, false, true,
		MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MResource.Table_Name,"S_Resource_ID"), DisplayType.TableDir))
		{		 				   			
			public void setValue(Object arg0) 
			{			
				super.setValue(arg0);
				executeQuery();
			};
		}; 
        lResource_ID.setLabelFor(fResource_ID);
        fResource_ID.setBackground(AdempierePLAF.getInfoBackground());  
		//Planner Lookup        
        fPlanner_ID = new VLookup("Planner_ID", false , false, true,		
		MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MPPProductPlanning.Table_Name,"Planner_ID"), DisplayType.Table))
        { 			
		             public void setValue(Object arg0) 
		             {				
		                	super.setValue(arg0);
		                	executeQuery();
		             };
        };
		lPlanner_ID.setLabelFor(fPlanner_ID);
		fPlanner_ID.setBackground(AdempierePLAF.getInfoBackground());
		//Wahrehouse Lookup		
		fWarehouse_ID = new VLookup("M_Warehouse_ID", false , false, true,		
        MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MWarehouse.Table_Name,"M_Warehouse_ID"), DisplayType.TableDir))
		{ 			
             public void setValue(Object arg0) 
             {				
                	super.setValue(arg0);
                	executeQuery();
             };
        };
		lWarehouse_ID.setLabelFor(fWarehouse_ID);
		fWarehouse_ID.setBackground(AdempierePLAF.getInfoBackground());
       
        fMaster.setSelected(false);
        fMaster.setReadWrite(false);
        fMRPReq.setSelected(false);
        fMRPReq.setReadWrite(false);
        fCreatePlan.setSelected(false);
        fCreatePlan.setReadWrite(false);
        fIssue.setSelected(false);
        fIssue.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(final ActionEvent e) {
        	}
        });
        fIssue.setReadWrite(false);
        lUOM.setText(Msg.translate(Env.getCtx(), "C_UOM_ID"));
        fUOM.setBackground(AdempierePLAF.getInfoBackground());
        fUOM.setReadWrite(false);
        
        lType.setText(Msg.translate(Env.getCtx(), "Order_Policy"));
        fType.setBackground(AdempierePLAF.getInfoBackground());
        fType.setReadWrite(false);
        
        lOrderPeriod.setText(Msg.translate(Env.getCtx(), "Order_Period"));
        fOrderPeriod.setBackground(AdempierePLAF.getInfoBackground());
        fOrderPeriod.setReadWrite(false);
        
        lTimefence.setText(Msg.translate(Env.getCtx(), "TimeFence"));
        fTimefence.setBackground(AdempierePLAF.getInfoBackground());
        fTimefence.setReadWrite(false);
        
        lLeadtime.setText(Msg.translate(Env.getCtx(), "DeliveryTime_Promised"));
        fLeadtime.setBackground(AdempierePLAF.getInfoBackground());
        fLeadtime.setReadWrite(false);
        
        lReplenishMin.setText(Msg.translate(Env.getCtx(), "Level_Min"));
        fReplenishMin.setBackground(AdempierePLAF.getInfoBackground());
        fReplenishMin.setReadWrite(false);
        
        lMinOrd.setText(Msg.translate(Env.getCtx(), "Order_Min"));
        fMinOrd.setBackground(AdempierePLAF.getInfoBackground());
        fMinOrd.setReadWrite(false);
        
         lMaxOrd.setText(Msg.translate(Env.getCtx(), "Order_Max"));
         fMaxOrd.setBackground(AdempierePLAF.getInfoBackground());
        fMaxOrd.setReadWrite(false);
        
        lOrdMult.setText(Msg.translate(Env.getCtx(), "Order_Pack"));
        fOrdMult.setBackground(AdempierePLAF.getInfoBackground());
        fOrdMult.setReadWrite(false);
        
        lOrderQty.setText(Msg.translate(Env.getCtx(), "Order_Qty"));
        fOrderQty.setBackground(AdempierePLAF.getInfoBackground());
        fOrderQty.setReadWrite(false);
        
        lYield.setText(Msg.translate(Env.getCtx(), "Yield"));
        fYield.setBackground(AdempierePLAF.getInfoBackground());
        fYield.setReadWrite(false);
        
        lOnhand.setText(Msg.translate(Env.getCtx(), "QtyOnHand"));
        fOnhand.setBackground(AdempierePLAF.getInfoBackground());
        fOnhand.setReadWrite(false);
        
        lSafetyStock.setText(Msg.translate(Env.getCtx(), "SafetyStock"));
		fSafetyStock.setBackground(AdempierePLAF.getInfoBackground());
        fSafetyStock.setReadWrite(false);

        lReserved.setText(Msg.translate(Env.getCtx(), "QtyReserved"));
   		fReserved.setBackground(AdempierePLAF.getInfoBackground());
        fReserved.setReadWrite(false);

        lAvailable.setText(Msg.translate(Env.getCtx(), "QtyAvailable"));
   		fAvailable.setBackground(AdempierePLAF.getInfoBackground());
        fAvailable.setReadWrite(false);

        lOrdered.setText(Msg.translate(Env.getCtx(), "QtyOrdered"));
   		fOrdered.setBackground(AdempierePLAF.getInfoBackground());
        fOrdered.setReadWrite(false);
        //Product Lookup
        fProduct_ID = new VLookup("M_Product_ID", true, false, true,
        MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MProduct.Table_Name,"M_Product_ID"), DisplayType.Search)) 
        {
        	public void setValue(Object arg0) {  				
        	super.setValue(arg0);
		    fAttrSetInstance_ID.setValue(new Integer(0));
		    executeQuery();
        	};
        }; 
   		//AttributeSet Button
        fAttrSetInstance_ID = new CButton("---") 
        {   			
   			private Object value;
   			public void setText(String text) 
   			{
   				
   				if(text == null) {
   					
   					text = "---";
   				}
   				if(text.length() > 23) {
   						
   					text = text.substring(0,20)+"...";
   				}

   				super.setText(text);
   			};
   			public void setValue(Object arg0) 
   			{ 
   				
   				value = arg0;
   				int i = (arg0 instanceof Integer) ? ((Integer)arg0).intValue() : 0;
   				if(i == 0) {
   					
   					setText(null);
   				}
   			};
   			public Object getValue() 
   			{ 
			
			return value; 
   			};
        };
   		
        fAttrSetInstance_ID.setValue(new Integer(0));
   		fAttrSetInstance_ID.addActionListener(new ActionListener()
   		{

   			public void actionPerformed(ActionEvent e) 
   			{
   				
   				selectAttributeSetInstance();
   				executeQuery();
   			}
   		});


   		lProduct_ID.setLabelFor(fProduct_ID);
		fProduct_ID.setBackground(AdempierePLAF.getInfoBackground());
		//
		lDueStart.setLabelFor(fDueStart);
		fDueStart.setBackground(AdempierePLAF.getInfoBackground());
		fDueStart.setToolTipText(Msg.translate(Env.getCtx(), "DueDate"));
		lDueEnd.setLabelFor(fDueEnd);
		fDueEnd.setBackground(AdempierePLAF.getInfoBackground());
		fDueEnd.setToolTipText(Msg.translate(Env.getCtx(), "DateTo"));
        fSupplyType = new VLookup("TypeMRP", false, false, true,		
		MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MPPMRP.Table_Name,"TypeMRP"), DisplayType.List));
        lSupplyType.setLabelFor(fSupplyType);
		fSupplyType.setBackground(AdempierePLAF.getInfoBackground());
		//
		parameterPanel.setLayout(new ALayout());
		//  1st Row
		parameterPanel.add(lProduct_ID, new ALayoutConstraint(0,0));
		parameterPanel.add(fProduct_ID, new ALayoutConstraint(0,1));
        parameterPanel.add(lUOM, new ALayoutConstraint(0,2));
        parameterPanel.add(fUOM, new ALayoutConstraint(0,3));
        parameterPanel.add(lType, new ALayoutConstraint(0,4));
        parameterPanel.add(fType,new ALayoutConstraint(0,5));

		//  2nd Row
		parameterPanel.add(lAttrSetInstance_ID, new ALayoutConstraint(1,0));
		parameterPanel.add(fAttrSetInstance_ID, new ALayoutConstraint(1,1));
        parameterPanel.add(lOnhand, new ALayoutConstraint(1,2));
        parameterPanel.add(fOnhand,new ALayoutConstraint(1,3));
        parameterPanel.add(lOrderPeriod, new ALayoutConstraint(1,4));
        parameterPanel.add(fOrderPeriod,new ALayoutConstraint(1,5));

		//  3rd Row
        parameterPanel.add(lPlanner_ID, new ALayoutConstraint(2,0));
		parameterPanel.add(fPlanner_ID, new ALayoutConstraint(2,1));
        parameterPanel.add(lSafetyStock, new ALayoutConstraint(2,2));
        parameterPanel.add(fSafetyStock, new ALayoutConstraint(2,3));
        parameterPanel.add(lMinOrd, new ALayoutConstraint(2,4));
        parameterPanel.add(fMinOrd,new ALayoutConstraint(2,5));

		//  4th Row
		parameterPanel.add(lWarehouse_ID, new ALayoutConstraint(3,0));
		parameterPanel.add(fWarehouse_ID, new ALayoutConstraint(3,1));
        parameterPanel.add(lReserved, new ALayoutConstraint(3,2));
        parameterPanel.add(fReserved, new ALayoutConstraint(3,3));
        parameterPanel.add(lMaxOrd, new ALayoutConstraint(3,4));
        parameterPanel.add(fMaxOrd,new ALayoutConstraint(3,5));

		//  5th Row
		parameterPanel.add(lResource_ID, new ALayoutConstraint(4,0));
		parameterPanel.add(fResource_ID, new ALayoutConstraint(4,1));
        parameterPanel.add(lAvailable, new ALayoutConstraint(4,2));
        parameterPanel.add(fAvailable, new ALayoutConstraint(4,3));
        parameterPanel.add(lOrdMult, new ALayoutConstraint(4,4));
        parameterPanel.add(fOrdMult,new ALayoutConstraint(4,5));
        
        //  6th Row
		parameterPanel.add(lDueStart, new ALayoutConstraint(5,0));
		parameterPanel.add(fDueStart, new ALayoutConstraint(5,1));
	    parameterPanel.add(lOrdered, new ALayoutConstraint(5,2));
	    parameterPanel.add(fOrdered, new ALayoutConstraint(5,3));
        parameterPanel.add(lOrderQty, new ALayoutConstraint(5,4));
        parameterPanel.add(fOrderQty,new ALayoutConstraint(5,5));
        
        //  7th Row
		parameterPanel.add(lDueEnd, new ALayoutConstraint(6,0));
		parameterPanel.add(fDueEnd, new ALayoutConstraint(6,1));
        parameterPanel.add(lTimefence, new ALayoutConstraint(6,4));
        parameterPanel.add(fTimefence,new ALayoutConstraint(6,5));

        //  8th Row
		parameterPanel.add(fMaster, new ALayoutConstraint(7,1));
        parameterPanel.add(fCreatePlan, new ALayoutConstraint(7,3));
        parameterPanel.add(lLeadtime, new ALayoutConstraint(7,4));
        parameterPanel.add(fLeadtime,new ALayoutConstraint(7,5));

        //  9th Row
        parameterPanel.add(fIssue, new ALayoutConstraint(8,1));
        parameterPanel.add(fMRPReq, new ALayoutConstraint(8,3));
        parameterPanel.add(lYield, new ALayoutConstraint(8,4));
        parameterPanel.add(fYield,new ALayoutConstraint(8,5));
	}	        
		        
	/**
	 * getIDFor get the ID to a value
	 * @param idValue
	 * @param propName
	 * @return
	 */
	private int getIDFor(Object idValue, String propName) 
	{
		
		int id = 0;
		if(idValue != null) 
		{

			if(idValue instanceof Integer) {
				
				id = ((Integer)idValue).intValue();
			}
			if(id <= 0 && propName != null) {

				id = Env.getContextAsInt(Env.getCtx(), propName);
			}
		}
		else if(propName != null) {
			
			id = Env.getContextAsInt(Env.getCtx(), propName);
		}
		
		return id;
	}
	
	/**
	 * filter by Attribute Set Instance
	 */
	private void selectAttributeSetInstance() {
		
		int m_warehouse_id = getIDFor(fWarehouse_ID.getValue(), "M_Warehouse_ID");
		int m_product_id = getIDFor(fProduct_ID.getValue(), null);
		
		String title = "";
		String sql = "SELECT p.Name, w.Name FROM M_Product p, M_Warehouse w WHERE p.M_Product_ID=? AND w.M_Warehouse_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_product_id);
			pstmt.setInt(2, m_warehouse_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				title = rs.getString(1) + " - " + rs.getString(2);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			
			pstmt = null;
		}
			
		PAttributeInstance pai = new PAttributeInstance(m_frame, title, m_warehouse_id, 0, m_product_id, 0);
		if(pai.getM_AttributeSetInstance_ID() != -1) {

			fAttrSetInstance_ID.setText(pai.getM_AttributeSetInstanceName());
			fAttrSetInstance_ID.setValue(new Integer(pai.getM_AttributeSetInstance_ID()));
			
		}
		else {

			fAttrSetInstance_ID.setText("---");
			fAttrSetInstance_ID.setValue(new Integer(0));
		}
	}
    /*
     * return true if have integer vlaue
     */
    private boolean isAttributeSetInstance() 
    {
    	
    	int id = 0;
    	if(fAttrSetInstance_ID.getValue() instanceof Integer) {
    		
    		id = ((Integer)fAttrSetInstance_ID.getValue()).intValue();
    	}
    	
    	return !(id <= 0);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        mainPanel = new javax.swing.JPanel();
        OrderPlanning = new javax.swing.JTabbedPane();
        PanelOrder = new javax.swing.JPanel();
        PanelFind = new javax.swing.JPanel();
        PanelCenter = new javax.swing.JPanel();
        PanelBottom = new javax.swing.JPanel();
        Results = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        mainPanel.setLayout(new java.awt.BorderLayout());

        PanelOrder.setLayout(new java.awt.BorderLayout());

        PanelOrder.add(PanelFind, java.awt.BorderLayout.NORTH);

        PanelOrder.add(PanelCenter, java.awt.BorderLayout.CENTER);

        PanelOrder.add(PanelBottom, java.awt.BorderLayout.SOUTH);

        OrderPlanning.addTab("Order", PanelOrder);

        OrderPlanning.addTab("Results", Results);

        mainPanel.add(OrderPlanning, java.awt.BorderLayout.CENTER);

        add(mainPanel, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    
	/**
	 *	Static Init
	 *  @throws Exception
	 */
	protected void jbInit() throws Exception
	{

            
        mainPanel.setLayout(new java.awt.BorderLayout());
               
        setLayout(new java.awt.BorderLayout());
		southPanel.setLayout(southLayout);
		southPanel.add(confirmPanel, BorderLayout.CENTER);
		southPanel.add(statusBar, BorderLayout.SOUTH);

                
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.add(parameterPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
                
		scrollPane.getViewport().add(p_table, null);
		//
		confirmPanel.addActionListener(this);
		confirmPanel.getResetButton().setVisible(hasReset());
		confirmPanel.getCustomizeButton().setVisible(hasCustomize());
		confirmPanel.getHistoryButton().setVisible(hasHistory());
		confirmPanel.getZoomButton().setVisible(hasZoom());
		//
		JButton print = ConfirmPanel.createPrintButton(true);
		print.addActionListener(this);
		confirmPanel.addButton(print);
		//
		popup.add(calcMenu);
		calcMenu.setText(Msg.getMsg(Env.getCtx(), "Calculator"));
		calcMenu.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Calculator16.gif")));
		calcMenu.addActionListener(this);
		//
		p_table.getSelectionModel().addListSelectionListener(this);           
		enableButtons();
             
	}	//	jbInit
        
                /**
	 *  Dynamic Init.
	 *  Table Layout, Visual, Listener
	 */
	private void dynInit()
	{
            
    }        
        
                /**
	 *	Fill Picks
	 *		Column_ID from C_Order
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void fillPicks() throws Exception
	{       
       prepareTable (m_layout, getTableName(), find() , "PP_MRP.DatePromised");         
    }
        
    
    public void actionPerformed(ActionEvent e) 
    {
		String cmd = e.getActionCommand();
		if (cmd.equals(ConfirmPanel.A_OK))
		{
			m_frame.dispose();
		}
		else if (cmd.equals(ConfirmPanel.A_CANCEL))
		{
			m_cancel = true;
			m_frame.dispose();
		}
         else if (cmd.equals(ConfirmPanel.A_ZOOM))
        {
                zoom();
        }
        else
        {
                    executeQuery();
        }
         m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));			
    }    
    
    public void dispose() {
        
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
    }
    
    public void executeASync(org.compiere.process.ProcessInfo processInfo) {
    }
    
    public boolean isUILocked() {
    return false;    
    }
    
    public void lockUI(org.compiere.process.ProcessInfo processInfo) {
    }
    
    public void stateChanged(ChangeEvent e) {
    }
    
    public void tableChanged(TableModelEvent e) {
    }
    
    public void unlockUI(org.compiere.process.ProcessInfo processInfo) {
    }
    
    public void valueChanged(ListSelectionEvent e) 
    {                                  
    }
    
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
    }
    
    /**
     * Set sql to get the head values
     * @return sql 
     */
    private String find()
    {
        
        StringBuffer sql = new StringBuffer();
            
		if (fProduct_ID.getValue() != null)
		{
			sql.append(" AND PP_MRP.M_Product_ID=?");
            sql.append(" AND ((PP_MRP.TYPEMRP IN ('SOO','MOP','POO','POR','STK','DOO')) OR (PP_MRP.TypeMRP='FCT' AND PP_MRP.DatePromised >= SYSDATE))");
            fillHead();
            setMRP();
        }

		if (isAttributeSetInstance()) {
			
			sql.append(" AND PP_MRP.M_AttributeSetInstance_ID=?");
            fillHead();
            setMRP();
        }
		                
		if (fResource_ID.getValue() != null)
    		sql.append(" AND PP_MRP.S_Resource_ID=?");
		if (fPlanner_ID.getValue() != null)
            sql.append(" AND PP_MRP.M_Planner_ID=?");
        if (fWarehouse_ID.getValue() != null)
            sql.append(" AND PP_MRP.M_Warehouse_ID=?");
        if (fDueStart.getValue() != null || fDueStart.getValue() != null)
        {
        	Timestamp from = (Timestamp)fDueStart.getValue();
        	Timestamp to = (Timestamp)fDueEnd.getValue();
        	if (from == null && to != null)
        		sql.append(" AND TRUNC(PP_MRP.DatePromised) <= ?");
        	else if (from != null && to == null)
        		sql.append(" AND TRUNC(PP_MRP.DatePromised) >= ?");
        	else if (from != null && to != null)
        		sql.append(" AND TRUNC(PP_MRP.DatePromised) BETWEEN ? AND ?");
        }

		Log.fine("MRP Info.setWhereClause="+ sql.toString());
        return sql.toString();        
    }

    /**
     * Fill the head value 
     */
    private void fillHead()  
    {
         
         String IsMPS="";
         String IsRequiredMRP="";
         String IsCreatePlan="";
         String IsIssue="";
         
         StringBuffer sql = new StringBuffer("SELECT mpp.IsMPS,mpp.Order_Period, mpp.IsRequiredMRP,mpp.IsCreatePlan, mpp.IsIssue, mpp.DeliveryTime_Promised,mpp.TimeFence, mpp.Order_Min, mpp.Order_Max,mpp.Order_Pack, mpp.Order_Qty, mpp.Yield, mpp.Order_Policy , mpp.SafetyStock FROM PP_Product_Planning mpp WHERE mpp.M_Product_ID=? AND mpp.AD_Client_ID=?");

         Object o = fProduct_ID.getValue();
         Integer M_Product_ID = o instanceof Integer ? (Integer)o : new Integer(0);    

         try
         {
             
             PreparedStatement pstmt = DB.prepareStatement(sql.toString(),null);
             pstmt.setInt(2,AD_Client_ID);
             pstmt.setInt(1,M_Product_ID.intValue()); 
             ResultSet rs = pstmt.executeQuery();                        
             //
             while (rs.next())
             {
         		 IsMPS = rs.getString(1);
                 if (IsMPS.equals("N"))
                    fMaster.setSelected(false);
                 else
                     fMaster.setSelected(true);
                 	 IsRequiredMRP = rs.getString(3);
                 if (IsRequiredMRP.equals("N"))
                     fMRPReq.setSelected(false);
                 else
                     fMRPReq.setSelected(true);
                     IsCreatePlan = rs.getString(4);
                 if (IsCreatePlan.equals("N"))
                    fCreatePlan.setSelected(false);
                 else
                     fCreatePlan.setSelected(true);
                     IsIssue = rs.getString(5);
                 if (IsIssue.equals("N"))
                     fIssue.setSelected(false);
                 else
                     fIssue.setSelected(true);
                 if (rs.getString(2)!=null)
                     fOrderPeriod.setText(rs.getString(2).toString());
                 if (rs.getString(6)!=null)
                    fLeadtime.setText(rs.getString(6).toString());
                 if (rs.getString(7)!=null)
                    fTimefence.setText(rs.getString(7).toString());
                 if (rs.getString(8)!=null)
                    fMinOrd.setText(rs.getString(8).toString());
                 if (rs.getString(9)!=null)
                    fMaxOrd.setText(rs.getString(9).toString());
                 if (rs.getString(10)!=null)
                    fOrdMult.setText(rs.getString(10).toString());
                 if (rs.getString(11)!=null)
                    fOrderQty.setText(rs.getString(11).toString());
                 if (rs.getString(12)!=null)
                    fYield.setText(rs.getString(12).toString());
                 if (rs.getString(13)!=null)
                    fType.setText(rs.getString(13).toString());         
                 if (rs.getString(13)!=null)
                     fSafetyStock.setText(rs.getString(14).toString()); 
             }
			rs.close();
			pstmt.close();
        }
        catch(SQLException ex)
        {
        	Log.log(Level.SEVERE, "No KeyColumn - " + sql , ex);
        }
     }    
    
    /**
     * Fill table information
     */
	private void setMRP()
    {


    	int    UOM=0;
        String Level_min="";
        Object o = null;
        
        o = fProduct_ID.getValue();
        Integer M_Product_ID = o instanceof Integer ? (Integer)o : new Integer(0);    
        
        o = fAttrSetInstance_ID.getValue();
        Integer M_AttributeSetInstance_ID =  o instanceof Integer ? (Integer)o : new Integer(0);
        
        o = fWarehouse_ID.getValue();
        Integer M_Warehouse_ID =  o instanceof Integer ? (Integer)o : new Integer(0);

        StringBuffer sql = new StringBuffer("SELECT ");
        sql.append("bomqtyonhandasi("+M_Product_ID+","+M_AttributeSetInstance_ID+","+M_Warehouse_ID+",0) as qtyonhand, ");
        sql.append("bomqtyreservedasi("+M_Product_ID+","+M_AttributeSetInstance_ID+","+M_Warehouse_ID+",0) as qtyreserved, ");
        sql.append("bomqtyavailableasi("+M_Product_ID+","+M_AttributeSetInstance_ID+","+M_Warehouse_ID+",0) as qtyavailable, ");
        sql.append("bomqtyorderedasi("+M_Product_ID+","+M_AttributeSetInstance_ID+","+M_Warehouse_ID+",0) as qtyordered FROM M_Product WHERE M_Product_ID="+M_Product_ID);       
        try
        {                 
	        PreparedStatement pstmt = DB.prepareStatement(sql.toString(),null);                       
	        ResultSet rs = pstmt.executeQuery();	
	        while (rs.next())
	        {	
	        	fOnhand.setText(rs.getBigDecimal(1).toString());
	        	fReserved.setText(rs.getBigDecimal(2).toString());                                                                
	        	fAvailable.setText(rs.getBigDecimal(3).toString());                                                                
	        	fOrdered.setText(rs.getBigDecimal(4).toString());                                                                
	        }
	        rs.close();
	        pstmt.close();
        }
		catch(SQLException ex)
		{
		  	Log.log(Level.SEVERE, "No KeyColumn - " + sql , ex);
		}
		
		sql = new StringBuffer("SELECT mrp.C_UOM_ID FROM M_Product mrp WHERE mrp.AD_Client_ID=? AND mrp.M_Product_ID=?");
		if(isAttributeSetInstance()) 
		{	  
			  sql.append(" AND mrp.M_AttributeSetInstance_ID=?");
		}
		
		try
		{            
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(),null);
            pstmt.setInt(1,AD_Client_ID);
            pstmt.setInt(2,M_Product_ID.intValue());                                            
            if(isAttributeSetInstance()) 
            {
            	pstmt.setInt(3, M_AttributeSetInstance_ID.intValue());
            }
            ResultSet rs = pstmt.executeQuery();                         
            if (rs.next())
            {
            	UOM = rs.getInt(1);
                MUOM um = new MUOM(Env.getCtx(),UOM, null);
                KeyNamePair kum = new KeyNamePair(um.getC_UOM_ID(),um.getName());
                fUOM.setText(kum.toString());
                                                     
            }
            rs.close();
            pstmt.close();
		}
		catch(SQLException ex)
		{
      	Log.log(Level.SEVERE, "setMRP - " + sql , ex);
		}
        
		sql = new StringBuffer("SELECT  mr.Level_Min FROM M_Replenish mr WHERE mr.AD_Client_ID=? AND mr.M_Product_ID=?");
		try
		{
            
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(),null);
            pstmt.setInt(1,AD_Client_ID);
            pstmt.setInt(2,M_Product_ID.intValue());                                            
            ResultSet rs = pstmt.executeQuery();                        
            //
            while (rs.next())
            {
            	Level_min = rs.getString(1);                              
                fReplenishMin.setText(Level_min);
            }
            rs.close();
            pstmt.close();
		}
		catch(SQLException ex)
		{
      	Log.log(Level.SEVERE, "No KeyColumn - " + sql , ex);
		}
    }

        
        
    /**
	 *  Reset Parameters
	 *	To be overwritten by concrete classes
	 */
	void doReset()					{}
	/**
	 *  Has Reset (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has reset (default false)
	 */
	boolean hasReset()				{return false;}
	/**
	 *  History dialog
	 *	To be overwritten by concrete classes
	 */
	void showHistory()					{}
	/**
	 *  Has History (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has history (default false)
	 */
	boolean hasHistory()				{return false;}
	/**
	 *  Customize dialog
	 *	To be overwritten by concrete classes
	 */
	void customize()					{}
	/**
	 *  Has Customize (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has customize (default false)
	 */
	boolean hasCustomize()				{return false;}
	
	/**
	 *  Zoom action
	 *	To be overwritten by concrete classes
	 */
	void zoom()							
    {
    Log.info( "InfoMRPDeatiled.zoom");
	Integer PP_MPR_ID = getSelectedRowKey();
	int AD_WindowNo = 0;
	if (PP_MPR_ID == null)
	return;
	MQuery query = null;
	//int AD_WindowNo = getAD_Window_ID("C_Order", fIsSOTrx.isSelected());
	
	MPPMRP mrp = new MPPMRP(Env.getCtx(),PP_MPR_ID.intValue(), null);
    String typemrp = mrp.getTypeMRP();
    if (typemrp.equals("POO"))
    {	
        AD_WindowNo = MWindow.getWindow_ID("Purchase Order"); 
        query = new MQuery("C_Order");
        query.addRestriction("C_Order_ID", MQuery.EQUAL, mrp.getC_Order_ID());
    } 
    else if (typemrp.equals("SOO"))
    {	
        AD_WindowNo = MWindow.getWindow_ID("Sales Order");
        query = new MQuery("C_Order");
        query.addRestriction("C_Order_ID", MQuery.EQUAL, mrp.getC_Order_ID());
    }    
    else if (typemrp.equals("MOP"))
    {	
        AD_WindowNo = MWindow.getWindow_ID("Manufacturing Order");
        query = new MQuery("PP_Order");
        query.addRestriction("PP_Order_ID", MQuery.EQUAL, mrp.getPP_Order_ID());
    }    
    else if (typemrp.equals("POR"))
    {	
        AD_WindowNo = MWindow.getWindow_ID("Requisition");
        query = new MQuery("M_Requisition");
        query.addRestriction("M_Requisition_ID", MQuery.EQUAL, mrp.getM_Requisition_ID());
    }
    else if (typemrp.equals("FCT"))
    {	
        AD_WindowNo = MWindow.getWindow_ID("Forecast");
        query = new MQuery("M_Forecat");
        query.addRestriction("M_Forecast_ID", MQuery.EQUAL, mrp.getM_Forecast_ID());
    }
    if (typemrp.equals("DOO"))
    {	
        AD_WindowNo = MWindow.getWindow_ID("Distribution Order"); 
        query = new MQuery("DD_Order");
        // [ 1977523 ] // query.addRestriction("DD_Order_ID", MQuery.EQUAL, mrp.getDD_Order_ID());
    } 
    if (AD_WindowNo == 0) 
    	return;
    
    Log.info("AD_WindowNo " + AD_WindowNo);
	zoom (AD_WindowNo, query);
    }
        
	/**
	 *	Zoom to target
	 *  @param AD_Window_ID window id
	 *  @param zoomQuery zoom query
	 * @throws InterruptedException
	 */
	void zoom (int AD_Window_ID, MQuery zoomQuery)
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		final AWindow frame = new AWindow();
		if (!frame.initWindow(AD_Window_ID, zoomQuery))
		return;
		
		new Thread()
		{
			public void run()
			{
				try
				{
					sleep(50);
				}
				catch (Exception e)
				{
				}
				AEnv.showCenterScreen(frame);
			}
		}.start();
	}	//	zoom
	
	/**
	 *  Has Zoom (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has zoom (default false)
	 */
	boolean hasZoom()					{return true;}
    
        
    /**
	 *  Enable OK, History, Zoom if row selected
	 */
	void enableButtons ()
	{
		boolean enable = true;//p_table.getSelectedRow() != -1;
                
		confirmPanel.getOKButton().setEnabled(true);
		if (hasHistory())
			confirmPanel.getHistoryButton().setEnabled(enable);
		if (hasZoom())
			confirmPanel.getZoomButton().setEnabled(enable);
	}   //  enableButtons
        
        
	/**************************************************************************
	 *  Execute Query
	 */
	void executeQuery()
	{
		//  ignore when running
		if (m_worker != null && m_worker.isAlive())
			return;
		m_worker = new Worker();
		m_worker.start();
	}   //  executeQuery
        
    /**************************************************************************
	 *  Prepare Table, Construct SQL (m_m_sqlMain, m_sqlAdd)
	 *  and size Window
	 *  @param layout layout array
	 *  @param from from clause
	 *  @param staticWhere where clause
	 *  @param orderBy order by clause
	 */
	protected void prepareTable (Info_Column[] layout, String from, String staticWhere, String orderBy)
	{
		p_layout = layout;
		StringBuffer sql = new StringBuffer ("SELECT ");
		//  add columns & sql
		for (int i = 0; i < layout.length; i++)
		{
			if (i > 0)
				sql.append(", ");
			sql.append(layout[i].getColSQL());
			//  adding ID column
			if (layout[i].isIDcol())
				sql.append(",").append(layout[i].getIDcolSQL());
			//  add to model
			p_table.addColumn(layout[i].getColHeader());
			if (layout[i].isColorColumn())
				p_table.setColorColumn(i);
			if (layout[i].getColClass() == IDColumn.class)
				m_keyColumnIndex = i;
		}
		//  set editors (two steps)
		for (int i = 0; i < layout.length; i++)
			p_table.setColumnClass(i, layout[i].getColClass(), layout[i].isReadOnly(), layout[i].getColHeader());

		sql.append( " FROM ").append(from);
		//
                StringBuffer where = new StringBuffer("PP_MRP.DocStatus IN ('IP','CO','DR')  AND PP_MRP.IsActive='Y' and PP_MRP.Qty!=0 ");
                sql.append(" WHERE ").append(where.toString());
		if (!staticWhere.equals("")) 
                sql.append(staticWhere);
               
		m_sqlMain = sql.toString();
             
		m_sqlAdd = "";
		if (orderBy != null && orderBy.length() > 0)
			m_sqlAdd = " ORDER BY " + orderBy;

		if (m_keyColumnIndex == -1)
			Log.log(Level.SEVERE, "No KeyColumn - " + sql);
                
		
		//  Table Selection
		p_table.setRowSelectionAllowed(true);
		//p_table.addMouseListener(this);
		p_table.setMultiSelection(false);
        p_table.setEditingColumn(0);
        p_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  

		//  Window Sizing
		parameterPanel.setPreferredSize(new Dimension (INFO_WIDTH, parameterPanel.getPreferredSize().height));
		scrollPane.setPreferredSize(new Dimension(INFO_WIDTH, 400));
	}   //  prepareTable
        
        	/**
	 *  Get the key of currently selected row
	 *  @return selected key
	 */
	protected Integer getSelectedRowKey()
	{
		int row = p_table.getSelectedRow();
		if (row != -1 && m_keyColumnIndex != -1)
		{
			Object data = p_table.getModel().getValueAt(row, m_keyColumnIndex);
			if (data instanceof IDColumn)
				data = ((IDColumn)data).getRecord_ID();
			if (data instanceof Integer)
				return (Integer)data;
		}
		return null;
	}   //  getSelectedRowKey
        
        /**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt statement
	 *  @throws SQLException
	 */
	void setParameters(PreparedStatement pstmt) throws SQLException
	{
		int index = 1;
        if (fProduct_ID.getValue() != null)
		{
			Integer pp = (Integer)fProduct_ID.getValue();
			pstmt.setInt(index++, pp.intValue());
			Log.fine("Product=" + pp);
		}

        if (isAttributeSetInstance())
		{
			Integer asi = (Integer)fAttrSetInstance_ID.getValue();
			pstmt.setInt(index++, asi.intValue());
			Log.fine("AttributeSetInstance=" + asi);
		}
		if (fResource_ID.getValue() != null)
		{
			Integer r = (Integer)fResource_ID.getValue();
			pstmt.setInt(index++, r.intValue());
			Log.fine("Resource=" + r);
		}
        if (fWarehouse_ID.getValue() != null)
		{
			Integer w = (Integer)fWarehouse_ID.getValue();
			pstmt.setInt(index++, w.intValue());
			Log.fine("Warehouse=" + w);
		}
        if (fPlanner_ID.getValue() != null)
		{
			Integer f = (Integer)fPlanner_ID.getValue();
			pstmt.setInt(index++, f.intValue());
			Log.fine("Planner=" + f);
		}
		if (fDueStart.getValue() != null || fDueEnd.getValue() != null)
		{
			Timestamp from = (Timestamp)fDueStart.getValue();
			Timestamp to = (Timestamp)fDueEnd.getValue();
			Log.fine("Date From=" + from + ", To=" + to);
			if (from == null && to != null)
				pstmt.setTimestamp(index++, to);
			else if (from != null && to == null)
				pstmt.setTimestamp(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setTimestamp(index++, from);
				pstmt.setTimestamp(index++, to);
			}
		}
	}   //  setParameters
        
        /**
	 *  Get Table name Synonym
	 *  @return table name
	 */
	String getTableName()
	{
                table = new MTable(Env.getCtx(),AD_Table_ID,null);
                p_tableName = table.getTableName();
                
		return p_tableName;
	}   //  getTableName
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane OrderPlanning;
    private javax.swing.JPanel PanelBottom;
    private javax.swing.JPanel PanelCenter;
    private javax.swing.JPanel PanelFind;
    private javax.swing.JPanel PanelOrder;
    private javax.swing.JPanel Results;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
    
    /**
	 *  Worker
	 */
	class Worker extends Thread
	{
		/**
		 *  Do Work (load data)
		 */
		public void run()
		{
			Log.fine("Info.Worker.run");
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);

			//  Clear Table
			p_table.setRowCount(0);
			//                       
			StringBuffer sql = new StringBuffer (m_sqlMain);
                        
			//String dynWhere = "" ;//find ();
            String dynWhere = find ();
			if (dynWhere.length() > 0)
                        {   System.out.println("where" +dynWhere);
				sql.append(dynWhere);   //  includes first AND
                        }
			sql.append(m_sqlAdd);
			String xSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
			xSql = MRole.getDefault().addAccessSQL(xSql, getTableName(), 
				MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
			try
			{
				PreparedStatement pstmt = DB.prepareStatement(xSql,null);
				Log.fine("SQL=" + xSql);
				setParameters (pstmt);
			//	Log.trace(Log.l6_Database, "Info.Worker.run - start query");
				ResultSet rs = pstmt.executeQuery();
			//	Log.trace(Log.l6_Database, "Info.Worker.run - end query");
				while (!isInterrupted() & rs.next())
				{
					int row = p_table.getRowCount();
					p_table.setRowCount(row+1);
					int colOffset = 1;  //  columns start with 1
					for (int col = 0; col < p_layout.length; col++)
					{
						Object data = null;
						Class c = p_layout[col].getColClass();
						int colIndex = col + colOffset;
						if (c == IDColumn.class)
                        {    
                                                    
                           IDColumn id = new IDColumn(rs.getInt(colIndex));
                           id.setSelected(true);
                           data = id;                             
                           p_table.setColumnReadOnly(0, false);      
                        }        
						else if (c == Boolean.class)
							data = new Boolean("Y".equals(rs.getString(colIndex)));
						else if (c == Timestamp.class)
							data = rs.getTimestamp(colIndex);
						else if (c == BigDecimal.class)
							data = rs.getBigDecimal(colIndex);
						else if (c == Double.class)
							data = new Double(rs.getDouble(colIndex));
						else if (c == Integer.class)
							data = new Integer(rs.getInt(colIndex));
						else if (c == KeyNamePair.class)
						{
							String display = rs.getString(colIndex);
							int key = rs.getInt(colIndex+1);
							data = new KeyNamePair(key, display);
							colOffset++;
						}
						else
							data = rs.getString(colIndex);
						//  store
						p_table.setValueAt(data, row, col);
					}
				}
				Log.fine("Info.Worker.run - interrupted=" + isInterrupted());
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{				
                                Log.log(Level.SEVERE, "Info.Worker.run - " + xSql, e);
			}
			
			p_table.autoSize();
			setCursor(Cursor.getDefaultCursor());   
			
			// 00 PP_MRP.PP_MRP_ID"
			// 01 Value, "(Select Value from M_Product p where p.M_Product_ID=PP_MRP.M_Product_ID)", String.class)
			// 02 Name, "(Select Name from M_Product p where p.M_Product_ID=PP_MRP.M_Product_ID)", String.class),
			// 03 Resource", "(Select Name from S_Resource sr where sr.S_Resource_ID=PP_MRP.S_Resource_ID)", String.class),	
			// 04 Warehouse", "(Select Name from M_Warehouse wh where wh.M_Warehouse_ID=PP_MRP.M_Warehouse_ID)", String.class),
			// 05 DatePromised, "PP_MRP.DatePromised", Timestamp.class),
	        // 06 Gross Reqs."), "(SELECT m.Qty FROM PP_MRP m WHERE m.Type='D' AND m.PP_MRP_ID=PP_MRP.PP_MRP_ID)",  BigDecimal.class),        
	        // 07 Schedule Reciept."), "(SELECT m.Qty FROM PP_MRP m WHERE m.Type='S' AND m.DocStatus ='CO' AND m.PP_MRP_ID=PP_MRP.PP_MRP_ID)",  BigDecimal.class),
	        // 08 Plan Orders"), "(SELECT m.Qty FROM PP_MRP m WHERE m.Type='S' AND m.DocStatus IN ('DR', 'IP') AND m.PP_MRP_ID=PP_MRP.PP_MRP_ID)",  BigDecimal.class),
	        // 09 Proj QOH"), "bomQtyOnHand( PP_MRP.M_Product_ID , PP_MRP.M_Warehouse_ID, 0)",  BigDecimal.class),
	        // 10 Details"), "PP_MRP.Type", String.class),
	        // 11 Type"), "PP_MRP.TypeMRP", String.class),
	        // 12 DocumentNo"), "documentNo(PP_MRP.PP_MRP_ID)", String.class),
	        // 13 DocStatus"), "PP_MRP.DocStatus", String.class),	// 4L - BUG #59
	        // 14 DateStartSchedule"), "PP_MRP.DateStartSchedule", Timestamp.class),
	        // 15 C_BPartner_ID"), "(SELECT cb.Name FROM C_BPartner cb WHERE cb.C_BPartner_ID=PP_MRP.C_BPartner_ID)", String.class)		
			BigDecimal OnHand = Env.ZERO;
			if (fOnhand.getDisplay() != null && !fOnhand.getDisplay().equals("") && fOnhand.getDisplay().length() != 0)
			OnHand = new BigDecimal(fOnhand.getDisplay());   
			
            for (int row=0;row < p_table.getRowCount();row++)
            {
                    	Timestamp datepromised = (Timestamp)p_table.getValueAt(row,5); 
                    	Timestamp today = new Timestamp (System.currentTimeMillis());
                    	String Type = (String)p_table.getValueAt(row,10);
                    	String TypeMRP = (String) p_table.getValueAt(row,11);
                        if (Type.equals("D") || Type.equals("D") && TypeMRP.equals("FCT") && datepromised.after(today))
                        {
                        BigDecimal QtyGrossReqs =  (BigDecimal)p_table.getValueAt(row,6);   
                        OnHand = OnHand.subtract(QtyGrossReqs);
                        p_table.setValueAt(OnHand,row,9);           			
                        }
                        if (Type.equals("S"))
                        {                        	
                        BigDecimal QtyScheduledReceipts = (BigDecimal)p_table.getValueAt(row,7);
                        BigDecimal QtyPlan = (BigDecimal)p_table.getValueAt(row,8);
                        if (QtyPlan == null)
                        QtyPlan = Env.ZERO;
                        if (QtyScheduledReceipts == null)
                        	QtyScheduledReceipts = Env.ZERO;
                        OnHand = OnHand.add(QtyScheduledReceipts.add(QtyPlan));
                        p_table.setValueAt(OnHand,row,9);  
                        }
            }                  
		}   //  run
	  }   //  Worker
   }
