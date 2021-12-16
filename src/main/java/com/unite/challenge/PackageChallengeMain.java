package com.unite.challenge;

import com.unite.challenge.processor.FileReader;
import com.unite.challenge.service.PackageService;
import lombok.extern.slf4j.Slf4j;

/**
 * Unite package challenge main class
 */
@Slf4j
public class PackageChallengeMain {

    public static void main(String[] args) {
        if (args.length < 1) {
            log.error("Please provide path to the text file as a program argument");
        } else {
            PackageService packageService = new PackageService(new FileReader());
            packageService.processInputFile(args[0]);
        }
    }
}
