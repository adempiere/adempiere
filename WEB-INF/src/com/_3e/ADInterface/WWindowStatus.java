package com._3e.ADInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.MQuery;

/*
 * ADEMPIERE/COMPIERE
 * 
 * A:
 * Replace  GridWindowVO with GridWindowVO
 * 	GridWindow with GridWindow
 *  GridTab  with GridTab 
 */

public class WWindowStatus 
{
	public class AD_WrongTabException extends Exception
	{
		public AD_WrongTabException( String msg ) {
			super(msg);
		}
	}
	
	public static WWindowStatus get (Map cache, int WinNo, boolean changeTab, int TabNo, boolean changeRow, /*int RowNo*/int RecordID ) //throws AD_WrongTabException 
	{
		WWindowStatus ws = (WWindowStatus)cache.get(new Integer(WinNo));
		if (ws != null)
		{
			if (changeTab &&ws.curTab.getTabNo()!=TabNo)
			{
				//if (!changeTab)
				//	return null;
					//throw ws.new AD_WrongTabException("");
				WWindowStatus.changeTabIfNeeded( ws, TabNo );
				ws.updateRecIDMap();
			}
			/*
			if (changeRow)
			{
				if (RowNo<0) RowNo = 0;
	    		if (ws.curTab.getCurrentRow() != RowNo) 
	    		{
	    			int r = ws.curTab.navigate(RowNo);
	    			if (r==-1)
	    				ws.curTab.dataRefresh();
	    		}
			}*/
			 // zakladamy ze dostajemy teraz RecordID zamiast RowNo;
			if (changeRow) {
				int RowNo = ws.getRowNoFromRecordID( RecordID );
				
				if (RowNo<0) RowNo = 0;
	    		if (ws.curTab.getCurrentRow() != RowNo) 
	    		{
	    			int r = ws.curTab.navigate(RowNo);
	    			if (r==-1)
	    				ws.curTab.dataRefresh();
	    		}
			}
			
		}
		
//		 ADEMPIERE/COMPIERE
		if (ws!=null && !ws.curTab.getTableModel().isOpen()) 
			ws.curTab.getTableModel().
			open(0); // adempiere
			//open(); // compiere
			
		return ws;
	}	//	get
	
	
	/**************************************************************************
	 *  Constructor - First Tab - First Row - Single Row.
	 *  <br>
	 *  Initialize Formats
	 *  @param GridWindowVO window VO
	 */
	public WWindowStatus ( GridWindowVO GridWindowVO)
	{
		mWindow = new GridWindow(GridWindowVO);
		curTab = mWindow.getTab(0);
		curTab.setSingleRow(true);	
		
		ctx = GridWindowVO.ctx;		
		m_needSave = false;
		
		ads = new ADDataStatusListener(ctx);
		curTab.addDataStatusListener( ads );
		
		RecordIDMap = new HashMap<Integer, Integer>();
	}   //  WWindowStatus

	
	/** The GridWindow                 */
	protected GridWindow       mWindow;
	/** The current GridTab            */
	protected GridTab          curTab;
	

	/** Window Context 				*/
	public Properties    ctx = null;

	public ADDataStatusListener ads = null;
	
	public int TabNo = 0;
	public MQuery findQuery = null;
	public boolean hasFindParams = false;
	public boolean findQueryApplied = false;
	public boolean m_needSave = false;

	
	/**
	 *  String representation
	 *  @return String representation
	 */
	public String toString()
	{
		return "WWindowStatus[" + mWindow
			+ " - " + curTab + "]";
	}   //  toString
	
	
	
	public static void changeTabIfNeeded(WWindowStatus ws, int TabNo)
	{
    	if (ws.curTab.getTabNo() != TabNo)  // tab change
    	{
    		//ws.curTab = ws.mWindow.getTab(TabNo);    		
			if (TabNo > ws.curTab.getTabNo())
			{
				//if (ws.curTab.getTableModel().isInserting() )  // kolec
				//	return;
				
				ws.curTab.removeDataStatusListener( ws.ads );
				// ADEMPIERE/COMPIERE
				ws.mWindow.initTab(TabNo); //adempiere specific
				ws.curTab = ws.mWindow.getTab(TabNo);				
				ws.curTab.addDataStatusListener( ws.ads );
				ws.curTab.query(ws.mWindow.isTransaction()); // false
				ws.curTab.navigate(0);				
				ws.m_needSave = false;
				ws.updateRecIDMap();
			}
			//  move back
			else if (TabNo < ws.curTab.getTabNo())
			{
				ws.curTab.removeDataStatusListener( ws.ads );
//				 ADEMPIERE/COMPIERE
				ws.mWindow.initTab(TabNo); //adempiere specific
				ws.curTab = ws.mWindow.getTab(TabNo);
				ws.curTab.addDataStatusListener( ws.ads );
				// ws.curTab.dataRefresh(); // bylo
				ws.curTab.query(ws.mWindow.isTransaction()); // false
				ws.curTab.navigate(0);	
				ws.m_needSave = false;
				ws.updateRecIDMap();
			}
    	}
	}
	
	
	
	Map<Integer, Integer> RecordIDMap = null;
		
	public int getRowNoFromRecordID( int RecordID ) {		
		Integer rowNo = this.RecordIDMap.get( RecordID ); 
		if (rowNo == null) rowNo = new Integer(-1);
		System.out.println("getRowNo From RecID "+RecordID+" = "+rowNo.intValue());
		return rowNo.intValue();		
	}

	public Map<Integer, Integer> getRecordIDMap() {
		return RecordIDMap;
	}
	
	public void updateRecIDMap() {
    	int rc = curTab.getRowCount();						
		int initRowNo = 0;						
		
		//Map<Integer, Integer> RecordIDMap = ws.getRecordIDMap();

		RecordIDMap.clear();
		
		for (int lineNo = initRowNo; lineNo < rc; lineNo++)
		{
			int recID = curTab.getKeyID( lineNo );
			//System.out.println(""+lineNo+" - "+recID);
			RecordIDMap.put( recID, lineNo );
			
		} 
	}

}   //  WWindowStatus
