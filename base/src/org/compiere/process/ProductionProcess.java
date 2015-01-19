package org.compiere.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.AdempiereUserError;


/**
 * 
 * Process to create production lines based on the plans
 * defined for a particular production header
 * @author Paul Bowden
 *
 */
public class ProductionProcess extends SvrProcess {

	private int p_M_Production_ID=0;
	private Timestamp p_MovementDate = null;
	private MProduction m_production = null;
	private boolean mustBeStocked = false;  //not used
	private boolean recreate = false;
	private boolean issued = false;
	//private int p_M_Locator_ID=0;
	
	
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
		//	log.fine("prepare - " + para[i]);
			if (para[i].getParameter() == null)
				;
			else if (name.equals("MovementDate"))
				p_MovementDate = (Timestamp)para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}
		
		p_M_Production_ID = getRecord_ID();
		m_production = new MProduction(getCtx(), p_M_Production_ID, get_TrxName());

	}	//prepare

	@Override
	protected String doIt() throws Exception {
		
		
		if ( m_production.get_ID() == 0 )
			throw new AdempiereUserError("Could not load production header");
		
		if ( m_production.getIsCreated().equals("N") )
			return "Not created";
		
		if ( m_production.isProcessed() )
			return "Already processed";
		
		
		return processLines();
			
	}

	protected String processLines() throws Exception {
		
		int processed = 0;
		m_production.setMovementDate(p_MovementDate);
		MProductionLine[] lines = m_production.getLines();
		StringBuffer errors = new StringBuffer();
		for ( int i = 0; i<lines.length; i++) {
			errors.append( lines[i].createTransactions(m_production.getMovementDate(), mustBeStocked) );
			//TODO error handling 
			lines[i].setProcessed( true );
			lines[i].save(get_TrxName());
			processed++;
		}
		
		if ( errors.toString().compareTo("") != 0 ) {
			log.log(Level.WARNING, errors.toString() );
			throw new AdempiereSystemError(errors.toString());
		}
		
		m_production.setProcessed(true);
		
		m_production.save(get_TrxName());
		return processed + " production lines were processed";
	}


}
