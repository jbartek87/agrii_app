package com.jbartek.agrii.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String farmNumber;
    private String email;
    private String password;
//    private List<ParcelDto> parcelDtoList;
}
