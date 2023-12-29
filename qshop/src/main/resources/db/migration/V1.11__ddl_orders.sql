DROP table if exists q_shop.orders CASCADE;

CREATE TABLE q_shop.orders
(
    id      INT            NOT NULL AUTO_INCREMENT,
    user_id INT            NULL,
    price   DECIMAL(15, 2) NULL,
    PRIMARY KEY (id)
);