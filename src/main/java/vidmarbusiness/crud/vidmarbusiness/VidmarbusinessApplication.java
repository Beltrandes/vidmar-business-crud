package vidmarbusiness.crud.vidmarbusiness;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vidmarbusiness.crud.vidmarbusiness.models.*;
import vidmarbusiness.crud.vidmarbusiness.repositorys.ClienteRepository;
import vidmarbusiness.crud.vidmarbusiness.repositorys.ItemOrcamentoRepository;
import vidmarbusiness.crud.vidmarbusiness.repositorys.ItemRepository;
import vidmarbusiness.crud.vidmarbusiness.repositorys.OrcamentoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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
	CommandLineRunner initClientesObraOrcaDatabase(ClienteRepository clienteRepository) {
		return args -> {
			clienteRepository.deleteAll();

			Cliente a = new Cliente();
			a.setName("Lilia");
			a.setAddress("Rua Cincinato Braga, 547");

			Work obra1 = new Work();
			obra1.setName("Cobertura Ibirapuera");
			obra1.setAddress("Rua Estado de Israel, 320");
			obra1.setServiceType("Vidraçaria");
			obra1.setInitialDate(LocalDate.of(2022, 3, 7));
			obra1.setFinishDate(LocalDate.of(2023, 1, 30));
			obra1.setCliente(a);

			Work obra2 = new Work();
			obra2.setName("Casa Perdizes");
			obra2.setServiceType("Marmoraria");
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
			obra3.setServiceType("Ambos");
			obra3.setCliente(b);
			obra3.setInitialDate(LocalDate.of(2022, 10, 20));
			obra3.setFinishDate(LocalDate.of(2023, 5, 6));

			b.setWorks(List.of(obra3));

			b.setNumber("954853126");

			clienteRepository.save(a);
			clienteRepository.save(b);

		};
	}

	@Bean
	CommandLineRunner initOrcamentosDatabase(OrcamentoRepository orcamentoRepository, ClienteRepository clienteRepository, ItemOrcamentoRepository itemOrcamentoRepository) {
		return args -> {
			orcamentoRepository.deleteAll();
			List<Cliente> clientes = clienteRepository.findAll();
			itemOrcamentoRepository.deleteAll();

			Cliente cliente1 = clientes.get(0);
			Cliente cliente2 = clientes.get(1);

			Orcamento a = new Orcamento();
			a.setCliente(cliente1);
			a.setTotal(8730.00);
			a.setData(LocalDate.now());
			a.setDescricao("Cozinha e área gourmet em Quartzo Branco");
			a.setContato(cliente1.getNumber());
			a.setStatus("Não Fechado");
			a.setItens(new ArrayList<ItemOrcamento>());

			Orcamento b = new Orcamento();
			b.setCliente(cliente2);
			b.setTotal(2350.00);
			b.setData(LocalDate.now());
			b.setDescricao("Lavatório em Quartzo Preto Estelar");
			b.setContato(cliente2.getNumber());
			b.setStatus("Fechado");
			b.setItens(new ArrayList<ItemOrcamento>());


			orcamentoRepository.save(a);
			orcamentoRepository.save(b);

			ItemOrcamento item = new ItemOrcamento();
			item.setOrcamento(a);
			item.setDetalhes("Frontões 10cm e Saia de 4cm");
			item.setNome("Bancada");
			item.setDimensoes("2070x720");
			item.setMaterial("Quartzo Cinza");
			item.setM2(1.49);
			item.setM2Valor(2200.0);
			item.setQuantidade(1.0);
			if(item.getQuantidade() != null) {
				item.setM2Total(item.getM2() * item.getQuantidade());
				item.setValorItem(item.getM2() * item.getM2Valor());
				item.setValorTotal(item.getM2Total() * item.getM2Valor());
			} else {
				item.setM2Total(0.0);
				item.setValorItem(0.0);
				item.setValorTotal(0.0);

			}


			ItemOrcamento item2 = new ItemOrcamento();
			item2.setOrcamento(a);
			item2.setDetalhes("Pontas bi-polidas com friso.");
			item2.setNome("Soleira");
			item2.setDimensoes("1240x180");
			item2.setMaterial("Preto São Gabriel");
			item2.setM2(0.23);
			item2.setM2Valor(850.0);
			item2.setQuantidade(10.0);
			if(item2.getQuantidade() != null) {
				item2.setM2Total(item2.getM2() * item2.getQuantidade());
				item2.setValorItem(item2.getM2() * item2.getM2Valor());
				item2.setValorTotal(item2.getM2Total() * item2.getM2Valor());
			} else {
				item2.setM2Total(0.0);
				item2.setValorItem(0.0);
				item2.setValorTotal(0.0);

			}


			ItemOrcamento item3 = new ItemOrcamento();
			item3.setOrcamento(b);
			item3.setDetalhes("Material escovado");
			item3.setNome("Bancada");
			item3.setDimensoes("1230x520");
			item3.setMaterial("Branco Itaúnas");
			item3.setM2(0.67);
			item3.setM2Valor(950.0);
			item3.setQuantidade(3.0);
			if(item2.getQuantidade() != null) {
				item3.setM2Total(item3.getM2() * item3.getQuantidade());
				item3.setValorItem(item3.getM2() * item3.getM2Valor());
				item3.setValorTotal(item3.getM2Total() * item3.getM2Valor());
			} else {
				item3.setM2Total(0.0);
				item3.setValorItem(0.0);
				item3.setValorTotal(0.0);

			}





			itemOrcamentoRepository.save(item);
			itemOrcamentoRepository.save(item2);
			itemOrcamentoRepository.save(item3);

			a.getItens().add(item);
			a.getItens().add(item2);
			b.getItens().add(item3);

			orcamentoRepository.save(a);
			orcamentoRepository.save(b);
		};
	}



}
