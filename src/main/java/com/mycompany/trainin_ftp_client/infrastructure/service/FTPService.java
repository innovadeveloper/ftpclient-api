/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trainin_ftp_client.infrastructure.service;

import java.util.List;

/**
 *
 * @author UPN
 */
public interface FTPService {
    void start();   // conectarse por ftp al servidor ftp
    List<String> compareDirectoriesBetweenLocalAndRemote(String localPath, String remotePath);
}
