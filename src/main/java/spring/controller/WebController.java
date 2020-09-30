package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import spring.dao.WeightRepository;
import spring.model.WeightEntry;

@Controller
public class WebController {

	@Autowired
	WeightRepository repo;
	
	@GetMapping("/view")
	public String viewAllEntries(Model model) {
		if (repo.findAll().isEmpty()) {
			return addNewEntry(model);
		}
		model.addAttribute("entries", repo.findAll());
		return "view";
	}

	@GetMapping("/input")
	public String addNewEntry(Model model) {
		WeightEntry c = new WeightEntry();
		model.addAttribute("newEntry", c);
		return "input";
	}

	@PostMapping("/input")
	public String addNewContact(@ModelAttribute WeightEntry c, Model model) {
		repo.save(c);
		return viewAllEntries(model);
	}

	@GetMapping("/edit/{id}")
	public String showUpdateEntry(@PathVariable("id") long id, Model model) {
		WeightEntry c = repo.findById(id).orElse(null);
		model.addAttribute("newEntry", c);
		return "input";
	}

	@PostMapping("/update/{id}")
	public String reviseEntry(WeightEntry c, Model model) {
		repo.save(c);
		return viewAllEntries(model);
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		WeightEntry c = repo.findById(id).orElse(null);
		repo.delete(c);
		return viewAllEntries(model);

	}
}
