CREATE TABLE tb_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    quantity INT,
    has_in_stock BOOLEAN,
    stock_id BIGINT,
    sale_id BIGINT,
    FOREIGN KEY (stock_id) REFERENCES tb_stock(id),
    FOREIGN KEY (sale_id) REFERENCES tb_sales(id)
);

