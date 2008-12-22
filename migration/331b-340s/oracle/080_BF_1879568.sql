-- BF [ 1879568 ] CalloutMouvement QtyAvailable issues
-- http://sourceforge.net/tracker/?func=detail&atid=879332&aid=1879568&group_id=176962
--
-- WARNING: if you already modified the Callout column, this update will do nothing, solve the issue manually
update AD_Column set Callout='org.compiere.model.CalloutMovement.locator'
where AD_Column_ID=3591 and Callout is null;
--
commit;
