package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.CountryService;
import softuni.exam.service.VolcanologistService;
import softuni.exam.service.VolcanoService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {


    private final VolcanoService volcanoService;
    private final VolcanologistService volcanologistService;
    private final CountryService countryService;

    @Autowired
    public ImportController(VolcanoService volcanoService, VolcanologistService volcanologistService, CountryService countryService) {
        this.volcanoService = volcanoService;
        this.volcanologistService = volcanologistService;
        this.countryService = countryService;
    }


    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.countryService.areImported(),
                this.volcanoService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }


    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
                this.volcanologistService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }


    @GetMapping("/volcanologists")
    public ModelAndView importVolcanologists() throws IOException {
        String volcanologistsXmlFileContent = this.volcanologistService.readVolcanologistsFromFile();

        return super.view("xml/import-volcanologists", "volcanologists", volcanologistsXmlFileContent);
    }

    @PostMapping("/volcanologists")
    public ModelAndView importVolcanologistsConfirm() throws JAXBException, IOException {
        System.out.println(this.volcanologistService.importVolcanologists());

        return super.redirect("/import/xml");
    }

    @GetMapping("/countries")
    public ModelAndView importCountries() throws IOException {
        String fileContent = this.countryService.readCountriesFromFile();

        return super.view("json/import-countries", "countries", fileContent);
    }

    @PostMapping("/countries")
    public ModelAndView importConstellationsConfirm() throws IOException {
        System.out.println(this.countryService.importCountries());
        return super.redirect("/import/json");
    }

    @GetMapping("/volcanoes")
    public ModelAndView importVolcanoes() throws IOException {
        String fileContent = this.volcanoService.readVolcanoesFileContent();

        return super.view("json/import-volcanoes", "volcanoes", fileContent);
    }

    @PostMapping("/volcanoes")
    public ModelAndView importVolcanoesConfirm() throws IOException, JAXBException {
        System.out.println(this.volcanoService.importVolcanoes());
        return super.redirect("/import/json");
    }
}
