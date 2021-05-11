package collectionManager;

public interface IdManager<T> {

    boolean idIsFree(T id);
    T getFreeId();
    void addId(T id);
    void removeId(T id);
    void clearIdentifiers();
}

