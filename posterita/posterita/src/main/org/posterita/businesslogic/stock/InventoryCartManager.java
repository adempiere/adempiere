package org.posterita.businesslogic.stock;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.NumberFormatter;

import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.tbody;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.tfoot;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.thead;
import org.apache.ecs.xhtml.tr;
import org.apache.struts.upload.FormFile;
import org.compiere.model.MDocType;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MUOM;
import org.compiere.process.InventoryCountCreate;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.InventoryCartBean;
import org.posterita.beans.InventoryLineBean;
import org.posterita.beans.ItemBean;
import org.posterita.beans.ProductBean;
import org.posterita.businesslogic.POSProductManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.performanceanalysis.CSVReportManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.CannotCreateInventoryLineException;
import org.posterita.exceptions.ImportProductException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotImportedException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;

public class InventoryCartManager
{
    private static final int CREATE_INVENTORY_COUNT_LIST_PROCESS=289;
    
    private static String getInventoryCartAsHTML(ArrayList<ItemBean> itemList, Properties ctx) throws ParseException, OperationException
    {
        NumberFormatter formatter = new NumberFormatter(new DecimalFormat("0"));
        
        int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);       
        String currSymbol = PriceListManager.getCurrency(ctx, priceListId);
        
        BigDecimal totalQtyCount = Env.ZERO;
        BigDecimal totalQtyBook = Env.ZERO;
        BigDecimal totalQtyCsv = Env.ZERO;
        BigDecimal difference = Env.ZERO;
        BigDecimal totalDifference = Env.ZERO;
        
        String[] headers = {"Name","Description","UOM","Quantity CSV", "Quantity Book","Quantity Count", "Difference"};
        table tbl = new table("0", "0", "0", "100%", "");
        tbl.setID("inventoryCart");
        tbl.addAttribute("class", "filterable sortable");
        
        BigDecimal qtyCsv = Env.ZERO;
        BigDecimal qtyCount = Env.ZERO;
        BigDecimal qtyBook = Env.ZERO;
        
        thead theader = new thead();
        
        tr headerRow = new tr();
        headerRow.setClass("itemTitleList");
        
        for(String header : headers)
        {
            th h = new th(header);
            headerRow.addElement(h);
        }
        
        theader.addElement(headerRow);
        tbl.addElement(theader);
        
        
        //add body
        tbody body = new tbody();
        int count = 0;
        
        if(itemList != null)
        {
            for(ItemBean bean : itemList)
            {
                count ++;
                               
                tr dataRow = new tr();
                
                dataRow.setClass("evenRow");
                dataRow.setID("row" + count);
                dataRow.addAttribute("productId", bean.getProductId().toString());
                dataRow.addAttribute("qty", bean.getQtyCount().toString());
                dataRow.addAttribute("qtyBook", bean.getQtyBook().toString());
                
                qtyCount = bean.getQtyCount();
                qtyCsv = bean.getQtyCsv();
                qtyBook = bean.getQtyBook();
                
                difference = qtyBook.subtract(qtyCount);
                
                totalQtyCount = totalQtyCount.add(bean.getQtyCount());
                totalQtyBook = totalQtyBook.add(bean.getQtyBook());
                totalQtyCsv = totalQtyCsv.add(bean.getQtyCsv());
                totalDifference = totalDifference.add(difference); 
                
                int n = qtyCount.compareTo(qtyBook);
                
                //Greater
                if(n == -1)
                {
                    dataRow.addAttribute("style", "color:#0101DF");
                }
                
                //Equal
                if(n == 0)
                {
                    dataRow.addAttribute("style", "color:#000000");
                }
                
                //less
                if(n == 1)
                {
                    dataRow.addAttribute("style", "color:#DF0101");
                }
                
                
                
                String[] columnData = {
                        bean.getProductName(), 
                        bean.getDescription(),
                        bean.getUom(),
                        formatter.valueToString(bean.getQtyCsv()),
                        formatter.valueToString(bean.getQtyBook()),
                        formatter.valueToString(bean.getQtyCount()),
                        formatter.valueToString(difference)
                    };
                
                for(int i=0; i<columnData.length; i++)
                {
                    td col = new td(columnData[i]);
                                        
                    if(i == columnData.length)
                    {
                        col.setClass("totalItem");
                    }
                                       
                    dataRow.addElement(col);
                }
                body.addElement(dataRow);
                
            }
        }
        
        
        tbl.addElement(body);
        tfoot footer = new tfoot();
        tr footerRow = new tr();
        footerRow.setClass("itemsTotal");
        footerRow.addElement(new td("<b>Total</b>&nbsp;").setColSpan("3"));
        footerRow.addElement(new td(new div(formatter.valueToString(totalQtyCsv)).setID("cartTotalCsv")));
        footerRow.addElement(new td(new div(formatter.valueToString(totalQtyBook)).setID("cartTotalBook")));
        footerRow.addElement(new td(new div(formatter.valueToString(totalQtyCount)).setID("cartTotalCount")));
        footerRow.addElement(new td(new div(formatter.valueToString(totalDifference)).setID("cartDifference")));
        
        footer.addElement(footerRow);       
        tbl.addElement(footer);
                
        
        return tbl.toString();
    }
    
    public static String getInventoryCartAsHTML(HttpServletRequest request) throws ParseException, OperationException
    {
        //add body
        ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.INVENTORY_CART_ITEMS);
        Properties ctx = TmkJSPEnv.getCtx(request);     
        return getInventoryCartAsHTML(itemList, ctx);
    }
    
    public static void clearInventorycart(Properties ctx, HttpServletRequest request)
    {
        //bug fix
        request.setAttribute("cartTotalCsv", null);
        request.setAttribute("cartTotalBook", null);
        request.setAttribute("cartTotalCount", null);
        
        Env.setContext(ctx, Constants.DESCRIPTION, "");
        Env.setContext(ctx, Constants.INVENTORY_ID, "");
        
        request.getSession().setAttribute(Constants.DOC_NO, null);
        request.getSession().setAttribute(Constants.DOC_STATUS, null);
        request.getSession().setAttribute(Constants.DESCRIPTION, null);
        request.getSession().setAttribute(Constants.INVENTORY_ID, null);
        request.getSession().setAttribute(Constants.INVENTORY_CART, null);      
        request.getSession().setAttribute(Constants.INVENTORY_CART_ITEMS, null);
    }
    
    public static void saveCountQtyInInventoryLine(Properties ctx,int inventoryLineId, BigDecimal qtyBook, BigDecimal countQty, BigDecimal qtyCsv, String trxName) throws OperationException
    {
        MInventoryLine line = new MInventoryLine(ctx,inventoryLineId,trxName);
        line.setQtyCount(countQty);
        line.setQtyBook(qtyBook);
        line.setQtyCsv(qtyCsv);
        PoManager.save(line);
    }
    
    public static boolean addInventoryLine(Properties  ctx, Integer inventoryId,Integer productId, BigDecimal qtyBook, BigDecimal qtyCount, BigDecimal qtyCsv, boolean updateCount,String trxName) throws  OperationException
    {
         if(productId == null || productId.intValue()==0)
         {
           throw new ProductNotFoundException("product not available");
         }
         
        MProduct product = new MProduct(ctx,productId.intValue(),trxName);
        if(product.getProductType().equalsIgnoreCase(MProduct.PRODUCTTYPE_Service))
        {
            throw new CannotCreateInventoryLineException("product is a service, cannot add to inventory");
        }
        MInventory inventory = new MInventory(ctx,inventoryId,trxName);
        MInventoryLine inventoryLine[]=inventory.getLines(true);
        boolean added=true;
        if(inventoryLine.length>0)
        {
            for(int i=0;i<inventoryLine.length;i++)
            {
                if(productId == inventoryLine[i].getM_Product_ID())
                {                    
                    saveCountQtyInInventoryLine(ctx,inventoryLine[i].get_ID(), qtyBook, qtyCount, qtyCsv, trxName);
                    return added;
                }
            }
        }
        
        ProcessInfoParameter param[]=
        {
                new ProcessInfoParameter("ProductValue",product.getValue(),null,null,null),
                new ProcessInfoParameter("DeleteOld","N",null,null,null),
                
        };
        
        MPInstance instance = new MPInstance(ctx,CREATE_INVENTORY_COUNT_LIST_PROCESS,inventoryId.intValue());
        instance.save();
        ProcessInfo poInfo = new ProcessInfo("Create Inventory Count List",CREATE_INVENTORY_COUNT_LIST_PROCESS);
        poInfo.setParameter(param);
        poInfo.setRecord_ID(inventoryId.intValue());
        poInfo.setAD_Process_ID(CREATE_INVENTORY_COUNT_LIST_PROCESS);
        poInfo.setAD_PInstance_ID(instance.get_ID());
        InventoryCountCreate countCreate = new InventoryCountCreate();
        Trx trx = null;
        
        if (trxName != null)
        {
            trx = Trx.get(trxName, false);
        }
        
        boolean success = countCreate.startProcess(ctx,poInfo,trx);
        if(success==false)
        {
            throw new OperationException("Problem encountered while creating inventory");
        }
        
        if(updateCount == true)
        {
            int createdInvLineId = getInventoryLineId(ctx, inventoryId, productId, trxName);
            MInventoryLine createdInvLine = new MInventoryLine(ctx, createdInvLineId, trxName);
            createdInvLine.setQtyCount(qtyCount);
            createdInvLine.setQtyCsv(qtyCsv);
            PoManager.save(createdInvLine);
        }
       
        return added;
    }
    
    public static int getInventoryLineId(Properties ctx, int inventoryId, int productId, String trxName) throws OperationException
    {
        StringBuffer whereClause = new StringBuffer();
        whereClause.append("M_Inventory_ID=").append(inventoryId);
        whereClause.append(" AND M_Product_ID=").append(productId);
        
        int inventoryLines[] = MInventoryLine.getAllIDs(MInventoryLine.Table_Name, whereClause.toString(), trxName);
        
        if (inventoryLines.length == 0)
        {
            throw new OperationException("No inventory line found");
        }
        else if (inventoryLines.length > 1)
        {
            throw new OperationException("More that one inventory lines found matching criteria");
        }
        else
        {
            return inventoryLines[0];
        }
        
    }
    
    public static MInventory createInventory(Properties ctx,String description,String trxName) throws OperationException
    {
        MInventory inventory = new MInventory(ctx,0,trxName);
        MDocType[] docTypes= MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_MaterialPhysicalInventory);
        if(docTypes.length>1)
            throw new OperationException("More than one document type for Physical Inventory");
        
        inventory.setC_DocType_ID(docTypes[0].get_ID());
        inventory.setM_Warehouse_ID(POSTerminalManager.getWarehouseId(ctx));
        if(description!=null)
            inventory.setDescription(description);
        
        PoManager.save(inventory);
        return inventory;
        
    }
    
    public static ArrayList<ItemBean> getInventoryLines(Properties ctx,int inventoryId) throws OperationException
    {
        
        MInventory inventory = new MInventory(ctx,inventoryId,null);
        MInventoryLine inventoryLine[]=inventory.getLines(true);
        
        ArrayList<ItemBean> countList = new ArrayList<ItemBean>();
        
        ItemBean bean=null;
        BigDecimal bookQtyValue=null;
        BigDecimal countQtyValue=null;
        
        for(int i=0;i<inventoryLine.length;i++)
        {
            bean=new ItemBean();
            MProduct product = new MProduct(ctx,inventoryLine[i].getM_Product_ID(),null);
            int uomPrecision = MUOM.getPrecision(ctx, product.getC_UOM_ID());
            String uom = product.getUOMSymbol();
            bean.setUom(uom);
            bean.setInventoryId(inventoryLine[i].getM_Inventory_ID());
            bean.setInventoryLineId(inventoryLine[i].get_ID());
            bean.setQtyBook(inventoryLine[i].getQtyBook().setScale(uomPrecision, RoundingMode.HALF_UP));
            bean.setQtyCount(inventoryLine[i].getQtyCount().setScale(uomPrecision, RoundingMode.HALF_UP));
            bean.setQtyCsv(inventoryLine[i].getQtyCsv().setScale(uomPrecision, RoundingMode.HALF_UP));
            bean.setProductId(inventoryLine[i].getM_Product_ID());
            bean.setDescription(inventory.getDescription());
            
            
            
            bean.setProductName(product.getName());
            bean.setBarCode(product.getUPC());
            
            int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
            MProductPrice purchasePrice = MProductPrice.get(ctx, purchasePriceListVersionId, inventoryLine[i].getM_Product_ID(), null);
            if(purchasePrice==null)
            {
                bookQtyValue=new BigDecimal(0);
                countQtyValue=new BigDecimal(0);
            }
            else
            {
                BigDecimal purchasePriceList = purchasePrice.getPriceList();
                bookQtyValue = purchasePriceList.multiply(inventoryLine[i].getQtyBook()).setScale(purchasePriceList.scale(), RoundingMode.HALF_UP);
                countQtyValue = purchasePriceList.multiply(inventoryLine[i].getQtyCount()).setScale(purchasePriceList.scale(), RoundingMode.HALF_UP);
            }
            bean.setBookQtyValue(bookQtyValue);
            bean.setCountQtyValue(countQtyValue);
            
            countList.add(bean);
        }
        
        return countList;
    }
    
    public static void deleteInventoryLine(Properties ctx,int inventoryId,int M_INVENTORYLINE_ID)
    {
        String sql="DELETE FROM M_INVENTORYLine WHERE M_INVENTORYLINE_ID="+M_INVENTORYLINE_ID+
                    " AND M_INVENTORY_ID="+inventoryId+
                    " AND AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        
        DB.executeUpdate(sql,null);
    }
    
    public static void deleteInventoryLines(Properties ctx,int inventoryId)
    {
        String sql="DELETE FROM M_INVENTORYLine WHERE " +
                    " M_INVENTORY_ID="+inventoryId+
                    " AND AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        
        DB.executeUpdate(sql,null);
    }
    
    public static String createCSVForInventory(Properties ctx, ArrayList<ItemBean> list) throws OperationException
    {
        Object[] columns = null;
        ArrayList<Object[]> objectList = new ArrayList<Object[]>();
        Object[] header =new Object[]{"Name", "Description", "Barcode", "Quantity Csv", "Quantity Book", "Quantity Count"};
        objectList.add(header);
        
        double sumQtyBook = 0.0d;
        double sumQtyCount = 0.0d;
        double sumQtyCsv = 0.0d;
        double sumBookQtyValue = 0.0d;
        double sumCountQtyValue = 0.0d;
        
        for(ItemBean bean : list)
        {
           columns = new Object[]{
               bean.getProductName(),
               bean.getDescription(),
               bean.getBarCode(),
               bean.getQtyCsv(),
               bean.getQtyBook(),
               bean.getQtyCount(),
           };
           
           objectList.add(columns);
           
           sumQtyBook += bean.getQtyBook().doubleValue();
           sumQtyCount += bean.getQtyCount().doubleValue();
           sumQtyCsv += bean.getQtyCsv().doubleValue();

        }
        Object[] total = new Object[]{
                "Total",
                " ",
                " ",
                new Double(sumQtyCsv),
                new Double(sumQtyBook),
                new Double(sumQtyCount),
                };
        objectList.add(total);
        
        return CSVReportManager.generateCSVReport(ctx,objectList);
    }

    public static ArrayList<Object[]> getInventoryData(Properties ctx, ArrayList<ItemBean> list, String trxName) throws ParseException
    {
        
        ArrayList<Object[]> reportData = new ArrayList<Object[]>();
        NumberFormatter formatter = new NumberFormatter(new DecimalFormat("0.00"));
        NumberFormatter qtyFormatter = new NumberFormatter(new DecimalFormat("0"));
        
        String productName = null;
        String productDescrition = null;
        String barcode = null;
        
        BigDecimal qtyBook = Env.ZERO;
        BigDecimal qtyCount = Env.ZERO;
        BigDecimal qtyCsv = Env.ZERO;
        
        BigDecimal bookValue = Env.ZERO;
        BigDecimal countValue = Env.ZERO;
        
        BigDecimal qtyBookTotal = Env.ZERO;
        BigDecimal qtyCountTotal = Env.ZERO;
        BigDecimal qtyCsvTotal = Env.ZERO;
        
        BigDecimal valueBookTotal = Env.ZERO;
        BigDecimal valueCountTotal = Env.ZERO;
        
        BigDecimal qtyDifference = Env.ZERO;
        BigDecimal valueDifference = Env.ZERO;
        
        
        for(ItemBean itemBean : list)
        {
            qtyBook = itemBean.getQtyBook();
            qtyCount = itemBean.getQtyCount();
            
            qtyBookTotal = qtyBookTotal.add(qtyBook);
            qtyCountTotal = qtyCountTotal.add(qtyCount);
            
            productName = itemBean.getProductName();
            productDescrition = itemBean.getDescription();
            barcode = itemBean.getBarCode();
            
            int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
            MProductPrice purchasePrice = MProductPrice.get(ctx, purchasePriceListVersionId, itemBean.getProductId(), trxName);
            
            if(purchasePrice==null)
            {
                bookValue=new BigDecimal(0);
                countValue=new BigDecimal(0);
            }
            else
            {
                bookValue=purchasePrice.getPriceList().multiply(itemBean.getQtyBook());
                countValue=purchasePrice.getPriceList().multiply(itemBean.getQtyCount());
            }
            
            valueBookTotal = valueBookTotal.add(bookValue);
            valueCountTotal = valueCountTotal.add(countValue);
            
            qtyDifference = qtyCount.subtract(qtyBook);
            valueDifference = countValue.subtract(bookValue);
            
            Object[] data = {barcode, productName, qtyFormatter.valueToString(qtyCsv), qtyFormatter.valueToString(qtyBook), qtyFormatter.valueToString(qtyCount), formatter.valueToString(bookValue), formatter.valueToString(countValue), qtyFormatter.valueToString(qtyDifference), formatter.valueToString(valueDifference)};
            
            reportData.add(data);
        }
                
        // Add Total
        Object[] totalValue = {"TOTAL"," ", qtyFormatter.valueToString(qtyCsvTotal) ,qtyFormatter.valueToString(qtyBookTotal), qtyFormatter.valueToString(qtyCountTotal), formatter.valueToString(valueBookTotal), formatter.valueToString(valueCountTotal), qtyFormatter.valueToString(qtyCountTotal.subtract(qtyBookTotal)), formatter.valueToString(valueCountTotal.subtract(valueBookTotal))};
        reportData.add(totalValue);
        
        return reportData;
    }

    public static InventoryCartBean addItemsFromCsv(Properties ctx, FormFile file, InventoryLineBean ibean, InventoryCartBean cartBean, String trxName) throws NumberFormatException, ProductAlreadyExistException,BarcodeAlreadyExistsException, OperationException 
    {
        
        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedReader reader = null;
        
        ArrayList<InventoryLineBean> ilBean = new ArrayList<InventoryLineBean>();
        
        StringBuffer csv = new StringBuffer();
        StringBuffer importProductEx = new StringBuffer();
        try 
        {
            is = file.getInputStream();
            bis = new BufferedInputStream(is);
            reader = new BufferedReader(new InputStreamReader(bis));
            
            String s = null;
            Integer lineNumber = 0;
            String barcode = null;
            String qty=null;

            BigDecimal qtyCsv = new BigDecimal(0);

            ArrayList<String[]> uomErrorList = new ArrayList<String[]>();

            boolean isProductImported = true;
            String filename = "";
                       
            while((s=reader.readLine())!=null && s.trim().length()>0)
            {
                lineNumber++;
                StringTokenizer st = new StringTokenizer(s,",");
                
                ibean = new InventoryLineBean();
                Trx trx = Trx.get(TrxPrefix.getPrefix(),true);  
                try
                {
                    trx.start();
                    try
                    {
                        barcode = st.nextToken();
                        barcode = barcode.replaceAll("\"","");
                        qty=st.nextToken();
                        qty=qty.replaceAll("\"","");
                        qtyCsv = new BigDecimal(qty);

                    }
                    catch (NoSuchElementException e) 
                    {
                        // TODO: handle exception
                        throw new ImportProductException("An error occurred while passing the data in the file.");
                    }

                    ProductBean product = POSProductManager.getProduct(ctx, barcode, trxName);

                    ibean.setProductId(product.getProductId());
                    ibean.setBarCode(barcode);
                    ibean.setProductName(product.getProductName());
                    ibean.setDescription(product.getDescription());
                    ibean.setUom(product.getUom());
                    ibean.setQtyCount(qtyCsv);
                    ibean.setQtyCsv(qtyCsv);
                    ibean.setQtyBook(product.getQtyOnHand());
                    ibean.setPriceListId(product.getPriceListId());
                    
                    ilBean.add(ibean);                    
                    trx.commit();
                    
                    cartBean = StockManager.addToInventoryCart(ctx, ibean, cartBean, false , true);
                }
                catch (OperationException e)
                {
                    trx.rollback();                
                    importProductEx = importProductEx.append("<br> Product with barcode: "+ barcode + " at line " + lineNumber + " not imported. ").append(e.getMessage());
                    isProductImported = false;
                    csv.append(s).append("\"\n");                
                }
                finally
                {
                    trx.close();
                }
                                                
            }
                        
            if (isProductImported == false)
            {
                filename = RandomStringGenerator.randomstring() + ".csv";
                String filepath = ReportManager.getReportPath(filename);                    
                try 
                {
                    FileWriter writer = new FileWriter(filepath);
                    writer.write(csv.toString());
                    writer.flush();
                    writer.close();
                }
                catch (IOException e1) 
                {
                    throw new OperationException(e1);
                }
                throw new ProductNotImportedException(filename+importProductEx.toString());
            }
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch(Exception ex)
                {}
            }

            if (bis != null)
            {
                try
                {
                    bis.close();
                }
                catch(Exception ex)
                {}
            }

            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch(Exception ex)
                {}
            }
        }
        
        return cartBean;
        
    }
    
}
