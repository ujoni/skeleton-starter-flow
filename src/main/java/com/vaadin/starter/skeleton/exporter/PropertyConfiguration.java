package com.vaadin.starter.skeleton.exporter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.function.SerializableBiConsumer;


public interface PropertyConfiguration<C extends Component, P> {
    PropertyConfiguration<C, P> onChange(SerializableBiConsumer<C, P> onChangeHandler);

    PropertyConfiguration<C, P> readOnly();
}
