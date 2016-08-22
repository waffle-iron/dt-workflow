package com.docklandstech.workflow.domain.bpmn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(namespace = "bpmn", localName = "outgoing")
public class BpmnOutgoing extends AbstractBpmnGraphElement {

  public BpmnOutgoing(String value) {
    this.value = value;
  }

  @JacksonXmlText()
  public String value;


  @Override
  public String getId() {
    return "";
  }
}
