/************* bomPriceLimit Alias *******************/
CREATE OR REPLACE FUNCTION bomPriceLimit (M_Product_ID NUMERIC, M_PriceList_Version_ID INTEGER)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomPriceLimit(ID(M_Product_ID),M_PriceList_Version_ID);
END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION bomPriceLimit (M_Product_ID NUMERIC, M_PriceList_Version_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomPriceLimit(ID(M_Product_ID),ID(M_PriceList_Version_ID));
END;
$$ LANGUAGE plpgsql VOLATILE;

/************* bomPriceList  *******************/
CREATE OR REPLACE FUNCTION bomPriceList (M_Product_ID NUMERIC, M_PriceList_Version_ID INTEGER)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomPriceList(ID(M_Product_ID),M_PriceList_Version_ID);
END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION bomPriceList (M_Product_ID NUMERIC, M_PriceList_Version_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomPriceList(ID(M_Product_ID),ID(M_PriceList_Version_ID));
END;
$$ LANGUAGE plpgsql VOLATILE;
    
/************* bomPriceStd *******************/
CREATE OR REPLACE FUNCTION bomPriceStd (M_Product_ID NUMERIC, M_PriceList_Version_ID INTEGER)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomPriceStd(ID(M_Product_ID),M_PriceList_Version_ID);
END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION bomPriceStd (M_Product_ID NUMERIC, M_PriceList_Version_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomPriceStd(ID(M_Product_ID),ID(M_PriceList_Version_ID));
END;
$$ LANGUAGE plpgsql VOLATILE;
    
/************* bomQtyAvailable *******************/
CREATE OR REPLACE FUNCTION bomQtyAvailable (M_Product_ID NUMERIC, M_Warehouse_ID INTEGER, M_Locator_ID INTEGER)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomQtyAvailable(ID(M_Product_ID),M_Warehouse_ID,M_Locator_ID);
END;
$$ LANGUAGE plpgsql VOLATILE;
    
    	
/************* bomQtyOnHand *******************/
CREATE OR REPLACE FUNCTION bomQtyOnHand (M_Product_ID NUMERIC, M_Warehouse_ID INTEGER, M_Locator_ID INTEGER)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomQtyOnHand(ID(M_Product_ID),M_Warehouse_ID,M_Locator_ID);
END;
$$ LANGUAGE plpgsql VOLATILE;
    
    	
/************* bomQtyOrdered *******************/
CREATE OR REPLACE FUNCTION bomQtyOrdered (M_Product_ID NUMERIC, M_Warehouse_ID INTEGER, M_Locator_ID INTEGER)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomQtyOrdered(ID(M_Product_ID),M_Warehouse_ID,M_Locator_ID);
END;
$$ LANGUAGE plpgsql VOLATILE;
    
    	
/************* bomQtyReserved *******************/
CREATE OR REPLACE FUNCTION bomQtyReserved  (M_Product_ID NUMERIC, M_Warehouse_ID INTEGER, M_Locator_ID INTEGER)
RETURNS NUMERIC AS $$
BEGIN
        RETURN bomQtyReserved(ID(M_Product_ID),M_Warehouse_ID,M_Locator_ID);
END;
$$ LANGUAGE plpgsql VOLATILE;