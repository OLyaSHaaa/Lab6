package collectionManager;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация интерфейса IdManager
 */
public class VenueIdManager implements IdManager<Integer>{
    private Set<Integer> usedIdentifiers;
    private static VenueIdManager instance;

    /**
     * @return объект VenueIdManager
     */
    public synchronized static VenueIdManager getInstance(){
        if (instance == null){
            instance = new VenueIdManager();
        }
        return instance;
    }

    private VenueIdManager(){
        usedIdentifiers = new HashSet<>();
    }

    @Override
    public boolean idIsFree(Integer id) {
        return !usedIdentifiers.contains(id);
    }

    @Override
    public Integer getFreeId() {
        Integer prevId = 0;
        for (Integer id: usedIdentifiers) {
            if (id - prevId != 1) {
                break;
            }
            prevId = id;
        }
        return prevId + 1;
    }

    @Override
    public void addId(Integer id) {
        usedIdentifiers.add(id);
    }

    @Override
    public void removeId(Integer id) {
        usedIdentifiers.remove(id);
    }

    @Override
    public void clearIdentifiers() {
        usedIdentifiers.clear();
    }
}
