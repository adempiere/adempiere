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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.engine.freight;
import java.util.HashMap;

/**
 * 
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> 20/08/16.
 */
public class FreightEngineFactory
{
	private static final HashMap<Integer, FreightEngine> engines = new HashMap<Integer, FreightEngine>();
	
	public static FreightEngine getFreightEngine(int clientId) {
		FreightEngine engine = engines.get(clientId);
		// Fallback to global engine
		if (engine == null && clientId > 0) {
			engine = engines.get(0);
		}
		// Create Default Engine
		if (engine == null) {
			engine = new FreightEngine();
			engines.put(clientId, engine);
		}
		return engine;
	}
}
