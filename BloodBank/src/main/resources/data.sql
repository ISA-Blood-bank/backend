insert into address (city, country, number, street ) values ('Novi sad', 'Srbija', 3, 'narodnog fronta');
insert into address (city, country, number, street ) values ('Beograd', 'Srbija', 6, 'bulevar oslobodjenja');
insert into address (city, country, number, street ) values ('Kragujevac', 'Srbija', 6, 'bulevar patrijarha pavla');
insert into registred_user (name, surname, jmbg, gender, email, password, address_id, occupation, job_or_school_info, points, category, penalties, phone, enabled, last_password_reset_date) values ('Tamara', 'Krgovic', '123345', 1, 'tamara@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 1, 'student', 'ftn', 0, 0, 0, '06502345', true, '2017-10-01 21:58:58.508-07');
insert into registred_user (name, surname, jmbg, gender, email, password, address_id, occupation, job_or_school_info, points, category, penalties, phone, enabled, last_password_reset_date) values ('Darko', 'Cokic', '123455', 0, 'darko@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 2, 'student', 'ftn', 0, 0, 0, '06502345', true, '2017-10-01 21:58:58.508-07');
insert into registred_user (name, surname, jmbg, gender, email, password, address_id, occupation, job_or_school_info, points, category, penalties, phone, enabled, last_password_reset_date) values ('Branka', 'Kljajic', '544545', 0, 'branka@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 2, 'student', 'ftn', 0, 0, 0, '06502345', true, '2017-10-01 21:58:58.508-07');
insert into blood_center (name, address_id, description, average_score) values ('Red cross', 1, 'good', 3);
insert into blood_center (name, address_id, description, average_score) values ('Blue cross', 2, 'nice', 4);
insert into blood_center (name, address_id, description, average_score) values ('Novi Sad Blood Center', 1, 'really good', 4.4);
insert into medical_staff (name, surname, jmbg, gender, email, password, address_id, blood_center_id) values ('Marko', 'Markovic', '1243455', 0, 'marko@gmail.com', 'qwdfhihgty',1,1);

insert into medical_staff (name, surname, jmbg, gender, email, password, address_id, blood_center_id) values ('Pera', 'Peric', '0987654321', 0, 'marko@gmail.com', 'qwdfhihgty',3,1);
INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1); -- admin-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);


commit;

