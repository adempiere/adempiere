/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 */

/**
	@author Alok Pathak
 */

package org.posterita.businesslogic;

import java.util.Properties;

import org.compiere.process.ProcessInfo;
import org.compiere.process.SequenceCheck;

import org.posterita.exceptions.SequenceUpdateException;


public class CheckSequenceManager {
    
//    private static final int TABLE_ID=115;
    private static final int PROCESS_ID=258;
    
    public static void runProcess(Properties ctx) throws SequenceUpdateException
    {
        ProcessInfo poInfo = new ProcessInfo("Sequencecheck",PROCESS_ID);
        SequenceCheck seqCheck = new SequenceCheck();
       boolean success= seqCheck.startProcess(ctx, poInfo,null);
       
       if(success==false)
           throw new SequenceUpdateException("Sequence not updated");
    }
    
    
}
