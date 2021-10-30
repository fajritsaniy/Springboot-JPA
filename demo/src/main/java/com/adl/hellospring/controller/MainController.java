package com.adl.hellospring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adl.hellospring.model.Contact;
import com.adl.hellospring.model.Profile;
import com.adl.hellospring.model.Resume;
import com.adl.hellospring.model.Skill;
import com.adl.hellospring.repository.ContactRepository;
import com.adl.hellospring.repository.ProfileRepository;
import com.adl.hellospring.repository.ResumeRepository;
import com.adl.hellospring.repository.SkillRepository;

@Controller
public class MainController {
	@Autowired
	private ProfileRepository profileRepo;
	
	@Autowired
	private SkillRepository skillRepo;
	
	@Autowired
	private ResumeRepository resumeRepo;
	
	@Autowired
	private ContactRepository cr;

	@GetMapping("/")
	public String helloWorld(@PageableDefault(size=2) Pageable pageable, Model model) {
		
		// List<Profile>listProfile = profileRepo.findAll(); untuk kondisi banyak
		Profile profile = profileRepo.findById(1).get();
		model.addAttribute("profile",profile); 
		List<Skill>listSkill = skillRepo.findAll();
		model.addAttribute("skill",listSkill);
		model.addAttribute("contact",new Contact()); 
		
		//List<Resume> lstResume = resumeRepo.findAll();
	
		/*List<Resume> lstResume = resumeRepo.findAllByTipeResume("jobs");
		model.addAttribute("resume",lstResume);*/
		//Pageable paging = PageRequest.of(0, 2);
					
		Page<Resume> pageResumeEducation = resumeRepo.findAllByTipeResume("Education",pageable);
		Page<Resume> pageResumeEmployment = resumeRepo.findAllByTipeResume("Employment",pageable);
		
		model.addAttribute("resumeEducation",pageResumeEducation);
		model.addAttribute("resumeEmployment",pageResumeEmployment);
		
		
		
		return "index";
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/belajarparameter")
	
	public String belajarParameter(Model model, @RequestParam(defaultValue = "") String nama) {
		
		System.out.println(nama);
		Profile profile = new Profile();
		profile.setFull_name("Chelsea");
		
		model.addAttribute("nama", "Nama saya adalah " + nama);
		model.addAttribute("Chelsea", profile);
		
		return "belajarparameter" ;
		
		
	}
	
	
	/*
	 * @PostMapping("/") public void saveData( @RequestParam("name") String
	 * nama, @RequestParam("email") String email) {
	 * 
	 * Contact contact = new Contact(); contact.setName(nama);
	 * contact.setEmail(email);
	 * 
	 * cr.save(contact);
	 * 
	 * }
	 */
	
	@PostMapping("/")
	public void saveData(@ModelAttribute(value = "contact") Contact contact) {
		
		cr.save(contact);
		
	}
	
	
	
	@GetMapping("/insert")
	@ResponseBody
	public void setData() {
		
		List<Resume> lstResume = new ArrayList<Resume>();
		Resume resume = new Resume();
		resume.setJudul("PT. Telkom Indonesia");
		resume.setTahun("2018 - 2019");
		resume.setKeterangan("Belajar Jaringan");
		resume.setProfile_id(1);
		resume.setTipe("Employment");
		
		lstResume.add(resume);
		
		Resume resume2 = new Resume();
		resume2.setJudul("Axiata Digital Labs");
		resume2.setTahun("2020 - 2023");
		resume2.setKeterangan("Belajar Pemrograman");
		resume2.setProfile_id(1);
		resume2.setTipe("Employment");
		lstResume.add(resume2);
		resumeRepo.saveAll(lstResume);
		
		
		
		
	}
	
}
