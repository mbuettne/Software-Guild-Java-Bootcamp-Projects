DROP DATABASE IF EXISTS numberGuessDB;
CREATE DATABASE numberGuessDB;

USE numberGuessDB;

CREATE TABLE Game(
gameId INT PRIMARY KEY AUTO_INCREMENT,
`progress` VARCHAR(50) NOT NULL,
`answer` VARCHAR(4)
);

CREATE TABLE Rounds(
roundsId INT PRIMARY KEY AUTO_INCREMENT,
`guess` VARCHAR(4) NOT NULL,
`time` TIMESTAMP NOT NULL,
`result` VARCHAR(10) NOT NULL
);

CREATE TABLE Game_Rounds(
gameId INT NOT NULL,
roundsId INT NOT NULL,
PRIMARY KEY(gameId, roundsId),
FOREIGN KEY (gameId) REFERENCES Game(gameId),
FOREIGN KEY (roundsId) REFERENCES Rounds(roundsId));

 INSERT INTO Game(`progress`, `answer`) VALUES
 ('In Progress', '1234'),
 ('Complete', '5728'),
 ('In Progress', '0183');
 
 INSERT INTO Rounds(`guess`, `time`, `result`) VALUES
 ('2946', '2018-01-01 18:00:00', 'e:0:p:1'),
 ('2947', '2018-01-01 15:00:00', 'e:2:p:2'),
 ('3027', '2018-01-01 16:00:00', 'e:1:p:3');
 
 INSERT INTO Game_Rounds(gameId, roundsId) VALUES
 (1, 1),
 (2, 2),
 (3, 1),
 (3, 3);
