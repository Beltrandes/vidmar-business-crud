package vidmarbusiness.crud.vidmarbusiness;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vidmarbusiness.crud.vidmarbusiness.models.Item;
import vidmarbusiness.crud.vidmarbusiness.repositorys.ItemRepository;

@SpringBootApplication
public class VidmarbusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidmarbusinessApplication.class, args);
	}

	@Bean
	CommandLineRunner initItensDatabase(ItemRepository itemRepository) {
		return args -> {
			itemRepository.deleteAll();
			Item a = new Item();
			a.setAmount(23);
			a.setName("PU40 (Branco)");
			a.setType("Marmoraria");
			a.setStatus("Em estoque");

			Item b = new Item();
			b.setAmount(0);
			b.setName("Disco de maquita (Porcelanato)");
			b.setType("Marmoraria");
			b.setStatus("Em falta");

			Item c = new Item();
			c.setAmount(4);
			c.setName("Silicone Acétio (Incolor)");
			c.setType("Vidraçaria");
			c.setStatus("Em estoque");

			itemRepository.save(a);
			itemRepository.save(b);
			itemRepository.save(c);
		};
	}

}
