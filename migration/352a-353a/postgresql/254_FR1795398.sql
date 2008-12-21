-- Jul 30, 2008 5:03:19 PM COT
-- [ 1795398 ] Process Parameter: add display logic
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56299,1663,0,14,285,'ReadOnlyLogic',TO_TIMESTAMP('2008-07-30 17:03:16','YYYY-MM-DD HH24:MI:SS'),100,'Logic to determine if field is read only (applies only when field is read-write)','D',2000,'format := {expression} [{logic} {expression}]<br> 
expression := @{context}@{operand}{value} or @{context}@{operand}{value}<br> 
logic := {|}|{&}<br>
context := any global or window context <br>
value := strings or numbers<br>
logic operators	:= AND or OR with the previous result from left to right <br>
operand := eq{=}, gt{&gt;}, le{&lt;}, not{~^!} <br>
Examples: <br>
@AD_Table_ID@=14 | @Language@!GERGER <br>
@PriceLimit@>10 | @PriceList@>@PriceActual@<br>
@Name@>J<br>
Strings may be in single quotes (optional)','Y','N','N','N','N','N','N','N','N','N','Y','Read Only Logic',0,TO_TIMESTAMP('2008-07-30 17:03:16','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jul 30, 2008 5:03:19 PM COT
-- [ 1795398 ] Process Parameter: add display logic
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56299 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 30, 2008 5:03:26 PM COT
-- [ 1795398 ] Process Parameter: add display logic
ALTER TABLE AD_Process_Para ADD COLUMN ReadOnlyLogic VARCHAR(2000)
;

-- Jul 30, 2008 5:08:57 PM COT
-- [ 1795398 ] Process Parameter: add display logic
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,56299,56333,0,246,TO_TIMESTAMP('2008-07-30 17:08:55','YYYY-MM-DD HH24:MI:SS'),100,'Logic to determine if field is read only (applies only when field is read-write)',60,'D','format := {expression} [{logic} {expression}]<br> 
expression := @{context}@{operand}{value} or @{context}@{operand}{value}<br> 
logic := {|}|{&}<br>
context := any global or window context <br>
value := strings or numbers<br>
logic operators	:= AND or OR with the previous result from left to right <br>
operand := eq{=}, gt{&gt;}, le{&lt;}, not{~^!} <br>
Examples: <br>
@AD_Table_ID@=14 | @Language@!GERGER <br>
@PriceLimit@>10 | @PriceList@>@PriceActual@<br>
@Name@>J<br>
Strings may be in single quotes (optional)','Y','Y','Y','N','N','N','N','N','Read Only Logic',250,0,TO_TIMESTAMP('2008-07-30 17:08:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 30, 2008 5:08:57 PM COT
-- [ 1795398 ] Process Parameter: add display logic
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56333 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


-- Jul 30, 2008 6:11:14 PM COT
-- [ 1795398 ] Process Parameter: add display logic
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56300,283,0,14,285,'DisplayLogic',TO_TIMESTAMP('2008-07-30 18:11:09','YYYY-MM-DD HH24:MI:SS'),100,'If the Field is displayed, the result determines if the field is actually displayed','D',2000,'format := {expression} [{logic} {expression}]<br> 
expression := @{context}@{operand}{value} or @{context}@{operand}{value}<br> 
logic := {|}|{&}<br>
context := any global or window context <br>
value := strings or numbers<br>
logic operators	:= AND or OR with the previous result from left to right <br>
operand := eq{=}, gt{&gt;}, le{&lt;}, not{~^!} <br>
Examples: <br>
@AD_Table_ID@=14 | @Language@!GERGER <br>
@PriceLimit@>10 | @PriceList@>@PriceActual@<br>
@Name@>J<br>
Strings may be in single quotes (optional)
','Y','N','N','N','N','N','N','N','N','N','Y','Display Logic',0,TO_TIMESTAMP('2008-07-30 18:11:09','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jul 30, 2008 6:11:16 PM COT
-- [ 1795398 ] Process Parameter: add display logic
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56300 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 30, 2008 6:11:19 PM COT
-- [ 1795398 ] Process Parameter: add display logic
ALTER TABLE AD_Process_Para ADD COLUMN DisplayLogic VARCHAR(2000)
;

-- Jul 30, 2008 6:19:21 PM COT
-- [ 1795398 ] Process Parameter: add display logic
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,56300,56334,0,246,TO_TIMESTAMP('2008-07-30 18:19:20','YYYY-MM-DD HH24:MI:SS'),100,'If the Field is displayed, the result determines if the field is actually displayed',60,null,'D','format := {expression} [{logic} {expression}]<br> 
expression := @{context}@{operand}{value} or @{context}@{operand}{value}<br> 
logic := {|}|{&}<br>
context := any global or window context <br>
value := strings or numbers<br>
logic operators	:= AND or OR with the previous result from left to right <br>
operand := eq{=}, gt{&gt;}, le{&lt;}, not{~^!} <br>
Examples: <br>
@AD_Table_ID@=14 | @Language@!GERGER <br>
@PriceLimit@>10 | @PriceList@>@PriceActual@<br>
@Name@>J<br>
Strings may be in single quotes (optional)
','Y','Y','Y','N','N','N','N','N','Display Logic',260,0,TO_TIMESTAMP('2008-07-30 18:19:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 30, 2008 6:19:22 PM COT
-- [ 1795398 ] Process Parameter: add display logic
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56334 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;
