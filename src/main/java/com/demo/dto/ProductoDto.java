package com.demo.dto;

import lombok.Data;

@Data
public class ProductoDto {

    private Long id;

    private String nombre;

    private String descripcion;

    private int precio;

    private int cantidad;
}
