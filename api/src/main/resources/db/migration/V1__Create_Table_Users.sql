CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    fullName VARCHAR(255),
    salary DOUBLE, -- Adicionando o novo campo 'salary'
    user_type VARCHAR(255)
);

