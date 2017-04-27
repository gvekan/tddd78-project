package se.liu.ida.gusan092.tddd78.project.properties;

import se.liu.ida.gusan092.tddd78.project.game.Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
	System.out.println(highScores.size());
	for (int i = 0; i < highScores.size(); i++) {
	    System.out.println(i);
	    String data = highScores.getProperty(Integer.toString(i));
	    String[] parts = data.split(",");
	    highScoreList.add(new Score(parts[0], Integer.parseInt(parts[1]), LocalDate.parse(parts[2])));
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

    public List<Score> getHighScores(int max) {
        List<Score> res = new ArrayList<>();
	int limit = Game.clamp(max,0, highScoreList.size());
	for (int i = 0; i < limit; i++) {
	    res.add(highScoreList.get(i));
	}
	return res;
    }

    public List<Score> getHighScores() {
        return getHighScores(highScoreList.size());
    }

    public void addHighScore(Score score) {
        int index = highScoreList.add(score);
        String newPlaceHolder = score.getName() + "," + score.getPoints() + "," + score.getDate();
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
