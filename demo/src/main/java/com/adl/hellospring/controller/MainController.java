package com.adl.hellospring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adl.hellospring.model.Profile;
import com.adl.hellospring.model.Resume;
import com.adl.hellospring.model.Skill;
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

	@GetMapping("/")
	public String helloWorld(Model model) {
		
		// List<Profile>listProfile = profileRepo.findAll(); untuk kondisi banyak
		Profile profile = profileRepo.findById(1).get();
		model.addAttribute("profile",profile); 
		List<Skill>listSkill = skillRepo.findAll();
		model.addAttribute("skill",listSkill);
		List<Resume> lstResume = resumeRepo.findAll();
		model.addAttribute("resume",lstResume);
		
		return "index";
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello";
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
