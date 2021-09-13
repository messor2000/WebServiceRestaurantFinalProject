package command.visitor;

import epam.task.finaltaskepam.command.visitor.Register;
import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.user.AppUserDao;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.AppUserPurse;
import epam.task.finaltaskepam.service.user.AppUserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import epam.task.finaltaskepam.servlet.FrontController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import service.AppUserServiceTests;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.sql.SQLException;

/**
 * @author Aleksandr Ovcharenko
 */
public class VisitorAbilitiesTests {

    private static final Logger logger = LogManager.getLogger(AppUserServiceTests.class);

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession session = Mockito.mock(HttpSession.class);
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    AppUser user = Mockito.mock(AppUser.class);

//    @Before
//    public static void precondition(){
//        logger.info("Registration Test");
////        testUser.setIdUser(55);
//        testUser.setUsername("testUser");
//        testUser.setEmail("testmail@gmail.com");
//        testUser.setPassword("password");
//
//        testUser.setPurse(testPurse);
//    }

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);
        user = mock(AppUser.class);
    }



    @Test
    public void registrationTest() throws IOException {
        when(request.getParameter("command")).thenReturn("registration");
        when(session.getAttribute("username")).thenReturn(user.getUsername());
        when(session.getAttribute("role")).thenReturn(user.getRole());

//        when(session.getAttribute("name_en")).thenReturn(faculty.getNameEn());

        new FrontController().doGet(request, response);
        verify(response, atLeast(1)).sendRedirect(anyString());
    }


}
