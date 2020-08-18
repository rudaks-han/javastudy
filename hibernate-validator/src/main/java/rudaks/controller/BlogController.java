package rudaks.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rudaks.sdo.BlogCdo;

@RestController
@RequestMapping("blogs")
public class BlogController {

    @PostMapping
    public void register(@RequestBody @Valid BlogCdo blogCdo) {

        System.out.println(blogCdo);
    }
}
