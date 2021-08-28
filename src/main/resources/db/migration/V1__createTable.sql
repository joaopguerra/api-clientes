CREATE TABLE IF NOT EXISTS tb_cliente
(
    id           bigint       NOT NULL AUTO_INCREMENT,
    razao_social varchar(100) NOT NULL,
    cnpj         varchar(100) NOT NULL,
    regime_tributario enum('SIMPLES_NACIONAL','LUCRO_PRESUMIDO') NOT NULL,
    email    varchar(100) NOT NULL,
    status    tinyint(1) DEFAULT NULL,
    PRIMARY KEY (id)
);