package com.docklandstech.workflow.domain.bpmn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(namespace = "bpmn", localName = "incoming")
public class BpmnIncoming extends AbstractBpmnGraphElement {

  public BpmnIncoming(String value) {
    this.value = value;
  }

  @JacksonXmlText()
  public String value;

  @Override
  public String getId() {
    return "";
  }
}
