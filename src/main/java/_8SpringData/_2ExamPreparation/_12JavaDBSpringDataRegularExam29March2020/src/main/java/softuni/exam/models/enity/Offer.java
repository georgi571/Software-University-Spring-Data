package softuni.exam.models.enity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "has_gold_status", nullable = false)
    private boolean hasGoldStatus;

    @Column(name = "added_on", nullable = false)
    private LocalDateTime addedOn;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    @ManyToMany
    @JoinTable(name = "offers_pictures",
    joinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"))
    private Set<Picture> pictureSet = new HashSet<>();

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Set<Picture> getPictureSet() {
        return pictureSet;
    }

    public void setPictureSet(Set<Picture> pictureSet) {
        this.pictureSet = pictureSet;
    }
}
