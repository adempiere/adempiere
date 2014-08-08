package org.eevolution.form;




import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.window.WPAttributeInstance;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLot;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MRefList;
import org.compiere.model.MResource;
import org.compiere.model.MRole;
import org.compiere.model.MStorage;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPProductPlanning;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Space;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.event.ListDataListener;


public class WMRPDetailed extends MRPDetailed implements IFormController, EventListener, ListDataListener, WTableModelListener {

	public WMRPDetailed()
	{
		//initComponents();
		init();
	}
	

	
	private void init()
	{
		try
		{
			//	UI
			statInit();
			fillPicks();
			jbInit();
		
			//m_frame.appendChild(mainLayout); // put at center
			//m_frame.appendChild(statusBar); // put at south
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "VMRPDetailed.init", e);
		}
	}
	
	
	/**	FormFrame			*/
	private CustomForm			m_frame = new CustomForm();
	private StatusBarPanel 			statusBar = new StatusBarPanel();
	/** Table                   */
	protected WListbox         p_table = new WListbox();
	

	

	/** Static Layout           */
	private Panel panel = new Panel();
	private Panel southPanel = new Panel();
	private Borderlayout southLayout = new Borderlayout();
	ConfirmPanel confirmPanel = new ConfirmPanel(true, true, true, true, true, true, true);
	private Grid parameterPanel = GridFactory.newGridLayout();
	

	
	private Menupopup popup = new Menupopup();
	private Menuitem calcMenu = new Menuitem(Msg.getMsg(Env.getCtx(), "Calculator"), "/images/Calculator16.png");

	

	private Label lProduct_ID = new Label(Msg.translate(getCtx(), MPPMRP.COLUMNNAME_M_Product_ID));
	private WSearchEditor fProduct_ID;
	private Label lAttrSetInstance_ID = new Label(Msg.translate(getCtx(), MPPOrder.COLUMNNAME_M_AttributeSetInstance_ID));
	
	private Button fAttrSetInstance_ID = 	fAttrSetInstance_ID = new Button();
	private Label lResource_ID = new Label(Msg.translate(getCtx(), MPPMRP.COLUMNNAME_S_Resource_ID));
	private WSearchEditor fResource_ID;
	private Label lWarehouse_ID = new Label(Msg.translate(getCtx(), MPPMRP.COLUMNNAME_M_Warehouse_ID));
	private WSearchEditor fWarehouse_ID;
	private Label lPlanner_ID = new Label(Msg.translate(getCtx(), MPPMRP.COLUMNNAME_Planner_ID));
	private WSearchEditor fPlanner_ID;
	
	private Tabbox OrderPlanning;
	
	private Panel PanelBottom;
	private Panel PanelCenter;
	private Panel PanelFind;
	private Tab PanelOrder;
	private Tab Results;
	private Borderlayout mainLayout = new Borderlayout();

	//
	private Label lDateFrom = new Label(Msg.translate(getCtx(), MLot.COLUMNNAME_DateFrom));
	
	//DueStart Field
	private WDateEditor fDateFrom = new WDateEditor();
	
	private Label lDateTo = new Label(Msg.translate(getCtx(), MLot.COLUMNNAME_DateTo));
	//DueEnd Field
	private WDateEditor fDateTo = new WDateEditor(); 


	private Label			lType			= new Label();
	private Textbox  		fType			= new Textbox();
	private Label			lUOM			= new Label();
	private Textbox 		fUOM			= new Textbox();
	private Label			lOrderPeriod 	= new Label();
	private WNumberEditor	fOrderPeriod	= new WNumberEditor();
	private Label			lTimefence 		= new Label();
	private WNumberEditor	fTimefence		= new WNumberEditor();
	private Label			lLeadtime		= new Label();
	private WNumberEditor	fLeadtime		= new WNumberEditor();
	private Label			lReplenishMin	= new Label();
	private WNumberEditor	fReplenishMin	= new WNumberEditor();       
	private Label			lMinOrd			= new Label();
	private WNumberEditor	fMinOrd			= new WNumberEditor();
	private Label			lMaxOrd			= new Label();
	private WNumberEditor	fMaxOrd			= new WNumberEditor();
	private Label			lOrdMult		= new Label();
	private WNumberEditor	fOrdMult		= new WNumberEditor();
	private Label			lOrderQty		= new Label();
	private WNumberEditor	fOrderQty		= new WNumberEditor();
	private Label			lYield			= new Label();
	private WNumberEditor	fYield			= new WNumberEditor();
	private Label			lOnhand			= new Label();
	private WNumberEditor	fOnhand			= new WNumberEditor();
	private Label			lSafetyStock	= new Label();
	private WNumberEditor	fSafetyStock	= new WNumberEditor();
	private Label			lOrdered		= new Label();
	private WNumberEditor	fOrdered		= new WNumberEditor();
	private Label			lReserved		= new Label();
	private WNumberEditor	fReserved		= new WNumberEditor();
	private Label			lAvailable		= new Label();
	private WNumberEditor	fAvailable		= new WNumberEditor();

	private Label			lSupplyType		= new Label(Msg.translate(getCtx(), MPPMRP.COLUMNNAME_TypeMRP));
	private WSearchEditor	fSupplyType		= null;
	private Checkbox		fMaster			= new Checkbox ();
	private Checkbox		fMRPReq			= new Checkbox ();
	private Checkbox		fCreatePlan		= new Checkbox ();
	private int ASI_ID = 0;
	
	private boolean isBaseLanguage = Env.getLanguage(Env.getCtx()).getBaseAD_Language().compareTo(Env.getLoginLanguage(Env.getCtx()).getAD_Language()) == 0;

	
	
	private void statInit() throws Exception
	{
		//Resource Lookup 
		Language language = Language.getLoginLanguage(); // Base Language
		MLookup resourceL = MLookupFactory.get(getCtx(), p_WindowNo,
				MColumn.getColumn_ID(MResource.Table_Name, MResource.COLUMNNAME_S_Resource_ID),
				DisplayType.TableDir, language, MResource.COLUMNNAME_S_Resource_ID, 0, false,
				MResource.Table_Name+"."
				+ MResource.COLUMNNAME_ManufacturingResourceType 
				+ "= '" 
				+ MResource.MANUFACTURINGRESOURCETYPE_Plant 
				+ "'");
		fResource_ID = new WSearchEditor(MPPMRP.COLUMNNAME_S_Resource_ID, false, false, true, resourceL)
		{		 				   			
			private final long serialVersionUID = 1L;
			public void setValue(Object arg0) 
			{			
				super.setValue(arg0);
			};
		}; 
      
		fPlanner_ID = new WSearchEditor(MPPMRP.COLUMNNAME_Planner_ID, false , false, true,		
				MLookupFactory.get (getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MPPProductPlanning.Table_Name,MPPMRP.COLUMNNAME_Planner_ID), DisplayType.Table))
		{ 			
			private final long serialVersionUID = 1L;
			public void setValue(Object arg0) 
			{				
				super.setValue(arg0);
			};
		};
	
		fWarehouse_ID = new WSearchEditor(MPPMRP.COLUMNNAME_M_Warehouse_ID, false , false, true,		
				MLookupFactory.get (getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MWarehouse.Table_Name,MPPMRP.COLUMNNAME_M_Warehouse_ID), DisplayType.TableDir))
		{ 			
			private final long serialVersionUID = 1L;
			public void setValue(Object arg0) 
			{				
				super.setValue(arg0);
			};
		};

		fMaster.setSelected(false);
		fMaster.setEnabled(false);
		fMRPReq.setSelected(false);
		fMRPReq.setEnabled(false);
		fCreatePlan.setSelected(false);
		fCreatePlan.setEnabled(false);


		lUOM.setText(Msg.translate(getCtx(), MUOM.COLUMNNAME_C_UOM_ID));
		fUOM.setReadonly(true);   

		lType.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_Order_Policy));
		fType.setReadonly(true);

		lOrderPeriod.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_Order_Period));
		fOrderPeriod.setReadWrite(false);

		lTimefence.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_TimeFence));
		fTimefence.setReadWrite(false);

		lLeadtime.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_DeliveryTime_Promised));
		fLeadtime.setReadWrite(false);
		
		lMinOrd.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_Order_Min));
		fMinOrd.setReadWrite(false);

		lMaxOrd.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_Order_Max));
		fMaxOrd.setReadWrite(false);

		lOrdMult.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_Order_Pack));
		fOrdMult.setReadWrite(false);

		lOrderQty.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_Order_Qty));
		fOrderQty.setReadWrite(false);

		lYield.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_Yield));
		fYield.setReadWrite(false);

		lOnhand.setText(Msg.translate(getCtx(), MStorage.COLUMNNAME_QtyOnHand));
		fOnhand.setReadWrite(false);

		lSafetyStock.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_SafetyStock));
		fSafetyStock.setReadWrite(false);

		lReserved.setText(Msg.translate(getCtx(), MStorage.COLUMNNAME_QtyReserved));
		fReserved.setReadWrite(false);

		lAvailable.setText(Msg.translate(getCtx(), "QtyAvailable"));
		fAvailable.setReadWrite(false);

		lOrdered.setText(Msg.translate(getCtx(), MPPOrder.COLUMNNAME_QtyOrdered));
		fOrdered.setReadWrite(false);
		//Product Lookup
		fProduct_ID = new WSearchEditor(MPPMRP.COLUMNNAME_M_Product_ID, true, false, true,
				MLookupFactory.get (getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MProduct.Table_Name,MPPMRP.COLUMNNAME_M_Product_ID), DisplayType.Search)) 
		{
			private final long serialVersionUID = 1L;
			public void setValue(Object arg0) {  				
				super.setValue(arg0);
				
			};
		}; 
		
		fMaster.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_IsMPS));
		fMRPReq.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_IsRequiredMRP));
		fCreatePlan.setText(Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_IsCreatePlan));
		
		
		//AttributeSet Button
		fAttrSetInstance_ID = new Button() 
		{   			
			private final long serialVersionUID = 1L;
			private Object m_value;
			public void setLabel(String text) 
			{
				if(text == null) {
					text = "---";
				}
				if(text.length() > 23) {
					text = text.substring(0,20)+"...";
				}
				super.setLabel(text);
			}
			public void setValue(Object arg0) 
			{ 
				m_value = arg0;
				int i = (arg0 instanceof Integer) ? ((Integer)arg0).intValue() : 0;
				if(i == 0) {
					setLabel(null);
				}
			}
			public Object getValue() 
			{ 
				return m_value; 
			}
		};
		
		fAttrSetInstance_ID.addActionListener(new EventListener(){
			@Override
			public void onEvent(Event event) throws Exception {
				selectAttributeSetInstance();
			}
		});

		//
		
		fDateFrom.getComponent().setTooltiptext(Msg.translate(getCtx(), MLot.COLUMNNAME_DateFrom));
		
		fDateTo.getComponent().setTooltiptext(Msg.translate(getCtx(), MLot.COLUMNNAME_DateTo));
		
		fSupplyType = new WSearchEditor(MPPMRP.COLUMNNAME_TypeMRP,false, false, true,		
				MLookupFactory.get (getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MPPMRP.Table_Name,MPPMRP.COLUMNNAME_TypeMRP), DisplayType.List));
		
		
		Rows rows = null;
		Row row = null;

		rows = new Rows();
		rows.setParent(parameterPanel);
		
		//  1st Row
		row = rows.newRow();
		row.appendChild(lProduct_ID.rightAlign());
		row.appendChild(fProduct_ID.getComponent());
		row.appendChild(lUOM.rightAlign());
		row.appendChild(fUOM);
		row.appendChild(lType.rightAlign());
		row.appendChild(fType);

		//  2nd Row
		row = rows.newRow();
		row.appendChild(lAttrSetInstance_ID.rightAlign());
		row.appendChild(fAttrSetInstance_ID);
		row.appendChild(lOnhand.rightAlign());
		row.appendChild(fOnhand.getComponent()); 
		row.appendChild(lOrderPeriod.rightAlign());
		row.appendChild(fOrderPeriod.getComponent()); 

		//  3rd Row
		row = rows.newRow();
		row.appendChild(lPlanner_ID.rightAlign());
		row.appendChild(fPlanner_ID.getComponent());
		row.appendChild(lSafetyStock.rightAlign());
		row.appendChild(fSafetyStock.getComponent());
		row.appendChild(lMinOrd.rightAlign());
		row.appendChild(fMinOrd.getComponent());

		//  4th Row
		row = rows.newRow();
		row.appendChild(lWarehouse_ID.rightAlign());
		row.appendChild(fWarehouse_ID.getComponent());
		row.appendChild(lReserved.rightAlign());
		row.appendChild(fReserved.getComponent());
		row.appendChild(lMaxOrd.rightAlign());
		row.appendChild(fMaxOrd.getComponent());

		//  5th Row
		row = rows.newRow();
		row.appendChild(lResource_ID.rightAlign());
		row.appendChild(fResource_ID.getComponent());
		row.appendChild(lAvailable.rightAlign());
		row.appendChild(fAvailable.getComponent());
		row.appendChild(lOrdMult.rightAlign());
		row.appendChild(fOrdMult.getComponent());

		//  6th Row
		row = rows.newRow();
		row.appendChild(lDateFrom.rightAlign());
		row.appendChild(fDateFrom.getComponent());
		row.appendChild(lOrdered.rightAlign());
		row.appendChild(fOrdered.getComponent());
		row.appendChild(lOrderQty.rightAlign());
		row.appendChild(fOrderQty.getComponent());

		//  7th Row
		row = rows.newRow();
		row.appendChild(lDateTo.rightAlign());
		row.appendChild(fDateTo.getComponent());
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(lTimefence.rightAlign());
		row.appendChild(fTimefence.getComponent());

		//  8th Row
		row = rows.newRow();
		row.appendChild(new Space());
		row.appendChild(fMaster);
		row.appendChild(new Space());
		row.appendChild(fCreatePlan);
		row.appendChild(lLeadtime.rightAlign());
		row.appendChild(fLeadtime.getComponent());

		//  9th Row
		row = rows.newRow();
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(fMRPReq);
		row.appendChild(lYield.rightAlign());
		row.appendChild(fYield.getComponent());
	}
	
	private void selectAttributeSetInstance() {
		int m_warehouse_id = 0;//getM_Warehouse_ID();
		int m_product_id = 0;//getM_Product_ID();
		
		if (m_product_id <= 0)
			return;
		MProduct product = MProduct.get(getCtx(), m_product_id);
		MWarehouse wh = MWarehouse.get(getCtx(), m_warehouse_id);
		String title = product.get_Translation(MProduct.COLUMNNAME_Name)
							+" - "+wh.get_Translation(MWarehouse.COLUMNNAME_Name);

		WPAttributeInstance pai = new WPAttributeInstance(title, m_warehouse_id, 0, m_product_id, 0);
		if(pai.getM_AttributeSetInstance_ID() != -1) {
			fAttrSetInstance_ID.setLabel(pai.getM_AttributeSetInstanceName());
			ASI_ID = new Integer(pai.getM_AttributeSetInstance_ID());
		}
		else {
			ASI_ID = 0;
		}
	}
	
	private boolean isAttributeSetInstance() 
	{
		return  getM_AttributeSetInstance_ID() > 0;
	}
	
	private void initComponents()
	{
		OrderPlanning = new Tabbox();
		PanelOrder = new Tab();
		PanelFind = new Panel();
		PanelCenter = new Panel();
		PanelBottom = new Panel();
		Results = new Tab();
		

		Borderlayout PanelOrderLayout = new Borderlayout();
		PanelOrder.appendChild(PanelOrderLayout);
		
		North north = new North();
		PanelOrderLayout.appendChild(north); 
		north.appendChild(PanelFind);

		Center center = new Center();
		PanelOrderLayout.appendChild(center);
		center.appendChild(PanelCenter);
		
		South south = new South();
		PanelOrderLayout.appendChild(south);
		south.appendChild(PanelBottom);
		
		OrderPlanning.appendChild(PanelOrder);
		OrderPlanning.appendChild(Results);
		
		PanelOrder.setLabel("Order");
		Results.setLabel("Result");
		

		Center center2 = new Center();
		mainLayout.appendChild(center2);
		center2.appendChild(OrderPlanning);
		
		m_frame.setWidth("99%");
		m_frame.setHeight("100%");
		m_frame.setStyle("position: absolute; padding: 0; margin: 0");
		m_frame.appendChild (mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setStyle("position: absolute");

	}

	
	protected void jbInit() throws Exception
	{


		m_frame.setWidth("99%");
		m_frame.setHeight("100%");
		m_frame.setStyle("position: absolute; padding: 0; margin: 0");
		m_frame.appendChild (mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setStyle("position: absolute");
		
		North north = new North();
		north.appendChild(parameterPanel);
		mainLayout.appendChild(north);
		
		Center center  = new Center();
		center.appendChild(p_table);
		mainLayout.appendChild(center);
		p_table.setVflex(true);
		p_table.setFixedLayout(true);
		center.setFlex(true);
		//center.setAutoscroll(true);
		
		Div div = new Div();
		div.appendChild(confirmPanel);
		div.appendChild(statusBar);
		
		South south = new South();
		south.appendChild(div);
		
		
		mainLayout.appendChild(south);
		
		confirmPanel.addActionListener(this);
		confirmPanel.setVisible(ConfirmPanel.A_RESET, hasReset());
		confirmPanel.setVisible(ConfirmPanel.A_CUSTOMIZE, hasCustomize());
		confirmPanel.setVisible(ConfirmPanel.A_HISTORY, hasHistory());
		confirmPanel.setVisible(ConfirmPanel.A_ZOOM, hasZoom());
		
		
		South southPanel = new South();
		southPanel.appendChild(southLayout);
		
		//
		Button print = confirmPanel.createButton(ConfirmPanel.A_PRINT);
		print.addActionListener(this);
		confirmPanel.addButton(print);
		//
		popup.appendChild(calcMenu);
		calcMenu.addEventListener(Events.ON_CLICK,this);
		p_table.getModel().addListDataListener(this);
           
		enableButtons();

	}	
	
	private void fillPicks() throws Exception
	{       
		m_keyColumnIndex = 0;
		m_sqlMain = p_table.prepareTable(m_layout, getTableName(), getWhereClause(getSQLWhere()), false, "RV_PP_MRP", false);
	}


	@Override
	public ADForm getForm() {
		return m_frame;
	}



	@Override
	public void onEvent(Event event) throws Exception {
		
		String cmd = event.getTarget().getId();
		
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
		else if (cmd.equals(ConfirmPanel.A_REFRESH))
		{
			executeQuery();
		}
	}

	public void dispose() {

		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}
	
	private String getSQLWhere()
	{

		 StringBuffer sql = new StringBuffer();

		 if (fProduct_ID.getValue() != null)
		 {
			 sql.append(" AND "+getTableName()+".M_Product_ID=?");
			 sql.append(" AND (("+getTableName()+".OrderType IN ('SOO','MOP','POO','POR','STK','DOO')) OR ("+getTableName()+".OrderType='FCT' AND "+getTableName()+".DatePromised >= SYSDATE))");
			 fillHead();
			 setMRP();
		 }

		 if (isAttributeSetInstance()) {

			 sql.append(" AND "+getTableName()+".M_AttributeSetInstance_ID=?");
			 fillHead();
			 setMRP();
		 }

		 if (fResource_ID.getValue() != null)
			 sql.append(" AND "+getTableName()+".S_Resource_ID=?");
		 if (fPlanner_ID.getValue() != null)
			 sql.append(" AND "+getTableName()+".Planner_ID=?");
		 if (fWarehouse_ID.getValue() != null)
			 sql.append(" AND "+getTableName()+".M_Warehouse_ID=?");
		 if (fDateFrom.getValue() != null || fDateFrom.getValue() != null)
		 {
			 Timestamp from = (Timestamp)fDateFrom.getValue();
			 Timestamp to = (Timestamp)fDateTo.getValue();
			 if (from == null && to != null)
				 sql.append(" AND TRUNC("+getTableName()+".DatePromised) <= ?");
			 else if (from != null && to == null)
				 sql.append(" AND TRUNC("+getTableName()+".DatePromised) >= ?");
			 else if (from != null && to != null)
				 sql.append(" AND TRUNC("+getTableName()+".DatePromised) BETWEEN ? AND ?");
		 }

		 log.fine("MRP Info.setWhereClause="+ sql.toString());
		 return sql.toString();        
	}
	
	private void fillHead()  
	 {
		 MPPProductPlanning pp = MPPProductPlanning.find(getCtx(), 
				 getAD_Org_ID(), getM_Warehouse_ID(), getS_Resource_ID(), getM_Product_ID(), null);
		 if (pp == null)
			 pp = new MPPProductPlanning(getCtx(), 0, null);
		 fMaster.setSelected(pp.isMPS());
		 fMRPReq.setSelected(pp.isRequiredMRP());
		 fCreatePlan.setSelected(pp.isCreatePlan());
		 fOrderPeriod.setValue(pp.getOrder_Period());
		 fLeadtime.setValue(pp.getDeliveryTime_Promised());
		 fTimefence.setValue(pp.getTimeFence());
		 fMinOrd.setValue(pp.getOrder_Min());
		 fMaxOrd.setValue(pp.getOrder_Max());
		 fOrdMult.setValue(pp.getOrder_Pack());
		 fOrderQty.setValue(pp.getOrder_Qty());
		 fYield.setValue(pp.getYield());
		 fType.setText(MRefList.getListName(getCtx(), MPPProductPlanning.ORDER_POLICY_AD_Reference_ID, pp.getOrder_Policy()));
		 fSafetyStock.setValue(pp.getSafetyStock());
	 } 
	
	private void setMRP()
	 {
		 int M_Product_ID = getM_Product_ID();
		 int M_AttributeSetInstance_ID = getM_AttributeSetInstance_ID();
		 int M_Warehouse_ID = getM_Warehouse_ID();
		 
		 //
		 // Check Product (mandatory):
		 if (M_Product_ID <= 0)
			 return;

		 //
		 // Set Quantities
		 PreparedStatement pstmt = null;                       
		 ResultSet rs = null;	
		 try
		 {                 
			 StringBuffer sql = new StringBuffer("SELECT ")
			 	.append("BOMQtyOnHandASI(M_Product_ID,?,?,?) as qtyonhand, ")
			 	.append("BOMQtyReservedASI(M_Product_ID,?,?,?) as qtyreserved, ")
			 	.append("BOMQtyAvailableASI(M_Product_ID,?,?,?) as qtyavailable, ")
			 	.append("BOMQtyOrderedASI(M_Product_ID,?,?,?) as qtyordered")
			 	.append(" FROM M_Product WHERE M_Product_ID=?");
			 pstmt = DB.prepareStatement(sql.toString(),null);
			 DB.setParameters(pstmt, new Object[]{
					 getM_AttributeSetInstance_ID(),getM_Warehouse_ID(),0,
					 getM_AttributeSetInstance_ID(),getM_Warehouse_ID(),0,
					 getM_AttributeSetInstance_ID(),getM_Warehouse_ID(),0,
					 getM_AttributeSetInstance_ID(),getM_Warehouse_ID(),0,
					 getM_Product_ID()
					 });
			 rs = pstmt.executeQuery();	
			 while (rs.next())
			 {	
				 fOnhand.setValue(rs.getBigDecimal(1));
				 fReserved.setValue(rs.getBigDecimal(2));                                                                
				 fAvailable.setValue(rs.getBigDecimal(3));                                                                
				 fOrdered.setValue(rs.getBigDecimal(4));                                                                
			 }
		 }
		 catch(SQLException ex)
		 {
			 throw new DBException(ex);
		 }
		 finally
		 {
			 DB.close(rs, pstmt);
			 rs = null; pstmt = null;
		 }
		 //
		 // Set UOM:
		 int uom_id = MProduct.get(getCtx(), M_Product_ID).getC_UOM_ID();
		 MUOM um = MUOM.get(getCtx(),uom_id);
		 KeyNamePair kum = new KeyNamePair(um.getC_UOM_ID(),um.get_Translation(MUOM.COLUMNNAME_Name));
		 fUOM.setText(kum.toString());
		 //
		 // Set Replenish Min Level:
		 BigDecimal replenishLevelMin = Env.ZERO;
		 if (getM_Warehouse_ID() > 0) {
			 String sql = "SELECT Level_Min FROM M_Replenish"
			 				+" WHERE AD_Client_ID=? AND M_Product_ID=? AND M_Warehouse_ID=?";
			 replenishLevelMin = DB.getSQLValueBD(null, sql, AD_Client_ID, M_Product_ID, M_Warehouse_ID);
		 }
		 fReplenishMin.setValue(replenishLevelMin);
	 }

	public void zoom()
	{
		super.zoom();

		//  Zoom
		AEnv.zoom(AD_Window_ID, query);  
	}
	
	void enableButtons ()
	 {
		 boolean enable = true;

		 confirmPanel.getOKButton().setEnabled(true);
		 if (hasHistory())
			 confirmPanel.getButton(ConfirmPanel.A_HISTORY).setEnabled(enable);
		 if (hasZoom())
			 confirmPanel.getButton(ConfirmPanel.A_ZOOM).setEnabled(enable);
	 }
	
	void executeQuery()
	{
		work();
	}
	

	
	 protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	 {
		 int index = 1;
		 if (getM_Product_ID() > 0)
		 {
			 int product_id = getM_Product_ID();
			 pstmt.setInt(index++, product_id);
			 log.fine("Product=" + product_id);
		 }

		 if (isAttributeSetInstance())
		 {
			 int asi = getM_AttributeSetInstance_ID();
			 pstmt.setInt(index++, asi);
			 log.fine("AttributeSetInstance=" + asi);
		 }
		 if (getS_Resource_ID() > 0)
		 {
			 int resource_id = getS_Resource_ID();
			 pstmt.setInt(index++, resource_id);
			 log.fine("Resource=" + resource_id);
		 }
		 if (getM_Warehouse_ID() > 0)
		 {
			 int warehouse_id = getM_Warehouse_ID();
			 pstmt.setInt(index++, getM_Warehouse_ID());
			 log.fine("Warehouse=" + warehouse_id);
		 }
		 if (getPlanner_ID() > 0)
		 {
			 int planner_id = getPlanner_ID();
			 pstmt.setInt(index++, planner_id);
			 log.fine("Planner=" + planner_id);
		 }
		 if (getDueStart() != null || getDueEnd() != null)
		 {
			 Timestamp from = getDueStart();
			 Timestamp to = getDueEnd();
			 log.fine("Date From=" + from + ", Date To=" + to);
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
	
	protected int getM_Product_ID() {
		 Object o = fProduct_ID.getValue();
		 return o != null && (o instanceof Integer) ? (Integer)o : Integer.valueOf(0);    
	 }
	 
	 protected int getM_AttributeSetInstance_ID() {		
		 return ASI_ID; 
	 }
	 

	 protected int getAD_Org_ID() {
		 int warehouse_id = getM_Warehouse_ID();
		 if (warehouse_id <= 0)
			 return 0;
		 return MWarehouse.get(getCtx(), warehouse_id).getAD_Org_ID();
	 }
	 
	 protected int getM_Warehouse_ID() {
		 Object o = fWarehouse_ID.getValue();
		 return o != null && (o instanceof Integer) ? (Integer)o : Integer.valueOf(0);    
	 }
	 
	 protected int getS_Resource_ID() {
		 Object o = fResource_ID.getValue();
		 return o != null && (o instanceof Integer) ? (Integer)o : Integer.valueOf(0);    
	 }
	 
	 protected int getPlanner_ID() {
		 Object o = fPlanner_ID.getValue();
		 return o != null && (o instanceof Integer) ? (Integer)o : Integer.valueOf(0);    
	 }
	 
	 protected Timestamp getDueStart() {
		 return (Timestamp)fDateFrom.getValue();
	 }
	 
	 protected Timestamp getDueEnd() {
		 return (Timestamp)fDateTo.getValue();
	 }
	 
	 protected BigDecimal getQtyOnHand() {
		 BigDecimal bd = (BigDecimal)fOnhand.getValue();
		 return bd != null ? bd : Env.ZERO; 
	 }
	@Override
	public void onChange(ListDataEvent event) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void tableChanged(WTableModelEvent event) {
		// TODO Auto-generated method stub
		
	}



	public Integer getSelectedRowKey()
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
	 }



	@Override
	public void zoom(int AD_Window_ID, MQuery zoomQuery) {
		// TODO Auto-generated method stub
		
	}
	

		 public void work()
		 {
			 log.fine("Info.Worker.run");
      
			 StringBuilder sql = new StringBuilder (m_sqlMain);

			 String dynWhere = getSQLWhere();
			 if (dynWhere.length() > 0)
			 {   System.out.println("where" +dynWhere);
			 sql.append(dynWhere);   //  includes first AND
			 }
			 StringBuilder sqlFinal = new StringBuilder (MRole.getDefault().addAccessSQL(Msg.parseTranslation(getCtx(), sql.toString()), getTableName(), 
					 MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO));  
			 sqlFinal.append(" ORDER BY DatePromised,ProductValue");
			 
			 try
			 {
				 PreparedStatement pstmt = DB.prepareStatement(sqlFinal.toString(),null);
				 log.fine("SQL=" + sqlFinal.toString());
				 setParameters (pstmt, false);
				 ResultSet rs = pstmt.executeQuery();
				 p_table.loadTable(rs);
				
				 rs.close();
				 pstmt.close();
			 }
			 catch (SQLException e)
			 {				
				 log.log(Level.SEVERE, "Info.Worker.run - " + sqlFinal.toString(), e);
			 }
			

			 if (getM_Product_ID() > 0) {
				 BigDecimal OnHand = getQtyOnHand();
				 for (int row=0; row < p_table.getRowCount(); row++)
				 {
					 Timestamp datepromised = (Timestamp)p_table.getValueAt(row,5); 
					 Timestamp today = new Timestamp (System.currentTimeMillis());
					 IDColumn id = (IDColumn)p_table.getValueAt(row,0);
					 String TypeMRP = DB.getSQLValueString(null, "SELECT TypeMRP FROM "+ getTableName() + " WHERE PP_MRP_ID=?", id.getRecord_ID());
					 String OrderType = (String) p_table.getValueAt(row,11);
					 if (MPPMRP.TYPEMRP_Demand.equals(TypeMRP)
							 || (MPPMRP.ORDERTYPE_Forecast.equals(OrderType) // TODO: arhipac: teo_sarca: is this ok, since gross req = sum of all demands ??? 
							 && datepromised.after(today))
					 	)
					 {
						 BigDecimal QtyGrossReqs =  (BigDecimal)p_table.getValueAt(row,6);   
						 OnHand = OnHand.subtract(QtyGrossReqs);
						 p_table.setValueAt(OnHand,row,9);           			
					 }
					 if (MPPMRP.TYPEMRP_Supply.equals(TypeMRP))
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
			 }

		 }
	 
}
