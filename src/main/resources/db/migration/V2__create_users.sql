insert into hexauthority(name) values ('ROLE_ADMIN');
insert into hexauthority(name) values ('ROLE_USER');

insert into "hexuser"(username, password, email, full_name, registration_date, deleted, active)
values ('root', '$2y$12$TADQ9IEShO/4yreFVWZ04uOm0A6mhhVViPcOO2QW5wTul4H.Gcxyq', 'root@mail.com', 'Root', now(), false, true);

insert into user_authorities(user_id, authority_id)
select u.id as user_id, a.id as authority_id
from "hexuser" u, hexauthority a where
u.username = 'root' and a.name = 'ROLE_ADMIN';