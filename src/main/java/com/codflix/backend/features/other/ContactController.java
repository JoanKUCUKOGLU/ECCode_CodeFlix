package com.codflix.backend.features.other;

import com.codflix.backend.core.Template;
import com.codflix.backend.features.user.UserController;
import com.codflix.backend.utils.URLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public String display(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return Template.render("contact.html", model);
    }

    public String contact(Request request, Response response) {
        if (request.requestMethod().equals("GET")) {
            Map<String, Object> model = new HashMap<>();
            return Template.render("contact.html", model);
        }

        // Get parameters
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String email = query.get("email");
        String message = query.get("message");

        return "OK";
    }
}
