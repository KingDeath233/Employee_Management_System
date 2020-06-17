package com.tyy.services;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Pageable;

public class MainService {

	public <T> List<T> searchUsingKey(List<T> key_search, Pageable pageable){
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<T> list;
		if(key_search.size()<startItem) {
			list= Collections.emptyList();
		}else {
			int toIndex = Math.min(startItem + pageSize,  key_search.size());
			list=key_search.subList(startItem, toIndex);
		}
		return list;
	}
}
