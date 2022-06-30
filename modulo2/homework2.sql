CREATE TABLE VEM_SER.PAIS(
	ID_PAIS NUMBER(38) NOT NULL,
	NOME VARCHAR(50) NOT NULL,
	PRIMARY KEY(ID_PAIS)
);


CREATE TABLE VEM_SER.ESTADO(
	ID_ESTADO NUMBER(38) NOT NULL,
	ID_PAIS NUMBER(38) NOT NULL,
	NOME VARCHAR2(50) NOT NULL,
	PRIMARY KEY(ID_ESTADO),
	CONSTRAINT FK_PAIS FOREIGN KEY (ID_PAIS) REFERENCES PAIS(ID_PAIS)
);


CREATE TABLE  VEM_SER.CIDADE(
	ID_CIDADE NUMBER(38) NOT NULL,
	ID_ESTADO NUMBER(38) NOT NULL,
	NOME VARCHAR2(50) NOT NULL,
	PRIMARY KEY(ID_CIDADE, ID_ESTADO),
	CONSTRAINT FK_ESTADO FOREIGN KEY (ID_ESTADO) REFERENCES ESTADO(ID_ESTADO)
);


CREATE TABLE VEM_SER.BAIRRO(
	ID_BAIRRO NUMBER(38) NOT NULL,
	ID_CIDADE NUMBER(38) NOT NULL,
	ID_ESTADO NUMBER(38) NOT NULL,
	NOME VARCHAR2(50) NOT NULL, 
	PRIMARY KEY(ID_BAIRRO, ID_CIDADE),
	CONSTRAINT FK_CIDADE FOREIGN KEY (ID_CIDADE, ID_ESTADO) REFERENCES CIDADE(ID_CIDADE, ID_ESTADO)
);


CREATE TABLE VEM_SER.ENDERECO(
	ID_ENDERECO NUMBER(38) NOT NULL,
	ID_BAIRRO NUMBER(38) NOT NULL, 
	ID_CIDADE NUMBER(38) NOT NULL,
	LOGRADOURO VARCHAR2(255) NOT NULL,
	NUMERO NUMBER(38) NOT NULL,
	COMPLEMENTO VARCHAR2(100),
	CEP CHAR(9),
	PRIMARY KEY(ID_ENDERECO),
	CONSTRAINT FK_BAIRRO FOREIGN KEY (ID_BAIRRO, ID_CIDADE) REFERENCES BAIRRO(ID_BAIRRO, ID_CIDADE)
);


CREATE SEQUENCE VEM_SER.SEQ_PAIS
 START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;


CREATE SEQUENCE VEM_SER.SEQ_ESTADO
 START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;


CREATE SEQUENCE VEM_SER.SEQ_CIDADE
 START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;


CREATE SEQUENCE VEM_SER.SEQ_BAIRRO
 START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;


CREATE SEQUENCE VEM_SER.SEQ_ENDERECO
 START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;


-- INSERTS TABELA PAIS
INSERT INTO VEM_SER.PAIS (ID_PAIS, NOME) VALUES(SEQ_PAIS.NEXTVAL, 'Brasil');


INSERT INTO VEM_SER.PAIS (ID_PAIS, NOME) VALUES(SEQ_PAIS.NEXTVAL, 'Argentina');



-- INSERTS TABELA ESTADO
INSERT INTO VEM_SER.ESTADO (ID_ESTADO, ID_PAIS, NOME) VALUES(SEQ_ESTADO.NEXTVAL, 1, 'Pernambuco');


INSERT INTO VEM_SER.ESTADO (ID_ESTADO, ID_PAIS, NOME) VALUES(SEQ_ESTADO.NEXTVAL, 1, 'Paraíba');


INSERT INTO VEM_SER.ESTADO (ID_ESTADO, ID_PAIS, NOME) VALUES(SEQ_ESTADO.NEXTVAL, 2, 'Buenos Aires');


INSERT INTO VEM_SER.ESTADO (ID_ESTADO, ID_PAIS, NOME) VALUES(SEQ_ESTADO.NEXTVAL, 2, 'Jujuy');


SELECT * FROM VEM_SER.ESTADO;



--INSERT TABELA CIDADES
INSERT INTO VEM_SER.CIDADE  (ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_CIDADE.NEXTVAL, 1, 'Recife');


INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_CIDADE.NEXTVAL, 1, 'Pesqueira');


INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_CIDADE.NEXTVAL, 2, 'João Pessoa');


INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_CIDADE.NEXTVAL, 2, 'Campina Grande');


INSERT INTO VEM_SER.CIDADE  (ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_CIDADE.NEXTVAL, 3, 'La Plata');


INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_CIDADE.NEXTVAL, 3, 'Quilmes');


INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_CIDADE.NEXTVAL, 4, 'Yavi');


INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_CIDADE.NEXTVAL, 4, 'Valle Grande');



--INSERT TABELA BAIRRO
INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 1, 1, 'Campo Grande');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 1, 1, 'Boa Viagem');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 2, 1, 'Prado');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 2, 1, 'Centro');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 3, 2, 'Bessa');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 3, 2, 'Bancários');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 4, 2, 'Catolé');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 4, 2, 'Alto Branco');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 5, 3, 'Almagro');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 5, 3, 'Villa Crespo');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 6, 3, 'Villa Azul');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 6, 3, 'Palermo Soho');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 7, 4, 'San Telmo');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 7, 4, 'Belgrano');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 8, 4, 'Olivos');


INSERT INTO VEM_SER.BAIRRO  (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME) VALUES(SEQ_BAIRRO.NEXTVAL, 8, 4, 'Vicente López');


SELECT * FROM VEM_SER.BAIRRO;



--INSERT TABELA ENDERECO
INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 1, 1, 'Rua 1', 10, 'Em frente a escola B', '55200-159');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 1, 1, 'Avenida 2', 11, 'Em frente ao hotel C', '55300-150');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 2, 1, 'arruda 3', 120, 'Em frente a escola B', '55200-789');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 2, 1, 'arruda 4', 110, 'Em frente ao hotel C', '55200-158');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 3, 2, 'Rua 1', 10, 'Em frente a escola B', '55200-000');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 3, 2, 'Avenida 2', 11, 'Em frente ao hotel D', '55400-000');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 4, 2, 'arruda 4', 120, 'Em frente a escola B', '51200-789');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 4, 2, 'Avenida 6', 110, 'Em frente ao hotel C', '53200-158');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 5, 3, 'Rua 27', 147, 'Em frente a escola F', '55215-000');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 5, 3, 'Avenida 2', 11, 'Em frente ao hotel D', '55400-000');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 6, 3, 'RUA Santa Cruz', 120, 'Em frente a escola J', '51252-900');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 6, 3, 'Avenida 6', 110, 'Em frente ao hotel C', '53200-158');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 7, 4, 'RUA Vitória', 127, 'Em frente a escola G', '51000-900');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 7, 4, 'avenida 19', 50, 'Em frente ao hotel C', '41200-158');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 8, 4, 'Rua 1', 10, 'Em frente a escola 29', '42200-159');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 8, 4, 'Avenida 55', 1195, 'Em frente ao hotel B', '55322-150');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 9, 5, 'arruda 1254', 562, 'Em frente a escola 22', '55248-789');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 9, 5, 'arruda 4', 110, 'Em frente ao hotel C', '55200-158');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 10, 5, 'Rua 13', 10, 'Em frente a escola B', '55211-000');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 10, 5, 'Avenida 23', 113, 'Em frente ao hotel D', '55400-111');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 11, 6, 'arruda 4', 120, 'Em frente a escola B', '51200-789');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 11, 6, 'Avenida 6', 110, 'Em frente ao hotel C', '53200-158');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 12, 6, 'Rua 27', 147, 'Em frente a escola F', '55215-000');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 12, 6, 'Avenida 2', 11, 'Em frente ao hotel D', '55400-000');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 13, 7, 'RUA Santa Cruz', 120, 'Em frente a escola J', '51252-900');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 13, 7, 'Avenida 6', 110, 'Em frente ao hotel C', '53200-158');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 14, 7, 'RUA Vitória', 127, 'Em frente a escola G', '51000-900');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 14, 7, 'avenida 19', 50, 'Em frente ao hotel C', '41200-158');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 15, 8, 'RUA Santa Cruz', 120, 'Em frente a escola J', '51252-900');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 15, 8, 'Avenida 6', 110, 'Em frente ao hotel C', '53200-158');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 16, 8, 'RUA Vitória', 127, 'Em frente a escola G', '51000-900');


INSERT INTO VEM_SER.ENDERECO  (ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES(SEQ_ENDERECO.NEXTVAL, 16, 8, 'avenida 19', 50, 'Em frente ao hotel C', '41200-158');



-- HOMEWORK2

-- SELECIONAR TODOS OS PAÍSES ORDENADOS POR NOME DESCRESCENTE
SELECT * 
  FROM VEM_SER.PAIS 
 ORDER BY NOME DESC;


--SELECIONAR LOGRADOURO E CEP. PORÉM SOMENTE OS LOGRADOUROS QUE COMECEM COM 'A' OU 'a'.
SELECT LOGRADOURO, CEP 
  FROM VEM_SER.ENDERECO 
 WHERE UPPER(LOGRADOURO) LIKE 'A%';
 

--SELECIONAR TODOS OS ENDEREÇOS QUE TENHAM CEP COM FINAL '0'
SELECT * 
  FROM VEM_SER.ENDERECO
 WHERE CEP LIKE '%0';


--SELECIONAR TODOS OS ENDEREÇOS QUE TENHAM NÚMEROS ENTRE 1 E 100;
SELECT * 
  FROM VEM_SER.ENDERECO 
 WHERE NUMERO BETWEEN 1 AND 100; 


--SELECIONAR TODOS OS ENDEREÇOS QUE COMECEM POR 'RUA' E ORDENAR PELO CEP DECRESCENTE
SELECT *
  FROM VEM_SER.ENDERECO
 WHERE LOGRADOURO LIKE 'RUA%' ORDER BY CEP DESC; 
 

--SELECIONAR A QUANTIDADE DE ENDEREÇOS CADASTRADOS NA TABELA
SELECT COUNT(ID_ENDERECO) 
  FROM VEM_SER.ENDERECO;


--SELECIONAR A QUANTIDADE DE ENDEREÇOS CADASTRADOS AGRUPADOS PELO ID DA CIDADE
SELECT COUNT(ID_ENDERECO)
  FROM VEM_SER.ENDERECO
 GROUP BY ID_CIDADE;