package com.example.edurankai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardResponse {

    private long totalStudents;
    private long activeStudents;
    private long inactiveStudents;
}