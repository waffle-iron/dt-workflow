package com.docklandstech.workflow.domain.bpmn;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(namespace = "bpmn", localName = "process")
public class BpmnProcess extends AbstractBpmnGraphElement {

  @JacksonXmlProperty(isAttribute = true)
  private String id;

  @JacksonXmlProperty(isAttribute = true)
  private boolean isExecutable;

  @JacksonXmlProperty(namespace = "bpmn", localName = "startEvent")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<BpmnStartEvent> startEvents = new ArrayList<>();

  @JacksonXmlProperty(namespace = "bpmn", localName = "endEvent")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<BpmnEndEvent> endEvents = new ArrayList<>();

  @JacksonXmlProperty(namespace = "bpmn", localName = "task")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<BpmnTask> tasks = new ArrayList<>();

  @JacksonXmlProperty(namespace = "bpmn", localName = "sequenceFlow")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<BpmnSequenceFlow> sequenceFlows = new ArrayList<>();

  @JacksonXmlProperty(namespace = "bpmn", localName = "exclusiveGateway")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<BpmnExclusiveGateway> exclusiveGateways = new ArrayList<>();

  public void setId(final String id) {
    this.id = id;
  }

  public boolean isExecutable() {
    return isExecutable;
  }

  public void setExecutable(final boolean executable) {
    isExecutable = executable;
  }

  public List<BpmnStartEvent> getStartEvents() {
    return startEvents;
  }

  public void setStartEvents(final List<BpmnStartEvent> startEvents) {
    startEvents.forEach(this.startEvents::add);
  }

  public List<BpmnEndEvent> getEndEvents() {
    return endEvents;
  }

  public void setEndEvents(final List<BpmnEndEvent> endEvents) {
    endEvents.forEach(this.endEvents::add);
  }

  public List<BpmnTask> getTasks() {
    return tasks;
  }

  public void setTasks(final List<BpmnTask> tasks) {
    tasks.forEach(this.tasks::add);
  }

  public List<BpmnSequenceFlow> getSequenceFlows() {
    return sequenceFlows;
  }

  public void setSequenceFlows(final List<BpmnSequenceFlow> sequenceFlows) {
    sequenceFlows.forEach(this.sequenceFlows::add);
  }

  public List<BpmnExclusiveGateway> getExclusiveGateways() {
    return exclusiveGateways;
  }

  public void setExclusiveGateways(final List<BpmnExclusiveGateway> exclusiveGateways) {
    exclusiveGateways.forEach(this.exclusiveGateways::add);
  }

  public boolean isEmpty() {
    return startEvents.isEmpty() && tasks.isEmpty() && sequenceFlows.isEmpty() && exclusiveGateways.isEmpty() && endEvents.isEmpty();
  }


  @Override
  public String getId() {
    return id;
  }
}
