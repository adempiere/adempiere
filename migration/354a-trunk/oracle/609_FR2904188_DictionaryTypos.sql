-- Nov 26, 2009 12:09:29 PM COT
-- FR2904188 Typos in dictionary
UPDATE AD_Element SET Help='Calculate the Maximum (↑)  of the data if the field is numeric, otherwise maximum length of the field.', Name='Calculate Maximum (↑)',Updated=TO_DATE('2009-11-26 12:09:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2207
;

-- Nov 26, 2009 12:09:29 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2207
;

-- Nov 26, 2009 12:09:29 PM COT
UPDATE AD_Column SET ColumnName='IsMaxCalc', Name='Calculate Maximum (↑)', Description='Calculate the maximim amount', Help='Calculate the Maximum (↑)  of the data if the field is numeric, otherwise maximum length of the field.' WHERE AD_Element_ID=2207
;

-- Nov 26, 2009 12:09:29 PM COT
UPDATE AD_Process_Para SET ColumnName='IsMaxCalc', Name='Calculate Maximum (↑)', Description='Calculate the maximim amount', Help='Calculate the Maximum (↑)  of the data if the field is numeric, otherwise maximum length of the field.', AD_Element_ID=2207 WHERE UPPER(ColumnName)='ISMAXCALC' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 26, 2009 12:09:29 PM COT
UPDATE AD_Process_Para SET ColumnName='IsMaxCalc', Name='Calculate Maximum (↑)', Description='Calculate the maximim amount', Help='Calculate the Maximum (↑)  of the data if the field is numeric, otherwise maximum length of the field.' WHERE AD_Element_ID=2207 AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:09:29 PM COT
UPDATE AD_Field SET Name='Calculate Maximum (↑)', Description='Calculate the maximim amount', Help='Calculate the Maximum (↑)  of the data if the field is numeric, otherwise maximum length of the field.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2207) AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:09:30 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Calc Max', Name='Calculate Maximum (↑)' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=2207)
;

-- Nov 26, 2009 12:10:13 PM COT
UPDATE AD_Element SET Help='Calculate the Minimum (↓) of the data if the field is numeric, otherwise minimum length of the field.', Name='Calculate Minimum (↓)',Updated=TO_DATE('2009-11-26 12:10:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2208
;

-- Nov 26, 2009 12:10:13 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2208
;

-- Nov 26, 2009 12:10:13 PM COT
UPDATE AD_Column SET ColumnName='IsMinCalc', Name='Calculate Minimum (↓)', Description='Calculate the minimum amount', Help='Calculate the Minimum (↓) of the data if the field is numeric, otherwise minimum length of the field.' WHERE AD_Element_ID=2208
;

-- Nov 26, 2009 12:10:14 PM COT
UPDATE AD_Process_Para SET ColumnName='IsMinCalc', Name='Calculate Minimum (↓)', Description='Calculate the minimum amount', Help='Calculate the Minimum (↓) of the data if the field is numeric, otherwise minimum length of the field.', AD_Element_ID=2208 WHERE UPPER(ColumnName)='ISMINCALC' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 26, 2009 12:10:14 PM COT
UPDATE AD_Process_Para SET ColumnName='IsMinCalc', Name='Calculate Minimum (↓)', Description='Calculate the minimum amount', Help='Calculate the Minimum (↓) of the data if the field is numeric, otherwise minimum length of the field.' WHERE AD_Element_ID=2208 AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:10:14 PM COT
UPDATE AD_Field SET Name='Calculate Minimum (↓)', Description='Calculate the minimum amount', Help='Calculate the Minimum (↓) of the data if the field is numeric, otherwise minimum length of the field.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2208) AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:10:14 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Calc Min', Name='Calculate Minimum (↓)' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=2208)
;

-- Nov 26, 2009 12:10:43 PM COT
UPDATE AD_Element SET Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (?)', Name='Calculate Deviation (σ)',Updated=TO_DATE('2009-11-26 12:10:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2275
;

-- Nov 26, 2009 12:10:43 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2275
;

-- Nov 26, 2009 12:10:43 PM COT
UPDATE AD_Column SET ColumnName='IsDeviationCalc', Name='Calculate Deviation (σ)', Description='Calculate Standard Deviation', Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (?)' WHERE AD_Element_ID=2275
;

-- Nov 26, 2009 12:10:44 PM COT
UPDATE AD_Process_Para SET ColumnName='IsDeviationCalc', Name='Calculate Deviation (σ)', Description='Calculate Standard Deviation', Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (?)', AD_Element_ID=2275 WHERE UPPER(ColumnName)='ISDEVIATIONCALC' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 26, 2009 12:10:44 PM COT
UPDATE AD_Process_Para SET ColumnName='IsDeviationCalc', Name='Calculate Deviation (σ)', Description='Calculate Standard Deviation', Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (?)' WHERE AD_Element_ID=2275 AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:10:44 PM COT
UPDATE AD_Field SET Name='Calculate Deviation (σ)', Description='Calculate Standard Deviation', Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (?)' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2275) AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:10:44 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Deviation', Name='Calculate Deviation (σ)' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=2275)
;

-- Nov 26, 2009 12:11:09 PM COT
UPDATE AD_Element SET Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (?)', Name='Calculate Variance (σ²)',Updated=TO_DATE('2009-11-26 12:11:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2276
;

-- Nov 26, 2009 12:11:09 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2276
;

-- Nov 26, 2009 12:11:09 PM COT
UPDATE AD_Column SET ColumnName='IsVarianceCalc', Name='Calculate Variance (σ²)', Description='Calculate Variance', Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (?)' WHERE AD_Element_ID=2276
;

-- Nov 26, 2009 12:11:09 PM COT
UPDATE AD_Process_Para SET ColumnName='IsVarianceCalc', Name='Calculate Variance (σ²)', Description='Calculate Variance', Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (?)', AD_Element_ID=2276 WHERE UPPER(ColumnName)='ISVARIANCECALC' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 26, 2009 12:11:09 PM COT
UPDATE AD_Process_Para SET ColumnName='IsVarianceCalc', Name='Calculate Variance (σ²)', Description='Calculate Variance', Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (?)' WHERE AD_Element_ID=2276 AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:11:09 PM COT
UPDATE AD_Field SET Name='Calculate Variance (σ²)', Description='Calculate Variance', Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (?)' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2276) AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:11:09 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Variance', Name='Calculate Variance (σ²)' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=2276)
;

-- Nov 26, 2009 12:11:22 PM COT
UPDATE AD_Element SET Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (μ)',Updated=TO_DATE('2009-11-26 12:11:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2276
;

-- Nov 26, 2009 12:11:22 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2276
;

-- Nov 26, 2009 12:11:22 PM COT
UPDATE AD_Column SET ColumnName='IsVarianceCalc', Name='Calculate Variance (σ²)', Description='Calculate Variance', Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (μ)' WHERE AD_Element_ID=2276
;

-- Nov 26, 2009 12:11:22 PM COT
UPDATE AD_Process_Para SET ColumnName='IsVarianceCalc', Name='Calculate Variance (σ²)', Description='Calculate Variance', Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (μ)', AD_Element_ID=2276 WHERE UPPER(ColumnName)='ISVARIANCECALC' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 26, 2009 12:11:22 PM COT
UPDATE AD_Process_Para SET ColumnName='IsVarianceCalc', Name='Calculate Variance (σ²)', Description='Calculate Variance', Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (μ)' WHERE AD_Element_ID=2276 AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:11:22 PM COT
UPDATE AD_Field SET Name='Calculate Variance (σ²)', Description='Calculate Variance', Help='The Variance (σ²) is the a measure of dispersion - used in combination with the Mean (μ)' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2276) AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:11:52 PM COT
UPDATE AD_Element SET Help='Calculate the Sum (Σ) of the data if the field is numeric, otherwise total sum length of the field.', Name='Calculate Sum (Σ)',Updated=TO_DATE('2009-11-26 12:11:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1241
;

-- Nov 26, 2009 12:11:52 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1241
;

-- Nov 26, 2009 12:11:52 PM COT
UPDATE AD_Column SET ColumnName='IsSummarized', Name='Calculate Sum (Σ)', Description='Calculate the Sum of numeric content or length', Help='Calculate the Sum (Σ) of the data if the field is numeric, otherwise total sum length of the field.' WHERE AD_Element_ID=1241
;

-- Nov 26, 2009 12:11:52 PM COT
UPDATE AD_Process_Para SET ColumnName='IsSummarized', Name='Calculate Sum (Σ)', Description='Calculate the Sum of numeric content or length', Help='Calculate the Sum (Σ) of the data if the field is numeric, otherwise total sum length of the field.', AD_Element_ID=1241 WHERE UPPER(ColumnName)='ISSUMMARIZED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 26, 2009 12:11:52 PM COT
UPDATE AD_Process_Para SET ColumnName='IsSummarized', Name='Calculate Sum (Σ)', Description='Calculate the Sum of numeric content or length', Help='Calculate the Sum (Σ) of the data if the field is numeric, otherwise total sum length of the field.' WHERE AD_Element_ID=1241 AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:11:52 PM COT
UPDATE AD_Field SET Name='Calculate Sum (Σ)', Description='Calculate the Sum of numeric content or length', Help='Calculate the Sum (Σ) of the data if the field is numeric, otherwise total sum length of the field.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1241) AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:11:52 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Sum', Name='Calculate Sum (Σ)' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=1241)
;

-- Nov 26, 2009 12:12:27 PM COT
UPDATE AD_Element SET Help='Calculate the Mean (μ) of the data if the field is numeric, otherwise calculate the average length of the field.', Name='Calculate Mean (μ)',Updated=TO_DATE('2009-11-26 12:12:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1834
;

-- Nov 26, 2009 12:12:27 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1834
;

-- Nov 26, 2009 12:12:27 PM COT
UPDATE AD_Column SET ColumnName='IsAveraged', Name='Calculate Mean (μ)', Description='Calculate Average of numeric content or length', Help='Calculate the Mean (μ) of the data if the field is numeric, otherwise calculate the average length of the field.' WHERE AD_Element_ID=1834
;

-- Nov 26, 2009 12:12:27 PM COT
UPDATE AD_Process_Para SET ColumnName='IsAveraged', Name='Calculate Mean (μ)', Description='Calculate Average of numeric content or length', Help='Calculate the Mean (μ) of the data if the field is numeric, otherwise calculate the average length of the field.', AD_Element_ID=1834 WHERE UPPER(ColumnName)='ISAVERAGED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 26, 2009 12:12:27 PM COT
UPDATE AD_Process_Para SET ColumnName='IsAveraged', Name='Calculate Mean (μ)', Description='Calculate Average of numeric content or length', Help='Calculate the Mean (μ) of the data if the field is numeric, otherwise calculate the average length of the field.' WHERE AD_Element_ID=1834 AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:12:27 PM COT
UPDATE AD_Field SET Name='Calculate Mean (μ)', Description='Calculate Average of numeric content or length', Help='Calculate the Mean (μ) of the data if the field is numeric, otherwise calculate the average length of the field.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1834) AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:12:27 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Mean', Name='Calculate Mean (μ)' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=1834)
;

-- Nov 26, 2009 12:12:44 PM COT
UPDATE AD_Element SET Help='Calculate the total number (№) of not empty (NULL) elements (maximum is the number of lines).', Name='Calculate Count (№)',Updated=TO_DATE('2009-11-26 12:12:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1835
;

-- Nov 26, 2009 12:12:44 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1835
;

-- Nov 26, 2009 12:12:44 PM COT
UPDATE AD_Column SET ColumnName='IsCounted', Name='Calculate Count (№)', Description='Count number of not empty elements', Help='Calculate the total number (№) of not empty (NULL) elements (maximum is the number of lines).' WHERE AD_Element_ID=1835
;

-- Nov 26, 2009 12:12:44 PM COT
UPDATE AD_Process_Para SET ColumnName='IsCounted', Name='Calculate Count (№)', Description='Count number of not empty elements', Help='Calculate the total number (№) of not empty (NULL) elements (maximum is the number of lines).', AD_Element_ID=1835 WHERE UPPER(ColumnName)='ISCOUNTED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 26, 2009 12:12:44 PM COT
UPDATE AD_Process_Para SET ColumnName='IsCounted', Name='Calculate Count (№)', Description='Count number of not empty elements', Help='Calculate the total number (№) of not empty (NULL) elements (maximum is the number of lines).' WHERE AD_Element_ID=1835 AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:12:44 PM COT
UPDATE AD_Field SET Name='Calculate Count (№)', Description='Count number of not empty elements', Help='Calculate the total number (№) of not empty (NULL) elements (maximum is the number of lines).' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1835) AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:12:44 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Count', Name='Calculate Count (№)' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=1835)
;

-- Nov 26, 2009 12:14:30 PM COT
UPDATE AD_Element SET Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (μ)',Updated=TO_DATE('2009-11-26 12:14:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2275
;

-- Nov 26, 2009 12:14:30 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2275
;

-- Nov 26, 2009 12:14:30 PM COT
UPDATE AD_Column SET ColumnName='IsDeviationCalc', Name='Calculate Deviation (σ)', Description='Calculate Standard Deviation', Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (μ)' WHERE AD_Element_ID=2275
;

-- Nov 26, 2009 12:14:30 PM COT
UPDATE AD_Process_Para SET ColumnName='IsDeviationCalc', Name='Calculate Deviation (σ)', Description='Calculate Standard Deviation', Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (μ)', AD_Element_ID=2275 WHERE UPPER(ColumnName)='ISDEVIATIONCALC' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 26, 2009 12:14:30 PM COT
UPDATE AD_Process_Para SET ColumnName='IsDeviationCalc', Name='Calculate Deviation (σ)', Description='Calculate Standard Deviation', Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (μ)' WHERE AD_Element_ID=2275 AND IsCentrallyMaintained='Y'
;

-- Nov 26, 2009 12:14:30 PM COT
UPDATE AD_Field SET Name='Calculate Deviation (σ)', Description='Calculate Standard Deviation', Help='The Standard Deviation (σ) is the a measure of dispersion - used in combination with the Mean (μ)' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2275) AND IsCentrallyMaintained='Y'
;

