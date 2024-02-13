package org.deliveroo.cronexpressionparser;

import org.deliveroo.cronexpressionparser.fields.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CronExpressionParser {

    static List<String> displayOrder = List.of("minute", "hour", "day", "month", "weekday");
    static Map<String, String> displayString = Map
            .of("day", "day of month", "weekday", "day of week");
    String expression;
    HashMap<String, List<Integer>> fieldsMap;
    String command;

    CronExpressionParser(String expression)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.expression = expression;
        this.fieldsMap = new HashMap<>();
        this.parse();
    }

    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (args.length != 1) {
            throw new RuntimeException("There should be 1 argument : " + String.join(";", args));
        }
        CronExpressionParser cronParser = new CronExpressionParser(args[0]);


        System.out.println(cronParser.parse().toString());
    }

    private CronExpressionParser parse()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.println("Expression entered : " + this.expression);
        String[] fields = this.expression.split("\\s+");

        if (fields.length < 6) {
            throw new RuntimeException("There must be 6 fields in the expression");
        }

        int i = 0;
        this.fieldsMap.put("minute", new Minute(fields[i++]).parse());
        this.fieldsMap.put("hour", new Hour(fields[i++]).parse());
        this.fieldsMap.put("day", new Day(fields[i++]).parse());
        this.fieldsMap.put("month", new Month(fields[i++]).parse());
        this.fieldsMap.put("weekday", new Weekday(fields[i++]).parse());
        this.command = fields[i] + extractArguments(fields, i + 1);
        return this;
    }

    private String extractArguments(String[] fields, int startingIndex) {
        StringBuilder arguments = new StringBuilder();
        for (int i = startingIndex; i < fields.length; i++) {
            arguments.append(" ").append(fields[i]);
        }
        return arguments.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String section : displayOrder) {
            if (this.fieldsMap.get(section) == null) {
                continue;
            }
            String displayString = CronExpressionParser.displayString.getOrDefault(section, section);
            sb.append(String.format(displayString + " ".repeat(14 - displayString.length()) + "%s",
                    this.fieldsMap.get(section).stream().map(Object::toString).collect(
                            Collectors.joining(" "))));
            sb.append("\n");
        }
        sb.append(String.format("command       %s", this.command));
        return sb.toString();
    }
}
