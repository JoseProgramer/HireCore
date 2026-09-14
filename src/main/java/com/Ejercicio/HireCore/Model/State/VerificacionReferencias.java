package com.Ejercicio.HireCore.Model.State;

import com.Ejercicio.HireCore.Model.Candidato;

public class VerificacionReferencias implements IEstadoCandidato {
    @Override
    public String getNombre() { return "VerificacionReferencias"; }

    @Override
    public boolean puedeAvanzarA(IEstadoCandidato nuevoEstado) {
        return nuevoEstado instanceof Oferta || nuevoEstado instanceof Rechazado;
    }

    @Override
    public void avanzar(Candidato candidato, IEstadoCandidato nuevoEstado) {
        if (puedeAvanzarA(nuevoEstado)) {
            candidato.setEstadoActual(nuevoEstado);
        } else {
            throw new IllegalStateException("Transición no permitida desde VerificacionReferencias a " + nuevoEstado.getNombre());
        }
    }
}