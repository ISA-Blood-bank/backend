-- Lozinka za sva tri user-a je 123
insert into address (city, country, number, street ) values ('Novi sad', 'Srbija', 3, 'narodnog fronta');
insert into address (city, country, number, street ) values ('Beograd', 'Srbija', 6, 'bulevar oslobodjenja');
insert into address (city, country, number, street ) values ('Kragujevac', 'Srbija', 6, 'bulevar patrijarha pavla');
insert into registred_user (name, surname, jmbg, gender, email, password, address_id, occupation, job_or_school_info, points, category, penalties, phone, enabled, last_password_reset_date, weight) values ('Tamara', 'Krgovic', '123345', 1, 'tamara@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 1, 'student', 'ftn', 0, 0, 0, '06502345', true, '2017-10-01 21:58:58.508-07', 70);
insert into registred_user (name, surname, jmbg, gender, email, password, address_id, occupation, job_or_school_info, points, category, penalties, phone, enabled, last_password_reset_date,weight) values ('Darko', 'Cokic', '123455', 0, 'darko@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 2, 'student', 'ftn', 0, 0, 0, '06502345', true, '2017-10-01 21:58:58.508-07', 70);
insert into registred_user (name, surname, jmbg, gender, email, password, address_id, occupation, job_or_school_info, points, category, penalties, phone, enabled, last_password_reset_date, weight) values ('Branka', 'Kljajic', '544545', 1, 'branka@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 2, 'student', 'ftn', 0, 0, 0, '06502345', true, '2017-10-01 21:58:58.508-07', 70);

insert into blood_center (name, address_id, description, average_score) values ('Red cross', 1, 'good', 3);
insert into blood_center (name, address_id, description, average_score) values ('Blue cross', 2, 'nice', 4);
insert into blood_center (name, address_id, description, average_score) values ('Novi Sad Blood Center', 3, 'really good', 4.4);
insert into medical_staff (name, surname, jmbg, gender, email, password, address_id, blood_center_id) values ('Marko', 'Markovic', '1243455', 0, 'marko@gmail.com', 'qwdfhihgty',1,1);
insert into medical_staff (name, surname, jmbg, gender, email, password, address_id, blood_center_id) values ('Pera', 'Peric', '0987654321', 0, 'marko@gmail.com', 'qwdfhihgty',3,1);
INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_MEDSTAFF');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_NOTAUTH');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 3);

insert into appointment (start, duration, available, blood_center_id, medical_staff_id, version) values ('2023-10-01 21:58:58.508-07', 1, true, 1, 2, 0);
insert into appointment (start, duration, available, blood_center_id, medical_staff_id, version) values ('2023-10-02 21:58:58.508-07', 1, true, 1, 2, 0);
insert into appointment (start, duration, available, blood_center_id, medical_staff_id, version) values ('2021-10-03 21:58:58.508-07', 1, false, 1, 2, 0);
insert into appointment (start, duration, available, blood_center_id, medical_staff_id, version) values ('2020-10-03 21:58:58.508-07', 1, false, 1, 2, 0);

insert into questionnaire (question1, question2, question3, question4, question5, question6, question7, question8, question9, question10, question11, question12, question13, question14, question15, question16, question17, question18, question19, question20, question21, question22, question23, question24, question25, question26, date, registred_user_id) values (true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, '2022-10-01 21:58:58.508-07', 1)
insert into scheduled_appointment (appointment_id, user_id, passed, canceled) values (3, 1, true, false);
insert into scheduled_appointment (appointment_id, user_id, passed, canceled) values (4, 1, true, false);
insert into scheduled_appointment (appointment_id, user_id, passed, canceled) values (1, 1, false, false);
insert into appointment_report (appointment_id, content, can_give_blood) values (1, 'Super', true);
insert into appointment_report (appointment_id, content, can_give_blood) values (2, 'Ekstra', true);

insert into visits(registred_user_id, blood_center_id) values (1, 1)

insert into blood(type, quantity, blood_center_id) values (0, 500, 1);
insert into blood(type, quantity, blood_center_id) values (1, 500, 1);
insert into blood(type, quantity, blood_center_id) values (2, 500, 1);
insert into blood(type, quantity, blood_center_id) values (3, 500, 1);
insert into blood(type, quantity, blood_center_id) values (4, 500, 1);
insert into blood(type, quantity, blood_center_id) values (5, 500, 1);
insert into blood(type, quantity, blood_center_id) values (6, 500, 1);
insert into blood(type, quantity, blood_center_id) values (7, 500, 1);
commit;
