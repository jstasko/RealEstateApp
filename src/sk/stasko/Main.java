package sk.stasko;

import sk.stasko.core.extendingHashing.ExtendingHashing;
import sk.stasko.core.extendingHashing.overflowingFile.OverflowingFile;
import sk.stasko.core.extendingHashing.overflowingFile.OverflowingFileImpl;
import sk.stasko.core.fileHandler.FileHandler;
import sk.stasko.core.hash.AbstractHash;
import sk.stasko.model.gps.Gps;
import sk.stasko.model.realEstate.RealEstate;
import sk.stasko.model.realEstate.RealEstateFileHandler;
import sk.stasko.model.realEstate.RealEstateKeyHash;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        IntStream.range(0, 1).forEach(i -> {
            System.out.println(i);
        });
        int numberOfRecord = 2;
        List<RealEstate> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                list.add(new RealEstate(1996, "QQQQ", new Gps(30d, 100d)));
            }
            list.add(new RealEstate(i, "jozef", new Gps(25d, 45d)));
        }

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("realEstate.dat", "rw");
            RandomAccessFile randomAccessFileOver = new RandomAccessFile("overFlowRealEstate.dat", "rw");
            FileHandler<RealEstate> r = new RealEstateFileHandler(randomAccessFile);
            FileHandler<RealEstate> ro = new RealEstateFileHandler(randomAccessFileOver);
            AbstractHash<RealEstate, Integer> h = new RealEstateKeyHash();
            OverflowingFile<RealEstate, Integer> o = new OverflowingFileImpl<>(numberOfRecord, RealEstate.allocatedMemory, ro);
            ExtendingHashing<RealEstate, Integer> extendibleHashing = new ExtendingHashing<>(numberOfRecord, RealEstate.allocatedMemory, 2, h, r, o);
            Collections.shuffle(list);
            for (RealEstate estate: list) {
                extendibleHashing.add(estate);

            }
            for (RealEstate estate: list) {
                RealEstate find = extendibleHashing.find(estate.getKey());
                int a = 0;
            }
            for (RealEstate estate: list) {
                extendibleHashing.delete(estate);
            }
            System.out.println(extendibleHashing.printBlocks(RealEstate.allocatedMemory));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
}
