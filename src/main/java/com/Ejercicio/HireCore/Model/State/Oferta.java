package com.Ejercicio.HireCore.Model.State;

import com.Ejercicio.HireCore.Model.Candidato;

public class Oferta implements IEstadoCandidato {
    @Override
    public String getNombre() { return "Oferta"; }

    @Override
    public boolean puedeAvanzarA(IEstadoCandidato nuevoEstado) {
        return nuevoEstado instanceof Contratado || nuevoEstado instanceof Rechazado;
    }

    @Override
    public void avanzar(Candidato candidato, IEstadoCandidato nuevoEstado) {
        if (puedeAvanzarA(nuevoEstado)) {
            candidato.setEstadoActual(nuevoEstado);
        } else {
            throw new IllegalStateException("Transición no permitida desde Oferta a " + nuevoEstado.getNombre());
        }
    }
}