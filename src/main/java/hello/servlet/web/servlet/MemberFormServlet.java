package hello.servlet.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// http://localhost:8080/servlet/members/new-form
@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    response.setContentType("text/html");
    response.setCharacterEncoding("utf-8");

    // 클라이언트에 데이터를 출력하기 위한 PrintWriter 객체 생성
    PrintWriter w = response.getWriter();
    
     // HTML 폼을 클라이언트에 보내는 HTML 코드 작성
    w.write("<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        " <meta charset=\"UTF-8\">\n" +
        " <title>Title</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "<form action=\"/servlet/members/save\" method=\"post\">\n" +
        " username: <input type=\"text\" name=\"username\" />\n" +
        " age: <input type=\"text\" name=\"age\" />\n" +
        " <button type=\"submit\">전송</button>\n" +
        "</form>\n" +
        "</body>\n" +
        "</html>\n");
  }
}
