USE Hotel;

--    1. Write a query that returns a list of reservations that end in July 2023, including the name of the guest, the room number(s), and the reservation dates.

SELECT
	g.FirstName GuestFirstName,
    g.LastName GuestLastName,
    r.RoomNumber RoomNumber,
    r.StartDate StartDate,
    r.EndDate EndDate
FROM Reservation r 
INNER JOIN Guest g ON r.GuestId = g.GuestId
WHERE r.EndDate BETWEEN '2023-07-01' AND '2023-07-31';


-- Answer1: 4 Rows 
-- GuestFirstName GuestLastName RoomNumber StartDate  EndDate
-- Matt             Buettner       205     2023-06-28  2023-07-02
-- Walter           Holaway        204     2023-07-13  2023-07-14
-- Wilfred           Vise          401     2023-07-18  2023-07-21
-- Bettyann          Seery         303     2023-07-28  2023-07-29

--    2. Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name, the room number, and the dates of the reservation.

SELECT
	g.FirstName GuestFirstName,
    g.LastName GuestLastName,
    r.RoomNumber RoomNumber,
    r.StartDate StartDate,
    r.EndDate EndDate
FROM Reservation r 
INNER JOIN Guest g ON r.GuestId = g.GuestId
INNER JOIN Room rm ON r.RoomNumber = rm.RoomNumber
INNER JOIN RoomAmenity ra ON rm.RoomNumber = ra.RoomNumber
INNER JOIN Amenity a ON ra.AmenityId = a.AmenityId
WHERE a.AmenityId = 4;

-- Answer2: 11 Rows
-- GuestFirstName GuestLastName RoomNumber StartDate     EndDate
-- Karie            Yang	        201	  2023-03-06	2023-03-07
-- Bettyann         Seery	        203	  2023-02-05	2023-02-10
-- Karie             Yang	        203	  2023-09-13	2023-09-15
-- Matt            Buettner	        205   2023-06-28	2023-07-02
-- Wilfred          Vise	        207	  2023-04-23	2023-04-24
-- Walter          Holaway	        301	  2023-04-09	2023-04-13
-- Mack            Simmer	        301	  2023-11-22	2023-11-25
-- Bettyann         Seery	        303	  2023-07-28	2023-07-29
-- Duane          Cullison	        305	  2023-02-22	2023-02-24
-- Bettyann         Seery	        305	  2023-08-30	2023-09-01
-- Matt           Buettner	        307	  2023-03-17	2023-03-20

--    3. Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the room(s) reserved, the starting date of the reservation, and how many people were included in the reservation. (Choose a guest's name from the existing data.)

SELECT
	g.FirstName GuestFirstName,
    g.LastName GuestLastName,
    r.RoomNumber RoomNumber,
    r.StartDate StartDate,
    r.Adults + r.Children TotalPeople
FROM Reservation r
INNER JOIN Guest g ON r.GuestId = g.GuestId
Where g.Firstname = 'Mack' AND g.LastName = 'Simmer';
    

-- Answer3: 4 Rows
-- GuestFirstName GuestLastName  RoomNumber  StartDate  TotalPeople
-- Mack             Simmer	        308	      2023-02-02	1
-- Mack             Simmer	        208	      2023-09-16	2
-- Mack             Simmer	        206	      2023-11-22	2
-- Mack             Simmer	        301	      2023-11-22	4

--    4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. The results should include all rooms, whether or not there is a reservation associated with the room.

SELECT
	rm.RoomNumber RoomNumber,
    r.ReservationId ReservationId,
    r.TotalPrice RoomCost
FROM Room rm
LEFT OUTER JOIN Reservation r ON rm.RoomNumber = r.RoomNumber;


-- Answer4: 26 Rows
-- RoomNumber ReservationId RoomCost
-- 205	          15	     699.96
-- 206	          12	     599.96
-- 206	          23	     449.97
-- 207            10	     174.99
-- 208	          13	     599.96
-- 208	          20	     149.99
-- 305	          3	         349.98
-- 305	          19	     349.98
-- 306	         null         null	
-- 307	          5	         524.97
-- 308	          1	         299.98
-- 201	          4	         199.99
-- 202	          7	         349.98
-- 203	          2	         999.95
-- 203	         21	         399.98
-- 204	         16	         184.99
-- 301	          9	         799.96
-- 301	         24	         599.97
-- 302	         6	         924.95
-- 302	         25	         699.96
-- 303	         18	         199.99
-- 304	         14	         184.99
-- 401	         11	         1199.97
-- 401	         17	         1259.97
-- 401	         22	         1199.97
-- 402	        null          null	

--    5. Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023.

SELECT
	g.FirstName GuestFirstName,
    g.LastName GuestLastName,
    r.RoomNumber RoomNumber,
    r.StartDate StartDate,
    r.EndDate EndDate,
    r.Adults + r.Children TotalPeople
FROM Reservation r
INNER JOIN Guest g ON r.GuestId = g.GuestId
WHERE 
	r.Adults + r.Children >= 3 AND
    ((r.StartDate BETWEEN '2023-04-01' AND '2023-04-30') OR
    (r.EndDate BETWEEN '2023-04-01' AND '2023-04-30'));

-- Answer5: 0 Rows

--    6. Write a query that returns a list of all guest names and the number of reservations per guest, sorted starting with the guest with the most reservations and then by the guest's last name.
SELECT
	g.LastName GuestLastName,
    COUNT(g.LastName) NumberOfReservations
FROM Reservation r 
INNER JOIN Guest g ON r.GuestId = g.GuestId
GROUP BY g.LastName
ORDER BY NumberOfReservations DESC, g.LastName;

-- Answer6: 11 Rows
-- GuestLastName  NumberOfReservations
-- Simmer	         4
-- Seery	         3
-- Buettner	         2
-- Cullison	         2
-- Holaway	         2
-- Lipton	         2
-- Tilton	         2
-- Tison	         2
-- Vise	             2 
-- Yang	             2
-- Luechtefeld	     1

--    7. Write a query that displays the name, address, and phone number of a guest based on their phone number. (Choose a phone number from the existing data.)
SELECT 
	g.FirstName FirstName,
    g.LastName LastName,
    g.Address StreetAddress,
    g.City City,
    g.State State,
    g.ZipCode ZipCode,
    g.PhoneNumber PhoneNumber
FROM Guest g
WHERE g.PhoneNumber = '(834) 727-1001';

-- Answer7: 1 Row
-- FirstName  LastName       StreetAddress       City  State  ZipCode    PhoneNumber
-- Wilfred	    Vise	77 West Surrey Street	Oswego	NY	   13126   (834) 727-1001
