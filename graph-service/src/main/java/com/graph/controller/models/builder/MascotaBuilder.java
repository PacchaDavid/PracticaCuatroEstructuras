package com.graph.controller.models.builder;

import com.graph.controller.models.Mascota;
import com.graph.controller.models.enumeration.SexoBiologico;
import com.graph.controller.models.enumeration.TipoMascota;

public class MascotaBuilder {
    private Mascota mascota;

    public MascotaBuilder() {
        this.mascota = new Mascota();
    }

    public MascotaBuilder(Mascota mascota) {
        this.mascota = mascota;
    }

    public MascotaBuilder mascotaWithGenericData() {
        this.mascota = new Mascota(1, "Mascota", "01/01/2021", 1.0f, 1.0f, TipoMascota.PERRO);
        this.mascota.setSexoBiologico(SexoBiologico.MACHO);
        return this;
    }

    public MascotaBuilder id(Integer id) {
        this.mascota.setId(id);
        return this;
    }

    public MascotaBuilder nombre(String nombre) {
        this.mascota.setNombre(nombre);
        return this;
    }

    public MascotaBuilder fechaNacimiento(String fechaNacimiento) {
        this.mascota.setFechaNacimiento(fechaNacimiento);
        return this;
    }

    public MascotaBuilder sexoBiologico(SexoBiologico sexoBiologico) {
        this.mascota.setSexoBiologico(sexoBiologico);
        return this;
    }

    public MascotaBuilder peso(Float peso) {
        this.mascota.setPeso(peso);
        return this;
    }

    public MascotaBuilder altura(Float altura) {
        this.mascota.setAltura(altura);
        return this;
    }

    public MascotaBuilder tipoMascota(TipoMascota tipoMascota) {
        this.mascota.setTipoMascota(tipoMascota);
        return this;
    }

    public Mascota build() {
        return this.mascota;
    }
}