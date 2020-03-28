DROP DATABASE IF EXISTS Hotel;

CREATE DATABASE IF NOT EXISTS Hotel;

USE Hotel;

CREATE TABLE `Guest` (
`GuestId` INT PRIMARY KEY AUTO_INCREMENT,
`FirstName` VARCHAR(50) NOT NULL,
`LastName` VARCHAR(50) NOT NULL,
`Address` VARCHAR(30) NOT NULL,
`City` VARCHAR(20) NOT NULL,
`State` CHAR(2) NOT NULL,
`ZipCode` VARCHAR(15),
`PhoneNumber` CHAR(20)
);

CREATE TABLE `Ada` (
`AdaId` INT PRIMARY KEY AUTO_INCREMENT,
`AdaAccessible` CHAR(3) NOT NULL
);

CREATE TABLE `Amenity` (
`AmenityId` INT PRIMARY KEY AUTO_INCREMENT,
`AmenityType` VARCHAR(20) NOT NULL,
`AmenityPrice` DECIMAL(4,2)
);

CREATE TABLE `RoomType` (
`RoomTypeId` INT PRIMARY KEY AUTO_INCREMENT,
`RoomType` VARCHAR(20),
`MaxPeople` TINYINT,
`BasePrice` DECIMAL(5,2),
`PricePerExtraPerson` DECIMAL(4,2),
`BedNumber` SMALLINT
);

CREATE TABLE `Room` (
`RoomNumber` INT PRIMARY KEY,
`RoomTypeId` INT,
`AdaId` INT,
CONSTRAINT `fk_RoomTypeId` FOREIGN KEY (`RoomTypeId`) REFERENCES `RoomType` (`RoomTypeId`),
CONSTRAINT `fk_AdaId` FOREIGN KEY (`AdaId`) REFERENCES `Ada` (`AdaId`)
);

CREATE TABLE `RoomAmenity` (
`RoomNumber` INT NOT NULL,
`AmenityId` INT NOT NULL,
CONSTRAINT `pk_RoomAmenity` PRIMARY KEY (`RoomNumber`, `AmenityId`),
CONSTRAINT `fk_RoomAmenity_Room` FOREIGN KEY (`RoomNumber`) REFERENCES `Room` (`RoomNumber`),
CONSTRAINT `fk_RoomAmenity_Amenity` FOREIGN KEY (`AmenityId`) REFERENCES `Amenity` (`AmenityId`)
);

CREATE TABLE `Reservation` (
`ReservationId` INT PRIMARY KEY AUTO_INCREMENT,
`GuestId` INT NOT NULL,
`RoomNumber` INT NOT NULL,
CONSTRAINT `fk_GuestId` FOREIGN KEY (`GuestId`) REFERENCES `Guest` (`GuestId`),
CONSTRAINT `fk_RoomNumber` FOREIGN KEY (`RoomNumber`) REFERENCES `Room` (`RoomNumber`),
`Adults` SMALLINT NOT NULL,
`Children` SMALLINT NULL,
`StartDate` DATE NOT NULL,
`EndDate` DATE NOT NULL,
`TotalPrice` DECIMAL(6,2)
);