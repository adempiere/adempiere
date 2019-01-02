package com._3e.ADInterface;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import java.util.logging.Level;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.codehaus.xfire.fault.XFireFault;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.GridTabVO;
import org.compiere.model.GridWindowVO;
import org.compiere.model.Lookup;
import org.compiere.model.MCountry;
import org.compiere.model.MLocation;
import org.compiere.model.MLookup;
import org.compiere.model.MQuery;
import org.compiere.model.MRegion;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Login;
import org.compiere.util.ValueNamePair;
import org.spin.util.XMLUtils;
import org.w3c.dom.Document;

import pl.x3E.adInterface.ADLoginRequest;
import pl.x3E.adInterface.ADLoginRequestDocument;
import pl.x3E.adInterface.ADLoginResponse;
import pl.x3E.adInterface.ADLoginResponseDocument;
import pl.x3E.adInterface.ADMenuDocument;
import pl.x3E.adInterface.ADMenuItem;
import pl.x3E.adInterface.ADMenuItemList;
import pl.x3E.adInterface.DataField;
import pl.x3E.adInterface.DataRow;
import pl.x3E.adInterface.DataSet;
import pl.x3E.adInterface.DocAction;
import pl.x3E.adInterface.DocActionDocument;
import pl.x3E.adInterface.Field;
import pl.x3E.adInterface.FieldList;
import pl.x3E.adInterface.GetLookupSearchDataReq;
import pl.x3E.adInterface.GetLookupSearchDataReqDocument;
import pl.x3E.adInterface.GetProcessParamsDocument;
import pl.x3E.adInterface.Location;
import pl.x3E.adInterface.LocationDocument;
import pl.x3E.adInterface.LookupInfo;
import pl.x3E.adInterface.LookupValue;
import pl.x3E.adInterface.LookupValues;
import pl.x3E.adInterface.ProcessParamsDocument;
import pl.x3E.adInterface.RunProcessDocument;
import pl.x3E.adInterface.RunProcessResponseDocument;
import pl.x3E.adInterface.StandardResponse;
import pl.x3E.adInterface.StandardResponseDocument;
import pl.x3E.adInterface.Tab;
import pl.x3E.adInterface.TabList;
import pl.x3E.adInterface.Window;
import pl.x3E.adInterface.WindowDocument;
import pl.x3E.adInterface.WindowTabData;
import pl.x3E.adInterface.WindowTabDataDocument;
import pl.x3E.adInterface.WindowTabDataReq;
import pl.x3E.adInterface.WindowTabDataReqDocument;

/*
 * ADEMPIERE/COMPIERE
 * 
 * replacement:
 * GridField by GridFieldVO
 * GridTabVO by GridTabVO
 * GridWindowVO by GridWindowVO	
 * 
 * Contributors: Carlos Ruiz - globalqss
 *     Add model oriented method modelSetDocAction
 *     Some Polish messages translated to english using google translate
 */


/**
 * 
 * @author kolec
 *
 */
public class ADServiceImpl implements ADService {

	private static CLogger	log = CLogger.getCLogger(ADServiceImpl.class);
	
	private static String webServiceName = new String("ADService");
	
	private CompiereService m_cs;
	
	private static final int MAX_ROWS = 200;
		
	public ADServiceImpl()
	{
		m_cs = new CompiereService();
		m_cs.connect();			
		
		log.info("Creating session object ADService");
	}
	
	public String getVersion() {
		return "0.7.0";
	}
	
	public boolean isLoggedIn() {
		
		return m_cs.isLoggedIn();
	}
	

	private void fillField( Field f, GridFieldVO fo )  {  ////(griddieldvo) adempiere specific
    	f.setADColumnID( fo.AD_Column_ID );    	
    	f.setADProcessID( fo.AD_Process_ID );
    	f.setADReferenceValueID( fo.AD_Reference_Value_ID );
    	f.setADWindowID( fo.AD_Window_ID );
    	f.setCallout( fo.Callout );
    	f.setColumnName( fo.ColumnName );
    	f.setDefaultValue( fo.DefaultValue );
    	f.setDefaultValue2( fo.DefaultValue2 );
    	f.setDescription( fo.Description );
    	f.setDisplayLength( fo.DisplayLength );
    	f.setDisplayLogic( fo.DisplayLogic );
    	f.setDisplayType( fo.displayType );
    	f.setFieldGroup( fo.FieldGroup );
    	f.setFieldLength( fo.FieldLength );
    	f.setHeader( fo.Header );
    	f.setHelp( fo.Help);
    	f.setIsAlwaysUpdateable( fo.IsAlwaysUpdateable );
    	f.setIsDisplayed( fo.IsDisplayed );
    	f.setIsEncryptedColumn( fo.IsEncryptedColumn );
    	f.setIsEncryptedField( fo.IsEncryptedField );
    	f.setIsFieldOnly( fo.IsFieldOnly );
    	f.setIsHeading( fo.IsHeading );
    	f.setIsKey( fo.IsKey);
    	f.setIsMandatory( fo.IsMandatory );
    	f.setIsParent( fo.IsParent );
    	f.setIsProcess( fo.isProcess );
    	f.setIsRange( fo.IsRange );
    	f.setIsReadOnly( fo.IsReadOnly );
    	f.setIsSameLine( fo.IsSameLine );
    	f.setIsSelectionColumn( fo.IsSelectionColumn );
    	f.setIsUpdateable( fo.IsUpdateable );
    	
    	if (DisplayType.isLookup( fo.displayType ))	{    		
    		GridField ff = new GridField( fo );
    		ArrayList<String> deps = ff.getDependentOn();
    		Lookup lookup = ff.getLookup();
    		
    		LookupInfo li = f.addNewLookupInfo();
    		if (fo.lookupInfo!=null) {    			
    			li.setZoomWindow( fo.lookupInfo.ZoomWindow );
    			li.setZoomWindow( fo.lookupInfo.ZoomWindow );
    		}
    		
    		//if (deps.size()==0)
    		if (lookup!=null && (fo.ValidationCode==null || (fo.ValidationCode!=null && fo.ValidationCode.length()==0))) {
    			LookupValues lvs = f.addNewLookup();
    			//System.out.println( "lookup "+fo.ColumnName+" "+lookup.getSize() );

    			//if(lookup.size() == 0) - nie robic tego
				//	System.out.println("lookup refresh ["+fo.ColumnName+"]= "+lookup.refresh());
				/*if(lookup.getSize() > 0)*/ 
					ArrayList ar = lookup.getData(ff.isMandatory(false), true, !ff.isReadOnly(), true); // the last was false, 2007-05-11
					if (ar != null && ar.size()>0) {
					Object[] list = ar.toArray();										
									
					for (int i=0; i<list.length; i++)  {
						boolean isNumber = list[0] instanceof KeyNamePair;
						
						LookupValue lv = lvs.addNewLv();
						
						if (isNumber) {
							KeyNamePair p = (KeyNamePair)list[i];												
							lv.setKey( Integer.toString(p.getKey()) );
							lv.setVal( p.getName() );							
							//System.out.println( "LV " + p.getKey() + " - "+ p.getName() );						
						} else 	{
							ValueNamePair p = (ValueNamePair)list[i];
							lv.setKey( p.getValue()  );
							lv.setVal( p.getName() );
							//System.out.println( "LV " + p.getValue() + " - "+ p.getName());
						}
						
					}						
				}

    		}
    		
    		
    	}
    }

    private void fillTab( Tab t, GridTabVO to )  {
    	t.setADColumnID( to.AD_Column_ID );
    	t.setADColumnSortOrderID( to.AD_ColumnSortOrder_ID );
    	t.setADImageID( to.AD_Image_ID );
    	t.setADProcessID( to.AD_Process_ID );
    	t.setADTabID( to.AD_Tab_ID );
    	t.setADTableID( to.AD_Table_ID );
    	t.setADWindowID( to.AD_Window_ID );
    	t.setDescription( to.Description );
    	t.setName( to.Name );
    	t.setIsSingleRow( to.IsSingleRow );
    	t.setCommitWarning( to.CommitWarning );
    	t.setHasTree( to.HasTree );
    	t.setDisplayLogic( to.DisplayLogic );
    	t.setHelp( to.Help );
    	t.setIncludedTabID( to.Included_Tab_ID );
    	t.setIsDeleteable( to.IsDeleteable );
    	t.setIsHighVolume( to.IsHighVolume );
    	t.setIsInsertRecord( to.IsInsertRecord );
    	t.setIsReadOnly( to.IsReadOnly );
    	t.setIsSecurityEnabled( to.IsSecurityEnabled );
    	t.setIsSingleRow( to.IsSingleRow );
    	t.setIsSortTab( to.IsSortTab );
    	//t.setIsSoTrx( to.)
    	t.setIsView( to.IsView );
    	t.setReadOnlyLogic( to.ReadOnlyLogic );
    	t.setTableName( to.TableName );
    	t.setTabLevel( to.TabLevel );

    	// ADEMPIERE/COMPIERE
    	
    	ArrayList<GridFieldVO>Fields = to.getFields(); // adempiere
    	//ArrayList<GridFieldVO>Fields = to.Fields; // compiere
    	
    	if (Fields !=null) {
    		FieldList fl = t.addNewFields(); 
	        for (int i=0; i<Fields.size(); i++) 	{
	    		fillField( fl.addNewField(), (GridFieldVO)Fields.get(i) );
	    	}
    	}
    }
    
    private void fillWindow( Window w, GridWindowVO wo )
    {
    	w.setADTableID( 0 ); //wo.AD_Table_ID );  //TODO
    	w.setADWindowID( wo.AD_Window_ID );
    	w.setIsSoTrx( wo.IsSOTrx );
    	w.setName( wo.Name );
    	w.setWindowNo( wo.WindowNo );
    	w.setHelp( wo.Help );
    	w.setDescription( wo.Description );
    	w.setWinHeight( wo.WinHeight );
    	w.setWinWidth( wo.WinHeight );
    	w.setIsReadWrite( "Y".equals(wo.IsReadWrite) );
    	w.setADImageID( wo.AD_Image_ID );
    	w.setADColorID( wo.AD_Color_ID );

    	
    	if (wo.Tabs!=null)
    	{
    		TabList tl = w.addNewTabs();
	    	for (int i=0; i<wo.Tabs.size(); i++)
	    	{
	    		fillTab( tl.addNewTab(), (GridTabVO)wo.Tabs.get(i) );
	    	}
    	}
    	
    }
    
    private HashMap WindowVOCache =new HashMap();
    private HashMap WindowCache =new HashMap();
    
    
    private GridWindowVO getWindowVO( int WindowNo, int AD_Window_ID, int AD_Menu_ID)
    {		
    	GridWindowVO w = (GridWindowVO)WindowVOCache.get(""+AD_Window_ID+"_"+AD_Menu_ID+"_"+WindowNo);
    	if (w != null)
    			return w;
    	w = GridWindowVO.create(m_cs.getM_ctx(), WindowNo, AD_Window_ID, AD_Menu_ID);
    	//create(m_cs.getM_ctx(), WindowNo, AD_Window_ID);
    	if (w!=null) 
    		WindowVOCache.put(""+AD_Window_ID+"_"+AD_Menu_ID+"_"+WindowNo, w);
    	return w;
    }
    
    public WindowDocument getADWindow(int WindowNo, int AD_Window_ID, int AD_Menu_ID) throws XFireFault   {
    	authenticate(webServiceName, "getADWindow");
    	
    	WindowDocument wc = (WindowDocument)WindowCache.get(new String(""+AD_Window_ID+"_"+AD_Menu_ID));
    	//if (wc != null)			return wc;
    	
    	WindowDocument res = WindowDocument.Factory.newInstance();
    	Window w = res.addNewWindow();
    	GridWindowVO wo = getWindowVO( WindowNo, AD_Window_ID, AD_Menu_ID);

    	if (wo!=null)
    	{
    	  fillWindow(w, wo); 

    	  WindowCache.put( new String(""+AD_Window_ID+"_"+AD_Menu_ID), res);
    	}
    	
    	return res;
    }
    
    

    /*<r>
    <f colname="AD_Client_ID">1000000</f>
    </r>
    
    <AD_Client_ID>1000000</AD_Client_ID>
    */
    
//	method to convert Document to String
	public String getStringFromDocument(Document doc)
	{
	    try
	    {
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       XMLUtils.setDefaultFeatures(tf);
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       return writer.toString();
	    }
	    catch(TransformerException ex)
	    {
	       ex.printStackTrace();
	       return null;
	    }
	}
	

	private Map WindowStatusMap = new HashMap(); 

	/*
	public WindowTabDataDocument getWindowTabData(int WindowNo, int AD_Window_ID, int AD_Menu_ID, int TabNo, int PrevTabNo, int PrevRecNo, boolean getData)	{
		return getWindowTabData(WindowNo, AD_Window_ID, AD_Menu_ID, TabNo, PrevTabNo, PrevRecNo, getData, 0, -1); 
	}
	
    public WindowTabDataDocument getWindowTabData(int WindowNo, int AD_Window_ID, int AD_Menu_ID, int TabNo, int PrevTabNo, int PrevRecNo, boolean getData, int RowStart, int RowCount)  
    {
    	return null; //TODO
    	
    }*/
    
    private MQuery createQuery( String table_name, DataRow dr ) {
    	MQuery q = new MQuery(table_name);
    	DataField df[] = dr.getFieldArray();
    	for (int i=0; i<df.length; i++) {
    		if (df[i].getVal()!=null && df[i].getVal().length()>0 )
    		{
   			 q.addRestriction("UPPER("+df[i].getColumn()+")", MQuery.LIKE, df[i].getVal().toUpperCase(), df[i].getColumn(), df[i].getVal().toUpperCase());  			 
    		}
    	}
    	return q;
    }
    
    
    void authenticate(String webServiceName, String method) throws XFireFault {
    	if (!m_cs.isLoggedIn()) 
    		throw new XFireFault( new Exception( "You need to login" ) );
    	// TODO: Authenticate webservice and method
		// TODO: Search for a service type for client and role access with the same value as the method
    	
    	// TODO: Increase security!
    }

    public WindowTabDataDocument getWindowTabData(WindowTabDataReqDocument reqd) throws XFireFault {
    	authenticate(webServiceName, "getWindowTabData");

    	WindowTabDataReq req = reqd.getWindowTabDataReq();
    	WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();
    	
    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, req.getWindowNo(), false, 0, false, 0);
    	if (ws == null)
    	{
    		GridWindowVO wo = getWindowVO( req.getWindowNo(), req.getADWindowID(), req.getADMenuID() );
    		ws = new WWindowStatus(wo);
    		WindowStatusMap.put(new Integer(req.getWindowNo()), ws);
    		    		
        	ws.curTab.query(ws.mWindow.isTransaction()); //!!!!!!!!!!
        	ws.curTab.navigate(0);		
        	ws.curTab.setSingleRow(true);
    	}   	

    	if (ws.curTab.getTabNo() != req.getPrevTabNo()) 
    	{
			ws.curTab.removeDataStatusListener(ws.ads);
			ws.curTab = ws.mWindow.getTab(req.getPrevTabNo());
			ws.curTab.query(ws.mWindow.isTransaction()); // false
			ws.curTab.navigate(0);
			ws.updateRecIDMap();
    	}
    	/*
    	if (ws.curTab.getCurrentRow() != req.getPrevRecNo())  
    	{
    		if (req.getPrevRecNo() >=0)
    			ws.curTab.navigate( req.getPrevRecNo() );     		
    	}
    	*/
    	int prevRecNo = ws.getRowNoFromRecordID( req.getPrevRecNo()); // we assume that it RecordID
    	if (ws.curTab.getCurrentRow() != prevRecNo)  
    	{
    		if (prevRecNo >=0)
    			ws.curTab.navigate( prevRecNo );     		
    	}
    	
    	
    	WWindowStatus.changeTabIfNeeded( ws, req.getTabNo() );
    	
    	if (req.getGetData())
    	{   
    		if (req.getFromZoom()) {
    			WWindowStatus ws2 = WWindowStatus.get(WindowStatusMap, req.getFromZoomWindowID(), true, req.getFromZoomTabID(), true, req.getFromZoomRowID());
    			System.out.println(ws2.curTab.getTableName());    			    			
        		GridField field = ws2.curTab.getField(req.getFromZoomColumnName());        		
        		ws2 = null;
        		if (field == null) return null;
        		MLookup lookup = (MLookup)field.getLookup(); 
        		if (lookup == null)          			
        			return null;
        		//
        		MQuery zoomQuery = lookup.getZoomQuery();
        		Object value = field.getValue();
        	    if (value == null)
        	    {
        		    value = req.getFromZoomColumnValue();
        	    }
        		//	If not already exist or exact value
        		if (zoomQuery == null || value != null)
        		{
        			zoomQuery = new MQuery();	//	ColumnName might be changed in GridTab.validateQuery
        			zoomQuery.addRestriction( req.getFromZoomColumnName(), MQuery.EQUAL, value);
        		}
       			ws.curTab.setQuery(zoomQuery);	       			
        		//ws.curTab.query(ws.mWindow.isTransaction());
       			
       			// ADEMPIERE/COMPIERE
        		ws.curTab.query(false, 0, 0); // adempiere
        		//ws.curTab.query(false, 0); // compiere
        		
    		} else 
    		{
    			DataRow findDR = req.getFindCriteria();
    			MQuery currentQuery = ws.curTab.getQuery(); 	
    			MQuery newQuery = createQuery( ws.curTab.getTableName(), findDR );    			
        		if  (findDR.getFieldArray().length>0) { //(!currentQuery.getWhereClause().equals( newQuery.getWhereClause() )) { // change the query for zak�adki
        			ws.curTab.setQuery(newQuery);	
        			//ws.curTab.query(ws.mWindow.isTransaction());
        			
        			//ADEMPIERE/COMPIERE
        			ws.curTab.query(false, 0, 0); // adempiere
        			//ws.curTab.query(false, 0); // compiere
        		}
    		}
    		
    		    		
    			
	    	int rc = 0;
	    	if (req.getRowCount()>0)
	    		rc = req.getRowCount(); else rc = ws.curTab.getRowCount();						
			int initRowNo = 0;		
			if (req.getRowStart() > 0) initRowNo = req.getRowStart();
			
			int lastRow =  Math.min(rc, initRowNo + MAX_ROWS); //ok
				//initRowNo + 5; // only for testing
			
			wd.setNumRows( lastRow );
			//lastRow += initRowNo;
			
			wd.setTotalRows( ws.curTab.getRowCount() ); // ok
			//wd.setTotalRows( 5 ); // only for testing
			
			wd.setStartRow(initRowNo);
			
			Map<Integer, Integer> RecordIDMap = ws.getRecordIDMap();
			try {
				RecordIDMap.clear();
				for (int lineNo = initRowNo; lineNo < lastRow; lineNo++)
				{
					ws.curTab.navigate(lineNo);
					
					int recID = ws.curTab.getRecord_ID();
					RecordIDMap.put( recID, lineNo );
					
					DataRow dr = ds.addNewDataRow();
					//System.out.println("row "+lineNo);
					fillDataRow( dr, ws, false, false );					
		
				} 
			} catch (Exception ex) { ex.printStackTrace(); };
			
			
	    	if ( lastRow <= ws.curTab.getRowCount() ) {  //last row
	    		ws.updateRecIDMap();
	    	}
    	}
    	

    	return ret;

    }
    

    
    public WindowTabDataDocument getDataRow(int WindowNo, int TabNo, int RowNo )  throws XFireFault  {
    	authenticate(webServiceName, "getDataRow");
    	
    	WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();    	
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();
    	
    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, true, TabNo, true, RowNo);
    	if (ws != null)
    	{
    		DataRow dr = ds.addNewDataRow();
    		fillDataRow( dr, ws, true, false );
    		
    		if (ws.ads.m_is_error)
    		{        			
				wd.setError(ws.ads.m_error_message );
				wd.setErrorInfo(ws.ads.m_error_info );
    		}
    		wd.setStatus(ws.ads.m_status_data);
    		wd.setStatusError(ws.ads.m_is_status_error);          		
    	}
    	    	
    	return ret;
    }
    
    public WindowTabDataDocument updateDataRow(int WindowNo, int TabNo, int RowNo, WindowTabDataDocument data )  throws XFireFault  {
    	authenticate(webServiceName, "updateDataRow");
    	
    	WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();
    	DataRow ret_dr = ds.addNewDataRow();

    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, true, TabNo, true, RowNo);
    	if (ws != null)
    	{
        	DataRow[] dr = data.getWindowTabData().getDataSet().getDataRowArray();
        	if (dr.length == 1)
        	{
        		DataRow dr0 = dr[0];
        		boolean err = updateFields( ws, dr0 );
        		
        		if (ws.ads.m_is_error)
        		{        			
    				wd.setError(ws.ads.m_error_message );
    				wd.setErrorInfo(ws.ads.m_error_info );
        		}
        		wd.setStatus(ws.ads.m_status_data);
        		wd.setStatusError(ws.ads.m_is_status_error);        		
        	        		
        		//ws.curTab.dataRefresh();
        		fillDataRow( ret_dr, ws, true, false );

        	}
        	    		
    	}
    	
    	return ret;
    }

    private final String recordIDfield = "_rowNo";
    

    private void fillDataRow( DataRow dr, WWindowStatus ws, boolean handleLookups, boolean onlyUpdated )   throws XFireFault {
    	authenticate(webServiceName, "fillDataRow");
    	
    	int noFields = ws.curTab.getFieldCount();
    	
		//m_cs.dateFormat = new SimpleDateFormat( m_cs.datePattern );
		//m_cs.dateTimeFormat = new SimpleDateFormat( m_cs.datePattern );
    			
    	String column = "";
    	boolean isEditable = false;
		//  for all columns
    	
    	if (onlyUpdated) {
	    	java.util.Date clientUpdated = new java.util.Date();		    	
			java.util.Date updated = (java.util.Date)ws.curTab.getValue("Updated");
			if (!updated.after(clientUpdated)) {
				DataField df = dr.addNewField();
				df.setColumn( recordIDfield );
				df.setDisp(false);
				df.setVal( Integer.toString( ws.curTab.getRecord_ID() ));
				return;
			}
    	}
    	    	    	
		for (int colNo = 0; colNo < noFields; colNo++)
		{				
			GridField field = ws.curTab.getField(colNo);
			column = field.getColumnName();

						
			if (!field.isDisplayed(true)) 
			{
				DataField df = dr.addNewField();
				df.setColumn( column );
				df.setDisp(false);
				df.setVal("" );
				//System.out.println("   *** not displayed: "+field.getColumnName()+"  | "+field.getDisplayLogic());
				continue;
			}
				
			if (field.getDisplayType() == DisplayType.Button)
				continue;
			
			DataField df = dr.addNewField();	
			
			isEditable = field.isEditable(true);
			df.setEdit(isEditable);
			
			//  Get Data - turn to string
			Object data = ws.curTab.getValue( column );
			String info = null;
			//System.out.println( "     displaytype = "+field.getDisplayType());
			//
			if (data == null && !DisplayType.isLookup( field.getDisplayType()))
				info = "";
			else
			{
				int dt = field.getDisplayType();
				switch (dt)
				{

					case DisplayType.Date:
						info = m_cs.dateFormat.format(data);
						//System.out.println( "Date: "+info );
						break;
					case DisplayType.DateTime:
						// TODO
						info = m_cs.dateFormat.format(data); //m_cs.dateTimeFormat.format(data);
						//System.out.println( "DateTime: "+info ); 
						break;
					case DisplayType.Amount:						
						info = m_cs.amountFormat.format(data);
						break;
					case DisplayType.Number:
					case DisplayType.CostPrice:
						info = m_cs.numberFormat.format(data);
						break;
					case DisplayType.Quantity:
						info = m_cs.quantityFormat.format(data);
						break;
					case DisplayType.Integer:
						info = m_cs.integerFormat.format(data);
						break;
					case DisplayType.YesNo:
						info = data.toString();
						if ("Y".equals(info)) info="true";
						if ("N".equals(info)) info="false";
						//info = Msg.getMsg(ws.ctx, data.toString());
						break;
					case DisplayType.Location:
						info = data.toString();
						if (handleLookups) {
						  String x = DB.getSQLValueString(null, "select l.address1||', '||l.postal||', '||l.city from c_location l where c_location_id=?", Integer.parseInt(info));
						  df.setLval( x );
						  System.out.println( "  location = "+x );
						}
						break;
					
					default:
						if (DisplayType.isLookup(dt))
						{
							Lookup lookup = field.getLookup();
							//ArrayList<String> deps = field.getDependentOn();						
														
							String lookupValue = null;
							if (field.getValue()!=null)
							{
									lookupValue = lookup.getDisplay( field.getValue() );
							}
							//lookup.refresh();
							if (data!=null)
							{
								  info = lookup.getDisplay(data);
								  if (info==null)
								  {
									  lookup.refresh();
									  info = lookup.getDisplay(data);
								  }
							}
							df.setLval(lookupValue);
							
							//System.out.println(field.getVO().ValidationCode);
							
							if (handleLookups /*&& isEditable*/ && lookup!=null 
									&& (field.getVO().ValidationCode!=null && field.getVO().ValidationCode.length()>0))//deps.size()>0) 
							{
								if (data!=null)
									  info = lookup.getDisplay(data);							
		

								LookupValues lvs = df.addNewLookup();

								ADLookup.fillLookupValues( lvs, lookup, field );
									
							}
							if (data!=null) 
								info = data.toString();

						}
						else
						{
							info = data.toString();
							//System.out.println(">>>>>>>> UNKNOWN > "+field.getColumnName() +"  = "+info);
						}
				}				
			}
			//System.out.println("		"+column+" = "+info);
			if ("M_Product_ID".equals(field.getColumnName())) 
				System.out.println("---  "+info);
			
			df.setDisp(true);			
			df.setVal( info );
			df.setColumn(field.getColumnName());
			if (field.isError())
			{
				df.setError(true);
				df.setErrorVal(field.getErrorValue());
			}			
		}	 
		DataField df = dr.addNewField();
		df.setColumn( recordIDfield );
		df.setVal(Integer.toString( ws.curTab.getRecord_ID() ));
    }
    

    
    public WindowTabDataDocument saveDataRow(int WindowNo, int TabNo, int RowNo, WindowTabDataDocument data ) throws XFireFault {
    	authenticate(webServiceName, "saveDataRow");

    	WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();
    	DataRow ret_dr = ds.addNewDataRow();
    	
    	    	
    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, false, 0, true, RowNo);
    	if (ws != null)
    	{	    		    		 
        	DataRow[] dr = data.getWindowTabData().getDataSet().getDataRowArray();
        	if (dr.length == 1)
        	{
        		DataRow dr0 = dr[0];
        		boolean error = updateFields( ws, dr0 );

        		DataField f[] = dr0.getFieldArray();
        		HashMap fmap = new HashMap();
        		for (int i=0; i<f.length; i++)
        			fmap.put(f[i].getColumn(), f[i].getVal());
        		
        		
           		//  Check Mandatory
        		int size = ws.curTab.getFieldCount();
        		for (int i = 0; i < size; i++)
        		{
        			GridField field = ws.curTab.getField(i);
        			if (field.isMandatory(true))        //  context check
        			{
        				Object value = new Object();
        				//jesli tak nie zrobimy, mozemy stracic wartosc
        				if(field.getValue() == null && fmap.get(field.getColumnName()) != null)
        					value = fmap.get(field.getColumnName());//field.getValue();
        				else
        					value = field.getValue();

        				//if (value!=null)
        				//	System.out.println("Field Save     "+field.getColumnName()+" = "+value.toString());
        				
        				if ( (value == null || value.toString().length() == 0)  )
        				{
        					field.setInserting (true);  //  set editable otherwise deadlock
        					field.setError(true);
        					field.setErrorValue(value == null ? null : value.toString());
        					if (!error)
        						error = true;
        					
        					System.out.println("Mandatory Error: " + field.getColumnName());
        					//log.info("Mandatory Error: " + field.getColumnName());
        				}
        				else
        					field.setError(false);
        			}
        		}
        		        		
        		//  save it - of errors ignore changes
        		wd.setSuccess( ws.curTab.dataSave(true) );
        			;//ws.curTab.dataIgnore();
        		//else
        		//	fillDataRow( ret_dr, ws, true);          	
          	       		
        		fillDataRow( ret_dr, ws, true, false );
        		if (ws.ads.m_is_error)
        		{        			
    				wd.setError(ws.ads.m_error_message );
    				wd.setErrorInfo(ws.ads.m_error_info );
        		} else {
        			updateRecIDMap( ws );
        		}
        		wd.setStatus(ws.ads.m_status_data);
        		wd.setStatusError(ws.ads.m_is_status_error);         		        		
        		
        		ws.m_needSave = false;
        		//log.fine("done");
        		
        	}        	               
    	}
    	
    	return ret;
    }

    
    
	/** Error Indicator                     */
	private static final String ERROR       = " ERROR! ";

    
	/**
	 * 	Update Field Values from Parameter
	 *	@param request request
	 *	@param wsc session context
	 *	@param ws window status
	 *	@return true if error
	 */
	private boolean updateFields(WWindowStatus ws, DataRow dr)
	{
	
		boolean error = false;
		Enumeration en = null; //request.getParameterNames();
		DataField[] df = dr.getFieldArray();
		DataField f;
		for (int i=0; i<df.length; i++)
		{
			
			//String key = (String)en.nextElement();
			f = df[i];
			String cn =  f.getColumn();
			GridField GridField = ws.curTab.getField( cn  );
			
			if (!GridField.isDisplayed(false))
				continue;
			if (GridField.getDisplayType() == DisplayType.Button) // kolec - byl dopisany warunek na 'DocAction' ale to nie jest ok:  && !cn.equals("DocAction")
				continue;

			if (GridField != null && GridField.isEditable(true))
			{
				String value = f.getVal(); //!!!!

				if (value.length()==0) value=null; // kolec (plaba -cos wstawia &nbsp; do input type="text")
			    if (value != null && (value.equals("-1")) && GridField.isLookup()) value = null; //kolec
			    
				if (GridField.getDisplayType() == DisplayType.YesNo )
				{				
					if (value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")))
							value = value.toLowerCase();
					//if (value != null && value.equalsIgnoreCase("null"))
					//	value = "false";
					//if (value == null)
					//	value = "false";
				}							   
			    
				Object dbValue = GridField.getValue();
				boolean fieldError = false;
				String columnName = GridField.getColumnName();

				/*
				System.out.println( 
				columnName 
					+ ": " + (dbValue==null ? "null" : dbValue.toString()) 
					+ " -> " + (value==null ? "null" : value.toString()));
									//  same = both null
									 * 
									 */
				
				if (dbValue == null && value == null)
					continue;
				//   new value null
				else if (dbValue != null && value == null)
					ws.curTab.setValue (GridField, null);
				//  from null to new value
				else if (dbValue == null && value != null)
				{					
					fieldError = !setFieldValue (ws, GridField, value);
				}
				//  same
				
				else if (dbValue.equals(value))
					continue;
				else			
					fieldError = !setFieldValue (ws, GridField, value);
				
					//
				if (!error && fieldError)
				{
					//log.info("Error: " + GridField.getColumnName());
					error = true;
				}
			}
		}   //  for all parameteres
		
		//	Re-Do Changed Column to overwrite
		/*
		String columnName = f.getColumn(); //WebUtil.getParameter (request, P_ChangedColumn);
		if (columnName != null && columnName.length() > 0)
		{
			ws.m_needSave = true;
			GridField GridField = ws.curTab.getField(columnName);
			if (GridField != null)
			{
				String value = "";//WebUtil.getParameter(request, columnName);
				Object newValue = getFieldValue (GridField, value);
				if (!ERROR.equals(newValue))
				{
					//	De-Selected Check Boxes are null 
					if (newValue == null && GridField.getDisplayType() == DisplayType.YesNo)
						newValue = "N";
					//log.fine("ChangedColumn: " + columnName + "=" + newValue);
					ws.curTab.setValue(GridField, newValue);
				}
			}
		}
		*/

		return error;
	}	//	updateFields

	
	/**************************************************************************
	 *  Set Field Value
	 *  @param wsc web session
	 *  @param ws window status
	 *  @param GridField field
	 *  @param value as String
	 *  @return true if correct
	 */
	private boolean setFieldValue ( WWindowStatus ws, 
		GridField GridField, String value)
	{
		Object newValue = getFieldValue (GridField, value);
		if (ERROR.equals(newValue))
		{
			GridField.setErrorValue(value);
			return false;
		}
		Object dbValue = GridField.getValue();
		if ((newValue == null && dbValue != null)
				|| (newValue != null && !newValue.equals(dbValue)))
		{
			//GridField.setValue(newValue,true);
			ws.curTab.setValue(GridField, newValue);
			
		}
		return true;
	}   //  setFieldValue

	
	/**
	 *  Get Field value (convert value to datatype of GridField)
	 *  @param wsc session context
	 *  @param GridField field
	 *  @param value String Value
	 *  @return converted Field Value
	 */
	private Object getFieldValue ( GridField GridField, String value)
	{
		if (value == null || value.length() == 0)
			return null;

		int dt = GridField.getDisplayType();
		String columnName = GridField.getColumnName();

		//  BigDecimal
		if (DisplayType.isNumeric(dt))
		{
			BigDecimal bd = null;
			try
			{
				Number nn = null;
				if (dt == DisplayType.Amount)
					nn = m_cs.amountFormat.parse(value);
				else if (dt == DisplayType.Quantity)
					nn = m_cs.quantityFormat.parse(value);					
				else	//	 DisplayType.CostPrice
					nn = m_cs.numberFormat.parse(value);
				if (nn instanceof BigDecimal)
					bd = (BigDecimal)nn;
				else
					bd = new BigDecimal(nn.toString());
			}
			catch (Exception e)
			{
				//log.warning("BigDecimal: " + columnName + "=" + value + ERROR);
				return ERROR;
			}
			//log.fine("BigDecimal: " + columnName + "=" + value + " -> " + bd);
			return bd;
		}

		//  ID
		else if (DisplayType.isID(dt))
		{
			Integer ii = null;
			try
			{
				ii = new Integer (value);
			}
			catch (Exception e)
			{
				//log.log(Level.WARNING, "ID: " + columnName + "=" + value, e);
				ii = null;
			}
			//  -1 indicates NULL
			if (ii != null && ii.intValue() == -1)
				ii = null;
			//log.fine("ID: " + columnName + "=" + value + " -> " + ii);
			return ii;
		}

		
		//  Date/DateTime
		else if (DisplayType.isDate(dt))
		{
			Timestamp ts = null;
			try
			{
				java.util.Date d = null;
				
				if (DisplayType.Date == dt)
					d = m_cs.dateFormat.parse(value);
				else if (DisplayType.DateTime == dt)
					d = m_cs.dateTimeFormat.parse(value);
				else if (DisplayType.Time == dt)
				{
					value = "2000/01/01 "+ value;
					d = m_cs.dateTimeFormat.parse(value);
				}
				ts = new Timestamp(d.getTime());
			}
			catch (Exception e)
			{
				//log.warning("Date: " + columnName + "=" + value + ERROR);
				return ERROR;
			}
			//log.fine("Date: " + columnName + "=" + value + " -> " + ts);
			return ts;
		}

		//  Checkbox
		else if (dt == DisplayType.YesNo)
		{
			Boolean retValue = Boolean.FALSE;
			if (value.equals("true"))
				retValue = Boolean.TRUE;
			//log.fine("YesNo: " + columnName + "=" + value + " -> " + retValue);
			return retValue;
		}

		//  treat as string
		//log.fine(columnName + "=" + value);
		return value;
	}   //  getFieldValue

	
	public WindowTabDataDocument addNewDataRow(int WindowNo, int TabNo ) throws XFireFault	{
    	authenticate(webServiceName, "addNewDataRow");
		
    	WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();

    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, true, TabNo, false, 0);
    	if (ws != null)
    	{
			if (ws.curTab.dataNew(false))
			{
				//ws.curTab.setSingleRow(true);
				wd.setSuccess(true);
				DataRow dr = ds.addNewDataRow();
				fillDataRow( dr, ws, true, false );
				updateRecIDMap( ws );
				System.out.println("New Row no = "+ws.curTab.getCurrentRow());
			} else
			{
				ws.curTab.dataIgnore();
				wd.setSuccess(false);
			}
    	}
    	
    	return ret;
	}
	
	public WindowTabDataDocument deleteDataRow(int WindowNo, int TabNo, int RowNo ) throws XFireFault	{
    	authenticate(webServiceName, "deleteDataRow");

    	WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();

    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, false, 0, true, RowNo);
    	if (ws != null)
    	{			
    		wd.setSuccess(
    				 ws.curTab.dataDelete()
    				 );
    		
    		DataRow ret_dr = ds.addNewDataRow();    	
    		fillDataRow( ret_dr, ws, true, false );
    		
    		if (ws.ads.m_is_error)
    		{        			
				wd.setError(ws.ads.m_error_message );
				wd.setErrorInfo(ws.ads.m_error_info );
    		} 
    		wd.setStatus(ws.ads.m_status_data);
    		wd.setStatusError(ws.ads.m_is_status_error);

    		updateRecIDMap( ws );
    	}
    	return ret;
	}
	
	public WindowTabDataDocument ignoreDataRow(int WindowNo, int TabNo, int RowNo ) throws XFireFault	{
    	authenticate(webServiceName, "ignoreDataRow");
		
    	WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();

    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, false, 0, true, RowNo);
    	if (ws != null)
    	{
    		ws.curTab.dataIgnore();
    		
    		wd.setSuccess(true);
    		
    		DataRow ret_dr = ds.addNewDataRow();    	
    		fillDataRow( ret_dr, ws, true, false );
    		
    		if (ws.ads.m_is_error)
    		{        			
				wd.setError(ws.ads.m_error_message );
				wd.setErrorInfo(ws.ads.m_error_info );
    		}
    		wd.setStatus(ws.ads.m_status_data);
    		wd.setStatusError(ws.ads.m_is_status_error);

    		updateRecIDMap( ws );
    		
    	}
    	return ret;
	}
	
	public WindowTabDataDocument refreshDataRow(int WindowNo, int TabNo, int RowNo ) throws XFireFault	{
    	authenticate(webServiceName, "refreshDataRow");
		
    	WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();

    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, false, 0, true, RowNo);
    	if (ws != null)
    	{
    		ws.curTab.dataRefresh();
    		
    		wd.setSuccess(true);
    		
    		DataRow ret_dr = ds.addNewDataRow();    	
    		fillDataRow( ret_dr, ws, true, false );
    		
    		if (ws.ads.m_is_error)
    		{        			
				wd.setError(ws.ads.m_error_message );
				wd.setErrorInfo(ws.ads.m_error_info );
    		}
    		wd.setStatus(ws.ads.m_status_data);
    		wd.setStatusError(ws.ads.m_is_status_error);
    		
    	}
    	return ret;
		
	}
	
	public WindowTabDataDocument getLookupSearchData(GetLookupSearchDataReqDocument req) throws XFireFault 
	{ //int WindowNo, int TabNo, int RowNo, DataRow dr 
    	authenticate(webServiceName, "getLookupSearchData");

    	GetLookupSearchDataReq reqt  = req.getGetLookupSearchDataReq();
		
		DataField[] df = reqt.getParams().getFieldArray();
    	for (int i=0; i<df.length; i++) {
    		if (df[i].getVal()!=null && df[i].getVal().length()>0 )
    		log.info("LookUp COlumn: "+ df[i].getColumn()+ " " + df[i].getVal());
    	}
    	
		WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();
    	
    	int WindowNo = reqt.getWindowNo();
    	int TabNo = reqt.getTabNo();
    	int RowNo = reqt.getTabNo();

    	WWindowStatus ws = null;
    	if (WindowNo>0)
    		ws = WWindowStatus.get(WindowStatusMap, WindowNo, true, TabNo, true, RowNo); //<-- Note changes to the active record (bledne action), probably are not properly communicated parameters    	
    	if (ws != null) {
    	
		  ADLookup lk = new ADLookup( df[0].getVal());
    	  lk.getLookupSearchValues( reqt.getParams(), ds, ws.ctx, WindowNo );
    	} else {
  		  ADLookup lk = new ADLookup( df[0].getVal());
    	  lk.getLookupSearchValues( reqt.getParams(), ds, this.m_cs.getM_ctx(), 0 );
    	}
    	return ret;		
	}
	
	public WindowTabDataDocument getLookupData(int WindowNo, int TabNo, int RowNo, String columnName ) throws XFireFault 
	{
    	authenticate(webServiceName, "getLookupData");

    	WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
    	WindowTabData wd = ret.addNewWindowTabData();
    	DataSet ds = wd.addNewDataSet();

    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, true, TabNo, true, RowNo);
    	if (ws != null)
    	{
    		DataRow dr = ds.addNewDataRow();
    		DataField df = dr.addNewField();
    		GridField field = ws.curTab.getField(columnName);
    		Lookup lookup = field.getLookup();
    		
    		df.setColumn(field.getColumnName());

    		String lookupValue = null;
    		if (field.getValue()!=null)
    				lookupValue = lookup.getDisplay( field.getValue() );
    		df.setLval(lookupValue);
    		if (field.getValue()!=null)
    			df.setVal(field.getValue().toString());
    		
    		LookupValues lvs = df.addNewLookup();
    		
    		ADLookup.fillLookupValues(lvs, lookup, field);
    	} 
    	return ret;		
	}    
    
    
    public ADMenuDocument getADMenu(int AD_Role_ID) throws XFireFault
    {
    	authenticate(webServiceName, "getADMenu");

    	ADMenuDocument res = ADMenuDocument.Factory.newInstance();
    	ADMenuItem menu = res.addNewADMenu();
    	menu.setName("Menu");
    	menu.setType("Summary");
    	
    	AD_Role_ID = Integer.parseInt(  m_cs.getM_ctx().getProperty("#AD_Role_ID") );
    	
		//  Load Menu Structure     ----------------------
		int AD_Tree_ID = DB.getSQLValue(null,
			"SELECT COALESCE(r.AD_Tree_Menu_ID, ci.AD_Tree_Menu_ID)" 
			+ "FROM AD_ClientInfo ci" 
			+ " INNER JOIN AD_Role r ON (ci.AD_Client_ID=r.AD_Client_ID) "
			+ "WHERE AD_Role_ID=?", AD_Role_ID);
		if (AD_Tree_ID <= 0)
			AD_Tree_ID = 10;	//	Menu
		
		//log.fine("doPost - AD_Tree_ID=" + AD_Tree_ID + " - " + Env.getAD_Language(wsc.ctx));
		
		MTree tree = new MTree (m_cs.getM_ctx(), AD_Tree_ID, false, null);	// Language set in WLogin
		//	Trim tree
		MTreeNode root = tree.getRoot();
		Enumeration en = root.preorderEnumeration();
		
		
		ADMenuItemList itl = null;// menu.addNewItems();
		ADMenuItem it = menu;//, it_last = null;
		
		Stack stack = new Stack();
		//stack.push( itl );
		
		while (en.hasMoreElements())
		{
			MTreeNode nd = (MTreeNode)en.nextElement();
			if (nd.isTask() 
				|| nd.isWorkbench() 
				|| nd.isWorkFlow()
				|| nd.getNode_ID() == 383	//	Reset Cache - kills the server
			)
			{
				MTreeNode parent = (MTreeNode)nd.getParent();
				parent.remove(nd);
			}
		}
		tree.trimTree();
		
		//	Print tree
		StringBuffer buf = new StringBuffer();
		StringBuffer barbuf = new StringBuffer();
		en = root.preorderEnumeration();
		int oldLevel = 0;
		while (en.hasMoreElements())
		{
			MTreeNode nd = (MTreeNode)en.nextElement();

			//  Level
			int level = nd.getLevel();	//	0 == root
			if (level == 0)
				continue;
			//
			while (oldLevel < level)
			{								
				if (itl != null) stack.push( itl );
				itl = it.addNewItems();				
				oldLevel++;
			}
			while (oldLevel > level)
			{
				oldLevel--;
				itl = (ADMenuItemList)stack.pop();
			}
				
			//	Print Node
			ADMenuItem it_last = printNode(nd, m_cs.getM_ctx(), itl );
			if (nd.isSummary())
				it = it_last;
			//if(nd.isOnBar() && !nd.isSummary())
				//barbuf.append(printNode(nd, m_cs.getM_ctx() ));
		}
		//	Final

		return res;
    }

	private ADMenuItem printNode (MTreeNode node, Properties ctx, ADMenuItemList itl)
	{
		ADMenuItem i = itl.addNewItem();
		i.setName( node.getName() );
		i.setDescription(node.getDescription() );
		i.setADMenuID( node.getNode_ID());
		String type = "";
		if(node.isWindow())type="Window";else
		if(node.isForm())type="Form";else
		if(node.isReport())type="Report";else
		if(node.isProcess())type="Process";else
		if(node.isSummary())type="Summary";else
		if(node.isTask())type="Task";else
		if(node.isWorkbench())type="Workbench";else
		if(node.isWorkFlow())type="Workbench";		
		i.setType( type );
		return i;
	}
	

	
	public ADLoginResponseDocument login( ADLoginRequestDocument req ) throws XFireFault
	{
    	authenticate(webServiceName, "login");

    	// TODO: Implement security layer
    	log.log(Level.SEVERE, "Warning: Security layer not implemented yet - opening web service " + webServiceName + " implies a security risk for server");

    	ADLoginResponseDocument res = ADLoginResponseDocument.Factory.newInstance();
		ADLoginResponse lr = res.addNewADLoginResponse();
		
		ADLoginRequest r = req.getADLoginRequest();
		
		if (r.getStage()==0)  // initial phase - return possible translations
		{
			LookupValues langs = lr.addNewLangs();
			for (int i = 0; i < Language.getLanguageCount(); i++)
			{
				Language language = Language.getLanguage(i);
				LookupValue lv = langs.addNewLv();
				lv.setKey( language.getAD_Language() );
				lv.setVal( language.getName() );
			}
		} else
		if (r.getStage()==1)  // Verify user and pass
		{
			KeyNamePair[] roles = null;
			KeyNamePair[] clients = null;
			KeyNamePair[] orgs  = null;
			KeyNamePair[] warehouses = null;
			
			Login login = new Login(m_cs.getM_ctx());
			
			roles = login.getRoles(r.getUser(), r.getPass());
			if (roles == null)
			{
				lr.setStatus(-1);
				return res;
			} else
			{
				if (r.getRoleID()==-1 && roles != null && roles.length>0)
					r.setRoleID( Integer.parseInt( roles[0].getID() ) );
				if (r.getRoleID()>-1) clients = login.getClients( new KeyNamePair(r.getRoleID(), "" ) );

				if (r.getClientID()==-1 && clients != null && clients.length>0)
					r.setClientID( Integer.parseInt( clients[0].getID() ) );
				if (r.getClientID()>-1) orgs = login.getOrgs( new KeyNamePair(r.getClientID(), "" ) );
								
				if (r.getOrgID()==-1 && orgs != null && orgs.length>0)
					r.setOrgID( Integer.parseInt( orgs[0].getID() ) );								
				if (r.getOrgID()>-1) warehouses = login.getWarehouses( new KeyNamePair(r.getOrgID(), "" ) );
				
				ADLookup.fillLookupValues( lr.addNewRoles(), roles );				
				ADLookup.fillLookupValues( lr.addNewClients(), clients );
				ADLookup.fillLookupValues( lr.addNewOrgs(), orgs );
				ADLookup.fillLookupValues( lr.addNewWarehouses(), warehouses );
			}
		} else
		if (r.getStage()==2)  // Verify user and pass
		{
			Login login = new Login(m_cs.getM_ctx());
			KeyNamePair[] roles = login.getRoles(r.getUser(), r.getPass());
			if (roles != null)
			{
				KeyNamePair org = new KeyNamePair(r.getRoleID(), Integer.toString(r.getRoleID()));
				String error = login.validateLogin(org);
				if (error != null && error.length() > 0)
				{
					lr.setStatus(-1);
					return res;							
				}						

				int AD_User_ID = Env.getAD_User_ID(m_cs.getM_ctx());
				
				if ( !m_cs.login( AD_User_ID, r.getRoleID(), r.getClientID(), r.getOrgID(), r.getWarehouseID(), r.getLang() ) ) {
					lr.setStatus(-1);
					return res;
				}
			}
			else
			{
				lr.setStatus(-1);
				return res;				
			}
		}
		
		return res;
	}
	
	

	
    public ProcessParamsDocument getProcessParams( GetProcessParamsDocument req ) throws XFireFault
    {
    	authenticate(webServiceName, "getProcessParams");
    	return Process.getProcessParams(m_cs, req );
    }
	
    public RunProcessResponseDocument runProcess( RunProcessDocument req )  throws XFireFault
    {
    	authenticate(webServiceName, "runProcess");
    	return Process.runProcess(m_cs, req);
    }
    
    public StandardResponseDocument saveLocation( LocationDocument req ) throws XFireFault
    {
    	authenticate(webServiceName, "saveLocation");
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse(); 
    		
    	Location rloc = req.getLocation(); 
    	
		MLocation location = new MLocation(m_cs.getM_ctx(), rloc.getCLocationID(), null);
		//log.fine("doPost updating C_Location_ID=" + C_Location_ID + " - " + targetBase);

		location.setAddress1 (rloc.getAddress1());
		location.setAddress2 (rloc.getAddress2());
		location.setCity ( rloc.getCity() );
		location.setPostal ( rloc.getPostalCode()  );
		location.setC_Country_ID ( rloc.getCCountryID() );
	    location.setC_Region_ID ( rloc.getCRegionID() );

		//  Save Location
		location.save();
		resp.setRecordID( location.getC_Location_ID() );
		
		return ret;

    }
    
    
    public LocationDocument getLocation( LocationDocument req ) throws XFireFault
    {
    	authenticate(webServiceName, "getLocation");
    	LocationDocument ret = LocationDocument.Factory.newInstance();
    	Location loc = ret.addNewLocation();
    	
		MLocation location = new MLocation(m_cs.getM_ctx(), req.getLocation().getCLocationID(), null);
		loc.setAddress1( location.getAddress1() );
		loc.setAddress2( location.getAddress2() );
		loc.setCity( location.getCity() );
		loc.setPostalCode( location.getPostal() );
		loc.setCCountryID( location.getC_Country_ID() );
		loc.setCRegionID( location.getC_Region_ID());
		
		loc.setCountries( this.getCountry( location ));
    	
    	return ret;
    }
    
    private LookupValues getCountry (MLocation location)
	{
		MCountry[] countries =  MCountry.getCountries (location.getCtx());
		int comp = location.getC_Country_ID();
		if (comp == 0)
			comp = Env.getContextAsInt(m_cs.getM_ctx(), "C_Country_ID");
		
		LookupValues lvs = LookupValues.Factory.newInstance();		
		for (int i = 0; i < countries.length; i++)
		{
			LookupValue lv = lvs.addNewLv();
			lv.setKey(String.valueOf(countries[i].getC_Country_ID()));
			lv.setVal( countries[i].getName() );
		}

		
		return lvs;
	}   

	private LookupValues getRegion (MLocation location)
	{
		MRegion[] regions =  MRegion.getRegions (location.getCtx(), location.getC_Country_ID());
		int comp = location.getC_Region_ID();
		if (comp == 0)
			comp = Env.getContextAsInt(m_cs.getM_ctx(), "C_Region_ID");

		LookupValues lvs = LookupValues.Factory.newInstance();
		for (int i = 0; i < regions.length; i++)
		{
			LookupValue lv = lvs.addNewLv();
			lv.setKey(String.valueOf(regions[i].getC_Region_ID()));
			lv.setVal( regions[i].getName() );
		}

		return lvs;
	}   

	public DocActionDocument getDocAction(int WindowNo, int TabNo, int RowNo, String ColName ) throws XFireFault
	{
    	authenticate(webServiceName, "getDocAction");
    	DocActionDocument ret = DocActionDocument.Factory.newInstance();
    	DocAction da = ret.addNewDocAction();

    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, true, TabNo, true, RowNo);
    	if (ws != null)
    	{
    		LookupValues lvs = da.addNewAction();
    		Process.renderDocActionOptions(lvs, ws.curTab);    		
    	}
    		
    	return ret;
	}

	public StandardResponseDocument setDocAction(int WindowNo, int TabNo, int RowNo, String ColName, String docAction ) throws XFireFault
	{
    	authenticate(webServiceName, "setDocAction");
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse sr = ret.addNewStandardResponse();

    	WWindowStatus ws = WWindowStatus.get(WindowStatusMap, WindowNo, true, TabNo, true, RowNo);
    	if (ws != null)
    	{ 
			ws.curTab.setValue("DocAction", docAction);
			boolean result = false;
			if (ws.curTab.needSave(true, false)) //slain - do not dispose of error, if not write musiales
			{
				if (! (result = ws.curTab.dataSave(true)))
					ws.curTab.dataIgnore();

			}
			sr.setIsError(!result);
    		
    	} else
    		sr.setIsError( true );
    		
    	return ret;		
	}


	private void updateRecIDMap(WWindowStatus ws) {
		ws.updateRecIDMap();
		/*
    	int rc = ws.curTab.getRowCount();						
		int initRowNo = 0;						
		
		Map<Integer, Integer> RecordIDMap = ws.getRecordIDMap();

		RecordIDMap.clear();
		
		for (int lineNo = initRowNo; lineNo < rc; lineNo++)
		{
			int recID = ws.curTab.getKeyID( lineNo );
			//System.out.println(""+lineNo+" - "+recID);
			RecordIDMap.put( recID, lineNo );
			
		} */
	}

}