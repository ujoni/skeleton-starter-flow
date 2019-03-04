package com.vaadin.starter.skeleton.exporter;

import com.vaadin.flow.component.Component;

import elemental.json.JsonValue;

public interface WebComponent<C extends Component> {
    void fireEvent(String eventName);

    void fireEvent(String eventName, JsonValue objectData);

    void fireEvent(String eventName, JsonValue objectData,
                   EventOptions options);

    <P> void setProperty(
            PropertyConfiguration<C, P> propertyConfiguration, P value);

    <P> P getProperty(PropertyConfiguration<C, P> propertyConfiguration);
}
