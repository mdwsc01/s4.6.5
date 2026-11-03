package com.example.session06.model.dto;

import com.example.session06.model.entity.Patient;

import java.util.List;

public class PaginationResponse {
    public List<Patient> data;
    private int totalPage;
    private long totalElement;
    private int currentPage;

    public List<Patient> getData(List<Patient> content) {
        return data;
    }

    public void setData(List<Patient> data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public PaginationResponse(List<Patient> data, int totalPage, long totalElement, int currentPage) {
        this.data = data;
        this.totalPage = totalPage;
        this.totalElement = totalElement;
        this.currentPage = currentPage;
    }

    public PaginationResponse() {
    }
}
