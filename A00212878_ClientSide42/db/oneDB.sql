DROP TABLE employee IF EXISTS;
CREATE TABLE employee (
Id INTEGER IDENTITY NOT NULL,
Name VARCHAR(32) NOT NULL,
Surname VARCHAR(32) NOT NULL,
Department VARCHAR(32) NOT NULL,
Positio VARCHAR(32) NOT NULL,
Rate DECIMAL(3,2) NOT NULL,
Hours DECIMAL(3,1) NOT NULL,
PRIMARY KEY(Id)
);
INSERT INTO employee VALUES(NULL,'NAME','SURNAME','DEPARTMENT','POSITION','0.00','0.0');
INSERT INTO employee VALUES(NULL,'Joe','Mullins','Engineering','Lecturer','63.08','12.0');
INSERT INTO employee VALUES(NULL,'Joan','Macgill','Science','Researcher','38.00','35.0');
INSERT INTO employee VALUES(NULL,'Jim','Mitchell','Business','Researcher','38.00','25.0');
INSERT INTO employee VALUES(NULL,'John','Magner','Humanities','Lecturer','63.08','16.0');
INSERT INTO employee VALUES(NULL,'Jean','Madden','Design','Professor','76.45','14.0');
SELECT * FROM employee;