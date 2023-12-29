DROP table if exists q_shop.orders_items CASCADE;

CREATE TABLE q_shop.orders_items
(
    id         BIGINT         NOT NULL AUTO_INCREMENT,
    order_id   INT            NULL,
    product_id INT            NULL,
    quantity   INT            NULL,
    price      DECIMAL(15, 2) NULL,
    PRIMARY KEY (id),
    INDEX orders_items_orders_idx (order_id ASC) VISIBLE,
    INDEX order_items_products_idx (product_id ASC) VISIBLE,
    CONSTRAINT orders_items_orders
        FOREIGN KEY (order_id)
            REFERENCES q_shop.orders (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT order_items_products
        FOREIGN KEY (product_id)
            REFERENCES q_shop.products (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);