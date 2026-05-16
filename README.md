# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.14/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.14/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.14/reference/data/sql.html#data.sql.jpa-and-spring-data)
# CLYVO VET — Backend Java

API REST desenvolvida em Spring Boot para o sistema de saúde contínua de pets **CLYVO VET**, como parte do Challenge FIAP 2026 — 1º Semestre.

---

## Integrantes do Grupo

| Nome | RM |
|---|---|
| Fabrício Henrique Pereira | RM 563237 |
| Leonardo José Pereira | RM 563065 |
| Miguel Henrique Oliveira Dias | RM 565492 |
| Pedro Henrique de Oliveira | RM 562312 |

---

## Descrição do Projeto

O CLYVO VET é uma plataforma digital de saúde animal que conecta tutores de pets, veterinários e clínicas parceiras. A solução promove a continuidade do cuidado preventivo, centralizando o histórico clínico dos animais, o agendamento de eventos e o controle de pagamentos.

Este repositório contém o **backend Java/Spring Boot**, responsável por expor uma API REST completa com persistência em banco Oracle (FIAP) ou H2 (desenvolvimento local).

### Benefícios para o Negócio

- Centralização do histórico clínico do pet, acessível para tutores e clínicas
- Redução do gap de continuidade no cuidado preventivo
- Agendamento e controle de eventos clínicos com rastreabilidade completa
- Geração de dados longitudinais para decisões clínicas mais assertivas
- Escalável para múltiplas clínicas e hospitais parceiros

---

## Arquitetura Macro

```
┌─────────────┐     HTTP/REST      ┌──────────────────────────┐
│  Front-end  │ ────────────────►  │  Spring Boot API (8080)  │
│  / Mobile   │                    │                          │
└─────────────┘                    │  Controller              │
                                   │  Service (Cache)         │
                                   │  Repository (JPA)        │
                                   │  Entity (@Column map)    │
                                   └────────────┬─────────────┘
                                                │
                                   ┌────────────▼─────────────┐
                                   │   Oracle 19c (FIAP)      │
                                   │   ou H2 (dev local)      │
                                   └──────────────────────────┘
```

**Stack:** Java 17 · Spring Boot 3.5 · JPA/Hibernate 6.6 · Oracle 19c · H2 · Swagger/OpenAPI · Bean Validation · Spring Cache · Lombok

---

## Pré-requisitos

- Java 17+
- Maven 3.8+
- Acesso ao Oracle FIAP **ou** rodar localmente com H2

---

## Instalação e Execução

### 1. Clonar o repositório

```bash
git clone https://github.com/leojp04/clyvovet-backend-java.git
cd clyvovet-backend-java
```

### 2. Configurar o banco de dados

#### Opção A — Oracle FIAP (produção)

Antes de iniciar a aplicação, execute o script SQL no SQL Developer:

```
src/main/resources/db/db-oracle.sql
```

Este script cria as 6 tabelas e insere os dados de seed. Execute com **Run Script (F5)** conectado ao Oracle da FIAP com suas credenciais.

Depois, configure o `application.properties` com suas credenciais Oracle:

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=SEU_RM
spring.datasource.password=SUA_SENHA
```

#### Opção B — H2 (desenvolvimento local, sem Oracle)

Altere o `application.properties` para usar H2:

```properties
spring.datasource.url=jdbc:h2:mem:clyvovet
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

### 3. Executar

```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`

### 4. Acessar o Swagger

```
http://localhost:8080/swagger-ui.html
```

---

## Endpoints da API

### Tutores — `/tutores`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/tutores` | Lista todos (paginado). Filtros: `?nome=` `?cidade=` |
| GET | `/tutores/{id}` | Busca por ID |
| POST | `/tutores` | Cadastra novo tutor |
| PUT | `/tutores/{id}` | Atualiza tutor |
| DELETE | `/tutores/{id}` | Remove tutor |

### Animais — `/animais`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/animais` | Lista todos (paginado). Filtros: `?nome=` `?especie=` |
| GET | `/animais/{id}` | Busca por ID |
| POST | `/animais` | Cadastra novo animal |
| PUT | `/animais/{id}` | Atualiza animal |
| DELETE | `/animais/{id}` | Remove animal |

### Clínicas — `/clinicas`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/clinicas` | Lista todas (paginado). Filtros: `?nome=` `?cidade=` |
| GET | `/clinicas/{id}` | Busca por ID |
| POST | `/clinicas` | Cadastra nova clínica |
| PUT | `/clinicas/{id}` | Atualiza clínica |
| DELETE | `/clinicas/{id}` | Remove clínica |

### Veterinários — `/veterinarios`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/veterinarios` | Lista todos (paginado). Filtros: `?nome=` `?especialidade=` |
| GET | `/veterinarios/{id}` | Busca por ID |
| POST | `/veterinarios` | Cadastra novo veterinário |
| PUT | `/veterinarios/{id}` | Atualiza veterinário |
| DELETE | `/veterinarios/{id}` | Remove veterinário |

### Eventos Clínicos — `/eventos-clinicos`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/eventos-clinicos` | Lista todos (paginado). Filtros: `?tipoEvento=` `?animalNome=` |
| GET | `/eventos-clinicos/{id}` | Busca por ID |
| POST | `/eventos-clinicos` | Cadastra novo evento |
| PUT | `/eventos-clinicos/{id}` | Atualiza evento |
| DELETE | `/eventos-clinicos/{id}` | Remove evento |

Valores válidos para `tipoEvento`: `CONSULTA` `RETORNO` `VACINA` `EXAME` `CIRURGIA` `OUTRO`

### Pagamentos — `/pagamentos`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/pagamentos` | Lista todos (paginado, ordenado por data) |
| GET | `/pagamentos/{id}` | Busca por ID |
| POST | `/pagamentos` | Registra novo pagamento |
| PUT | `/pagamentos/{id}` | Atualiza pagamento |
| DELETE | `/pagamentos/{id}` | Remove pagamento |

---

## Exemplos de Uso

### Paginação e ordenação

```
GET /animais?page=0&size=5&sort=nome,asc
GET /tutores?nome=Lucas&page=0&size=10
GET /veterinarios?especialidade=Cardiologia
GET /eventos-clinicos?tipoEvento=VACINA
```

### Exemplo de POST — Criar Tutor

```json
POST /tutores
{
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao@email.com",
  "telefone": "(11) 99999-9999",
  "sexo": "MASCULINO",
  "dataNascimento": "1990-01-15",
  "endereco": {
    "logradouro": "Av. Paulista",
    "numero": "1000",
    "bairro": "Bela Vista",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "01310100"
  }
}
```

### Exemplo de POST — Criar Animal

```json
POST /animais
{
  "nome": "Rex",
  "especie": "CAO",
  "raca": "Pastor Alemão",
  "porte": "GRANDE",
  "cor": "Preto e marrom",
  "sexo": "MACHO",
  "dataNascimento": "2020-01-18",
  "observacao": "Cão de guarda",
  "tutorId": "uuid-do-tutor"
}
```

---

## Requisitos Técnicos Implementados

| Requisito | Status |
|---|---|
| Bean Validation nos Requests | ✅ |
| Paginação de resultados | ✅ todas as 6 entidades |
| Ordenação de resultados | ✅ todas as 6 entidades |
| Busca com parâmetros | ✅ todas as 6 entidades |
| Cache para otimizar requisições | ✅ todas as 6 entidades |
| Tratamento de erros/exceções | ✅ GlobalExceptionHandler + EntityNotFoundException |
| DTOs (Request/Response) | ✅ todas as 6 entidades |
| Documentação com Swagger | ✅ `/swagger-ui.html` |

---

## Estrutura do Projeto

```
src/main/java/br/com/fiap/clyvovet/
├── controller/       # Controllers REST (6 entidades)
├── service/          # Regras de negócio + cache
├── repository/       # JPA Repositories com queries JPQL
├── model/            # Entidades JPA
├── dto/              # DTOs de Request e Response
├── mapper/           # Conversão Entity ↔ DTO
├── exception/        # GlobalExceptionHandler
└── ClyvovetApplication.java

src/main/resources/
├── application.properties
└── db/
    └── db-oracle.sql  # Script de criação de tabelas + seed para Oracle FIAP
```

---

## Testando os Endpoints

A coleção de requisições para testes está disponível na pasta `documentos/` do repositório (exportação do Insomnia/Postman).

Você também pode testar diretamente pelo Swagger em:
```
http://localhost:8080/swagger-ui.html
```