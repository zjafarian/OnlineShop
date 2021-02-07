package com.example.onlineshop.data.network.models;

import com.google.gson.annotations.SerializedName;

public class ReviewerAvatarUrls{

	@SerializedName("24")
	private String jsonMember24;

	@SerializedName("48")
	private String jsonMember48;

	@SerializedName("96")
	private String jsonMember96;

	public ReviewerAvatarUrls() {
	}

	public ReviewerAvatarUrls(String jsonMember24, String jsonMember48, String jsonMember96) {
		this.jsonMember24 = jsonMember24;
		this.jsonMember48 = jsonMember48;
		this.jsonMember96 = jsonMember96;
	}

	public String getJsonMember24(){
		return jsonMember24;
	}

	public String getJsonMember48(){
		return jsonMember48;
	}

	public String getJsonMember96(){
		return jsonMember96;
	}
}