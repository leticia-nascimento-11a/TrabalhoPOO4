import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;

public class Main {

    // Interface que representa a DLL, usando JNA
    public interface ImpressoraDLL extends Library {

        // Caminho completo para a DLL
        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "C:\\Users\\leticia_conceicao\\Downloads\\Java-Aluno EM\\Java-Aluno EM\\E1_Impressora01.dll",
                ImpressoraDLL.class
        );

        int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param);

        int FechaConexaoImpressora();

        int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho);

        int Corte(int avanco);

        int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);

        int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);

        int AvancaPapel(int linhas);

        int StatusImpressora(int param);

        int AbreGavetaElgin();

        int AbreGaveta(int pino, int ti, int tf);

        int SinalSonoro(int qtd, int tempoInicio, int tempoFim);

        int ModoPagina();

        int LimpaBufferModoPagina();

        int ImprimeModoPagina();

        int ModoPadrao();

        int PosicaoImpressaoHorizontal(int posicao);

        int PosicaoImpressaoVertical(int posicao);

        int ImprimeXMLSAT(String dados, int param);

        int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);
    }

    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;
    private static final Scanner scanner = new Scanner(System.in);


    private static boolean impressaoTexto = false;
    private static String dados;
    private static int posicao;
    private static int stilo;
    private static int tamanho;

    private static boolean Corte = false;
    private static int avanco;

    private static boolean ImpressaoQRCode = false;
    private static String qrdados;
    private static int qrtamanho;
    private static int nivelCorrecao;

    private static boolean ImpressaoCodigoBarra = false;
    private static int cbtipo;
    private static String cbdados;
    private static int altura;
    private static int largura;
    private static int HRI;

    private static boolean AvancaPapel = false;
    private static int linhas;

    private static boolean AbreGavetaElgin = false;

    private static boolean AbreGaveta = false;
    private static int pino;
    private static int ti;
    private static int tf;

    private static boolean SinalSonoro = false;
    private static int qtd;
    private static int tempoInicio;
    private static int tempoFim;

    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static void configurarConexao() {
        if (!conexaoAberta) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o tipo de conexão (ex: 1 para USB, 2 para serial, etc.): ");
            tipo = scanner.nextInt();

            System.out.println("Digite o modelo: ");
            modelo = scanner.next();
            //scanner.next();

            System.out.println("Digite a conexao");
            conexao = scanner.next();

            System.out.println("Digite o parametro");
            parametro = scanner.nextInt();

            System.out.println("Dados salvos com sucesso!!!");

        }
    }

    public static void abrirConexao () {

        //sempre que for chamar uma funçao da biblioteca, usar como abaixo (ImpressoraDLL.INSTANCE.nomeDaFuncao)

        if (!conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir conexão. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Conexão já está aberta.");
        }
    }


    public static int fecharConexao () {
        return 0;
    }

    public static void ImpressaoTexto () {
        if(conexaoAberta){
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto("TESTE", 1, 4, 0);
            if(retorno == 0){

                System.out.println("Impressão ok");

            }else{
                System.out.println("Erro " + retorno);
            }
        }
    }

    public static void Corte() {
        if(Corte){
            int retorno = ImpressoraDLL.INSTANCE.Corte(avanco);
            if(retorno == 0){

                System.out.println("");

            } else{
                System.out.println("Erro" + retorno);
            }
        }
    }

    public static void ImpressaoQRCode(){
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode("teste", 6, 4);
        if(retorno == 0){
            System.out.println("Impressão OK");
        }else{
            System.out.println("Erro" + retorno);
        }
    }

    public static void ImpressaoCodigoBarra(){
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, "{A012345678912", 100, 2, 3);
        if(retorno == 0){
            System.out.println("0");
        }else{
            System.out.println("Erro" + retorno);
        }
    }

    public static void AvancaPapel(){
        int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(2);
        if(retorno == 0){
            System.out.println("2");
        }
    }

    public static void AbreGavetaElgin(){
        int retorno = ImpressoraDLL.INSTANCE.AbreGavetaElgin();
        if(retorno == 0){
            System.out.println("Gaveta Aberta");
        }else{
            System.out.println("Erro" + retorno);
        }
    }

    public static void AbreGaveta(){
        int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(1, 5, 10);
        if(retorno == 0){
            System.out.println();
        }else{
            System.out.println("Erro" + retorno);
        }
    }

    public static void SinalSonoro(){
        int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(4,5,5);
        if(retorno == 0){
            System.out.println("4, 5, 5");
        }else{
            System.out.println("Erro" + retorno);
        }
    }


    //criar o restante das funçoes aqui!

	/* - `ImpressaoTexto()`          ("Teste de impressao", 1, 4, 0);
	- `Corte()`						(2)  usar sempre após a impressao de algum documento
	- `ImpressaoQRCode()`            ("Teste de impressao", 6, 4)
	- `ImpressaoCodigoBarras()`    (8, "{A012345678912", 100, 2, 3)
	- `AvancaPapel()`                 (2)  usar sempre após a impressao de algum documento
	- `AbreGavetaElgin()`            (1, 50, 50)
	- `AbreGaveta()`                  (1, 5, 10)
	- `SinalSonoro()`				 (4,5,5)
	- `ImprimeXMLSAT()`
	- `ImprimeXMLCancelamentoSAT()`    (assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";)
	*/


    public static void main (String[]args){
        while (true) {
            System.out.println("\n*************************************************");
            System.out.println("**************** MENU IMPRESSORA *******************");
            System.out.println("*************************************************\n");

            System.out.println("1  - Configurar Conexao");
            System.out.println("2  - Abrir Conexao");
            System.out.println("3  - Impressão Texto");
            System.out.println("4  - Impressão QRcode");
            System.out.println("5  - Impressão Cod Barras");
            System.out.println("6  - Impressão XML SAT");
            System.out.println("7  - Impressao XML Canc SAT");
            System.out.println("8  -  Gaveta Elgin");
            System.out.println("9  - Abrir Gaveta");
            System.out.println("10  - Sinal Sonoro");
            System.out.println("0  - Fechar Conexão e Sair");


            String escolha = capturarEntrada("\nDigite a opção desejada: ");

            if (escolha.equals("0")) {
                fecharConexao();
                System.out.println("Programa encerrado.");
                break;
            }

            switch (escolha) {
                case "1":
                    configurarConexao();
                    break;
                case "2":
                    abrirConexao();
                    break;
                case "3":
                    ImpressoraDLL.INSTANCE.LimpaBufferModoPagina();
                    ImpressaoTexto();
                    ImpressoraDLL.INSTANCE.Corte(4);
                    break;

                case "4":
                    ImpressaoQRCode();
                    ImpressoraDLL.INSTANCE.Corte(4);
                    break;
                case "5":
                    ImpressaoCodigoBarra();
                    ImpressoraDLL.INSTANCE.Corte(4);
                    break;

                case "6":
                    if (conexaoAberta) {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File(".")); // Diretório atual do programa
                        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos XML", "xml"));

                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();

                            try {
                                String conteudoXML = lerArquivoComoString(path);
                                int retImpXMLSAT = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(conteudoXML, 0);
                                ImpressoraDLL.INSTANCE.Corte(5);
                                System.out.println(retImpXMLSAT == 0 ? "Impressão de XML realizada" : "Erro ao realizar a impressão do XML SAT! Retorno: " + retImpXMLSAT);
                            } catch (IOException e) {
                                System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Nenhum arquivo selecionado.");
                        }
                    } else {
                        System.out.println("Erro: Conexão não está aberta.");
                    }
                    break;

                case "7":
                    if (conexaoAberta) {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File(".")); // Diretório atual do programa
                        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos XML", "xml"));
                        String assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";

                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();

                            try {
                                String conteudoXML = lerArquivoComoString(path);
                                int retImpCanXMLSAT = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(conteudoXML, assQRCode, 0);
                                ImpressoraDLL.INSTANCE.Corte(5);
                                System.out.println(retImpCanXMLSAT == 0 ? "Impressão de XML de Cancelamento realizada" : "Erro ao realizar a impressão do XML de Cancelamento SAT! Retorno: " + retImpCanXMLSAT);
                            } catch (IOException e) {
                                System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Nenhum arquivo selecionado.");
                        }
                    } else {
                        System.out.println("Erro: Conexão não está aberta.");
                    }
                    break;


                case "8":

                    AbreGavetaElgin();
                    break;
                case "9":

                    AbreGaveta();
                    break;
                case "10":
                    SinalSonoro();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }

        scanner.close();
    }

    private static String lerArquivoComoString (String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] data = fis.readAllBytes();
        fis.close();
        return new String(data, StandardCharsets.UTF_8);
    }
}