USE PersonalTrainer;

-- Select all columns from ExerciseCategory and Exercise.
-- The tables should be joined on ExerciseCategoryId.
-- This query returns all Exercises and their associated ExerciseCategory.
-- 64 rows
SELECT *
FROM Exercise
INNER JOIN ExerciseCategory ON Exercise.ExerciseCategoryId = ExerciseCategory.ExerciseCategoryId;
--------------------
    
-- Select ExerciseCategory.Name and Exercise.Name
-- where the ExerciseCategory does not have a ParentCategoryId (it is null).
-- Again, join the tables on their shared key (ExerciseCategoryId).
-- 9 rows
SELECT 
	ExerciseCategory.Name,
    Exercise.Name
FROM Exercise
INNER JOIN ExerciseCategory ON Exercise.ExerciseCategoryId = ExerciseCategory.ExerciseCategoryId
WHERE ExerciseCategory.ParentCategoryId IS NULL;
--------------------

-- The query above is a little confusing. At first glance, it's hard to tell
-- which Name belongs to ExerciseCategory and which belongs to Exercise.
-- Rewrite the query using an aliases. 
-- Alias ExerciseCategory.Name as 'CategoryName'.
-- Alias Exercise.Name as 'ExerciseName'.
-- 9 rows
SELECT 
	ExerciseCategory.Name CategoryName,
    Exercise.Name ExerciseName
FROM Exercise
INNER JOIN ExerciseCategory ON Exercise.ExerciseCategoryId = ExerciseCategory.ExerciseCategoryId
WHERE ExerciseCategory.ParentCategoryId IS NULL;
--------------------

-- Select FirstName, LastName, and BirthDate from Client
-- and EmailAddress from Login 
-- where Client.BirthDate is in the 1990s.
-- Join the tables by their key relationship. 
-- What is the primary-foreign key relationship?
-- 35 rows
SELECT
	Client.FirstName FirstName,
    Client.LastName LastName,
    Client.BirthDate BirthDate,
    Login.EmailAddress Email
FROM Client
INNER JOIN Login ON Client.ClientId = Login.ClientId
WHERE Client.BirthDate BETWEEN '1990-01-01' AND '1999-12-31';
--------------------

-- Select Workout.Name, Client.FirstName, and Client.LastName
-- for Clients with LastNames starting with 'C'?
-- How are Clients and Workouts related?
-- 25 rows
SELECT
	Workout.Name WorkoutName,
    Client.FirstName FirstName,
    Client.LastName LastName
FROM Workout
INNER JOIN ClientWorkout ON Workout.WorkoutId = ClientWorkout.WorkoutId
INNER JOIN Client ON ClientWorkout.ClientId = Client.ClientId
WHERE Client.LastName LIKE 'C%';
--------------------

-- Select Names from Workouts and their Goals.
-- This is a many-to-many relationship with a bridge table.
-- Use aliases appropriately to avoid ambiguous columns in the result.
SELECT
	Workout.Name WorkoutName,
    Goal.Name GoalName
FROM Workout
INNER JOIN WorkoutGoal ON Workout.WorkoutId = WorkoutGoal.WorkoutId
INNER JOIN Goal ON WorkoutGoal.GoalId = Goal.GoalId;
--------------------

-- Select FirstName and LastName from Client.
-- Select ClientId and EmailAddress from Login.
-- Join the tables, but make Login optional.
-- 500 rows

SELECT
	Client.FirstName FirstName,
    Client.LastName LastName,
    Login.ClientId ClientId,
    Login.EmailAddress Email
FROM Client
LEFT OUTER JOIN Login ON Client.ClientId = Login.ClientId;
--------------------

-- Using the query above as a foundation, select Clients
-- who do _not_ have a Login.
-- 200 rows

SELECT
	Client.FirstName FirstName,
    Client.LastName LastName,
    Login.ClientId LoginClientId,
    Login.EmailAddress Email
FROM Client
LEFT OUTER JOIN Login ON Client.ClientId = Login.ClientId
WHERE Login.ClientId IS NULL;
--------------------

-- Does the Client, Romeo Seaward, have a Login?
-- Decide using a single query.
-- nope :(

SELECT
	Client.FirstName FirstName,
    Client.LastName LastName,
    Login.ClientId LoginClientId,
    Login.EmailAddress Email
FROM Client
LEFT OUTER JOIN Login ON Client.ClientId = Login.ClientId
WHERE Client.FirstName = 'Romeo' AND Client.LastName = 'Seaward';
--------------------

-- Select ExerciseCategory.Name and its parent ExerciseCategory's Name.
-- This requires a self-join.
-- 12 rows

SELECT
	parent.Name ParentCategory,
    child.Name ChildCategory
FROM ExerciseCategory parent
INNER JOIN ExerciseCategory child ON parent.ExerciseCategoryId = child.ParentCategoryId;
--------------------
    
-- Rewrite the query above so that every ExerciseCategory.Name is
-- included, even if it doesn't have a parent.
-- 16 rows

SELECT
	parent.Name ParentCategory,
    child.Name ChildCategory
FROM ExerciseCategory parent
RIGHT OUTER JOIN ExerciseCategory child ON parent.ExerciseCategoryId = child.ParentCategoryId;
--------------------
    
-- Are there Clients who are not signed up for a Workout?
-- 50 rows
SELECT
	Client.FirstName FirstName,
    Client.LastName LastName,
    Workout.Name Workout
FROM Client
LEFT OUTER JOIN ClientWorkout ON Client.ClientId = ClientWorkout.ClientId
 LEFT OUTER JOIN Workout ON ClientWorkout.WorkoutId = Workout.WorkoutId
WHERE Workout.WorkoutId IS NULL;
--------------------

-- Which Beginner-Level Workouts satisfy at least one of Shell Creane's Goals?
-- Goals are associated to Clients through ClientGoal.
-- Goals are associated to Workouts through WorkoutGoal.
-- 6 rows, 4 unique rows

SELECT
	Goal.GoalId GoalId,
    Goal.Name GoalName
FROM Goal
INNER JOIN ClientGoal ON Goal.GoalId = ClientGoal.GoalId
INNER JOIN Client ON ClientGoal.ClientId = Client.ClientId
WHERE Client.FirstName = 'Shell' AND Client.LastName = 'Creane'; -- 7 (Well-Being), 10 (Core Strength), 16(Focus: Glutes)


SELECT
	Level.Name Level,
    Workout.Name WorkoutName,
    Goal.Name Goal
FROM Level
INNER JOIN Workout ON Level.LevelId = Workout.LevelId
INNER JOIN WorkoutGoal ON Workout.WorkoutId = WorkoutGoal.WorkoutId
INNER JOIN Goal ON WorkoutGoal.GoalId = Goal.GoalId
WHERE Level.LevelId = 1 AND (Goal.GoalId = 7 OR Goal.GoalId = 10 OR Goal.GoalId = 16);

-- One Single Query From Answer Key
SELECT 
	w.WorkoutId,
    w.Name WorkoutName
FROM Client c
INNER JOIN ClientGoal cg ON c.ClientId = cg.ClientId
INNER JOIN WorkoutGoal wg ON cg.GoalId = wg.GoalId
INNER JOIN Workout w ON wg.WorkoutId = w.WorkoutId
WHERE c.FirstName = 'Shell' AND c.LastName = 'Creane' AND w.LevelId = 1;
--------------------

-- Select all Workouts. 
-- Join to the Goal, 'Core Strength', but make it optional.
-- You may have to look up the GoalId before writing the main query.
-- If you filter on Goal.Name in a WHERE clause, Workouts will be excluded.
-- Why?
-- 26 Workouts, 3 Goals
SELECT *
FROM Workout
LEFT OUTER JOIN WorkoutGoal ON Workout.WorkoutId = WorkoutGoal.WorkoutId AND WorkoutGoal.GoalId = 10
LEFT OUTER JOIN Goal ON WorkoutGoal.GoalId = Goal.GoalId;

--------------------

-- The relationship between Workouts and Exercises is... complicated.
-- Workout links to WorkoutDay (one day in a Workout routine)
-- which links to WorkoutDayExerciseInstance 
-- (Exercises can be repeated in a day so a bridge table is required) 
-- which links to ExerciseInstance 
-- (Exercises can be done with different weights, repetions,
-- laps, etc...) 
-- which finally links to Exercise.
-- Select Workout.Name and Exercise.Name for related Workouts and Exercises.

SELECT
	w.Name WorkoutName,
    e.Name ExerciseName
FROM Workout w 
INNER JOIN WorkoutDay wd ON w.WorkoutId = wd.WorkoutId
INNER JOIN WorkoutDayExerciseInstance wdi ON wd.WorkoutDayId = wdi.WorkoutDayId
INNER JOIN ExerciseInstance ei ON wdi.ExerciseInstanceId = ei.ExerciseInstanceId
INNER JOIN Exercise e ON ei.ExerciseId = e.ExerciseId;
--------------------
   
-- An ExerciseInstance is configured with ExerciseInstanceUnitValue.
-- It contains a Value and UnitId that links to Unit.
-- Example Unit/Value combos include 10 laps, 15 minutes, 200 pounds.
-- Select Exercise.Name, ExerciseInstanceUnitValue.Value, and Unit.Name
-- for the 'Plank' exercise. 
-- How many Planks are configured, which Units apply, and what 
-- are the configured Values?
-- 4 rows, 1 Unit, and 4 distinct Values

SELECT
	e.Name ExerciseName,
    eiu.Value UnitValue,
    u.Name UnitName
FROM Exercise e
INNER JOIN ExerciseInstance ei ON e.ExerciseId = ei.ExerciseId
INNER JOIN ExerciseInstanceUnitValue eiu ON ei.ExerciseInstanceId = eiu.ExerciseInstanceId
INNER JOIN Unit u ON eiu.UnitId = u.UnitId
WHERE e.Name = 'Plank';