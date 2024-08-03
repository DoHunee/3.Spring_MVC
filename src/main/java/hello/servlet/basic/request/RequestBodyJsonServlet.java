package hello.servlet.basic.request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//http://localhost:8080/request-body-json
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

  // JSON을 객체로 변환하기 위한 ObjectMapper 인스턴스
  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    ServletInputStream inputStream = request.getInputStream();  // 요청 바디에서 입력 스트림을 가져옴
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);  // 입력 스트림을 문자열로 변환
    System.out.println("messageBody = " + messageBody); // 요청 바디 출력

      // JSON 문자열을 HelloData 객체로 변환
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

    // 변환된 객체의 username과 age를 출력
    System.out.println("helloData.username = " + helloData.getUsername());
    System.out.println("helloData.age = " + helloData.getAge());

     // 응답으로 "ok"를 보냄
    response.getWriter().write("ok");
  }
}