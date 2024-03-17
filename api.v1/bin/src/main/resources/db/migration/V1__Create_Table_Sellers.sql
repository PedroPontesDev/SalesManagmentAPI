CREATE TABLE tb_sellers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    full_name VARCHAR(255),
    salary DOUBLE,
    sale_quantity_in_month INT
);

