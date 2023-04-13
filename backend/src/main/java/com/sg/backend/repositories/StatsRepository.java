package com.sg.backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sg.backend.models.Stats;

@Repository
public class StatsRepository {

    @Autowired
    private JdbcTemplate template;

    public void insert(Stats stats) {

        Object[] params = new Object[] {
                stats.getClubPlayerId(),
                stats.getGoals(),
                stats.getAssists(),
                stats.getYellowCards(),
                stats.getRedCards(),
                stats.getCleanSheets(),
                stats.getConcededGoals(),
                stats.isGoalkeeper()
        };
        template.update(Queries.SQL_INSERT_PLAYER_STATS, params);
    }

    public List<String> getClubPlayerIds() {

        List<String> clubPlayerIds = template.queryForList(Queries.SQL_CLUB_PLAYER_ID_STATS, String.class);
        return clubPlayerIds;
    }
}
