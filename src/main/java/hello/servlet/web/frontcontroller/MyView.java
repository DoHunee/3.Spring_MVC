package hello.servlet.web.frontcontroller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyView {

   // 뷰 파일의 경로를 저장하는 변수
    private String viewPath;

    // 생성자에서 뷰 경로를 초기화합니다.
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 요청을 받아서 지정된 뷰로 포워딩하는 메서드
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
