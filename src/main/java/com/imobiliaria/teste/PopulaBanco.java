import com.imobiliaria.model.Cliente;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.Locacao;
import com.imobiliaria.model.TipoImovel;
import com.imobiliaria.repository.ClienteRepository;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.repository.LocacaoRepository;
import com.imobiliaria.repository.TipoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PopulaBanco {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static ClienteRepository clienteRepository = new ClienteRepository(manager);
    private static TipoImovelRepository tipoImovelRepository = new TipoImovelRepository(manager);
    private static ImovelRepository imovelRepository = new ImovelRepository(manager);
    private static LocacaoRepository locacaoRepository = new LocacaoRepository(manager);

    public static void main(String[] args) {
        try {
            System.out.println("========== Populando Banco de Dados ==========");
            popularClientes();
            popularTiposImovel();
            popularImoveis();
            popularLocacoes();
        } finally {
            manager.close();
            factory.close();
        }
    }

    private static void popularClientes() {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Cliente cliente1 = new Cliente(null, "Alice Pereira", "11111111111", "999111111", "alice@example.com", parseDate("1980-01-01"), new ArrayList<>(), new ArrayList<>());
            Cliente cliente2 = new Cliente(null, "Bruno Silva", "22222222222", "999222222", "bruno@example.com", parseDate("1985-02-02"), new ArrayList<>(), new ArrayList<>());
            Cliente cliente3 = new Cliente(null, "Carla Souza", "33333333333", "999333333", "carla@example.com", parseDate("1990-03-03"), new ArrayList<>(), new ArrayList<>());
            Cliente cliente4 = new Cliente(null, "Daniel Alves", "44444444444", "999444444", "daniel@example.com", parseDate("1995-04-04"), new ArrayList<>(), new ArrayList<>());
            Cliente cliente5 = new Cliente(null, "Evelyn Costa", "55555555555", "999555555", "evelyn@example.com", parseDate("2000-05-05"), new ArrayList<>(), new ArrayList<>());

            cliente1 = clienteRepository.salvaOuAtualiza(cliente1);
            cliente2 = clienteRepository.salvaOuAtualiza(cliente2);
            cliente3 = clienteRepository.salvaOuAtualiza(cliente3);
            cliente4 = clienteRepository.salvaOuAtualiza(cliente4);
            cliente5 = clienteRepository.salvaOuAtualiza(cliente5);

            transacao.commit();

            System.out.println("Clientes criados com sucesso.");
            imprimirCliente(cliente1);
            imprimirCliente(cliente2);
            imprimirCliente(cliente3);
            imprimirCliente(cliente4);
            imprimirCliente(cliente5);
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void popularTiposImovel() {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            TipoImovel tipo1 = new TipoImovel(null, "Chácara", new ArrayList<>());
            TipoImovel tipo2 = new TipoImovel(null, "Fazenda", new ArrayList<>());
            TipoImovel tipo3 = new TipoImovel(null, "Pousada", new ArrayList<>());

            tipo1 = tipoImovelRepository.salvaOuAtualiza(tipo1);
            tipo2 = tipoImovelRepository.salvaOuAtualiza(tipo2);
            tipo3 = tipoImovelRepository.salvaOuAtualiza(tipo3);

            transacao.commit();

            System.out.println("Tipos de Imóvel criados com sucesso.");
            imprimirTipoImovel(tipo1);
            imprimirTipoImovel(tipo2);
            imprimirTipoImovel(tipo3);
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void popularImoveis() {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Cliente cliente1 = clienteRepository.buscaPorId(Cliente.class, 1);
            Cliente cliente2 = clienteRepository.buscaPorId(Cliente.class, 2);
            Cliente cliente3 = clienteRepository.buscaPorId(Cliente.class, 3);
            Cliente cliente4 = clienteRepository.buscaPorId(Cliente.class, 4);
            Cliente cliente5 = clienteRepository.buscaPorId(Cliente.class, 5);

            TipoImovel tipo1 = tipoImovelRepository.buscaPorId(TipoImovel.class, 1);
            TipoImovel tipo2 = tipoImovelRepository.buscaPorId(TipoImovel.class, 2);
            TipoImovel tipo3 = tipoImovelRepository.buscaPorId(TipoImovel.class, 3);

            Imovel imovel1 = new Imovel(null, tipo1, cliente1, "Rua A", "Bairro A", "12345-101", 120, (byte) 3, (byte) 2, (byte) 1, (byte) 2, 2000.00, "Observação A", new ArrayList<>(), new ArrayList<>());
            Imovel imovel2 = new Imovel(null, tipo2, cliente2, "Rua B", "Bairro B", "12345-102", 150, (byte) 4, (byte) 3, (byte) 2, (byte) 3, 2500.00, "Observação B", new ArrayList<>(), new ArrayList<>());
            Imovel imovel3 = new Imovel(null, tipo3, cliente3, "Rua C", "Bairro C", "12345-103", 180, (byte) 5, (byte) 4, (byte) 3, (byte) 4, 3000.00, "Observação C", new ArrayList<>(), new ArrayList<>());
            Imovel imovel4 = new Imovel(null, tipo1, cliente4, "Rua D", "Bairro D", "12345-104", 200, (byte) 6, (byte) 5, (byte) 4, (byte) 5, 3500.00, "Observação D", new ArrayList<>(), new ArrayList<>());
            Imovel imovel5 = new Imovel(null, tipo2, cliente5, "Rua E", "Bairro E", "12345-105", 220, (byte) 7, (byte) 6, (byte) 5, (byte) 6, 4000.00, "Observação E", new ArrayList<>(), new ArrayList<>());

            imovel1 = imovelRepository.salvaOuAtualiza(imovel1);
            imovel2 = imovelRepository.salvaOuAtualiza(imovel2);
            imovel3 = imovelRepository.salvaOuAtualiza(imovel3);
            imovel4 = imovelRepository.salvaOuAtualiza(imovel4);
            imovel5 = imovelRepository.salvaOuAtualiza(imovel5);

            transacao.commit();

            System.out.println("Imóveis criados com sucesso.");
            imprimirImovel(imovel1);
            imprimirImovel(imovel2);
            imprimirImovel(imovel3);
            imprimirImovel(imovel4);
            imprimirImovel(imovel5);
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void popularLocacoes() {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Cliente cliente1 = clienteRepository.buscaPorId(Cliente.class, 1);
            Cliente cliente2 = clienteRepository.buscaPorId(Cliente.class, 2);
            Cliente cliente3 = clienteRepository.buscaPorId(Cliente.class, 3);
            Cliente cliente4 = clienteRepository.buscaPorId(Cliente.class, 4);
            Cliente cliente5 = clienteRepository.buscaPorId(Cliente.class, 5);

            Imovel imovel1 = imovelRepository.buscaPorId(Imovel.class, 1);
            Imovel imovel2 = imovelRepository.buscaPorId(Imovel.class, 2);
            Imovel imovel3 = imovelRepository.buscaPorId(Imovel.class, 3);
            Imovel imovel4 = imovelRepository.buscaPorId(Imovel.class, 4);
            Imovel imovel5 = imovelRepository.buscaPorId(Imovel.class, 5);

            Locacao locacao1 = new Locacao(null, imovel1, cliente1, 1200.00, 2.0, (byte) 5, new Date(), null, true, "Observação de teste 1", new ArrayList<>());
            Locacao locacao2 = new Locacao(null, imovel2, cliente2, 1300.00, 2.0, (byte) 5, new Date(), null, true, "Observação de teste 2", new ArrayList<>());
            Locacao locacao3 = new Locacao(null, imovel3, cliente3, 1400.00, 2.0, (byte) 5, new Date(), null, true, "Observação de teste 3", new ArrayList<>());
            Locacao locacao4 = new Locacao(null, imovel4, cliente4, 1500.00, 2.0, (byte) 5, new Date(), new Date(), false, "Observação de teste 4", new ArrayList<>());
            Locacao locacao5 = new Locacao(null, imovel5, cliente5, 1600.00, 2.0, (byte) 5, new Date(), new Date(), false, "Observação de teste 5", new ArrayList<>()); // Locação inativa

            locacao1 = locacaoRepository.salvaOuAtualiza(locacao1);
            locacao2 = locacaoRepository.salvaOuAtualiza(locacao2);
            locacao3 = locacaoRepository.salvaOuAtualiza(locacao3);
            locacao4 = locacaoRepository.salvaOuAtualiza(locacao4);
            locacao5 = locacaoRepository.salvaOuAtualiza(locacao5);

            transacao.commit();

            System.out.println("Locações criadas com sucesso.");
            imprimirLocacao(locacao1);
            imprimirLocacao(locacao2);
            imprimirLocacao(locacao3);
            imprimirLocacao(locacao4);
            imprimirLocacao(locacao5);
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void imprimirCliente(Cliente cliente) {
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Data de Nascimento: " + new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDtNascimento()));
        System.out.println("=====================================");
    }

    private static void imprimirTipoImovel(TipoImovel tipoImovel) {
        System.out.println("ID: " + tipoImovel.getId());
        System.out.println("Descrição: " + tipoImovel.getDescricao());
        System.out.println("=====================================");
    }

    private static void imprimirImovel(Imovel imovel) {
        System.out.println("ID: " + imovel.getId());
        System.out.println("Tipo de Imóvel: " + imovel.getTipoImovel().getDescricao());
        System.out.println("Proprietário: " + imovel.getProprietario().getNome());
        System.out.println("Logradouro: " + imovel.getLogradouro());
        System.out.println("Bairro: " + imovel.getBairro());
        System.out.println("CEP: " + imovel.getCep());
        System.out.println("Metragem: " + imovel.getMetragem());
        System.out.println("Dormitórios: " + imovel.getDormitorios());
        System.out.println("Banheiros: " + imovel.getBanheiros());
        System.out.println("Suítes: " + imovel.getSuites());
        System.out.println("Vagas de Garagem: " + imovel.getVagasGaragem());
        System.out.println("Valor do Aluguel Sugerido: " + imovel.getValorAluguelSugerido());
        System.out.println("Observações: " + imovel.getObs());
        System.out.println("=====================================");
    }

    private static void imprimirLocacao(Locacao locacao) {
        System.out.println("ID: " + locacao.getId());
        System.out.println("Imóvel: " + locacao.getImovel().getLogradouro());
        System.out.println("Inquilino: " + locacao.getInquilino().getNome());
        System.out.println("Valor Aluguel: " + locacao.getValorAluguel());
        System.out.println("Percentual Multa: " + locacao.getPercentualMulta());
        System.out.println("Dia Vencimento: " + locacao.getDiaVencimento());
        System.out.println("Data Início: " + locacao.getDataInicio());
        System.out.println("Data Fim: " + locacao.getDataFim());
        System.out.println("Ativo: " + locacao.getAtivo());
        System.out.println("Observações: " + locacao.getObs());
        System.out.println("Quantidade de Aluguéis: " + (locacao.getAlugueis() != null ? locacao.getAlugueis().size() : 0));
        System.out.println("=====================================");
    }

    private static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
