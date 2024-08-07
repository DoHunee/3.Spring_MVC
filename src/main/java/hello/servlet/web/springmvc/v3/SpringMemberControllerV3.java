package hello.servlet.web.springmvc.v3;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.repository.MemberRepository;

/**
 * * v3 Model 도입 ViewName 직접 반환
 *
 * @RequestParam 사용
 * @RequestMapping -> @GetMapping, @PostMapping
 */
// http://localhost:8080/springmvc/v3/members
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP GET 요청을 처리하고 "new-form" 뷰 이름을 반환
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";  // 뷰 이름을 반환하여 해당 뷰로 이동
    }

    // HTTP POST 요청을 처리하고 "save-result" 뷰 이름을 반환
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username, // 요청 파라미터 "username"을 메서드 인자로 바인딩
            @RequestParam("age") int age, // 요청 파라미터 "age"를 메서드 인자로 바인딩
            Model model) {  // 모델에 데이터를 담기 위한 객체
        // 받은 요청 파라미터로 Member 객체 생성
        Member member = new Member(username, age);
        memberRepository.save(member);  // 생성된 회원 정보를 저장
        model.addAttribute("member", member);  // 모델에 "member"라는 이름으로 객체 추가
        return "save-result";  // 뷰 이름을 반환하여 해당 뷰로 이동
    }

    // HTTP GET 요청을 처리하고 "members" 뷰 이름을 반환
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();  // 모든 회원 정보를 조회
        model.addAttribute("members", members);  // 모델에 "members"라는 이름으로 리스트 추가
        return "members";  // 뷰 이름을 반환하여 해당 뷰로 이동
    }
}
