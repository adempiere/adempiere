--Enable Iran in country Table
BEGIN TRANSACTION;

UPDATE c_country SET hasregion = 'Y', regionname = 'Iran' WHERE c_country_id = 210;

COMMIT;

--Add Iran States
BEGIN TRANSACTION;

INSERT INTO c_region VALUES (468, 0, 0, 'Y', '2007-04-30 16:40:42', 0, '2007-04-30 16:40:42', 0, 'Ardabil', 'Ardabil', 210, 'N');
INSERT INTO c_region VALUES (469, 0, 0, 'Y', '2007-04-30 16:40:57', 0, '2007-04-30 16:40:57', 0, 'West Azarbāijān', 'West Azārbāijān', 210, 'N');
INSERT INTO c_region VALUES (470, 0, 0, 'Y', '2007-04-30 16:41:06', 0, '2007-04-30 16:41:06', 0, 'East Azarbāijān', 'East Azarbāijān', 210, 'N');
INSERT INTO c_region VALUES (471, 0, 0, 'Y', '2007-04-30 16:41:19', 0, '2007-04-30 16:41:19', 0, 'Bushehr', 'Bushehr', 210, 'N');
INSERT INTO c_region VALUES (472, 0, 0, 'Y', '2007-04-30 16:41:29', 0, '2007-04-30 16:41:29', 0, 'Chāhārmahāl and Bakhtiāri', 'Chāhārmahāl and Bakhtiāri', 210, 'N');
INSERT INTO c_region VALUES (473, 0, 0, 'Y', '2007-04-30 16:41:49', 0, '2007-04-30 16:41:49', 0, 'Isfahān', 'Isfahān', 210, 'N');
INSERT INTO c_region VALUES (474, 0, 0, 'Y', '2007-04-30 16:42:04', 0, '2007-04-30 16:42:04', 0, 'Fārs', 'Fārs', 210, 'N');
INSERT INTO c_region VALUES (475, 0, 0, 'Y', '2007-04-30 16:42:19', 0, '2007-04-30 16:42:19', 0, 'Gilān', 'Gilān', 210, 'N');
INSERT INTO c_region VALUES (476, 0, 0, 'Y', '2007-04-30 16:42:34', 0, '2007-04-30 16:42:34', 0, 'Golestān', 'Golestān', 210, 'N');
INSERT INTO c_region VALUES (477, 0, 0, 'Y', '2007-04-30 16:42:40', 0, '2007-04-30 16:42:40', 0, 'Hamadān', 'Hamadān', 210, 'N');
INSERT INTO c_region VALUES (478, 0, 0, 'Y', '2007-04-30 16:42:49', 0, '2007-04-30 16:42:49', 0, 'Hormozgān', 'Hormozgān', 210, 'N');
INSERT INTO c_region VALUES (479, 0, 0, 'Y', '2007-04-30 16:43:00', 0, '2007-04-30 16:43:00', 0, 'Ilām', 'Ilām', 210, 'N');
INSERT INTO c_region VALUES (480, 0, 0, 'Y', '2007-04-30 16:43:11', 0, '2007-04-30 16:43:11', 0, 'Kermān', 'Kermān', 210, 'N');
INSERT INTO c_region VALUES (481, 0, 0, 'Y', '2007-04-30 16:43:21', 0, '2007-04-30 16:43:21', 0, 'Kermānshāh', 'Kermānshāh', 210, 'N');
INSERT INTO c_region VALUES (482, 0, 0, 'Y', '2007-04-30 16:43:31', 0, '2007-04-30 16:43:31', 0, 'North Khorāsān', 'North Khorāsān', 210, 'N');
INSERT INTO c_region VALUES (483, 0, 0, 'Y', '2007-04-30 16:43:31', 0, '2007-04-30 16:43:31', 0, 'Razavi Khorāsān', 'Razavi Khorāsān', 210, 'N');
INSERT INTO c_region VALUES (484, 0, 0, 'Y', '2007-04-30 16:43:31', 0, '2007-04-30 16:43:31', 0, 'South Khorāsān', 'Southth Khorāsān', 210, 'N');
INSERT INTO c_region VALUES (485, 0, 0, 'Y', '2007-04-30 16:43:40', 0, '2007-04-30 16:43:40', 0, 'Khuzestān', 'Khuzestān', 210, 'N');
INSERT INTO c_region VALUES (486, 0, 0, 'Y', '2007-04-30 16:43:53', 0, '2007-04-30 16:43:53', 0, 'Kohgiluyeh and Boyer-Ahmad', 'Kohgiluyeh and Boyer-Ahmad', 210, 'N');
INSERT INTO c_region VALUES (487, 0, 0, 'Y', '2007-04-30 16:44:03', 0, '2007-04-30 16:44:03', 0, 'Kurdistān', 'Kurdistān', 210, 'N');
INSERT INTO c_region VALUES (488, 0, 0, 'Y', '2007-04-30 16:44:30', 0, '2007-04-30 16:44:30', 0, 'Lorestān', 'Lorestān', 210, 'N');
INSERT INTO c_region VALUES (489, 0, 0, 'Y', '2007-04-30 16:44:43', 0, '2007-04-30 16:44:43', 0, 'Markazi', 'Markazi', 210, 'N');
INSERT INTO c_region VALUES (490, 0, 0, 'Y', '2007-04-30 16:44:59', 0, '2007-04-30 16:44:59', 0, 'Māzandarān', 'Māzandarān', 210, 'N');
INSERT INTO c_region VALUES (491, 0, 0, 'Y', '2007-04-30 16:45:07', 0, '2007-04-30 16:45:07', 0, 'Qazvin', 'Qazvin', 210, 'N');
INSERT INTO c_region VALUES (492, 0, 0, 'Y', '2007-04-30 16:45:20', 0, '2007-04-30 16:45:20', 0, 'Qom', 'Qom', 210, 'N');
INSERT INTO c_region VALUES (493, 0, 0, 'Y', '2007-04-30 16:45:30', 0, '2007-04-30 16:45:30', 0, 'Semnān', 'Semnān', 210, 'N');
INSERT INTO c_region VALUES (494, 0, 0, 'Y', '2007-04-30 16:45:40', 0, '2007-04-30 16:45:40', 0, 'Sistān and Baluchestān', 'Sistān and Baluchestān', 210, 'N');
INSERT INTO c_region VALUES (495, 0, 0, 'Y', '2007-04-30 16:45:53', 0, '2007-04-30 16:45:53', 0, 'Tehrān', 'Tehrān', 210, 'N');
INSERT INTO c_region VALUES (496, 0, 0, 'Y', '2007-04-30 16:46:03', 0, '2007-04-30 16:46:03', 0, 'Yazd', 'Yazd', 210, 'N');
INSERT INTO c_region VALUES (497, 0, 0, 'Y', '2007-04-30 16:46:03', 0, '2007-04-30 16:46:03', 0, 'Zanjān', 'Zanjān', 210, 'N');

COMMIT;

BEGIN TRANSACTION;

UPDATE AD_SEQUENCE
   SET currentnextsys = (SELECT MAX (c_region_id) + 1
                           FROM C_REGION
                          WHERE c_region_id < 1000000)
 WHERE NAME = 'C_Region';

COMMIT;
