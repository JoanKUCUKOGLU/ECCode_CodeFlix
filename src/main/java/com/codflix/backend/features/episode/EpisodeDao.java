package com.codflix.backend.features.episode;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.Episode;
import com.codflix.backend.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpisodeDao {
    public int getTotalDuration(int id) {
        int duration = 0;
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT SUM(duration) from episode WHERE media_id = ?;");

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                duration = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return duration;
    }

    public List<List<Episode>> getEpisodesByMediaId(int id) {
        Connection connection = Database.get().getConnection();

        List<Episode> episodeList = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement("SELECT * from episode WHERE media_id = ? ORDER BY saison_index ASC, episode_index ASC;");

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                episodeList.add(mapToEpisode(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return mapToEpisodeListBySaisons(episodeList);
    }

    private Episode mapToEpisode(ResultSet rs) throws SQLException {
        return new Episode(
                rs.getInt(1), // id
                rs.getInt(2), // media_id
                rs.getInt(3), // saison_id
                rs.getInt(4), // episode_id
                rs.getString(5), // name
                rs.getInt(6), // duration
                rs.getString(7), // summary
                rs.getString(8) // url
        );
    }

    private List<List<Episode>> mapToEpisodeListBySaisons(List<Episode> episodeList) {
        List<List<Episode>> episodeListBySaisons = new ArrayList<>();
        for(int i = 1; i <= episodeList.get(episodeList.size()-1).getSaisonIndex(); i++) {
            List<Episode> saison = new ArrayList<>();
            for(Episode episode : episodeList) {
                if(episode.getSaisonIndex() == i) {
                    saison.add(episode);
                }
            }
            episodeListBySaisons.add(saison);
        }
        return episodeListBySaisons;
    }
}
