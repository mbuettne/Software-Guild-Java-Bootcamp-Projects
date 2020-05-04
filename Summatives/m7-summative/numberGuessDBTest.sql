DROP DATABASE IF EXISTS numberGuessDBTest;
CREATE DATABASE numberGuessDBTest;

USE numberGuessDBTest;

CREATE TABLE Game(
`gameId` INT PRIMARY KEY AUTO_INCREMENT,
`progress` VARCHAR(50) NOT NULL,
`answer` VARCHAR(4)
);

CREATE TABLE Rounds(
`roundsId` INT PRIMARY KEY AUTO_INCREMENT,
`gameId` INT,
`guess` VARCHAR(4) NOT NULL,
`time` VARCHAR(50) NOT NULL,
`result` VARCHAR(10) NOT NULL,
-- `hasWon` BOOLEAN NOT NULL,
FOREIGN KEY (`gameId`) REFERENCES Game(`gameId`)
);