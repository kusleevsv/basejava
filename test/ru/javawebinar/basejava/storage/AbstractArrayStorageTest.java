package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;


public abstract class AbstractArrayStorageTest {
    public final static Resume RESUME_1 = new Resume("uuid1");
    public final static Resume RESUME_2 = new Resume("uuid2");
    public final static Resume RESUME_3 = new Resume("uuid3");
    public final static Resume RESUME_4 = new Resume("uuid4");
    protected Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.save(RESUME_1);
        storage.save(RESUME_3);
        storage.save(RESUME_2);
    }

    @Test
    public void size_getSizeStorage_int3() {
        storage.save(RESUME_4);
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void get_getResume_currentResume() {
        Assert.assertEquals(RESUME_2, storage.get(RESUME_2.getUuid()));
    }

    @Test
    public void save_addResume_getAddedResume() {
        storage.save(RESUME_4);
        Assert.assertEquals(RESUME_4, storage.get(RESUME_4.getUuid()));
    }

    @Test
    public void upgrade_saveNewResume_getUpdatedResume() {
        storage.save(RESUME_4);
        Resume resume4new = new Resume(RESUME_4.getUuid());
        storage.update(resume4new);
        Assert.assertEquals(resume4new, storage.get(RESUME_4.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete_deleteResume_exception() {
        storage.save(RESUME_4);
        storage.delete(storage.get(RESUME_4.getUuid()).getUuid());
        storage.get(RESUME_4.getUuid());
    }


    @Test
    public void clear_clearStorage_storageSizeZero() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll_saveAndDeleteResume_orderResume() {
    }
}
