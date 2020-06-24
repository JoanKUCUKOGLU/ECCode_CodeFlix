package com.codflix.backend.features.mediaType;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.Media;
import com.codflix.backend.models.MediaType;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MediaTypeDao {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public List<MediaType> getAllMediaTypes() {
        List<MediaType> mediaTypes = new ArrayList<>();

        Connection connection = Database.get().getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM media_type;");
            while (rs.next()) {
                mediaTypes.add(mapToMediaType(rs));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return mediaTypes;
    }

    private MediaType mapToMediaType(ResultSet rs) throws SQLException, ParseException {
        return new MediaType(
                rs.getInt(1), // id
                rs.getString(2) // name
        );
    }
}
