package com.docklandstech.workflow.domain

import com.docklandstech.workflow.domain.status.TaskStatus
import java.util.*

open class GraphTask(id : String, name: String) {
    private val taskTitle: String
    private val taskId: String
    private val status: TaskStatus = TaskStatus.CREATED
    private val outgoingLinkNames = ArrayList<GraphTask>()

    init {
        this.taskId = id
        this.taskTitle = name
    }

    constructor(id: String, name: String, nextElement: GraphTask ) : this(id, name){
        this.addChild(nextElement)
    }

    fun addChild(nextElement: GraphTask) {
        this.outgoingLinkNames.add(nextElement)
    }
}