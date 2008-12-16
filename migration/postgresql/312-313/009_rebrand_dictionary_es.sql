SET DEFINE OFF;
SET SQLBLANKLINES OFF;

UPDATE ad_element_trl
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_language = 'es_MX' AND ad_element_id = 3093;

UPDATE ad_element_trl
   SET description =
          'El día que comenzó la implementación ó producción (se pone en ejecución) en Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2488;

UPDATE ad_element_trl
   SET description = 'Fecha en que vence el soporte a Adempiere'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2938;

UPDATE ad_element_trl
   SET description =
                  'Número de las unidades de ayuda para la ayuda de Adempiere'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2124;

UPDATE ad_element_trl
   SET description = 'Adempiere Solicita No de Documento '
 WHERE ad_language = 'es_MX' AND ad_element_id = 2891;

UPDATE ad_element_trl
   SET HELP =
          'Patrón de la fecha en la notación Java. Ejemplos: dd.MM.yyyy - dd/MM/yyyy
Si el patrón para el lenguaje no es correcto, porfavor cree una petición en la ayuda de Adempiere con la información correcta.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2673;

UPDATE ad_element_trl
   SET HELP =
          'Si esta seleccionada, los números son impresos con un punto decimal "." - Si no con una coma decimal ",". 
Los mil separadores son el contrario.
Si el patrón para su lenguaje no está correcto, cree por favor una petición en la ayuda de Adempiere con la información correcta.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2674;

UPDATE ad_element_trl
   SET HELP =
          'Patrón de tiempo en notación Java. Ejemplos: "hh:mm:ss aaa z" - "HH:mm:ss"
Si el patrón del lenguaje no es correcto, porfavor cree una petición en la ayuda de Adempiere con la información correcta.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2676;

UPDATE ad_element_trl
   SET HELP =
          'el elemento contable definido por el usuario refiere a una tabla de Adempiere. Esto le permite emplear el contenido de cualquier tabla como una dimensión contable (ej  Actividad de Proyecto). Note que los Elementos de Usuario son opcionales y son llenados desde el contexto del Documento (ej. No Solicitado).'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2877;

UPDATE ad_element_trl
   SET HELP =
          'Un Elemento Contable definido por el Usuario refiere a una Tabla de Adempiere. Esto le permite emplear el contenido de cualquier Tabla como una Dimensión Contable (Ej. Actividad de  Proyecto). Note que los Elementos de Usuario son opcionales y son llenados desde el contexto del Documento (ej. No Solicitado).'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2878;

UPDATE ad_element_trl
   SET HELP =
          'Si Adempiere mantiene el estado de crédito, el estado "Credito Correcto" es movido a "Crédito en Verificación"  empleando este valor como límite. De no definirlo, se empleará el 90%.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2883;

UPDATE ad_element_trl
   SET HELP =
          'Para automatizar el reporte de errores, emvie los errores a Adempiere. Solamente información (seguimiento de pila) es enviada (No datos o Información confidencial). Esto nos ayuda a responder rápidamente. Si usted tiene un contrato de soporte, le informaremos sobre las medidas correctivas. Hasta el momento, esta funcionalidad es experimental.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2886;

UPDATE ad_element_trl
   SET HELP =
          'Las ediciónes de  sistema son creadas para acelerar la resolución de cualquier edición relacionada del sistema (errores potenciales). Si es habilitado, serán reportados automáticamente a Adempiere. No se transfiere información confidencial.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2887;

UPDATE ad_element_trl
   SET HELP =
          'Representación visual del Desempeño mediante colores.  El esquema frrecuentemente tiene 3 niveles (ej. rojo-amarillo-verde).  Adempiere soporta dos niveles (ej. rojo-verde) o cuatro noveles (ej. gris-bronce-plata-oro).  Note que una Meta sin Medida es representada en Blanco.  El  porcentaje puede ser entre 0 y  sin limite  (ej. sobre 100%).'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2903;

UPDATE ad_element_trl
   SET HELP =
          'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).'
 WHERE ad_language = 'es_MX' AND ad_element_id = 3052;

UPDATE ad_element_trl
   SET HELP =
          'If your appplication requires additional jar files, enter them here. The jar files must be located in the $ADEMPIERE_HOME/lib directory.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 3054;

UPDATE ad_element_trl
   SET HELP =
          'Adempiere permite crear automáticamente archivos de los documentos (ej. facturas) o de los informes. Usted define el material archivado con el espectador del archivo'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2672;

UPDATE ad_element_trl
   SET HELP =
          'Si su banco proporciona un número internacional de la cuenta bancaria, incorpórelo aquí detallamente ISO 13616 y http://www.ecbs.org. El número de cuenta tiene la longitud máxima de 22 caracteres (sin espacios). El IBAN se imprime a menudo con un espacio después de 4 caracteres. No incorpore los espacios a Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2664;

UPDATE ad_element_trl
   SET HELP =
          'Usted puede restringir la capacidad de exportar datos de Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2204;

UPDATE ad_element_trl
   SET HELP =
          'Usted puede comprar la ayuda comercial de Adempiere, Inc.
El honorario está por 10 usuarios internos. El número de las unidades de ayuda se exhibe aquí.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2124;

UPDATE ad_element_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_element_id = 1682;

UPDATE ad_element_trl
   SET HELP =
          'Adempiere permite definir condiciones de alerta en el sistema para estar informado.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 2087;

UPDATE ad_element_trl
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_language = 'es_MX' AND ad_element_id = 3093;

UPDATE ad_field_trl
   SET description =
               'Name your Adempiere System installation, e.g. Joe Block, Inc.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5899;

UPDATE ad_field_trl
   SET description =
                  'Número de las unidades de ayuda para la ayuda de Adempiere'
 WHERE ad_language = 'es_MX' AND ad_field_id = 6980;

UPDATE ad_field_trl
   SET description =
          'Los registros nos ayudan a mejorar servicio de la base de usuario de Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8330;

UPDATE ad_field_trl
   SET description =
          'El día que comenzó la implementación ó producción (se pone en ejecución) en Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 10221;

UPDATE ad_field_trl
   SET description = 'Reportar Surtimiento a Adempiere'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12662;

UPDATE ad_field_trl
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13749;

UPDATE ad_field_trl
   SET description = 'Fecha en que vence el soporte a Adempiere'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12870;

UPDATE ad_field_trl
   SET description = 'Register your extension with Adempiere'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13507;

UPDATE ad_field_trl
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13715;

UPDATE ad_field_trl
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13723;

UPDATE ad_field_trl
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13738;

UPDATE ad_field_trl
   SET description = 'Adempiere Solicita No de Documento '
 WHERE ad_language = 'es_MX' AND ad_field_id = 12664;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5121;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5123;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5124;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5125;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5127;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5128;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5129;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5808;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5809;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5810;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5811;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5812;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5813;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5814;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5815;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5816;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5817;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5821;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5823;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5824;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 5827;

UPDATE ad_field_trl
   SET HELP =
          'Adempiere permite definir condiciones de alerta en el sistema para estar informado.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 6858;

UPDATE ad_field_trl
   SET HELP =
          'Adempiere permite definir condiciones de alerta en el sistema para estar informado.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 6866;

UPDATE ad_field_trl
   SET HELP =
          'Usted puede comprar la ayuda comercial de Adempiere, Inc.
El honorario está por 10 usuarios internos. El número de las unidades de ayuda se exhibe aquí.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 6980;

UPDATE ad_field_trl
   SET HELP =
          'Adempiere permite definir condiciones de alerta en el sistema para estar informado.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 7002;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 7519;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 7521;

UPDATE ad_field_trl
   SET HELP =
          'Usted puede restringir la capacidad de exportar datos de Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8314;

UPDATE ad_field_trl
   SET HELP =
          'Usted puede restringir la capacidad de exportar datos de Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8319;

UPDATE ad_field_trl
   SET HELP =
          'Usted puede restringir la capacidad de exportar datos de Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8321;

UPDATE ad_field_trl
   SET HELP =
          'No pondremos los datos a disposición de terceros ni utilizaremos la información para propósitos estadísticos.  
Nos ayudará, si usted permitirá publicar su uso en Adempiere. Nos pondremos en contacto directamente antes de que publiquemos cualquier información.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8330;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8353;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8372;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8858;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8860;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8862;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8863;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 8879;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 10100;

UPDATE ad_field_trl
   SET HELP =
          'Si su banco proporciona un número internacional de la cuenta bancaria, incorpórelo aquí detallamente ISO 13616 y http://www.ecbs.org. El número de cuenta tiene la longitud máxima de 22 caracteres (sin espacios). El IBAN se imprime a menudo con un espacio después de 4 caracteres. No incorpore los espacios a Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 11017;

UPDATE ad_field_trl
   SET HELP =
          'Adempiere permite crear automáticamente archivos de los documentos (ej. facturas) o de los informes. Usted define el material archivado con el espectador del archivo'
 WHERE ad_language = 'es_MX' AND ad_field_id = 11025;

UPDATE ad_field_trl
   SET HELP =
          'Patrón de la fecha en la notación Java. Ejemplos: dd.MM.yyyy - dd/MM/yyyy
Si el patrón para el lenguaje no es correcto, porfavor cree una petición en la ayuda de Adempiere con la información correcta.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 11172;

UPDATE ad_field_trl
   SET HELP =
          'Si esta seleccionada, los números son impresos con un punto decimal "." - Si no con una coma decimal ",". 
Los mil separadores son el contrario.
Si el patrón para su lenguaje no está correcto, cree por favor una petición en la ayuda de Adempiere con la información correcta.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 11173;

UPDATE ad_field_trl
   SET HELP =
          'Patrón de tiempo en notación Java. Ejemplos: "hh:mm:ss aaa z" - "HH:mm:ss"
Si el patrón del lenguaje no es correcto, porfavor cree una petición en la ayuda de Adempiere con la información correcta.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 11175;

UPDATE ad_field_trl
   SET HELP =
          'Patrón de la fecha en la notación Java. Ejemplos: dd.MM.yyyy - dd/MM/yyyy
Si el patrón para el lenguaje no es correcto, porfavor cree una petición en la ayuda de Adempiere con la información correcta.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 11180;

UPDATE ad_field_trl
   SET HELP =
          'Si esta seleccionada, los números son impresos con un punto decimal "." - Si no con una coma decimal ",". 
Los mil separadores son el contrario.
Si el patrón para su lenguaje no está correcto, cree por favor una petición en la ayuda de Adempiere con la información correcta.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 11181;

UPDATE ad_field_trl
   SET HELP =
          'Patrón de tiempo en notación Java. Ejemplos: "hh:mm:ss aaa z" - "HH:mm:ss"
Si el patrón del lenguaje no es correcto, porfavor cree una petición en la ayuda de Adempiere con la información correcta.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 11183;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12535;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12570;

UPDATE ad_field_trl
   SET HELP =
          'el elemento contable definido por el usuario refiere a una tabla de Adempiere. Esto le permite emplear el contenido de cualquier tabla como una dimensión contable (ej  Actividad de Proyecto). Note que los Elementos de Usuario son opcionales y son llenados desde el contexto del Documento (ej. No Solicitado).'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12604;

UPDATE ad_field_trl
   SET HELP =
          'Un Elemento Contable definido por el Usuario refiere a una Tabla de Adempiere. Esto le permite emplear el contenido de cualquier Tabla como una Dimensión Contable (Ej. Actividad de  Proyecto). Note que los Elementos de Usuario son opcionales y son llenados desde el contexto del Documento (ej. No Solicitado).'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12605;

UPDATE ad_field_trl
   SET HELP =
          'el elemento contable definido por el usuario refiere a una tabla de Adempiere. Esto le permite emplear el contenido de cualquier tabla como una dimensión contable (ej  Actividad de Proyecto). Note que los Elementos de Usuario son opcionales y son llenados desde el contexto del Documento (ej. No Solicitado).'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12607;

UPDATE ad_field_trl
   SET HELP =
          'Un Elemento Contable definido por el Usuario refiere a una Tabla de Adempiere. Esto le permite emplear el contenido de cualquier Tabla como una Dimensión Contable (Ej. Actividad de  Proyecto). Note que los Elementos de Usuario son opcionales y son llenados desde el contexto del Documento (ej. No Solicitado).'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12608;

UPDATE ad_field_trl
   SET HELP =
          'el elemento contable definido por el usuario refiere a una tabla de Adempiere. Esto le permite emplear el contenido de cualquier tabla como una dimensión contable (ej  Actividad de Proyecto). Note que los Elementos de Usuario son opcionales y son llenados desde el contexto del Documento (ej. No Solicitado).'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12610;

UPDATE ad_field_trl
   SET HELP =
          'Un Elemento Contable definido por el Usuario refiere a una Tabla de Adempiere. Esto le permite emplear el contenido de cualquier Tabla como una Dimensión Contable (Ej. Actividad de  Proyecto). Note que los Elementos de Usuario son opcionales y son llenados desde el contexto del Documento (ej. No Solicitado).'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12611;

UPDATE ad_field_trl
   SET HELP =
          'Si Adempiere mantiene el estado de crédito, el estado "Credito Correcto" es movido a "Crédito en Verificación"  empleando este valor como límite. De no definirlo, se empleará el 90%.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12648;

UPDATE ad_field_trl
   SET HELP =
          'Para automatizar el reporte de errores, emvie los errores a Adempiere. Solamente información (seguimiento de pila) es enviada (No datos o Información confidencial). Esto nos ayuda a responder rápidamente. Si usted tiene un contrato de soporte, le informaremos sobre las medidas correctivas. Hasta el momento, esta funcionalidad es experimental.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12653;

UPDATE ad_field_trl
   SET HELP =
          'Las ediciónes de  sistema son creadas para acelerar la resolución de cualquier edición relacionada del sistema (errores potenciales). Si es habilitado, serán reportados automáticamente a Adempiere. No se transfiere información confidencial.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12673;

UPDATE ad_field_trl
   SET HELP =
          'Representación visual del Desempeño mediante colores.  El esquema frrecuentemente tiene 3 niveles (ej. rojo-amarillo-verde).  Adempiere soporta dos niveles (ej. rojo-verde) o cuatro noveles (ej. gris-bronce-plata-oro).  Note que una Meta sin Medida es representada en Blanco.  El  porcentaje puede ser entre 0 y  sin limite  (ej. sobre 100%).'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12767;

UPDATE ad_field_trl
   SET HELP =
          'Representación visual del Desempeño mediante colores.  El esquema frrecuentemente tiene 3 niveles (ej. rojo-amarillo-verde).  Adempiere soporta dos niveles (ej. rojo-verde) o cuatro noveles (ej. gris-bronce-plata-oro).  Note que una Meta sin Medida es representada en Blanco.  El  porcentaje puede ser entre 0 y  sin limite  (ej. sobre 100%).'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12774;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12821;

UPDATE ad_field_trl
   SET HELP =
          'El proceso se conecta al servidor de  Servicios de Soporte Adempiere y vsñifs el contrato de soporte. Para contratar soporte por favor ingrese a www.e-evolution.com.mx
'
 WHERE ad_language = 'es_MX' AND ad_field_id = 12871;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13020;

UPDATE ad_field_trl
   SET HELP =
          'If your appplication requires additional jar files, enter them here. The jar files must be located in the $ADEMPIERE_HOME/lib directory.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13498;

UPDATE ad_field_trl
   SET HELP =
          'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13502;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13503;

UPDATE ad_field_trl
   SET HELP =
          'You can register the four character extension with Adempiere. This makes sure that your extension can be automatically distributed and implemented.  You will also be able to certify extensions.  Contact Adempiere for details.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13507;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13513;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13575;

UPDATE ad_field_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13597;

UPDATE ad_field_trl
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13715;

UPDATE ad_field_trl
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13723;

UPDATE ad_field_trl
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13738;

UPDATE ad_field_trl
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_language = 'es_MX' AND ad_field_id = 13749;

UPDATE ad_menu_trl
   SET description = 'Flujo de Trabajo Adempiere'
 WHERE ad_language = 'es_MX' AND ad_menu_id = 501;

UPDATE ad_menu_trl
   SET description = 'Mantenimiento del Servidor Adempiere'
 WHERE ad_language = 'es_MX' AND ad_menu_id = 456;

UPDATE ad_menu_trl
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_language = 'es_MX' AND ad_menu_id = 594;

UPDATE ad_menu_trl
   SET description = 'Alterta Adempiere'
 WHERE ad_language = 'es_MX' AND ad_menu_id = 379;

UPDATE ad_message_trl
   SET msgtext =
          'Descargados  {0} for {1}

Versión = {2} - Lot = {3} - SerNo = {4}
Guarantee Date = {5,date,short}

Thank you for using Adempiere Customer Asset Management
'
 WHERE ad_language = 'es_MX' AND ad_message_id = 747;

UPDATE ad_message_trl
   SET msgtext = '© Jorg Janke - Adempiere; Inc. 1999-2002'
 WHERE ad_language = 'es_MX' AND ad_message_id = 125;

UPDATE ad_message_trl
   SET msgtip =
          'Cree el archivo del seguimiento en el directorio de Adempiere ó en el directorio home del usuario'
 WHERE ad_language = 'es_MX' AND ad_message_id = 813;

UPDATE ad_message_trl
   SET msgtip = 'Ventana-Metal-Adempiere'
 WHERE ad_language = 'es_MX' AND ad_message_id = 570;

UPDATE ad_message_trl
   SET msgtip = 'http://www.adempiere.org'
 WHERE ad_language = 'es_MX' AND ad_message_id = 125;

UPDATE ad_message_trl
   SET msgtip =
               'Name your Adempiere System installation, e.g. Joe Block, Inc.'
 WHERE ad_language = 'es_MX' AND ad_message_id = 867;

UPDATE ad_message_trl
   SET msgtip =
          'Sign up for Adempiere Support - also supports the product development'
 WHERE ad_language = 'es_MX' AND ad_message_id = 868;

UPDATE ad_process_para_trl
   SET HELP =
          'Los tipos de entidad "Diccionario"; "Adempiere"; y "Aplicación" podrían ser automáticamente sincronizados y las personalizaciones eliminadas ó sobreescritas'
 WHERE ad_language = 'es_MX' AND ad_process_para_id = 630;

UPDATE ad_process_trl
   SET description =
          'Los registros nos ayudan a mejorar servicio de la base de usuario de Adempiere.'
 WHERE ad_language = 'es_MX' AND ad_process_id = 250;

UPDATE ad_process_trl
   SET description = 'Reportar Surtimiento a Adempiere'
 WHERE ad_language = 'es_MX' AND ad_process_id = 339;

UPDATE ad_process_trl
   SET description = 'Register your extension with Adempiere'
 WHERE ad_language = 'es_MX' AND ad_process_id = 348;

UPDATE ad_process_trl
   SET HELP =
          'Para aumentar funcionamiento, Adempiere reasigna los datos usados. Este proceso despeja el cache local.'
 WHERE ad_language = 'es_MX' AND ad_process_id = 205;

UPDATE ad_process_trl
   SET HELP =
          'No pondremos los datos a disposición de terceros ni utilizaremos la información para propósitos estadísticos.  
Nos ayudará, si usted permitirá publicar su uso en Adempiere. Nos pondremos en contacto directamente antes de que publiquemos cualquier información.'
 WHERE ad_language = 'es_MX' AND ad_process_id = 250;

UPDATE ad_process_trl
   SET HELP =
          'El proceso se conecta al servidor de  Servicios de Soporte Adempiere y vsñifs el contrato de soporte. Para contratar soporte por favor ingrese a www.e-evolution.com.mx
'
 WHERE ad_language = 'es_MX' AND ad_process_id = 342;

UPDATE ad_process_trl
   SET HELP =
          'You can register the four character extension with Adempiere. This makes sure that your extension can be automatically distributed and implemented.  You will also be able to certify extensions.  Contact Adempiere for details.'
 WHERE ad_language = 'es_MX' AND ad_process_id = 348;

UPDATE ad_ref_list_trl
   SET description = 'Aplicación Adempiere (sinchronizada)'
 WHERE ad_language = 'es_MX' AND ad_ref_list_id = 488;

UPDATE ad_ref_list_trl
   SET NAME = 'Adempiere'
 WHERE ad_language = 'es_MX' AND ad_ref_list_id = 488;

UPDATE ad_tab_trl
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_language = 'es_MX' AND ad_tab_id = 849;

UPDATE ad_tab_trl
   SET description = 'Adempiere Alerta'
 WHERE ad_language = 'es_MX' AND ad_tab_id = 504;

UPDATE ad_tab_trl
   SET HELP =
          'Representación Visual del desempeño mediante colores. Frecuentemente el esquema tiene tres niveles (ej. rojo - amarillo - verde). Adempiere soporta dos niveles (ej. rojo - verde) o cuatro niveles (ej. gris - bronce - plata - oro). Note que las medidasd sin límite son representadas en Blanco. Los porcentajes pueden ser entre 0 y sin límite (ej. superior a 100%).'
 WHERE ad_language = 'es_MX' AND ad_tab_id = 779;

UPDATE ad_tab_trl
   SET HELP =
          'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).'
 WHERE ad_language = 'es_MX' AND ad_tab_id = 831;

UPDATE ad_tab_trl
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_language = 'es_MX' AND ad_tab_id = 849;

UPDATE ad_tab_trl
   SET HELP =
          'Adempiere Alerta permíte definir condiciones de sistema que usted desea ser alertado'
 WHERE ad_language = 'es_MX' AND ad_tab_id = 504;

UPDATE ad_tab_trl
   SET HELP =
          'Antes de importar, Adempiere comprueba la unidad de la medida (por default si no es fijado), La categoria del producto (por default si no es fijado), El socio de negocio, la moneda (Por default la moneda de la contabilidad si no es fijada), El tipo de producto (solamente artículos y servicios), los únicos de UPC, llave y existencia de el No. de producto.<br>
Adempiere intenta para el mapa de los productos existentes, si el UPC, La llave y el vendedor del producto no siguen (en esta secuencia). Si el expediente importado podría ser emparejado, los valores del campo del producto serán sobreescritos solamente, si el campo correspondiente de la importación se define explícitamente. Ejemplo: la categoría del producto será sobreescrita solamente si está fijada explícitamente en la importación.'
 WHERE ad_language = 'es_MX' AND ad_tab_id = 442;

UPDATE ad_tab_trl
   SET HELP =
          'Las versiones de sistema son creadas para apresurar la resolución de cualquier versión de sistema relacionaddo (errores potenciales). Si habilitó, automáticaamente son reportados a Adempiere. No sé transfiere información confidencial.'
 WHERE ad_language = 'es_MX' AND ad_tab_id = 777;

UPDATE ad_wf_node_trl
   SET HELP =
          'Representación visual del desempeño mediante colores. El esquema frecuentemente tiene tres niveles (ej. rojo-amarillo-verde). Adempiere sporta dos niveles (ej. rojo-verde) o cuatro niveles (ej. gris-bronce-plata-oro). Note que las médidas sin límite son representadas en blanco. Los porcentajes pueden ser entre cero y sin límite   (ej. superior a 100%).'
 WHERE ad_language = 'es_MX' AND ad_wf_node_id = 147;

UPDATE ad_window_trl
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_language = 'es_MX' AND ad_window_id = 389;

UPDATE ad_window_trl
   SET description = 'Alterta Adempiere'
 WHERE ad_language = 'es_MX' AND ad_window_id = 276;

UPDATE ad_window_trl
   SET HELP =
          'Representación visual del desempeño mediante colores. El esquema frecuentemente tiene tres niveles (ej. rojo-amarillo-verde). Adempiere sporta dos niveles (ej. rojo-verde) o cuatro niveles (ej. gris-bronce-plata-oro). Note que las médidas sin límite son representadas en blanco. Los porcentajes pueden ser entre cero y sin límite   (ej. superior a 100%).'
 WHERE ad_language = 'es_MX' AND ad_window_id = 364;

UPDATE ad_window_trl
   SET HELP =
          'Las versiones de sistema son creados para acelerar la resolución de cualquier versión de sistema relacionado (errores potenciales). Si habilitó, son automáticamente reportados a Adempiere. No se transfieren datos o información confidencial.'
 WHERE ad_language = 'es_MX' AND ad_window_id = 363;

UPDATE ad_window_trl
   SET HELP =
          'Las alarmas de Adempiere le permiten definen condiciones de sistema que usted desea ser alertado'
 WHERE ad_language = 'es_MX' AND ad_window_id = 276;

UPDATE ad_window_trl
   SET HELP =
          'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).'
 WHERE ad_language = 'es_MX' AND ad_window_id = 381;

UPDATE ad_window_trl
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_language = 'es_MX' AND ad_window_id = 389;

UPDATE cm_container_trl
   SET meta_keywords = 'GardenWorld, Adempiere'
 WHERE ad_language = 'es_MX' AND cm_container_id = 101;

UPDATE cm_cstage_trl
   SET meta_keywords = 'GardenWorld, Adempiere'
 WHERE ad_language = 'es_MX' AND cm_cstage_id = 101;

COMMIT ;
