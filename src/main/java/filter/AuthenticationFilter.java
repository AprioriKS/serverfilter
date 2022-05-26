package filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/index")
public class AuthenticationFilter implements Filter {
    private final Set<String> allowedIp = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        allowedIp.add("127.0.0.1");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String clientIp = request.getRemoteAddr();
        if (allowedIp.contains(clientIp)) {
            filterChain.doFilter(request, response);
            return;
        }
        //request.getRequestDispatcher("/WEB-INF/views/noaccess.jsp").forward(request, response);
        System.out.println("ERROR!");
    }
}
