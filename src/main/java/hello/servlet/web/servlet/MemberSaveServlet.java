package hello.servlet.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 서블릿 매핑, 클라이언트 요청 URL을 지정
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
  // 싱글톤 인스턴스로 회원 정보 저장소 사용
  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("MemberSaveServlet.service");

    // 요청에서 사용자 이름과 나이를 추출
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age); // 추출된 데이터를 바탕으로 새로운 회원 객체 생성
    System.out.println("member = " + member); // 생성된 객체 정보를 로그로 출력

    // 회원 객체를 저장소에 저장
    memberRepository.save(member);

    // 응답의 컨텐츠 유형과 문자 인코딩 설정
    response.setContentType("text/html");
    response.setCharacterEncoding("utf-8");

    // 클라이언트에 HTML 응답 전송
    PrintWriter w = response.getWriter();
    w.write(
        "<html>\n" +
        "<head>\n" +
        " <meta charset=\"UTF-8\">\n" +
        "</head>\n" +
        "<body>\n" +
        "성공\n" + // 성공 메시지
        "<ul>\n" +
        " <li>id=" + member.getId() + "</li>\n" + // 회원 ID 출력
        " <li>username=" + member.getUsername() + "</li>\n" + // 사용자 이름 출력
        " <li>age=" + member.getAge() + "</li>\n" + // 사용자 나이 출력
        "</ul>\n" +
        "<a href=\"/index.html\">메인</a>\n" + // 메인 페이지로 이동하는 링크
        "</body>\n" +
        "</html>");
  }
}