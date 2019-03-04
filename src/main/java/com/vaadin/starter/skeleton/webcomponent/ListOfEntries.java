package com.vaadin.starter.skeleton.webcomponent;

import java.util.ArrayList;
import java.util.Collection;

import org.vaadin.stefan.fullcalendar.Entry;

public class ListOfEntries extends ArrayList<Entry> {
    public ListOfEntries(Collection<Entry> entries) {
        super(entries);
    }
}
