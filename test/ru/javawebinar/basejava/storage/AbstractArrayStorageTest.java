package ru.javawebinar.basejava.storage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;


public abstract class AbstractArrayStorageTest {
    private final static Resume RESUME_1 = new Resume("uuid1");
    private final static Resume RESUME_2 = new Resume("uuid2");
    private final static Resume RESUME_3 = new Resume("uuid3");
    private final static Resume RESUME_4 = new Resume("uuid4");
    private Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @After
    public void clearData() {
        storage.clear();
    }

    @Test
    public void size_getSizeStorage_sizeEq4() {
        storage.save(RESUME_4);
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void get_getResume2_true() {
        Assert.assertEquals(RESUME_2, storage.get(RESUME_2.getUuid()));
    }

    @Test
    public void save_addResume4_getResume4True() {
        storage.save(RESUME_4);
        Assert.assertEquals(RESUME_4, storage.get(RESUME_4.getUuid()));
    }

    @Test
    public void upgrade_saveAndUpdateResume4_getUpdatedResume4True() {
        storage.save(RESUME_4);
        Resume resume4new = new Resume(RESUME_4.getUuid());
        storage.update(resume4new);
        Assert.assertEquals(resume4new, storage.get(RESUME_4.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete_deleteAndGetResume4_NotExistStorageException() {
        storage.save(RESUME_4);
        storage.delete(storage.get(RESUME_4.getUuid()).getUuid());
        storage.get(RESUME_4.getUuid());
    }


    @Test
    public void clear_storageSizeZero_true() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll_comparingTwoArrays_true() {
        Resume[] resumes = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void get_getResume4_NotExistStorageException() throws NotExistStorageException {
        storage.get(RESUME_4.getUuid());
    }

    @Test(expected = StorageException.class)
    public void save_add10_001Resumes_StorageException() throws StorageException {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("uuid" + AbstractArrayStorage.STORAGE_LIMIT + 1));
    }

    @Test(expected = ExistStorageException.class)
    public void save_resume3_ExistStorageException() throws ExistStorageException {
        storage.save(RESUME_3);
    }

}
