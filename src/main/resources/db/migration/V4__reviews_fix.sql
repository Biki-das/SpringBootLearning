DROP TABLE IF EXISTS reviews;

CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    comment VARCHAR(255),
    rating DECIMAL(38, 2) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6),
    deleted_at DATETIME(6),
    PRIMARY KEY (id),
    CONSTRAINT fk_review_order FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES products (id)
);
