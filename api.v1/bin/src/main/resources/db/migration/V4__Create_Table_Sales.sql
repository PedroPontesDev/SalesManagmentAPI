CREATE TABLE tb_sales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    moment_of_sale TIMESTAMP,
    seller_who_sale_id BIGINT,
    client_who_buy_id BIGINT,
    value DOUBLE,
    FOREIGN KEY (seller_who_sale_id) REFERENCES tb_sellers(id),
    FOREIGN KEY (client_who_buy_id) REFERENCES tb_clients(id)
);

