-- SET FOREIGN_KEY_CHECKS=0;
delete from user_role;
delete from users;

insert into users(id, active, password, username) values
(1, true, '$2a$08$XUSWXBtHO7N4SLkv6VlXWeVguDlQryMS.u9igSmkc30eZyUYYJ25a', 'Cheslav'),
(2, true, '$2a$08$XUSWXBtHO7N4SLkv6VlXWeVguDlQryMS.u9igSmkc30eZyUYYJ25a', 'test');

insert into user_role(user_id, roles) values
(1, 'USER'), (1,'ADMIN'),
(2,'USER')

