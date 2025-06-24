# 🛒 DevStore

A **DevStore** é uma aplicação desenvolvida com base no modelo de domínio "Course", proposto pelo professor **Nélio Alves** no curso _"Java COMPLETO - Programação Orientada a Objetos"_. A partir dessa estrutura inicial, o projeto foi ampliado com a adoção de novas tecnologias e a implementação de funcionalidades adicionais, tornando-se uma aplicação mais robusta, atualizada e alinhada com as práticas utilizadas no desenvolvimento de sistemas modernos.

## 🎯 Objetivo

O principal objetivo da DevStore é fornecer uma **API REST simples e eficiente** para gerenciar produtos e usuários em um ambiente de e-commerce. O foco está em aplicar boas práticas de desenvolvimento backend, garantindo **segurança**, **organização do código** e **facilidade de manutenção**.

---

## 🧰 Tecnologias Usadas

- **Java 17**
- **Spring Boot 3.1.8**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring HATEOAS
- **H2 (para testes de persistência)**
- **MySQL**
- **PostgreSQL**
- **Lombok**
- **RestTemplate**
- **Hibernate Validator**
- **JWT** (com a biblioteca [java-jwt](https://github.com/auth0/java-jwt), sem OAuth2)
- **SpringDoc OpenAPI** (Swagger)

---

## ✅ Funcionalidades

- **CRUD de produtos**: Permite a criação, leitura, atualização e exclusão de produtos.

- **Associação entre produtos e categorias**: Organiza os produtos em categorias, facilitando o gerenciamento.

- **Cadastro e autenticação de usuários com JWT**: Garante que os usuários possam se registrar e fazer login de maneira segura.

- **Documentação da API com Swagger/OpenAPI**: Interface interativa para explorar e testar os endpoints da API.

- **Integração com FakeAPI**: A aplicação consome dados de uma **FakeAPI** usando **`RestTemplate`** para obter informações de produtos, e esses produtos são salvos na base de dados local, permitindo que eles sejam gerenciados junto aos produtos criados diretamente.

- **HATEOAS**: Utilização do **Spring HATEOAS** para adicionar links dinâmicos nas respostas da API, facilitando a navegação entre recursos.


---

## 🌍 Integração com FakeAPI

A aplicação realiza a **importação de produtos** a partir de uma **FakeAPI** utilizando o **`RestTemplate`**. O processo funciona da seguinte forma:

1. **Consumindo a FakeAPI**: A aplicação faz uma requisição HTTP GET para a FakeAPI, que fornece dados de produtos fictícios.

2. **Transformação e Mapeamento**: Os dados recebidos da FakeAPI são mapeados para **DTOs (Data Transfer Objects)** e, em seguida, convertidos para **entidades JPA**.

3. **Persistência**: Os produtos recebidos são salvos na **base de dados local** para serem gerenciados com os produtos cadastrados diretamente pelo usuário.

---

## 🐳 Configuração do Ambiente com Docker
Para facilitar a execução do banco de dados PostgreSQL e da ferramenta de administração pgAdmin, o projeto utiliza o **Docker Compose**.
### Como subir os serviços:
Na raiz do projeto, onde está o arquivo `docker-compose.yml`, execute:
```bash
docker-compose up -d
```
---

## 📁 Estrutura do Projeto

- **`src/`**
  - **`main/`**
    - **`java/`**
      - **`com.domain.devstore/`**
        - **`configurations/`** — Arquivos de configuração (segurança, Swagger, etc.)
        - **`controllers/`** — Controladores REST (endpoints)
        - **`dto/`** — Objetos de transferência de dados
        - **`entities/`** — Entidades JPA (modelo de domínio)
        - **`exceptions/`** — Tratamento de exceções personalizadas
        - **`filters/`** — Filtros de autenticação/autorização (ex: JWT)
        - **`mapper/`** — Conversões entre entidades e DTOs
        - **`repositories/`** — Interfaces para acesso ao banco (JPA)
        - **`services/`** — Regras de negócio e lógica da aplicação
        - **`DevstoreApplication.java`** — Classe principal da aplicação (Spring Boot)
    - **`resources/`**
      - **`application.yaml`** — Configurações da aplicação

- **`DevStore.postman_collection.json`** — Arquivo com os endpoints da aplicação
- **`pom.xml`** — Arquivo de configuração do Maven
- **`README.md`** — Documentação do projeto

---

## 📄 Documentação da API

A documentação interativa da API está disponível via **Swagger**. Você pode acessá-la através do seguinte link:

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

*Nota: Substitua o `localhost:8080` pelo endereço da sua aplicação se estiver rodando em outro ambiente.*

---

