update AD_Column 
set isparent = 'N'
where AD_Column_ID in (13467, 13468);

update ad_tab
set ad_column_id = null
where AD_Tab_ID=701;