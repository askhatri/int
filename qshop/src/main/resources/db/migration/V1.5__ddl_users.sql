DROP table if exists q_shop.users CASCADE;

CREATE TABLE q_shop.users
(
    id           INT          NOT NULL AUTO_INCREMENT,
    phone_number VARCHAR(32)  NOT NULL,
    password     VARCHAR(128) NOT NULL,
    email        VARCHAR(128) NULL,
    first_name   VARCHAR(64)  NULL,
    last_name    VARCHAR(64)  NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE
);