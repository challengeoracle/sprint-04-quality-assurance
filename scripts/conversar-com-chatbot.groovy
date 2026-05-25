import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonOutput as JsonOutput
import groovy.json.JsonSlurper as JsonSlurper

// 1. AUTENTICAÇÃO (LOGIN)
def responseLogin = WS.sendRequest(findTestObject('Postman/Autenticação/Login Paciente'))
WS.verifyResponseStatusCode(responseLogin, 200)

def slurper = new JsonSlurper()
def jsonData = slurper.parseText(responseLogin.getResponseBodyContent())
String token = jsonData.token

if (!token) {
    KeywordUtil.markFailedAndStop("Token não veio no login. Resposta: " + responseLogin.getResponseBodyContent())
}

// 2. FUNÇÃO REUTILIZÁVEL DO CHATBOT
def enviarMensagem = { String mensagem ->
    def response = WS.sendRequest(
        findTestObject('Postman/Conversar com o Chatbot', [
            ('token')           : token,
            ('mensagem_usuario'): mensagem
        ])
    )

    WS.verifyResponseStatusCode(response, 200)

    KeywordUtil.logInfo(
        "\n[USUÁRIO]: ${mensagem}\n" +
        "[CHATBOT]:\n" + JsonOutput.prettyPrint(response.getResponseBodyContent())
    )

    return response
}

// 3. FLUXO DA CONVERSA
enviarMensagem("Oi, bom dia")
enviarMensagem("Quais são meus próximos agendamentos")
enviarMensagem("paciente1@gmail.com")
