package hello.servlet.web.servletmvc;

import java.io.IOException;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.repository.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// http://localhost:8080/servlet-mvc/members/save
@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {
      
    // MemberRepository의 싱글톤 인스턴스를 가져옴
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 클라이언트로부터 전달된 파라미터를 사용하여 회원 정보를 생성
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        
        memberRepository.save(member); //생성된 회원 정보를 저장소에 저장 

        
        request.setAttribute("member", member); // 모델에 데이터를 저장 (request 객체에 member 데이터를 추가)

         // 결과를 보여줄 뷰 페이지로 포워딩
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
