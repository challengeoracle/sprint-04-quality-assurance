import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper
import groovy.json.JsonOutput as JsonOutput
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

// --- COLOQUE O ID DO AGENDAMENTO QUE SERÁ TESTADO AQUI ---
String idAgendamento = "6"

// PASSO 1: AUTENTICAÇÃO
responseLogin = WS.sendRequest(findTestObject('Postman/Autenticação/Login Paciente'))

JsonSlurper slurper = new JsonSlurper()
Map jsonData = slurper.parseText(responseLogin.getResponseBodyContent())

String token = ""
if (jsonData.token) {
    token = jsonData.token
}

// Prepara o header padrão reutilizável
String bearerToken = 'Bearer ' + token

// PASSO 2: CONFIRMAR AGENDAMENTO
responseConfirmar = WS.sendRequest(findTestObject('Postman/Agendamentos (PACIENTE)/Confirmar Agendamento', [
	('token') : bearerToken, 
	('id') : idAgendamento
]))

// Imprime a resposta da Confirmação formatada
String jsonConfirmarBruto = responseConfirmar.getResponseBodyContent()
String jsonConfirmarFormatado = JsonOutput.prettyPrint(jsonConfirmarBruto)
KeywordUtil.logInfo("\n=============== 1. AGENDAMENTO CONFIRMADO ===============\nID: " + idAgendamento + "\n" + jsonConfirmarFormatado + "\n=======================================================")

// Valida sucesso da confirmação
WS.verifyResponseStatusCode(responseConfirmar, 204)

// PASSO 3: CANCELAR AGENDAMENTO
responseCancelar = WS.sendRequest(findTestObject('Postman/Agendamentos (PACIENTE)/Cancelar Agendamento', [
	('token') : bearerToken, 
	('id') : idAgendamento
]))

// Imprime a resposta do Cancelamento formatada
String jsonCancelarBruto = responseCancelar.getResponseBodyContent()
String jsonCancelarFormatado = JsonOutput.prettyPrint(jsonCancelarBruto)
KeywordUtil.logInfo("\n=============== 2. AGENDAMENTO CANCELADO ===============\nID: " + idAgendamento + "\n" + jsonCancelarFormatado + "\n=======================================================")

// Valida sucesso do cancelamento
WS.verifyResponseStatusCode(responseCancelar, 204)
