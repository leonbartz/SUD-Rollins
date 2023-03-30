package backend.game_map.room;
import helpers.weightedRendering.WeightedRandomBag;

public class TilesWeightedRendering {
    WeightedRandomBag<String> tilesWeighted = new WeightedRandomBag<>();

    public TilesWeightedRendering(){
        tilesWeighted.addEntry("floor_1.png", 15.0);
        tilesWeighted.addEntry("floor_2.png", 7.5);
        tilesWeighted.addEntry("floor_3.png", 15.0);
        tilesWeighted.addEntry("floor_4.png", 1.0);
        tilesWeighted.addEntry("floor_5.png", 15.0);
        tilesWeighted.addEntry("floor_6.png", 1.0);
        tilesWeighted.addEntry("floor_7.png", 1.0);
        tilesWeighted.addEntry("floor_8.png", 1.0);
    }


}
