CREATE OR REPLACE VIEW AD_WINDOW_VT
(AD_LANGUAGE, AD_WINDOW_ID, NAME, DESCRIPTION, HELP, 
 WINDOWTYPE, AD_COLOR_ID, AD_IMAGE_ID, ISACTIVE, WINWIDTH, 
 WINHEIGHT, ISSOTRX)
AS 
SELECT trl.AD_Language, 
	bt.AD_Window_ID, trl.Name, trl.Description, trl.Help, bt.WindowType, 
	bt.AD_Color_ID, bt.AD_Image_ID, bt.IsActive, bt.WinWidth, bt.WinHeight,
    bt.IsSOTrx
FROM AD_Window bt
	INNER JOIN AD_Window_Trl trl ON (bt.AD_Window_ID=trl.AD_Window_ID)
WHERE bt.IsActive='Y';



