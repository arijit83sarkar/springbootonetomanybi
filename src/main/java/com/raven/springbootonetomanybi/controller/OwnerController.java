package com.raven.springbootonetomanybi.controller;

import java.util.ArrayList;
import java.util.List;

import com.raven.springbootonetomanybi.entity.Blog;
import com.raven.springbootonetomanybi.entity.Owner;
import com.raven.springbootonetomanybi.repository.OwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerController {
	@Autowired
	private OwnerRepository ownerRepository;

	@PostMapping("/saveOwner")
	public String saveOwner(@RequestBody Owner owner) {
		System.out.println("Owner save called...");

		Owner ownerOut = ownerRepository.save(owner);

		System.out.println("Saved!!!");
		return "Owner saved!!!";
	}

	@PostMapping("/saveBlog")
	public String saveBlog(@RequestParam(name = "id") String id) {
		System.out.println("Blog save called...");

		Owner ownerTemp = ownerRepository.getById(Integer.valueOf(id));

		List<Blog> blogs = new ArrayList<>();
		blogs.add(new Blog("title", "category", "content"));
		blogs.add(new Blog("title", "category", "content"));

		ownerRepository.save(ownerTemp);

		System.out.println("Saved!!!");
		return "Blog saved!!!";
	}

	@GetMapping("/getOwner/{id}")
	public String getOwner(@PathVariable(name = "id") String id) {
		System.out.println("Owner get called...");

		Owner ownerOut = ownerRepository.getById(Integer.valueOf(id));
		System.out.println("\nOwner details with Blogs :: \n" + ownerOut);
		System.out.println("\nList of Blogs :: \n" + ownerOut.getBlogList());

		System.out.println("\nDone!!!");
		return "Owner fetched...";
	}
}
