package by.svirski.testweb.dao;

import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.BeanIndicator;

public interface Dao<T extends BeanIndicator> {
	
	boolean insert(Map<String, String> paramters);
	boolean update(Map<String, String> parameters);
	List<T> select(List<String> parameters);
}
