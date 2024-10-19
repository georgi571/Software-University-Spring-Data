package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("SELECT o FROM Offer o JOIN o.apartment a WHERE a.apartmentType = :apartmentType ORDER BY a.area DESC, o.price ASC")
    List<Offer> findAllByApartmentTypeOrderByAreaDescPriceAsc(ApartmentType apartmentType);
}
