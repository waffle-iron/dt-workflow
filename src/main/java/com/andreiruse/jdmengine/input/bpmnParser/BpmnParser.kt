package com.andreiruse.jdmengine.input.bpmnParser

import com.andreiruse.jdmengine.domain.InMemoryGraph
import org.w3c.dom.Node
import org.xml.sax.SAXException
import java.io.IOException
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

class BpmnParser {

    @Throws(ParserConfigurationException::class, IOException::class, SAXException::class)
    fun parse(bpmnFilePath: String): InMemoryGraph {
        val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bpmnFilePath)
        document.documentElement.normalize()
        val rootNode = document.documentElement
        val bpmnNodes = IntProgression.fromClosedRange(0, rootNode.childNodes.length, 1)
                .map { x -> rootNode.childNodes.item(x) }
                .find { x -> "bpmn:process".equals(x?.nodeName) }
                ?.childNodes
        if(bpmnNodes == null || bpmnNodes.length == 0) {
            return InMemoryGraph()
        }
        val graph = InMemoryGraph()
        val bpmnNodeList = IntProgression.fromClosedRange(0, bpmnNodes.length, 1).map { x -> bpmnNodes.item(x) } //Fixme Length-1!
        val edgesToProcessLater : MutableList<Node> = ArrayList()
        bpmnNodeList.forEach {
            if(it != null)
                if("bpmn:sequenceFlow".equals(it.nodeName)) {
                    edgesToProcessLater.add(it)
                } else if("bpmn:exclusiveGateway".equals(it.nodeName)) {
                    graph.addGateway(it)
                } else {
                    graph.addTask(it)
                }
        }
        for (node in edgesToProcessLater) {
            graph.addSequence(node)
        }
        println("graph.graph = ${graph.graph}")
        return graph
    }

}
