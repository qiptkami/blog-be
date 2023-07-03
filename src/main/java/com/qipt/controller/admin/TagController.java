package com.qipt.controller.admin;

import com.qipt.pojo.Tag;
import com.qipt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{id}")
    public String updatePage(@PathVariable Long id, Model model) {
        model.addAttribute("type", tagService.selectOne(id));
        return "admin/types-input";
    }

    @GetMapping("/tags/input")
    public String insertPage(Model model) {
        model.addAttribute("type", new Tag());
        return "admin/types-input";
    }

    @PostMapping("/tags")
    public String insert(@Valid Tag type, BindingResult result, RedirectAttributes attributes) {
        if (tagService.selectOne(type.getName()) != null) {
            result.rejectValue("name", "nameError", "该分类已存在");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Tag t = tagService.insert(type);
        if (t == null) {
            attributes.addFlashAttribute("errMsg","新增失败");
        } else {
            attributes.addFlashAttribute("successMsg","新增成功");
        }
        return "redirect:/admin/types";  //返回到 /admin/type 请求 再去查询
    }

    @PutMapping("/tags/{id}")
    public String update(@PathVariable Long id, @Valid Tag type, BindingResult result, RedirectAttributes attributes) {
        if (tagService.selectOne(type.getName()) != null) {
            result.rejectValue("name", "nameError", "该分类已存在");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Tag t = tagService.update(id, type);
        if (t == null) {
            attributes.addFlashAttribute("errMsg","更新失败");
        } else {
            attributes.addFlashAttribute("successMsg","更新成功");
        }
        return "redirect:/admin/types";  //返回到 /admin/type 请求 再去查询
    }

    @DeleteMapping("/tags/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            tagService.delete(id);
        } catch (RuntimeException e) {
            attributes.addFlashAttribute("errMsg", "该类型下还有所属博客！！！");
            return "redirect:/admin/types";
        }
        attributes.addFlashAttribute("successMsg", "删除成功");
        return "redirect:/admin/types";
    }
}
