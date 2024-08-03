package hello.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// HTML 콘텐츠를 전송하기 위한 서블릿
// http://localhost:8080/response-html
@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 응답의 Content-Type을 text/html로 설정하여 HTML 콘텐츠임을 명시
    response.setContentType("text/html");
    // 응답의 문자 인코딩을 UTF-8로 설정하여 한글 처리를 가능하게 함
    response.setCharacterEncoding("utf-8");

    // 응답에 HTML 콘텐츠를 쓰기 위해 PrintWriter 객체 생성
    PrintWriter writer = response.getWriter();
    writer.println("<html>");
    writer.println("<body>");
    writer.println(" <div>안녕?</div>");
    writer.println("</body>");
    writer.println("</html>");
  }
}
