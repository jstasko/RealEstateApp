package sk.stasko.core.hash;

public interface ExtendingHashFunction<T, U> {
    int getIndexFromItem(T item, int numberOfBits);
    int getIndexFromKey(U key, int numberOfBits);
    int getIndexFromItem(T item, int numberOfBits, int localDepth);
}
