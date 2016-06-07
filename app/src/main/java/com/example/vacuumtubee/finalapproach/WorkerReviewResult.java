package com.example.vacuumtubee.finalapproach;

/**
 * Created by NIT on 4/25/2016.
 */
public class WorkerReviewResult {
    String review;
    String rating;
    String employer;
    String Job;
    String Worker;
    String avgRating;

    public WorkerReviewResult(String rating, String review, String employer, String job, String worker, String avgRating) {
        this.rating = rating;
        this.review = review;
        this.employer = employer;
        Job = job;
        Worker = worker;
        this.avgRating = avgRating;
    }

    public WorkerReviewResult(String review, String rating, String employer) {
        this.review = review;
        this.rating = rating;
        this.employer = employer;
    }

    @Override
    public String toString() {
        return "WorkerReviewResult{" +
                "review='" + review + '\'' +
                ", rating='" + rating + '\'' +
                ", employer='" + employer + '\'' +
                '}';
    }
}
