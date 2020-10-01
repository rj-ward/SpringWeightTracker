package spring.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
	
	@GetMapping(value = {"chart"})
    public String getData(Model model){
		model.addAttribute("dataPoints", getDataPoints());
		model.addAttribute("entries", repo.findAll());
        return "chart";
    }

	private String getDataPoints() {
		ArrayList<WeightEntry> data = (ArrayList<WeightEntry>) repo.findAll();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.size(); i++) {
			LocalDateTime date = LocalDateTime.ofInstant(data.get(i).getDate().toInstant(), ZoneId.systemDefault());
			LocalDate calender = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
			sb.append("{ label: ");
			sb.append("\"");
			sb.append(calender.format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
			sb.append("\"");
			sb.append(",  y: ");
			sb.append(data.get(i).getWeight());
			sb.append("  }");
			if (i < data.size() - 1) {
				sb.append(", ");
			}
		}
		
		return sb.toString();
	}
	
}
