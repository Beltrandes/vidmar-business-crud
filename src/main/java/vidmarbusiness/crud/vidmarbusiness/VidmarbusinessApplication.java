package vidmarbusiness.crud.vidmarbusiness;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vidmarbusiness.crud.vidmarbusiness.models.Cliente;
import vidmarbusiness.crud.vidmarbusiness.models.Item;
import vidmarbusiness.crud.vidmarbusiness.models.Work;
import vidmarbusiness.crud.vidmarbusiness.repositorys.ClienteRepository;
import vidmarbusiness.crud.vidmarbusiness.repositorys.ItemRepository;
import vidmarbusiness.crud.vidmarbusiness.repositorys.WorkRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

			Item b = new Item();
			b.setAmount(0);
			b.setName("Disco de maquita (Porcelanato)");
			b.setType("Marmoraria");

			Item c = new Item();
			c.setAmount(4);
			c.setName("Silicone Acético (Incolor)");
			c.setType("Vidraçaria");

			itemRepository.save(a);
			itemRepository.save(b);
			itemRepository.save(c);
		};
	}
	@Bean
	CommandLineRunner initClientesObraDatabase(ClienteRepository clienteRepository, WorkRepository workRepository) {
		return args -> {
			clienteRepository.deleteAll();
			workRepository.deleteAll();

			Cliente a = new Cliente();
			a.setName("Lilia");
			a.setAddress("Rua Cincinato Braga, 547");

			Work obra1 = new Work();
			obra1.setName("Cobertura Ibirapuera");
			obra1.setAddress("Rua Estado de Israel, 320");
			obra1.setInitialDate(LocalDate.of(2022, 3, 7));
			obra1.setFinishDate(LocalDate.of(2023, 1, 30));
			obra1.setCliente(a);

			Work obra2 = new Work();
			obra2.setName("Casa Perdizes");
			obra2.setAddress("Rua dos Perdizes, 212");
			obra2.setInitialDate(LocalDate.of(2022, 5, 13));
			obra2.setFinishDate(LocalDate.of(2022, 6, 25));
			obra2.setCliente(a);

			a.setWorks(Arrays.asList(obra1, obra2));

			a.setNumber("984652176");

			Cliente b = new Cliente();
			b.setName("André");
			b.setAddress("Rua Vendaval Arcano, 1100");

			Work obra3 = new Work();

			obra3.setName("Cobertura Clube Esperia");
			obra3.setAddress("Av. Marginal Tietê, 12345");
			obra3.setCliente(b);
			obra3.setInitialDate(LocalDate.of(2022, 10, 20));
			obra3.setFinishDate(LocalDate.of(2023, 5, 6));

			b.setWorks(List.of(obra3));

			b.setNumber("954853126");

			clienteRepository.save(a);
			clienteRepository.save(b);

			workRepository.save(obra1);
			workRepository.save(obra2);
			workRepository.save(obra3);

		};
	}


}
