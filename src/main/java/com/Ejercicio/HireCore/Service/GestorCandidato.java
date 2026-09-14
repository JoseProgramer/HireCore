package com.Ejercicio.HireCore.Service;

import com.Ejercicio.HireCore.Model.Candidato;
import com.Ejercicio.HireCore.Model.Memento.CandidatoMemento;
import com.Ejercicio.HireCore.Model.Observer.Observador;
import com.Ejercicio.HireCore.Model.State.IEstadoCandidato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class GestorCandidato {

    private final List<Observador> observadores = new ArrayList<>();
    private final Stack<CandidatoMemento> historial = new Stack<>();
    private Candidato candidatoActual;

    public GestorCandidato() {
        // Constructor por defecto para Spring
    }

    @Autowired(required = false)
    public void setObservadores(List<Observador> observadoresIniciales) {
        if (observadoresIniciales != null) {
            this.observadores.addAll(observadoresIniciales);
        }
    }

    public Candidato getCandidatoActual() {
        return candidatoActual;
    }

    public void setCandidatoActual(Candidato candidatoActual) {
        this.candidatoActual = candidatoActual;
    }

    public void suscribir(Observador o) {
        observadores.add(o);
    }

    public void desuscribir(Observador o) {
        observadores.remove(o);
    }

    public void notificar(Candidato candidato, IEstadoCandidato nuevoEstado) {
        for (Observador o : observadores) {
            o.actualizar(candidato, nuevoEstado);
        }
    }

    public void cambiarEstado(Candidato candidato, IEstadoCandidato nuevoEstado, String usuario) {
        if (!candidato.getEstadoActual().puedeAvanzarA(nuevoEstado)) {
            throw new IllegalStateException("Transición no permitida de " 
                + candidato.getEstadoActual().getNombre() + " a " + nuevoEstado.getNombre());
        }
        historial.push(candidato.crearMemento(usuario));
        candidato.getEstadoActual().avanzar(candidato, nuevoEstado);
        notificar(candidato, nuevoEstado);
    }

    public void deshacerCambio(Candidato candidato) {
        if (!historial.isEmpty()) {
            CandidatoMemento memento = historial.pop();
            candidato.restaurarMemento(memento);
            notificar(candidato, candidato.getEstadoActual());
        }
    }
}