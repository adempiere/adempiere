--Fixed bug report [ 1716528 ] R_IssueSource not a System table.
--https://sourceforge.net/tracker/index.php?func=detail&aid=1716528&group_id=176962&atid=879332

update ad_table 
   set entitytype = 'D', 
       tablename = 'R_IssueSource', 
       name = 'R_IssueSource' 
 where ad_table_id = 1000006;

commit;