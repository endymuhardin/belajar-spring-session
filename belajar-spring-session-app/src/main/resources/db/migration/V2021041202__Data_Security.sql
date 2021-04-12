insert into s_role (id, nama) values ('s_user', 's_user');

-- password : user00
insert into s_user (id, id_role, username, hashed_password, nama)
values ('u001', 's_user', 'user001', '$2a$13$UHzktQVUWnzI46FqJBMVgunMPKWaxsvuKdHw5LWWsczDCvf.CtoQu', 'User 001');

-- password : user00
insert into s_user (id, id_role, username, hashed_password, nama)
values ('u002', 's_user', 'user002', '$2a$13$UHzktQVUWnzI46FqJBMVgunMPKWaxsvuKdHw5LWWsczDCvf.CtoQu', 'User 002');

-- password : user00
insert into s_user (id, id_role, username, hashed_password, nama)
values ('u003', 's_user', 'user003', '$2a$13$UHzktQVUWnzI46FqJBMVgunMPKWaxsvuKdHw5LWWsczDCvf.CtoQu', 'User 003');