package com.Ejercicio.HireCore.Model.Observer;

import com.Ejercicio.HireCore.Model.Candidato;
import com.Ejercicio.HireCore.Model.State.IEstadoCandidato;
import org.springframework.stereotype.Component;

@Component
public class LogAuditoria implements Observador {

    @Override
    public void actualizar(Candidato candidato, IEstadoCandidato nuevoEstado) {
        System.out.println(String.format(" [AUDITORÍA SYSTEM] Candidato '%s' (ID: %s) -> Estado actualizado a: %s", 
                candidato.getNombre(), candidato.getId(), nuevoEstado.getNombre()));
    }
}