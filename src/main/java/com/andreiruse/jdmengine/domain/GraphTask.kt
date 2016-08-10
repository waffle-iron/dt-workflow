package com.andreiruse.jdmengine.domain

import com.andreiruse.jdmengine.domain.status.Created
import com.andreiruse.jdmengine.domain.status.TaskStatus
import org.apache.commons.lang3.builder.ToStringBuilder
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.util.*

open class GraphTask(map: NamedNodeMap, nodeList: NodeList) : GraphObject {
    private val taskId: Node
    private val taskTitle: Node
    private val status: TaskStatus = Created()
    private val incomingLinkNames = ArrayList<String>()
    private val outgoingLinkNames = ArrayList<String>()

    init {
        this.taskId = map.getNamedItem("id")
        this.taskTitle = map.getNamedItem("name")
        val edges: List<String> = IntProgression.fromClosedRange(0, nodeList.length, 1)
                .map { index -> nodeList.item(index) }
                .map { item -> (if ( item != null) item.nodeName else "") } //FixMe this will only get the tag name, not the value
        edges.filter { nodeName -> "bpmn:incoming".equals(nodeName) }
                .toCollection(incomingLinkNames)
        edges.filter { nodeName -> "bpmn:outgoing".equals(nodeName) }
                .toCollection(outgoingLinkNames)
    }

    override fun toString(): String {
        return ToStringBuilder(this).append("taskId", taskId).append("taskTitle", taskTitle).append("status", status).append("incomingLinkNames", incomingLinkNames).append("outgoingLinkNames", outgoingLinkNames).toString()
    }
}
