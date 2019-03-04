package com.vaadin.starter.skeleton.calendar;

import java.util.List;

import org.vaadin.stefan.fullcalendar.Entry;

public interface UserEventRepository {
    List<Entry> getUserEntries(Long id);
}
