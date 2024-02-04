package fr.noais.byt.models;

import java.util.Arrays;

public enum Category {
    AUTRE("Autre"),
    SPORT("Sport"),
    MUSIQUE("Musique"),
    CUISINE("Cuisine"),
    JEUX("Jeux");

    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String[] getNames() {
        return Arrays.stream(Category.values()).map(Category::getName).toArray(String[]::new);
    }

    public static Category fromName(String name) {
        return Arrays.stream(Category.values()).filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }
}
