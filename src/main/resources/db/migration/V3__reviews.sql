CREATE TABLE IF NOT EXISTS reviews (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    product_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6),
    deleted_at DATETIME(6),
    PRIMARY KEY (id),
    CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES products (id)
);
