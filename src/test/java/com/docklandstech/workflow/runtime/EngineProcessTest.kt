package com.docklandstech.workflow.runtime

import org.apache.commons.cli.MissingOptionException
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class EngineProcessTest {
    @Rule
    @JvmField var expected: ExpectedException = ExpectedException.none()

    @Test
    fun run() {
        EngineProcess.main(arrayOf("-env", "test", "-bpmn", "src/main/resources/diagram.bpmn"))
    }

    @Test
    fun runWithNoEnv() {
        expected.expect(MissingOptionException::class.java)
        expected.expectMessage("Missing required option: env")
        EngineProcess.main(arrayOf("-bpmn", "src/main/resources/diagram.bpmn"))
    }

    @Test
    fun runWithNoBpmn() {
        expected.expect(MissingOptionException::class.java)
        expected.expectMessage("Missing required option: bpmn")
        EngineProcess.main(arrayOf("-env", "test"))
    }
}
