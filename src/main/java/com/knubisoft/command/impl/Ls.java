package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.ls.Strategy;
import com.knubisoft.util.Context;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

public class Ls extends Command {
    private static final String PACKAGE = "com.knubisoft";
    private static final String NAME = "name";
    private List<Strategy> columns;
    private Map<String, Integer> maxLengthValues;

    public Ls(Context context) {
        super(context);
    }

    @Override
    public List<String> execute(List<String> args) {
        if (args.size() == 0) {
            columns = getColumns(List.of());
        } else {
            columns = getColumns(args);
        }

        File currentDirectory = context.getCurrentDirectory();
        File[] files = currentDirectory.listFiles();

        if (files == null || files.length == 0) {
            return List.of("No folders and files are in folder: "
                    + currentDirectory.getName() + "!");
        }

        maxLengthValues = getMaxLengthValues(files);

        return Arrays.stream(files)
                .map(this::getRowValues)
                .map(this::createRow)
                .collect(Collectors.toList());
    }

    private Map<String, String> getRowValues(File file) {
        return columns.stream()
                .collect(Collectors.toMap(column -> column.getClass().getSimpleName()
                                .toLowerCase(),
                        column -> column.getValue(file)));
    }

    private String createRow(Map<String, String> values) {
        String row = getColumnsName().stream()
                .map(name -> createCell(values, name))
                .collect(Collectors.joining());
        return row + " |\n";
    }

    private String createCell(Map<String, String> values, String name) {
        String value = values.get(name);
        return String.format("| %s", value)
                + " ".repeat(maxLengthValues.get(name) - value.length());
    }

    private List<String> getColumnsName() {
        return columns.stream()
                .map(column -> column.getClass().getSimpleName().toLowerCase())
                .collect(Collectors.toList());
    }

    private List<Strategy> getColumns(List<String> columnsNames) {
        Reflections reflections = new Reflections(PACKAGE, Scanners.SubTypes);
        List<Strategy> strategies = reflections.getSubTypesOf(Strategy.class).stream()
                .map(this::createInstance)
                .collect(Collectors.toList());
        return columnsNames.isEmpty() ? strategies : chooseStrategies(strategies, columnsNames);
    }

    private List<Strategy> chooseStrategies(List<Strategy> strategies,
                                            List<String> columnsNames) {
        TreeSet<String> tree = new TreeSet<>(columnsNames);
        tree.add(NAME);
        return strategies.stream()
                .filter(fileStrategy -> tree.contains(fileStrategy.getClass().getSimpleName()
                        .toLowerCase()))
                .sorted(Comparator.comparing(fileStrategy -> fileStrategy.getClass()
                        .getSimpleName()))
                .collect(Collectors.toList());
    }

    private Map<String, Integer> getMaxLengthValues(File[] files) {
        return columns.stream()
                .collect(Collectors.toMap(column -> column.getClass().getSimpleName()
                                .toLowerCase(),
                        column -> getMaxColumns(column, files)));
    }

    private Integer getMaxColumns(Strategy column, File[] files) {
        return Arrays.stream(files)
                .map(file -> column.getValue(file).length())
                .max(Comparator.naturalOrder())
                .orElseGet(() -> 0);
    }

    @SneakyThrows
    private <T> T createInstance(Class<T> clazz) {
        return clazz.getDeclaredConstructor().newInstance();
    }
}
