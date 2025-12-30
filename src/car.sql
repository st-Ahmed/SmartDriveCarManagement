-- Create the database
CREATE DATABASE IF NOT EXISTS car_db;

-- Use the database
USE car_db;

-- Create the car table
-- We dropped 'make' and used 'brand' to match your Java code exactly.
CREATE TABLE car (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plate VARCHAR(20),
    brand VARCHAR(50),
    model VARCHAR(50),
    year INT,
    speed INT
);