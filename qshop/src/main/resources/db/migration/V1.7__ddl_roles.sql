DROP table if exists q_shop.roles CASCADE;

CREATE TABLE q_shop.roles
(
    id   INT          NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE
);

CREATE TABLE `q_shop`.`users_roles` (
                                        `user_id` INT NOT NULL,
                                        `role_id` INT NOT NULL,
                                        PRIMARY KEY (`user_id`, `role_id`),
                                        INDEX `roles_cross(users_roles)_idx` (`role_id` ASC) VISIBLE,
                                        CONSTRAINT `users_cross(users_roles)`
                                            FOREIGN KEY (`user_id`)
                                                REFERENCES `q_shop`.`users` (`id`)
                                                ON DELETE NO ACTION
                                                ON UPDATE NO ACTION,
                                        CONSTRAINT `roles_cross(users_roles)`
                                            FOREIGN KEY (`role_id`)
                                                REFERENCES `q_shop`.`roles` (`id`)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE);
