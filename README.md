# Sistema de Agendamento de Workshops

## Sobre o Projeto

O Sistema de Agendamento de Workshops é uma aplicação desenvolvida em Java para realizar o gerenciamento de workshops, permitindo o cadastro, consulta, atualização e exclusão de informações.

O projeto foi desenvolvido utilizando o padrão MVC e evoluiu de uma aplicação web para uma API REST, permitindo que os dados sejam acessados por meio de requisições HTTP e retornados no formato JSON.

## Tecnologias Utilizadas

- Java 21
- Maven
- Jakarta Servlet
- JSP
- HTML e CSS
- MySQL
- Apache Tomcat 10
- Jackson (JSON)
- JUnit

## Arquitetura

O projeto utiliza o padrão MVC (Model-View-Controller), organizado em camadas para facilitar a manutenção e a separação das responsabilidades.

- **Model:** representa as entidades e os dados do sistema.
- **View:** responsável pela interface visual utilizando JSP, HTML e CSS.
- **Controller:** recebe as requisições e direciona as operações.
- **Service:** concentra as regras de negócio e validações.
- **DAO:** responsável pelo acesso e manipulação dos dados no banco de dados.

## Funcionalidades

### Sistema Web

- Cadastrar workshops.
- Listar workshops.
- Editar workshops.
- Excluir workshops.

### API REST

- Listar todos os workshops.
- Consultar um workshop por ID.
- Cadastrar um novo workshop.
- Atualizar um workshop.
- Excluir um workshop.

## Banco de Dados

O projeto utiliza o MySQL com o banco de dados `sistema_workshops`.

A configuração de conexão é realizada por meio de variáveis de ambiente:

```env
DB_URL=jdbc:mysql://127.0.0.1:3306/sistema_workshops
DB_USER=workshops
DB_PASSWORD=sua_senha
```


##  Como Executar o Projeto

### 1. Pré-requisitos

- Java instalado
- Maven
- MySQL
- Apache Tomcat 10

### 2. Configurar o banco de dados

Crie o banco de dados MySQL:

```sql
CREATE DATABASE sistema_workshops;
```

Configure as credenciais de acesso no arquivo `.env`, localizado na raiz do projeto.

### 3. Compilar o projeto

Na raiz do projeto, execute:

```bash
mvn clean package
```

### 4. Deploy no Tomcat

Copie o arquivo WAR gerado para a pasta `webapps` do Tomcat:

```bash
cp target/sistema-workshops.war apache-tomcat-10.1.61/webapps/
```

### 5. Iniciar o Tomcat

```bash
./apache-tomcat-10.1.61/bin/startup.sh
```

A aplicação estará disponível em:

https://zany-rotary-phone-x5w5gw6wr69v26wwq-8080.app.github.dev/sistema-workshops/workshops

## API REST — Endpoints

Base URL:

```text
http://localhost:8080/sistema-workshops/api/workshops
```

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/workshops` | Listar todos os workshops |
| GET | `/api/workshops/{id}` | Buscar workshop por ID |
| POST | `/api/workshops` | Cadastrar workshop |
| PUT | `/api/workshops/{id}` | Atualizar workshop |
| DELETE | `/api/workshops/{id}` | Excluir workshop |

## Exemplos de Requisições

### Listar workshops

```http
GET /api/workshops
```

### Buscar workshop por ID

```http
GET /api/workshops/2
```

### Cadastrar workshop

```http
POST /api/workshops
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "titulo": "Workshop de Java",
  "descricao": "Introdução à programação Java",
  "data": "2026-12-10",
  "horario": "14:00:00",
  "local": "Laboratório 1",
  "vagas": 20
}
```

### Atualizar workshop

```http
PUT /api/workshops/2
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "titulo": "Workshop de Java - Atualizado",
  "descricao": "Workshop atualizado",
  "data": "2026-12-10",
  "horario": "15:00:00",
  "local": "Laboratório 2",
  "vagas": 30
}
```

### Excluir workshop

```http
DELETE /api/workshops/2
```

## Testes da API

Os endpoints foram testados utilizando comandos `curl` no terminal.

| Operação | Resultado esperado | Resultado obtido |
|---|---|---|
| Listagem | 200 OK | 200 OK |
| Cadastro | 201 Created | 201 |
| Consulta por ID | 200 OK | 200 |
| Atualização | 200 OK | 200 |
| Exclusão | 204 No Content | 204 |
| Listagem após exclusão | 200 OK | 200 — lista vazia |

Todos os testes CRUD realizados apresentaram os códigos HTTP esperados.

## Projeto Acadêmico

Projeto desenvolvido para fins acadêmicos, com o objetivo de aplicar conceitos de desenvolvimento web, arquitetura MVC, APIs REST, integração com banco de dados e operações CRUD.
