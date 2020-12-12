package sk.stasko.model.realEstate;

import sk.stasko.core.converter.ByteConverter;
import sk.stasko.core.savableObject.SavableObject;
import sk.stasko.core.hash.AbstractHash;

public class RealEstateKeyHash extends AbstractHash<RealEstate, Integer> {
    @Override
    protected int hash(SavableObject<Integer> item) {
        return this.myHashFunction(item.getKey());
    }

    @Override
    protected int hash(Integer key) {
        return this.myHashFunction(key);
    }

    @Override
    public int getIndexFromItem(RealEstate realEstate, int numberOfBits) {
        if (numberOfBits == 0) {
            return 0;
        }
        byte[] hash = ByteConverter.intToBytes(this.hash(realEstate));
        return this.getIndex(hash, numberOfBits);
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
    public int getIndexFromItem(RealEstate item, int numberOfBits, int localDepth) {
        if (numberOfBits == 0) {
            return 0;
        }
        byte[] hash = ByteConverter.intToBytes(this.hash(item));
        return getIndex(hash, numberOfBits, localDepth);
    }

    private int myHashFunction(Integer key) {
        return key;
    }
}
