package hello.servlet.basic.response;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// JSON 데이터를 처리하기 위한 서블릿
// http://localhost:8080/response-json
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

  // JSON 처리를 위한 ObjectMapper 인스턴스 생성
  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // 응답의 Content-Type을 application/json으로 설정하여 JSON 데이터를 전송함을 명시
    response.setHeader("content-type", "application/json");
    // 응답의 문자 인코딩을 UTF-8로 설정하여 한글 처리를 가능하게 함
    response.setCharacterEncoding("utf-8");
    
    // HelloData 객체 생성 및 데이터 설정
    HelloData data = new HelloData();
    data.setUsername("kim");
    data.setAge(20);

    // 객체를 JSON 문자열로 변환 {"username":"kim","age":20}
    String result = objectMapper.writeValueAsString(data);

    // JSON 데이터를 응답 본문에 씀
    response.getWriter().write(result);
  }
}