package br.com.hbsis.pocrabbit.Cargo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CargoService {

    private final ICargoRepository iCargoRepository;

    public CargoService(ICargoRepository iCargoRepository) {
        this.iCargoRepository = iCargoRepository;
    }

    public CargoDTO save(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();

        validate(cargoDTO);

        cargo.setCargoCode(cargoDTO.getCargoCode());
        cargo.setFrom(cargoDTO.getFrom());
        cargo.setTo(cargoDTO.getTo());
        cargo.setSituation(cargoDTO.getSituation());
        cargo.setDeliveryDate(cargoDTO.getDeliveryDate());

        return CargoDTO.of(iCargoRepository.save(cargo));
    }

    private void validate(CargoDTO cargoDTO) {
        if (cargoDTO.getCargoCode().isEmpty()) {
            throw new IllegalArgumentException("Código da carga não pode ser nulo");
        }

        if(cargoDTO.getFrom().isEmpty()) {
            throw new IllegalArgumentException("Remetente não pode ser nulo");
        }

        if(cargoDTO.getTo().isEmpty()) {
            throw new IllegalArgumentException("Destinatário não pode ser nulo");
        }

        if(cargoDTO.getSituation().isEmpty()) {
            throw new IllegalArgumentException("Situação não pode estar nula");
        }
    }

    public CargoDTO saveFromXML(String xml) {
        CargoDTO cargoDTO = new CargoDTO();

        cargoDTO.setCargoCode(xml.substring(xml.lastIndexOf("<cargocode>") + "<cargocode>".length(), xml.indexOf("</cargocode>")));
        cargoDTO.setFrom(xml.substring(xml.lastIndexOf("<from>") + "<from>".length(), xml.indexOf("</from>")));
        cargoDTO.setTo(xml.substring(xml.lastIndexOf("<to>") + "<to>".length(), xml.indexOf("</to>")));
        cargoDTO.setSituation(xml.substring(xml.lastIndexOf("<situacao>") + "<situacao>".length(), xml.indexOf("</situacao>")));

        try {
            String date = xml.substring(xml.lastIndexOf("<datarecebimento>") + "<datarecebimento>".length(), xml.indexOf("</datarecebimento>"));
            cargoDTO.setDeliveryDate(LocalDate.of(Integer.parseInt(date.substring(0,4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8,10))));
        } catch (Exception e){
            cargoDTO.setDeliveryDate(null);
        }

        save(cargoDTO);

        return cargoDTO;
    }

}
