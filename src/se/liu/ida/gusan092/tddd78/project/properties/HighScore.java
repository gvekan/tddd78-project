package se.liu.ida.gusan092.tddd78.project.properties;

import java.time.LocalDate;

public class HighScore
{
    private String name;
    private int points;
    private LocalDate date;

    public HighScore(final String name, final int points, final LocalDate date) {

	this.name = name;
	this.points = points;
	this.date = date;
    }

    public String getName() {
	return name;
    }

    public int getPoints() {
	return points;
    }

    public LocalDate getDate() {
	return date;
    }
}
