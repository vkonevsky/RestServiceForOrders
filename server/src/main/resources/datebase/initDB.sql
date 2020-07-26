CREATE TABLE public.users
(
    id bigserial PRIMARY KEY NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status VARCHAR(25) DEFAULT 'ACTIVE' NOT NULL
);
CREATE UNIQUE INDEX users_id_uindex ON public.users (id);
CREATE UNIQUE INDEX users_username_uindex ON public.users (username);
CREATE UNIQUE INDEX users_email_uindex ON public.users (email);


CREATE TABLE public.roles
(
    id bigserial PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status VARCHAR(25) DEFAULT 'ACTIVE' NOT NULL
);
CREATE UNIQUE INDEX users_id_uindex ON public.users (id);

CREATE TABLE public.user_roles
(
    user_id BIGINT,
    role_id BIGINT,
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_roles_roles FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

CREATE TABLE public.orders
(
    "id" bigserial PRIMARY KEY NOT NULL,
    user_id BIGINT NOT NULL,
    description VARCHAR(255) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(25) DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users (id)
);