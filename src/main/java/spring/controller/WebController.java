package spring.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dao.WeightRepository;
import spring.model.WeightEntry;

/**
 * This class manages database connections and web mapping for the application.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-10-1
 */
@Controller
public class WebController {

	@Autowired
	WeightRepository repo;

	/**
	 * Handles the list view of all entries in the database.
	 * 
	 * @param model
	 * @return Connects to view.html
	 */
	@GetMapping("/view")
	public String viewAllEntries(Model model) {
		// If no entries, redirect to add new item form
		if (repo.findAll().isEmpty()) {
			return addNewEntry(model);
		}
		// Full list of database entries
		model.addAttribute("entries", repo.findAll());
		// list of entries formatted for use in javascript chart
		model.addAttribute("dataPoints", getDataPoints());
		return "view";
	}

	/**
	 * Sets up form page for use
	 * 
	 * @param model
	 * @return Connects to input.html
	 */
	@GetMapping("/input")
	public String addNewEntry(Model model) {
		WeightEntry c = new WeightEntry();
		model.addAttribute("newEntry", c);
		return "input";
	}

	/**
	 * Saves form submission to database
	 * 
	 * @param c     WeightEntry initialized in @GetMapping method.
	 * @param model
	 * @return Redirects to view.html
	 */
	@PostMapping("/input")
	public String addNewEntry(@ModelAttribute WeightEntry c, Model model) {
		repo.save(c);
		return viewAllEntries(model);
	}

	/**
	 * Sets up form page for use with existing data to be edited.
	 * 
	 * @param id    primary key id to be edited
	 * @param model
	 * @return Connects to input.html
	 */
	@GetMapping("/edit/{id}")
	public String showUpdateEntry(@PathVariable("id") long id, Model model) {
		WeightEntry c = repo.findById(id).orElse(null);
		model.addAttribute("newEntry", c);
		return "input";
	}

	/**
	 * Merges form data with database entry
	 * 
	 * @param c     Bean created in @GetMapping
	 * @param model
	 * @return Connects to view.html
	 */
	@PostMapping("/update/{id}")
	public String reviseEntry(WeightEntry c, Model model) {
		repo.save(c);
		return viewAllEntries(model);
	}

	/**
	 * Deletes selected entry from database
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String deleteEntry(@PathVariable("id") long id, Model model) {
		WeightEntry c = repo.findById(id).orElse(null);
		repo.delete(c);
		return viewAllEntries(model);

	}

	/**
	 * Generates a map of database entry data points for use in Canvas.js graph
	 * visualization.
	 * 
	 * @return List of data for use in javascript
	 */
	private List<Map<String, Object>> getDataPoints() {
		// Retrieves data from databse
		ArrayList<WeightEntry> data = (ArrayList<WeightEntry>) repo.findAll();

		// Creates list of hash map points to pass to javascript application
		List<Map<String, Object>> result = new ArrayList<>(data.size());

		// Loops through list to build result list
		for (int i = 0; i < data.size(); i++) {
			// Formats date values from java.util.Date object
			LocalDateTime date = LocalDateTime.ofInstant(data.get(i).getDate().toInstant(), ZoneId.systemDefault());
			LocalDate calender = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());

			// Creates data point
			Map<String, Object> entry = new HashMap<>();
			entry.put("label", calender.format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
			entry.put("y", data.get(i).getWeight());

			// Stores data point
			result.add(entry);
		}

		return result;
	}

}
