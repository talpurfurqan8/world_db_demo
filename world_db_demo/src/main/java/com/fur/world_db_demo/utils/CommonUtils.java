package com.fur.world_db_demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;

public class CommonUtils {

	public static List<Sort.Order> createSortOrder(String sort, String sortOrder) {
		List<Sort.Order> sorts = new ArrayList<>();
		Sort.Direction direction;
		if (sortOrder != null) {
			direction = Sort.Direction.fromString(sortOrder);
		} else {
			direction = Sort.Direction.DESC;
		}
		sorts.add(new Sort.Order(direction, sort));
		return sorts;
	}
}
