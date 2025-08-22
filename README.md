📘 Autenticação e Autorização com Spring Security, JWT Tokens e Roles
Este projeto demonstra como implementar autenticação e autorização em uma aplicação Java usando Spring Security, JWT (JSON Web Tokens) e controle de acesso por roles.

👨‍🏫 Autor
João Keslei

🧱 Estrutura do Projeto
O projeto está organizado em camadas:

entity: contém as entidades do sistema (ex: Cadastro)
repository: interfaces para acesso ao banco de dados
service: lógica de negócio
controller: endpoints REST
security: configuração de autenticação e autorização
🔐 Segurança com Spring Security
Tela de login padrão (localhost:8080)
Geração de senha automática
Autenticação via JWT
Autorização baseada em roles (ROLE_USER, ROLE_ADMIN, etc.)
🧾 Funcionalidades
Cadastro de usuários
Login com geração de token JWT
Proteção de rotas com base em roles
Validação de token em cada requisição
Criação de serviços como AuthorizationService e AuthenticationController
🛠️ Tecnologias Utilizadas
Java 17
Spring Boot
Spring Security
JWT
Maven
PostgreSQL (opcional)
Docker (opcional)
