
CREATE TABLE C_ElementValue (
  C_ElementValue_ID       NUMERIC PRIMARY KEY,
  AccountType             CHAR(1),
  AccountSign             CHAR(1)
);

INSERT INTO C_ElementValue VALUES(1., 'A', 'N');
INSERT INTO C_ElementValue VALUES(2., 'L', 'N');
INSERT INTO C_ElementValue VALUES(3., 'R', 'N');
INSERT INTO C_ElementValue VALUES(4., 'E', 'N');
INSERT INTO C_ElementValue VALUES(5., 'O', 'N');
INSERT INTO C_ElementValue VALUES(6., 'M', 'N');
INSERT INTO C_ElementValue VALUES(7., 'M', 'C');
INSERT INTO C_ElementValue VALUES(8., 'M', 'C');
INSERT INTO C_ElementValue VALUES(9., 'M', 'D');
INSERT INTO C_ElementValue VALUES(10., 'M', 'D');

SELECT 1 AS TestNo, acctbalance(1,40.50,50.50), 10.00 AS ExpectedValue;
SELECT 2 AS TestNo, acctbalance(2,40.50,50.50), -10.00 AS ExpectedValue;
SELECT 3 AS TestNo, acctbalance(3,40.50,50.50), -10.00 AS ExpectedValue;
SELECT 4 AS TestNo, acctbalance(4,40.50,50.50), 10.00 AS ExpectedValue;
SELECT 5 AS TestNo, acctbalance(5,40.50,50.50), -10.00 AS ExpectedValue;
SELECT 6 AS TestNo, acctbalance(6,40.50,50.50), -10.00 AS ExpectedValue;
SELECT 7 AS TestNo, acctbalance(7,40.50,50.50), -10.00 AS ExpectedValue;
SELECT 8 AS TestNo, acctbalance(8,40.50,50.50), -10.00 AS ExpectedValue;
SELECT 9 AS TestNo, acctbalance(9,40.50,50.50), 10.00 AS ExpectedValue;
SELECT 10 AS TestNo, acctbalance(10,40.50,50.50), 10.00 AS ExpectedValue;

SELECT 11 AS TestNo, acctbalance(null,40.50,50.50), 10.00 AS ExpectedValue;
SELECT 12 AS TestNo, acctbalance(10,null,50.50), 50.50 AS ExpectedValue;
SELECT 13 AS TestNo, acctbalance(10,50.50,null), -50.50 AS ExpectedValue;
DROP TABLE C_ElementValue;
