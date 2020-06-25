package com.codflix.backend;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Database;
import com.codflix.backend.core.Template;
import com.codflix.backend.features.genre.GenreController;
import com.codflix.backend.features.history.HistoryController;
import com.codflix.backend.features.media.MediaController;
import com.codflix.backend.features.other.ContactController;
import com.codflix.backend.features.other.HomeController;
import com.codflix.backend.features.user.UserController;
import com.codflix.backend.middlewares.AuthMiddleware;
import com.codflix.backend.middlewares.LoggerMiddleware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.simple.SimpleLogger;
import spark.Spark;

public class App {
    static {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
    }

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        initializeServer();

        // Controllers
        // Additional controllers should be declared and used here
        HomeController home = new HomeController();
        UserController user = new UserController();
        GenreController genre = new GenreController();
        MediaController media = new MediaController();
        ContactController contact = new ContactController();
        HistoryController history = new HistoryController();

        // Routes
        // Every request should be mapped here to a controller method
        logger.info("Welcome to CodFlix Backend!");
        Spark.get("/login", (req, res) -> user.login(req, res));
        Spark.post("login", (req, res) -> user.login(req, res));
        Spark.get("/signup", (req, res) -> user.signUp(req, res));
        Spark.post("signup", (req, res) -> user.signUp(req, res));
        Spark.get("logout", (req, res) -> user.logout(req, res));

        Spark.get("/genres/", (req, res) -> genre.list(req, res));
        Spark.get("/user", (req, res) -> user.detail(req, res, "launch"));
        Spark.post("user/updateEmail", (req, res) -> user.detail(req, res, "updateEmail"));
        Spark.post("user/updatePassword", (req, res) -> user.detail(req, res, "updatePassword"));
        Spark.get("/user/delete", (req, res) -> user.delete(req, res));
        Spark.get("/medias/:id", (req, res) -> media.detail(req, res));
        Spark.get("/medias/", (req, res) -> media.list(req, res));
        Spark.get("/contact", (req, res) -> contact.display(req, res));
        Spark.post("contact", (req, res) -> contact.contact(req, res));
        Spark.get("/histories/", (req, res) -> history.list(req, res));

        Spark.get("/", (req, res) -> home.home(req, res));
    }

    private static void initializeServer() {
        Template.initialize();
        Database.get().checkConnection();

        // Display exceptions in logs
        Spark.exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // Serve static files (img/css/js)
        Spark.staticFiles.externalLocation(Conf.STATIC_DIR.getPath());

        // Configure server port
        Spark.port(Conf.HTTP_PORT);

        // Log each request with the HTTP verb (GET / POST / etc)
        if (Conf.DEBUG_MODE) {
            final LoggerMiddleware log = new LoggerMiddleware();
            Spark.before(log::process);
        }

        // Protect logged routes
        final AuthMiddleware auth = new AuthMiddleware();
        Spark.before((auth::process));
    }

}
