package com.raven.springbootonetomanybi.controller;

import java.util.ArrayList;
import java.util.List;

import com.raven.springbootonetomanybi.entity.Blog;
import com.raven.springbootonetomanybi.entity.Owner;
import com.raven.springbootonetomanybi.repository.BlogRepository;
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

	@Autowired
	private BlogRepository blogRepository;

	@PostMapping("/saveOwner")
	public String saveOwner(@RequestBody Owner owner) {
		System.out.println("Owner save called...");

		Owner ownerIn = new Owner(owner.getName(), owner.getEmail());

		List<Blog> blogs = new ArrayList<>();
		for (Blog blogIn : owner.getBlogList()) {
			Blog blog = new Blog(blogIn.getTitle(), blogIn.getCategory(), blogIn.getContent());
			blog.setOwner(ownerIn); // set owner to blog
			blogs.add(blog);
		}
		ownerIn.setBlogList(blogs);

		Owner ownerOut = ownerRepository.save(ownerIn);
		System.out.println("Owner out :: " + ownerOut);

		System.out.println("Saved!!!");
		return "Owner saved!!!";
	}

	@PostMapping("/saveBlog")
	public String saveBlog(@RequestParam(name = "id") String id) {
		System.out.println("Blog save called...");

		Owner ownerTemp = ownerRepository.getById(Integer.valueOf(id));

		List<Blog> blogs = new ArrayList<>();

		Blog blog = new Blog("Build application server using NodeJs", "nodeJs",
				"We will build REStful api using nodeJs.");
		blog.setOwner(ownerTemp); // set owner to blog
		blogs.add(blog);

		blog = new Blog("Single Page Application using Angular", "Angular",
				"We can build robust application using Angular framework.");
		blog.setOwner(ownerTemp); // set owner to blog
		blogs.add(blog);

		ownerTemp.setBlogList(blogs);

		ownerRepository.save(ownerTemp);

		System.out.println("Saved!!!");
		return "Blog saved!!!";
	}

	@GetMapping("/getOwner/{id}")
	public String getOwner(@PathVariable(name = "id") String id) {
		System.out.println("Owner get called...");

		Owner ownerOut = ownerRepository.getById(Integer.valueOf(id));
		System.out.println("\nOwner details :: \n" + ownerOut);
		System.out.println("\nList of Blogs :: \n" + ownerOut.getBlogList());

		System.out.println("\nDone!!!");
		return "Owner fetched...";
	}

	@GetMapping("/getBlog/{id}")
	public String getBlog(@PathVariable(name = "id") String id) {
		System.out.println("Blog get called...");

		Blog blogOut = blogRepository.getById(Integer.valueOf(id));
		System.out.println("\nBlog details :: \n" + blogOut);
		System.out.println("\nOwner details :: \n" + blogOut.getOwner());

		System.out.println("\nDone!!!");
		return "Blog fetched...";
	}
}
