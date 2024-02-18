package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public interface Subject {

    /**
     * To add new observer
     *
     * @param obs the new observer to add
     */
    public void registerObserver(Observer obs);

    /**
     * To reemove an observer from subject
     *
     * @param obs the new observer to add
     */
    public void removeObserver(Observer obs);

    /**
     * To add new observer
     */
    public void notifyObserver();
}
