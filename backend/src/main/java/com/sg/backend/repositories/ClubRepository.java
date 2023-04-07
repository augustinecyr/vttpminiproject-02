package com.sg.backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sg.backend.models.Club;

@Repository
public class ClubRepository {

    @Autowired
    private JdbcTemplate template;

    public void insert(Club player) {
        /*
        // check for existing entries
        // shifted this logic into a new method and @ClubService
        List<String> ids = template.queryForList(Queries.SQL_ID_CLUB_PLAYER, String.class);
        if (ids.contains(player.getId())) {
            System.out.println("Player ID already exists in database.");
            return;
        } 
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
}
