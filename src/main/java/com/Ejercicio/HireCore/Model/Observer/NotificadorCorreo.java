package com.Ejercicio.HireCore.Model.Observer;

import com.Ejercicio.HireCore.Model.Candidato;
import com.Ejercicio.HireCore.Model.State.IEstadoCandidato;
import org.springframework.stereotype.Component;

@Component
public class NotificadorCorreo implements Observador {


    @Override
    public void actualizar(Candidato candidato, IEstadoCandidato nuevoEstado) {
        String mensaje;

        switch (nuevoEstado.getNombre().toLowerCase()) {
            case "contratado":
                mensaje = String.format(" ¡Felicidades %s! Has completado exitosamente el proceso y has sido CONTRATADO/A.", candidato.getNombre());
                break;
            case "rechazado":
                mensaje = String.format(" Hola %s, lamentamos informarte que tu proceso ha sido finalizado en estado RECHAZADO.", candidato.getNombre());
                break;
            default:
                mensaje = String.format(" Hola %s, tu estado en el proceso de selección ha cambiado a: [%s].", candidato.getNombre(), nuevoEstado.getNombre());
                break;
        }

        System.out.println(String.format("\n [CORREO ENVIADO A: %s]\n    De: reclutamiento@hirecore.com\n    Para: %s\n    Mensaje: %s", 
                candidato.getCandidatoEmail(), candidato.getCandidatoEmail(), mensaje));
    }
}