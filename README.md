# Festivities
Test Prodigious Festivities

#Instruccions
- Descompress Festivities.zip
- Modify application.properties that is inside Festivities-0.0.1-SNAPSHOT.jar with the right information about the PostgresDB’s configuration.
  #URL DATABASE
  spring.datasource.url = jdbc:postgresql://localhost/festivity
  # Username and password
  spring.datasource.username = postgres
  spring.datasource.password = postgres2016
- Open terminal
- Go to the root of the Project (Festivities)
- Type:java -jar target/Festivities-0.0.1-SNAPSHOT.jar

#Load initial data
- Open a browser and go on
- RequestMethod.GET
  http://localhost:8080/festivityapi/festivity/load
  This will take festivities.xml and execute ETL for creating the initial data

#Queries
-Find all festivities
  RequestMethod.GET
  http://localhost:8080/festivityapi/festivity/

-Find by Name
  RequestMethod.GET
  http://localhost:8080/festivityapi/festivity/name?name=[name]
-Find by Start Date
  RequestMethod.GET
  Date Format yyyy-MM-dd'T'HH:mm:ss.SSS'Z' (e.g. 2016-07-30T19:16:01.001Z)
  http://localhost:8080/festivityapi//festivity/start_date?startDate=[startDate]

-Find by Data Range
  RequestMethod.GET
  Date Format yyyy-MM-dd'T'HH:mm:ss.SSS'Z' (e.g. 2016-07-30T19:16:01.001Z)
  http://localhost:8080/festivityapi//festivity/date_range?startDate=[ startDate] &endDate=[ endDate]

-Find by Place
  RequestMethod.GET
  http://localhost:8080/festivityapi//festivity/name_place?namePlace=[namePlace]

#New Festivity
  RequestMethod.POST
  Date Format yyyy-MM-dd'T'HH:mm:ss.SSS'Z' (e.g. 2016-07-30T19:16:01.001Z)
  @RequestBody
  JSON File
    {
      "name": "Jared's event 85",
      "start": "2016-07-30T11:08:44.044Z",
      "end": "2017-04-27T11:08:44.044Z",
      "place": "Powell's joint"
    }
  http://localhost:8080/festivityapi//festivity/new

#Update Festivity
  RequestMethod.POST
  Date Format yyyy-MM-dd'T'HH:mm:ss.SSS'Z' (e.g. 2016-07-30T19:16:01.001Z)
  @RequestBody
  JSON File
    {
      "name": "Jared's event 90",
      "start": "2016-07-30T11:08:44.044Z",
      "end": "2017-04-27T11:08:44.044Z"
    }
  http://localhost:8080/festivityapi//festivity/update?id=[id
