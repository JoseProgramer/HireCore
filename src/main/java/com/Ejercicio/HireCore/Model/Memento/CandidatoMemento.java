package com.Ejercicio.HireCore.Model.Memento;

import java.util.Date;
import com.Ejercicio.HireCore.Model.State.IEstadoCandidato;

public class CandidatoMemento {
    private final IEstadoCandidato estado;
    private final String usuario;
    private final Date fechaHora;

    public CandidatoMemento(IEstadoCandidato estado, String usuario) {
        this.estado = estado;
        this.usuario = usuario;
        this.fechaHora = new Date();
    }

    public IEstadoCandidato getEstado() { return estado; }
    public String getUsuario() { return usuario; }
    public Date getFechaHora() { return fechaHora; }
}