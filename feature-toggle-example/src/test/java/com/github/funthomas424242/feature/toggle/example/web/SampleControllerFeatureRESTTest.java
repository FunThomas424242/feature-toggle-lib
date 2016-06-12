package com.github.funthomas424242.feature.toggle.example.web;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.funthomas424242.feature.toggle.example.Application;
import com.github.funthomas424242.feature.toggle.example.config.Features;
import com.github.funthomas424242.feature.toggle.example.domain.Text;
import com.github.funthomas424242.libs.toggle.FeatureToggleRule;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SampleControllerFeatureRESTTest {

	static final Logger LOG = LoggerFactory
			.getLogger(SampleControllerFeatureRESTTest.class);

	@Rule
	public FeatureToggleRule togglRule = new FeatureToggleRule(
			Features.values());

	Text mickey;
	Text minnie;
	Text pluto;

	@Value("${local.server.port}")
	int port;

	@Before
	public void setUp() {

		RestAssured.port = port;
	}

	@Test
	public void testFeatureHello() {
		LOG.debug("TEST ThreadId:" + Thread.currentThread().getId());
		togglRule.enable(Features.FEATURE_HELLO);
		togglRule.disable(Features.FEATURE_HALLO);
		togglRule.disable(Features.FEATURE_HERO);

		when().get("/features/message").then().statusCode(HttpStatus.SC_OK);
		// .body("name", Matchers.is("Mickey Mouse"));
	}

	// @Test
	// public void canFetchAll() {
	// when().get("/characters").then().statusCode(HttpStatus.SC_OK).body(
	// "name",
	// Matchers.hasItems("Mickey Mouse", "Minnie Mouse", "Pluto"));
	// }
	//
	// @Test
	// public void canDeletePluto() {
	// final Integer plutoId = pluto.getId();
	//
	// when().delete("/characters/{id}", plutoId).then()
	// .statusCode(HttpStatus.SC_NO_CONTENT);
	// }
}