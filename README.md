# ToDo Simple API

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen) ![Maven](https://img.shields.io/badge/Maven-4.0.0-red)

API REST simples para um aplicativo de lista de tarefas (To-Do), desenvolvida com Spring Boot. O projeto permite o gerenciamento completo de usuários e suas respectivas tarefas.

## ✨ Funcionalidades

-   **Gerenciamento de Usuários:**
    -   Criar um novo usuário.
    -   Buscar um usuário pelo ID.
    -   Atualizar a senha de um usuário.
    -   Deletar um usuário.
-   **Gerenciamento de Tarefas:**
    -   Criar uma nova tarefa para um usuário.
    -   Buscar uma tarefa pelo ID.
    -   Atualizar a descrição de uma tarefa.
    -   Deletar uma tarefa.
    -   Listar todas as tarefas de um usuário específico.

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

-   **Backend:**
    -   [**Java 17**](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
    -   [**Spring Boot 3.5.5**](https://spring.io/projects/spring-boot): Framework principal para a construção da aplicação.
    -   [**Spring Data JPA**](https://spring.io/projects/spring-data-jpa): Para persistência de dados e comunicação com o banco.
    -   [**Spring Web**](https://docs.spring.io/spring-framework/reference/web/webmvc.html): Para a criação dos endpoints REST.
    -   [**Spring Validation**](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#io.validation): Para a validação dos dados de entrada.
-   **Banco de Dados:**
    -   [**SQL Server**](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads): Banco de dados principal da aplicação.
    -   [**H2 Database**](https://www.h2database.com/html/main.html): Banco de dados em memória para execução dos testes.
-   **Ferramentas e Outros:**
    -   [**Maven**](https://maven.apache.org/): Gerenciador de dependências e build do projeto.
    -   [**Lombok**](https://projectlombok.org/): Para reduzir código boilerplate (getters, setters, construtores, etc.).
    -   [**ModelMapper**](http://modelmapper.org/): Para mapeamento de objetos (DTOs).

## 🚀 Começando

Siga as instruções abaixo para configurar e executar o projeto em seu ambiente local.

### Pré-requisitos

-   **JDK 17** ou superior.
-   **Maven** 3.8 ou superior.
-   Uma instância do **SQL Server** em execução.

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/nalandavictoria/todosimple-api.git
    cd todosimple-api
    ```

2.  **Configure o banco de dados:**
    Abra o arquivo `src/main/resources/application.properties` e altere as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com as credenciais do seu banco de dados SQL Server.

    ```properties
    # SQL Server
    spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=ToDoSimpleDB;encrypt=true;trustServerCertificate=true
    spring.datasource.username=sa
    spring.datasource.password=123
    ```

### Executando a Aplicação

Você pode executar a aplicação utilizando o Maven Wrapper incluído no projeto:
```bash
# No Linux ou macOS
./mvnw spring-boot:run

# No Windows
./mvnw.cmd spring-boot:run
````

A API estará disponível em `http://localhost:8080`.

## 📖 Documentação da API

Aqui estão os endpoints disponíveis na API.

### Recurso: Usuário (`/user`)

| Método | Endpoint     | Descrição                    |
| :----- | :----------- | :--------------------------- |
| `GET`  | `/user/{id}` | Busca um usuário pelo seu ID.  |
| `POST` | `/user`      | Cria um novo usuário.          |
| `PUT`  | `/user/{id}` | Atualiza um usuário existente. |
| `DELETE`| `/user/{id}` | Deleta um usuário.             |

#### Exemplo: `POST /user`

**Request Body:**

```json
{
    "name": "Nome do Usuário",
    "password": "umaSenhaForte"
}
```

## 📝 Recurso: Tarefa (`/task`)

| Método | Endpoint              | Descrição                               |
|:---|:---|:---|
| `GET`    | `/task/{id}`          | Busca uma tarefa pelo seu ID.           |
| `POST`   | `/task`               | Cria uma nova tarefa.                   |
| `PUT`    | `/task/{id}`          | Atualiza uma tarefa existente.          |
| `DELETE` | `/task/{id}`          | Deleta uma tarefa.                      |
| `GET`    | `/task/user/{userId}` | Lista todas as tarefas de um usuário.   |

### 📤 Exemplo: Criar uma nova tarefa

**POST** `/task`

**Request Body:**

```json
{
  "description": "Descrição da minha nova tarefa",
  "user": {
    "id": 1
  }
}
```
