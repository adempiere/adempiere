CREATE OR REPLACE VIEW RV_PRINTFORMATDETAIL
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_PRINTFORMAT_ID, NAME, DESCRIPTION, 
 ISTABLEBASED, ISFORM, AD_TABLE_ID, AD_REPORTVIEW_ID, AD_PRINTPAPER_ID, 
 DEFAULT_AD_PRINTCOLOR_ID, DEFAULT_AD_PRINTFONT_ID, ISSTANDARDHEADERFOOTER, AD_PRINTTABLEFORMAT_ID, HEADERMARGIN, 
 FOOTERMARGIN, PRINTERNAME, ISDEFAULT, AD_PRINTFORMATITEM_ID, ITEMNAME, 
 PRINTNAME, PRINTNAMESUFFIX, ISPRINTED, PRINTAREATYPE, SEQNO, 
 PRINTFORMATTYPE, AD_COLUMN_ID, AD_PRINTFORMATCHILD_ID, IMAGEISATTACHED, IMAGEURL, 
 ISRELATIVEPOSITION, ISNEXTLINE, XSPACE, YSPACE, XPOSITION, 
 YPOSITION, MAXWIDTH, ISHEIGHTONELINE, MAXHEIGHT, ISFIXEDWIDTH, 
 ISSETNLPOSITION, ISSUPPRESSNULL, BELOWCOLUMN, FIELDALIGNMENTTYPE, LINEALIGNMENTTYPE, 
 AD_PRINTCOLOR_ID, AD_PRINTFONT_ID, ISORDERBY, SORTNO, ISGROUPBY, 
 ISPAGEBREAK, ISNEXTPAGE, ISSUMMARIZED, ISAVERAGED, ISCOUNTED, 
 ISMINCALC, ISMAXCALC, ISVARIANCECALC, ISDEVIATIONCALC, ISRUNNINGTOTAL, 
 RUNNINGTOTALLINES, AD_PRINTGRAPH_ID)
AS 
SELECT f.AD_Client_ID, f.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
    f.AD_PrintFormat_ID,
    f.Name, f.Description,
    f.IsTableBased,f.IsForm,f.AD_Table_ID,f.AD_ReportView_ID,
    f.AD_PrintPaper_ID, 
    f.AD_PrintColor_ID AS Default_AD_PrintColor_ID,
    f.AD_PrintFont_ID AS Default_AD_PrintFont_ID,
    f.IsStandardHeaderFooter,
    f.AD_PrintTableFormat_ID,
    f.HeaderMargin,f.FooterMargin,
    f.PrinterName,f.IsDefault,
    i.AD_PrintFormatItem_ID,
    i.Name AS ItemName, i.PrintName, i.PrintNameSuffix, i.IsPrinted, i.PrintAreaType, i.SeqNo,
    i.PrintFormatType, i.AD_Column_ID, i.AD_PrintFormatChild_ID, i.ImageIsAttached,i.ImageURL,
    i.IsRelativePosition, i.IsNextLine, XSpace,YSpace, XPosition,YPosition,
    i.MaxWidth, IsHeightOneLine, MaxHeight, i.IsFixedWidth,
    i.IsSetNLPosition,i.IsSuppressNull, i.BelowColumn,
    i.FieldAlignmentType,i.LineAlignmentType,
    i.AD_PrintColor_ID,i.AD_PrintFont_ID,
    i.IsOrderBy,i.SortNo, i.IsGroupBy,i.IsPageBreak,i.IsNextPage,
    i.IsSummarized,i.IsAveraged,i.IsCounted,i.IsMinCalc,i.IsMaxCalc,
    i.IsVarianceCalc,i.IsDeviationCalc,
    i.IsRunningTotal,i.RunningTotalLines,
    i.AD_PrintGraph_ID
FROM AD_PrintFormat f
  INNER JOIN AD_PrintFormatItem i ON (f.AD_PrintFormat_ID=i.AD_PrintFormat_ID);



