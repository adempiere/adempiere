/**
 * Copyright (C) 2003-2022, e-Evolution. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.compiere.util;

import io.vavr.CheckedFunction1;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.stream.StreamSupport;

/**
 * Iterable Functional Result Set
 * @param <T>
 */
public class ResultSetIterable<T> implements Iterable<T> {

    private final ResultSet resultSet;
    private final CheckedFunction1<ResultSet, T> onNext;

    public ResultSetIterable(ResultSet resultSet, CheckedFunction1<ResultSet, T> onNext){
        this.resultSet = resultSet;
        //onNext is the mapper function to get the values from the resultSet
        this.onNext = onNext;
    }

    private boolean resultSetHasNext(){
        try {
            return resultSet.next();
        } catch (SQLException e) {
            //you should add proper exception handling here
            throw new RuntimeException(e);
        }
    }


    @Override
    public Iterator<T> iterator() {

        try {
            return new Iterator<T>() {

                //the iterator state is initialized by calling next() to
                //know whether there are elements to iterate
                boolean hasNext = resultSetHasNext();

                @Override
                public boolean hasNext() {
                    return hasNext;
                }

                @Override
                public T next() {

                    T result = null;
                    try {
                        result = onNext.apply(resultSet);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                    //after each get, we need to update the hasNext info
                    hasNext = resultSetHasNext();
                    return result;
                }
            };
        } catch (Exception e) {
            //you should add proper exception handling here
            throw new RuntimeException(e);
        }
    }

    //adding stream support based on iterable is easy
    public Stream<T> asJavaStream() {
        return Stream.ofAll(StreamSupport.stream(this.spliterator(), false));
    }

    //adding stream support based on iterable is easy
    public Stream<T> stream() {
        return Stream.ofAll(asJavaStream());
    }

    public List<T> toList() {
        return stream().toList();
    }

    public java.util.List<T>  asJavaList() {
        return stream().toList().asJava();
    }
}
