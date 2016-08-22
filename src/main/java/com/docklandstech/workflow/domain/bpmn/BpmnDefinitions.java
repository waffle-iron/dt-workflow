package com.docklandstech.workflow.domain.bpmn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(namespace = "bpmn", localName = "definitions")
public class BpmnDefinitions {
  @JacksonXmlProperty()
  public String id;

  @JacksonXmlProperty()
  @JsonIgnore
  public String targetNamespace;

  @JacksonXmlProperty(namespace = "bpmn", localName = "process")
  public BpmnProcess process;

  @JacksonXmlProperty(namespace = "bpmndi", localName = "BPMNDiagram")
  @JsonIgnore
  public String bpmnDiagram;

  @Override
  public String toString() {
    return "BpmnDefinitions{" +
        "id='" + id + '\'' +
        ", process=" + process +
        '}';
  }
}

