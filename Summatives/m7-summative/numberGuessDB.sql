DROP DATABASE IF EXISTS numberGuessDB;
CREATE DATABASE numberGuessDB;

USE numberGuessDB;

CREATE TABLE `Game`(
`gameId` INT PRIMARY KEY AUTO_INCREMENT,
`progress` VARCHAR(50) NOT NULL,
`answer` VARCHAR(4)
);

CREATE TABLE `Rounds`(
`roundsId` INT PRIMARY KEY AUTO_INCREMENT,
`gameId` INT,
`guess` VARCHAR(4) NOT NULL,
`time` TIMESTAMP NOT NULL,
`result` VARCHAR(10) NOT NULL,
CONSTRAINT `fk_GameId` FOREIGN KEY (`gameId`) REFERENCES `Game`(`gameId`)
);

 INSERT INTO Game(`progress`, `answer`) VALUES
 ('In Progress', '1234'),
 ('Complete', '5728'),
 ('In Progress', '0183');
 
 INSERT INTO Rounds(`guess`, `time`, `result`, `gameId`) VALUES
 ('2946', '2018-01-01 18:00:00', 'e:0:p:1', 1),
 ('2947', '2018-01-01 15:00:00', 'e:2:p:2', 1),
 ('5728', '2018-01-01 16:00:00', 'e:1:p:3', 2);

