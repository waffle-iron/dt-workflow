package com.docklandstech.workflow.domain

import com.docklandstech.workflow.domain.bpmn.*
import org.jgrapht.DirectedGraph
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import java.util.*

class InMemoryGraph {
    var graphNodes: MutableMap<String, AbstractBpmnGraphElement> = HashMap()
    var graph: DirectedGraph<AbstractBpmnGraphElement, DefaultEdge> = DefaultDirectedGraph(DefaultEdge::class.java)

    fun addTask(task: BpmnTask) {
        graphNodes.put(task.id, task)
        graph.addVertex(task)
    }

    fun addStartEvent(startEvent : BpmnStartEvent) {
        graphNodes.put(startEvent.id, startEvent)
        graph.addVertex(startEvent)
    }

    fun addEndEvent(endEvent: BpmnEndEvent) {
        graphNodes.put(endEvent.id, endEvent)
        graph.addVertex(endEvent)
    }

    fun addGateway(gateway : BpmnExclusiveGateway) {
        graphNodes.put(gateway.id, gateway)
        graph.addVertex(gateway)
    }

    fun addSequenceFlow(sequenceFlow : BpmnSequenceFlow) {
        val sourceNode : AbstractBpmnGraphElement? = graphNodes[sequenceFlow.sourceRef]
        val targetNode : AbstractBpmnGraphElement? = graphNodes[sequenceFlow.targetRef]
        graph.addEdge(sourceNode,targetNode)
    }

    fun getSize(): Int {
        return graph.vertexSet().size
    }

    fun getVertices(): Set<AbstractBpmnGraphElement> {
        return graph.vertexSet()
    }

}
