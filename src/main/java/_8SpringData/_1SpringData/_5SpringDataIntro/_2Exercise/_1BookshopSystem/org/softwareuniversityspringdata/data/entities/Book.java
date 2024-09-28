package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.base.BaseEntity;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.enums.AgeRestriction;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.enums.EditionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Enumerated(value = EnumType.STRING)
    private EditionType editionType;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "copies", nullable = false)
    private int copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)
    private AgeRestriction ageRestriction;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "books_categories",
    joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;

    public Book() {
    }

    public Book(String title, EditionType editionType, BigDecimal price, int copies, LocalDate releaseDate, AgeRestriction ageRestriction, Author author, Set<Category> categories) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCopies() {
        return copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
