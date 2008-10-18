-- Additional Role Configuration for Discount
ALTER TABLE AD_ROLE ADD (IsDiscountUptoLimitPrice char(1) DEFAULT 'N' NOT NULL);
ALTER TABLE AD_ROLE ADD (IsDiscountAllowedOnTotal char(1) DEFAULT 'N' NOT NULL);