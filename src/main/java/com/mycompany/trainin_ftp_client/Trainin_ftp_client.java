/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.trainin_ftp_client;

import com.mycompany.trainin_ftp_client.infrastructure.service.FTPService;
import com.mycompany.trainin_ftp_client.infrastructure.service.impl.FTPServiceImpl;
import java.io.File;

/**
 * 
 * @author UPN
 */
public class Trainin_ftp_client {

    private static FTPService fTPService = new FTPServiceImpl();
    public static void main(String[] args) {
        // fTPService.start();
        String localPath = ("D:" + File.separator + "DATA_LOCAL" + File.separator + "BACKUPS");
        fTPService.compareDirectoriesBetweenLocalAndRemote("D:\\DATA_LOCAL\\BACKUPS", "D:\\DATA_REMOTO\\BACKUPS");
         
    }
}
