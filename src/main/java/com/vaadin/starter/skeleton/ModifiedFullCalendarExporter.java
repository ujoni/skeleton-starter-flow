package com.vaadin.starter.skeleton;

import java.time.LocalDate;
import java.util.List;

import org.vaadin.stefan.fullcalendar.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.EntryDroppedEvent;

import com.vaadin.starter.skeleton.calendar.EntryUpdatedEvent;
import com.vaadin.starter.skeleton.calendar.ExposedEntryDroppedEvent;
import com.vaadin.starter.skeleton.calendar.ModifiedFullCalendar;
import com.vaadin.starter.skeleton.calendar.ViewChangedEvent;
import com.vaadin.starter.skeleton.exporter.EventPublisher;
import com.vaadin.starter.skeleton.exporter.PropertyConfiguration;
import com.vaadin.starter.skeleton.exporter.WebComponentConfiguration;
import com.vaadin.starter.skeleton.exporter.WebComponentExporter;


public class ModifiedFullCalendarExporter implements WebComponentExporter<ModifiedFullCalendar> {
    @Override
    public String getTag() {
        return "modified-full-calendar";
    }

    @Override
    public void configure(WebComponentConfiguration<ModifiedFullCalendar> configuration) {
        configuration.addProperty("user-id", Integer.class)
                .onChange(ModifiedFullCalendar::setUser)
                // no property update events (better name pending)
                .noNotify();

        configuration.addProperty("display-mode", CalendarViewImpl.class)
                .defaultValue(CalendarViewImpl.MONTH)
                // custom string (de)serialization
                .serializer(CalendarViewImpl::valueOf,
                        CalendarViewImpl::getClientSideValue)
                .onChange(ModifiedFullCalendar::changeView)
                // update property on component-event
                .updateOn(ViewChangedEvent.class,
                        ModifiedFullCalendar::getView);

        PropertyConfiguration<ModifiedFullCalendar, List<Entry>> entryProperty =
                configuration.addListProperty("events", Entry.class)
                .readOnly();

        // this is ugly
        PropertyConfiguration<ModifiedFullCalendar, LocalDate> selectedDateProperty =
                configuration.addProperty("selected-date",
                        LocalDate.class)
                        .onChange(ModifiedFullCalendar::gotoDate);

        /*
            Exposes ComponentEvents as DOM events. Sadly, the parameter
            signature is
                exposeEvent(Class<? extends ComponentEvent<?>> eventType)
            instead of
                exposeEvent(Class<? extends ComponentEvent<C>> eventType)
            as the component events could be tied to the class being exported or
            a super class thereof. No compile time protection here.
         */
        // added a new <ComponentEvent> that makes more sense to expose
        configuration.exposeEvent(EntryUpdatedEvent.class);
        configuration.exposeEvent(EntryDroppedEvent.class,
                ExposedEntryDroppedEvent::new);

        configuration.setConstructor(context -> {
            ModifiedFullCalendar calendar = new ModifiedFullCalendar();

            // calendar does not offer a direct callback for gotoDate-events
            // we did add a custom `getView` method, but sometimes one must
            // work with limitations
            calendar.addViewRenderedListener(event -> {
                // TODO: this bugs out - the date might have been set on the
                //       server-side, and that is not nice
                selectedDateProperty.writeProperty(calendar,
                        event.getIntervalStart());

                // update entries based on what is visible
                List<Entry> entries = calendar.getEntries(
                        event.getStart().atStartOfDay(),
                        event.getEnd().atStartOfDay());
                entryProperty.writeProperty(calendar, entries);
            });

            // is there an existing way to push DOM events
            EventPublisher publisher = context.getEventPublisher();

            // or some some BEAN based on notification:
            calendar.addNotificationHandler(publisher::publishAsDOMEvent);

            return calendar;
        });
    }
}
