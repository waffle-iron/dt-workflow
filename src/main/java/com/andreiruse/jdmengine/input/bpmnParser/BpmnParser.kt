package com.andreiruse.jdmengine.input.bpmnParser

import com.andreiruse.jdmengine.domain.Graph
import org.xml.sax.SAXException
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

class BpmnParser {

    @Throws(ParserConfigurationException::class, IOException::class, SAXException::class)
    fun parse(bpmnFilePath: String): Graph {
        val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bpmnFilePath)
        document.documentElement.normalize()
        val rootNode = document.documentElement
        val bpmnNodes = IntProgression.fromClosedRange(0, rootNode.childNodes.length, 1)
                .map { x -> rootNode.childNodes.item(x) }
                .find { x -> "bpmn:process".equals(x?.nodeName) }
                ?.childNodes
        if(bpmnNodes == null || bpmnNodes.length == 0) {
            return Graph()
        }
        val graph = Graph()
        val bpmnNodeList = IntProgression.fromClosedRange(0, bpmnNodes.length, 1).map { x -> bpmnNodes.item(x) } //Fixme Length-1!
        bpmnNodeList.forEach {
            if(it != null)
                if("bpmn:sequenceFlow".equals(it.nodeName)) {
                    graph.addSequence(it)
                } else if("bpmn:exclusiveGateway".equals(it.nodeName)) {
                    graph.addGateway(it)
                } else {
                    graph.addTask(it)
                }
        }
        return graph
    }

}
