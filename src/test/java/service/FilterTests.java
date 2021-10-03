package service;

import epam.task.finaltaskepam.servlet.filter.EncodingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * @author Aleksandr Ovcharenko
 */
public class FilterTests {

    EncodingFilter filter;
    HttpSession session;
    FilterChain chain;
    HttpServletRequest request;
    HttpServletResponse response;
    FilterConfig encFilterConfig;
    FilterConfig authFilterConfig;

    @Before
    public void setUp() throws Exception {
        session = mock(HttpSession.class);
        chain = mock(FilterChain.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        encFilterConfig = mock(FilterConfig.class);
        authFilterConfig = mock(FilterConfig.class);

        filter = new EncodingFilter();
        filter.init(encFilterConfig);
    }

    @After
    public void tearDown() {
        filter.destroy();

    }

    @Test
    public void encFilterShouldSetProperEncoding() throws ServletException, IOException {
        when(request.getCharacterEncoding()).thenReturn(null);
        filter.doFilter(request, response, chain);
        verify(request, times(1)).setCharacterEncoding(anyString());
    }

}
