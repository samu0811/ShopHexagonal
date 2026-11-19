package com.shop.tienda_ropa.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.tienda_ropa.application.service.CategoriaService;
import com.shop.tienda_ropa.application.service.ProductoService;
import com.shop.tienda_ropa.domain.port.in.CategoriaUseCase;
import com.shop.tienda_ropa.domain.port.in.ProductoUseCase;
import com.shop.tienda_ropa.domain.port.out.CategoriaRepositoryPort;
import com.shop.tienda_ropa.domain.port.out.ProductoRepositoryPort;

@Configuration 
public class BeanConfiguration {

    @Bean 
    public CategoriaUseCase categoriaUseCase(CategoriaRepositoryPort categoriaRepository,
        ProductoRepositoryPort productoRepository){
        return new CategoriaService(categoriaRepository, productoRepository);
    }

    @Bean
    public ProductoUseCase productoUseCase(ProductoRepositoryPort productoRepository, CategoriaRepositoryPort categoriaRepository){
        return new ProductoService(categoriaRepository, productoRepository);
    }
}
