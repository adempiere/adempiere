-- Additional Role Configuration for Discount
ALTER TABLE ad_role ADD COLUMN IsDiscountUptoLimitPrice char(1) NOT NULL DEFAULT 'N';
ALTER TABLE ad_role ADD COLUMN IsDiscountAllowedOnTotal char(1) NOT NULL DEFAULT 'N';