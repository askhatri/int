DROP table if exists q_shop.products CASCADE;

CREATE TABLE q_shop.products
(
    id          INT            NOT NULL AUTO_INCREMENT,
    title       VARCHAR(256)   NULL,
    description VARCHAR(4096)  NULL,
    price       DECIMAL(15, 2) NULL,
    category_id INT            NULL,
    PRIMARY KEY (id),
    INDEX fk_products_categories_idx (category_id ASC) VISIBLE,
    CONSTRAINT fk_products_categories
        FOREIGN KEY (category_id)
            REFERENCES q_shop.categories (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);