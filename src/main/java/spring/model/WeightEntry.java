package spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * This class is a database storage bean, holding weight values and the dates
 * measurements were taken.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-10-1
 */
@Entity
public class WeightEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private double weight;

	public WeightEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
		return "WeightEntry [id=" + id + ", date=" + date + ", weight=" + weight + "]";
	}
}
