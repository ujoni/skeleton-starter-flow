package com.vaadin.starter.skeleton.exporter;

import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.function.SerializableBiConsumer;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.shared.Registration;

public interface WebComponentDefinition<C extends Component> {

    <P> PropertyConfiguration<C, P> addProperty(
            String name, P defaultValue);

    <P> PropertyConfiguration<C, P> addProperty(
            String name, Class<P> type);

    /*
    default PropertyConfiguration<C, Integer> addProperty(
            String name, int defaultValue) {
        return addProperty(name, defaultValue, Integer.class);
    }

    default PropertyConfiguration<C, Double> addProperty(
            String name, double defaultValue) {
        return addProperty(name, defaultValue, Double.class);
    }

    default PropertyConfiguration<C, String> addProperty(
            String name, String defaultValue) {
        return addProperty(name, defaultValue, String.class);
    }

    default PropertyConfiguration<C, Boolean> addProperty(
            String name, boolean defaultValue) {
        return addProperty(name, defaultValue, Boolean.class);
    }

    default PropertyConfiguration<C, JsonValue> addProperty(
            String name, JsonValue defaultValue) {
        return addProperty(name, defaultValue, JsonValue.class);
    }
*/
    void setInstanceConfigurator(SerializableBiConsumer<WebComponent<C>, C> instanceConfigurator);

    <P> PropertyConfiguration<C, List<P>> addListProperty(
            String name, Class<P> entryClass);

    <P> PropertyConfiguration<C, List<P>> addListProperty(String name,
                                                          List<P> defaultValue);

    default <P> PropertyConfiguration<C, List<P>> addListProperty(
            String name, P... defaultValues) {
        return addListProperty(name, Arrays.asList(defaultValues));
    }

    Registration exposeEvent(Class<? extends ComponentEvent<?>> eventType);

    <T extends ComponentEvent<?>, R> Registration exposeEvent(
            Class<T> eventType, SerializableFunction<T, R> converter);
}
