-- CREATE DATABASE cars_crud;
-- USE cars_crud;

CREATE TABLE cars (
    id int not null AUTO_INCREMENT,
    name varchar(50) not null,
    type varchar(50) not null,
    PRIMARY KEY (id)
)