package com.docklandstech.workflow.runtime

import org.junit.Test

class EngineProcessTest {

    @Test
    fun run() {
        EngineProcess.main(arrayOf("-env", "test", "-bpmn", "src/main/resources/diagram.bpmn"))

    }

}
