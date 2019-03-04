package com.vaadin.starter.skeleton.exporter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.function.SerializableBiConsumer;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.function.ValueProvider;


public interface PropertyConfiguration<C extends Component, P> {
    PropertyConfiguration<C, P> defaultValue(P value);

    PropertyConfiguration<C, P> serializer(SerializableFunction<String, P> in,
                                           SerializableFunction<P, String> out);

    PropertyConfiguration<C, P> onChange(SerializableBiConsumer<C, P> onChangeHandler);

    <E extends ComponentEvent<C>> PropertyConfiguration<C, P> updateOn(Class<E> eventClass, ValueProvider<C, P> valueProvider);

    PropertyConfiguration<C, P> noNotify();

    PropertyConfiguration<C, P> readOnly();

    void writeProperty(C component, P value);
}
