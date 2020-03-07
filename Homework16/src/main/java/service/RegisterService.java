package service;



import config.FreemarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import repository.UserRepository;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class RegisterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageSenderService messageSenderService;

    public Optional<User> getUser(Long id) {
        return userRepository.read(id);
    }

    public Optional<User> setUser(User user, ServletContext servletContext){
        Optional<User> userOptional = userRepository.create(user);
        String email = null;
        if (userOptional.isPresent()) {
            email = userOptional.get().getEmail();
        }
        Template template = null;
        try {
            template = FreemarkerConfig.getInstance(servletContext.getRealPath("/WEB-INF/freemarker")).getTemplate("registerEmail.ftl");
        } catch (IOException e) {
            System.out.println("Can not getTemplate");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        String message = null;
        try {
            message = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (TemplateException e) {
            System.out.println(e.getBlamedExpressionString());
        }
        messageSenderService.sendMessage(email, message);

        return userOptional;
    }

}

