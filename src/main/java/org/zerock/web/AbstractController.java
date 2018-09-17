package org.zerock.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


public abstract class AbstractController extends HttpServlet {

    public abstract String getBasic();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("service..................");

        // 똑같다.
//        String path = req.getPathInfo();
        String path = req.getRequestURI().substring(getBasic().length());

        String way = req.getMethod(); //get인지 post인지 받는 코드

        System.out.println(path + " : " + way); //확인

        String methodName = path + way; // writeGET, listGET, writePOST

        Class clz = this.getClass(); // 모든 인스턴스는 자신의 클래스를 알 수 있다.

        try {
            // 클래스에 선언된 메소드를 찾는다. (메소드 이름, 파라미터1,파라미터2)
            System.out.println("methodName: " + methodName);
            Method method = clz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            System.out.println("method: " + method);

            Object result = method.invoke(this,req,resp); //메소드를 실행한다. 결과는 object로 나온다.

            String returnURL = (String)result;

            System.out.println("RETURN: " + returnURL);

            if(returnURL.startsWith("redirect")){
                resp.sendRedirect(returnURL.substring(9));
                return;
            }
            req.getRequestDispatcher("/WEB-INF/" + returnURL + ".jsp").forward(req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
