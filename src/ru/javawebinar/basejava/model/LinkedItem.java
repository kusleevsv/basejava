package ru.javawebinar.basejava.model;

public class LinkedItem {
    private int index;
    private Resume resume;
    private LinkedItem previousItem;
    private LinkedItem nextItem;

    public LinkedItem(Resume resume, LinkedItem previousItem, LinkedItem nextItem, int index) {
        this.resume = resume;
        this.previousItem = previousItem;
        this.nextItem = nextItem;
        this.index = index;
    }

    public Resume getResume() {
        return resume;
    }

    public LinkedItem getPreviousItem() {
        return previousItem;
    }

    public LinkedItem getNextItem() {
        return nextItem;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public void setPreviousItem(LinkedItem previousItem) {
        this.previousItem = previousItem;
    }

    public void setNextItem(LinkedItem nextItem) {
        this.nextItem = nextItem;
    }

    public int getIndex() {return  index;}

    public void setIndex(int index) {
        this.index = index;
    }
}
