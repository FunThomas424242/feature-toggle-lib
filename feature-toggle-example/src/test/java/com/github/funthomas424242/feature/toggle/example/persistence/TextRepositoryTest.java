package com.github.funthomas424242.feature.toggle.example.persistence;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.funthomas424242.feature.toggle.example.domain.Text;

public class TextRepositoryTest {

	TextRepository repository;

	Text mickey;
	Text minnie;
	Text pluto;

	@Before
	public void setUp() {
		mickey = new Text("Mickey Mouse");
		mickey.setId(1);
		minnie = new Text("Minnie Mouse");
		minnie.setId(2);
		pluto = new Text("Pluto");
		pluto.setId(3);

		repository = new TextRepository();
		repository.dbTable.clear();
		repository.dbTable.put(1, mickey);
		repository.dbTable.put(2, minnie);
		repository.dbTable.put(3, pluto);

	}

	@Test
	public void testFindMinnie() {

		final Text text = repository.find(2);
		assertEquals((Integer) 2, text.getId());

	}

	@Test
	public void testFindAll() {
		final List<Text> alleTexte = repository.findAll();
		assertEquals(3, alleTexte.size());
	}

	@Test
	public void testDeletePluto() {
		assertEquals(3, repository.dbTable.size());
		repository.delete(pluto.getId());
		assertEquals(2, repository.dbTable.size());
		assertNull(repository.find(pluto.getId()));
	}

	@Test
	public void testDeleteAll() {
		assertEquals(3, repository.dbTable.size());
		repository.deleteAll();
		assertEquals(0, repository.dbTable.size());
	}

	@Test
	public void testSave() {
		final Text text1 = new Text("Schweinchen Babe");
		final Text text2 = new Text("Kermit der Frosch");
		final List<Text> texte = Arrays.asList(text1, text2);
		repository.save(texte);

		final Text kermit = repository.find(5);
		assertEquals(text2, kermit);
	}

}
