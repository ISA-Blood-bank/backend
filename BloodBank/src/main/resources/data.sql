insert into address (city, country, number, street ) values ('Novi sad', 'Srbija', 3, 'narodnog fronta');
insert into address (city, country, number, street ) values ('Novi sad', 'Srbija', 6, 'bulevar oslobodjenja');
insert into registred_user (name, surname, jmbg, gender, email, password, address_id, occupation, job_or_school_info, points, category, penalties) values ('Tamara', 'Krgovic', '123345', 1, 'tamara@gmail.com', 'qwerty', 1, 'student', 'ftn', 0, 0, 0);
insert into registred_user (name, surname, jmbg, gender, email, password, address_id, occupation, job_or_school_info, points, category, penalties) values ('Darko', 'Cokic', '123455', 0, 'darko@gmail.com', 'qwdfgty', 2, 'student', 'ftn', 0, 0, 0);
insert into registred_user (name, surname, jmbg, gender, email, password, address_id, occupation, job_or_school_info, points, category, penalties) values ('Branka', 'Kljajic', '544545', 0, 'branka@gmail.com', 'qwdsfgty', 2, 'student', 'ftn', 0, 0, 0);
insert into medical_staff (name, surname, jmbg, gender, email, password, address_id, blood_center_id) values ('Marko', 'Markovic', '1243455', 0, 'marko@gmail.com', 'qwdfhihgty',1,1);
insert into blood_center (name, address_id, description, average_score) values ('Red cross', 1, 'good', 0);
insert into blood_center (name, address_id, description, average_score) values ('Blue cross', 1, 'nice', 0);
commit;

