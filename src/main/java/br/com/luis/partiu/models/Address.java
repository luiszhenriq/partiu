package br.com.luis.partiu.models;


import br.com.luis.partiu.dto.address.AddressDto;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
public class Address {

    private String street;

    private String neighborhood;

    private String zipCode;

    private String ref;

    public Address(AddressDto addressDto) {
        this.street = addressDto.street();
        this.neighborhood = addressDto.neighborhood();
        this.zipCode = addressDto.zipCode();
        this.ref = addressDto.ref();
    }

}
