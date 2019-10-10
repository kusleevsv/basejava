package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        if (!checkResume(resume)) {
            return;
        }
        int index = getIndex(resume);

        if (index == -1) {
            System.out.println("Resume " + resume.getUuid() + " not exist");
        }

        storage[index] = resume;
    }

    public void save(Resume resume) {
        if (!checkResume(resume)) {
            return;
        }

        if (size >= STORAGE_LIMIT) {
            System.out.println("Error: failed to save resume. No free space in storage.");
            return;
        }

        int index = getIndex(resume);

        if (index > 0) {
            System.out.println("Error: failed to save resume. Resume with uuid: " + resume.getUuid() + " already exists.");
            return;
        }

        addResume(resume, -(index + 1));
        size++;
    }

    public Resume get(String uuid) {
        int index = getIndex(new Resume(uuid));
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(new Resume(uuid));

        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return;
        }

        deleteByIndex(index);
        storage[size - 1] = null;
        size--;
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

    protected abstract int getIndex(Resume resume);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void deleteByIndex(int index);
}
