package com.docklandstech.workflow.domain

import org.jgrapht.DirectedGraph
import org.jgrapht.Graph
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

    // TODO find kotlin-idiomatic way of doing this
    private var _startTask : GraphTask? = null
    var startTask: GraphTask
    get() {
        return _startTask!!
    }
    set(value) {
        _startTask = value
    }

    fun addTask(xmlNode: Node, isStartPoint: Boolean = false) {
        if (xmlNode.hasAttributes()) {
            val taskId = xmlNode.attributes.getNamedItem("id").nodeValue
            val taskName = if (xmlNode.attributes.getNamedItem("name") != null) xmlNode.attributes.getNamedItem("name").nodeValue else ""
            val vertexValues = GraphTask(taskId, taskName)
            graphNodes.put(taskId, vertexValues)
            graph.addVertex(vertexValues)
            if (isStartPoint)
                startTask = vertexValues
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

            edgeSourceObject!!.addChild(edgeDestinationObject!!)

            graph.addEdge(edgeSourceObject, edgeDestinationObject)

        }

    }

    fun getSize() : Int {
        return graph.vertexSet().size
    }

    fun getVertices() : MutableSet<GraphTask>? {
        return graph.vertexSet()
    }

}
