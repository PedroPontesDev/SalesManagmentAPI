CREATE TABLE tb_clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255),
    cpf BIGINT,
    sale_id BIGINT,
    FOREIGN KEY (sale_id) REFERENCES tb_sales(id)
);

