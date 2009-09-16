alter table a_asset_acct drop constraint a_asset_acct_pkey;

alter table a_asset_acct add constraint a_asset_acct_pkey primary key (a_asset_acct_id);

alter table c_order drop constraint c_order_m_freightcategory_id_fkey;

alter table c_order add constraint mfreightcategory_order foreign key(m_freightcategory_id) references m_freightcategory(m_freightcategory_id);
