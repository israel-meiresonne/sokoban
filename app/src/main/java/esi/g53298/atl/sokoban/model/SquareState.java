package esi.g53298.atl.sokoban.model;

/**
 * all possible state of a Square
 *
 * @author israelmeiresonne
 */
public enum SquareState {
    STATE_EMPTY_GAOL("STATE_EMPTY_GAOL"),
    STATE_BOX_ON_GAOL("STATE_BOX_ON_GAOL"),
    STATE_PLAYER_ON_GAOL("STATE_PLAYER_ON_GAOL"),
    STATE_EMPTY("STATE_EMPTY"),
    STATE_BOX("STATE_BOX"),
    STATE_PLAYER("STATE_PLAYER"),
    STATE_WALL("STATE_WALL");

    private final String state;

    /**
     * Constructor
     *
     * @param state the squre state
     */
    private SquareState(String state) {
        this.state = state;
    }
}
