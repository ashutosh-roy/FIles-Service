package com.algomox.filesservice.models;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("license_data")
public class LicensedData {
	
	
	@PrimaryKey
	String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Object> getIdentifiers() {
		return identifiers;
	}
	public void setIdentifiers(List<Object> identifiers) {
		this.identifiers = identifiers;
	}
	public List<Object> getLinks() {
		return links;
	}
	public void setLinks(List<Object> links) {
		this.links = links;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Object> getOther_names() {
		return other_names;
	}
	public void setOther_names(List<Object> other_names) {
		this.other_names = other_names;
	}
	public String getSuperseded_by() {
		return superseded_by;
	}
	public void setSuperseded_by(String superseded_by) {
		this.superseded_by = superseded_by;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public List<Object> getText() {
		return text;
	}
	public void setText(List<Object> text) {
		this.text = text;
	}
	List<Object> identifiers;
	List<Object> links;
	String name;
	List<Object> other_names;
	String superseded_by;
	List<String> keywords;
	List<Object> text;
	
	}
