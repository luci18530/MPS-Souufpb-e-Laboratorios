import java.util.ArrayList;
import java.util.List;

public class UpgradeApartamento {
    private List<ApartamentoUpgrader> upgraders;

    public UpgradeApartamento() {
        upgraders = new ArrayList<>();
        upgraders.add(new ApartamentoAreaUpgrader());
        upgraders.add(new ApartamentoQuartosUpgrader());
    }

    public void upgrade(Apartamento apartamento) {
        for (ApartamentoUpgrader upgrader : upgraders) {
            upgrader.upgrade(apartamento);
        }
    }
}
