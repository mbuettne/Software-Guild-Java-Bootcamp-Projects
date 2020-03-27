DROP DATABASE IF EXISTS MovieCatalogue;

CREATE DATABASE IF NOT EXISTS MovieCatalogue;

USE MovieCatalogue;

CREATE TABLE IF NOT EXISTS `Genre` (
`GenreID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`GenreName` varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Rating` (
`RatingID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`RatingName` varchar(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Director` (
`DirectorID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`FirstName` varchar(30) NOT NULL,
`LastName` varchar(30) NOT NULL,
`BirthDate` date NULL
);

CREATE TABLE IF NOT EXISTS `Actor` (
`ActorID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`FirstName` varchar(30) NOT NULL,
`LastName` varchar(30) NOT NULL,
`BirthDate` date NULL
);

CREATE TABLE IF NOT EXISTS `Movie` (
`MovieID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`GenreID` int(11) NOT NULL,
CONSTRAINT `fk_MovieGenre` FOREIGN KEY (`GenreID`) REFERENCES `Genre` (`GenreID`),
`DirectorID` int(11) NULL,
CONSTRAINT `fk_MovieDirector` FOREIGN KEY (`DirectorID`) REFERENCES `Director` (`DirectorID`),
`RatingID` int(11) NULL,
CONSTRAINT `fk_MovieRating` FOREIGN KEY (`RatingID`) REFERENCES `Rating` (`RatingID`),
`Title` varchar(128) NOT NULL,
`ReleaseDate` date NULL
);

CREATE TABLE IF NOT EXISTS `CastMembers` (
`CastMembersID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`ActorID` int(11) NOT NULL,
CONSTRAINT `fkCastMemberActor` FOREIGN KEY (`ActorID`) REFERENCES `Actor` (`ActorID`),
`MovieID` int(11) NOT NULL,
CONSTRAINT `fkCastMemberMovie` FOREIGN KEY (`MovieID`) REFERENCES `Movie` (`MovieID`),
`Role` varchar(50) NOT NULL
);

