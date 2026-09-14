package com.Ejercicio.HireCore.Model.Observer;

import com.Ejercicio.HireCore.Model.Candidato;
import com.Ejercicio.HireCore.Model.State.IEstadoCandidato;

public interface Observador {
    void actualizar(Candidato candidato, IEstadoCandidato nuevoEstado);
}