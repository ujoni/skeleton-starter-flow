package com.vaadin.starter.skeleton;

import com.vaadin.flow.component.WebComponentExporter;
import com.vaadin.flow.component.webcomponent.PropertyConfiguration;
import com.vaadin.flow.component.webcomponent.WebComponent;

public class PersonExporter extends WebComponentExporter<PersonComponent> {
    private PropertyConfiguration<PersonComponent, Boolean> isAdultProperty;

    public PersonExporter() {
        super("person-display");
        addProperty("name", "John Doe")
                .onChange(PersonComponent::setName);
        addProperty("age", 0)
                .onChange(PersonComponent::setAge);

        isAdultProperty = addProperty("is-adult", false);
    }

    @Override
    public void configureInstance(WebComponent<PersonComponent> webComponent, PersonComponent component) {
        component.setAdultAge(18); // initialization

        component.addAgeChangedListener(event -> {
            webComponent.setProperty(isAdultProperty, component.isAdult());
        });

        component.addAgeChangedListener(event -> {
            if (event.getAge() > 65) {
                webComponent.fireEvent("retirement-age-reached");
            }
        });
    }
}
