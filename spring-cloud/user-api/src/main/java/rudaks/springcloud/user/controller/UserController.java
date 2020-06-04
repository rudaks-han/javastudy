package rudaks.springcloud.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private Environment env;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("status/check")
    public String status() {
        return "working on port " + env.getProperty("local.server.port");
    }

    @GetMapping("test")
    public String test() {
        return "prop1 : " + env.getProperty("prop1")
            + "<br/>sameProp: " + env.getProperty("sameProp")
            + "<br/>where: " + env.getProperty("where");
    }
}
