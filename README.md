# Sync - Backend - Sistema de Gestão Industrial

## Visão Geral
O **Sync Backend** é uma API REST para gestão de produção industrial, responsável pelo cadastro, consulta e gerenciamento de:

- Máquinas
- Funcionários
- Setores/Departamentos
- Usuários e autenticação
- Relatórios de operação e produção

Desenvolvido em **Java Spring Boot** com **MySQL**, o backend fornece dados e regras de negócio consumidos pelo front-end do projeto Sync.

---

## Rotas Principais da API

### Rotas públicas (sem autenticação JWT)

| Método | Rota               | Descrição                                                |
|--------|--------------------|----------------------------------------------------------|
| POST   | `/login`           | Autenticação de usuário, geração de token JWT           |
| POST   | `/sign-in`         | Cadastro de usuário                                     |
| POST   | `/forgot-password` | Solicitação de redefinição de senha                     |
| POST   | `/reset-password`  | Redefinição de senha a partir de token/código           |

### Rotas de máquinas (`MachineController`)

Requerem token JWT com escopos adequados.

| Método | Rota                 | Descrição                                                   | Permissões                         |
|--------|----------------------|-------------------------------------------------------------|------------------------------------|
| POST   | `/machine`           | Cadastro de máquina                                         | `ADMIN`                            |
| GET    | `/machine/{id}`      | Detalhes de uma máquina por ID                             | `ADMIN`, `GERENTE`, `OPERADOR`     |
| PUT    | `/machine/{id}`      | Atualização de dados da máquina                            | `ADMIN`                            |
| DELETE | `/machine/{id}`      | Exclusão de máquina                                        | `ADMIN`                            |
| GET    | `/machine`           | Pesquisa paginada com filtros (nome, setor, status, etc.) | `ADMIN`, `GERENTE`, `OPERADOR`     |

### Rotas de funcionários (`EmployeeController`)

Também protegidas por JWT.

| Método | Rota                   | Descrição                                                     | Permissões                         |
|--------|------------------------|----------------------------------------------------------------|------------------------------------|
| POST   | `/employee`            | Cadastro de funcionário                                       | `ADMIN`                            |
| GET    | `/employee/{id}`       | Detalhes de um funcionário por ID                             | `ADMIN`, `GERENTE`, `OPERADOR`     |
| PUT    | `/employee/{id}`       | Atualização de dados do funcionário                           | `ADMIN`                            |
| DELETE | `/employee/{id}`       | Exclusão de funcionário                                       | `ADMIN`                            |
| GET    | `/employee`            | Pesquisa paginada com filtros (nome, matrícula, turno, etc.) | `ADMIN`, `GERENTE`, `OPERADOR`     |

Outros módulos (departamentos, setores, relatórios, etc.) seguem o mesmo padrão de:

- Controllers REST
- DTOs de entrada e saída
- Services com regras de negócio
- Repositórios JPA

---

## Tecnologias e Ferramentas

- Java 21
- Spring Boot 3
- Spring Web — criação das APIs REST
- Spring Data JPA — acesso e persistência de dados
- Spring Validation — validação de DTOs
- Spring Security + OAuth2 Resource Server — autenticação com JWT
- Lombok — redução de boilerplate (getters, construtores, etc.)
- MapStruct — mapeamento entre entidades e DTOs
- MySQL — banco de dados relacional
- springdoc-openapi — documentação automática (Swagger UI)
- JasperReports — geração de relatórios (PDF, etc.)
- Maven — gerenciamento de dependências e build

---

## Estrutura de Pastas

```text
src/
├── main/
│   ├── java/com/projeto/tcc/
│   │   ├── config/           # Configurações (Security, CORS, JWT, etc.)
│   │   ├── controller/       # Controllers REST (Machine, Employee, Public, etc.)
│   │   ├── dto/              # DTOs de entrada/saída
│   │   │   ├── entry/        # Dados recebidos na requisição
│   │   │   └── exit/         # Dados devolvidos na resposta
│   │   ├── entities/         # Entidades JPA
│   │   ├── repository/       # Interfaces JPA (Repositórios)
│   │   ├── service/          # Regras de negócio
│   │   ├── security/         # UserDetails, serviços de autenticação
│   │   ├── mapper/           # MapStruct mappers (MachineMapper, etc.)
│   │   └── TccApplication.java  # Classe principal (Spring Boot)
│   └── resources/
│       ├── application.yml   # Configurações de banco, CORS, JWT, etc.
│       └── templates/        # Relatórios Jasper (quando aplicável)
└── test/
    └── ...                   # Testes unitários e de integração
```

---

## Organização e Padrões do Projeto

### Arquitetura e Camadas

- Controller: recebe requisições HTTP, valida DTOs e delega para o service.
- Service: contém regras de negócio, orquestra repositórios, validações e integrações.
- Repository: interfaces JPA para acesso ao banco de dados.
- DTOs (entry/exit): separam modelos de domínio (entidades) dos contratos de API.
- Mapper (MapStruct): conversão automática entre entidades e DTOs.

### Boas Práticas

- Validação com Bean Validation (`@Valid`, `@NotNull`, etc.).
- Respostas consistentes com `ResponseEntity`.
- Paginação via `Page<T>` do Spring Data nas listagens.
- Segurança por escopo (`SCOPE_ADMIN`, `SCOPE_GERENTE`, `SCOPE_OPERADOR`).
- Não expor entidades diretamente na API (uso de DTOs).

---

## Configuração de Banco de Dados

As configurações principais ficam em `src/main/resources/application.yml`.

Exemplo genérico (ajuste para seu ambiente):

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tccdb
    username: seu_usuario
    password: sua_senha
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      hibernate.format_sql: true
```

Ajuste `url`, `username` e `password` conforme o ambiente local ou de produção. Não versione credenciais reais em repositórios públicos.

---

## Autenticação e Segurança

- Autenticação baseada em **JWT** (Resource Server).
- Chaves pública/privada configuradas em `application.yml`:
  - `jwt.private.key`: caminho para chave privada
  - `jwt.public.key`: caminho para chave pública
- Rotas públicas definidas em `ConfigSecurity`:
  - `/login`, `/sign-in`, `/forgot-password`, `/reset-password`
  - `/swagger-ui/**`, `/v3/api-docs/**`
- Demais rotas protegidas, com validação de escopos (`SCOPE_*`).

---

## Instruções de Uso

1. Clonar o repositório

   ```bash
   git clone https://seu-repo/sync-backend.git
   cd sync-backend
   ```

2. Configurar o banco de dados MySQL

   - Criar o banco:
     ```sql
     CREATE DATABASE tccdb;
     ```
   - Ajustar credenciais em `application.yml`.

3. Gerar chaves JWT (se ainda não existirem)

   - Gerar par de chaves RSA (privada e pública).
   - Colocar os arquivos na pasta `resources` ou caminho acessível.
   - Atualizar `jwt.private.key` e `jwt.public.key` em `application.yml`.

4. Rodar a aplicação

   ```bash
   mvn spring-boot:run
   ```

5. Acessar a documentação da API (Swagger/OpenAPI)

   - Geralmente disponível em:
     - `http://localhost:8080/swagger-ui/index.html`
     - `http://localhost:8080/v3/api-docs`

---

## Como Contribuir

### Padrões de Código

- Manter a separação em camadas: `controller`, `service`, `repository`, `dto`, `mapper`.
- Utilizar DTOs para entrada/saída, evitando expor entidades JPA.
- Implementar validação com Bean Validation em DTOs.
- Padronizar mensagens de erro e respostas HTTP.
- Manter classes pequenas, com responsabilidade única.

### Estrutura de Commits

```text
feat(machine): adiciona criação e listagem paginada de máquinas
feat(employee): implementa filtros de turno e setor na busca de funcionários
fix(security): ajusta CORS e escopos de autorização
refactor(dto): separa DTOs de entrada e saída para máquinas
docs(readme): atualiza documentação da API e rotas públicas
```

### Criando Novas Features

1. Criar entidades e repositórios JPA (se necessário).
2. Criar DTOs de entrada e saída.
3. Criar mappers com MapStruct.
4. Implementar service com regras de negócio e validações.
5. Expor a funcionalidade via controller REST.
6. Adicionar testes e, se aplicável, atualizar a documentação Swagger.

---

## Status do Projeto

- API base de autenticação: implementada (login, cadastro, recuperação de senha).
- Módulo de máquinas: CRUD completo com filtros e paginação.
- Módulo de funcionários: CRUD completo com filtros e paginação.
- Segurança / JWT / CORS: configurados e integrados com o front.
- Relatórios: em evolução com apoio de JasperReports.
- Documentação OpenAPI: disponível via Swagger UI.

---

## Integração com o Front-end Sync

- O front consome as rotas `/login`, `/machine`, `/employee`, entre outras.
- CORS configurado para aceitar domínios de desenvolvimento (`localhost`) e de produção (URL do front).
- O token JWT retornado pelo `/login` deve ser enviado no header `Authorization: Bearer <token>` em todas as chamadas protegidas.

---

> Para dúvidas, sugestões ou colaboração, entre em contato com o responsável pelo projeto.
