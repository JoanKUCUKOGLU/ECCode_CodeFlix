package com.codflix.backend.features.media;

import com.codflix.backend.core.Database;
import com.codflix.backend.features.episode.EpisodeDao;
import com.codflix.backend.models.Media;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MediaDao {
    private final EpisodeDao episodeDao = new EpisodeDao();
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public List<Media> getAllMedias() {
        List<Media> medias = new ArrayList<>();
        Media currMedia = null;

        Connection connection = Database.get().getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT media.*, genre.name FROM media LEFT JOIN media_genre ON media_genre.media_id = media.id LEFT JOIN genre ON genre.id = media_genre.genre_id ORDER BY release_date DESC, id ASC;");
            while (rs.next()) {
                if(currMedia == null || currMedia.getId() != rs.getInt(1)) {
                    currMedia = mapToMedia(rs);
                    if(currMedia.getDuration() == 0) {
                        currMedia.setDuration(episodeDao.getTotalDuration(currMedia.getId()));
                    }
                    medias.add(currMedia);
                }

                medias.get(medias.size()-1).addGenre(rs.getString(10));

            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return medias;
    }

    public List<Media> filterMedias(String title) {
        List<Media> medias = new ArrayList<>();
        Media currMedia = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT media.*, genre.name FROM media LEFT JOIN media_genre ON media_genre.media_id = media.id LEFT JOIN genre ON genre.id = media_genre.genre_id WHERE title LIKE ? ORDER BY release_date DESC, id ASC;");
            st.setString(1, "%" + title + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if(currMedia == null || currMedia.getId() != rs.getInt(1)) {
                    currMedia = mapToMedia(rs);
                    if(currMedia.getDuration() == 0) {
                        currMedia.setDuration(episodeDao.getTotalDuration(currMedia.getId()));
                    }
                    medias.add(currMedia);
                }
                medias.get(medias.size()-1).addGenre(rs.getString(10));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return medias;
    }

    public Media getMediaById(int id) {
        Media media = null;
        List<String> genreList = new ArrayList<>();

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT media.*, genre.name FROM media LEFT JOIN media_genre ON media_genre.media_id = media.id LEFT JOIN genre ON genre.id = media_genre.genre_id WHERE media.id = ?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if(media == null) {
                    media = mapToMedia(rs);
                    if(media.getDuration() == 0) {
                        media.setDuration(episodeDao.getTotalDuration(media.getId()));
                    }
                }
                genreList.add(rs.getString(10));
            }
            if(media != null) {
                media.setGenreList(genreList);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return media;
    }

    private Media mapToMedia(ResultSet rs) throws SQLException, ParseException {
        return new Media(
                rs.getInt(1), // id
                new ArrayList<>(), // genre list
                rs.getString(2), // title
                rs.getInt(3), // type
                rs.getString(4), // status
                DATE_FORMAT.parse(rs.getString(5)), // release_date
                rs.getString(6), // summary
                rs.getInt(7),
                rs.getString(8),
                rs.getString(9) // trailer_url
        );
    }
}
