package com.docklandstech.workflow.domain.bpmn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(namespace = "bpmn", localName = "tasks")
public class BpmnTask extends AbstractBpmnGraphElement {
  @JacksonXmlProperty(isAttribute = true)
  public String id;
  @JacksonXmlProperty(isAttribute = true)
  public String name;
  @JacksonXmlProperty(namespace = "bpmn", localName = "incoming")
  public BpmnIncoming incoming;
  @JacksonXmlProperty(namespace = "bpmn", localName = "outgoing")
  public BpmnOutgoing outgoing;

  @Override
  public String getId() {
    return id;
  }
}
