package softuni.exam.service;

import softuni.exam.models.entity.Volcano;

import java.io.IOException;

public interface VolcanoService {

    boolean areImported();

    String readVolcanoesFileContent() throws IOException;

    String importVolcanoes() throws IOException;

    Volcano findVolcanoById(Long volcanoId);
//
//    void addAndSaveAddedVolcano(Volcano volcano, Volcanologist volcanologist);

    String exportVolcanoes();
}
