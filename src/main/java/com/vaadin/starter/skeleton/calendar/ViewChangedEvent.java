package com.vaadin.starter.skeleton.calendar;

import com.vaadin.flow.component.ComponentEvent;

public class ViewChangedEvent extends ComponentEvent<ModifiedFullCalendar> {

    public ViewChangedEvent(ModifiedFullCalendar source, boolean fromClient) {
        super(source, fromClient);
    }
}
