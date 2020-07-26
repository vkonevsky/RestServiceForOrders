INSERT INTO public.roles(
	name)
	VALUES ('ROLE_USER');
INSERT INTO public.roles(
	name)
	VALUES ('ROLE_ADMIN');

INSERT INTO public.users(
	id, username, email, first_name, last_name, password)
	VALUES (1, 'test', 'test@test.com', 'testf', 'testl', '$2a$04$71e24oCbAM5GwX534isgjuZ8xnyC/AYxPaL2FtsjPErL1vc6NHnbq');

INSERT INTO public.user_roles(
	user_id, role_id)
	VALUES (1, 1);

INSERT INTO public.user_roles(
	user_id, role_id)
	VALUES (1, 2);

INSERT INTO public.orders(
	user_id, description)
	VALUES (1, 'test description');