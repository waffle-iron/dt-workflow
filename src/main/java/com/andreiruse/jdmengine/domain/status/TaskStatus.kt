package com.andreiruse.jdmengine.domain.status

import java.time.LocalDateTime

interface TaskStatus {
    companion object {
        val stateEnteringTime = LocalDateTime.now()
        val stateExitingTime: LocalDateTime? = null
    }
}
