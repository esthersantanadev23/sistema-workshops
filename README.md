# Sistema de Agendamento de Workshops

## Sobre o Projeto

O Sistema de Agendamento de Workshops é uma aplicação desenvolvida em Java para o gerenciamento de workshops, permitindo o cadastro, consulta, atualização e exclusão de informações.

O projeto utiliza o padrão arquitetural MVC (Model-View-Controller) e conta com uma interface web para gerenciamento dos workshops, além de uma API REST que permite acessar os dados por meio de requisições HTTP, utilizando o formato JSON.

## Tecnologias Utilizadas

- Java 21
- Maven
- Jakarta Servlet
- JSP
- HTML5 e CSS3
- MySQL
- Apache Tomcat 10
- Jackson (JSON)
- JUnit

## Arquitetura

O projeto utiliza o padrão MVC, organizado em camadas para facilitar a manutenção, a organização do código e a separação de responsabilidades.

- **Model:** representa as entidades e os dados do sistema.
- **View:** responsável pela interface visual, utilizando JSP, HTML e CSS.
- **Controller:** recebe as requisições e direciona as operações.
- **Service:** concentra as regras de negócio e validações.
- **DAO:** responsável pelo acesso e manipulação dos dados no banco de dados.

## Funcionalidades

### Sistema Web

- Cadastrar workshops.
- Listar workshops.
- Editar workshops.
- Excluir workshops.
- Buscar workshops por título ou local.
- Visualizar dashboard com informações resumidas:
  - Total de workshops cadastrados.
  - Próximos workshops.
  - Total de vagas disponíveis.

### API REST

- Listar todos os workshops.
- Consultar um workshop por ID.
- Cadastrar um novo workshop.
- Atualizar um workshop.
- Excluir um workshop.

## Banco de Dados

O projeto utiliza o MySQL com o banco de dados `sistema_workshops`.

A conexão é configurada por meio de variáveis de ambiente:

```env
DB_URL=jdbc:mysql://127.0.0.1:3306/sistema_workshops
DB_USER=workshops
DB_PASSWORD=sua_senha
```

## Como Executar o Projeto

### 1. Pré-requisitos

- Java 21 instalado.
- Maven instalado.
- MySQL instalado e configurado.
- Apache Tomcat 10.

### 2. Configurar o Banco de Dados

Crie o banco de dados MySQL:

```sql
CREATE DATABASE sistema_workshops;
```

Configure as credenciais de acesso no arquivo `.env`, localizado na raiz do projeto.

### 3. Compilar o Projeto

Na raiz do projeto, execute:

```bash
mvn clean package
```

O Maven irá compilar o projeto e gerar o arquivo WAR na pasta `target`.

### 4. Implantar no Tomcat

Copie o arquivo WAR gerado para a pasta `webapps` do Tomcat:

```bash
cp target/sistema-workshops.war apache-tomcat-10.1.61/webapps/
```

### 5. Iniciar o Tomcat

Execute:

```bash
./apache-tomcat-10.1.61/bin/startup.sh
```

A aplicação estará disponível em:

[http://localhost:8080/sistema-workshops/workshops](http://localhost:8080/sistema-workshops/workshops)

## API REST — Endpoints

### URL Base

```text
http://localhost:8080/sistema-workshops/api/workshops
```

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| GET | `/api/workshops` | Listar todos os workshops |
| GET | `/api/workshops/{id}` | Buscar workshop por ID |
| POST | `/api/workshops` | Cadastrar workshop |
| PUT | `/api/workshops/{id}` | Atualizar workshop |
| DELETE | `/api/workshops/{id}` | Excluir workshop |

## Exemplos de Requisições

### Listar Workshops

```http
GET /api/workshops
```

### Buscar Workshop por ID

```http
GET /api/workshops/2
```

### Cadastrar Workshop

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

### Atualizar Workshop

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

### Excluir Workshop

```http
DELETE /api/workshops/2
```

## Testes da API

Os endpoints foram testados utilizando comandos `curl` no terminal.

| Operação | Código HTTP | Resultado |
|---|---:|---|
| Listagem | 200 OK | Sucesso |
| Cadastro | 201 Created | Sucesso |
| Consulta por ID | 200 OK | Sucesso |
| Atualização | 200 OK | Sucesso |
| Exclusão | 204 No Content | Sucesso |
| Listagem após exclusão | 200 OK | Sucesso |

Os testes CRUD apresentaram os códigos HTTP esperados, validando o funcionamento das operações da API.

## Projeto Acadêmico

Projeto desenvolvido para fins acadêmicos, com o objetivo de aplicar conceitos de desenvolvimento web, arquitetura MVC, APIs REST, integração com banco de dados, organização em camadas e operações CRUD.