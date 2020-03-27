use PersonalTrainer;

-- Use an aggregate to count the number of Clients.
-- 500 rows
SELECT COUNT(*) NumberOfClients
FROM Client;
--------------------

-- Use an aggregate to count Client.BirthDate.
-- The number is different than total Clients. Why?
-- 463 rows
SELECT COUNT(c.BirthDate) ClientsWithBirthDate
FROM Client c;
--------------------

-- Group Clients by City and count them.
-- Order by the number of Clients desc.
-- 20 rows
SELECT
	c.City City,
	COUNT(c.ClientId) NumberOfClients
FROM Client c
GROUP BY c.city
ORDER BY COUNT(c.ClientId) DESC;
--------------------

-- Calculate a total per invoice using only the InvoiceLineItem table.
-- Group by InvoiceId.
-- You'll need an expression for the line item total: Price * Quantity.
-- Aggregate per group using SUM().
-- 1000 rows
SELECT 
	ili.InvoiceId InvoiceId,
	SUM(ili.Price * ili.Quantity) LineItemTotal    
FROM InvoiceLineItem ili
GROUP BY ili.InvoiceId;

--------------------

-- Calculate a total per invoice using only the InvoiceLineItem table.
-- (See above.)
-- Only include totals greater than $500.00.
-- Order from lowest total to highest.
-- 234 rows
SELECT 
	ili.InvoiceId InvoiceId,
	SUM(ili.Price * ili.Quantity) LineItemTotal    
FROM InvoiceLineItem ili
GROUP BY ili.InvoiceId
HAVING SUM(ili.Price * ili.Quantity) >= 500
ORDER BY SUM(ili.Price * ili.Quantity);
--------------------

-- Calculate the average line item total
-- grouped by InvoiceLineItem.Description.
-- 3 rows
SELECT
	`Description` LineItemDescription,
	AVG(Price * Quantity) AvgLineTotal
FROM InvoiceLineItem
GROUP BY `Description`;
--------------------

-- Select ClientId, FirstName, and LastName from Client
-- for clients who have *paid* over $1000 total.
-- Paid is Invoice.InvoiceStatus = 2.
-- Order by LastName, then FirstName.
-- 146 rows
SELECT
	c.ClientId ClientId,
    c.FirstName FirstName,
    c.LastName LastName,
    SUM(ili.Price * ili.Quantity) TotalPaid
FROM Client c
INNER JOIN Invoice i ON c.ClientId = i.ClientId
INNER JOIN InvoiceLineItem ili ON i.InvoiceId = ili.InvoiceId
WHERE i.InvoiceStatus = 2
GROUP BY c.ClientId, c.FirstName, c.LastName
HAVING SUM(ili.Price * ili.Quantity) >= 1000
ORDER BY c.LastName, c.FirstName;


--------------------

-- Count exercises by category.
-- Group by ExerciseCategory.Name.
-- Order by exercise count descending.
-- 13 rows
SELECT
    ec.Name ExerciseCategory,
    COUNT(e.Name) NumberOfExercises
FROM ExerciseCategory ec
INNER JOIN Exercise e ON ec.ExerciseCategoryId = e.ExerciseCategoryId
GROUP BY ec.Name
ORDER BY COUNT(e.Name) DESC;	
--------------------

-- Select Exercise.Name along with the minimum, maximum,
-- and average ExerciseInstance.Sets.
-- Order by Exercise.Name.
-- 64 rows
SELECT
	e.Name ExerciseName,
    MIN(ei.sets) MinSets,
    MAX(ei.sets) MaxSets,
    AVG(ei.sets) AvgSets
FROM Exercise e
INNER JOIN ExerciseInstance ei ON e.ExerciseId = ei.ExerciseId
GROUP BY e.ExerciseId 
ORDER BY e.Name;
--------------------

-- Find the minimum and maximum Client.BirthDate
-- per Workout.
-- 26 rows
-- Sample: 
-- WorkoutName, EarliestBirthDate, LatestBirthDate
-- '3, 2, 1... Yoga!', '1928-04-28', '1993-02-07'
SELECT
	w.Name WorkoutName,
    MIN(c.BirthDate) EarliestBirthDate,
    MAX(c.BirthDate) LatestBirthDate
FROM Workout w
INNER JOIN ClientWorkout cw ON w.WorkoutId = cw.WorkoutId
INNER JOIN Client c ON cw.ClientId = c.ClientId
GROUP BY w.Name;
--------------------

-- Count client goals.
-- Be careful not to exclude rows for clients without goals.
-- 500 rows total
-- 50 rows with no goals
SELECT
	c.ClientId ClientId,
    COUNT(cg.GoalId) ClientGoals
FROM Client c
LEFT OUTER JOIN ClientGoal cg ON c.ClientId = cg.ClientId
GROUP BY c.ClientId;

--------------------

-- Select Exercise.Name, Unit.Name, 
-- and minimum and maximum ExerciseInstanceUnitValue.Value
-- for all exercises with a configured ExerciseInstanceUnitValue.
-- Order by Exercise.Name, then Unit.Name.
-- 82 rows
SELECT
	e.Name ExerciseName,
    u.Name UnitName,
    MIN(euv.Value) MinUnitValue,
    MAX(euv.Value) MaxUnitValue
FROM Exercise e
INNER JOIN ExerciseInstance ei ON e.ExerciseId = ei.ExerciseId
INNER JOIN ExerciseInstanceUnitValue euv ON ei.ExerciseInstanceId = euv.ExerciseInstanceId
INNER JOIN Unit u ON euv.UnitId = u.UnitId
GROUP BY e.ExerciseId, e.Name, u.UnitId, u.Name
ORDER BY e.Name, u.name;
    
--------------------

-- Modify the query above to include ExerciseCategory.Name.
-- Order by ExerciseCategory.Name, then Exercise.Name, then Unit.Name.
-- 82 rows
SELECT
	e.Name ExerciseName,
    ec.Name ExerciseCategory,
    u.Name UnitName,
    MIN(euv.Value) MinUnitValue,
    MAX(euv.Value) MaxUnitValue
FROM Exercise e 
INNER JOIN ExerciseCategory ec ON e.ExerciseCategoryId = ec.ExerciseCategoryId
INNER JOIN ExerciseInstance ei ON e.ExerciseId = ei.ExerciseId
INNER JOIN ExerciseInstanceUnitValue euv ON ei.ExerciseInstanceId = euv.ExerciseInstanceId
INNER JOIN Unit u ON euv.UnitId = u.UnitId
GROUP BY e.ExerciseId, e.Name, u.UnitId, u.Name
ORDER BY ec.Name, e.Name, u.name;
--------------------

-- Select the minimum and maximum age in years for
-- each Level.
-- To calculate age in years, use the MySQL function DATEDIFF.
-- 4 rows
SELECT
	l.Name LevelName,
	MIN(DATEDIFF('2020-03-26', c.BirthDate)/365) MinAge,
    MAX(DATEDIFF('2020-03-26', c.BirthDate)/365) MAXAge
FROM Level l
INNER JOIN Workout w ON l.LevelId = w.LevelId
INNER JOIN ClientWorkout cw ON w.WorkoutId = cw.WorkoutId
INNER JOIN Client c ON cw.ClientId = c.ClientId
GROUP BY l.Name
ORDER BY l.LevelId;
    
--------------------

-- Stretch Goal!
-- Count logins by email extension (.com, .net, .org, etc...).
-- Research SQL functions to isolate a very specific part of a string value.
-- 27 rows (27 unique email extensions)
SELECT
SUBSTRING_INDEX(EmailAddress, '.', -1) EmailExtension,
    COUNT(EmailAddress)
FROM Login
GROUP BY SUBSTRING_INDEX(EmailAddress, '.', -1)
ORDER BY COUNT(EmailAddress) DESC;

--------------------

-- Stretch Goal!
-- Match client goals to workout goals.
-- Select Client FirstName and LastName and Workout.Name for
-- all workouts that match at least 2 of a client's goals.
-- Order by the client's last name, then first name.
-- 139 rows
