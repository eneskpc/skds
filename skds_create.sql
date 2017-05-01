-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-04-26 09:50:39.022

-- tables
-- Table: Company
CREATE TABLE Company (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    detail varchar(100) NOT NULL,
    contactName varchar(25) NOT NULL,
    contactPhone varchar(11) NOT NULL,
    imageUrl int NOT NULL,
    approved binary(1) NOT NULL,
    User_id int NOT NULL,
    CONSTRAINT Company_pk PRIMARY KEY (id)
) COMMENT 'Şirket Bilgilerini tutar';

-- Table: Customer
CREATE TABLE Customer (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(25) NOT NULL,
    gender varchar(1) NOT NULL,
    birthYear int NOT NULL,
    homeCity int NOT NULL,
    User_id int NOT NULL,
    CONSTRAINT Customer_pk PRIMARY KEY (id)
);

-- Table: Messages
CREATE TABLE Messages (
    id int NOT NULL AUTO_INCREMENT,
    sender_id int NOT NULL,
    receiver_id int NOT NULL,
    title varchar(20) NOT NULL,
    detail varchar(100) NOT NULL,
    previous int NOT NULL,
    CONSTRAINT Messages_pk PRIMARY KEY (id)
);

-- Table: Reponse
CREATE TABLE Reponse (
    id int NOT NULL AUTO_INCREMENT,
    message text NOT NULL,
    previous int NOT NULL,
    Request_id int NOT NULL,
    User_id int NOT NULL,
    CONSTRAINT Reponse_pk PRIMARY KEY (id)
);

-- Table: Request
CREATE TABLE Request (
    id int NOT NULL AUTO_INCREMENT,
    title varchar(50) NOT NULL,
    date date NOT NULL,
    detail text NOT NULL,
    Company_id int NOT NULL,
    Customer_id int NOT NULL,
    Staff_id int NOT NULL,
    CONSTRAINT Request_pk PRIMARY KEY (id)
);

-- Table: Staff
CREATE TABLE Staff (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(25) NOT NULL,
    Company_id int NOT NULL,
    User_id int NOT NULL,
    CONSTRAINT Staff_pk PRIMARY KEY (id)
);

-- Table: User
CREATE TABLE User (
    id int NOT NULL AUTO_INCREMENT,
    email varchar(50) NOT NULL,
    password varchar(10) NOT NULL,
    name varchar(25) NOT NULL,
    type int NOT NULL COMMENT '1-Müşteri / 2-Personel / 3-Şirket / 4-Webmaster',
    CONSTRAINT User_pk PRIMARY KEY (id)
) COMMENT 'Kullanıcı giriş bilgilerini ve kullanıcı tipini tutar
';

-- foreign keys
-- Reference: Company_User (table: Company)
ALTER TABLE Company ADD CONSTRAINT Company_User FOREIGN KEY Company_User (User_id)
    REFERENCES User (id);

-- Reference: Customer_User (table: Customer)
ALTER TABLE Customer ADD CONSTRAINT Customer_User FOREIGN KEY Customer_User (User_id)
    REFERENCES User (id);

-- Reference: Messages_User_Receiver (table: Messages)
ALTER TABLE Messages ADD CONSTRAINT Messages_User_Receiver FOREIGN KEY Messages_User_Receiver (receiver_id)
    REFERENCES User (id);

-- Reference: Messages_User_Sender (table: Messages)
ALTER TABLE Messages ADD CONSTRAINT Messages_User_Sender FOREIGN KEY Messages_User_Sender (sender_id)
    REFERENCES User (id);

-- Reference: Reponse_Request (table: Reponse)
ALTER TABLE Reponse ADD CONSTRAINT Reponse_Request FOREIGN KEY Reponse_Request (Request_id)
    REFERENCES Request (id);

-- Reference: Reponse_User (table: Reponse)
ALTER TABLE Reponse ADD CONSTRAINT Reponse_User FOREIGN KEY Reponse_User (User_id)
    REFERENCES User (id);

-- Reference: Request_User_Company (table: Request)
ALTER TABLE Request ADD CONSTRAINT Request_User_Company FOREIGN KEY Request_User_Company (Customer_id)
    REFERENCES User (id);

-- Reference: Request_User_Customer (table: Request)
ALTER TABLE Request ADD CONSTRAINT Request_User_Customer FOREIGN KEY Request_User_Customer (Staff_id)
    REFERENCES User (id);

-- Reference: Request_User_Staff (table: Request)
ALTER TABLE Request ADD CONSTRAINT Request_User_Staff FOREIGN KEY Request_User_Staff (Company_id)
    REFERENCES User (id);

-- Reference: Staff_User_Company (table: Staff)
ALTER TABLE Staff ADD CONSTRAINT Staff_User_Company FOREIGN KEY Staff_User_Company (Company_id)
    REFERENCES User (id);

-- Reference: Staff_User_Staff (table: Staff)
ALTER TABLE Staff ADD CONSTRAINT Staff_User_Staff FOREIGN KEY Staff_User_Staff (User_id)
    REFERENCES User (id);

-- End of file.

