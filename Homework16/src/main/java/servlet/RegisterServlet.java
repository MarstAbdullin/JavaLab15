package servlet;

import model.User;
import org.springframework.context.ApplicationContext;
import service.RegisterService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private RegisterService registerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        registerService = springContext.getBean(RegisterService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        registerService.setUser(User.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build(), getServletContext());
        try {
            response.sendRedirect("/Homework16_war_exploded/sendMessage");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Can not reach sendMessage");
        }
    }
}
