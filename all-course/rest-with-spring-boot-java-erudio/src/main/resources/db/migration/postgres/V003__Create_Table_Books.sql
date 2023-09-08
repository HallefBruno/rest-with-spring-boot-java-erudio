CREATE TABLE IF NOT EXISTS books (
  id serial PRIMARY KEY,
  author varchar(255),
  launch_date timestamp NOT NULL,
  price decimal(65,2) NOT NULL,
  title varchar(255)
);
