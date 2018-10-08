package com.cinema.booking.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cinema.booking.model.Movie;
import com.cinema.booking.service.MovieService;


@Controller()
public class AdminController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/admin")
	public String getAdminPage(@ModelAttribute("movieModel") Movie movie, Model model) {
		List<Movie> movieList = movieService.getAllMovies();
		model.addAttribute("movie", new Movie());
		model.addAttribute("movieList", movieList);
		return "admin";
	}
	
	@ResponseBody
    @RequestMapping(value = "/admin/loadSomethingTable")
    public List<Movie> loadSomethingTable() {
		System.out.println("listMovie2");
		System.out.println(movieService.getAllMovies());
        return movieService.getAllMovies();
    }

	
	@PostMapping("/admin/addMovie")
	public String addMovie(@ModelAttribute("movieModel") Movie movie, Model model) {
		System.out.println("addMovie:start");
		movieService.save(movie);
		System.out.println(movie);
		List<Movie> movieList = movieService.getAllMovies();
		model.addAttribute("movie", new Movie());
		model.addAttribute("movieList", movieList);
		System.out.println("addMovie:end");
		return "admin";
	}

	@Transactional
	@GetMapping("/admin/deleteMovie/{id}")
	public String deleteMovie(@ModelAttribute("movieModel") Movie movie, @PathVariable("id") Long id, Model model) {
		System.out.println("deleteMovie");
		movieService.delete(id);
		List<Movie> movieList = movieService.getAllMovies();
		model.addAttribute("movieList", movieList);
		System.out.println("deleteMovie");
		return "redirect:/admin/listMovie";
	}

	@RequestMapping("/admin/editMovie/{id}")
	public String editMovie(@ModelAttribute("movieModel") Movie movie, @PathVariable("id") Long id, Model model) {
		System.out.println("editMovie");
		movieService.save(movie);
		System.out.println(movie);
		List<Movie> movieList = movieService.getAllMovies();
		model.addAttribute("movieList", movieList);
		System.out.println("editMovie");
		return "admin/movie";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public void updateMovie(@ModelAttribute("movie") Movie movie) {
		System.out.println(movie);
		System.out.println("updateMovie");
		movieService.save(movie);
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
	public void deleteMovie(@ModelAttribute("movie") Movie movie) {
		System.out.println(movie);
		System.out.println("deleteMovie");
		movieService.delete(movie.getId());
	}
	
	@RequestMapping(value = "/admin/movie/delete/{id}")
	public String deleteEntity(@PathVariable("id") Long id,Model model) {
		System.out.println("delete Movie called");
		movieService.delete(id);
		List<Movie> movieList = movieService.getAllMovies();
		model.addAttribute("movieList", movieList);
		return "admin/movie";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/loadEntity/{id}", method = RequestMethod.GET)
	public Movie loadEntity(@PathVariable("id") Long id) {
		System.out.println("loadEntity called");
		return movieService.findById(id);
	}
}
