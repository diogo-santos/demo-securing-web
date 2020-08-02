DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
  USER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  USERNAME VARCHAR(128) NOT NULL UNIQUE,
  PASSWORD VARCHAR(256) NOT NULL
);

DROP TABLE IF EXISTS CUSTOMER;

CREATE TABLE CUSTOMER (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  email VARCHAR(40),
  address1 VARCHAR(40),
  address2 VARCHAR(40),
  country VARCHAR(20),
  city VARCHAR(20),
  zip_code VARCHAR(10),
  date_of_birth DATE
);