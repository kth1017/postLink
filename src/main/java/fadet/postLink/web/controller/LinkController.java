package fadet.postLink.web.controller;

import fadet.postLink.domain.NewCode;
import fadet.postLink.domain.OldCode;
import fadet.postLink.repository.CodeRepository;
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
    private final CodeRepository codeRepository;

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
        newOne.init();

        linkService.saveCode(newOne);
        int id = codeRepository.size();
        return "redirect:/valid/"+id;
    }

    @GetMapping("/valid/{id}")
    public String valid(@PathVariable("id") Long id, Model model) {
        OldCode oldCode = linkService.findOne(id);

        ValidForm validForm = new ValidForm();
        validForm.setAllCode(oldCode.getAllCode());
        validForm.setTitleHtmlKeyword(oldCode.getTitleHtmlKeyword());
        validForm.setIndexHtmlKeyword(oldCode.getIndexHtmlKeyword());

        model.addAttribute("validForm", validForm);
        model.addAttribute("oldCode", oldCode);
        model.addAttribute("titleNum", oldCode.getNewTitleList().size());

        return "valid";
    }

    @PostMapping("/valid/{id}")
    public String validPost(@PathVariable("id") Long id, @ModelAttribute("validForm") ValidForm validForm, Model model) {

        OldCode changeOne = new OldCode();
        changeOne.setAllCode(validForm.getAllCode());
        changeOne.setTitleHtmlKeyword(validForm.getTitleHtmlKeyword());
        changeOne.setIndexHtmlKeyword(validForm.getIndexHtmlKeyword());
        changeOne.init();

        linkService.changeCode(changeOne);
        OldCode findOne = linkService.findOne(id);

        model.addAttribute("validForm", new ValidForm());
        model.addAttribute("oldCode", findOne);
        model.addAttribute("titleNum", findOne.getNewTitleList().size());

        return "redirect:/valid/"+id;
    }



    @GetMapping("/{id}")
    public String result(@PathVariable("id") Long id, Model model) {
        NewCode result = linkService.newCode(id);
        model.addAttribute("newCode", result);


        return "result";

    }




}
