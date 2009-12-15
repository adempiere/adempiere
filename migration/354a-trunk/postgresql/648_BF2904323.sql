-- Fix [2904323] - Cannot set invoiced on request type

UPDATE C_Payment SET R_CVV2Match = 'N' WHERE R_CVV2Match IS NULL;

UPDATE GL_JournalBatch SET IsApproved = 'N' WHERE IsApproved IS NULL;

UPDATE PP_MRP SET IsAvailable = 'N' WHERE IsAvailable IS NULL;

UPDATE PP_Order SET IsQtyPercentage = 'N' WHERE IsQtyPercentage IS NULL;

UPDATE PP_Order_Node SET IsMilestone = 'N' WHERE IsMilestone IS NULL;

UPDATE PP_Order_Node SET IsSubcontracting = 'N' WHERE IsSubcontracting IS NULL;

UPDATE R_RequestType SET IsInvoiced = 'N' WHERE IsInvoiced IS NULL;

