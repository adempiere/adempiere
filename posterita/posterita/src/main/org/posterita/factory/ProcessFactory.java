/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on Jul 6, 2005 by vishee
 */

package org.posterita.factory;

import java.util.Properties;

import org.compiere.model.MProcess;
import org.compiere.process.SvrProcess;
import org.posterita.exceptions.OperationException;



public class ProcessFactory extends AbstractFactory
{
    
    
    private static ProcessFactory singleton;
    
   
    private ProcessFactory() throws OperationException
    {
        super();

    }
    
    public static ProcessFactory getFactoryInstance(Properties ctx) throws Exception
    {
        if (singleton ==null)
            singleton = new ProcessFactory();
        
        return singleton;
    }
    

    protected void loadFactory(Properties ctx) throws OperationException
    {
    	loadFactory(ctx, singleton);
    }

   

    /*
     * key - the value retrieved from properties file for the name of the process
     * className - the class from which the process will run (eg com.mu.process.MyProcess where MyProcess is the java file)
     */
    private MProcess createProcess(Properties ctx,  String className) throws OperationException
    {
        
            MProcess mProcess = new MProcess(ctx,0, null);
            mProcess.setClassname(className);
            mProcess.setDescription(className);
            mProcess.setName(className);
            mProcess.setValue(className);
            
            
            return mProcess;
      
    }
    
    public  SvrProcess getSvrProcess(Properties ctx,String key) throws Exception
    {
        MProcess p = (MProcess) singleton.get(ctx, key);
        Object arr[] = {};
        SvrProcess process = (SvrProcess) Class.forName(p.getClass().getName()).getConstructors()[0].newInstance(arr); // use better reflection
        return process;
    }

	@Override
	protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException
	{
		// TODO Auto-generated method stub
		
	}
}
