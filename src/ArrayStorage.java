/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume resume) {
        for (int i = 0; i < size(); i++) {
            if (storage[i] == null) {
                storage[i] = resume;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] withoutHoles = new Resume[size()];
        int j = 0;
        for (int i = 0; i < size(); i++) {
            if (storage[i] != null) {
                withoutHoles[j] = storage[i];
                j++;
            }
        }
        return withoutHoles;
    }

    int size() {
        return storage.length;
    }
}
