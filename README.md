# 🗪 ForumHub - API

API REST que fornece as funcionalidades de backend para um fórum de discussões, permitindo o gerenciamento de tópicos, cursos, usuários e autenticação segura.

## Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Banco de Dados:** MySQL
* **Persistência & Migrações:** Spring Data JPA e Flyway
* **Segurança:** Spring Security e Token JWT
* **Documentação:** SpringDoc OpenAPI (Swagger)
* **Facilitadores:** Lombok

## Funcionalidades

* **Autenticação (`/auth`):** Login de usuários e geração de token JWT.

Endpoints com CRUD completo (Cadastro, Listagem paginada, Detalhamento, Atualização e Exclusão):
* **Tópicos (`/topicos`)**
* **Respostas (`/topicos/{topicoId}/respostas`)**
* **Cursos (`/cursos`)** 
* **Usuários (`/usuarios`)**


## Como Executar

Para rodar a aplicação localmente, você precisa configurar as variáveis de ambiente utilizadas no arquivo `application.properties`. Configure-as na sua IDE ou no sistema operacional:

```env
DB_HOST=localhost:3306
DB_NAME=nome_do_banco
DB_USER=seu_usuario_mysql
DB_PASSWORD=sua_senha_mysql
JWT_SECRET=sua_chave_secreta_para_jwt
```

Após configurar o banco de dados e as variáveis, execute o projeto pela classe principal. As tabelas serão criadas automaticamente pelo Flyway.

## Documentação da API

A documentação interativa dos endpoints pode ser acessada com a aplicação rodando através do navegador:

* **Swagger UI:** `http://localhost:8080/swagger-ui.html`
