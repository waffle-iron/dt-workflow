package com.docklandstech.workflow.input.bpmnParser

import com.docklandstech.workflow.domain.InMemoryGraph
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.*

class BpmnParserTest {
    @Test(expected = IOException::class)
    fun parseEmptyDoc() {
        val parser = BpmnParser()
        parser.parse(Paths.get(""))
    }

    @Test
    fun parseBpmnEmptyXml() {
        val emptyBpmnXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">" +
                "</definitions>"
        val pathToFile = Paths.get("./emptyBpmnXML.tmp")
        Files.write(pathToFile, emptyBpmnXml.toByteArray(), StandardOpenOption.CREATE)
        val parser = BpmnParser()
        parser.parse(pathToFile)
    }

    @Test
    fun parseDefaultBpmnXml() {
        val parser : BpmnParser = BpmnParser()
        val graph : InMemoryGraph = parser.parse(Paths.get("src/main/resources/diagram.bpmn"))
        assertEquals(11, graph.getSize())
        val expectedGraphElements = Arrays.asList("StartEvent_1",
                "Task_1wwmb21",
                "ExclusiveGateway_0dr5n4j",
                "Task_00cjjzc",
                "Task_0q3mwgo",
                "EndEvent_0csk7l5",
                "ExclusiveGateway_1k7wbt3",
                "Task_14m4ix9",
                "Task_10zg8wa",
                "ExclusiveGateway_0jg3gwp",
                "EndEvent_0j4fie2")
        assert(graph.getVertices().map { x -> x.id }.containsAll(expectedGraphElements))
    }
}
