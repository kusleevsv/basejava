package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {
        Arrays.fill( storage, 0, size, null );
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange( storage, 0, size );
    }

    @Override
    protected int getIndex( Resume resume ) {
        for( int i = 0 ; i < size ; i++ ) {
            if( resume.getUuid().equals( storage[ i ].getUuid() ) ) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void addResume( Resume resume ) {
        storage[ size ] = resume;
        size++;
    }

    @Override
    protected void updateByIndex( Resume resume, int index ) {
        storage[ index ] = resume;
    }

    @Override
    protected void deleteByIndex( int index ) {
        storage[ index ] = storage[ size - 1 ];
        storage[ size - 1 ] = null;
        size--;
    }
}