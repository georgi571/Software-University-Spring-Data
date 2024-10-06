package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class GameAddDTO {

    @Size(min = 3, max = 100, message = "Length should be between 3 and 100 letters.")
    @Pattern(regexp = "[A-Z][a-z]+(?:['\\-\\s][A-Za-z]+)*", message = "Title should start with capital letter.")
    private String title;
    @Size(min = 11, max = 11)
    private String trailer;
    @Pattern(regexp = "^(?:http://)*(?:https://)*.+", message = "Thumbnail doesn't start with correct protocol.")
    private String thumbnail;
    @Min(value = 0)
    private double size;
    @Min(value = 0)
    private double price;
    @Size(min = 20)
    private String description;
    private LocalDate releaseDate;

    public GameAddDTO(String title, String trailer, String thumbnail, double size, double price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.thumbnail = thumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
