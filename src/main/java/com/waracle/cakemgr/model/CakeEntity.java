package com.waracle.cakemgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author gfeng
 */

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "CakeEntity", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
public class CakeEntity {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Getter
	@Setter
	@Column(name = "TITLE", unique = false, nullable = false, length = 100)
	private String title;

	@Getter
	@Setter
	@Column(name = "DESCRIPTION", unique = false, nullable = false, length = 100)
	private String desc;

	@Getter
	@Setter
	@Column(name = "IMAGE", unique = false, nullable = false, length = 300)
	private String image;
}