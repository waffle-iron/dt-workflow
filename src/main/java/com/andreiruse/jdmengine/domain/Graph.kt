package com.andreiruse.jdmengine.domain

import org.w3c.dom.Node

class Graph {
    fun addTask(xmlNode: Node?) {
        if(xmlNode!!.hasAttributes()) {
            println("Adding new task: ${xmlNode.attributes.item(1)}")
        }
    }

    fun addGateway(xmlNode: Node?) {
        if(xmlNode!!.hasAttributes())
            println("Adding new gateway: ${xmlNode.attributes.item(1)}")
    }

    fun addSequence(xmlNode: Node?) {
        if(xmlNode!!.hasAttributes())
            println("Adding new sequence: ${xmlNode.attributes.item(1)}")
    }

}