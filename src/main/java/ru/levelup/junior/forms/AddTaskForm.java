package ru.levelup.junior.forms;

import javax.validation.constraints.Size;

public class AddTaskForm {
    @Size(min = 4, max = 100, message = "Name length should be at least 4 and at most 100 characters length")
    private String name;
    @Size(min = 4, max = 500, message = "Text length should be at least 4 and at most 500 characters length")
    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
