package com.sg.backend.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.backend.models.Club;
import com.sg.backend.models.PlayerSQL;

@Repository
public class ClubRepository {

    @Autowired
    private JdbcTemplate template;

    public void insert(Club player) {
        /*
         * // check for existing entries
         * // shifted this logic into a new method and @ClubService
         * List<String> ids = template.queryForList(Queries.SQL_ID_CLUB_PLAYER,
         * String.class);
         * if (ids.contains(player.getId())) {
         * System.out.println("Player ID already exists in database.");
         * return;
         * }
         */

        Object[] params = new Object[] {
                player.getId(),
                player.getName()
        };
        template.update(Queries.SQL_INSERT_CLUB_PLAYER, params);
    }

    // return list of IDs in mySQL
    public List<String> getPlayerIds() {

        List<String> ids = template.queryForList(Queries.SQL_ID_CLUB_PLAYER, String.class);
        return ids;
    }

    public List<PlayerSQL> getPlayerList() {
        List<PlayerSQL> players = template.query(Queries.SQL_VIEW_ALL_CLUB_PLAYER, new RowMapper<PlayerSQL>() {
            public PlayerSQL mapRow(ResultSet rs, int rowNum) throws SQLException {
                PlayerSQL player = new PlayerSQL();
                player.setId(rs.getString("id"));
                player.setName(rs.getString("name"));
                return player;
            }
        });
        return players;
    }

    // delete by records by id
    public void deletePlayerById(String id) {
        // must delete any record that references PK first
        template.update(Queries.SQL_DELETE_PLAYER_STATS_BY_PLAYERID, id);
        template.update(Queries.SQL_DELETE_CLUB_PLAYER_BY_ID, id);
    }
}
