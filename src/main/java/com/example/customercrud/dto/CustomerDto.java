package com.example.customercrud.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

    @NotNull(message = "Name ustunini toldirmadingiz")
    private String name;

    @NotNull(message = "Phone ustunini toldirmadingiz")
    private String phone;

    @NotNull(message = "Address ustunini toldirmadingiz")
    private String address;
}
