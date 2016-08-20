package com.docklandstech.workflow.domain

import org.apache.commons.lang3.builder.ToStringBuilder
import org.w3c.dom.NamedNodeMap

class SequenceFlow(map: NamedNodeMap) : GraphEdge {

    private val sourceRef: String
    private val targetRef: String
    private val id: String

    init {
        id = map.getNamedItem("id").nodeName
        sourceRef = map.getNamedItem("sourceRef").nodeName
        targetRef = map.getNamedItem("targetRef").nodeName
    }

    override fun toString(): String {
        return ToStringBuilder(this).append("sourceRef", sourceRef).append("targetRef", targetRef).toString()
    }
}
