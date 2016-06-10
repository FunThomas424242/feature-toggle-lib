package com.github.funthomas424242.feature.toggle.example.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.github.funthomas424242.feature.toggle.example.domain.Text;

@Component
public class TextRepository {

	final Map<Integer, Text> dbTable = new HashMap<>();

	protected synchronized int addText(final Text text) {
		text.setId(new Integer(dbTable.size() + 1));
		dbTable.put(text.getId(), text);
		return text.getId();
	}

	protected void updateText(final Text text) {
		final int id = text.getId();
		dbTable.put(id, text);
	}

	public Text find(final Integer id) {
		return dbTable.get(id);
	}

	public List<Text> findAll() {
		return dbTable.values().stream().collect(Collectors.toList());
	}

	public void delete(final Integer id) {
		dbTable.remove(id);
	}

	public void deleteAll() {
		dbTable.clear();
	}

	public void save(final List<Text> texte) {
		for (final Text text : texte) {
			final Integer id = text.getId();
			if (id == null || !dbTable.containsKey(id)) {
				if (id == null) {
					addText(text);
				} else {
					updateText(text);
				}
			}
		}
	}

}
