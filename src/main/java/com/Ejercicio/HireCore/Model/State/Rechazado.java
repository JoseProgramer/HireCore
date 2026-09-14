package com.Ejercicio.HireCore.Model.State;

import com.Ejercicio.HireCore.Model.Candidato;

public class Rechazado implements IEstadoCandidato {
    @Override
    public String getNombre() { return "Rechazado"; }

    @Override
    public boolean puedeAvanzarA(IEstadoCandidato nuevoEstado) { return false; }

    @Override
    public void avanzar(Candidato candidato, IEstadoCandidato nuevoEstado) {
        throw new IllegalStateException("El candidato ya fue Rechazado y no puede cambiar de estado.");
    }
}