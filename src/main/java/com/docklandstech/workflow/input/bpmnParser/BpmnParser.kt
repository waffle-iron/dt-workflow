package com.docklandstech.workflow.input.bpmnParser

import com.docklandstech.workflow.domain.InMemoryGraph
import com.docklandstech.workflow.domain.bpmn.BpmnDefinitions
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.nio.file.Files
import java.nio.file.Path

class BpmnParser {

    fun parse(bpmnFilePath: Path): InMemoryGraph {
        val bpmnDocument: BpmnDefinitions = loadBpmnDiagramFromFile(bpmnFilePath)
        val graph = InMemoryGraph()
        if (bpmnDocument.process == null || bpmnDocument.process.isEmpty)
            return graph
        bpmnDocument.process.tasks.forEach { graph.addTask(it) }
        bpmnDocument.process.exclusiveGateways.forEach { graph.addGateway(it) }
        bpmnDocument.process.startEvents.forEach { graph.addStartEvent(it) }
        bpmnDocument.process.endEvents.forEach { graph.addEndEvent(it) }
        bpmnDocument.process.sequenceFlows.forEach { graph.addSequenceFlow(it) }
        return graph
    }

    private fun loadBpmnDiagramFromFile(bpmnFilePath: Path): BpmnDefinitions {
        val xmlDocSource: String = Files.readAllLines(bpmnFilePath).joinToString("\n")
        val xmlMapper: XmlMapper = XmlMapper()
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val bpmnDocument: BpmnDefinitions = xmlMapper.readValue(xmlDocSource, BpmnDefinitions::class.java)
        return bpmnDocument
    }

}
