/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */
package org.compiere.process;
import org.compiere.model.MDistribution;

/**
 * Copy From Distribution
 */
public class CopyFromDistribution extends CopyFromDistributionAbstract {
	protected void prepare() {
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception {
		if (getRecord_ID() == 0)
			throw new IllegalArgumentException("@GL_Distribution_ID@ @NotFound@");
		MDistribution from = new MDistribution (getCtx(), getDistributionId(), get_TrxName());
		MDistribution to = new MDistribution (getCtx(), getRecord_ID(), get_TrxName());
		int no = to.copyLinesFrom (from);
		return "@Copied@=" + no;
	}

}
