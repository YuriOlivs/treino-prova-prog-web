package school.sptech.exerciciopraticaac2.entity;

public enum TipoIngressoEnum {
    INTEIRA("Inteira"),
    MEIA("Meia"),
    CORTESIA("Cortesia");

    private String descricao;

    private TipoIngressoEnum(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return descricao; }
}
