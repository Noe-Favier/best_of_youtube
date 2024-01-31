package fr.noais.byt.models;

public enum Category {
    SPORT("Sport"),
    MUSIQUE("Musique"),
    CUISINE("Cuisine"),
    JEUX("Jeux"),
    AUTRE("Autre");

    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
