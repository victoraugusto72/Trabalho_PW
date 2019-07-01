package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "voo_agendado")
public class VooAgendado implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_voo_agendado", sequenceName = "seq_voo_agendado_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_voo_agendado", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A aeronave não pode ser nula")
    @NotBlank(message = "A aeronave não pode ser em branco")
    @Column(name = "aeronave", nullable = false)
    private String aeronave;
    @Min(value = 0, message = "O total de passageiros não pode ser negativo")
    @NotNull(message = "O total de passageiros não pode ser nulo")
    @Column(name = "total_passageiros", nullable = false)
    private Integer totalPassageiros;
    @NotNull(message = "A data não pode ser nula")
    @Temporal(TemporalType.DATE)
    private Calendar data;
    @NotNull(message = "O voo deve ser informado")
    @ManyToOne
    @JoinColumn(name = "voo", referencedColumnName = "id", nullable = false, 
            foreignKey = @ForeignKey(name = "fk_voo_voo_agendado"))
    private Voo voo;
    
    @OneToMany(mappedBy = "vooAgendado", cascade = CascadeType.ALL,
        orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Passagem> passagens;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAeronave() {
        return aeronave;
    }

    public void setAeronave(String aeronave) {
        this.aeronave = aeronave;
    }

    public Integer getTotalPassageiros() {
        return totalPassageiros;
    }

    public void setTotalPassageiros(Integer totalPassageiros) {
        this.totalPassageiros = totalPassageiros;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    
    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<Passagem> passagens) {
        this.passagens = passagens;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.getId());
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
        final VooAgendado other = (VooAgendado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
