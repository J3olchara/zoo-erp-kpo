package com.application.domain.service;

import org.springframework.stereotype.Service;

import com.application.domain.animals.Animal;

@Service
public class VetClinic {
    public boolean checkHealth(Animal animal) {
        // Логика проверки здоровья
        return true;
    }
}