# Discord Moderation Bot

Um bot de moderação para Discord desenvolvido em **Java** utilizando **JDA (Java Discord API)** e **Spring Boot**. Este projeto foi criado com foco em comandos básicos de administração e integração com IA para possíveis respostas inteligentes entre outras funcionalides. 

⚠️ Em desenvolvimento

Este bot ainda está incompleto. Novas funcionalidades serão adicionadas futuramente.

## 🔧 Tecnologias Utilizadas

- Java
- Spring Boot
- JDA (Java Discord API)
- Google GenAI (para respostas baseadas em IA)
- Banco de dados MongoDB
- Maven (gerenciador de dependências e build)

## 📦 Funcionalidades

### Comandos Principais (prefixo `!`)

- `!ping` – Verifica se o bot está online (resposta de latência).
- `!ban <usuário>` – Bane um membro do servidor.
- `!unban <usuário>` – Desbane um membro do servidor.
- `!kick <usuário>` – Expulsa um membro do servidor.
- `!say <mensagem>` – Faz o bot repetir a mensagem especificada.

### Eventos

- 🎉 **Guild Join/Leave Listener** – Detecta quando o bot entra ou sai de um servidor.
- 💬 **Resposta automática a mensagens** – Pode responder automaticamente a certas mensagens, com potencial de integração com IA.

## 🚀 Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Configure o token do bot no arquivo application.properties:
```properties
discord.bot.token=SEU_TOKEN_AQUI
```
3. Execute o projeto com Maven:
```bash
mvn spring-boot:run
```

