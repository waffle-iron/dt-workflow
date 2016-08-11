package com.andreiruse.jdmengine.domain.status

enum class TaskStatus {
    CREATED(), //Task is created
    OPEN(), //Task is launched, but not executing
    IN_PROGRESS(), //Task is executing and not pending any input
    PENDING_USER(), //Pending for user input, on user task
    PENDING_EXECUTION(), //Pending for other input, on non-user task
    COMPLETED(), //Task has been successfully completed
    FAILED(), //Task execution has failed with an exception
}
