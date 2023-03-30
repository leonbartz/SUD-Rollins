package frontend.renderbehaviour;


import lombok.Getter;

public class RenderBehaviourManager {

    public enum RenderMode {
        MAP, INVENTORY, MAIN_MENU
    }

    private final MapRenderBehaviour mapRenderBehaviour;
    private final InventoryRenderBehaviour inventoryRenderBehaviour;
    @Getter
    private RenderBehaviour activeRenderBehaviour;

    public RenderBehaviourManager(MapRenderBehaviour mapRenderBehaviour, InventoryRenderBehaviour inventoryRenderBehaviour) {
        this.mapRenderBehaviour = mapRenderBehaviour;
        this.inventoryRenderBehaviour = inventoryRenderBehaviour;
        setRenderBehaviour(RenderMode.MAP);
    }

    public void setRenderBehaviour(RenderMode renderMode) {
        activeRenderBehaviour = switch (renderMode) {
            case MAIN_MENU -> null;
            case MAP -> mapRenderBehaviour;
            case INVENTORY -> inventoryRenderBehaviour;
        };
    }
}
