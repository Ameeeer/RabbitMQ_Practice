package ru.itis.controller;

import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.model.CarNews;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SimpleController {
    @Autowired
    RabbitTemplate template;
    @GetMapping("/carNews")
    public String getPageCars(
            ModelMap map
    ) {
        List<CarNews> list = new ArrayList<>();
        template.convertSendAndReceive("getCarNews");
        List<CarNews> news = new Gson()
                .fromJson((String) template.convertSendAndReceive("getCarNews"), (Type) CarNews.class);
        map.put("allNews", news);
        return "index";
    }

    @GetMapping("/habrNews")
    public String getPageHabr(
            ModelMap map
    ) {
        return "index";
    }

    @GetMapping("/filmNews")
    public String getPageFilm(
            ModelMap map
    ) {
        return "index";
    }
}
