package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Column(name = "discount")
    private double discount;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public Sale() {
    }

    public Sale(double discount, Car car, Customer customer) {
        this.discount = discount;
        this.car = car;
        this.customer = customer;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;

        return Objects.equals(car.getId(), sale.car.getId()) &&
                Objects.equals(customer.getId(), sale.customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(car.getId(), customer.getId());
    }
}
