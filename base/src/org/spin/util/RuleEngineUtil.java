/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2019 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.util;

import java.util.Optional;

import org.compiere.model.MEntityType;
import org.compiere.model.MRule;

/**
 * 	Generate Rule Classes util
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class RuleEngineUtil {
	
	/**
	 * Get Method Name
	 * @return
	 */
	public static String getClassName(MRule rule) {
		if(rule == null
				|| rule.getAD_Rule_ID() <= 0) {
			return null;
		}
		return rule.getValue().replaceAll("[+^: &áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$()*#/><]", "_");
	}
	
	/**
	 * Get Package Name
	 * @return
	 */
	public static String getPackageName(MRule rule) {
		if(rule == null
				|| rule.getAD_Rule_ID() <= 0) {
			return null;
		}
		MEntityType entityType = MEntityType.get(rule.getCtx(), rule.getEntityType());
		String packageName = Optional.ofNullable(entityType.getModelPackage()).orElse("org.spin.model");
		int index = packageName.lastIndexOf(".");
		packageName = packageName.substring(0, index);
		return packageName + "." + "engine";
	}
	
	/**
	 * Get complete classname
	 * @param rule
	 * @return
	 */
	public static String getCompleteClassName(MRule rule) {
		String packageName = getPackageName(rule);
		if(packageName == null) {
			return null;
		}
		String className = getClassName(rule);
		if(className == null) {
			return null;
		}
		return packageName + "." + className;
	}
}
