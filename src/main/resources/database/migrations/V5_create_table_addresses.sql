CREATE TABLE IF NOT EXISTS addresses (
    address_id BIGSERIAL PRIMARY KEY,
    address_zip_code VARCHAR(8) NOT NULL,
    address_street VARCHAR(60) NOT NULL,
    address_street_number VARCHAR(10) NOT NULL,
    address_district VARCHAR(60) NOT NULL,
    address_complement VARCHAR(60),
    address_city VARCHAR(60) NOT NULL,
    address_state VARCHAR(2) NOT NULL,
    address_created_at timestamp without time zone NOT NULL,
    address_updated_at timestamp without time zone NOT NULL,
    user_id bigint REFERENCES users(user_id)
);
