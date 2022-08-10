/**
 *
 */
package org.compiere.FA.feature;

import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.SetGetModel;
 
 
 /**	Describe Use life Feature
  *	@author Teo Sarca, SC Arhipac SRL
  *	@version $Id$
  */
 public interface UseLife extends SetGetModel {
	public Properties getCtx();
	
	//~ public void setUseLifeMonths(int value);
	//~ public int getUseLifeMonths();
	
	//~ public void setUseLifeYears(int value);
	//~ public int getUseLifeYears();

	//~ public void setUseLifeMonths_F(int value);
	//~ public int getUseLifeMonths_F();
	
	//~ public void setUseLifeYears_F(int value);
	//~ public int getUseLifeYears_F();
	
	public Timestamp getAssetServiceDate();
	
	/* commented out by @win
	public int getA_Asset_Class_ID();
	*/
 }