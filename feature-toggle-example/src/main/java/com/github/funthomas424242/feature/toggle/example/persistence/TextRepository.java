package com.github.funthomas424242.feature.toggle.example.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.github.funthomas424242.feature.toggle.example.domain.Text;

@Component
public class TextRepository {

	protected int globalId = 0;

	final Map<Integer, Text> dbTable = new HashMap<>();

	protected synchronized int getNextId() {
		return new Integer(++globalId);
	}

	public Text find(final Integer id) {
		return dbTable.get(id);
	}

	public List<Text> findAll() {
		return dbTable.values().stream()
				.collect(Collectors.toList());
	}

	public void delete(final Integer id) {
		// TODO Auto-generated method stub
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	public void save(final List<Text> texte) {
		for (final Text text : texte) {
			Integer id = text.getId();
			if (id == null || !dbTable.containsKey(id)) {
				if (id == null) {
					id = getNextId();
					text.setId(id);
				}
				// both: insert and update text
				dbTable.put(id, text);
			}
		}
	}

}
