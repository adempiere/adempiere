package org.compiere.model;


import org.compiere.util.DB;

/*
 * @author: Abhijeet Singh
 * Need: Customising Windows in Adempiere
 */

public class AD_WindowCustomization {
	

	public Boolean getDeleteButtonStatus(int WindowID, int tabID , int UserID, int RoleID)
	{
		String TStatus=null;
		Boolean v_status=true;
		String sql2;
		
		String sql="select t.RDelete from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_User_ID="+UserID+" and    t.IsActive='Y'";
		TStatus=DB.getSQLValueString(null, sql);	
			
		if(TStatus==null)
		{
			sql2="Select RDelete from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_User_ID="+UserID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
	
		}
		
		if(TStatus==null)
		{
			sql="select t.RDelete from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_Role_ID="+RoleID+" and t.IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql);	
		}	

		if(TStatus==null)
		{
			sql2="Select RDelete from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_Role_ID="+RoleID+" and IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
			if(TStatus==null) TStatus="N";
		}
		
		v_status=TStatus.equals("Y")?false:true;	
		
		return v_status;
	}
	
	public Boolean getNewButtonStatus(int WindowID, int tabID , int UserID , int RoleID)
	{
		Boolean v_status=true;
		String TStatus=null;
		String sql2;
		
		String sql="select t.RNew from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_User_ID="+UserID+" and t.IsActive='Y'";
		TStatus=DB.getSQLValueString(null, sql);	
			
		if(TStatus==null)
		{
			sql2="Select RNew from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_User_ID="+UserID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
	//		if(TStatus==null) TStatus="N";
		}
		
		if(TStatus==null)
		{
			sql="select t.RNew from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_Role_ID="+RoleID+" and    t.IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql);	
		}	

		if(TStatus==null)
		{
			sql2="Select RNew from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_Role_ID="+RoleID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
			if(TStatus==null) TStatus="N";
		}
		
		v_status=TStatus.equals("Y")?false:true;	
		
		return v_status;
	}
	
	public String getUpdateButtonStatus(int WindowID, int tabID , int UserID , int RoleID)
	{
		String TStatus=null;
		String sql2;
		
		String sql="select t.RUpdate from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_User_ID="+UserID+" and    t.IsActive='Y'";
		TStatus=DB.getSQLValueString(null, sql);	
			
		if(TStatus==null)
		{
			sql2="Select RUpdate from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_User_ID="+UserID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);				
		}
		
		if(TStatus==null)
		{
			sql="select t.RUpdate from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_Role_ID="+RoleID+" and    t.IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql);	
		}	

		if(TStatus==null)
		{
			sql2="Select RUpdate from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_Role_ID="+RoleID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
			if(TStatus==null) TStatus="N";
		}
		
		return TStatus;
	}

	public Boolean getDeleteAllButtonStatus(int WindowID, int tabID , int UserID , int RoleID)
	{
		Boolean v_status=true;
		String TStatus=null;
		String sql2;
		
		String sql="select t.RDeleteall from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_User_ID="+UserID+" and    t.IsActive='Y'";
		TStatus=DB.getSQLValueString(null, sql);	
			
		if(TStatus==null)
		{
			sql2="Select RDeleteall from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_User_ID="+UserID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
//			if(TStatus==null) TStatus="N";
		}
		
		if(TStatus==null)
		{
			sql="select t.RDeleteall from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_Role_ID="+RoleID+" and    t.IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql);	
		}	

		if(TStatus==null)
		{
			sql2="Select RDeleteall from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_Role_ID="+RoleID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
			if(TStatus==null) TStatus="N";
		}
		
		v_status=TStatus.equals("Y")?false:true;	
		
		return v_status;
	}
	
	public Boolean getReportButtonStatus(int WindowID, int tabID , int UserID , int RoleID)
	{
		Boolean v_status=true;
		String TStatus=null;
		String sql2;
		
		String sql="select t.RReport from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_User_ID="+UserID+" and    t.IsActive='Y'";
		TStatus=DB.getSQLValueString(null, sql);	
			
		if(TStatus==null)
		{
			sql2="Select RReport from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_User_ID="+UserID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
//			if(TStatus==null) TStatus="N";
		}
		
		if(TStatus==null)
		{
			sql="select t.RReport from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_Role_ID="+RoleID+" and    t.IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql);	
		}	

		if(TStatus==null)
		{
			sql2="Select RReport from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_Role_ID="+RoleID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
			if(TStatus==null) TStatus="N";
		}
		
		v_status=TStatus.equals("Y")?false:true;	
		
		return v_status;
	}
	
	public Boolean getPrintButtonStatus(int WindowID, int tabID , int UserID , int RoleID)
	{
		Boolean v_status=true;
		String TStatus=null;
		String sql2;
		
		String sql="select t.RPrint from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_User_ID="+UserID+" and    t.IsActive='Y'";
		TStatus=DB.getSQLValueString(null, sql);	
			
		if(TStatus==null)
		{
			sql2="Select RPrint from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_User_ID="+UserID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
//			if(TStatus==null) TStatus="N";
		}
		
		if(TStatus==null)
		{
			sql="select t.RPrint from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_Role_ID="+RoleID+" and    t.IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql);	
		}	

		if(TStatus==null)
		{
			sql2="Select RPrint from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_Role_ID="+RoleID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
			if(TStatus==null) TStatus="N";
		}
		
		v_status=TStatus.equals("Y")?false:true;	
		
		return v_status;
	}
	
/*	public Boolean getExportButtonStatus(int WindowID, int tabID , int UserID , int RoleID)
	{
		Boolean v_status=true;
		String TStatus=null;
		String sql2;
		
		String sql="select t.RExport from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_User_ID="+UserID+" and    t.IsActive='Y'";
		TStatus=DB.getSQLValueString(null, sql);	
			
		if(TStatus==null)
		{
			sql2="Select RExport from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_User_ID="+UserID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
		}
		
		if(TStatus==null)
		{
			sql="select t.RExport from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_Role_ID="+RoleID+" and    t.IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql);	
		}	

		if(TStatus==null)
		{
			sql2="Select RExport from AD_UserDef_Win where AD_Window_ID="+WindowID+" and AD_Role_ID="+RoleID+" and  IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql2);	
			if(TStatus==null) TStatus="N";
		}
		
		v_status=TStatus.equals("Y")?false:true;	
		
		return v_status;
	}
*/	
	public String getFieldHiddenStatus(int WindowID, int tabID , int UserID, int FieldID, int RoleID)
	{
		String value=null;
		
		String sql=" select f.isdisplayed from ad_userdef_field f inner join ad_userdef_tab t on t.ad_userdef_tab_id=f.ad_userdef_tab_id "
				+ "inner join ad_userdef_win w on w.ad_userdef_win_id=t.ad_userdef_win_id where w.ad_window_id="+WindowID+" "
						+ "and t.ad_tab_id="+tabID+" and f.ad_field_id="+FieldID+" and w.AD_User_ID="+UserID+" and t.IsActive='Y'";
		value=DB.getSQLValueString(null, sql);
		
		if(value==null)
		{
			sql=" select f.isdisplayed from ad_userdef_field f inner join ad_userdef_tab t on t.ad_userdef_tab_id=f.ad_userdef_tab_id "
					+ "inner join ad_userdef_win w on w.ad_userdef_win_id=t.ad_userdef_win_id where w.ad_window_id="+WindowID+""
							+ "and t.ad_tab_id="+tabID+" and f.ad_field_id="+FieldID+" and w.AD_Role_ID="+RoleID+" and t.IsActive='Y'";
			value=DB.getSQLValueString(null, sql);
		}
		

		return value;
	}
	
	public String getFieldReadStatus(int WindowID, int tabID , int UserID, int FieldID, int RoleID)
	{
		String value=null;
		
		String sql=" select f.isreadonly from ad_userdef_field f inner join ad_userdef_tab t on t.ad_userdef_tab_id=f.ad_userdef_tab_id "
				+ "inner join ad_userdef_win w on w.ad_userdef_win_id=t.ad_userdef_win_id where w.ad_window_id="+WindowID+""
						+ "and t.ad_tab_id="+tabID+" and f.ad_field_id="+FieldID+" and w.AD_User_ID="+UserID+" and t.IsActive='Y'";
		value=DB.getSQLValueString(null, sql);	
		
		if(value==null)
		{
			sql=" select f.isreadonly from ad_userdef_field f inner join ad_userdef_tab t on t.ad_userdef_tab_id=f.ad_userdef_tab_id "
					+ "inner join ad_userdef_win w on w.ad_userdef_win_id=t.ad_userdef_win_id where w.ad_window_id="+WindowID+""
							+ "and t.ad_tab_id="+tabID+" and f.ad_field_id="+FieldID+" and w.AD_Role_ID="+RoleID+" and t.IsActive='Y'";
			value=DB.getSQLValueString(null, sql);
		}
		

		return value;
	}
	
	public String getTabWhereClause(int WindowID, int tabID , int UserID , int RoleID)
	{
		String TStatus=null;
		
		String sql="select t.Displaylogic from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_User_ID="+UserID+" and    t.IsActive='Y'";
		TStatus=DB.getSQLValueString(null, sql);	
		
		if(TStatus==null)
		{
			sql="select t.Displaylogic from ad_userdef_tab t inner join ad_userdef_win  w on t.ad_userdef_win_id=w.ad_userdef_win_id "
				+ "where w.AD_Window_ID="+WindowID+" and t.AD_Tab_ID="+tabID+" and w.AD_Role_ID="+RoleID+" and    t.IsActive='Y'";
			TStatus=DB.getSQLValueString(null, sql);	
		}	
	
		return TStatus;
	}

}
