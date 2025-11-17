# Sync - Backend (API Industrial Management System)

## Visão Geral
O **Sync Backend** é a API REST responsável pela gestão de máquinas, funcionários, departamentos e relatórios de produção industrial.  
Desenvolvido em **Java Spring Boot** e integrado ao **MySQL**, segue arquitetura limpa e profissional, servindo como base de dados do front-end do projeto.

---

## 🚀 Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- Lombok
- MySQL 8+
- MySQL Connector
- DevTools
- Postman

---

## 📁 Arquitetura do Projeto
src/
├── main/
│ ├── java/com/sync
│ │ ├── controllers/
│ │ ├── dtos/
│ │ ├── entities/
│ │ ├── repositories/
│ │ ├── services/
│ │ ├── config/
│ │ └── SyncApplication.java
│ └── resources/
│ ├── application.properties
│ └── data.sql
└── test/

yaml
Copiar código

---

## 🗄 Configuração do Banco de Dados
Arquivo: `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sync?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=SUASENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
Criação do banco:

sql
Copiar código
CREATE DATABASE sync;
▶ Como Executar
1. Clonar o repositório
bash
Copiar código
git clone https://github.com/seu-repo/sync-backend.git
2. Rodar o MySQL e criar o banco
sql
Copiar código
CREATE DATABASE sync;
3. Rodar a aplicação
bash
Copiar código
mvn spring-boot:run
Servidor disponível em:

arduino
Copiar código
http://localhost:8080
📦 Entidades Principais
Departamento
id

nome

orçamento

performance

Funcionário
id

nome

cargo

desempenho

departamento_id

Máquina
id

nome

setor

status

oee

vazao

departamento_id

🔗 Endpoints
Máquinas
Método	Rota	Descrição
GET	/maquinas	Lista todas
GET	/maquinas/{id}	Busca por ID
POST	/maquinas	Cadastra
PUT	/maquinas/{id}	Atualiza
DELETE	/maquinas/{id}	Remove

Funcionários
Método	Rota	Descrição
GET	/funcionarios	Lista
POST	/funcionarios	Cadastra
PUT	/funcionarios/{id}	Atualiza
DELETE	/funcionarios/{id}	Remove

Departamentos
Método	Rota	Descrição
GET	/departamentos	Lista
POST	/departamentos	Cadastra
PUT	/departamentos/{id}	Atualiza
DELETE	/departamentos/{id}	Remove

🧩 DER — Diagrama Conceitual
scss
Copiar código
DEPARTAMENTO (1) ---- (N) FUNCIONARIO
DEPARTAMENTO (1) ---- (N) MAQUINA
bash
Copiar código
[Departamento]
 id (PK)
 nome
 orcamento
 performance

            1:N

[Funcionario]
 id (PK)
 nome
 cargo
 desempenho
 departamento_id (FK)

            1:N

[Maquina]
 id (PK)
 nome
 setor
 status
 oee
 vazao
 departamento_id (FK)
🧱 Modelo Relacional (SQL)
sql
Copiar código
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
📌 Boas Práticas
Uso de DTOs

Controllers limpos

Services contendo regras de negócio

Respostas HTTP adequadas

Lombok para reduzir boilerplate

Código padronizado e organizado

📝 Padrão de Commits
scss
Copiar código
feat(maquinas): cria CRUD completo
feat(funcionarios): implementa validação
fix(departamentos): ajusta regra de negócio
refactor(api): separa responsabilidades
docs(readme): atualiza documentação
📊 Status do Projeto
✔ Concluído
CRUD completo

Integração MySQL

Testes via Postman

🚧 Em Desenvolvimento
Autenticação JWT

Endpoints de relatórios

Testes automatizados

📋 Próximos Passos
Implementação de JWT

KPIs industriais

Exportação de relatórios

Deploy final

📞 Contato
Para dúvidas e sugestões, entre em contato com o responsável pelo projeto.
