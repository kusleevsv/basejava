/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume resume) {
        if(resume != null && resume.uuid != null) {
            for( int i = 0 ; i < storage.length ; i++ ) {
                if( storage[ i ] == null ) {
                    storage[ i ] = resume;
                    break;
                }
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                storage = this.getAll();
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] withoutHoles = new Resume[storage.length];
        int j = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                withoutHoles[j] = storage[i];
                j++;
            }
        }
        return withoutHoles;
    }

    int size() {
        int resumeCount = 0;
        for(int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                resumeCount++;
            }
        }
        return resumeCount;
    }
}
