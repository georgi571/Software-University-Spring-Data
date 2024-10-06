package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos;

import java.time.LocalDate;

public class GameDetailsDTO {
    private String title;
    private double price;
    private String description;
    private LocalDate localDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return String.format("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s%n",
                this.getTitle(),
                this.getPrice(),
                this.getDescription(),
                this.getLocalDate());
    }
}
