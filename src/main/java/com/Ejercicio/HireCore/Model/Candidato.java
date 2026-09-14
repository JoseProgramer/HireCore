package com.Ejercicio.HireCore.Model;

import com.Ejercicio.HireCore.Model.Memento.CandidatoMemento;
import com.Ejercicio.HireCore.Model.State.Aplicado;
import com.Ejercicio.HireCore.Model.State.IEstadoCandidato;

public class Candidato {
    private String id;
    private String nombre;
    private String candidatoEmail;
    private String reclutadorEmail;
    private IEstadoCandidato estadoActual;

    public Candidato(String id, String nombre, String candidatoEmail, String reclutadorEmail) {
        this.id = id;
        this.nombre = nombre;
        this.candidatoEmail = candidatoEmail;
        this.reclutadorEmail = reclutadorEmail;
        this.estadoActual = new Aplicado();
    }

    public CandidatoMemento crearMemento(String usuario) {
        return new CandidatoMemento(this.estadoActual, usuario);
    }

    public void restaurarMemento(CandidatoMemento memento) {
        if (memento != null) {
            this.estadoActual = memento.getEstado();
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCandidatoEmail() { return candidatoEmail; }
    public String getReclutadorEmail() { return reclutadorEmail; }
    public IEstadoCandidato getEstadoActual() { return estadoActual; }
    public void setEstadoActual(IEstadoCandidato estadoActual) { this.estadoActual = estadoActual; }
}