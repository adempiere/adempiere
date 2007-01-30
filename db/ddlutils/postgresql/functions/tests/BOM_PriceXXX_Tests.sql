CREATE TABLE m_productprice (
 m_pricelist_version_id  numeric,             
 m_product_id            numeric,             
 pricelist               numeric,              
 pricestd                numeric,             
 pricelimit              numeric            
);

CREATE TABLE m_product_bom (
 m_product_bom_id  numeric(10,0),
 m_product_id      numeric(10,0),              
 m_productbom_id   numeric(10,0),             
 bomqty            numeric(22,0)            
);

INSERT INTO m_productprice VALUES (1.,1.,0.,0.,0.);
INSERT INTO m_productprice VALUES (1.,2.,10.20,12.20,1.50);
INSERT INTO m_productprice VALUES (1.,3.,10.20,12.20,2.00);
INSERT INTO m_productprice VALUES (1.,4.,0,0,0);

INSERT INTO m_product_bom VALUES (1.,1.,2.,5.);
INSERT INTO m_product_bom VALUES (1.,3.,2.,1.);
INSERT INTO m_product_bom VALUES (1.,1.,2.,2.);
INSERT INTO m_product_bom VALUES (1.,4.,1.,3.);


SELECT 1 AS TestNo, bompricelimit(2.,1.), 1.50 AS ExpectedValue;
SELECT 2 AS TestNo, bompricelimit(1.,1.), 10.50 AS ExpectedValue;
SELECT 3 AS TestNo, bompricelimit(4.,1.), 31.50 AS ExpectedValue;


SELECT 4 AS TestNo, bompricelist(2.,1.), 10.20 AS ExpectedValue;
SELECT 5 AS TestNo, bompricelist(1.,1.), 71.40 AS ExpectedValue;
SELECT 6 AS TestNo, bompricelist(4.,1.), 214.20 AS ExpectedValue;

SELECT 5 AS TestNo, bompricestd(2.,1.), 12.20 AS ExpectedValue;
SELECT 6 AS TestNo, bompricestd(1.,1.), 85.40 AS ExpectedValue;
SELECT 7 AS TestNo, bompricestd(4.,1.), 256.20 AS ExpectedValue;

DROP TABLE m_product_bom;
DROP TABLE m_productprice;

