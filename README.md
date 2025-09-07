# Sistema de Agendamento de Aulas

Um sistema completo para agendamento de aulas particulares, desenvolvido com Spring Boot no backend e Next.js no frontend. O sistema permite que alunos agendem aulas com professores, com recursos de notificações em tempo real, autenticação segura e interface moderna.

## 🚀 Funcionalidades Principais

### Para Alunos
- **Cadastro e Login**: Sistema de autenticação com JWT
- **Busca de Professores**: Visualização de professores disponíveis com informações sobre matérias e valores
- **Agendamento de Aulas**: Interface intuitiva para agendar aulas presenciais ou online
- **Gerenciamento de Aulas**: Visualização e cancelamento de aulas agendadas
- **Notificações**: Recebimento de notificações sobre confirmações e cancelamentos
- **Dashboard Personalizado**: Resumo de aulas e informações importantes

### Para Professores
- **Perfil Profissional**: Configuração de matérias, valores por hora e disponibilidade
- **Gerenciamento de Aulas**: Visualização de todas as aulas agendadas pelos alunos
- **Sistema de Notificações**: Notificações instantâneas sobre novos agendamentos
- **Controle de Disponibilidade**: Possibilidade de alterar status de disponibilidade

### Recursos Técnicos
- **Autenticação JWT**: Sistema seguro de autenticação e autorização
- **Notificações em Tempo Real**: Sistema completo de notificações com contadores e marcação de lidas
- **Interface Responsiva**: Design moderno com Tailwind CSS
- **API RESTful**: Backend robusto com Spring Boot
- **Validação de Dados**: Validação tanto no frontend quanto no backend

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21**: Linguagem de programação principal
- **Spring Boot**: Framework principal para desenvolvimento da API
- **Spring Security**: Autenticação e autorização com JWT
- **Spring Data JPA**: Persistência de dados com Hibernate
- **H2/MySQL**: Sistema de banco de dados
- **Gradle**: Gerenciamento de dependências

### Frontend
- **Next.js 14**: Framework React para desenvolvimento web
- **TypeScript**: Linguagem de programação para maior segurança de tipos
- **Tailwind CSS**: Framework CSS para estilização
- **Lucide React**: Biblioteca de ícones
- **React Hooks**: Gerenciamento de estado e efeitos

## 📋 Estrutura do Projeto

```
sistema-agendamento-aulas/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/backend/
│   │   │   │   ├── controller/         # Controllers da API REST
│   │   │   │   │   ├── AulaController.java
│   │   │   │   │   ├── UsuarioController.java
│   │   │   │   │   ├── ProfessorController.java
│   │   │   │   │   └── NotificacaoController.java
│   │   │   │   ├── domain/             # Entidades JPA
│   │   │   │   │   ├── Usuario.java
│   │   │   │   │   ├── Professor.java
│   │   │   │   │   ├── Aula.java
│   │   │   │   │   └── Notificacao.java
│   │   │   │   ├── repository/         # Repositórios JPA
│   │   │   │   ├── service/            # Lógica de negócio
│   │   │   │   ├── security/           # Configurações de segurança
│   │   │   │   └── BackendApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/                       # Testes automatizados
│   └── build.gradle                    # Configuração do Gradle
├── frontend/
│   ├── src/
│   │   ├── app/                        # Páginas do Next.js
│   │   │   ├── dashboard/              # Dashboard principal
│   │   │   ├── login/                  # Página de login
│   │   │   ├── register/               # Página de cadastro
│   │   │   ├── professores/            # Área dos professores
│   │   │   └── aulas/                  # Gerenciamento de aulas
│   │   ├── components/                 # Componentes React reutilizáveis
│   │   │   ├── ProfileSection.tsx
│   │   │   ├── AulaCard.tsx
│   │   │   └── NotificationDropdown.tsx
│   │   ├── hooks/                      # Hooks personalizados
│   │   │   ├── useAuth.ts
│   │   │   ├── useNotifications.ts
│   │   │   └── useAulas.ts
│   │   ├── types/                      # Definições de tipos TypeScript
│   │   └── styles/                     # Estilos CSS
│   ├── tailwind.config.js
│   └── package.json
├── .gitignore
└── README.md
```

## 🔧 Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- **Java 21 ou superior** (JDK)
- **Gradle 8.0+**
- **Node.js 18.0+**
- **npm ou yarn**
- **Git**

## 🚀 Instalação e Configuração

### 1. Clone o Repositório

```bash
git clone <url-do-repositorio>
cd sistema-agendamento-aulas
```

### 2. Configuração do Backend

```bash
cd backend

# Instalar dependências e compilar
./gradlew build

# Executar o servidor backend
./gradlew bootRun
```

O backend estará disponível em `http://localhost:8080`

### 3. Configuração do Frontend

```bash
cd frontend

# Instalar dependências
npm install

# Executar o servidor de desenvolvimento
npm run dev
```

O frontend estará disponível em `http://localhost:3000`

## 🎯 Como Usar

### Primeiro Acesso

1. **Cadastre-se** como aluno ou professor em `/register`
2. **Faça login** em `/login`
3. **Complete seu perfil** (especialmente professores devem configurar matérias e valores)

### Para Alunos

1. Acesse o **Dashboard** para ver a visão geral
2. Vá para **Professores** para ver todos os professores disponíveis
3. **Agende uma aula** clicando no professor desejado
4. Acompanhe suas aulas em **Minhas Aulas**
5. Receba **notificações** sobre confirmações e atualizações

### Para Professores

1. Configure seu **perfil profissional** em Configurações
2. Visualize **aulas agendadas** pelos alunos
3. Receba **notificações** sobre novos agendamentos
4. Gerencie sua **disponibilidade**

## 🔐 Autenticação

O sistema utiliza JWT (JSON Web Tokens) para autenticação:

- **Tokens** são armazenados no localStorage do navegador
- **Autorização** baseada em roles (ALUNO/PROFESSOR)
- **Middleware** de autenticação em todas as rotas protegidas
- **Expiração automática** dos tokens por segurança

## 📊 API Endpoints

### Usuários
- `POST /api/usuarios/register` - Cadastro de usuário
- `POST /api/usuarios/login` - Login de usuário
- `GET /api/usuarios/profile` - Perfil do usuário logado

### Professores
- `GET /api/professores` - Listar professores
- `PUT /api/professores/{id}` - Atualizar perfil do professor

### Aulas
- `POST /api/aulas` - Agendar aula
- `GET /api/aulas/aluno` - Aulas do aluno
- `GET /api/aulas/professor` - Aulas do professor
- `DELETE /api/aulas/{id}` - Cancelar aula

### Notificações
- `GET /api/notificacoes` - Listar notificações
- `GET /api/notificacoes/count` - Contar não lidas
- `PUT /api/notificacoes/{id}/lida` - Marcar como lida

## 🎨 Interface e Design

- **Design Responsivo**: Funciona perfeitamente em desktop e mobile
- **Componentes Reutilizáveis**: Arquitetura modular com componentes React
- **Feedback Visual**: Loading states, notificações toast e validações em tempo real
- **Acessibilidade**: Implementada seguindo boas práticas de UX/UI

## 🔄 Fluxo de Dados

1. **Autenticação**: Login → Token JWT → Armazenamento local
2. **Agendamento**: Aluno seleciona professor → Preenche dados → API cria aula → Notificação enviada
3. **Notificações**: Sistema cria notificação → Frontend busca periodicamente → Atualiza contador
4. **Tempo Real**: Hooks personalizados mantêm dados sincronizados

## 🧪 Testes

```bash
# Backend
cd backend
./gradlew test

# Frontend
cd frontend
npm run test
```

## 🚀 Deploy

### Backend
```bash
./gradlew build
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
npm run build
npm start
```

## 📝 Próximas Funcionalidades

- [ ] Sistema de avaliações de professores
- [ ] Calendário visual para agendamentos
- [ ] Integração com sistemas de pagamento
- [ ] Chat em tempo real entre aluno e professor
- [ ] Relatórios e analytics
- [ ] Notificações push

# TESTES -------------------------------------------------------------

# Testes Unitários no Backend

Este projeto inclui testes unitários para garantir a qualidade e o correto funcionamento da lógica de negócio na camada de serviço do backend. Utilizamos as seguintes tecnologias para a suíte de testes:

* **JUnit 5:** O framework padrão para a escrita de testes em Java e no ecossistema Spring Boot.
* **Mockito:** Uma biblioteca para a criação de "mocks" (objetos dublês), que nos permite testar uma classe de forma isolada, simulando o comportamento de suas dependências (como o repositório).

## O Que é Testado: `UsuarioServiceTest.java`

O principal arquivo de teste implementado é o `UsuarioServiceTest.java`, que foca em validar os métodos da classe `UsuarioService`. Atualmente, cobrimos os seguintes cenários para o método `atualizar`:

### 1. `deveAtualizarUsuarioComSucesso()`
Este teste valida o "caminho feliz", ou seja, o cenário em que a atualização de um usuário ocorre como esperado.

* **O que ele faz:**
    1.  **Prepara (Arrange):** Cria um objeto `Usuario` que simula um usuário já existente no banco de dados e um segundo objeto com os novos dados para a atualização.
    2.  **Simula (Arrange):** Usando o Mockito, instruímos o `UsuarioRepository` falso a retornar o usuário existente quando o método `findById()` for chamado.
    3.  **Executa (Act):** Chama o método `usuarioService.atualizar()`.
    4.  **Verifica (Assert):** Confere se o usuário retornado pelo serviço contém as informações novas (nome, email e senha) e se os métodos `findById()` e `save()` do repositório foram chamados exatamente uma vez.

### 2. `deveLancarExcecaoAoTentarAtualizarUsuarioInexistente()`
Este teste garante que a aplicação se comporte de maneira segura e previsível ao tentar atualizar um usuário que não existe no banco de dados.

* **O que ele faz:**
    1.  **Prepara e Simula (Arrange):** Configuramos o mock do `UsuarioRepository` para retornar um resultado vazio (`Optional.empty()`) ao tentar buscar um usuário com um ID específico (ex: 99L).
    2.  **Executa e Verifica (Act & Assert):** Tentamos chamar o método `usuarioService.atualizar()` com o ID inexistente e verificamos se ele lança a exceção esperada (`RuntimeException`).
    3.  **Verifica (Assert):** Confirmamos também que a mensagem de erro está correta e que o método `save()` do repositório **nunca** foi chamado, evitando operações indevidas no banco de dados.

## Como a Estrutura do Teste Funciona

Os testes seguem o padrão **Arrange, Act, Assert (AAA)**, que organiza o código de forma clara e legível:

* **`@Mock`**: Anotação do Mockito que cria uma versão falsa de uma dependência (ex: `UsuarioRepository`).
* **`@InjectMocks`**: Cria uma instância real da classe que queremos testar (ex: `UsuarioService`) e injeta automaticamente os `mocks` criados.
* **`when(...).thenReturn(...)`**: Função do Mockito que nos permite "programar" o comportamento do mock. Por exemplo: "**Quando** o método `findById` for chamado com o ID 1, **então retorne** nosso usuário de teste".

## Como Executar os Testes

Existem duas maneiras principais de executar os testes deste projeto:

### 1. Pela IDE (IntelliJ IDEA)

A forma mais simples durante o desenvolvimento.

1.  Navegue até o arquivo de teste: `src/test/java/com/example/backend/service/UsuarioServiceTest.java`.
2.  Clique no ícone de "play" (▶️) que aparece ao lado do nome da classe para rodar todos os testes do arquivo, ou ao lado de um método específico para rodar apenas aquele teste.
3.  Os resultados serão exibidos em um painel na parte inferior da IDE.
á compilar o código, executar todos os testes unitários e informar se a construção foi bem-sucedida (`BUILD SUCCESSFUL`). Um relatório detalhado em HTML será gerado em `backend/build/reports/tests/test/index.html`.

## Testes de Integração no Backend

Além dos testes unitários, o projeto também possui testes de integração. O objetivo principal é verificar se as diferentes camadas da aplicação (Controllers, Services, Repositories) funcionam corretamente em conjunto e se a aplicação consegue se conectar com sucesso a serviços externos, como o banco de dados PostgreSQL.

### Visão Geral

O principal teste de integração é o `DatabaseConnectionTest.java`. Sua função é servir como um "teste de saúde" (health check) fundamental para a aplicação, garantindo que:
1.  O contexto do Spring Boot consegue ser inicializado sem erros.
2.  A aplicação consegue se conectar ao banco de dados PostgreSQL usando as configurações fornecidas.
3.  A camada de persistência (JPA/Hibernate) está funcional e consegue se comunicar com os repositórios.

### O Teste `DatabaseConnectionTest.java`

Este teste foi criado para ser uma verificação simples e direta da conexão com o banco de dados.

* **O que ele faz:**
  1.  **Carrega a Aplicação:** A anotação `@SpringBootTest` instrui o Spring a carregar o contexto completo da aplicação, lendo o arquivo `application.properties` e criando todos os beans necessários (assim como na aplicação real).
  2.  **Injeta o Repositório:** A anotação `@Autowired` pede ao Spring para injetar uma instância funcional do `UsuarioRepository`. Para que isso funcione, o Spring obrigatoriamente precisa ter se conectado ao PostgreSQL e configurado toda a camada de persistência.
  3.  **Verifica a Injeção:** O teste `assertNotNull(usuarioRepository)` confirma que o repositório foi injetado com sucesso. Se este passo falhar, significa que houve um erro ao iniciar a aplicação ou conectar ao banco.
  4.  **Executa uma Consulta Real:** Como uma verificação final, o teste executa `usuarioRepository.count()`, que realiza uma consulta `SELECT COUNT(*)` real no banco de dados, provando que a comunicação está 100% funcional.

## Como Executar o Teste

Para executar este teste, é necessário um pré-requisito fundamental.

### **Pré-requisito Indispensável**

O servidor do banco de dados **PostgreSQL deve estar rodando e acessível** no endereço configurado em `src/main/resources/application.properties` (por padrão, `localhost:5432`).

### 1. Pela IDE (IntelliJ IDEA)

1.  Navegue até o arquivo de teste: `src/test/java/com/example/backend/DatabaseConnectionTest.java`.
2.  Clique no ícone de "play" (▶️) ao lado do nome da classe `DatabaseConnectionTest` para executá-lo.
3.  Os resultados serão exibidos no painel de testes da IDE.
O terminal exibirá a mensagem `BUILD SUCCESSFUL` se o teste passar.

# TESTE DE SISTEMA

Passo 1: Você inicia seu frontend e seu backend.

Passo 2: Abre o navegador no seu site (http://localhost:3000).

Passo 3: Você clica na página de cadastro, preenche os campos (nome, email, senha) e clica em "Cadastrar".

Passo 4: Você observa o resultado. Foi redirecionado para a página de login? Apareceu uma mensagem de sucesso?

Passo 5: Você tenta fazer login com os dados que acabou de cadastrar.

Passo 6: Deu certo? Você entrou na área logada do sistema?