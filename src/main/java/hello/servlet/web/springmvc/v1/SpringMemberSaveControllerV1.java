package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// http://localhost:8080/springmvc/v1/members/save
@Controller
public class SpringMemberSaveControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        
        // HTTP 요청에서 전달된 "username"과 "age" 파라미터를 추출
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        
        // 추출한 정보를 사용하여 새로운 Member 객체 생성
        Member member = new Member(username, age);
        System.out.println("member = " + member);

        // 생성된 Member 객체를 MemberRepository에 저장
        memberRepository.save(member);
        
         // 반환할 뷰와 모델 데이터를 포함하는 ModelAndView 객체 생성
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}
