package se.liu.ida.gusan092.tddd78.project.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class AppProperties
{
    private final String DEFAULTFILE = "default.properties";
    private final String SETTINGSFILE = "settings.properties";
    private final String HIGH_SCORESFILE = "high_scores.properties";
    private static final AppProperties file = new AppProperties("");
    private Properties defSettings = new Properties();
    private Properties settings;
    private Properties highScores = new Properties();
    private HighScoreList highScoreList = new HighScoreList();

    private AppProperties(String start) {
        read(defSettings, DEFAULTFILE);
	settings = new Properties(defSettings); //setting defSettings as the default
        read(settings, SETTINGSFILE);
        read(highScores, HIGH_SCORESFILE);
	for (int i = 1; i < highScores.size()+1; i++) {
	    String data = highScores.getProperty(Integer.toString(i));
	    String[] parts = data.split(",");
	    highScoreList.add(new HighScore(parts[0], Integer.parseInt(parts[2]), LocalDate.parse(parts[1])));
	}
    }

    public static AppProperties getInstance() {
        return file;
    }

    private void read(final Properties prop, final String fileName) {
	FileInputStream in = null;
	try {
	    in = new FileInputStream(fileName);
	    prop.load(in);
	} catch (IOException e) {
	    System.out.println("Error accessing " + fileName);
	    e.printStackTrace();
	} finally {
	    if(in != null) {
	        try {
	            in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    private void write(final Properties prop, final String fileName) {
	FileOutputStream out = null;
        try {
	    out = new FileOutputStream(fileName);
	    prop.store(out, "---No Comment---");
	} catch (IOException e) {
	    System.out.println("Error writing app");
	    e.printStackTrace();
	} finally {
	    if(out != null) {
	        try {
	            out.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public void setDefault() {
	settings = new Properties(defSettings);
	write(settings, SETTINGSFILE);
    }

    public HighScore getHighScore(int pos) {
        return highScoreList.get(pos-1);
    }

    public void addHighScore(HighScore highScore) {
        int index = highScoreList.add(highScore);
        String newPlaceHolder = highScore.getName()+ "," + highScore.getPoints()+ "," + highScore.getDate();
	for (int i = index; i < highScores.size(); i++) {
	    String place = Integer.toString(i);
	    String oldPlaceHolder = highScores.getProperty(place);
	    highScores.setProperty(place,newPlaceHolder);
	    newPlaceHolder = oldPlaceHolder;
	}
	highScores.setProperty(Integer.toString(highScores.size()), newPlaceHolder);
	write(highScores, HIGH_SCORESFILE);
    }


}
