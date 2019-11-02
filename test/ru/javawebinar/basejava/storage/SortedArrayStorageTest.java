package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Override
    @Test
    public void getAll_saveAndDeleteResume_orderResume() {
        storage.save(RESUME_4);
        storage.delete(RESUME_2.getUuid());
        Resume[] resumes = {RESUME_1, RESUME_3, RESUME_4};
        Assert.assertArrayEquals(resumes, storage.getAll());
    }
}