package com.Ejercicio.HireCore.Model.State;

import com.Ejercicio.HireCore.Model.Candidato;

public class PruebaTecnica implements IEstadoCandidato {
    @Override
    public String getNombre() { return "PruebaTecnica"; }

    @Override
    public boolean puedeAvanzarA(IEstadoCandidato nuevoEstado) {
        return nuevoEstado instanceof VerificacionReferencias || nuevoEstado instanceof Rechazado;
    }

    @Override
    public void avanzar(Candidato candidato, IEstadoCandidato nuevoEstado) {
        if (puedeAvanzarA(nuevoEstado)) {
            candidato.setEstadoActual(nuevoEstado);
        } else {
            throw new IllegalStateException("Transición no permitida desde PruebaTecnica a " + nuevoEstado.getNombre());
        }
    }
}