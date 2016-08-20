package com.docklandstech.workflow.input.bpmnParser

import org.junit.Test
import org.xml.sax.SAXParseException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class BpmnParserTest {
    @Test(expected = SAXParseException::class)
    fun parseEmptyDoc() {
        val parser = BpmnParser()
        parser.parse("")
    }

    @Test
    fun parseBpmnEmptyXml() {
        val emptyBpmnXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">" +
                "</definitions>"
        val pathToFile = Paths.get("./emptyBpmnXML.tmp")
        Files.write(pathToFile, emptyBpmnXml.toByteArray(), StandardOpenOption.CREATE)
        val parser = BpmnParser()
        parser.parse(pathToFile.toString())
    }
}