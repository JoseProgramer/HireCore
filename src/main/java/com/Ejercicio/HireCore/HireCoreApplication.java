package com.Ejercicio.HireCore;

import com.Ejercicio.HireCore.Model.Candidato;
import com.Ejercicio.HireCore.Model.State.*;
import com.Ejercicio.HireCore.Service.GestorCandidato;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HireCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HireCoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner ejecutarPruebas(GestorCandidato gestor) {
        return args -> {

            System.out.println("\n=======================================================");
            System.out.println("   CASO 1: FLUJO COMPLETO DE SELECCIÓN (HASTA CONTRATADO) ");
            System.out.println("=======================================================");
            
            Candidato c1 = new Candidato("1", "Carlos Pérez", "carlos@mail.com", "recruiter@company.com");
            gestor.setCandidatoActual(c1);

            gestor.cambiarEstado(c1, new Entrevista(), "admin");
            gestor.cambiarEstado(c1, new PruebaTecnica(), "admin");
            gestor.cambiarEstado(c1, new VerificacionReferencias(), "admin");
            gestor.cambiarEstado(c1, new Oferta(), "admin");
            gestor.cambiarEstado(c1, new Contratado(), "admin");

            System.out.println("\n=======================================================");
            System.out.println("   CASO 2: FLUJO DE RECHAZO Y RESTRICCIÓN DE ESTADO ");
            System.out.println("=======================================================");

            Candidato c2 = new Candidato("2", "Ana Gómez", "ana@mail.com", "recruiter@company.com");
            gestor.setCandidatoActual(c2);
            gestor.cambiarEstado(c2, new Entrevista(), "admin");
            gestor.cambiarEstado(c2, new Rechazado(), "admin");

            try {
                gestor.cambiarEstado(c2, new Oferta(), "admin");
            } catch (Exception e) {
                System.out.println("\n  [SISTEMA DE SEGURIDAD] Operación no permitida: " + e.getMessage());
            }

            System.out.println("\n=======================================================");
            System.out.println("   CASO 3: DESHACER (MEMENTO) Y REANUDACIÓN DE PROCESO ");
            System.out.println("=======================================================");

            Candidato c3 = new Candidato("3", "Luis Martínez", "luis@mail.com", "recruiter@company.com");
            gestor.setCandidatoActual(c3);
            gestor.cambiarEstado(c3, new Entrevista(), "admin");
            gestor.cambiarEstado(c3, new PruebaTecnica(), "admin");

            System.out.println("\n  [MEMENTO PATTERN] Se solicitó un rollback de estado para Luis Martínez...");
            gestor.deshacerCambio(c3); 

            System.out.println("\n  [REANUDANDO FLUJO] Avanzando nuevamente desde el estado restaurado...");
            gestor.cambiarEstado(c3, new PruebaTecnica(), "admin");
            gestor.cambiarEstado(c3, new VerificacionReferencias(), "admin");
            gestor.cambiarEstado(c3, new Oferta(), "admin");
            gestor.cambiarEstado(c3, new Contratado(), "admin");
            
            System.out.println("\n=======================================================");
            System.out.println("     ¡TODAS LAS PRUEBAS FINALIZARON EXITOSAMENTE! ");
            System.out.println("=======================================================\n");
        };
    }
}