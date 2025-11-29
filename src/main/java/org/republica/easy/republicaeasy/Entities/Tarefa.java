package org.republica.easy.republicaeasy.Entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;

    @ManyToOne
    private User usuarioResponsavel;

    @ManyToOne
    private Republica republica;

    private boolean finalizada = false;

    public UUID getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public User getUsuarioResponsavel() { return usuarioResponsavel; }
    public void setUsuarioResponsavel(User usuarioResponsavel) { this.usuarioResponsavel = usuarioResponsavel; }

    public Republica getRepublica() { return republica; }
    public void setRepublica(Republica republica) { this.republica = republica; }

    public boolean isFinalizada() { return finalizada; }
    public void setFinalizada(boolean finalizada) { this.finalizada = finalizada; }
}
