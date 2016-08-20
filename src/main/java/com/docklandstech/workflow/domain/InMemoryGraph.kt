package com.docklandstech.workflow.domain

import org.jgrapht.DirectedGraph
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import org.w3c.dom.Node
import java.util.*

class InMemoryGraph {
    private var graphNodes: MutableMap<String, Pair<String, String>> = HashMap()
    private var _graph: DirectedGraph<Pair<String, String>, DefaultEdge> = DefaultDirectedGraph(DefaultEdge::class.java)
    var graph: DirectedGraph<Pair<String, String>, DefaultEdge>
        get() = _graph
        set(value) {
            _graph = value
        }

    fun addTask(xmlNode: Node) {
        if (xmlNode.hasAttributes()) {
            val taskId = xmlNode.attributes.getNamedItem("id").nodeValue
            val taskName = if (xmlNode.attributes.getNamedItem("name") != null) xmlNode.attributes.getNamedItem("name").nodeValue else ""
            val vertexValues = Pair(taskId, taskName)
            graphNodes.put(taskId, vertexValues)
            graph.addVertex(vertexValues)
        }
    }

    fun addGateway(xmlNode: Node) {
        if (xmlNode.hasAttributes()) {
            val gatewayId = xmlNode.attributes.getNamedItem("id").nodeValue
            val gatewayName = if (xmlNode.attributes.getNamedItem("name") != null) xmlNode.attributes.getNamedItem("name").nodeValue else ""
            val vertexValues = Pair(gatewayId, gatewayName)
            graphNodes.put(gatewayId, vertexValues)
            graph.addVertex(vertexValues)
        }
    }

    fun addSequence(xmlNode: Node) {
        if (xmlNode.hasAttributes()) {
            if (xmlNode.attributes.item(1) == null || xmlNode.attributes.item(2) == null) {
                return
            }
            val edgeSource = xmlNode.attributes.getNamedItem("sourceRef").nodeValue
            val edgeDestination = xmlNode.attributes.getNamedItem("targetRef").nodeValue
            val edgeSourceObject: Pair<String, String>? = graphNodes[edgeSource]
            val edgeDestinationObject: Pair<String, String>? = graphNodes[edgeDestination]
            graph.addEdge(edgeSourceObject, edgeDestinationObject)
        }
    }
}
