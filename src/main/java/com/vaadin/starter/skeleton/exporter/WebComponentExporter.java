package com.vaadin.starter.skeleton.exporter;

import com.vaadin.flow.component.Component;

public interface WebComponentExporter<C extends Component> {
    String getTag();

    void configure(WebComponentConfiguration<C> configuration);
}
