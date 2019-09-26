/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public ArrayStorage() {
    }

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (resume != null && resume.uuid != null && size < storage.length) {
            Integer idx = searchByUuid(resume.uuid);
            if (idx != null) {
                storage[idx] = resume;
            } else {
                storage[size] = resume;
                size++;
            }
        }
    }

    private Integer searchByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    Resume get(String uuid) {
        Integer idx = searchByUuid(uuid);
        if (idx != null) {
            return storage[idx];
        }
        return null;
    }

    void delete(String uuid) {
        Integer idx = searchByUuid(uuid);
        if (idx != null) {
            for (int i = idx; i < size; i++) {
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
