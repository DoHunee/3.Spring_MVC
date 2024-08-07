package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// http://localhost:8080/springmvc/v1/members/new-form
@Controller  // 스프링 부트 3.0 이상부터는  @Controller 가 있어야 스프링 컨트롤러로 인식한다
// @RequestMapping // 이것만 있으면 안된다!
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
