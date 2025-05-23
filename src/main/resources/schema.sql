CREATE TABLE livro (
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo        VARCHAR(255) NOT NULL,
    autor         VARCHAR(255) NOT NULL,
    categoria     VARCHAR(120),
    ano_publicacao VARCHAR(4)
);