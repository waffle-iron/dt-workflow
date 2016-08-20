package com.docklandstech.workflow.domain

import com.docklandstech.workflow.domain.status.TaskStatus
import org.apache.commons.lang3.builder.ToStringBuilder
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.util.*

open class GraphTask(id : Int, name: String) {
    private val taskTitle: String
    private val taskId: Int
    private val status: TaskStatus = TaskStatus.CREATED
    private val outgoingLinkNames = ArrayList<GraphTask>()

    init {
        this.taskId = id
        this.taskTitle = name
    }

    constructor(id: Int, name: String, nextElement: GraphTask ) : this(id, name){
        this.addChild(nextElement)
    }

    fun addChild(nextElement: GraphTask) {
        this.outgoingLinkNames.add(nextElement)
    }
}
