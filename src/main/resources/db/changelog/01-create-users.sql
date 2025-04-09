CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Пароль: 123456 (захеширован c помощью BCrypt)
INSERT INTO users (username, password)
VALUES ('user', '$2a$12$MyGfPzNgs3F6LHo2ZHoLLulvF8PdZ2QyADXcmcVL4gEEcsO2kPazu');