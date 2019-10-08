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
                Resume[] tmp = Arrays.copyOfRange(storage, i, size );
                storage[i] = resume;
                System.arraycopy(tmp, 0, storage, i + 1, tmp.length);
                return;
            }
        }
        storage[size] = resume;
    }

    @Override
    protected void updateByIndex(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected void deleteByIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size] = null;
        size--;
    }
}
