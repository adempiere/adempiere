package org.posterita.struts.pos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MCurrency;
import org.compiere.util.CLogger;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.CurrencyBean;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.CurrencyForm;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.util.PoManager;

public class CurrencyAction extends BaseDispatchAction
{
	private static CLogger logger = CLogger.getCLogger(CurrencyAction.class);
	
	public static final String VIEW_CURRENCY = "viewCurrency";
	public ActionForward updateCurrency(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		
	    CurrencyForm currencyForm = (CurrencyForm) form; 
	    CurrencyBean currencyBean = (CurrencyBean) currencyForm.getBean();
	    
	    Properties ctx = TmkJSPEnv.getCtx(request);
	    Trx trx = Trx.get(TrxPrefix.getPrefix(),true);		
	    
	    try
	    {
	    	trx.start();
	    	if((currencyBean.getCurrencyId() != 0) && (currencyBean.getCurrencyId() != null))
	 	    {
	 	    	MCurrency currency = MCurrency.get(ctx, currencyBean.getCurrencyId());
	 	    	
	 	    	if(currencyBean.getRoundOffFactor().doubleValue() < 0)
	 	    	{
	 	    		postGlobalError("error.round.off.cannot.be.negative", request);
	 	    		return mapping.getInputForward();
	 	    	}
	 	    	
	 	    	currency.setRoundOffFactor(currencyBean.getRoundOffFactor());
	 	    	currency.setStdPrecision(currencyBean.getStdPrecision()); 
	 	    	currency.setIsActive(currencyBean.getIsActive());
	 	    	PoManager.save(currency);
	 	    }
	    	trx.commit();
	    	//postGlobalMessage("success.currency.updated.successfully", request);
	     }
	    
	    catch(OperationException e)
	    {
			trx.rollback();
			logger.log(Level.SEVERE, e.getMessage());
			postGlobalError("error.process", e.getMessage(), request);
	    }
	    
	    finally
	    {
	    	trx.close();
	    }
	    
	    request.getSession().removeAttribute(Constants.CURRENCY_LIST);
	    request.getSession().setAttribute(Constants.CURRENCY, currencyBean);
	    return mapping.getInputForward();
    }
	
	
	public static final String LIST_CURRENCIES = "listCurrencies";
	public ActionForward listCurrencies(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		
	    CurrencyForm currencyForm = (CurrencyForm) form; 
	    CurrencyBean currencyBean = (CurrencyBean) currencyForm.getBean();
	    
	    Properties ctx = TmkJSPEnv.getCtx(request);
	    Trx trx = Trx.get(TrxPrefix.getPrefix(),true);		
	    ArrayList<CurrencyBean> list = new ArrayList<CurrencyBean>();
	    list = getAllCurrencies(ctx, currencyBean.getIsoCode(), trx.getTrxName());
	    
	    request.getSession().setAttribute(Constants.CURRENCY_LIST, list);
	    return mapping.findForward(LIST_CURRENCIES);
	   
    }
	
	public static final String UPDATE_CURRENCY = "updateCurrency";
	public ActionForward viewCurrency(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
		Integer currencyId = Integer.valueOf(request.getParameter("currencyId"));
		String next =request.getParameter("isNext"); 		
        Boolean isNext= null;
        if ("true".equals(next))
        {
        	isNext = true;
        }
        else if ("false".equals(next))
        {
        	isNext = false;
        }

		CurrencyForm currencyForm = (CurrencyForm)form;
		
		ArrayList<CurrencyBean> list = (ArrayList<CurrencyBean>) request.getSession().getAttribute(Constants.CURRENCY_LIST); 
		
        CurrencyBean currencyBean = new CurrencyBean();      
		currencyBean = getCurrencyBean(ctx, list, currencyId, isNext, trx.getTrxName());        
		currencyForm.populate(currencyBean);
        
		request.getSession().setAttribute(Constants.CURRENCY, currencyBean);

		return mapping.findForward(UPDATE_CURRENCY);
	}
	
	/**
	 * Retrieves all currencies or search for currency by ISO_Code
	 * @param ctx Context
	 * @param isoCode Currency ISO Code. Search for currency by ISO Code
	 * @param trxName Transaction Name
	 * @return ArrayList of type CurrencyBean
	 */
	public ArrayList<CurrencyBean> getAllCurrencies(Properties ctx, String isoCode, String trxName)
	{
		String whereClause = "C_Currency_Id IS NOT NULL ORDER BY ISO_CODE";
		
		if(isoCode != null)
		{
			whereClause = "ISO_CODE = '" + isoCode.toUpperCase() + "'";
		}
		
		int[] currencyIds = MCurrency.getAllIDs(MCurrency.Table_Name, whereClause, null);
		    
	    ArrayList<CurrencyBean> list = new ArrayList<CurrencyBean>();
	    
	    if((currencyIds == null) || (currencyIds.length == 0))
	    {
	    	return list;
	    }
	
	    for(int id : currencyIds)
	    {
	    	CurrencyBean currencyBean = new CurrencyBean();
	    	MCurrency currency = new MCurrency(ctx, id, trxName );
	    	currencyBean.setCurrencyId(currency.getC_Currency_ID());
	    	currencyBean.setIsActive(currency.isActive());
	    	currencyBean.setIsoCode(currency.getISO_Code());
	    	currencyBean.setCurSymbol(currency.getCurSymbol());
	    	currencyBean.setRoundOffFactor(currency.getRoundOffFactor());
	    	currencyBean.setStdPrecision(currency.getStdPrecision());
	    	currencyBean.setDescription(currency.getDescription());
	    	
	    	list.add(currencyBean); 
	    }
	    
	    return list;
	}
	
	/**
	 * Get a specific currencyBean from an ArrayList of CurrencyBean
	 * @param ctx Context
	 * @param list Arraylist of type CurrencyBean
	 * @param currencyId  
	 * @param isNext used to keep track of next CurrencyBean relative to bean having currencyId
	 * @return CurrencyBean
	 * @throws OperationException
	 */
	public static CurrencyBean getCurrencyBean(Properties ctx,
			ArrayList<CurrencyBean> list, Integer currencyId, Boolean isNext, String trxName) throws OperationException 
	{
		int index = -1;
		
		if (currencyId == null || currencyId == 0)
		{
			return new CurrencyBean();
		}
		else 
		{
			if (list != null)
			{
				Iterator<CurrencyBean> iter = list.iterator();
				
				while (iter.hasNext())
				{
					CurrencyBean currBean = iter.next();
					if (currBean.getCurrencyId().equals(currencyId))
					{
						index = list.indexOf(currBean);
					}
				}
				if (index != -1)
				{
					if (isNext!=null)
					{
						if (isNext)
						{
							int nextIndex = (index + 1)%list.size();
							index = nextIndex;
						}
						else
						{
							int previousIndex = (index-1)%list.size();
							if (previousIndex < 0)
							{
								previousIndex += list.size();
							}
							index = previousIndex;
						}
					}
					return list.get(index);
				}
				else
				{
					throw new OperationException("no currency not found in search list");
				}
			}
			else
			{
				return getCurrencyBean(ctx, currencyId, trxName);
			}
		}
	}
	
	
	public static CurrencyBean getCurrencyBean(Properties ctx, Integer currencyId, String trxName)
	{
		CurrencyBean currencyBean = new CurrencyBean();
		MCurrency currency = MCurrency.get(ctx, currencyId);
		currencyBean.setCurrencyId(currency.getC_Currency_ID());
		currencyBean.setIsActive(currency.isActive());
		currencyBean.setCurSymbol(currency.getCurSymbol());
		currencyBean.setIsoCode(currency.getISO_Code());
		currencyBean.setStdPrecision(currency.getStdPrecision());
		currencyBean.setRoundOffFactor(currency.getRoundOffFactor());
				
		return currencyBean;
	}
}
