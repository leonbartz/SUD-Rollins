package frontend.renderbehaviour;


import lombok.Getter;

public class RenderBehaviourManager {

    public enum RenderMode {
        MAP, INVENTORY, MAIN_MENU
    }

    private final MapRenderBehaviour mapRenderBehaviour;
    @Getter
    private RenderBehaviour activeRenderBehaviour;

    public RenderBehaviourManager(MapRenderBehaviour mapRenderBehaviour) {
        this.mapRenderBehaviour = mapRenderBehaviour;
        setRenderBehaviour(RenderMode.MAP);
    }

    public void setRenderBehaviour(RenderMode renderMode) {
        activeRenderBehaviour = switch (renderMode) {
            case MAIN_MENU -> null;
            case MAP -> mapRenderBehaviour;
            case INVENTORY -> null;
        };
    }
}
