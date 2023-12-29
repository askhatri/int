DROP TABLE IF EXISTS q_shop.users_roles CASCADE;

CREATE TABLE q_shop.users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    INDEX roles_crosstable_idx (role_id ASC) VISIBLE,
    CONSTRAINT users_crosstable
        FOREIGN KEY (`user_id`)
            REFERENCES q_shop.users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT roles_crosstable
        FOREIGN KEY (role_id)
            REFERENCES q_shop.roles (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
