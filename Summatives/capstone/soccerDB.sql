drop database if exists soccerDB;

create database soccerDB;

use soccerDB;

create table team(
teamId int primary key auto_increment,
teamName VARCHAR(50) not null,
coachName VARCHAR(50) not null,
logoUrl VARCHAR(250) null
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
foreign key (teamId) references team(teamId)
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
foreign key (teamId) references team(teamId)
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
foreign key (playerId) references player(playerId)
);

create table `role`(
roleId int primary key auto_increment,
roleName varchar(30) not null
);

create table users(
userId int primary key auto_increment,
roleId int not null,
firstName VARCHAR(25) not null,
lastName VARCHAR(50) not null,
username VARCHAR(50) not null unique,
`password` VARCHAR(100) not null,
enabled boolean not null,
teamId int not null,
foreign key (teamId) references team(teamId),
foreign key (roleId) references role(roleId)
);

insert into team(teamId, teamName, coachName)
	values(1, "testTeam", "testCoach");
    
insert into teamGame(teamGameId, gameDate, gameLocation, opponent, teamScore, opponentScore, result, teamId)
	values(1, '2020-01-18', "Away", "Opponent", 3, 2, "Win", 1);
    
insert into player(playerId, firstName, lastName, height, weight, playerPosition, dominantFoot, teamId)
	values(1, "testPlayer", "testPlayerson", 70, 185, "Defense", "Right", 1);
    
insert into playerGame(playerGameId, shots, goals, assists, dribbles, passes, passPercentage, tackles, interceptions, shotsDefensed, shotsSaved, goalsAllowed, playerId)
	values(1, 1, 0, 0, 0, 16, 78, 7, 4, 0, 0, 0, 1);

insert into `role`(roleId, roleName)
	values(1, "ROLE_ADMIN"),(2, "ROLE_USER");
    
insert into users(userId, roleId, firstName, lastName, username, `password`, enabled, teamId)
	values(1, 1, "AdminFName", "AdminLName", "Admin", "admin", true, 1),
		  (2, 2, "UserFName", "UserLName", "User", "user", true, 1);
          
-- UPDATE users SET `password` = '$2a$10$drm6FN9XjL0uEXpb9BaQheBVxxmEt65VT6x/6DNz9LtKCT9iYdupG' WHERE userId = 1;
-- UPDATE users SET `password` = '$2a$10$GKttED.JCtgRPhnohx66/OsPLCfp9sGtqYN9g5XOamv1cGsArEbb2' WHERE userId = 2;