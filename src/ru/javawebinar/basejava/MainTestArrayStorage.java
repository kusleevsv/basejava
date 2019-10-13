package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test ru.javawebinar.basejava.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new ArrayStorage();
    private static final Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        runTests(ARRAY_STORAGE);
        runTests(SORTED_ARRAY_STORAGE);
    }

    private static void runTests(Storage storage) {
        System.out.println("------------ START test with: " + storage.getClass() + " ------------------");
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");
        Resume r5 = new Resume("uuid5");
        Resume r6 = new Resume("uuid6");

        storage.save(r1);
        storage.save(r3);
        storage.save(r2);
        storage.save(r5);
        storage.save(r6);
        storage.save(r6);
        storage.save(r4);

        System.out.println("Get r1: " + storage.get(r1.getUuid()));
        System.out.println("Size: " + storage.size());
        System.out.println("Get r1: " + storage.get(r1.getUuid()));

        System.out.println("Before update uuid3. HashCode: " + storage.get(r3.getUuid()).hashCode());
        r3 = new Resume(r3.getUuid());
        storage.update(r3);
        System.out.println("After update uuid3. HashCode: " + storage.get(r3.getUuid()).hashCode());

        System.out.println("Get dummy: " + storage.get("dummy"));

        printAll(storage);
        storage.delete(r1.getUuid());
        printAll(storage);
        storage.delete(r5.getUuid());
        printAll(storage);
        storage.clear();
        printAll(storage);

        System.out.println("Size: " + storage.size());
        System.out.println("============ END test with: " + storage.getClass() + " ====================");
        System.out.println(" ");
    }

    private static void printAll(Storage storage) {
        System.out.println("\nGet All");
        for (Resume resume : storage.getAll()) {
            System.out.println(resume);
        }
    }
}
