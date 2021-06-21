package com.timePlanner.controller;

import com.timePlanner.repository.TaskRepository;
import com.timePlanner.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.timePlanner.models.Tasks;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.timePlanner.Calendar.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Task {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskRepository repository;

    @GetMapping("/")
    public String main(ModelMap model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
        Calendar cal = Calendar.getInstance();

        model.addAttribute("viewCalendarLink", "view/" + dateFormat.format(cal.getTime()));
        return "main";
    }

    @GetMapping("/view")
    public String mainPage(ModelMap model) {

        TaskServices taskServices = new TaskServices();
        model.addAttribute("tasks", taskServices.findAll());

        return "view";
    }

    @GetMapping("/view/{year}/{month}")
    public String viewMonthTasks(@PathVariable("year") int year, @PathVariable("month") int month, ModelMap model) {
        GregorianCalendar firstDay = new GregorianCalendar(year, (month - 1), 1);

        model.addAttribute("year", firstDay.get(GregorianCalendar.YEAR));
        model.addAttribute("days", Month.getByYearAndMonthNum(year, month, taskRepository));
        model.addAttribute("monthText", Month.getMonthText(firstDay.get(GregorianCalendar.MONTH)));

        model.addAttribute("prevLink", getPrevLink(firstDay.get(GregorianCalendar.YEAR), firstDay.get(GregorianCalendar.MONTH)));
        model.addAttribute("nextLink", getNextLink(firstDay.get(GregorianCalendar.YEAR), firstDay.get(GregorianCalendar.MONTH)));

        return "task/view_month";
    }

    @GetMapping("/view/{year}/{month}/{day}")
    public String viewDayTasks(
            @PathVariable("year") int year,
            @PathVariable("month") int month,
            @PathVariable("day") int day,
            ModelMap model
    ) {
        month = month - 1;
        List tasks = Month.getTasksByDay(new GregorianCalendar(year, month, day), taskRepository);
        model.put("tasks", tasks);

        return "task/view_day";
    }

    @GetMapping("/create")
    public String create(Map<String, Object> model) {
        model.put("task", new Tasks());

        return "task/create";
    }

    @RequestMapping("/create")
    public String create(@ModelAttribute Tasks task) {
        TaskServices taskServices = new TaskServices();
        taskServices.save(task);

        return "redirect:/view";
    }

    @RequestMapping("/delete/{task_id}")
    public String delete(@PathVariable("task_id") int taskId) {
        TaskServices taskServices = new TaskServices();
        Tasks task = taskServices.findById(taskId);
        taskServices.delete(task);
        return "redirect:/view";
    }

    @RequestMapping("/edit")
    public String edit(@ModelAttribute Tasks task) {
        TaskServices taskServices = new TaskServices();
        taskServices.update(task);

        return "redirect:/view";
    }

    @RequestMapping("/edit/{task_id}")
    public String edit(@PathVariable("task_id") int taskId, Map<String, Object> model) {
        TaskServices taskServices = new TaskServices();
        model.put("task", taskServices.findById(taskId));

        return "task/edit";
    }

    private static String getPrevLink(int year, int month) {
        if (month < 1) {
            month = 12;
            year --;
        }

        return "/view/" +  year + "/" + month;
    }

    private static String getNextLink(int year, int month) {
        month +=2; // +1 т.к нумерация начинается с 0 и +1 т.к нужен следующий месяц
        if (month > 12) {
            month = 1;
            year++;
        }
        return "/view/" +  year + "/" + month;
    }
}