ALTER TABLE U_POSTerminal 
ADD COLUMN Value VARCHAR(40)
;

UPDATE U_POSTerminal
 Set Value = nextidfunc( (SELECT AD_Sequence_ID FROM AD_Sequence WHERE AD_Client_ID = 0 AND Name = 'U_POSTerminal')::integer, 'N'::varchar)
WHERE U_POSTerminal.Value IS NULL
 AND U_POSTerminal.AD_Client_ID = 11
;

ALTER TABLE U_POSTerminal
 ADD Constraint U_POSTerminal_Value UNIQUE(AD_Client_ID, Value)
;

-- Jan 20, 2009 10:32:14 PM EET
-- [ 2523861 ] Add Value column to U_POSTerminal table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56677,620,0,10,52004,'Value',TO_TIMESTAMP('2009-01-20 22:32:11','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique','D',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','N','Y','Search Key',TO_TIMESTAMP('2009-01-20 22:32:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jan 20, 2009 10:32:14 PM EET
-- [ 2523861 ] Add Value column to U_POSTerminal table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56677 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 20, 2009 10:38:13 PM EET
-- [ 2523861 ] Add Value column to U_POSTerminal table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,56677,56620,0,53181,TO_TIMESTAMP('2009-01-20 22:38:12','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique',0,'D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','N','Search Key',25,0,TO_TIMESTAMP('2009-01-20 22:38:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 20, 2009 10:38:13 PM EET
-- [ 2523861 ] Add Value column to U_POSTerminal table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56620 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;
