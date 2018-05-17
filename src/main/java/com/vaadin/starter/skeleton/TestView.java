package com.vaadin.starter.skeleton;

import java.util.List;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.starter.skeleton.TestView.MyModel;

@Route("test")
@HtmlImport("src/test-view.html")
@Tag("test-view")
public class TestView extends PolymerTemplate<MyModel> {
    public interface MyModel extends TemplateModel {
        public List<Item> getItems();

        public void setItems(List<Item> items);
    }

    public static class Item {
        private String text;
        private boolean enabled;

        public Item() {
        }

        public Item(String text) {
            setText(text);
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

    }

    public TestView() {
        getModel().getItems().add(new Item("Item 1"));
    }

    @ClientCallable
    public void add() {
        getModel().getItems().add(new Item("New"));
    }

    @ClientCallable
    public void toggle(Item item) {
        item.setEnabled(!item.isEnabled());
    }

}
