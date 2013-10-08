 /******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP							  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.migration.logger.impl;

import org.adempiere.ad.migration.logger.IMigrationLoggerContext;
import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.util.Check;

public class SingletonMigrationLoggerContext implements IMigrationLoggerContext
{
	private final I_AD_Migration migration;
	private boolean generateComments = false;

	public SingletonMigrationLoggerContext(final I_AD_Migration migration)
	{
		Check.assumeNotNull(migration, "migration not null");
		this.migration = migration;
	}

	/**
	 * @return true always
	 */
	@Override
	public boolean isEnabled()
	{
		return true;
	}

	@Override
	public I_AD_Migration getMigration(String key)
	{
		return migration;
	}

	@Override
	public void putMigration(String key, I_AD_Migration migration)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isGenerateComments()
	{
		return generateComments;
	}

	public void setGenerateComments(final boolean generateComments)
	{
		this.generateComments = generateComments;
	}
}
