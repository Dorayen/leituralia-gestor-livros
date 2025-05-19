CREATE TABLE livro (
    id            VARCHAR(36) PRIMARY KEY,
    titulo        VARCHAR(255) NOT NULL,
    autor         VARCHAR(255) NOT NULL,
    categoria     VARCHAR(120),
    ano_publicacao VARCHAR(4)
);