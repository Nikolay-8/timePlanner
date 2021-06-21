package com.timePlanner.Calendar.elements;

import java.util.Calendar;
import java.util.List;

public class Day {

    public Calendar day;

    public List<com.timePlanner.models.Tasks> tasks;

    public Day(Calendar day) {
        this.day = day;
    }

    public List getTasks() {
        return this.tasks;
    }

    public Day setTasks(List tasks) {
        this.tasks = tasks;
        return this;
    }

    public Calendar getDay() {
        return day;
    }
}
