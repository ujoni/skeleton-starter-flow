package com.vaadin.starter.skeleton;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.BookService.Book;

@Route("")
public class MainView extends HorizontalLayout {

    public MainView() {
        ComboBox<Book> comboBox = new ComboBox<>();
        comboBox.setWidth("250px");

        List<Book> books = BookService.getBooks(30);
        comboBox.setItems(books);

        comboBox.setItemLabelGenerator(Book::getName);

        comboBox.setRenderer(TemplateRenderer.<Book> of(
                "<div>[[item.name]]<br><small>By: [[item.author]]</small></div>")
                .withProperty("name", Book::getName)
                .withProperty("author", Book::getAuthor));

        Button button = new Button("Add more books", e -> {
            books.addAll(BookService.getBooks(30));
            comboBox.getDataProvider().refreshAll();
        });

        add(comboBox, button);
    }
}
