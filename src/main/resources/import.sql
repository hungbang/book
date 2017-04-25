INSERT INTO role(role_key, role_type) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role(role_key, role_type) VALUES (2, 'ROLE_USER');


INSERT INTO user(email, first_name, last_name, password, role_key) VALUES ('admin@test.test','Admin','Admin','123456', 1);


INSERT INTO book(title, author, date_create, date_update, description) VALUES ('A Programmer''s Guide to Java Certification','Khalid Mughal','2017-04-23 00:00:00','2017-04-23 00:00:00', 'Passing the Sun Certified Programmer for Java 2 Platform 1.4 exam (SCPJ2 1.4) is an important step in acquiring the high level of expertise essential for professional development. This book is written for any experienced programmer interested in mastering the Java programming language and passing the SCPJ2 1.4 exam.');