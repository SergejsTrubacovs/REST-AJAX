# REST-AJAX
# This is Eclipse project

# REST
  Switch to Java EE perspective.
  Open Ant view. In Eclipse menu go to Window -> Show View -> Other -> Ant.
  Drag and drop /A00212878_ClientSide42/db/manageDB.xml file into Ant tab.
  Start HSQLDB server.
  Start HSQLDB client. Type: HSQL Database Engine Server. URL: jdbc:hsqldb:hsql://localhost/oneDB. No password.
  Start Apache Tomcat v8.0 server.
  Run on server /A00212878_ClientSide42/src/server/Employee.java file. 404 error is OK.
  Run /A00212878_ClientSide42/src/client/Client.java file as Java Application. Clicking on List All button displays employees' details stored in database. Clicking on Add button stores new employee details in database. Leave the Employee Id text field empty.

# AJAX
  Run on server /A00212878_ClientSide42/WebContent/index.html file or go to http://localhost:8080/A00212878_ClientSide42/index.html in Google Chrome. Clicking on employee name displays his/her details stored in database.
