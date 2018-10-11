package com.vaadin.starter.skeleton;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

public class BookService {

    private static Faker faker = Faker.instance();

    public static List<Book> getBooks(int count) {

        return IntStream.range(0, count).mapToObj(i -> {
            com.github.javafaker.Book book = faker.book();
            return new Book(book.title(), book.author());
        }).collect(Collectors.toList());
    }

    public static class Book {

        private String name;
        private String author;

        public Book(String name, String author) {
            super();
            this.name = name;
            this.author = author;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

    }

}
