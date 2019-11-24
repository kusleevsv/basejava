package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.LinkedItem;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Objects;

public class ListStorage extends AbstractStorage {
    private int size = 0;
    private int capacity = 10;
    private LinkedItem[] linkedItems;
    private LinkedItem boudaryItem = new LinkedItem(null, null, null, -1);

    public ListStorage() {
        linkedItems = new LinkedItem[capacity];
    }

    public ListStorage(int capacity) {
        this.capacity = capacity;
        new ListStorage();
    }

    public ListStorage(Resume[] resumes) {
        new ListStorage(resumes.length);
        for (Resume resume : resumes) {
            putItem(resume);
        }
    }

    @Override
    public void clear() {
        size = 0;
        capacity = 10;
        linkedItems = new LinkedItem[capacity];
        boudaryItem = new LinkedItem(null, null, null, -1);
    }

    @Override
    public void save(Resume resume) {
        if (boudaryItem.getIndex() == linkedItems.length) {
            increaseCapacity();
        }
        putItem(resume);
    }

    @Override
    public Resume get(String uuid) {
        LinkedItem linkedItem = getItem(uuid);
        if (Objects.nonNull(linkedItem)) {
            return linkedItem.getResume();
        }
        return null;
    }

    private LinkedItem getItem(String uuid) {
        LinkedItem linkedItem = boudaryItem.getPreviousItem();
        while (linkedItem != null) {
            if (uuid.equals(linkedItem.getResume().getUuid())) {
                return linkedItem;
            }
            linkedItem = linkedItem.getNextItem();
        }
        return null;
    }

    @Override
    public Resume[] getAll() {
        LinkedItem linkedItem = boudaryItem.getPreviousItem();
        Resume[] resumes = new Resume[size];
        int i = 0;
        while (linkedItem != null) {
            resumes[i] = linkedItem.getResume();
            i++;
            linkedItem = linkedItem.getNextItem();
        }
        return resumes;
    }

    @Override
    public void delete(String uuid) {
        LinkedItem linkedItem = getItem(uuid);
        if (Objects.nonNull(linkedItem)) {
            if (Objects.nonNull(linkedItem.getPreviousItem())) {
                   linkedItem.getPreviousItem().setNextItem(linkedItem.getNextItem());
            } else {
                boudaryItem.setPreviousItem(linkedItem.getNextItem());
            }

            if(Objects.nonNull(linkedItem.getNextItem())) {
                linkedItem.getNextItem().setPreviousItem(linkedItem.getPreviousItem());
            } else {
                boudaryItem.setNextItem(linkedItem.getPreviousItem());
            }

            linkedItem = null;
            size--;
        }
    }

    @Override
    public void update(Resume resume) {
        LinkedItem linkedItem = getItem(resume.getUuid());
        if(Objects.nonNull(linkedItem)) {
            linkedItem.setResume(resume);
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void increaseCapacity() {
        capacity = capacity * 2;
        Arrays.copyOf(linkedItems, capacity);
    }

    private void putItem(Resume resume) {
        LinkedItem linkedItem = new LinkedItem(resume, boudaryItem.getNextItem(), null, boudaryItem.getIndex() + 1);
        linkedItems[boudaryItem.getIndex() + 1] = linkedItem;
        if (boudaryItem.getPreviousItem() == null) {
            boudaryItem.setPreviousItem(linkedItem);
        } else {
            boudaryItem.getNextItem().setNextItem(linkedItem);
        }
        boudaryItem.setNextItem(linkedItem);
        boudaryItem.setIndex(boudaryItem.getIndex() + 1);
        size++;
    }
}
