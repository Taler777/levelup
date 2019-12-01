package ru.levelup.junior.forms;

import javax.validation.constraints.Size;

/**
 * Форма при добавлении задачи
 */
public class AddTaskForm {

    private String name;

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