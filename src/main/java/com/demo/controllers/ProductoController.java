package com.demo.controllers;


import com.demo.dto.ProductoDto;
import com.demo.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/producto")
public class ProductoController {

@Autowired
private ProductoService productoService;

@PostMapping
public ResponseEntity<ProductoDto>crearProducto(@Valid @RequestBody ProductoDto productoDto){
    return new ResponseEntity<>(productoService.crearProducto(productoDto), HttpStatus.CREATED);
}

@GetMapping
    public List<ProductoDto> listarProductos(){
        return productoService.obtenerProductos();
}

@GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerProducto(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(productoService.obtenerProducto(id), HttpStatus.OK);
}

@GetMapping("search")
public ResponseEntity<ProductoDto> obtenerProductoPorNombre(@RequestParam(value = "name") String nombre) {
return new ResponseEntity<>(productoService.obtenerProductoByNombre(nombre), HttpStatus.OK);
}

@PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(@PathVariable(name = "id") Long id,
                                                          @RequestBody ProductoDto productoDto
){
    return new ResponseEntity<>(productoService.actualizarProducto(id, productoDto), HttpStatus.OK);
}

@DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable(name = "id") Long id){
    productoService.eliminarProducto(id);
}

}
