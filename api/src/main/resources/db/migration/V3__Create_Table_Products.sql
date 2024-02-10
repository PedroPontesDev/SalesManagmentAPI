CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    hasInStock BOOLEAN NOT NULL,
    stock_id BIGINT, -- Adicionando a coluna para a chave estrangeira
    FOREIGN KEY (stock_id) REFERENCES stocks(id)
);

