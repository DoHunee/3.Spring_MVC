package hello.servlet.web.frontcontroller.v4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 서블릿 매핑, "/front-controller/v4/*"로 들어오는 모든 요청을 이 서블릿이 처리
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    // 각 URL에 대응하는 컨트롤러를 매핑하는 맵
    private final Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 생성자에서 URL 별로 컨트롤러 인스턴스를 매핑합니다.
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);

        // 컨트롤러가 없으면 404 에러를 응답
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(request); // HTTP 요청으로부터 파라미터를 추출하여 맵에 저장
        Map<String, Object> model = new HashMap<>(); // 컨트롤러에서 사용될 모델 객체

        String viewName = controller.process(paramMap, model); // 컨트롤러 실행 후 뷰 이름을 반환

        MyView view = viewResolver(viewName); // 뷰 이름에 따라 MyView 객체 생성
        view.render(model, request, response); // MyView 객체를 사용하여 모델 데이터와 함께 뷰 렌더링
    }

    // 요청에서 모든 파라미터를 추출하여 맵으로 변환하는 메서드
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                request.getParameter(paramName)));
        return paramMap;
    }

    // 뷰 이름을 받아 완전한 JSP 경로로 변환하는 뷰 리졸버 메서드
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
