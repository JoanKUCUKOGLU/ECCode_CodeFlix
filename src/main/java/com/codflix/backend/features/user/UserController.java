package com.codflix.backend.features.user;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import com.codflix.backend.models.User;
import com.codflix.backend.utils.GlobalData;
import com.codflix.backend.utils.URLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Session;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserDao userDao = new UserDao();

    public String login(Request request, Response response) {
        if (request.requestMethod().equals("GET")) {
            Map<String, Object> model = new HashMap<>();
            return Template.render("auth_login.html", model);
        }

        // Get parameters
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String email = query.get("email");
        String password = null;

        try {
            password = hashToSHA256(query.get("password"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Authenticate user
        User user = userDao.getUserByCredentials(email, password);
        if (user == null) {
            logger.info("User not found. Redirect to login");
            response.removeCookie("session");
            response.redirect("/login");
            return "KO";
        }

        // Create session
        GlobalData.getInstance().login(user);
        Session session = request.session(true);
        session.attribute("user_id", user.getId());
        response.cookie("/", "user_id", "" + user.getId(), 3600, true);

        // Redirect to medias page
        response.redirect(Conf.ROUTE_LOGGED_ROOT);
        return "OK";
    }

    public String signUp(Request request, Response response) {
        if (request.requestMethod().equals("GET")) {
            Map<String, Object> model = new HashMap<>();
            return Template.render("auth_signup.html", model);
        }

        // Get parameters
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String email = query.get("email");
        String password_confirm = query.get("password_confirm");
        String password = query.get("password");

        // Check if email is valid && available
        if(!isEmailValid(email) || !isEmailAvailable(email)) {
            logger.info("Email invalide");
            response.removeCookie("session");
            response.redirect("/signup");
            return "KO";
        }
        if (!isPasswordValid(password) || !password.equals(password_confirm)) {
            logger.info("Password incorect");
            response.removeCookie("session");
            response.redirect("/signup");
            return "KO";
        }

        try {
            password = hashToSHA256(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        userDao.createNewUser(email, password);

        // Authenticate user (and test if user created successfully)
        User user = userDao.getUserByCredentials(email, password);
        if (user == null) {
            logger.info("User has not been created");
            response.removeCookie("session");
            response.redirect("/signup");
            return "KO";
        }

        // Create session
        GlobalData.getInstance().login(user);
        Session session = request.session(true);
        session.attribute("user_id", user.getId());
        response.cookie("/", "user_id", "" + user.getId(), 3600, true);

        // Redirect to medias page
        response.redirect(Conf.ROUTE_LOGGED_ROOT);

        return "CREATED";
    }

    public String detail(Request request, Response response, String type) {
        if(request.requestMethod().equals("GET")) {
            Map<String, Object> model = new HashMap<>();
            model.put("currentUser", GlobalData.getInstance().getCurrentUser());
            return Template.render("user.html", model);
        }

        Map<String, String> query = URLUtils.decodeQuery(request.body());

        if(type == "updateEmail") {
            String email = query.get("email");
            String password = query.get("password");

            try {
                if(isEmailValid(email) && hashToSHA256(password).equals(GlobalData.getInstance().getCurrentUser().getPassword())) {
                    userDao.updateUserEmail(email);
                    GlobalData.getInstance().getCurrentUser().setEmail(email);
                    response.redirect("/user");
                    return "OK";
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else if (type == "updatePassword") {
            String oldPassword = query.get("old_password");
            String newPassword = query.get("new_password");
            String newPasswordConfirmation = query.get("new_password_confirmation");

            try {
                if(hashToSHA256(oldPassword).equals(GlobalData.getInstance().getCurrentUser().getPassword()) &&
                        isPasswordValid(newPassword) && newPassword.equals(newPasswordConfirmation)) {
                    newPassword = hashToSHA256(newPassword);
                    userDao.updateUserPassword(newPassword);
                    GlobalData.getInstance().getCurrentUser().setPassword(newPassword);
                    response.redirect("/user");
                    return "OK";
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "OK";
        }

        return "KO";
    }

    public String logout(Request request, Response response) {
        GlobalData.getInstance().logout();
        Session session = request.session(false);
        if (session != null) {
            session.invalidate();
        }
        response.removeCookie("session");
        response.removeCookie("JSESSIONID");
        response.redirect("/");

        return "";
    }

    public String delete(Request request, Response response) {
        userDao.deleteUser(GlobalData.getInstance().getCurrentUser().getId());
        GlobalData.getInstance().logout();
        Session session = request.session(false);
        if (session != null) {
            session.invalidate();
        }
        response.removeCookie("session");
        response.removeCookie("JSESSIONID");
        response.redirect("/");

        return "";
    }

    private String hashToSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    private boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9+._%\\-+]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+");
        return pattern.matcher(email).matches();
    }

    private boolean isEmailAvailable(String email) {
        // Authenticate user
        User user = userDao.getUserByEmail(email);
        return user == null;
    }

    private boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$");
        return pattern.matcher(password).matches();
    }
}
