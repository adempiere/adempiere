-- [ 1871741 ] Collapse grid as default
UPDATE AD_FieldGroup SET FieldGroupType = 'C' WHERE FieldGroupType IS NULL;