package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;


public abstract class AbstractArrayStorageTest {
    Storage storage;
    private final static Resume resume1 = new Resume("uuid1");
    private final static Resume resume2 = new Resume("uuid2");
    private final static Resume resume3 = new Resume("uuid3");

    public AbstractArrayStorageTest() {
    }

    protected  AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume updatedResume = new Resume("uuid2");
        storage.update(resume2);
        Assert.assertEquals(updatedResume, storage.get(updatedResume.getUuid()));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resume = {resume1, resume2, resume3};
        Assert.assertArrayEquals(resume, storage.getAll());
    }

    @Test
    public void save() throws Exception {
        Assert.assertEquals(resume2, storage.get(resume2.getUuid()));
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(resume2.getUuid(), storage.get(resume2.getUuid()).getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}