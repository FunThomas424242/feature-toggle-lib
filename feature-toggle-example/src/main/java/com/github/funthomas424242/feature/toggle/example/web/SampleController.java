package com.github.funthomas424242.feature.toggle.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.funthomas424242.feature.toggle.example.command.MessageCommand;
import com.github.funthomas424242.feature.toggle.example.domain.Text;
import com.github.funthomas424242.feature.toggle.example.persistence.TextRepository;

@RestController
public class SampleController {

	@Autowired
	private TextRepository repository;

	@Autowired
	private MessageCommand messageCommand;

	@RequestMapping(value = "/characters/{id}", method = RequestMethod.GET)
	public Text text(@PathVariable("id") final Integer id) {
		return repository.find(id);
	}

	@RequestMapping(value = "/characters", method = RequestMethod.GET)
	public List<Text> texteAll() {
		return repository.findAll();
	}

	@RequestMapping(value = "/characters/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Integer id) {
		repository.delete(id);
	}

	@RequestMapping(value = "/features/message", method = RequestMethod.GET)
	public String getMessage() {
		return messageCommand.getMessage();
	}

}
