package com.akila.demo.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Phuc Nguyen
 * @param <E>
 * @param <D>
 */
public interface EntityConverter<E, D> {

    default List<D> convertToDtoList(List<E> entityList) {
        List<D> dtoList = new ArrayList<>();

        if (entityList != null) {
            entityList.forEach(entity -> {
                if (entity != null) {
                    dtoList.add(convertToDto(entity));
                }
            });
        }

        return dtoList;
    }

    D convertToDto(E entity);
}
