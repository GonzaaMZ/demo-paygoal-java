package com.demo.services;

import com.demo.dto.ProductoDto;

import java.util.List;

public interface ProductoService {

    public ProductoDto crearProducto(ProductoDto productoDto);

    public ProductoDto obtenerProducto(Long id);

    public ProductoDto obtenerProductoByNombre(String nombre);

    public List<ProductoDto> obtenerProductos();

    public void eliminarProducto(Long id);

    public ProductoDto actualizarProducto(Long id, ProductoDto productoDto);
}
