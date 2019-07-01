package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "voo")
public class Voo implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_voo", sequenceName = "seq_voo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_voo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descrição não pode ser em branco")
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Min(message = "O tempo estimado de voo não pode ser negativo", value = 0)
    @NotNull(message = "O tempo estimado de voo não pode ser nulo")
    @Column(name = "tempoEstimado", nullable = false, columnDefinition = "numeric(12,2)")
    private Double tempoEstimado;
    @NotNull(message = "É preciso informar se o voo é ativo ou não")
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
    @NotNull(message = "A periodicidade não pode ser nula")
    @Column(name = "periodicidade", nullable = false)
    private String periodicidade;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "escalas",
            joinColumns = 
            @JoinColumn(name = "voo", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "aeroporto", referencedColumnName = "id", nullable = false))
    private List<Aeroporto> escalas;
    @OneToMany(mappedBy = "voo", cascade = CascadeType.ALL,
        orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VooAgendado> voosAgendados;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Double tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public List<Aeroporto> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<Aeroporto> escalas) {
        this.escalas = escalas;
    }

    public List<VooAgendado> getVoosAgendados() {
        return voosAgendados;
    }

    public void setVoosAgendados(List<VooAgendado> voosAgendados) {
        this.voosAgendados = voosAgendados;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voo other = (Voo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
