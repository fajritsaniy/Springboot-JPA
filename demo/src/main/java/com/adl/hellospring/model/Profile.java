package com.adl.hellospring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




/* +--------------+-------------+------+-----+---------+----------------+
| Field        | Type        | Null | Key | Default | Extra          |
+--------------+-------------+------+-----+---------+----------------+
| id           | int(11)     | NO   | PRI | NULL    | auto_increment |
| full_name    | varchar(50) | YES  |     | NULL    |                |
| phone_number | varchar(20) | YES  |     | NULL    |                |
| website      | varchar(20) | YES  |     | NULL    |                |
| email        | varchar(20) | YES  |     | NULL    |                |
+--------------+-------------+------+-----+---------+----------------+ */


@Entity
@Getter     //LOMBOK HANYA UNTUK GETTER AND SETTER
@Setter
@NoArgsConstructor
public class Profile implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String full_name;
	private String phone_number;
	private String website;
	private String email;
}
