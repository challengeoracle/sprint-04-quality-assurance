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

// 1. Executa o login do paciente para gerar o acesso
responseLogin = WS.sendRequest(findTestObject('Postman/Autenticação/Login Paciente'))

// 2. Extrai o Token do JSON de resposta do login
JsonSlurper slurper = new JsonSlurper()
Map jsonData = slurper.parseText(responseLogin.getResponseBodyContent())

String token = ""
if (jsonData.token) {
    token = jsonData.token
}

// 3. Executa a chamada GET para visualizar o próximo agendamento enviando o token
responseProximo = WS.sendRequest(findTestObject('Postman/Agendamentos (PACIENTE)/Visualizar Próximo Agendamento', [('token') : 'Bearer ' + token]))

String jsonBruto = responseProximo.getResponseBodyContent()
String jsonFormatado = JsonOutput.prettyPrint(jsonBruto)
KeywordUtil.logInfo(jsonFormatado)

// 4. Valida se a API respondeu com sucesso (Status 200 OK)
WS.verifyResponseStatusCode(responseProximo, 200)
