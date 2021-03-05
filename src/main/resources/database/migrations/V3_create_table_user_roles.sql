CREATE TABLE IF NOT EXISTS user_roles (
    user_id bigint REFERENCES users(user_id),
    role_id bigint REFERENCES roles(role_id),
    CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id)
);