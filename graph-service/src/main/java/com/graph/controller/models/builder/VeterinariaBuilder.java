package com.graph.controller.models.builder;

import com.graph.controller.models.Veterinaria;
import com.graph.controller.models.enumeration.Ciudad;

public class VeterinariaBuilder {
    private Veterinaria veterinaria;

    public VeterinariaBuilder() {
        this.veterinaria = new Veterinaria();
    }

    public VeterinariaBuilder(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
    }

    public VeterinariaBuilder veterinariaWithGenericData() {
        this.veterinaria = new Veterinaria(1, "Veterinaria", "1234567890",
         "Calle 123", "veterinaria@example.com");
        return this;
    }

    public VeterinariaBuilder correo(String correo) {
        this.veterinaria.setCorreo(correo);
        return this;
    }

    public VeterinariaBuilder id(Integer id) {
        this.veterinaria.setId(id);
        return this;
    }

    public VeterinariaBuilder nombre(String nombre) {
        this.veterinaria.setNombre(nombre);
        return this;
    }

    public VeterinariaBuilder telefono(String telefono) {
        this.veterinaria.setTelefono(telefono);
        return this;
    }

    public VeterinariaBuilder direccion(String direccion) {
        this.veterinaria.setDireccion(direccion);
        return this;
    }

    public VeterinariaBuilder ciudad(Ciudad ciudad) {
        this.veterinaria.setCiudad(ciudad);
        return this;
    }

    public Veterinaria build() {
        return this.veterinaria;
    }

}
