/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * Created by victor.perez@e-evolution.com,e-Evolution on 04/12/13.
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/854">
 * 		@see FR [ 854 ] Add new columns for Concept Attribute</a>
 */
public class MHRGrade extends X_HR_Grade {
    /**
	 * 
	 */
	private static final long serialVersionUID = 35499589044459660L;

	public MHRGrade(Properties ctx, int HR_Grade_ID, String trxName) {
        super(ctx, HR_Grade_ID, trxName);
    }

    public MHRGrade(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Grade Constructor
     * @param ctx
     * @param gradeValue
     * @param gradeName
     * @param trxName
     */
    public MHRGrade(Properties ctx , String gradeValue , String gradeName , String trxName) {
        super(ctx , 0 , trxName);
        setAD_Org_ID(0);
        setValue(gradeValue);
        setName(gradeName);
    }
    
    /** Static Cache */
   	private static CCache<Integer, MHRGrade> gradeCacheIds = new CCache<Integer, MHRGrade>(Table_Name, 30);
   	/** Static Cache */
   	private static CCache<String, MHRGrade> gradeCacheValues = new CCache<String, MHRGrade>(Table_Name, 30);

   	/**
   	 * Get/Load Shift group [CACHED]
   	 * @param ctx context
   	 * @param gradeId
   	 * @return activity or null
   	 */
   	public static MHRGrade getById(Properties ctx, int gradeId) {
   		if (gradeId <= 0)
   			return null;

   		MHRGrade grade = gradeCacheIds.get(gradeId);
   		if (grade != null && grade.get_ID() > 0)
   			return grade;

   		grade = new Query(ctx , Table_Name , COLUMNNAME_HR_Grade_ID + "=?" , null)
   				.setClient_ID()
   				.setParameters(gradeId)
   				.first();
   		if (grade != null && grade.get_ID() > 0)
   		{
   			int clientId = Env.getAD_Client_ID(ctx);
   			String key = clientId + "#" + grade.getValue();
   			gradeCacheValues.put(key, grade);
   			gradeCacheIds.put(grade.get_ID(), grade);
   		}
   		return grade;
   	}

   	/**
   	 * get Activity By Value [CACHED]
   	 * @param ctx
   	 * @param gradeValue
   	 * @return
   	 */
   	public static MHRGrade getByValue(Properties ctx , String gradeValue) {
   		if (gradeValue == null)
   			return null;
   		if (gradeCacheValues.size() == 0 )
   			getAll(ctx, true);

   		int clientId = Env.getAD_Client_ID(ctx);
   		String key = clientId + "#" + gradeValue;
   		MHRGrade grade = gradeCacheValues.get(key);
   		if (grade != null && grade.get_ID() > 0 )
   			return grade;

   		grade =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", null)
   				.setClient_ID()
   				.setParameters(gradeValue)
   				.first();

   		if (grade != null && grade.get_ID() > 0) {
   			gradeCacheValues.put(key, grade);
   			gradeCacheIds.put(grade.get_ID() , grade);
   		}
   		return grade;
   	}
   	
   	/**
   	 * Get All Campaign
   	 * @param ctx
   	 * @param resetCache
   	 * @return
   	 */
   	public static List<MHRGrade> getAll(Properties ctx, boolean resetCache) {
   		List<MHRGrade> gradeList;
   		if (resetCache || gradeCacheIds.size() > 0 ) {
   			gradeList = new Query(Env.getCtx(), Table_Name, null , null)
   					.setClient_ID()
   					.setOrderBy(COLUMNNAME_Name)
   					.list();
   			gradeList.stream().forEach(grade -> {
   				int clientId = Env.getAD_Client_ID(ctx);
   				String key = clientId + "#" + grade.getValue();
   				gradeCacheIds.put(grade.getHR_Grade_ID(), grade);
   				gradeCacheValues.put(key, grade);
   			});
   			return gradeList;
   		}
   		gradeList = gradeCacheIds.entrySet().stream()
   				.map(grade -> grade.getValue())
   				.collect(Collectors.toList());
   		return  gradeList;
   	}
}
