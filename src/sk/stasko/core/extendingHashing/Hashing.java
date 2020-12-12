package sk.stasko.core.extendingHashing;

import java.io.IOException;

public interface Hashing<T, U> {
    void add(T item) throws IOException;
    boolean delete(T item) throws IOException;
    T find(U key) throws IOException;
    String printBlocks(int spaceInOneBlock) throws IOException;
    String printBlankBlock();
}
