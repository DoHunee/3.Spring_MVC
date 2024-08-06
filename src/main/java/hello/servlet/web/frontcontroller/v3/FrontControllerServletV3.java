package hello.servlet.web.frontcontroller.v3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 이 서블릿은 "/front-controller/v3/*"의 모든 요청을 처리합니다.
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    // 각 요청 URI에 대응하는 컨트롤러를 매핑합니다.
    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    // 생성자에서 URI별 컨트롤러 인스턴스를 맵에 저장합니다.
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

     // 서비스 메서드는 HTTP 요청을 처리합니다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 컨트롤러가 없으면 404 에러를 응답합니다.
            return;
        }
        
        Map<String, String> paramMap = createParamMap(request);  // 요청 파라미터를 맵으로 변환합니다.
        ModelView mv = controller.process(paramMap);  // 컨트롤러를 실행하고 결과로 ModelView를 받습니다.
        String viewName = mv.getViewName();  // ModelView에서 뷰 이름을 가져옵니다.
        MyView view = viewResolver(viewName);  // 뷰 이름을 기준으로 MyView 인스턴스를 생성합니다.
        view.render(mv.getModel(), request, response);  // 뷰를 렌더링합니다.
    }

    // 요청의 파라미터를 맵으로 변환하는 메서드입니다.
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                request.getParameter(paramName)));
        return paramMap;
    }

    // 뷰 이름을 받아서 완전한 JSP 경로로 변환하는 뷰 리졸버 메서드입니다.
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
