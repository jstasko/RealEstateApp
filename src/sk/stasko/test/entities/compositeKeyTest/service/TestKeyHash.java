package sk.stasko.test.entities.compositeKeyTest.service;

import sk.stasko.core.converter.ByteConverter;
import sk.stasko.core.savableObject.SavableObject;
import sk.stasko.core.hash.AbstractHash;
import sk.stasko.test.entities.compositeKeyTest.entity.TestKey;
import sk.stasko.test.entities.compositeKeyTest.entity.TestObject;

public class TestKeyHash extends AbstractHash<TestObject, TestKey> {
    @Override
    protected int hash(SavableObject<TestKey> item) {
        return myHash(item.getKey());
    }

    @Override
    protected int hash(TestKey key) {
        return myHash(key);
    }

    private int myHash(TestKey key) {
        return key.getOne() + key.getTwo();
    }

    @Override
    public int getIndexFromItem(TestObject item, int numberOfBits) {
        if (numberOfBits == 0) {
            return 0;
        }
        byte[] hash = ByteConverter.intToBytes(this.hash(item));
        return getIndex(hash, numberOfBits);
    }

    @Override
    public int getIndexFromKey(TestKey key, int numberOfBits) {
        if (numberOfBits == 0) {
            return 0;
        }
        byte[] hash = ByteConverter.intToBytes(this.hash(key));
        return getIndex(hash, numberOfBits);
    }
    @Override
    public int getIndexFromItem(TestObject item, int numberOfBits, int localDepth) {
        if (numberOfBits == 0) {
            return 0;
        }
//        byte[] hash = ByteConverter.intToBytes(this.hash(item.getKey()));
//        return getFirstBit(hash, numberOfBits);
        return 0;
    }
}
