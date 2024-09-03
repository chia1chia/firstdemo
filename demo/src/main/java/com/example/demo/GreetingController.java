package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class GreetingController {
 
	private static final String template = "Hello and how are you doing, %s!";
	private final AtomicLong counter = new AtomicLong();
 
	private GreetingComponent gc;
 
	@Autowired
	public GreetingController(GreetingComponent gc) {
		this.gc = gc;
	}
 
	// @RequestParam is used to bind request parameter that you going to add to url
	// to function parameter 'name'
	// if no value passed as parameter, then it will use default value 'World'
	@GetMapping("/greeting")
//	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
 
	// test the greeting component
	@GetMapping("/testgreeting")
	public ResponseEntity<String> getMessage() {
		return ResponseEntity.ok(gc.getMessage());
	}
	
	// add methods to handle POST, PUT and DELETE request
	@PostMapping("/testgreeting")
	public ResponseEntity<String> postMessage() {
		return ResponseEntity.ok(gc.postMessage());
	}
	
	@PutMapping("/testgreeting")
	public ResponseEntity<String> putMessage() {
		return ResponseEntity.ok(gc.putMessage());
	}
	
	@DeleteMapping("/testgreeting")
	public ResponseEntity<String> deleteMessage() {
		return ResponseEntity.ok(gc.deleteMessage());
	}
}