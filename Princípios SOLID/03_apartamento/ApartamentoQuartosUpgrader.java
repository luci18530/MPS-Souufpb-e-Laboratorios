class ApartamentoQuartosUpgrader implements ApartamentoUpgrader {
    public void upgrade(Apartamento apartamento) {
        if (apartamento.getClass() != Studio.class) {
            apartamento.numeroQuartos += 1;
        }
    }
}
