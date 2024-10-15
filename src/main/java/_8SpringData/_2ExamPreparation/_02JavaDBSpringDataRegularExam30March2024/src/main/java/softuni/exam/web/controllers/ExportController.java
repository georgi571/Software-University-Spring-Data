package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.VolcanoService;
import softuni.exam.service.VolcanologistService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    private final VolcanoService volcanoService;
    private final VolcanologistService volcanologistService;
    @Autowired
    public ExportController(VolcanoService volcanoService, VolcanologistService volcanologistService) {
        this.volcanoService = volcanoService;
        this.volcanologistService = volcanologistService;
    }


    @GetMapping("/volcanoes")
    public ModelAndView exportVolcanoes() {
        String exportedVolcanoes = this.volcanoService.exportVolcanoes();

        return super.view("export/export-volcanoes.html", "exportedVolcanoes", exportedVolcanoes);
    }
}
