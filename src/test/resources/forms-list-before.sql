delete from form;

insert into form(id, date, message, subject, user_id) values
(1,'2021-03-01', 'first', 'hp', 1),
(2,'2021-03-01', 'second', 'samsung', 1),
(3,'2021-03-01', 'third', 'lenovo', 1),
(4,'2021-03-01', 'fourth', 'iphone', 1);

alter table form AUTO_INCREMENT 10;