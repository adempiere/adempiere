-- reversed allocations should be marked posted (with no facts)
update c_allocationhdr
set posted = 'Y'
where posted = 'N'
and processed = 'Y'
and docstatus = 'RE'
and isactive = 'N';
