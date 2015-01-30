/**
 * 
 */
package org.compiere.FA.process;

import java.lang.reflect.Field;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;

/**
 * Server Process Template (v2).
 * In this version, parameters fields will be automatically filled if they start with p_ and are accessible.
 * 
 * @author Teo Sarca, www.arhipac.ro
 *
 */
public abstract class SvrProcess2 extends SvrProcess
{
	/** Logger */
	private static final CLogger s_log = CLogger.getCLogger(SvrProcess2.class);
	
	
	protected final void prepare()
	{
		readParameters(this, getParameter());
	}

	private static void readParameters(SvrProcess process, ProcessInfoParameter[] params)
	{
		try
		{
			for (Field field : process.getClass().getFields())
			{
				if (!field.getName().startsWith("p_"))
				{
					continue;
				}
				final String parameterName;
				final boolean isTo;
				if (field.getName().endsWith("_To"))
				{
					parameterName = field.getName().substring(2, field.getName().length() - 3);
					isTo = true;
				}
				else
				{
					parameterName = field.getName().substring(2);
					isTo = false;
				}
				//
				boolean isSet = false;
				for (ProcessInfoParameter para : params)
				{
					if (!parameterName.equals(para.getParameterName()))
					{
						continue;
					}
					if (field.getType() == int.class)
					{
						if (isTo)
							field.setInt(process, para.getParameter_ToAsInt());
						else
							field.setInt(process, para.getParameterAsInt());
					}
					else if (field.getType() == boolean.class)
					{
						if (isTo)
							field.setBoolean(process, para.getParameter_ToAsBoolean());
						else
							field.setBoolean(process, para.getParameterAsBoolean());
					}
					else
					{
						if (isTo)
							field.set(process, para.getParameter_To());
						else
							field.set(process, para.getParameter());
					}
					isSet = true;
					break;
				} // for ProcessInfoParameter
				//
				if (!isSet)
				{
					s_log.info("Parameter not set - "+parameterName);
				}
			} // for Field
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
	}
}
