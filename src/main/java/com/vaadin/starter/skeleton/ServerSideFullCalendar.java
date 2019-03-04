package com.vaadin.starter.skeleton;

import java.time.LocalDate;
import java.util.List;

import org.vaadin.stefan.fullcalendar.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.EntryDroppedEvent;
import org.vaadin.stefan.fullcalendar.FullCalendar;

import com.vaadin.flow.component.WebComponent;
import com.vaadin.flow.component.webcomponent.PropertyValueChangeEvent;
import com.vaadin.flow.component.webcomponent.WebComponentProperty;
import com.vaadin.starter.skeleton.calendar.CalendarNotification;
import com.vaadin.starter.skeleton.calendar.EntryUpdatedEvent;
import com.vaadin.starter.skeleton.calendar.UserEventRepository;
import com.vaadin.starter.skeleton.calendar.UserRepository;
import com.vaadin.starter.skeleton.webcomponent.CreationContext;
import com.vaadin.starter.skeleton.webcomponent.ListOfEntries;
import com.vaadin.starter.skeleton.webcomponent.ReadOnly;
import com.vaadin.starter.skeleton.webcomponent.WebComponentConstructor;

@WebComponent("full-calendar")
public class ServerSideFullCalendar extends FullCalendar {

    private WebComponentProperty<Long> userId =
            new WebComponentProperty<>(Long.class);

    private WebComponentProperty<CalendarViewImpl> displayMode =
            new WebComponentProperty<>(CalendarViewImpl.MONTH,
                    CalendarViewImpl.class);

    @ReadOnly // this could also be a parameter or method call
    // list properties, Option 1:
    private WebComponentProperty<ListOfEntries> entries =
            new WebComponentProperty<>(ListOfEntries.class);
    /* Option 2:
    private WebComponentListProperty<Entry> entries =
            new WebComponentListProperty<>(Entry.class);
    */

    private WebComponentProperty<LocalDate> selectedDate =
            new WebComponentProperty<>(LocalDate.now(), LocalDate.class);

    private UserRepository userRepository;
    private UserEventRepository userEventRepository;

    public ServerSideFullCalendar(UserRepository userRepository,
                                  UserEventRepository userEventRepository) {
        super();

        this.userRepository = userRepository;
        this.userEventRepository = userEventRepository;

        userId.addValueChangeListener(this::userIdChanged);
        // a proposal for a validator approach known from Binder
        userId.addValidator(id -> userRepository::exits(id));

        // (de)serialized proposal from Exporter approach is left out here
        // as adding it didn't seem clear enough (or it can be added very
        // similarly)
        displayMode.addValueChangeListener(this::displayModeChanged);


        addViewRenderedListener(event -> {
            // TODO: this bugs out - the date might have been set on the
            //       server-side, and causes it to be set again for no
            //       reason
            selectedDate.set(event.getIntervalStart());

            // update entries based on what is visible
            List<Entry> entriesToShow = getEntries(
                    event.getStart().atStartOfDay(),
                    event.getEnd().atStartOfDay());
            entries.set(new ListOfEntries(entriesToShow));
        });


        // DOES HEAVY THINGS HERE
    }

    // WebComponent CTOR
    private ServerSideFullCalendar(CreationContext context) {
        // SKIPS HEAVY THINGS
        // in fact, nothing much should be done here
    }

    private void displayModeChanged(PropertyValueChangeEvent<CalendarViewImpl> event) {
        // do stuff with event
    }

    private void userIdChanged(PropertyValueChangeEvent<Long> event) {
        // do stuff with event
    }

    // called from a relevant place
    private void fireEntryUpdatedEvent(EntryUpdatedEvent event) {
        // true -> fire the ComponentEvent as DOM event
        fireEvent(event, true);
    }

    // called from a relevant place
    private void fireEntryDroppedEvent(EntryDroppedEvent event) {
        // true -> fire the ComponentEvent as DOM event
        fireEvent(event, true);
    }

    // called from where ever notifications are being generated
    private void publishNotificationAsDOMEvent(
            CalendarNotification notification) {

        /*
            Use a "thing" to publish notification as a DOM event. This
            "thing" could be part of
                - UI
                - ComponentUtil
                - Some new entity
                - Component
                - WebComponentUtil
                - Just getElement.executeJavaScript(...)
         */
    }

    /**
     * Alternative way for creating a server-side web component, if calling
     * the default CTOR is a bad idea
     * @param context
     * @return
     */
    @WebComponentConstructor
    public static ServerSideFullCalendar createCalendar(CreationContext context) {
        return new ServerSideFullCalendar(context);
    }
}
