package ru.javawebinar.basejava.storage;
import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedList;

public class LinkedS {
    private Item header;
    private LinkedList<String> example = new LinkedList<>();

    public LinkedS() {
        header = new Item(null, null, null);
    }

    public void add(Resume resume) {

    }

    private static class Item {
        private int index;
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
