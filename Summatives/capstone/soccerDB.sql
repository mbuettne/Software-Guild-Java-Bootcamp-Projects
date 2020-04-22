drop database if exists soccerDB;

create database soccerDB;

use soccerDB;

create table team(
teamId int primary key auto_increment,
teamName VARCHAR(50),
coachName VARCHAR(50)
);

create table teamGame(
teamGameId int primary key auto_increment,
gameDate date not null,
gameLocation VARCHAR(10) not null,
opponent VARCHAR(50) not null,
teamScore int not null,
opponentScore int not null,
result VARCHAR(10) not null,
teamId int not null,
        constraint fk_team_game
	  foreign key (teamId)
      references team(teamId)
);

create table player(
playerId int primary key auto_increment,
firstName VARCHAR(25) not null,
lastName VARCHAR(50) not null,
height int null,
weight int null,
playerPosition VARCHAR(25) not null,
dominantFoot VARCHAR(10) null,
teamId int not null,
        constraint fk_team_player
	  foreign key (teamId)
      references team(teamId)
);

create table playerGame(
playerGameId int primary key auto_increment,
shots int null,
goals int null,
assists int null,
dribbles int null,
passes int null,
passPercentage int null,
tackles int null,
interceptions int null,
shotsDefensed int null,
shotsSaved int null,
goalsAllowed int null,
playerId int not null,
        constraint fk_player_game
	  foreign key (playerId)
      references player(playerId)
);

create table users(
	userID int primary key auto_increment,
    userType varchar(5) not null,
    firstName VARCHAR(25) not null,
    lastName VARCHAR(50) not null,
    email VARCHAR(50),
    `password` VARCHAR(25),
    teamId int not null,
        constraint fk_team_user
	  foreign key (teamId)
      references team(teamId)
);