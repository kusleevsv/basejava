package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(Resume resume) {
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    protected void addResume(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].compareTo(resume) > 0) {
                System.arraycopy(storage, i, storage, i + 1, size - i + 1  );
                storage[i] = resume;
                return;
            }
        }
        storage[size] = resume;
    }

    @Override
    protected void deleteByIndex(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}
