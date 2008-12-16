-- Column wrong in displaylogic

update ad_field
   set displaylogic = '@IsOwned@=Y & @IsDepreciated@=Y'
 where displaylogic = '@IsOwned@=Y & @IsDepeciated@=Y'
;