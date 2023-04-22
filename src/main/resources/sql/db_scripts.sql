CREATE SCHEMA IF NOT EXISTS users;

CREATE TABLE IF NOT EXISTS users."users"
(
  uuid UUID PRIMARY KEY,
  first_name VARCHAR,
  last_name VARCHAR,
  email VARCHAR,
  age INT4,
  active BOOLEAN
);