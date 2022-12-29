CREATE TABLE IF NOT EXISTS person
(
  id serial NOT NULL PRIMARY KEY, 
  address character varying(255),
  first_name character varying(255),
  gender character varying(255),
  last_name character varying(255)
)
