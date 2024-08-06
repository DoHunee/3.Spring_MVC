package hello.servlet.web.frontcontroller.v5.adapter;

import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// ControllerV3 타입의 컨트롤러를 처리하는 핸들러 어댑터 구현
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    // handler 객체가 ControllerV3 타입인지 확인
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    // HTTP 요청을 ControllerV3 타입의 컨트롤러로 처리하고 ModelView 객체를 반환
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        return mv;
    }
      
    // 요청에서 모든 파라미터를 추출하여 맵으로 변환하는 메서드
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                request.getParameter(paramName)));
        return paramMap;
    }
}
