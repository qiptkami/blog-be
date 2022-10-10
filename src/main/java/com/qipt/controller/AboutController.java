package com.qipt.controller;

import com.qipt.pojo.Type;
import com.qipt.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AboutController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/about")
    public String about(Model model) {
        List<Type> types = typeService.selectList();
        model.addAttribute("types", types);
        return "about";
    }
}
