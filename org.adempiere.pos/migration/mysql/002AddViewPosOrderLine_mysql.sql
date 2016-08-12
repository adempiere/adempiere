DROP VIEW IF EXISTS POS_OrderLine_v;
CREATE OR REPLACE VIEW POS_OrderLine_v AS 
 SELECT ol.C_OrderLine_ID,
    ol.C_Order_ID,
    ol.AD_Client_ID,
    ol.AD_Org_ID,
    ol.IsActive,
    ol.Created,
    ol.CreatedBy,
    ol.Updated,
    ol.UpdatedBy,
    p.Name AS ProductName,
    ol.PriceActual,
    ol.QtyOrdered,
    uom.UOMSymbol AS UOMSymbol,
    t.TaxindIcator,
    t.Rate,
    ol.LineNetAmt,
    (ol.LineNetAmt + ((ol.LineNetAmt * t.Rate) / 100)) AS GrandTotal,
    ol.Discount
  FROM c_orderline ol
     INNER JOIN C_UOM uom ON(ol.C_UOM_ID = uom.C_UOM_ID)
     INNER JOIN C_Order i ON(ol.C_Order_ID = i.C_Order_ID)
     LEFT JOIN M_Product p ON(ol.M_Product_ID = p.M_Product_ID)
     LEFT JOIN C_Tax t ON(ol.C_Tax_ID = t.C_Tax_ID);