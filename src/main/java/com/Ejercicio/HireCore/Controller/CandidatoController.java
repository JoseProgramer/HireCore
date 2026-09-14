package com.Ejercicio.HireCore.Controller;

import com.Ejercicio.HireCore.Model.Candidato;
import com.Ejercicio.HireCore.Model.State.*;
import com.Ejercicio.HireCore.Service.GestorCandidato;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    private final GestorCandidato gestorCandidato;

    public CandidatoController(GestorCandidato gestorCandidato) {
        this.gestorCandidato = gestorCandidato;
    }

    @PostMapping
    public ResponseEntity<Candidato> crearCandidato(@RequestParam String id,
                                                    @RequestParam String nombre,
                                                    @RequestParam String candidatoEmail,
                                                    @RequestParam String reclutadorEmail) {
        Candidato nuevo = new Candidato(id, nombre, candidatoEmail, reclutadorEmail);
        gestorCandidato.setCandidatoActual(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/estado")
    public ResponseEntity<?> cambiarEstado(@RequestParam String nuevoEstadoNombre,
                                           @RequestParam String usuario) {
        try {
            Candidato actual = gestorCandidato.getCandidatoActual();
            if (actual == null) {
                return ResponseEntity.badRequest().body("No hay ningún candidato activo seleccionado.");
            }

            IEstadoCandidato nuevoEstado = mapearEstado(nuevoEstadoNombre);
            gestorCandidato.cambiarEstado(actual, nuevoEstado, usuario);
            
            return ResponseEntity.ok(actual);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/deshacer")
    public ResponseEntity<?> deshacerEstado() {
        try {
            Candidato actual = gestorCandidato.getCandidatoActual();
            if (actual == null) {
                return ResponseEntity.badRequest().body("No hay ningún candidato activo.");
            }
            gestorCandidato.deshacerCambio(actual);
            return ResponseEntity.ok(actual);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Candidato> obtenerCandidato() {
        Candidato candidato = gestorCandidato.getCandidatoActual();
        if (candidato == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(candidato);
    }

    // Método auxiliar para instanciar la clase de estado según el string que llegue de la API
    private IEstadoCandidato mapearEstado(String nombre) {
        switch (nombre.toLowerCase()) {
            case "entrevista": return new Entrevista();
            case "pruebatecnica": 
            case "prueba_tecnica": return new PruebaTecnica();
            case "verificacionreferencias":
            case "verificacion_referencias": return new VerificacionReferencias();
            case "oferta": return new Oferta();
            case "contratado": return new Contratado();
            case "rechazado": return new Rechazado();
            default: return new Aplicado();
        }
    }
}