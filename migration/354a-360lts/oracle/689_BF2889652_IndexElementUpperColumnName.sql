DROP INDEX ad_element_columnname;

CREATE UNIQUE INDEX ad_element_uppercolumnname
    ON ad_element(UPPER(columnname));
