/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trainin_ftp_client.infrastructure.service.impl;

import com.mycompany.trainin_ftp_client.infrastructure.service.FTPService;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author UPN
 */
public class FTPServiceImpl implements FTPService{

    @Override
    public void start() {
        
        
        FTPClient ftpClient = new FTPClient();
        try {
 
            String server = "serverdevops.abexa.pe";
            String username = "user";
            String password = "123";
            
            ftpClient.connect(server, 21);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                
            FTPFile[] files = ftpClient.listFiles(); 
            System.out.println("___");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
       
    }

    
    
    @Override
    public List<String> compareDirectoriesBetweenLocalAndRemote(String localPath, String remotePath) {
        List<String> localFiles = FTPServiceImpl.obtenerArchivosEnDirectorios(localPath, localPath);
        List<String> remoteFiles = FTPServiceImpl.obtenerArchivosEnDirectorios(remotePath, remotePath);
            // Comparar archivos
        for (String archivo : localFiles) {
            if (remoteFiles.contains(archivo)) {
                System.out.println("El archivo " + archivo + " SI EXISTE en el lugar de destino.");
            } else {
                System.out.println("El archivo " + archivo + " no existe en el lugar de destino.");
            }
        }
        return localFiles;
    }
    
      // Función para obtener la lista de archivos en un directorio dado (incluyendo subdirectorios)
    public static List<String> obtenerArchivosEnDirectorios(String rutaDirectorio, String rutaBase) {
        List<String> archivos = new ArrayList<>();
        File directorio = new File(rutaDirectorio);

        if (directorio.exists() && directorio.isDirectory()) {
            File[] listaArchivos = directorio.listFiles();

            if (listaArchivos != null) {
                for (File archivo : listaArchivos) {
                    if (archivo.isFile()) {
                        // Obtener la ruta relativa del archivo
                        // String rutaRelativa = archivo.toPath().relativize(new File(rutaDirectorio).toPath()).toString();
                        String rutaRelativa = new File(rutaDirectorio).toPath().relativize(archivo.toPath()).toString();
                        String rootPath = rutaDirectorio.replace(rutaBase, "");
                        String finalPath = rootPath + File.separator + rutaRelativa;
                       //  Path archivoPath = Paths.get(rutaDirectorio).relativize(archivo.toPath());

                        archivos.add(finalPath);
                    } else if (archivo.isDirectory()) {
                        // Recursivamente obtener archivos en subdirectorios
                        List<String> archivosSubDirectorio = obtenerArchivosEnDirectorios(archivo.getPath(), rutaBase);
                        archivos.addAll(archivosSubDirectorio);
                    }
                }
            }
        } else {
            System.err.println("El directorio no existe o no es válido: " + rutaDirectorio);
        }

        return archivos;
    }
    
}
