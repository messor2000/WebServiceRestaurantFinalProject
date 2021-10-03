package epam.task.finaltaskepam.command.visitor;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.util.Constants;
import epam.task.finaltaskepam.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Aleksandr Ovcharenko

 * ChangeLanguage class is used to handle request to change language of
 * application.
 */
public class Language implements Command {

    private final ArrayList<String> supportedLanguages = new ArrayList<>();

    public Language() {
        supportedLanguages.add(Constants.ENGLISH);
        supportedLanguages.add(Constants.RUSSIAN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String language = request.getParameter(Constants.LANGUAGE);

        HttpSession session = request.getSession(true);
        if (language != null) {
            if (!supportedLanguages.contains(language)) {
                language = Constants.ENGLISH;
            }

            session.setAttribute(Constants.SESSION_LANGUAGE, language);
        }

        String previousQuery = Util.getPreviousQuery(request);
        response.sendRedirect(previousQuery);
    }
}
