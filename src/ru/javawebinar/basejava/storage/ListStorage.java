package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Objects;

public class ListStorage extends AbstractStorage {
    private Item header;
    private int size = 0;

    public ListStorage() {
        header = new Item(null, null, null);
    }

    @Override
    public void save(Resume resume) {
        Item item = new Item(resume, header.nextItem, null);
        if (Objects.nonNull(header.previousItem)) {
            header.nextItem.nextItem = item;
        } else {
            header.previousItem = item;
        }
        header.nextItem = item;
        size++;
    }

    @Override
    public void clear() {
        Item item = header.previousItem;
        while (Objects.nonNull(item)) {
            item = item.nextItem;
            if (Objects.nonNull(item)) {
                item.previousItem = null;
            }
        }
        header.nextItem = null;
        header.previousItem = null;
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        Item item = header.previousItem;
        while (Objects.nonNull(item)) {
            if (item.resume.getUuid().equals(resume.getUuid())) {
                item.resume = resume;
                return;
            }
            item = item.nextItem;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    @Override
    public Resume get(String uuid) {
        Item item = header.previousItem;
        while (Objects.nonNull(item)) {
            if (item.resume.getUuid().equals(uuid)) {
                return item.resume;
            }
            item = item.nextItem;
        }
       return null;
    }

    @Override
    public void delete(String uuid) {
        Item item = header.previousItem;
        while (Objects.nonNull(item)) {
            if (item.resume.getUuid().equals(uuid)) {
                if(Objects.nonNull(item.previousItem)) {
                    item.previousItem.nextItem = item.nextItem;
                } else {
                    header.previousItem = item.nextItem;
                }
                if(Objects.nonNull(item.nextItem)) {
                    item.nextItem.previousItem = item.previousItem;
                } else {
                    header.nextItem = item.previousItem;
                }
                size--;
                return;
            }
            item = item.nextItem;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        Item item = header.previousItem;
        int i = 0;
        while (Objects.nonNull(item)) {
            resumes[i] = item.resume;
            item = item.nextItem;
            i++;
        }
        return resumes;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Item {
        private Resume resume;
        private Item previousItem;
        private Item nextItem;

        Item(Resume resume, Item previousItem, Item nextItem) {
            this.resume = resume;
            this.previousItem = previousItem;
            this.nextItem = nextItem;
        }
    }
}
