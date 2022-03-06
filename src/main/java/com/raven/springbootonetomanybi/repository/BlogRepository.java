package com.raven.springbootonetomanybi.repository;

import com.raven.springbootonetomanybi.entity.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
