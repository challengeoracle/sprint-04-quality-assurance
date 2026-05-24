# 🧪 Compliance & Quality Assurance — Medix
###  Sprint 4

---

## 📌 Sobre este documento

Este repositório contém a documentação de validação de sistema (plano de testes manuais) referente à **Sprint 4** do projeto **Medix** — um sistema de gestão e agendamento médico e gerenciamento de saúde dos usuários.

---

## ⚠️ Observações importantes sobre a metodologia

### Azure Test Plans — limitação da conta estudantil

A plataforma **Azure DevOps** oferece um módulo dedicado chamado **Test Plans**, projetado para o gerenciamento estruturado de casos de teste, execução e rastreamento de resultados.

No entanto, **a conta estudantil utilizada pela equipe não possui licença para acessar o Azure Test Plans**, pois trata-se de um recurso pago que não está incluso no plano gratuito disponibilizado para fins acadêmicos.

Como alternativa, os testes foram cadastrados **manualmente** diretamente nos cards do **Azure Boards**, utilizando o caminho:

> `Board → card do PBI → ⋯ (3 pontos) → Add Test`

Cada teste foi registrado com título, passos (Actions) e resultado esperado (Expected Result), mantendo total rastreabilidade com o PBI correspondente.

---

### Postman — substituição ao Katalon Studio

O plano original previa a automação dos testes via **Katalon Studio (Record & Playback)**. Porém, durante a Sprint 4, o ambiente de produção com interface de usuário (frontend) apresentou instabilidades que impossibilitaram a gravação dos scripts de automação de tela.

Como solução, os testes automatizados foram executados via **Postman**, ferramenta amplamente reconhecida no mercado para testes de API REST.

> **Os fluxos de teste executados no Postman seguem fielmente os mesmos passos definidos para o frontend**, cobrindo os mesmos cenários de negócio: autenticação, criação de agendamento, cancelamento e interação com o chatbot.

A API testada está disponível em:
```
https://sprint-04-java.onrender.com
```

---

## 🗂️ Testes cadastrados no Azure Boards

Os testes foram vinculados aos PBIs correspondentes e podem ser visualizados diretamente no Azure Boards pelo link disponível na seção de links deste documento.

Cada **Test Case ID** corresponde a um item criado manualmente nos cards do board.

---

### ✅ Test Case #71 & #95 — Agendamento e Cancelamento de Consulta

| Campo | Detalhes |
|---|---|
| **IDs no Azure Boards** | Test Case 71 (Cancelamento) · Test Case 95 (Agendamento) |
| **PBI relacionado** | Cancelamento de Consulta Confirmada · Criar Agendamento |
| **Sprint** | Sprint 2 / Sprint 3 |
| **Perfil testado** | Paciente |

**Fluxo testado:**
1. Login com credenciais de paciente
2. Criação de um novo agendamento (especialidade, unidade, data e horário)
3. Verificação de confirmação do agendamento (status `201 Created`)
4. Cancelamento do agendamento recém-criado via endpoint `PATCH /agendamentos/{id}/cancelar`
5. Verificação de que o status foi atualizado para `CANCELADO`

**Ferramenta utilizada:** Postman  
**Endpoint principal:** `POST /agendamentos` → `PATCH /agendamentos/{id}/cancelar`

---

### ✅ Test Case #84 — Conversa com o Chatbot (Medix AI)

| Campo | Detalhes |
|---|---|
| **ID no Azure Boards** | Test Case 84 |
| **PBI relacionado** | Interface do Medix AI e Tratamento de Resposta |
| **Sprint** | Sprint 4 |
| **Perfil testado** | Paciente |

**Fluxo testado:**
1. Login com conta de paciente para obtenção do token JWT
2. Envio de uma pergunta médica ao chatbot: `"Quais são os sintomas de pressão alta?"`
3. Verificação de que a resposta é retornada com status `200 OK` e conteúdo não vazio
4. Validação de ausência de mensagens de erro na resposta

**Ferramenta utilizada:** Postman  
**Endpoint principal:** `POST /api/chat/ask`

---

### ✅ Test Case #93 — Visualizar Consulta Agendada (Próximo Agendamento)

| Campo | Detalhes |
|---|---|
| **ID no Azure Boards** | Test Case 93 |
| **PBI relacionado** | Visualizar Consultas Agendadas |
| **Sprint** | Sprint 3 |
| **Perfil testado** | Paciente |

**Fluxo testado:**
1. Login com conta de paciente
2. Inserção do token JWT no campo Authorization (Bearer Token)
3. Requisição `GET /agendamentos/proximo` para visualizar o próximo agendamento ativo
4. Verificação de que os dados retornados contêm: `id`, `especialidade`, `dataHoraInicio` e `dataHoraFim`
5. Confirmação de que nenhum erro é retornado

**Ferramenta utilizada:** Postman  
**Endpoint principal:** `GET /agendamentos/proximo`

---

## 🔗 Links de entrega

| Recurso | Link |
|---|---|
| 🗂️ **Azure Boards ** | `[INSERIR LINK DO AZURE BOARDS AQUI]` |
| 🎥 **Vídeo de demonstração dos testes (YouTube)** | `[INSERIR LINK DO YOUTUBE AQUI]` |

---

## 👥 Equipe

| Nome | Função |
|---|---|
| Davi Cavalcanti Jorge | RM 559873|
| Mateus da Silveira Lima | RM 55987|
| Arthur Thomas Mariano de Souza | RM 561061|

---

## 🛠️ Tecnologias utilizadas nos testes

- **Azure DevOps Boards** — Gerenciamento e rastreamento dos casos de teste
- **Postman** — Execução dos testes de API (substituto ao Katalon)
- **Java / Spring Boot** — Backend testado
- **Render** — Ambiente de produção da API
