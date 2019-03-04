package com.vaadin.starter.skeleton.exporter;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.shared.Registration;

public interface WebComponentConfiguration<C extends Component> {
    <P> PropertyConfiguration<C, P> addProperty(String name,
                                                Class<P> propertyClass);

    void setConstructor(SerializableFunction<WebComponentContext, C> constructor);

    <P> PropertyConfiguration<C, List<P>> addListProperty(String events,
                                                          Class<P> entryClass);

    Registration exposeEvent(Class<? extends ComponentEvent<?>> eventType);

    <T extends ComponentEvent<?>, R> Registration exposeEvent(
            Class<T> eventType, SerializableFunction<T, R> converter);
}
