package fadet.postLink.web.controller;

import fadet.postLink.domain.NewCode;
import fadet.postLink.domain.OldCode;
import fadet.postLink.repository.OldCodeRepository;
import fadet.postLink.service.LinkService;
import fadet.postLink.web.InputForm;
import fadet.postLink.web.ValidForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class LinkController {

    private final LinkService linkService;
    private final OldCodeRepository oldCodeRepository;

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
        newOne.setIndexHtmlKeyword(form.getIndexHtmlKeyword());

        linkService.saveCode(newOne);
        int id = oldCodeRepository.size();
        return "redirect:/valid/"+id;
    }

    @GetMapping("/valid/{id}")
    public String valid(@PathVariable("id") Long id, @ModelAttribute("validForm") ValidForm validForm, Model model) {
        OldCode oldCode = linkService.findOne(id);
        model.addAttribute("titleNum", oldCode.getNewTitleList().size());

        return "valid";
    }

    @GetMapping("/{id}")
    public String result(@PathVariable("id") Long id, Model model) {
        NewCode result = linkService.newCode(id);
        model.addAttribute("newCode", result);


        return "result";

    }




}
