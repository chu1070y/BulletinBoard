package org.zerock.web;

import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@WebServlet("/member/*")
@Log4j
public class MemberController extends AbstractController {

    public String loginGET(HttpServletRequest req, HttpServletResponse resp)throws Exception{

        log.info("login Post");

        Cookie loginCookie = new Cookie("login", URLEncoder.encode("Chu Yeon Hoon","UTF-8"));

        loginCookie.setMaxAge(60*60*24); //쿠키가 유지되는 시간 설정.. 시간이 지나면 없어진다.\
        loginCookie.setPath("/");   //path를 안주면 member라는 경로에만 쿠키가 쓰인다.
        resp.addCookie(loginCookie); //쿠키에 쿠키를 넣어라

        return "/board/list";
    }

    @Override
    public String getBasic() {
        return "/member/";
    }

}
