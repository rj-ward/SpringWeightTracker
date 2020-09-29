package spring.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class WeightEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Autowired
	private User username;
	private LocalDate date;
	private double weight;
	
	
	public WeightEntry() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public WeightEntry(User username, LocalDate date, double weight) {
		super();
		this.username = username;
		this.date = date;
		this.weight = weight;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUsername() {
		return username;
	}
	public void setUsername(User username) {
		this.username = username;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}


	@Override
	public String toString() {
		return "WeightEntry [id=" + id + ", username=" + username + ", date=" + date + ", weight=" + weight + "]";
	}
	
	
}
