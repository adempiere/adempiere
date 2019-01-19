/*************************************************************************
 * $Id: CreateUser.sql,v 1.1 2006/12/27 17:51:58 globalqss Exp $
 ***
 * Title:	Drop User and re-create new
 * Description:	
 *	Parameter: UserID UserPwd
 *	Run as postgres
 ************************************************************************/
DROP DATABASE &1;

DROP USER &1;

CREATE USER &1 WITH CREATEDB CREATEUSER PASSWORD '&2'

CREATE DATABASE &1
  WITH ENCODING='UNICODE'
       OWNER=&1; 