/*Database  create*/
DROP DATABASE IF EXISTS Thogakade;
CREATE DATABASE IF NOT EXISTS Thogakade;

SHOW DATABASES;
USE Thogakade;
SHOW TABLES;

/*Customer Table create*/
DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    customerId VARCHAR (15),
    name VARCHAR (45) NOT NULL DEFAULT 'Unknown',
    address TEXT,
    salary DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (customerId)
);
SHOW TABLES;
DESCRIBE Customer;

/*Order Table create*/
DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order`(
    orderId VARCHAR (15),
    cId VARCHAR (15),
    orderData DATE,
    time VARCHAR (15),
    cost DOUBLE,
    CONSTRAINT PRIMARY KEY (orderId),
    CONSTRAINT FOREIGN KEY (cId) REFERENCES Customer(customerId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE `Order`;


/*Item Table create*/
DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
    code VARCHAR (15),
    description TEXT,
    qtyOnHand INT DEFAULT 0,
    unitPrice DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (code)
);
SHOW TABLES;
DESCRIBE Item;


/*Associate Table create -- > OrderDetail*/
DROP TABLE IF EXISTS OrderDetail;
CREATE TABLE IF NOT EXISTS OrderDetail(
    itemCode VARCHAR (15),
    orderDetailId VARCHAR (15),
    qty INT,
    price DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (itemCode,orderDetailId),
    CONSTRAINT FOREIGN KEY (itemCode) REFERENCES Item(code) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (orderDetailId) REFERENCES `Order`(orderId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE OrderDetail;

/*Desc Tables*/
DESCRIBE Customer;
DESCRIBE `Order`;
DESCRIBE Item;
DESCRIBE OrderDetail;

SELECT * FROM Customer;
SELECT * FROM `Order`;
SELECT * FROM Item;
SELECT * FROM OrderDetail;


INSERT INTO Item VALUES ("I-001","Apple",10,50);
INSERT INTO Item VALUES ("I-002","Banana",20,110);
INSERT INTO Item VALUES ("I-003","Cake",75,25);