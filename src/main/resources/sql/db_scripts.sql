CREATE SCHEMA IF NOT EXISTS users;

CREATE TABLE IF NOT EXISTS users.users
(
  uuid UUID NOT NULL,
  first_name VARCHAR NOT NULL,
  last_name VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  age INT4 NOT NULL,
  active BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT users_pkey PRIMARY KEY (uuid)
);