UPDATE AD_ELEMENT
SET Name = 'Package Version',
PrintName = 'Package Version'
wHERE AD_Element_ID = '50003';

UPDATE AD_COLUMN
SET Name = 'Package Version'
WHERE AD_Column_ID IN (50008, 50047, 50094);

UPDATE AD_FIELD
SET Name = 'Package Version'
WHERE AD_Column_ID IN (50008, 50047, 50094);

UPDATE AD_ELEMENT
SET Name = 'Update System Maintained Application Dictionary'
WHERE AD_Element_ID = 50032;

UPDATE AD_COLUMN
SET Name = 'Update System Maintained Application Dictionary',
DefaultValue = 'Y'
WHERE AD_Column_ID = 50169;

UPDATE AD_FIELD
SET Name = 'Update System Maintained Application Dictionary'
WHERE AD_Column_ID = 50169;

UPDATE AD_ELEMENT
SET Name = 'Package Directory',
Description = 'Package directory, default to AdempiereHome/packages'
WHERE AD_Element_ID = 50033;

UPDATE AD_COLUMN
SET Name = 'Package Directory',
Description = 'Package directory, default to AdempiereHome/packages'
WHERE AD_Column_ID = 50170;

UPDATE AD_FIELD
SET Name = 'Package Directory',
Description = 'Package directory, default to AdempiereHome/packages'
WHERE AD_Column_ID = 50170;

UPDATE AD_ELEMENT
SET Name = 'Package Source',
PrintName = 'Package Source',
Description = 'Fully qualified package source file name'
WHERE AD_Element_ID = 50035;

UPDATE AD_Column
SET Name = 'Package Source',
IsMandatory = 'Y',
AD_Reference_ID = 39,
Description = 'Fully qualified package source file name'
WHERE AD_Column_ID = 50172;

UPDATE AD_Field
SET Name = 'Package Source',
Description = 'Fully qualified package source file name'
WHERE AD_Column_ID = 50172;

UPDATE AD_Element
SET Name = 'Package Source Type',
Description = 'Type of package source - file, ftp, webservice etc'
WHERE AD_Element_ID = 50036;

UPDATE AD_Column
SET Name = 'Package Source Type',
Description = 'Type of package source - file, ftp, webservice etc'
WHERE AD_Column_ID = 50173;

UPDATE AD_Field
SET Name = 'Package Source Type',
Description = 'Type of package source - file, ftp, webservice etc'
WHERE AD_Column_ID = 50173;

COMMIT;
