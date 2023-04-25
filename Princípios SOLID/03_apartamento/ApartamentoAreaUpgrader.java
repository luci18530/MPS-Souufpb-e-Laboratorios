class ApartamentoAreaUpgrader implements ApartamentoUpgrader {
    public void upgrade(Apartamento apartamento) {
        apartamento.areaApartamento += 40;
    }
}
