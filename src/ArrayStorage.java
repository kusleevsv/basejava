import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[3];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        if (!checkResume(resume)) {
            return;
        }

        if (size >= storage.length) {
            System.out.println("Error: failed to save resume. No free space in storage.");
            return;
        }

        if (searchByUuid(resume.uuid) != -1) {
            System.out.println("Error: failed to save resume. Resume with uuid: " + resume.uuid + " already exists.");
            return;
        }

        storage[size] = resume;
        size++;
    }

    void update(Resume resume) {
        if (!checkResume(resume)) {
            return;
        }

        int idx = searchByUuid(resume.uuid);
        if (idx == -1) {
            System.out.println("Error: No resume found.");
            return;
        }

        storage[idx] = resume;
    }

    Resume get(String uuid) {
        int idx = searchByUuid(uuid);
        if (idx != -1) {
            return storage[idx];
        }
        System.out.println("Error: No resume found.");
        return null;
    }

    void delete(String uuid) {
        int idx = searchByUuid(uuid);
        if (idx != -1) {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error: No resume found.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int getSize() {
        return size;
    }

    private int searchByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
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

        if (resume.uuid == null || resume.uuid.equals("")) {
            System.out.println("Error: Resume must not be null and must not be empty");
            return false;
        }

        return true;
    }
}
