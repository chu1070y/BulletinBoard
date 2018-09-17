package org.zerock.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/**/*")
public class SampleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init sample filter");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("do sample filter");

        //다운캐스팅
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        String pageStr = servletRequest.getParameter("page");

        try{
            if(pageStr == null){
                throw  new Exception("NULL");
            }

            int page  = Integer.parseInt(pageStr);

            if(page < 1){
                //강제로 예외 발생
                throw new Exception("PAGE NUM");
            }

        }catch (NumberFormatException e){
            resp.sendRedirect("/error");
            return;
        }catch (Exception ee){

        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy sample filter");

    }



}
