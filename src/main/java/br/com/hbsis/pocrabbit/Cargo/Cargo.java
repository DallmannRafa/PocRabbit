package br.com.hbsis.pocrabbit.Cargo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDate;

@Document
public class Cargo {

    @Id
    private BigInteger id;

    private String cargoCode;
    private String from;
    private String to;
    private String situation;
    private LocalDate deliveryDate;

    public Cargo() {
    }

    public Cargo(String cargoCode, String from, String to, String situation, LocalDate deliveryDate) {
        this.cargoCode = cargoCode;
        this.from = from;
        this.to = to;
        this.situation = situation;
        this.deliveryDate = deliveryDate;
    }

    public BigInteger getId() {
        return id;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", cargoCode='" + cargoCode + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", situation='" + situation + '\'' +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
