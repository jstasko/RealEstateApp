package sk.stasko.test.entities.personTest.service;

import sk.stasko.core.converter.ByteConverter;
import sk.stasko.core.savableObject.SavableObject;
import sk.stasko.core.hash.AbstractHash;
import sk.stasko.test.entities.personTest.entity.Person;

public class PersonKeyHash extends AbstractHash<Person, Integer> {
    @Override
    protected int hash(SavableObject<Integer> item) {
        return item.getKey();
    }

    @Override
    protected int hash(Integer key) {
        return key;
    }

    @Override
    public int getIndexFromItem(Person item, int numberOfBits) {
        if (numberOfBits == 0) {
            return 0;
        }
        byte[] hash = ByteConverter.intToBytes(this.hash(item));
        return getIndex(hash, numberOfBits);
    }

    @Override
    public int getIndexFromKey(Integer key, int numberOfBits) {
        if (numberOfBits == 0) {
            return 0;
        }
        byte[] hash = ByteConverter.intToBytes(this.hash(key));
        return getIndex(hash, numberOfBits);
    }

    @Override
    public int getIndexFromItem(Person item, int numberOfBits, int localDepth) {
        if (numberOfBits == 0) {
            return 0;
        }
        byte[] hash = ByteConverter.intToBytes(this.hash(item));
        return getIndex(hash, numberOfBits, localDepth);
    }
}
