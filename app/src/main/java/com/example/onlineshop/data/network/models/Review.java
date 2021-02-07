package com.example.onlineshop.data.network.models;

import com.google.gson.annotations.SerializedName;

public class Review{

	@SerializedName("reviewer_avatar_urls")
	private ReviewerAvatarUrls reviewerAvatarUrls;

	@SerializedName("review")
	private String review;

	@SerializedName("product_id")
	private int productId;

	@SerializedName("rating")
	private int rating;



	@SerializedName("id")
	private int id;

	@SerializedName("reviewer")
	private String reviewer;

	@SerializedName("reviewer_email")
	private String reviewerEmail;

	@SerializedName("status")
	private String status;

	public Review() {
	}

	public Review(ReviewerAvatarUrls reviewerAvatarUrls, String review, int productId, int rating, String reviewer, String reviewerEmail, String status) {
		this.reviewerAvatarUrls = reviewerAvatarUrls;
		this.review = review;
		this.productId = productId;
		this.rating = rating;
		this.reviewer = reviewer;
		this.reviewerEmail = reviewerEmail;
		this.status = status;
	}

	public Review(int productId, String review, String reviewer, String reviewerEmail, int rating) {
		this.review = review;
		this.productId = productId;
		this.rating = rating;
		this.reviewer = reviewer;
		this.reviewerEmail = reviewerEmail;
	}


	public void setReviewerAvatarUrls(ReviewerAvatarUrls reviewerAvatarUrls) {
		this.reviewerAvatarUrls = reviewerAvatarUrls;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ReviewerAvatarUrls getReviewerAvatarUrls(){
		return reviewerAvatarUrls;
	}

	public String getReview(){
		return review;
	}

	public int getProductId(){
		return productId;
	}

	public int getRating(){
		return rating;
	}

	public int getId(){
		return id;
	}

	public String getReviewer(){
		return reviewer;
	}

	public String getReviewerEmail(){
		return reviewerEmail;
	}

	public String getStatus(){
		return status;
	}
}