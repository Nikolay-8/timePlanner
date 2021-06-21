package com.timePlanner.Calendar;

import com.timePlanner.Calendar.elements.Day;
import com.timePlanner.repository.TaskRepository;

import java.util.*;

public class Month {

    public static HashMap getMonthText()
    {
        HashMap<Integer, String> monthText = new HashMap<>();
        monthText.put(Calendar.JANUARY, "Январь");
        monthText.put(Calendar.FEBRUARY, "Февраль");
        monthText.put(Calendar.MARCH, "Март");
        monthText.put(Calendar.APRIL, "Апрель");
        monthText.put(Calendar.MAY, "Май");
        monthText.put(Calendar.JUNE, "Июнь");
        monthText.put(Calendar.JULY, "Июль");
        monthText.put(Calendar.AUGUST, "Август");
        monthText.put(Calendar.SEPTEMBER, "Сентябрь");
        monthText.put(Calendar.OCTOBER, "Октябрь");
        monthText.put(Calendar.NOVEMBER, "Ноябрь");
        monthText.put(Calendar.DECEMBER, "Декабрь");

        return monthText;
    }

    public static String getMonthText(int monthNum)
    {
        HashMap monthTextAll = getMonthText();

        return (String) monthTextAll.get(monthNum);
    }

    public static List getTasksByDay(GregorianCalendar day, TaskRepository taskRepository)
    {
        return taskRepository.findByTaskDate(day.getTime());
    }

    public static List<Day> getByYearAndMonthNum(int year, int monthNum, TaskRepository taskRepository) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);

        // Индексация месяцев начинается с нуля
        monthNum--;
        calendar.set(Calendar.MONTH, monthNum);
        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int i = 1;

        List<Day> days = new ArrayList();

        while (i<=numberOfDaysInMonth) {
            Calendar item = new GregorianCalendar(year, monthNum , i);
            List tasksByDay = taskRepository.findByTaskDate(item.getTime());

            Day day = new Day(item);
            day.setTasks(tasksByDay);
            days.add(day);
            i++;
        }

        return days;
    }
}
