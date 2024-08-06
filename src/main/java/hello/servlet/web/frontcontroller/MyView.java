package hello.servlet.web.frontcontroller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyView {

    // 뷰 파일의 경로를 저장하는 변수
    private final String viewPath;

    // 생성자에서 뷰 경로를 초기화합니다.
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 요청을 받아서 지정된 뷰로 포워딩하는 메서드
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    // 모델 데이터를 포함하여 뷰를 렌더링하는 오버로드된 메서드
    public void render(Map<String, Object> model, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request); // 모델 데이터를 요청 속성으로 변환
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); // 요청을 뷰로 포워딩
    }
    
    // 모델 데이터를 요청 속성으로 변환하는 메서드
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
