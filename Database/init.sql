CREATE TABLE payment (
	id serial PRIMARY KEY,
	order_id BIGINT NOT NULL,
	card_authorization_info VARCHAR(50) NOT NULL,
	username VARCHAR(50)
);

GRANT ALL PRIVILEGES ON TABLE payment to example;
