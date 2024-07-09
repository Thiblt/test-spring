package fr.diginamic.hello.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.diginamic.hello.service.CsvService;
import fr.diginamic.hello.service.VilleService;

@SpringBootApplication
public class TraitementFichiersApplication implements CommandLineRunner {
	@Autowired
	private VilleService villeService;
	
	

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TraitementFichiersApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
        // Load data from CSV files
        List<String> villeLignes = CsvService.readFile("C:/Data/Info/CDA/ToutLesCours/29SpringBoot/recensement.csv");
       
        // Process and save data on DB
        villeService.saveVillefromCsv(villeLignes);
	}
}
