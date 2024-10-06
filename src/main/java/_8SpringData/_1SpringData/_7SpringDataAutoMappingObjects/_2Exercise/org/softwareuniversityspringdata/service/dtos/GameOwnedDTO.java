package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos;

public class GameOwnedDTO {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s", this.title);
    }
}
