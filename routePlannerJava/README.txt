************************************************
Bus Route Planner (BRP) v0.1 
-Team1-Section 601 **********************************************************

SUMMARY

******************************************************************************************

 
With a plethora of places to visit and classes to attend, it is a real challenge for both the new and current students on campus to reach their destination on time. The present bus transport system works well with providing us the routes of buses on a map. This map consists of main bus stops and timings at which the buses would pass by a particular stop. However, a student may be oblivious to the exact locations of bus stops and the bus to be taken to reach his/her destination on time.  Moreover, he may not be aware of the bus occupancy and any delays that may occur. To address this situation, our system will get the source and destination from the user and will then display the best route to reach his destination on time. Along with the route, the system will also show if the buses on the route are running full or if there are any delays. This solution would ensure a hassle-free travel for all the students on campus! 

******************************************************************************************


CHANGES
******************************************************************************************
version 0.4
- release candidate build 1
- Enhanced the application to allow user to enter his desired source and Destination address 
( Note: Address should end with College Station TX or Bryan TX) 
- GUI will show the 5 possible nearest bus stops corresponding to user's desired address in 'From' or 'To' field
- Refined the GUI aesthetics to be more user interactive
- Added 'Show OnCampusMaps' and 'Show OffCampus Maps' buttons which will open Bus Transport maps in web browser. 
- Added confirmation box on Exit button click. 

Plan for next iteration
- Display the bus occupancy levels based on the user's input to 'From', 'To', and 'Time' fields 
- Attempt to get route plans based on From time field. 

******************************************************************************************
version 0.3
- beta release of Bus Route Planner application
- Refined the GUI of the application to populate the drop downs with BusStops from the database
- Updated the GUI to show the results in a text box to show 
- Modified the Qeuery results to be more efficient by adding indexes and making it more readable
- Added Trip, StopTime nodes and Uses and TIMED_STOP relationships to the Neo4j database as an attempt towards implementing Use case 2

Plan for Next iteration
- Implement the search From 'My location' feature of the Use case 1.
- Populate real time data and try to get the timetable for waypoint stops.
- Display the itinerary with bus timings and occupancy value. 


***************************************************************************************
version 0.2
- alpha release
- completed first pass of primary use case (Get RoutePlan between selected Bus Stops)
- added all BusStop and BusRoute Labels along with couple of relationship labels between them
- added all properties from API to nodes and edges
- Loaded data directly from REST API 
- Interactive GUI for easy selection


********************************************************************************************
version 0.1
- proof of architecture


Purpose of the build: To provide proof of architecture of the Route Planner application for Texas A&M University. 
Features:
The current build, imports the bus route names as nodes to the Neo4j database and fetches them from the DB and displays on the console
 
******************************************************************************************

SETUP


1. Make sure internet connection availability as data is loaded to DB directly from REST API

2. Change the dbconfig.properties file under the src/main/resources folder if your db settings for neo4j are DIFFERENT from the defaults mentioned below.
************************
Neo4jDBurl=bolt://localhost:7687
dbuser=istm622
dbpaswrd=istm622
bustopcsvfilepath=file:///BusRoute.csv
***********************************************
To RUN the application: 
Import the Eclipse gradle file and build and run the gradle task to execute the application. Look out on the Eclipse console for the custom logs and system outout.