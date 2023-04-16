package com.sg.backend.repositories;

public class Queries {
    public static String SQL_INSERT_CONTACT = "insert into contact(id, email, title, text, attachment) values (?, ?, ?, ?, ?)";
    public static String SQL_VIEW_ALL_CONTACT = "select * from contact";
    public static String SQL_INSERT_CLUB_PLAYER = "insert into club_player(id, name) values (?, ?)";
    public static String SQL_ID_CLUB_PLAYER = "select id from club_player";
    public static String SQL_VIEW_ALL_CLUB_PLAYER = "select * from club_player order by name";
    public static String SQL_INSERT_PLAYER_STATS = "insert into player_stats(club_player_id, goals, assists, yellowCards, redCards, cleanSheets, concededGoals, isGoalkeeper) values (?, ?, ?, ?, ?, ?, ?, ?)";
    public static String SQL_CLUB_PLAYER_ID_STATS = "select club_player_id from player_stats";
}
