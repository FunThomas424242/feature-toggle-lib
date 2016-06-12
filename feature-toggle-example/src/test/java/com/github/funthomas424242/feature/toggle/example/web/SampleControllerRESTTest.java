package com.github.funthomas424242.feature.toggle.example.web;

import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.funthomas424242.feature.toggle.example.Application;
import com.github.funthomas424242.feature.toggle.example.domain.Text;
import com.github.funthomas424242.feature.toggle.example.persistence.TextRepository;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SampleControllerRESTTest {

	@Autowired
	TextRepository repository;

	Text mickey;
	Text minnie;
	Text pluto;

	@Value("${local.server.port}")
	int port;

	@Before
	public void setUp() {

		mickey = new Text("Mickey Mouse");
		minnie = new Text("Minnie Mouse");
		pluto = new Text("Pluto");

		repository.deleteAll();
		repository.save(Arrays.asList(mickey, minnie, pluto));

		RestAssured.port = port;
	}

	@Test
	public void canFetchMickey() {
		final Integer mickeyId = mickey.getId();

		when().get("/characters/{id}", mickeyId).then()
				.statusCode(HttpStatus.SC_OK)
				.body("name", Matchers.is("Mickey Mouse"))
				.body("id", Matchers.is(mickeyId));
	}

	@Test
	public void canFetchAll() {
		when().get("/characters").then().statusCode(HttpStatus.SC_OK).body(
				"name",
				Matchers.hasItems("Mickey Mouse", "Minnie Mouse", "Pluto"));
	}

	@Test
	public void canDeletePluto() {
		final Integer plutoId = pluto.getId();

		when().delete("/characters/{id}", plutoId).then()
				.statusCode(HttpStatus.SC_NO_CONTENT);
	}
}