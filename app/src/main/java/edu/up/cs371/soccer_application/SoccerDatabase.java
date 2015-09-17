package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable<String, SoccerPlayer> search = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName, int uniformNumber, String teamName) {
        if (search.containsKey(firstName + "_" + lastName)) {
            return false;
        }

        search.put(firstName + "_" + lastName, new SoccerPlayer(firstName, lastName, uniformNumber, teamName));

        return true;
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {

        if (search.containsKey(firstName + "_" + lastName)) {
            search.remove(firstName + "_" + lastName);
            return true;
        }

        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {

        if (search.containsKey(firstName + "_" + lastName)) {
            return search.get(firstName + "_" + lastName);
        }

        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {

        if (getPlayer(firstName, lastName) != null) {
            getPlayer(firstName, lastName).bumpGoals();
            return true;
        }

        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if (getPlayer(firstName, lastName) != null) {
            getPlayer(firstName, lastName).bumpAssists();
            return true;
        }

        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if (getPlayer(firstName, lastName) != null) {
            getPlayer(firstName, lastName).bumpShots();
            return true;
        }

        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if (getPlayer(firstName, lastName) != null) {
            getPlayer(firstName, lastName).bumpSaves();
            return true;
        }

        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if (getPlayer(firstName, lastName) != null) {
            getPlayer(firstName, lastName).bumpFouls();
            return true;
        }

        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if (getPlayer(firstName, lastName) != null) {
            getPlayer(firstName, lastName).bumpYellowCards();
            return true;
        }

        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if (getPlayer(firstName, lastName) != null) {
            getPlayer(firstName, lastName).bumpRedCards();
            return true;
        }

        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        int count = 0;
        if(teamName==null) count = search.size();

        int i = 0;
        Object[] names = search.keySet().toArray();
        for(i=0;i<names.length;i++){
            if(search.get(names[i].toString()).getTeamName().equals(teamName)) count++;
        }
        return count;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {

        Object[] names = search.keySet().toArray();
        if(teamName!=null) {
            int i;
            ArrayList<String> teamPlayers = new ArrayList<String>();
            for(i=0;i<names.length;i++){
                if(search.get(names[i].toString()).getTeamName().equals(teamName)){
                    teamPlayers.add(names[i].toString());
                }
            }
            if (idx >= teamPlayers.size()) return null;
            return search.get(teamPlayers.get(idx));
        }
        if (idx >= names.length) return null;
        return search.get(names[idx]);
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(file);
            int i;
            Object[] names = search.keySet().toArray();
            for(i=0;i<names.length;i++){
                String firstName = search.get(names[i].toString()).getFirstName();
                String lastName = search.get(names[i].toString()).getLastName();
                String team = search.get(names[i].toString()).getTeamName();
                int uniformNo = search.get(names[i].toString()).getUniform();
                int goals = search.get(names[i].toString()).getGoals();
                int assists = search.get(names[i].toString()).getAssists();
                int shots = search.get(names[i].toString()).getShots();
                int saves = search.get(names[i].toString()).getSaves();
                int fouls = search.get(names[i].toString()).getFouls();
                int yellows = search.get(names[i].toString()).getYellowCards();
                int reds = search.get(names[i].toString()).getRedCards();

                writer.println(logString("First Name: " + firstName));
                writer.println(logString("Last Name: " + lastName));
                writer.println(logString("Team Name: " + team));
                writer.println(logString("Uniform Number: " + uniformNo));
                writer.println(logString("Goals: " + goals));
                writer.println(logString("Assists: " + assists));
                writer.println(logString("Shots: " + shots));
                writer.println(logString("Saves: " + saves));
                writer.println(logString("Fouls: " + fouls));
                writer.println(logString("Yellow Cards: " + yellows));
                writer.println(logString("Red Cards: " + reds));
            }
            return true;

        }catch(Exception e){return false;}
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
