package com.vaadin.starter.skeleton.calendar;

import org.vaadin.stefan.fullcalendar.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.FullCalendar;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.shared.Registration;

public class ModifiedFullCalendar extends FullCalendar {
    public ModifiedFullCalendar() {
        super();
    }

    public CalendarViewImpl getView() {
        return null;
    }

    public void setUser(int integer) {
    }

    public Registration addEntryUpdateListener(
            ComponentEventListener<EntryUpdatedEvent> listener) {
        return null;
    }

    public Registration addNotificationListener(
            SerializableConsumer<CalendarNotification> notificationHandler) {
        return null;
    }
}
