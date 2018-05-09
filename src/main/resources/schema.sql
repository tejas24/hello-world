CREATE TABLE Associates(
 AssociateId int AUTO_INCREMENT NOT NULL ,
 Name nvarchar(100) NULL,
 Phone nvarchar(100) NULL,
 Address nvarchar(100) NULL,
 PRIMARY KEY (AssociateId)
 );
 
 CREATE TABLE Specialization(
 id int AUTO_INCREMENT NOT NULL ,
 AssociateId int NOT NULL ,
 Name nvarchar(100) NULL,
 CONSTRAINT FK_Associate FOREIGN KEY (AssociateId)
   REFERENCES Associates(AssociateId),
   PRIMARY KEY (id)
 );