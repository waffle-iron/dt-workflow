package com.docklandstech.workflow.runtime;

import com.docklandstech.workflow.domain.GraphTask;
import com.docklandstech.workflow.domain.InMemoryGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WorkflowRunner {

    private InMemoryGraph graph;

    public WorkflowRunner(InMemoryGraph graph) {
        this.graph = graph;
    }

    public void run() {
        //TODO Make connected tasks wait for one another. Maybe use CompletableFutures?
        GraphTask task = graph.getStartTask();
        List<Future<Integer>> scripts = new LinkedList<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        while (task.getNext() != null) {
            String name = task.getTaskTitle();
            Callable<Integer> script = () -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + name);
                Thread.sleep(1000);
                return 1;
            };
            scripts.add(executor.submit(script));
            task = task.getNext();
        }
    }
}
