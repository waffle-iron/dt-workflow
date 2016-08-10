package com.andreiruse.jdmengine.input.bpmnParser

import com.andreiruse.jdmengine.domain.GraphTask
import com.andreiruse.jdmengine.domain.SequenceFlow
import org.xml.sax.SAXException
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

class BpmnParser {

    @Throws(ParserConfigurationException::class, IOException::class, SAXException::class)
    fun parse(bpmnFilePath: String) {
        val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bpmnFilePath)
        document.documentElement.normalize()
        val rootNode = document.documentElement
        val bpmnNodes = IntProgression.fromClosedRange(0, rootNode.childNodes.length, 1)
                .map { x -> rootNode.childNodes.item(x) }
                .find { x -> "bpmn:process".equals(x?.nodeName) }
                ?.childNodes
        val bpmnNodeCount: Int = bpmnNodes?.length as Int
        val bpmnNodeList = IntProgression.fromClosedRange(0, bpmnNodes?.length as Int, 1).map { x -> bpmnNodes?.item(x) } //Fixme Length-1!
        //ToDo Find a generic way to parse all types of elements, and recursively (subprocesses, etc)
        val processEdges = bpmnNodeList.filter { xmlNode -> "bpmn:sequenceFlow".equals(xmlNode?.nodeName) }.map { edge -> SequenceFlow(edge?.attributes!!) }
        val processTasks = bpmnNodeList.filter { xmlNode -> "bpmn:task".equals(xmlNode?.nodeName) }.map { node -> GraphTask(node?.attributes!!, node?.childNodes!!) }
        val exclusiveGateways = bpmnNodeList.filter { xmlNode -> "bpmn:exclusiveGateway".equals(xmlNode?.nodeName) }
        val parallelGateways = bpmnNodeList.filter { xmlNode -> "bpmn:parallelGateway".equals(xmlNode?.nodeName) }
        val startEvents = bpmnNodeList.filter { xmlNode -> "bpmn:startEvent".equals(xmlNode?.nodeName) }
        val endEvents = bpmnNodeList.filter { xmlNode -> "bpmn:endEvent".equals(xmlNode?.nodeName) }
    }

}
