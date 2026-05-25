# Compliance & Quality Assurance — Medix

## Sprint 4

---

## Informações de Entrega

### Azure Boards

```
https://dev.azure.com/RM559873/Medix/_boards/board/t/Medix%20Team/Backlog%20items
```

### Vídeo demonstrativo

Link da demonstração completa dos testes executados:

```
https://youtu.be/0knb4Ujl3bQ
```

### Scripts de automação

Os scripts utilizados nos testes automatizados estão disponíveis na pasta:

```
/scripts
```

---

## Sobre este Documento

Este repositório contém a documentação de validação do sistema (plano de testes manuais e automatizados) referente à Sprint 4 do projeto Medix — uma plataforma voltada para gestão médica, agendamento de consultas e gerenciamento de saúde dos usuários.

O objetivo deste documento é apresentar os cenários de testes executados, os fluxos validados e as ferramentas utilizadas durante o processo de garantia de qualidade da aplicação.

---

## Metodologia Utilizada

### Azure Test Plans — Limitação da Conta Acadêmica

O Azure DevOps disponibiliza o módulo Azure Test Plans para gerenciamento estruturado de casos de teste, execuções e rastreamento de resultados.

Entretanto, a conta acadêmica utilizada pela equipe não possui acesso a esse recurso, pois trata-se de uma funcionalidade licenciada separadamente e indisponível no plano gratuito estudantil.

Como alternativa, os casos de teste foram cadastrados manualmente diretamente nos cards do Azure Boards através do caminho:

```
Board → Card do PBI → ⋯ → Add Test
```

Cada teste foi registrado contendo:

- Título
- Passos de execução (Actions)
- Resultado esperado (Expected Result)

Dessa forma, foi mantida a rastreabilidade entre os testes e seus respectivos Product Backlog Items (PBIs).

---

### Automação de Testes — Postman e Katalon Studio

Inicialmente, a estratégia de automação previa a utilização do Katalon Studio com abordagem Record & Playback para validação da interface gráfica.

Durante o desenvolvimento da Sprint 4, o ambiente frontend apresentou instabilidades que comprometeram a gravação e execução dos fluxos automatizados via interface.

Como solução alternativa, os testes automatizados passaram a ser executados via Postman e Katalon Studio utilizando chamadas REST diretamente contra a API da aplicação.

Os fluxos implementados reproduzem exatamente os mesmos cenários funcionais previstos para o frontend, incluindo:

- Autenticação de usuários
- Criação de agendamentos
- Cancelamento de consultas
- Interações com o chatbot Medix AI

API utilizada nos testes:

```
https://sprint-04-java.onrender.com
```

---

## Testes Registrados no Azure Boards

Todos os casos de teste foram vinculados aos respectivos PBIs no Azure Boards.

Cada identificador apresentado abaixo corresponde a um item registrado manualmente dentro do board do projeto.

---

## Test Case #71 & #95 — Agendamento e Cancelamento de Consulta

| Campo               | Detalhes                                                 |
| ------------------- | -------------------------------------------------------- |
| IDs no Azure Boards | Test Case 71 (Cancelamento) · Test Case 95 (Agendamento) |
| PBI relacionado     | Cancelamento de Consulta Confirmada · Criar Agendamento  |
| Sprint              | Sprint 2 / Sprint 3                                      |
| Perfil testado      | Paciente                                                 |

### Fluxo Testado

1. Login com credenciais de paciente
2. Criação de um novo agendamento contendo:
    - especialidade
    - unidade
    - data
    - horário
3. Validação do retorno de criação com status:
    ```
    201 Created
    ```
4. Cancelamento do agendamento recém-criado utilizando o endpoint:
    ```
    PATCH /agendamentos/{id}/cancelar
    ```
5. Verificação de atualização do status para:
    ```
    CANCELADO
    ```

### Ferramentas e Endpoints

- Ferramenta: Postman
- Endpoint principal:
    ```
    POST /agendamentos
    PATCH /agendamentos/{id}/cancelar
    ```

---

## Test Case #84 — Conversa com o Chatbot (Medix AI)

| Campo              | Detalhes                                       |
| ------------------ | ---------------------------------------------- |
| ID no Azure Boards | Test Case 84                                   |
| PBI relacionado    | Interface do Medix AI e Tratamento de Resposta |
| Sprint             | Sprint 4                                       |
| Perfil testado     | Paciente                                       |

### Fluxo Testado

1. Login com conta de paciente para obtenção do token JWT
2. Envio da pergunta:
    ```
    Quais são os sintomas de pressão alta?
    ```
3. Verificação de retorno com status:
    ```
    200 OK
    ```
4. Validação de resposta não vazia
5. Verificação de ausência de erros no retorno da API

### Ferramentas e Endpoints

- Ferramenta: Postman / Katalon Studio
- Endpoint principal:
    ```
    POST /api/chat/ask
    ```

---

## Test Case #93 — Visualizar Consulta Agendada

| Campo              | Detalhes                       |
| ------------------ | ------------------------------ |
| ID no Azure Boards | Test Case 93                   |
| PBI relacionado    | Visualizar Consultas Agendadas |
| Sprint             | Sprint 3                       |
| Perfil testado     | Paciente                       |

### Fluxo Testado

1. Login com conta de paciente
2. Inserção do token JWT no Authorization Bearer
3. Execução da requisição:
    ```
    GET /agendamentos/proximo
    ```
4. Verificação dos seguintes campos retornados:
    - id
    - especialidade
    - dataHoraInicio
    - dataHoraFim
5. Validação de ausência de erros na resposta

### Ferramentas e Endpoints

- Ferramenta: Postman
- Endpoint principal:
    ```
    GET /agendamentos/proximo
    ```

---

## Links de Entrega

| Recurso             | Link                           |
| ------------------- | ------------------------------ |
| Azure Boards        | https://dev.azure.com/RM559873/Medix/_boards/board/t/Medix%20Team/Backlog%20items |
| Vídeo Demonstrativo | https://youtu.be/0knb4Ujl3bQ   |

---

## Equipe

| Nome                           | RM     |
| ------------------------------ | ------ |
| Davi Cavalcanti Jorge          | 559873 |
| Mateus da Silveira Lima        | 559728 |
| Arthur Thomas Mariano de Souza | 561061 |

---

## Tecnologias Utilizadas

- Azure DevOps Boards
- Postman
- Katalon Studio
- Java
- Spring Boot
- Render
