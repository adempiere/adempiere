SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
-- Function: unaccent_string(text, numeric)
-- DROP FUNCTION unaccent_string;
CREATE OR REPLACE FUNCTION unaccent_string
(
  p_input_string IN VARCHAR2,
  p_version IN NUMBER DEFAULT 1
)
RETURN VARCHAR2  
IS 
    input_string VARCHAR2(2000) := NULL;
BEGIN
input_string := p_input_string;
input_string := translate(input_string, 'âaaaaaÁÂAAAAAA', 'aaaaaaaaaaaaaa');
input_string := translate(input_string, 'eééeëeeeeeEËEEE', 'eeeeeeeeeeeeeee');
input_string := translate(input_string, 'iíîiiiiïÏÍÎIIIII', 'iiiiiiiiiiiiiiii');
input_string := translate(input_string, 'óôooooOÓÔOOOOO', 'oooooooooooooo');
input_string := translate(input_string, 'uúuuuuuUÚUUUUUU', 'uuuuuuuuuuuuuuu');
input_string := translate(input_string, 'ç', 'c');
IF (p_version = 1) THEN
 input_string := replace(lower(input_string),'ß','ss');
 input_string := replace(lower(input_string),'ä','ae');
 input_string := replace(lower(input_string),'ö','oe');
 input_string := replace(lower(input_string),'ü','ue');
ELSE
 input_string := replace(lower(input_string),'ss','ß');
 input_string := replace(lower(input_string),'ae','ä');
 input_string := replace(lower(input_string),'oe','ö');
 input_string := replace(lower(input_string),'ue','ü');
END IF;
RETURN input_string;
END unaccent_string;
/
