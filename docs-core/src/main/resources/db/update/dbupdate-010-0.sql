insert into T_CONFIG(CFG_ID_C, CFG_VALUE_C) values('GUEST_LOGIN', 'true');
insert into T_USER(USE_ID_C, USE_IDROLE_C, USE_USERNAME_C, USE_PASSWORD_C, USE_EMAIL_C, USE_CREATEDATE_D, USE_PRIVATEKEY_C) values('guest', 'user', 'guest', '', 'guest@localhost', NOW(), 'GuestPk');
update T_CONFIG set CFG_VALUE_C = '10' where CFG_ID_C = 'DB_VERSION';

insert into T_MESSAGE (MSG_ID_C, MSG_USERNAME_C, MSG_EMAIL_C, MSG_PASSWORD_C, MSG_CREATEDATE_D) values ('test-msg-1', 'clown', 'clown@rednos.com', '$2y$10$xg0EEKVUehutDI1m6qQhVeFz7SMQMl1jQzjf2KkVsR2c7aV2vyyjK', NOW());
