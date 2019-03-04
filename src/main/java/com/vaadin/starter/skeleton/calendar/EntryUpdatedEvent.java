package com.vaadin.starter.skeleton.calendar;

import org.vaadin.stefan.fullcalendar.FullCalendar;

import com.vaadin.flow.component.ComponentEvent;

public class EntryUpdatedEvent extends ComponentEvent<FullCalendar> {
    /**
     * Creates a new event using the given source and indicator whether the
     * event originated from the client side or the server side.
     *
     * @param source     the source component
     * @param fromClient <code>true</code> if the event originated from the client
     */
    public EntryUpdatedEvent(FullCalendar source, boolean fromClient) {
        super(source, fromClient);
    }
}
