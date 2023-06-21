package business.model;

import java.util.HashMap;
import java.util.Map;

public class Resultado {
    private Map<String, Integer> scorePorArea;

    public Resultado(){
        this.scorePorArea = new HashMap<>();
    }

    public void addScore(String area, int score){
        this.scorePorArea.put(area, this.scorePorArea.getOrDefault(area, 0) + score);
    }

    public Map<String, Integer> getScorePorArea() {
        return scorePorArea;
    }

    public String getAreaComMaiorPontuacao() {
        return this.scorePorArea.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }
}