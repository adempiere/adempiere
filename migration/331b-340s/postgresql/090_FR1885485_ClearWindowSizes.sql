-- [ 1885485 ] Some windows too little
UPDATE AD_WINDOW
SET winheight = NULL, winwidth = NULL
WHERE ad_window_id IN (140, 143, 181, 195, 240)
;