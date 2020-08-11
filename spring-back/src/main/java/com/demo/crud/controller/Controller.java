package com.demo.crud.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.demo.crud.entities.Phone;
import com.demo.crud.entities.PojoAddComment;
import com.demo.crud.entities.Post;
import com.demo.crud.entities.Spec;
import com.demo.crud.entities.Brand;
import com.demo.crud.entities.Comments;
import com.demo.crud.respository.BrandRepositoryInterface;
import com.demo.crud.respository.PhoneRespositoryInterface;
import com.demo.crud.respository.PostRepositoryInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
//para evitar el problema del cors desde el servidor (ESPERO...)
@CrossOrigin(origins = "http://localhost:8081")
public class Controller {
	
	@Autowired
	private PhoneRespositoryInterface phoneRepository;	
	@Autowired
	private BrandRepositoryInterface brandRepository;
	@Autowired
	private PostRepositoryInterface postRepository;
	
	@GetMapping("/")
	public String hello(){		
		return "hello";
	}
	@GetMapping(value= "/api/index", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<List<String>> getAllBrands(){
		
		System.out.println("hi vue");
		
		List<Brand> brands = this.brandRepository.findAll();
		List<List<String>> toReturn = new ArrayList<>();
		brands.stream()
				.forEach( brand -> {
					List<String> myList = new ArrayList<String>();
					myList.add(brand.getName());
					myList.add(brand.getDescription());
					myList.add(
							((Long) (brand.getId()) ).toString()
							);
					toReturn.add(myList);
				});
		return toReturn;
	
	}
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping(value = "api/brand/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<List<String>> getAllPhones(@PathVariable(value = "id") Long brandId){
		
		List<List<String>> toReturn = new ArrayList<>();
		
		System.out.println(brandId);
		Brand brand = this.brandRepository.findById(brandId)
				.orElseThrow(() -> new NullPointerException());
		
		brand.getPhones().stream()
		.forEach(phone -> {
			List<String> myList = new ArrayList<String>();
			myList.add(phone.getName());
			myList.add(
					((Long) (phone.getId()) ).toString()
					);
			toReturn.add(myList);
		});
		
		return toReturn;
	}
	@GetMapping(value = "api/phone/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<List<String>> getPhone(@PathVariable(value = "id") Long phoneId){
		System.out.println("vue reading phone specs");
		List<List<String>> toReturn = new ArrayList<>();
		Phone phone = this.phoneRepository.findById(phoneId)
				.orElseThrow(() -> new NullPointerException());
		
		phone.getSpecs().stream().forEach( spec -> {
			List<String> myList = new ArrayList<String>();
			myList.add(spec.getName());
			myList.add(spec.getValor().toString());
			myList.add(spec.getDescription());		
			toReturn.add(myList);
		});
		
		return toReturn;		
	}
	@GetMapping(value = "api/phone/post/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<List<String>> getPhoneSpecs(@PathVariable(value = "id") Long phoneId){
		System.out.println("vue reading phone specs");
		List<List<String>> toReturn = new ArrayList<>();
		
		
		Phone phone = this.phoneRepository.findById(phoneId)
				.orElseThrow(() -> new NullPointerException());
		
		phone.getSpecs().stream().forEach( spec -> {
			List<String> myList = new ArrayList<String>();
			myList.add(spec.getName());
			myList.add(spec.getValor().toString());
			myList.add(spec.getDescription());		
			toReturn.add(myList);
		});
		
		return toReturn;		
	}
	@GetMapping(value = "api/phone/comments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<List<String>> getPhoneComments(@PathVariable(value = "id") Long phoneId){
		System.out.println("vue reading phone specs");
		List<List<String>> toReturn = new ArrayList<>();		
		System.out.println("hla???");
		Phone phone = this.phoneRepository.findById(phoneId)
				.orElseThrow(() -> new NullPointerException());
		
		phone.getPost().getComments().stream().forEach( comment -> {
			List<String> myList = new ArrayList<String>();
			myList.add(comment.getUser());
			myList.add(comment.getText());		
			toReturn.add(myList);
		});
		
		return toReturn;		
	}
	
	@GetMapping("/api/ini2")
	public String ini2() {
		String lore = "bgfbfbpassages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum recently with desktop publishing softwa";
		Brand one = new Brand("apple",lore);		
		Brand two = new Brand("samsung",lore);
		Brand three = new Brand("htc",lore);
		Brand four = new Brand("acer",lore);
		Brand five = new Brand("lenovo",lore);
		Brand seven = new Brand("huawei",lore);
		Brand eigth = new Brand("lg",lore);
		Brand nine = new Brand("motorola",lore);
		Brand ten = new Brand("blackberry",lore);
		Brand eleven = new Brand("asus",lore);
		Brand twelve = new Brand("alcatel",lore);
		
		this.brandRepository.save(one);
		this.brandRepository.save(two);
		this.brandRepository.save(three);
		this.brandRepository.save(four);
		this.brandRepository.save(five);
		
		this.brandRepository.save(seven);
		this.brandRepository.save(eigth);
		this.brandRepository.save(nine);
		this.brandRepository.save(ten);
		this.brandRepository.save(eleven);
		this.brandRepository.save(twelve);
		
		
		Brand six = new Brand("xiaomi",lore);
		
		List<Spec> specs = new ArrayList<>();
		specs.add(new Spec("almacenamiento", "64GB + microsd", 64.0));
		specs.add(new Spec("chipset", "ARM 8-core", 1.33));
		specs.add(new Spec("camera frontal", "good quality niggi, 8mp", 8.0));		
		
		List<Spec> specs1 = new ArrayList<>();
		specs1.add(new Spec("Announced"," Sep 2019",0.0));
		specs1.add(new Spec("Display", "Super AMOLED", 0.99));
		specs1.add(new Spec("Chipset", "Snapdragon 855+", 8.0));
		specs1.add(new Spec("Battery", "4000 mAh", 8.0));
		specs1.add(new Spec("RAM", "12 GB", 12.0));
		specs1.add(new Spec("Storage", "512 GB", 512.0));		
		
		List<Comments> mycomments;
		Post post;
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "User1"));
		mycomments.add(new Comments(lore, "User2"));
		mycomments.add(new Comments(lore, "User3"));
		mycomments.add(new Comments(lore, "User4"));
		mycomments.add(new Comments(lore, "User4"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		
		Phone unouno = new Phone("xiaomi-mi-9-pro.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone unodos = new Phone("xiaomi-mi-cc9-pro-.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone unotres = new Phone("xiaomi-mi-note10-.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone unocuatro = new Phone("xiaomi-mi-watch-.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone unocinco = new Phone("xiaomi-poco-x2.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone unoseis = new Phone("xiaomi-redmi-10x-5g.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone unosiete = new Phone("xiaomi-redmi-8a.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone  unoocho= new Phone("xiaomi-redmi-9.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone  unonueve= new Phone("xiaomi-redmi-9c.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone  unodiez= new Phone("xiaomi-redmi-k30-5g.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone  unodoce= new Phone("xiaomi-redmi-k30i-5g-.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone  unotrece= new Phone("xiaomi-redmi-k30-pro.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone  unocatorce= new Phone("xiaomi-redmi-note-8t.jpg",six ,specs1,post);
		
		mycomments = new ArrayList<>();
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		mycomments.add(new Comments(lore, "vue mola"));
		post = new Post(mycomments);
		this.postRepository.save(post);
		Phone  unoquince = new Phone("xiaomi-watch-color-.jpg",six ,specs1,post);
		
		this.phoneRepository.save(unouno );
		this.phoneRepository.save(unodos );
		this.phoneRepository.save(unotres );
		this.phoneRepository.save( unocuatro);
		this.phoneRepository.save(unocinco );
		this.phoneRepository.save( unoseis);
		this.phoneRepository.save(unosiete );
		this.phoneRepository.save( unoocho);
		this.phoneRepository.save(unonueve );
		this.phoneRepository.save(unodiez );
		this.phoneRepository.save( unodoce);
		this.phoneRepository.save(unotrece );
		this.phoneRepository.save(unocatorce );
		this.phoneRepository.save(unoquince );
		return "done";
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(value = "/api/img/{brand}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable(value = "brand") String brand) throws IOException {
        Resource imgFile = new ClassPathResource("static/images/"+brand+".png");

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
	@CrossOrigin(origins = "http://localhost:8081/*")
    @RequestMapping(value = "/api/img/{brand}/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImagePhones(HttpServletResponse response, @PathVariable(value = "brand") String brand, @PathVariable(value = "name") String name)
    		throws IOException {

        Resource imgFile = new ClassPathResource("static/images/"+brand+"/"+name);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
	@CrossOrigin(origins = "http://localhost:8081/*")	
    @RequestMapping(value = "/api/img/profile", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImageProfile(HttpServletResponse response)
    		throws IOException {

        Resource imgFile = new ClassPathResource("static/images/profile/"+(new Random().nextInt(7) + 1)+".jpg");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
        System.out.println("user profile");
    }
	
	// POST METHODS
	@PostMapping(path = "/api/upload/mssg", consumes = "application/json")
	public void newPost(@RequestBody Map<String, Object> post) {
		
		Phone phone = this.phoneRepository.findById(Long.parseLong((String)post.get("phoneid")))
				.orElseThrow(() -> new NullPointerException());
		phone.getPost().getComments().add(new Comments(post.get("comment").toString(), post.get("user").toString()));
		System.out.println(post.get("comment").toString());
		this.phoneRepository.save(phone);
	
	}
	
	
	
}
