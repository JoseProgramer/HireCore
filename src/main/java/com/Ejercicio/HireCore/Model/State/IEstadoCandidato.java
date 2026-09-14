package com.Ejercicio.HireCore.Model.State;

import com.Ejercicio.HireCore.Model.Candidato;

public interface IEstadoCandidato {
    String getNombre();
    boolean puedeAvanzarA(IEstadoCandidato nuevoEstado);
    void avanzar(Candidato candidato, IEstadoCandidato nuevoEstado);
}