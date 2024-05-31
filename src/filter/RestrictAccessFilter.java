package filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class RestrictAccessFilter
 */
@WebFilter(filterName= "RestrictAccessFilter", urlPatterns = "/*" )
public class RestrictAccessFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public RestrictAccessFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	      
		  HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;

	        // Ottieni l'URI della richiesta
	        String uri = httpRequest.getRequestURI();

	        // Controlla se l'URI contiene WEB-INF o META-INF
	        if (uri.contains("/WEB-INF/") || uri.contains("/META-INF/")) {
	            // Reindirizza alla home del sito
	            httpResponse.sendRedirect(httpRequest.getContextPath() + "/Home.jsp");
	            return;
	        }

	        // Controlla i parametri della richiesta
	        Map<String, String[]> parameters = httpRequest.getParameterMap();
	        for (String[] values : parameters.values()) {
	            for (String value : values) {
	                if (value.contains("WEB-INF") || value.contains("META-INF")) {
	                    // Reindirizza alla home del sito
	                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/Home.jsp");
	                    return;
	                }
	            }
	        }

	        // Continua con la catena di filtri
	        chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
