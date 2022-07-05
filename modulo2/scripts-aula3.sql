-- EXERCÍCIO 1
-- ATUALIIZAR LOGRADOURO E COMPLEMENTO ENDEREÇOS COM ID 2 E 3
UPDATE
  VEM_SER.ENDERECO 
SET
  LOGRADOURO = 'Rua Santa Águeda',
  COMPLEMENTO = 'Em frente à loja Delta'
WHERE
  ID_ENDERECO = 2 
 
  
UPDATE 
  VEM_SER.ENDERECO
SET 
  LOGRADOURO = 'Rua D79',
  COMPLEMENTO = 'Perto do bar de Lulu'
WHERE 
  ID_ENDERECO = 3
  

-- ATUALIZAR O NÚMERO DO ENDEREÇO ONDE ID = 4 PARA 999999
UPDATE
  VEM_SER.ENDERECO
SET
  NUMERO = 999999
WHERE
  ID_ENDERECO = 4 
  

-- REMOVER O ÚLTIMO REGISTRO DA TABELA ENDEREÇO (UTILIZANDO A FUNÇÃO MAX)
DELETE 
  FROM ENDERECO
WHERE ID_ENDERECO = (SELECT MAX(ID_ENDERECO) FROM ENDERECO)

  
-- REMOVER O ENDEREÇO ONDE O NÚMERO = 999999
DELETE
  FROM ENDERECO
WHERE NUMERO LIKE '%999999%'


-- REMOVER 2 REGISTROS DA TABELA ENDEREÇO
DELETE
  FROM ENDERECO
WHERE ID_ENDERECO = 31


DELETE
  FROM ENDERECO
WHERE ID_ENDERECO = 27



-- EXERCICIO 2
-- CROSS JOIN PESSOA E CONTATO
SELECT P.ID_PESSOA, P.NOME, C.NUMERO
  FROM PESSOA P
 CROSS JOIN CONTATO C
 

-- INNER JOIN PESSOA E CONTATO
 SELECT *
   FROM PESSOA P
  INNER JOIN CONTATO C ON P.ID_PESSOA = C.ID_CONTATO
  

  
 -- INNER JOIN PESSOA, PESSOA_X_PESSOA_ENDERECO , ENDERECO_PESSOA 
 SELECT *
   FROM PESSOA P
  INNER JOIN PESSOA_X_PESSOA_ENDERECO PXPE ON PXPE.ID_PESSOA = P.ID_PESSOA
  INNER JOIN ENDERECO_PESSOA EP ON EP.ID_ENDERECO = PXPE.ID_ENDERECO
  
  
-- INNER JOIN ENTR TODAS AS TABELAS (COMEÇANDO POR PESSOA)
SELECT *
  FROM PESSOA P
 INNER JOIN CONTATO C ON P.ID_PESSOA = C.ID_PESSOA
 INNER JOIN PESSOA_X_PESSOA_ENDERECO PXPE ON PXPE.ID_PESSOA = P.ID_PESSOA
 INNER JOIN ENDERECO_PESSOA EP ON EP.ID_ENDERECO = PXPE.ID_ENDERECO
 
 
 
-- LEFT JOIN PESSOA E CONTATO
SELECT *
  FROM PESSOA P
  LEFT JOIN CONTATO C ON P.ID_PESSOA = C.ID_CONTATO
  
  

-- LEFT JOIN PESSOA, PESSOA_X_PESSOA_ENDERECO , ENDERECO_PESSOA 
 SELECT *
   FROM PESSOA P
   LEFT JOIN PESSOA_X_PESSOA_ENDERECO PXPE ON PXPE.ID_PESSOA = P.ID_PESSOA
   LEFT JOIN ENDERECO_PESSOA EP ON EP.ID_ENDERECO = PXPE.ID_ENDERECO
  

   
-- LEFT JOIN ENTR TODAS AS TABELAS (COMEÇANDO POR PESSOA)
SELECT *
  FROM PESSOA P
  LEFT JOIN CONTATO C ON P.ID_PESSOA = C.ID_PESSOA
  LEFT JOIN PESSOA_X_PESSOA_ENDERECO PXPE ON PXPE.ID_PESSOA = P.ID_PESSOA
  LEFT JOIN ENDERECO_PESSOA EP ON EP.ID_ENDERECO = PXPE.ID_ENDERECO
  
  
 

  