package cn.edu.svtcc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CheckingFilter
 * ������������ֱ�ӷ��ʹ���Ա����ķ���
 */
@WebFilter("/admin/*")
public class CheckingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckingFilter() {
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
		/**
		 * ��request��responseת��ΪHTTPRequest��HTTPRespobse
		 */
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		//�ж��Ƿ��ǹ���Ա��¼
		if(req.getSession().getAttribute("admin")==null) {
			resp.getWriter().write("��ʹ�ù���Ա��¼");
			return;
		} else {
			//���ȷ���ǹ���Ա��¼�������
			chain.doFilter(req, resp);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
