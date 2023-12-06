package com.norjull.petcompanionshipexchange.application.query.owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOwnerDTO {

    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;

}
