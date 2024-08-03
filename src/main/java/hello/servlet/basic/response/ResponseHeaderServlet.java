package hello.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

 
// http://localhost:8080/response-header
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // [status-line]
    response.setStatus(HttpServletResponse.SC_OK); //  HTTP 상태 코드를 200(OK)로 설정
    // response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //  상태 코드를 400(Bad Request)로 설정
    
    // [response-headers]
    response.setHeader("Content-Type", "text/plain;charset=utf-8"); // Content-Type 헤더를 설정 (응답의 콘텐츠 타입과 인코딩 정보)
    response.setHeader("Cache-Control", "no-cache, no-store, mustrevalidate"); // 캐시 무효화를 위한 헤더 설정
    response.setHeader("Pragma", "no-cache");  // 이전 HTTP/1.0 캐시 정책을 위한 헤더 설정
    response.setHeader("my-header", "hello"); // 사용자 정의 헤더 설정
    
    // [Header 편의 메서드]
    content(response);
    cookie(response);
    redirect(response);
    
    // [message body]
    PrintWriter writer = response.getWriter();
    // writer.println("안녕하세요");
    writer.println("ok");

  }

  // Content 편의 메서드
  private void content(HttpServletResponse response) {
    //Content-Type: text/plain;charset=utf-8
    //Content-Length: 2
    //response.setHeader("Content-Type", "text/plain;charset=utf-8");
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
    //response.setContentLength(2); //(생략시 자동 생성)
   }

  // 쿠키 편의 메서드
  private void cookie(HttpServletResponse response) {
    //Set-Cookie: myCookie=good; Max-Age=600;
    //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
    Cookie cookie = new Cookie("myCookie", "good");
    cookie.setMaxAge(600); //600초
    response.addCookie(cookie);
   }   

  // redirect 편의 메서드
  private void redirect(HttpServletResponse response) throws IOException {
    //Status Code 302
    //Location: /basic/hello-form.html
    //response.setStatus(HttpServletResponse.SC_FOUND); //302
    //response.setHeader("Location", "/basic/hello-form.html");
    response.sendRedirect("/basic/hello-form.html");}
  
}