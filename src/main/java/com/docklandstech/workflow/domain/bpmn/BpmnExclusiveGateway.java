package com.docklandstech.workflow.domain.bpmn;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(namespace = "bpmn", localName = "exclusiveGateway")
public class BpmnExclusiveGateway extends AbstractBpmnGraphElement {
  @JacksonXmlProperty(isAttribute = true)
  public String id;
  @JacksonXmlProperty(isAttribute = true)
  public String name;
  @JacksonXmlProperty(namespace = "bpmn", localName = "incoming")
  @JacksonXmlElementWrapper(useWrapping = false)
  public List<BpmnIncoming> incomingFlows;
  @JacksonXmlProperty(namespace = "bpmn", localName = "outgoing")
  @JacksonXmlElementWrapper(useWrapping = false)
  public List<BpmnOutgoing> outgoingFlows;

  @Override
  public String getId() {
    return id;
  }
}
