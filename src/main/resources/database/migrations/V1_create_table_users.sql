CREATE TABLE IF NOT EXISTS users (
    user_id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(90) NOT NULL,
    user_password VARCHAR(200) NOT NULL,
    user_created_at timestamp without time zone NOT NULL
);
