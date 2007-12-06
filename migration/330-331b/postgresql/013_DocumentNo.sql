CREATE FUNCTION documentNo(p_MPC_MRP_ID numeric) RETURNS character varying

    AS $$org.compiere.sqlj.Manufacturing.documentNo(int)$$
    LANGUAGE java;


ALTER FUNCTION adempiere.documentNo(p_MPC_MRP_ID numeric) OWNER TO postgres;
