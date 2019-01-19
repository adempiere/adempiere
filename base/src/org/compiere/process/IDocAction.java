package org.compiere.process;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author Nikunj Panelia
 *
 */
public interface IDocAction extends DocAction
{
	public HashMap<Integer, BigDecimal> getChargeLines();

	public String getLineTableName();

	public String getAmtColumnName();
}
