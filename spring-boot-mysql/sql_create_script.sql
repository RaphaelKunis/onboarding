create database db_example;
create user 'springuser'@'%' identified by 'ThePassword1234';
grant all on db_example.* to 'springuser'   -- gives all rights - needed for table creation
-- security fixes for just working with existing structures
revoke all on db_example.* from 'springuser'@'%';   -- revoke all right
grant select, insert, delete, update on db_example.* to 'springuser'@'%';