package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (!checkResume(resume)) {
            return;
        }

        if (size >= storage.length) {
            System.out.println("Error: failed to save resume. No free space in storage.");
            return;
        }

        if (searchByUuid(resume.getUuid()) != -1) {
            System.out.println("Error: failed to save resume. Resume with uuid: " + resume.getUuid() + " already exists.");
            return;
        }

        storage[size] = resume;
        size++;
    }

    public void update(Resume resume) {
        if (!checkResume(resume)) {
            return;
        }

        int idx = searchByUuid(resume.getUuid());
        if (idx == -1) {
            System.out.println("Error: Resume with uuid: " + resume.getUuid() + " no found.");
            return;
        }

        storage[idx] = resume;
    }

    public Resume get(String uuid) {
        int idx = searchByUuid(uuid);
        if (idx != -1) {
            return storage[idx];
        }
        System.out.println("Error: Resume with uuid: " + uuid + " no found.");
        return null;
    }

    public void delete(String uuid) {
        int idx = searchByUuid(uuid);
        if (idx != -1) {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error: Resume with uuid: " + uuid + " no found.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int getSize() {
        return size;
    }

    private int searchByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkResume(Resume resume) {
        if (resume == null) {
            System.out.println("Error: Resume must not be null.");
            return false;
        }

        if (resume.getUuid() == null || resume.getUuid().isEmpty()) {
            System.out.println("Error: Resume uuid must not be null and must not be empty");
            return false;
        }

        return true;
    }
}
