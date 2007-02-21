UPDATE ad_process
SET classname = 'org.adempiere.pipo.PackRoll'
WHERE classname = 'org.compiere.PackOut.PackRoll';

UPDATE ad_process
SET classname = 'org.adempiere.pipo.PackOut'
WHERE classname = 'org.compiere.PackOut.PackOut';

UPDATE ad_process
SET classname = 'org.adempiere.pipo.PackIn'
WHERE classname = 'org.compiere.PackOut.IntPackIn';

COMMIT ;
