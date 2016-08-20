package com.docklandstech.workflow.domain

import org.jgrapht.DirectedGraph
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import org.w3c.dom.Node
import java.util.*

class InMemoryGraph {
    private var graphNodes: MutableMap<String, GraphTask> = HashMap()
    private var _graph: DirectedGraph<GraphTask, DefaultEdge> = DefaultDirectedGraph(DefaultEdge::class.java)
    var graph: DirectedGraph<GraphTask, DefaultEdge>
        get() = _graph
        set(value) {
            _graph = value
        }

    fun addTask(xmlNode: Node) {
        if (xmlNode.hasAttributes()) {
            val taskId = xmlNode.attributes.getNamedItem("id").nodeValue
            val taskName = if (xmlNode.attributes.getNamedItem("name") != null) xmlNode.attributes.getNamedItem("name").nodeValue else ""
            val vertexValues = GraphTask(taskId, taskName)
            graphNodes.put(taskId, vertexValues)
            graph.addVertex(vertexValues)
        }
    }

    fun addGateway(xmlNode: Node) {
        if (xmlNode.hasAttributes()) {
            val gatewayId = xmlNode.attributes.getNamedItem("id").nodeValue
            val gatewayName = if (xmlNode.attributes.getNamedItem("name") != null) xmlNode.attributes.getNamedItem("name").nodeValue else ""
            val vertexValues = GraphTask(gatewayId, gatewayName)
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
            val edgeSourceObject: GraphTask? = graphNodes[edgeSource]
            val edgeDestinationObject: GraphTask? = graphNodes[edgeDestination]
            graph.addEdge(edgeSourceObject, edgeDestinationObject)
        }
    }
}
