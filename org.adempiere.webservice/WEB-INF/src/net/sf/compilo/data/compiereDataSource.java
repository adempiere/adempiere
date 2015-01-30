/**
 *   reportServer for JasperReport.
 *   Copyright (C) 2004  Peter Shen.
 *   Shanghai, China.
 *   Email: zpshen@gmail.com
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *   Contributor: Marco LOMBARDO, Compilo subAdministrator.
 *                lombardo@mayking.com, mar9000@gmail.com
 *                Italy.
 **/
package net.sf.compilo.data;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.compiere.util.CLogger;

/**
 *  compiereDataSource
 *
 * 	@author 	Peter Shen
 * 	@version 	$Id: compiereDataSource.java,v 1.4 2005/08/04 09:42:41 pshen Exp $
 *	@description:	compiereDataSource
 */
public abstract class compiereDataSource implements JRDataSource
{	
	protected CLogger log = CLogger.getCLogger(compiereDataSource.class);
	
	public abstract Object getFieldValue(JRField field) throws JRException;
	
	public abstract boolean next() throws JRException;
	
	public abstract void close();	
}
