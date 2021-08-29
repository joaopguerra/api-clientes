# Sobre 

- A aplicação consiste em uma API Rest para cadastro de empresas com intuito de criar, atualizar, deletar e consultar.

- CRUD básico desenvolvido utilizando Java / Spring e o banco de dados H2.

# Como rodar
- Clone o projeto: https://github.com/joaopguerra/api-clientes.git
- Rode a classe ApiClientesApplication.
#### Com o projeto rodando
- No Postman use como host o link http://localhost:8080
- Para visualizar o banco de dados no navegador entre em http://localhost:8080/h2-console/login.jsp
e clique em Connect.

`Pré-requisitos: Java 11`

# Operações disponíveis

- POST   /clientes |  Criar um cliente 
-  GET   clientes/id |  Listar um cliente por id 
-  GET   /clientes |  Listar todos os clientes 
-  PUT   /clientes/id |  Atualizar cliente 
-  DELETE   /clientes/id |  Deletar cliente 

# Documentação - Swagger
http://localhost:8080/swagger-ui.html#/

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
## Banco de dados
- H2
## Testes unitários
- jUnit 5
- Mockito


# Autor
João Guerra

https://www.linkedin.com/in/joaopguerra-/

