package com.docklandstech.workflow.domain

import com.docklandstech.workflow.domain.status.TaskStatus
import java.util.*

data class GraphTask(val taskId : String, val taskTitle: String) {
    private val status: TaskStatus = TaskStatus.CREATED
    private val outgoingLinkNames = ArrayList<GraphTask>()

    fun addChild(nextElement: GraphTask) {
        this.outgoingLinkNames.add(nextElement)
    }
}