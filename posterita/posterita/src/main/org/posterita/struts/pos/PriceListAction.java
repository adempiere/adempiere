package org.posterita.struts.pos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MPriceList;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.PriceListBean;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.core.SessionStorage;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.PriceListForm;
import org.posterita.struts.core.BaseDispatchAction;

public class PriceListAction extends BaseDispatchAction 
{

	private static CLogger logger = CLogger.getCLogger(PriceListAction.class);
	
	public static final String CREATE_OR_UPDATE_PRICE_LIST = "createOrUpdatePriceList";
	public ActionForward createOrUpdatePriceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException, InstantiationException, IllegalAccessException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		PriceListForm priceListForm = (PriceListForm)form;
		PriceListBean priceListBean = (PriceListBean)priceListForm.getBean();
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(),true);		
		try
		{
			trx.start();			
			PriceListManager.createOrUpdatePriceList(ctx, priceListBean, trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{
			trx.rollback();
			logger.log(Level.SEVERE, e.getMessage());
			postGlobalError("error.process", e.getMessage(), request);
		}
		finally
		{
			trx.close();
		}
		
		request.getSession().setAttribute(Constants.PRICE_LIST, priceListBean);
		SessionStorage.putPriceLists(ctx, request);
		priceListForm.populate(priceListBean);
		return mapping.findForward(CREATE_OR_UPDATE_PRICE_LIST);
	}
	
	
	public static final String LIST_PRICE_LISTS = "listPriceLists";
	public ActionForward listPriceLists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		PriceListForm priceListForm = (PriceListForm)form;
		PriceListBean priceListBean = (PriceListBean)priceListForm.getBean();
		
		String name = priceListBean.getName();
		ArrayList<PriceListBean> list = PriceListManager.getPriceLists(ctx, 0, name, null, null, null, null, null);
		
		request.getSession().setAttribute(Constants.LIST_PRICE_LISTS, list);
		
		String fromDeletePriceOnPriceList = request.getParameter("isFromDeletePriceOnPriceList");
		
		if(fromDeletePriceOnPriceList != null && fromDeletePriceOnPriceList.equals("true"))
		{
			request.setAttribute(Constants.IS_FROM_DELETE_PRODUCT_PRICE, "true");
		}
		
		return mapping.findForward(LIST_PRICE_LISTS);
		
	}
	
	public static final String VIEW_PRICE_LIST = "viewPriceList";
	public ActionForward viewPriceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		Integer priceListId = Integer.valueOf(request.getParameter("priceListId"));
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

		PriceListForm priceListform = (PriceListForm)form;
		
		ArrayList<PriceListBean> list = (ArrayList<PriceListBean>) request.getSession().getAttribute(Constants.LIST_PRICE_LISTS); 
		PriceListBean priceListBean = null;
              
		priceListBean = PriceListManager.getPriceListBean(ctx, list, priceListId, isNext);        
		priceListform.populate(priceListBean);
        
		request.getSession().setAttribute(Constants.PRICE_LIST, priceListBean);

		return mapping.findForward(VIEW_PRICE_LIST);
	}
		
	
	public static final String SET_DEFAULT_PRICE_LIST = "setDefaultPriceList";
	public ActionForward setDefaultPriceList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		int priceListId = 0;
		try
		{
			priceListId = Integer.parseInt(String.valueOf(request.getParameter("priceListId")));
			MPriceList priceList = MPriceList.get(ctx, priceListId, null);
			PriceListManager.setDefaultPriceList(ctx, priceList, true, null);
		}
		catch (NumberFormatException e)
		{
			return mapping.getInputForward();
		}
		ArrayList<PriceListBean> list = (ArrayList<PriceListBean>)request.getSession().getAttribute(Constants.LIST_PRICE_LISTS);
		ArrayList<PriceListBean> n_list = new ArrayList<PriceListBean>();
		for (PriceListBean pBean : list)
		{
			pBean = PriceListManager.getPriceList(ctx, pBean.getPriceListId(), null);			
			n_list.add(pBean);
		}	
		request.getSession().setAttribute(Constants.LIST_PRICE_LISTS, n_list);
		return mapping.findForward(LIST_PRICE_LISTS);
		
	}
	
	public static final String LOAD_PRICE_LISTS = "loadPriceLists";
	
	public ActionForward loadPriceLists(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		PriceListForm priceListForm = (PriceListForm)form;
		PriceListBean priceListBean = (PriceListBean)priceListForm.getBean();
		
		int ad_org_id = Env.getAD_Org_ID(ctx);
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(),true);	
		
		String name = priceListBean.getName();
		ArrayList<PriceListBean> list = PriceListManager.getPriceLists(ctx, ad_org_id , name, true, null, true, true, trx.getTrxName());
		String priceListHTML = PriceListManager.loadPriceListAsHTMLSelect(ctx, list);
		
		PrintWriter writer = response.getWriter();        
        writer.print(priceListHTML);
        writer.flush();
        writer.close();
		
		return null;
		
	}
	
	public ActionForward fromDeletePriceOnPriceList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException
	{
		request.setAttribute(Constants.IS_FROM_DELETE_PRODUCT_PRICE, "true");
		return mapping.findForward(LIST_PRICE_LISTS);
	}
		
}
