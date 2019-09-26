/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[3];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size - 1; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (resume != null && resume.uuid != null && size < storage.length) {
            int idx = searchByUuid(resume.uuid);
            if (idx == -1) {
                storage[size] = resume;
                size++;
            }
        }
    }

    private int searchByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    Resume get(String uuid) {
        int idx = searchByUuid(uuid);
        if (idx != -1) {
            return storage[idx];
        }
        return null;
    }

    void delete(String uuid) {
        int idx = searchByUuid(uuid);
        if (idx != -1) {
            for (int i = idx; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int getSize() {
        return size;
    }
}
