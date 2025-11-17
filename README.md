Sync - Backend (API Industrial Management System)
Visão Geral

O Sync Backend é a API REST responsável por gerenciar dados de máquinas, funcionários, departamentos e relatórios de produção industrial.
Desenvolvido em Java Spring Boot e integrado ao banco de dados MySQL, o sistema segue arquitetura limpa, separação de camadas e boas práticas profissionais.

Esta API fornece os endpoints necessários para que o front-end consuma e exiba métricas em tempo real sobre produção, desempenho e manutenção industrial.

Tecnologias Utilizadas
Backend

Java 17+

Spring Boot

Spring Web

Spring Data JPA

Spring Validation

Lombok

MySQL Connector

Spring DevTools

Banco de Dados

MySQL 8+

Ferramentas

Postman

MySQL Workbench

IntelliJ / VSCode / Eclipse

Arquitetura do Projeto
src/
├── main/
│   ├── java/com/sync
│   │   ├── controllers/      # Endpoints REST
│   │   ├── dtos/             # Objetos de transferência
│   │   ├── entities/         # Entidades JPA
│   │   ├── repositories/     # Interfaces DAO com JPA
│   │   ├── services/         # Regras de negócio
│   │   ├── config/           # Configurações (CORS, auth, etc.)
│   │   └── SyncApplication.java
│   └── resources/
│       ├── application.properties
│       └── data.sql          # Seeding inicial (opcional)
└── test/

Princípios da Arquitetura

MVC + Services

Responsabilidade única

DTOs para entrada e saída de dados

Regras isoladas no service

Repository padrão JPA

Controllers limpos e objetivos

Configuração do Banco de Dados

Arquivo: src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/sync?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=SUASENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Como Executar
1. Clonar o repositório
git clone https://github.com/seu-repo/sync-backend.git

2. Rodar o MySQL

Criar o banco:

CREATE DATABASE sync;

3. Iniciar o projeto
mvn spring-boot:run

4. Acessar
http://localhost:8080

Entidades Principais
Máquina

id

nome

setor

status

oee

vazao

manutencaoPreventiva

Funcionário

id

nome

cargo

departamento

turno

desempenho

Departamento

id

nome

orçamento

performance

totalFuncionarios

Endpoints
Autenticação
Método	Rota	Descrição
POST	/auth/login	Login
POST	/auth/register	Criar usuário
Máquinas
Método	Rota	Descrição
GET	/maquinas	Lista todas
GET	/maquinas/{id}	Busca ID
POST	/maquinas	Cadastra
PUT	/maquinas/{id}	Atualiza
DELETE	/maquinas/{id}	Remove
Funcionários
Método	Rota	Descrição
GET	/funcionarios	Listagem
POST	/funcionarios	Cadastro
PUT	/funcionarios/{id}	Atualização
DELETE	/funcionarios/{id}	Remoção
Departamentos
Método	Rota	Descrição
GET	/departamentos	Listagem
POST	/departamentos	Cadastro
PUT	/departamentos/{id}	Atualização
DELETE	/departamentos/{id}	Remoção
Diagrama Conceitual (DER)
DEPARTAMENTO (1) -------- (N) FUNCIONARIOS

DEPARTAMENTO (1) -------- (N) MAQUINAS

FUNCIONARIOS (N) -------- (N) MAQUINAS
(relacionamento opcional dependendo do escopo do TCC)


Representação:

[Departamento]
 ID (PK)
 nome
 orçamento
 performance

        1:N

[Funcionário]
 ID (PK)
 nome
 cargo
 desempenho
 dept_id (FK)

        1:N

[Máquina]
 ID (PK)
 nome
 setor
 status
 oee
 dept_id (FK)

Modelo de Dados Relacional (SQL)
CREATE TABLE departamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    orcamento DECIMAL(10,2),
    performance DECIMAL(5,2)
);

CREATE TABLE funcionario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(100),
    desempenho DECIMAL(5,2),
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES departamento(id)
);

CREATE TABLE maquina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    setor VARCHAR(100),
    status VARCHAR(50),
    oee DECIMAL(5,2),
    vazao DECIMAL(10,2),
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES departamento(id)
);

Boas Práticas do Projeto

Uso de DTOs para reduzir acoplamento

Services com regras de negócio isoladas

Controllers somente para fluxo de requisição

Validação com Bean Validation

Lombok para reduzir repetição

Respostas HTTP adequadas

Código limpo e organizado

Padrão de Commits
feat(maquinas): cria CRUD completo
feat(funcionarios): adiciona validação e DTOs
fix(departamentos): corrige regra de atualização
refactor(api): separa responsabilidades
docs(readme): adiciona documentação do backend

Status do Projeto
✔ Concluído

Estrutura do backend

Entidades principais

CRUD completo

Integração com MySQL

Testes no Postman

🚧 Em Desenvolvimento

Autenticação JWT

Endpoints de relatório

Testes unitários

Próximos Passos

Implementação de JWT

Dashboard com KPIs calculados

Exportação de relatórios

Deploy completo

Contato

Para dúvidas, sugestões ou contribuições, entre em contato com o responsável pelo projeto.
