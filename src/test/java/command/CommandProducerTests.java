package command;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.servlet.command.CommandProducer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static epam.task.finaltaskepam.repo.Request.SHOW_MENU;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

/**
 * @author Aleksandr Ovcharenko
 */
public class CommandProducerTests {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(request.getParameter("userId")).thenReturn("1");

    }

    @Test
    public void shouldGetValidCommand() {
        Command c = CommandProducer.getInstance().getCommandForUser("admin", SHOW_MENU);
        Assert.assertNotNull(c);
    }
}
