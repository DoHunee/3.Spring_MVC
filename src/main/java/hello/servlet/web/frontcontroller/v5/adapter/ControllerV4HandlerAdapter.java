package hello.servlet.web.frontcontroller.v5.adapter;

import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// ControllerV4를 처리하기 위한 핸들러 어댑터
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    // handler 객체가 ControllerV4 타입인지 확인
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

     // ControllerV4를 사용하여 요청을 처리하고 ModelView 객체를 반환
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

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
