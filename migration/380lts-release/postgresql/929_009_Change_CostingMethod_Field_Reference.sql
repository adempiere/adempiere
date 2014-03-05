-- Jul 2, 2010 3:24:26 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
--UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=122, FieldLength=1,Updated=TO_TIMESTAMP('2010-07-02 15:24:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=59584
--;

--update M_Cost c set CostingMethod=(select ce.CostingMethod FROM M_CostElement ce where ce.M_CostElement_ID=c.M_CostElement_ID)
--where Length(costingMethod)>1 or costingMethod is null;

-- Jul 2, 2010 3:24:36 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
--INSERT INTO t_alter_column values('m_cost','CostingMethod','CHAR(1)',null,'NULL')
--;

-- Jul 2, 2010 4:57:59 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=122,Updated=TO_TIMESTAMP('2010-07-02 16:57:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=59583
;

-- Jul 2, 2010 4:58:08 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('m_costdetail','CostingMethod','CHAR(1)',null,'NULL')
;

