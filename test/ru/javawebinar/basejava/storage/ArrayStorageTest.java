package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public class ArrayStorageTest {
    Storage storage;
    private final static Resume resume1 = new Resume("uuid1");
    private final static Resume resume2 = new Resume("uuid2");
    private final static Resume resume3 = new Resume("uuid3");
    private final static String UUID4 = "uuid4";

    @Before
    public void setUp() {
        storage = new ArrayStorage();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(resume2, storage.get(resume2.getUuid()));
    }

    @Test
    public void save() {
        Resume resume4 = add(UUID4);
        Assert.assertEquals(resume4, storage.get(resume4.getUuid()));
    }

    @Test
    public void upgrade() {
        Resume resume4 = add(UUID4);
        Resume resume4new = new Resume(resume4.getUuid());
        storage.update(resume4new);
        Assert.assertEquals(resume4new, storage.get(resume4.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        add(UUID4);
        storage.delete(UUID4);
        storage.get(UUID4);
    }

    private Resume add(String uuid) {
        Resume resume4 = new Resume(uuid);
        storage.save(resume4);
        return resume4;
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        add(UUID4);
        storage.delete(resume2.getUuid());
        Resume[] resumes = {resume1, new Resume(UUID4), resume3};
        Assert.assertArrayEquals(resumes, storage.getAll());
    }
}