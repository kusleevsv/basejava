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
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
        Assert.assertEquals(resume4, storage.get(resume4.getUuid()));
    }

    @Test
    public void upgrade() {
        Resume resume4 = new Resume("uuid4");
        storage.update(resume4);
        Assert.assertEquals(resume4, storage.get(resume4.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid4");
        storage.get("uuid4");
    }
}