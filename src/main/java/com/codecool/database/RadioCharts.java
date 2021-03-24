package com.codecool.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RadioCharts {
	private final String url;
	private final String user;
	private final String password;

	public RadioCharts(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public String getMostPlayedSong(){
		String mostPlayedSong = "";
		String SQL = "SELECT song, sum(times_aired) as sum " +
					 "FROM music_broadcast " +
					 "GROUP BY song " +
					 "ORDER BY sum desc";
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			ResultSet queryResult = statement.executeQuery(SQL);
			queryResult.next();
			mostPlayedSong = queryResult.getString(1);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return mostPlayedSong;
	}

	public String getMostActiveArtist(){
		String mostActiveArtist = "";
		String SQL = "SELECT artist, count(distinct(song)) as count " +
			         "FROM music_broadcast " +
					 "GROUP BY artist " +
					  "Order by count desc ";

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			ResultSet queryResult = statement.executeQuery(SQL);
			queryResult.next();
			mostActiveArtist = queryResult.getString(1);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return mostActiveArtist;
	}
}
