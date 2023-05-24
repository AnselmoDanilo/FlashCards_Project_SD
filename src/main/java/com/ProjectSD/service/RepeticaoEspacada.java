/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjectSD.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RepeticaoEspacada {
    
    public static LocalDateTime somaDias(LocalDateTime dateTime, int quantidade) {
        return dateTime.plus(quantidade, ChronoUnit.DAYS);
    }

    public static LocalDateTime somaHoras(LocalDateTime dateTime, int quantidade) {
        return dateTime.plus(quantidade, ChronoUnit.HOURS);
    }
    public static LocalDateTime somaSegundos(LocalDateTime dateTime, int quantidade) {
      return dateTime.plus(quantidade, ChronoUnit.SECONDS);
    }
    public static LocalDateTime somaMinutos(LocalDateTime dateTime, int quantidade) {
        return dateTime.plus(quantidade, ChronoUnit.MINUTES);
    }

    public static LocalDateTime somaMeses(LocalDateTime dateTime, int quantidade) {
        return dateTime.plus(quantidade, ChronoUnit.MONTHS);
    }

    public static LocalDateTime somaAnos(LocalDateTime dateTime, int quantidade) {
        return dateTime.plus(quantidade, ChronoUnit.YEARS);
    }
}
