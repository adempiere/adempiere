-- oracle version
-- Function: unaccent_string(text, numeric)

-- DROP FUNCTION unaccent_string(text, numeric);

CREATE OR REPLACE FUNCTION unaccent_string
(
	p_input_string IN VARCHAR2,
	p_version IN NUMBER
)
  RETURN NUMBER 
AS
	v_output_string VARCHAR2;
BEGIN
v_output_string : = p_input_string
v_output_string := translate(v_output_string, 'âaaaaaÁÂAAAAAA', 'aaaaaaaaaaaaaa');
v_output_string := translate(v_output_string, 'eééeëeeeeeEËEEE', 'eeeeeeeeeeeeeee');
v_output_string := translate(v_output_string, 'iíîiiiiïÏÍÎIIIII', 'iiiiiiiiiiiiiiii');
v_output_string := translate(v_output_string, 'óôooooOÓÔOOOOO', 'oooooooooooooo');
v_output_string := translate(v_output_string, 'uúuuuuuUÚUUUUUU', 'uuuuuuuuuuuuuuu');
v_output_string := translate(v_output_string, 'ç', 'c');
if p_version = 1 then
 v_output_string := replace(lower(v_output_string),'ß','ss');
 v_output_string := replace(lower(v_output_string),'ä','ae');
 v_output_string := replace(lower(v_output_string),'ö','oe');
 v_output_string := replace(lower(v_output_string),'ü','ue');
else
 v_output_string := replace(lower(v_output_string),'ss','ß');
 v_output_string := replace(lower(v_output_string),'ae','ä');
 v_output_string := replace(lower(v_output_string),'oe','ö');
 v_output_string := replace(lower(v_output_string),'ue','ü');
end if;
return v_output_string;
END;


-- Function: unaccent_string(text)

-- DROP FUNCTION unaccent_string(text);

CREATE OR REPLACE FUNCTION unaccent_string
(
	p_text IN VARCHAR2
)
  RETURN VARCHAR 
AS
BEGIN
	return unaccent_string(p_text, 1);
END;

