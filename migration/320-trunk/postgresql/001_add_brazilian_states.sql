--Enable Brazil in country Table
UPDATE c_country SET hasregion = 'Y', regionname = 'Estado' WHERE c_country_id = 139;

COMMIT;

--Add Brazilian States
INSERT INTO c_region VALUES (441, 0, 0, 'Y', '2007-04-30 16:40:42', 0, '2007-04-30 16:40:42', 0, 'AC', 'Acre', 139, 'N');
INSERT INTO c_region VALUES (442, 0, 0, 'Y', '2007-04-30 16:40:57', 0, '2007-04-30 16:40:57', 0, 'AL', 'Alagoas', 139, 'N');
INSERT INTO c_region VALUES (443, 0, 0, 'Y', '2007-04-30 16:41:06', 0, '2007-04-30 16:41:06', 0, 'AP', 'Amapá', 139, 'N');
INSERT INTO c_region VALUES (444, 0, 0, 'Y', '2007-04-30 16:41:19', 0, '2007-04-30 16:41:19', 0, 'AM', 'Amazonas', 139, 'N');
INSERT INTO c_region VALUES (445, 0, 0, 'Y', '2007-04-30 16:41:29', 0, '2007-04-30 16:41:29', 0, 'BA', 'Bahia', 139, 'N');
INSERT INTO c_region VALUES (446, 0, 0, 'Y', '2007-04-30 16:41:49', 0, '2007-04-30 16:41:49', 0, 'CE', 'Ceará°', 139, 'N');
INSERT INTO c_region VALUES (447, 0, 0, 'Y', '2007-04-30 16:42:04', 0, '2007-04-30 16:42:04', 0, 'DF', 'Distrito Federal', 139, 'N');
INSERT INTO c_region VALUES (448, 0, 0, 'Y', '2007-04-30 16:42:19', 0, '2007-04-30 16:42:19', 0, 'ES', 'Espírito Santo', 139, 'N');
INSERT INTO c_region VALUES (449, 0, 0, 'Y', '2007-04-30 16:42:34', 0, '2007-04-30 16:42:34', 0, 'GO', 'Goiás', 139, 'N');
INSERT INTO c_region VALUES (450, 0, 0, 'Y', '2007-04-30 16:42:40', 0, '2007-04-30 16:42:40', 0, 'MA', 'Maranhão', 139, 'N');
INSERT INTO c_region VALUES (451, 0, 0, 'Y', '2007-04-30 16:42:49', 0, '2007-04-30 16:42:49', 0, 'MT', 'Mato Grosso', 139, 'N');
INSERT INTO c_region VALUES (452, 0, 0, 'Y', '2007-04-30 16:43:00', 0, '2007-04-30 16:43:00', 0, 'MS', 'Mato Grosso do Sul', 139, 'N');
INSERT INTO c_region VALUES (453, 0, 0, 'Y', '2007-04-30 16:43:11', 0, '2007-04-30 16:43:11', 0, 'MG', 'Minas Gerais', 139, 'N');
INSERT INTO c_region VALUES (454, 0, 0, 'Y', '2007-04-30 16:43:21', 0, '2007-04-30 16:43:21', 0, 'PA', 'Pará', 139, 'N');
INSERT INTO c_region VALUES (455, 0, 0, 'Y', '2007-04-30 16:43:31', 0, '2007-04-30 16:43:31', 0, 'PB', 'Paraíba', 139, 'N');
INSERT INTO c_region VALUES (456, 0, 0, 'Y', '2007-04-30 16:43:40', 0, '2007-04-30 16:43:40', 0, 'PR', 'Paraná', 139, 'N');
INSERT INTO c_region VALUES (457, 0, 0, 'Y', '2007-04-30 16:43:53', 0, '2007-04-30 16:43:53', 0, 'PE', 'Pernambuco', 139, 'N');
INSERT INTO c_region VALUES (458, 0, 0, 'Y', '2007-04-30 16:44:03', 0, '2007-04-30 16:44:03', 0, 'PI', 'Piauí', 139, 'N');
INSERT INTO c_region VALUES (459, 0, 0, 'Y', '2007-04-30 16:44:30', 0, '2007-04-30 16:44:30', 0, 'RJ', 'Rio de Janeiro', 139, 'N');
INSERT INTO c_region VALUES (460, 0, 0, 'Y', '2007-04-30 16:44:43', 0, '2007-04-30 16:44:43', 0, 'RN', 'Rio Grande do Norte', 139, 'N');
INSERT INTO c_region VALUES (461, 0, 0, 'Y', '2007-04-30 16:44:59', 0, '2007-04-30 16:44:59', 0, 'RS', 'Rio Grande do Sul', 139, 'N');
INSERT INTO c_region VALUES (462, 0, 0, 'Y', '2007-04-30 16:45:07', 0, '2007-04-30 16:45:07', 0, 'RO', 'Rondônia', 139, 'N');
INSERT INTO c_region VALUES (463, 0, 0, 'Y', '2007-04-30 16:45:20', 0, '2007-04-30 16:45:20', 0, 'RR', 'Roraima', 139, 'N');
INSERT INTO c_region VALUES (464, 0, 0, 'Y', '2007-04-30 16:45:30', 0, '2007-04-30 16:45:30', 0, 'SC', 'Santa Catarina', 139, 'N');
INSERT INTO c_region VALUES (465, 0, 0, 'Y', '2007-04-30 16:45:40', 0, '2007-04-30 16:45:40', 0, 'SP', 'Sõo Paulo', 139, 'N');
INSERT INTO c_region VALUES (466, 0, 0, 'Y', '2007-04-30 16:45:53', 0, '2007-04-30 16:45:53', 0, 'SE', 'Sergipe', 139, 'N');
INSERT INTO c_region VALUES (467, 0, 0, 'Y', '2007-04-30 16:46:03', 0, '2007-04-30 16:46:03', 0, 'TO', 'Tocantins', 139, 'N');

COMMIT;

UPDATE AD_SEQUENCE
   SET currentnextsys = (SELECT MAX (c_region_id) + 1
                           FROM C_REGION
                          WHERE c_region_id < 1000000)
 WHERE NAME = 'C_Region';

COMMIT;
