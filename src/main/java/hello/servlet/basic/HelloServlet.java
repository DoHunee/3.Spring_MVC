package hello.servlet.basic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet{
   
  @Override
    protected void service(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("HelloServlet.service()"); // 스프링을 동작하면 이 메소드가 실행된다.
        System.out.println("request = " + request); //request = org.apache.catalina.connector.RequestFacade@13913c21
        System.out.println("response = " + response); //response = org.apache.catalina.connector.ResponseFacade@423571ee  

        // http://localhost:8080/hello?username=jang
        // 콘솔 출력 : jang
        String username = request.getParameter("username"); //username = null
        System.out.println("username = " + username);

        // http://localhost:8080/hello?username=jang
        // 화면에 : Hello jang
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("Hello " + username);
    }

}
