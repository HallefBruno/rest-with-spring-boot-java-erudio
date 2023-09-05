CREATE TABLE books (
  id integer serial PRIMARY KEY,
  author varchar(255),
  launch_date timestamp NOT NULL,
  price decimal(65,2) NOT NULL,
  title varchar(255)
);
