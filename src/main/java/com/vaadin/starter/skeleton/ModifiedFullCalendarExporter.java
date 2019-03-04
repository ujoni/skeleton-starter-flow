package com.vaadin.starter.skeleton;

import java.time.LocalDate;
import java.util.List;

import org.vaadin.stefan.fullcalendar.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.Entry;

import com.vaadin.starter.skeleton.calendar.CalendarNotification;
import com.vaadin.starter.skeleton.calendar.ModifiedFullCalendar;
import com.vaadin.starter.skeleton.exporter.PropertyConfiguration;
import com.vaadin.starter.skeleton.exporter.WebComponentDefinition;
import com.vaadin.starter.skeleton.exporter.WebComponentExporter;

import elemental.json.JsonValue;


public class ModifiedFullCalendarExporter implements
        WebComponentExporter<ModifiedFullCalendar> {

    @Override
    public String getTag() {
        return "modified-full-calendar";
    }

    @Override
    public void define(WebComponentDefinition<ModifiedFullCalendar> definition) {
        definition.addProperty("user-id", -1)
                // validation is inside handler, exception based
                .onChange(ModifiedFullCalendar::setUser);

        definition.addProperty("display-mode", CalendarViewImpl.MONTH)
                .onChange(ModifiedFullCalendar::changeView);

        // add a list property
        PropertyConfiguration<ModifiedFullCalendar, List<Entry>> entriesProperty =
                definition.addListProperty("entries", Entry.class)
                .readOnly();

        PropertyConfiguration<ModifiedFullCalendar, LocalDate> selectedDateProperty =
                definition.addProperty("selected-date", LocalDate.now())
                        .onChange(ModifiedFullCalendar::gotoDate);

        definition.setInstanceConfigurator((webComponent, instance) -> {

            instance.addViewRenderedListener(event -> {
                // update entries based on what is visible on the calendar
                List<Entry> entries = instance.getEntries(
                        event.getStart().atStartOfDay(),
                        event.getEnd().atStartOfDay());

                webComponent.setProperty(entriesProperty, entries);

                webComponent.setProperty(selectedDateProperty,
                        event.getIntervalStart());
            });


            instance.addNotificationListener(notification -> {
                JsonValue notificationJson = makeNotificationJSON(notification);
                webComponent.fireEvent("notification-event", notificationJson);
            });

            List<Entry> returnedEntries =
                    webComponent.getProperty(entriesProperty);

        });
    }

    private JsonValue makeNotificationJSON(CalendarNotification notification) {
        return null;
    }
}
