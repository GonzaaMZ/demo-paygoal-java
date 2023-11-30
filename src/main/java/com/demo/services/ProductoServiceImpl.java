package com.demo.services;

import com.demo.dto.ProductoDto;
import com.demo.entities.Producto;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.repositories.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public ProductoDto crearProducto(ProductoDto productoDto) {
        Producto producto = mapearAEntidad(productoDto);
        productoRepository.save(producto);
        return mapearADto(producto);
    }

    @Override
    public ProductoDto obtenerProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Not Found", id));

        return mapearADto(producto);
    }

    @Override
    public ProductoDto obtenerProductoByNombre(String nombre) {
        Producto producto = productoRepository.findByNombre(nombre).orElseThrow(() -> new ResourceNotFoundException("Product", "Not found"));
        return mapearADto(producto);
    }

    @Override
    public List<ProductoDto> obtenerProductos() {
        List<Producto> productoList = productoRepository.findByOrderByPrecioDesc();
        List<ProductoDto> productoDtoList =productoList.stream().map(producto -> mapearADto(producto)).collect(Collectors.toList());
        return productoDtoList;
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDto actualizarProducto(Long id, ProductoDto productoDto) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Not Found", id));
        producto.setNombre(productoDto.getNombre());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());
        producto.setCantidad(productoDto.getCantidad());

        productoRepository.save(producto);
        return mapearADto(producto);
    }

    private ProductoDto mapearADto(Producto producto){
    ProductoDto productoDto = modelMapper.map(producto, ProductoDto.class);
    return productoDto;
}

private Producto mapearAEntidad (ProductoDto productoDto){
        Producto producto = modelMapper.map(productoDto, Producto.class);
        return producto;
}

}
