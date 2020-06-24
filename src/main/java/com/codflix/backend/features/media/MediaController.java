package com.codflix.backend.features.media;

import com.codflix.backend.core.Template;
import com.codflix.backend.features.episode.EpisodeDao;
import com.codflix.backend.features.mediaType.MediaTypeDao;
import com.codflix.backend.models.Episode;
import com.codflix.backend.models.Media;
import com.codflix.backend.models.MediaType;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaController {
    private final MediaDao mediaDao = new MediaDao();
    private final MediaTypeDao mediaTypeDao = new MediaTypeDao();
    private final EpisodeDao episodeDao = new EpisodeDao();

    public String list(Request request, Response response) {
        List<Media> medias;
        List<MediaType> mediaTypes;

        String title = request.queryParams("title");

        if (title != null && !title.isEmpty()) {
            medias = mediaDao.filterMedias(title);
        } else {
            medias = mediaDao.getAllMedias();
        }


        Map<String, Object> model = new HashMap<>();
        model.put("medias", medias);
        mediaTypes = mediaTypeDao.getAllMediaTypes();
        model.put("mediaTypes", mediaTypes);
        return Template.render("media_list.html", model);
    }

    public String detail(Request request, Response res) {
        int id = Integer.parseInt(request.params(":id"));
        Media media = mediaDao.getMediaById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("media", media);

        if(media.getTypeId() == 2) {
            List<List<Episode>> episodeList = episodeDao.getEpisodesByMediaId(id);
            model.put("saisons", episodeList);
        }

        return Template.render("media_detail.html", model);
    }
}
