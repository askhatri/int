DROP table if exists q_shop.categories CASCADE;

CREATE TABLE q_shop.categories
(
    id    INT          NOT NULL AUTO_INCREMENT,
    title VARCHAR(256) NULL,
    PRIMARY KEY (id)
);