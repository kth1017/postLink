package fadet.postLink.web.controller;

import fadet.postLink.domain.NewCode;
import fadet.postLink.domain.OldCode;
import fadet.postLink.service.LinkService;
import fadet.postLink.web.InputForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("form", new InputForm());
        return "input";
    }

    @PostMapping("/")
    public String input(@ModelAttribute("form") InputForm form) {

        OldCode newOne = new OldCode();
        newOne.setAllCode(form.getAllCode());
        newOne.setTitleHtmlKeyword(form.getTitleHtmlKeyword());

        linkService.saveCode(newOne);
        return "redirect:/";
    }

    @GetMapping("/1")
    public String result(Model model) {
        NewCode result = linkService.newCode(1L);
        model.addAttribute("newCode", result);
        return "result";

    }




}
