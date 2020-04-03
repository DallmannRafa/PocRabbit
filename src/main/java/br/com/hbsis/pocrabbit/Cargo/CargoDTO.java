package br.com.hbsis.pocrabbit.Cargo;

import java.math.BigInteger;
import java.time.LocalDate;

public class CargoDTO {

    private BigInteger id;
    private String cargoCode;
    private String from;
    private String to;
    private String situation;
    private LocalDate deliveryDate;

    public CargoDTO() {
    }

    public CargoDTO(BigInteger id, String cargoCode, String from, String to, String situation, LocalDate deliveryDate) {
        this.id = id;
        this.cargoCode = cargoCode;
        this.from = from;
        this.to = to;
        this.situation = situation;
        this.deliveryDate = deliveryDate;
    }

    public static CargoDTO of(Cargo cargo) {
        return new CargoDTO(
                cargo.getId(),
                cargo.getCargoCode(),
                cargo.getFrom(),
                cargo.getTo(),
                cargo.getSituation(),
                cargo.getDeliveryDate()
        );
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
        return "CargoDTO{" +
                "id=" + id +
                ", cargoCode='" + cargoCode + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", situation='" + situation + '\'' +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
