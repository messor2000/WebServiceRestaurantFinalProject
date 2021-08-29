package epam.task.finaltaskepam.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import org.apache.log4j.Logger;


@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private final Logger logger = Logger.getLogger(AuthenticationFilter.class);

    public void init(FilterConfig fConfig) {
        logger.info("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        logger.info("Requested Resource::" + uri);

        HttpSession session = req.getSession(false);

        if (session == null && !(uri.endsWith("jsp") || uri.endsWith("login") || uri.endsWith("register"))) {
            logger.error("Unauthorized access request");
            res.sendRedirect("login.jsp");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        //close any resources here
    }
}
