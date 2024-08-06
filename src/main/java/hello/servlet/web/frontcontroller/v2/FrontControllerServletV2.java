package hello.servlet.web.frontcontroller.v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 서블릿 매핑을 통해 URL 패턴을 정의합니다.
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    // 컨트롤러 객체를 저장할 맵을 선언합니다.
    private final Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 생성자에서 URL 경로에 따라 컨트롤러 객체를 매핑합니다.
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 URI를 추출합니다.
        String requestURI = request.getRequestURI();

        // 매핑된 컨트롤러를 찾습니다.
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            // 컨트롤러가 매핑되지 않은 경우 404 상태 코드를 설정합니다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 컨트롤러를 실행하고 반환된 뷰 객체를 사용하여 뷰를 렌더링합니다.
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
