package org.compiere.process;
/**
* Created By : - BJ
* Edited By: AB
*/

import org.compiere.model.Query;
import org.compiere.model.X_AD_Process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ReportIpChange_Process extends SvrProcess{

		public String V_Old_IP=null;
		public String V_New_IP=null;
		boolean saved = Boolean.TRUE;
		StringBuffer errorMsg = new StringBuffer();
		List<X_AD_Process> obj_process = new ArrayList<X_AD_Process>();
		public ReportIpChange_Process() {
		// TODO Auto-generated constructor stub
		}
		
		@Override
	protected void prepare() 
	{
			// TODO Auto-generated method stub
			ProcessInfoParameter[] parameters = getParameter();
			for (ProcessInfoParameter parameter : parameters)
			{
				String name = parameter.getParameterName();
				
				if (parameter.getParameter() != null) {
				if (name.equalsIgnoreCase("JasperReport")) {
				V_Old_IP = parameter.getParameter().toString();
			} 
			else
			{
				V_New_IP = parameter.getParameter().toString();
			
			}
			
			} 
			else
			{
				log.log(Level.SEVERE, " UnKnown Parameter " + name);
			}
		}
		
		
	}
		
		@Override
	protected String doIt() throws Exception
	{
		// TODO Auto-generated method stub
		
		obj_process=udf_getProcessList();
		for(X_AD_Process obj_list : obj_process)
		{
		
			String v_IP=obj_list.getJasperReport().toString();
			obj_list.setJasperReport(v_IP.replace(V_Old_IP, V_New_IP));
			obj_list.save();
			
			addLog(" <font color= Blue> IP Changed for report "+ obj_list.getName()+ " From "+ v_IP+" to "
						+v_IP.replace(V_Old_IP, V_New_IP));
	
		}
		return "Yeah! IP Addresses are sucessfully Changed. ";
	}
		
		
	private List<X_AD_Process> udf_getProcessList()
	{
		
		//String sql = " jasperreport is not null or jasperreport like '%"+V_Old_IP+"%' ";
		//AB
		String sql	=	" jasperreport like '%"+V_Old_IP+"%' ";
		//AB
		List<X_AD_Process> obj_process = new ArrayList<X_AD_Process>();
		obj_process= new Query(getCtx(), X_AD_Process.Table_Name, sql, get_TrxName()).list();
		return obj_process;
	}
}

