package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Override
    @Test
    public void getAll_saveAndDeleteResume_orderResume() {
        storage.delete(RESUME_2.getUuid());
        storage.save(RESUME_4);
        storage.save(RESUME_2);
        Resume[] resumes = {RESUME_1, RESUME_3, RESUME_4, RESUME_2};
        Assert.assertArrayEquals(resumes, storage.getAll());
    }
}