package com.fur.world_db_demo.base;

import java.util.List;

public interface CrudBaseModel<T, K> {

	List<T> getAll();

	T findOne(K key);
}
