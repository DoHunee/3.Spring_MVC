package hello.servlet.web.springmvc.v2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 클래스 단위 -> 메서드 단위
// @RequestMapping 클래스 레벨과 메서드 레벨 조합
// http://localhost:8080/springmvc/v2/members
@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    // "/new-form" 경로로의 요청을 처리하는 메서드
    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form"); // "new-form" 뷰를 반환
    }

    // "/save" 경로로의 요청을 처리하는 메서드
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        // HTTP 요청에서 "username"과 "age" 파라미터를 추출
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 추출한 정보를 사용하여 Member 객체 생성 및 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        // "save-result" 뷰와 함께 member 데이터를 반환
        ModelAndView mav = new ModelAndView("save-result");
        mav.addObject("member", member);
        return mav;
    }

    // 클래스 레벨의 @RequestMapping 경로로의 기본 요청 처리
    @RequestMapping
    public ModelAndView members() {
        // 모든 회원 목록을 가져와서 모델에 추가
        List<Member> members = memberRepository.findAll();
        ModelAndView mav = new ModelAndView("members");
        mav.addObject("members", members);
        return mav;
    }
}
