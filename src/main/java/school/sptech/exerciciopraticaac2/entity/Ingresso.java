package school.sptech.exerciciopraticaac2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @DecimalMin("30.0")
    private Double preco;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoIngressoEnum tipoIngresso;

    @ManyToOne
    private Show show;

    @Transient
    private Double precoFinal;

    @PostLoad
    @PostPersist
    @PostUpdate
    private void calcularPrecoFinal() {
        switch (this.tipoIngresso) {
            case INTEIRA:
                this.precoFinal = this.preco;
                break;
            case MEIA:
                this.precoFinal = this.preco / 2;
                break;
            case CORTESIA:
                this.precoFinal = 0.0;
                break;
        }
    }
}
