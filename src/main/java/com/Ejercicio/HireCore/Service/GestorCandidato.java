package com.Ejercicio.HireCore.Service;

import com.Ejercicio.HireCore.Model.Candidato;
import com.Ejercicio.HireCore.Model.Memento.CandidatoMemento;
import com.Ejercicio.HireCore.Model.Observer.Observador;
import com.Ejercicio.HireCore.Model.State.IEstadoCandidato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@Service
public class GestorCandidato {

    // 1. Usar HashSet para impedir duplicados a nivel de lenguaje
    private final Set<Observador> observadores = new HashSet<>();
    private final Stack<CandidatoMemento> historial = new Stack<>();
    private Candidato candidatoActual;

    public GestorCandidato() {
    }

    // 2. Inyección de Spring hacia el Set
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
        if (o != null) {
            observadores.add(o);
        }
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