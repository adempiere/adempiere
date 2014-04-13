SET DEFINE OFF
SET SQLBLANKLINES ON
-- Sep 3, 2012 12:54:04 PM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64353,139,0,19,53223,'AD_Val_Rule_ID',TO_DATE('2012-09-03 12:54:03','YYYY-MM-DD HH24:MI:SS'),100,'Dynamic Validation Rule','EE07',22,'These rules define how an entry is determined to valid. You can use variables for dynamic (context sensitive) validation.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Dynamic Validation',0,TO_DATE('2012-09-03 12:54:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 3, 2012 12:54:04 PM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64353 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 3, 2012 12:54:10 PM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD AD_Val_Rule_ID NUMBER(10) DEFAULT NULL 
;

-- Sep 3, 2012 12:54:44 PM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64354,272,0,10,53223,'DefaultValue',TO_DATE('2012-09-03 12:54:44','YYYY-MM-DD HH24:MI:SS'),100,'Default value hierarchy, separated by ;','EE07',2000,'The defaults are evaluated in the order of definition, the first not null value becomes the default value of the column. The values are separated by comma or semicolon. a) Literals:. ''Text'' or 123 b) Variables - in format @Variable@ - Login e.g. #Date, #AD_Org_ID, #AD_Client_ID - Accounting Schema: e.g. $C_AcctSchema_ID, $C_Calendar_ID - Global defaults: e.g. DateFormat - Window values (all Picks, CheckBoxes, RadioButtons, and DateDoc/DateAcct) c) SQL code with the tag: @SQL=SELECT something AS DefaultValue FROM ... The SQL statement can contain variables.  There can be no other value other than the SQL statement. The default is only evaluated, if no user preference is defined.  Default definitions are ignored for record columns as Key, Parent, Client as well as Buttons.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Default Logic',0,TO_DATE('2012-09-03 12:54:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 3, 2012 12:54:44 PM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64354 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 3, 2012 12:54:46 PM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD DefaultValue NVARCHAR2(2000) DEFAULT NULL 
;

-- Sep 3, 2012 12:54:57 PM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64355,1529,0,10,53223,'DefaultValue2',TO_DATE('2012-09-03 12:54:56','YYYY-MM-DD HH24:MI:SS'),100,'Default value hierarchy, separated by ;','EE07',2000,'The defaults are evaluated in the order of definition, the first not null value becomes the default value of the column. The values are separated by comma or semicolon. a) Literals:. ''Text'' or 123 b) Variables - in format @Variable@ - Login e.g. #Date, #AD_Org_ID, #AD_Client_ID - Accounting Schema: e.g. $C_AcctSchema_ID, $C_Calendar_ID - Global defaults: e.g. DateFormat - Window values (all Picks, CheckBoxes, RadioButtons, and DateDoc/DateAcct) c) SQL code with the tag: @SQL=SELECT something AS DefaultValue FROM ... The SQL statement can contain variables.  There can be no other value other than the SQL statement. The default is only evaluated, if no user preference is defined.  Default definitions are ignored for record columns as Key, Parent, Client as well as Buttons.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Default Logic 2',0,TO_DATE('2012-09-03 12:54:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 3, 2012 12:54:57 PM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64355 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 3, 2012 12:54:59 PM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD DefaultValue2 NVARCHAR2(2000) DEFAULT NULL 
;

-- Sep 3, 2012 12:55:31 PM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64356,283,0,14,53223,'DisplayLogic',TO_DATE('2012-09-03 12:55:29','YYYY-MM-DD HH24:MI:SS'),100,'If the Field is displayed, the result determines if the field is actually displayed','EE07',2000,'format := {expression} [{logic} {expression}]<br> 
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
Strings may be in single quotes (optional)','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Display Logic',0,TO_DATE('2012-09-03 12:55:29','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 3, 2012 12:55:31 PM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64356 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 3, 2012 12:55:33 PM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD DisplayLogic NVARCHAR2(2000) DEFAULT NULL 
;

-- Sep 3, 2012 12:56:21 PM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64357,302,0,11,53223,'FieldLength',TO_DATE('2012-09-03 12:56:20','YYYY-MM-DD HH24:MI:SS'),100,'Length of the column in the database','EE07',22,'The Length indicates the length of a column as defined in the database.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Length',0,TO_DATE('2012-09-03 12:56:20','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 3, 2012 12:56:21 PM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64357 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 3, 2012 12:56:22 PM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD FieldLength NUMBER(10) DEFAULT NULL 
;

-- Sep 3, 2012 12:56:55 PM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64358,1663,0,14,53223,'ReadOnlyLogic',TO_DATE('2012-09-03 12:56:54','YYYY-MM-DD HH24:MI:SS'),100,'Logic to determine if field is read only (applies only when field is read-write)','EE07',2000,'format := {expression} [{logic} {expression}]<br> 
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
Strings may be in single quotes (optional)','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Read Only Logic',0,TO_DATE('2012-09-03 12:56:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 3, 2012 12:56:55 PM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64358 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 3, 2012 12:56:56 PM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD ReadOnlyLogic NVARCHAR2(2000) DEFAULT NULL 
;

-- Sep 3, 2012 12:57:15 PM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64359,616,0,10,53223,'VFormat',TO_DATE('2012-09-03 12:57:15','YYYY-MM-DD HH24:MI:SS'),100,'Format of the value; Can contain fixed format elements, Variables: "_lLoOaAcCa09"','EE07',60,'<B>Validation elements:</B>
 	(Space) any character
_	Space (fixed character)
l	any Letter a..Z NO space
L	any Letter a..Z NO space converted to upper case
o	any Letter a..Z or space
O	any Letter a..Z or space converted to upper case
a	any Letters & Digits NO space
A	any Letters & Digits NO space converted to upper case
c	any Letters & Digits or space
C	any Letters & Digits or space converted to upper case
0	Digits 0..9 NO space
9	Digits 0..9 or space

Example of format "(000)_000-0000"','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Value Format',0,TO_DATE('2012-09-03 12:57:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 3, 2012 12:57:15 PM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64359 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 3, 2012 12:57:17 PM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD VFormat NVARCHAR2(60) DEFAULT NULL 
;

-- Sep 3, 2012 12:57:39 PM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64360,1059,0,10,53223,'ValueMax',TO_DATE('2012-09-03 12:57:38','YYYY-MM-DD HH24:MI:SS'),100,'Maximum Value for a field','EE07',20,'The Maximum Value indicates the highest allowable value for a field','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Max. Value',0,TO_DATE('2012-09-03 12:57:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 3, 2012 12:57:39 PM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64360 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 3, 2012 12:57:40 PM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD ValueMax NVARCHAR2(20) DEFAULT NULL 
;

-- Sep 3, 2012 12:57:57 PM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64361,1060,0,10,53223,'ValueMin',TO_DATE('2012-09-03 12:57:57','YYYY-MM-DD HH24:MI:SS'),100,'Minimum Value for a field','EE07',20,'The Minimum Value indicates the lowest  allowable value for a field.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Min. Value',0,TO_DATE('2012-09-03 12:57:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 3, 2012 12:57:57 PM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64361 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 3, 2012 12:57:59 PM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD ValueMin NVARCHAR2(20) DEFAULT NULL 
;

-- Sep 3, 2012 12:58:48 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64354,65292,0,53247,TO_DATE('2012-09-03 12:58:47','YYYY-MM-DD HH24:MI:SS'),100,'Default value hierarchy, separated by ;',2000,'EE07','The defaults are evaluated in the order of definition, the first not null value becomes the default value of the column. The values are separated by comma or semicolon. a) Literals:. ''Text'' or 123 b) Variables - in format @Variable@ - Login e.g. #Date, #AD_Org_ID, #AD_Client_ID - Accounting Schema: e.g. $C_AcctSchema_ID, $C_Calendar_ID - Global defaults: e.g. DateFormat - Window values (all Picks, CheckBoxes, RadioButtons, and DateDoc/DateAcct) c) SQL code with the tag: @SQL=SELECT something AS DefaultValue FROM ... The SQL statement can contain variables.  There can be no other value other than the SQL statement. The default is only evaluated, if no user preference is defined.  Default definitions are ignored for record columns as Key, Parent, Client as well as Buttons.','Y','Y','Y','N','N','N','N','N','Default Logic',TO_DATE('2012-09-03 12:58:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:48 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65292 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:58:49 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64355,65293,0,53247,TO_DATE('2012-09-03 12:58:48','YYYY-MM-DD HH24:MI:SS'),100,'Default value hierarchy, separated by ;',2000,'EE07','The defaults are evaluated in the order of definition, the first not null value becomes the default value of the column. The values are separated by comma or semicolon. a) Literals:. ''Text'' or 123 b) Variables - in format @Variable@ - Login e.g. #Date, #AD_Org_ID, #AD_Client_ID - Accounting Schema: e.g. $C_AcctSchema_ID, $C_Calendar_ID - Global defaults: e.g. DateFormat - Window values (all Picks, CheckBoxes, RadioButtons, and DateDoc/DateAcct) c) SQL code with the tag: @SQL=SELECT something AS DefaultValue FROM ... The SQL statement can contain variables.  There can be no other value other than the SQL statement. The default is only evaluated, if no user preference is defined.  Default definitions are ignored for record columns as Key, Parent, Client as well as Buttons.','Y','Y','Y','N','N','N','N','N','Default Logic 2',TO_DATE('2012-09-03 12:58:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:49 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65293 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:58:51 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64356,65294,0,53247,TO_DATE('2012-09-03 12:58:49','YYYY-MM-DD HH24:MI:SS'),100,'If the Field is displayed, the result determines if the field is actually displayed',2000,'EE07','format := {expression} [{logic} {expression}]<br> 
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
Strings may be in single quotes (optional)','Y','Y','Y','N','N','N','N','N','Display Logic',TO_DATE('2012-09-03 12:58:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:51 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65294 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:58:51 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64353,65295,0,53247,TO_DATE('2012-09-03 12:58:51','YYYY-MM-DD HH24:MI:SS'),100,'Dynamic Validation Rule',22,'EE07','These rules define how an entry is determined to valid. You can use variables for dynamic (context sensitive) validation.','Y','Y','Y','N','N','N','N','N','Dynamic Validation',TO_DATE('2012-09-03 12:58:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:51 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65295 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:58:52 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64357,65296,0,53247,TO_DATE('2012-09-03 12:58:51','YYYY-MM-DD HH24:MI:SS'),100,'Length of the column in the database',22,'EE07','The Length indicates the length of a column as defined in the database.','Y','Y','Y','N','N','N','N','N','Length',TO_DATE('2012-09-03 12:58:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:52 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65296 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:58:53 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64360,65297,0,53247,TO_DATE('2012-09-03 12:58:52','YYYY-MM-DD HH24:MI:SS'),100,'Maximum Value for a field',20,'EE07','The Maximum Value indicates the highest allowable value for a field','Y','Y','Y','N','N','N','N','N','Max. Value',TO_DATE('2012-09-03 12:58:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:53 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65297 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:58:55 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64361,65298,0,53247,TO_DATE('2012-09-03 12:58:53','YYYY-MM-DD HH24:MI:SS'),100,'Minimum Value for a field',20,'EE07','The Minimum Value indicates the lowest  allowable value for a field.','Y','Y','Y','N','N','N','N','N','Min. Value',TO_DATE('2012-09-03 12:58:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:55 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65298 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:58:56 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64222,65299,0,53247,TO_DATE('2012-09-03 12:58:55','YYYY-MM-DD HH24:MI:SS'),100,'Include in sort order',1,'EE07','The records are ordered by the value of this column. If a column is used for grouping, it needs to be included in the sort order as well.','Y','Y','Y','N','N','N','N','N','Order by',TO_DATE('2012-09-03 12:58:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:56 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65299 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:58:58 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64358,65300,0,53247,TO_DATE('2012-09-03 12:58:56','YYYY-MM-DD HH24:MI:SS'),100,'Logic to determine if field is read only (applies only when field is read-write)',2000,'EE07','format := {expression} [{logic} {expression}]<br> 
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
Strings may be in single quotes (optional)','Y','Y','Y','N','N','N','N','N','Read Only Logic',TO_DATE('2012-09-03 12:58:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:58 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65300 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:58:59 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64221,65301,0,53247,TO_DATE('2012-09-03 12:58:58','YYYY-MM-DD HH24:MI:SS'),100,'Determines in what order the records are displayed',22,'EE07','The Record Sort No indicates the ascending sort sequence of the records. If the number is negative, the records are sorted descending. 
Example: A tab with C_DocType_ID (1), DocumentNo (-2) will be sorted ascending by document type and descending by document number (SQL: ORDER BY C_DocType, DocumentNo DESC)','Y','Y','Y','N','N','N','N','N','Record Sort No',TO_DATE('2012-09-03 12:58:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:58:59 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65301 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 12:59:00 PM CDT
-- Smart Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64359,65302,0,53247,TO_DATE('2012-09-03 12:58:59','YYYY-MM-DD HH24:MI:SS'),100,'Format of the value; Can contain fixed format elements, Variables: "_lLoOaAcCa09"',60,'EE07','<B>Validation elements:</B>
 	(Space) any character
_	Space (fixed character)
l	any Letter a..Z NO space
L	any Letter a..Z NO space converted to upper case
o	any Letter a..Z or space
O	any Letter a..Z or space converted to upper case
a	any Letters & Digits NO space
A	any Letters & Digits NO space converted to upper case
c	any Letters & Digits or space
C	any Letters & Digits or space converted to upper case
0	Digits 0..9 NO space
9	Digits 0..9 or space

Example of format "(000)_000-0000"','Y','Y','Y','N','N','N','N','N','Value Format',TO_DATE('2012-09-03 12:58:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 3, 2012 12:59:00 PM CDT
-- Smart Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65302 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=65302
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=65295
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=65296
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=57434
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=57435
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=57436
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=57437
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=57438
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=64654
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=57439
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=65294
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=65300
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=57440
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=65292
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=65293
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=65298
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=65297
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=64393
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=64394
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=65299
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=65301
;

-- Sep 3, 2012 1:04:29 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=57441
;

-- Sep 3, 2012 1:06:09 PM CDT
-- Smart Browse
UPDATE AD_Field SET DisplayLength=20,Updated=TO_DATE('2012-09-03 13:06:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65302
;

-- Sep 3, 2012 1:06:22 PM CDT
-- Smart Browse
UPDATE AD_Field SET DisplayLength=14, IsSameLine='Y',Updated=TO_DATE('2012-09-03 13:06:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65295
;

-- Sep 3, 2012 1:07:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=65295
;

-- Sep 3, 2012 1:07:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=65302
;

-- Sep 3, 2012 1:08:39 PM CDT
-- Smart Browse
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2012-09-03 13:08:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57433
;

-- Sep 3, 2012 1:09:19 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=65295
;

-- Sep 3, 2012 1:09:19 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=57433
;

-- Sep 3, 2012 1:10:13 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57434
;

-- Sep 3, 2012 1:10:13 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=65296
;

-- Sep 3, 2012 1:10:22 PM CDT
-- Smart Browse
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-09-03 13:10:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65296
;

-- Sep 3, 2012 1:13:41 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57435
;

-- Sep 3, 2012 1:13:41 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=57434
;

-- Sep 3, 2012 1:13:41 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=65296
;

-- Sep 3, 2012 1:16:57 PM CDT
-- Smart Browse
UPDATE AD_Field SET DisplayLength=60,Updated=TO_DATE('2012-09-03 13:16:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65294
;

-- Sep 3, 2012 1:17:06 PM CDT
-- Smart Browse
UPDATE AD_Field SET DisplayLength=60,Updated=TO_DATE('2012-09-03 13:17:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65300
;

-- Sep 3, 2012 1:17:59 PM CDT
-- Smart Browse
UPDATE AD_Field SET DisplayLogic='@IsQueryCriteria@=''Y''',Updated=TO_DATE('2012-09-03 13:17:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65294
;

-- Sep 3, 2012 1:18:05 PM CDT
-- Smart Browse
UPDATE AD_Field SET DisplayLogic='@IsQueryCriteria@=''Y''',Updated=TO_DATE('2012-09-03 13:18:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65300
;

-- Sep 3, 2012 1:18:25 PM CDT
-- Smart Browse
UPDATE AD_Field SET DisplayLogic='@IsQueryCriteria@=''Y''',Updated=TO_DATE('2012-09-03 13:18:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65292
;

-- Sep 3, 2012 1:19:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET DisplayLength=26,Updated=TO_DATE('2012-09-03 13:19:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65292
;

-- Sep 3, 2012 1:20:09 PM CDT
-- Smart Browse
UPDATE AD_Field SET DisplayLength=26, DisplayLogic='@IsQueryCriteria@=''Y'' & @IsRange@=''Y''', IsSameLine='Y',Updated=TO_DATE('2012-09-03 13:20:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65293
;

-- Sep 3, 2012 1:21:37 PM CDT
-- Smart Browse
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-09-03 13:21:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65297
;

-- Sep 3, 2012 1:22:03 PM CDT
-- Smart Browse
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-09-03 13:22:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65301
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=65299
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=65301
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=57436
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=57437
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=57438
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=64654
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=57439
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=65294
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=65300
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=57440
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=65292
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=65293
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=65298
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=65297
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=64393
;

-- Sep 3, 2012 1:23:30 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=64394
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57441
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=57435
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=57430
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=57431
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=57432
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=65295
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57433
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=65302
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=57434
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=65296
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=65299
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=65301
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=57436
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=57437
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=57438
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=64654
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=57439
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=65294
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=65300
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=57440
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=65292
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=65293
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=65298
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=65297
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=64393
;

-- Sep 3, 2012 1:28:17 PM CDT
-- Smart Browse
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=64394
;

