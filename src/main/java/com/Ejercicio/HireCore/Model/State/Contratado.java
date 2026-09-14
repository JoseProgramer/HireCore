package com.Ejercicio.HireCore.Model.State;

import com.Ejercicio.HireCore.Model.Candidato;

public class Contratado implements IEstadoCandidato {
    @Override
    public String getNombre() { return "Contratado"; }

    @Override
    public boolean puedeAvanzarA(IEstadoCandidato nuevoEstado) { return false; }

    @Override
    public void avanzar(Candidato candidato, IEstadoCandidato nuevoEstado) {
        throw new IllegalStateException("El candidato ya fue Contratado y no puede cambiar de estado.");
    }
}