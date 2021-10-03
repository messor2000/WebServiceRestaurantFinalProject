package command;

import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.servlet.FrontController;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * @author Aleksandr Ovcharenko
 */
public class CommandTests {

    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    RequestDispatcher dispatcher;

    @Before
    public void setUp() throws ServletException, IOException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(session.getAttribute("userRole")).thenReturn(null);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);
        doNothing().when(request).setAttribute(anyString(), anyObject());
    }

    @Test
    public void testLogin() throws IOException {
        when(request.getParameter("command")).thenReturn("login");
        new FrontController().doGet(request, response);
//        new FrontController().doPost(request, response);
        verify(response, times(2)).sendRedirect(anyString());
    }


    @Test
    public void testNoCommand() throws IOException {
        when(request.getParameter("command")).thenReturn("Ooops something went wrong");
        new FrontController().doGet(request, response);
        verify(response, times(1)).sendRedirect(anyString());
    }
}
