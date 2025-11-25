import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;

public class Main {

    // Interface que representa a DLL, usando JNA.
    // Aqui o programa diz: “Essas são as funções que existem dentro da DLL”.
    public interface ImpressoraDLL extends Library {

        // Carrega a DLL pelo caminho completo do arquivo .dll no seu computador.
        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "C:\\Users\\leticia_conceicao\\Downloads\\Java-Aluno EM\\Java-Aluno EM\\E1_Impressora01.dll",
                ImpressoraDLL.class
        );

        // Abaixo ficam TODAS as funções que a DLL disponibiliza.
        // Cada linha representa uma função da impressora.

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

    // Variáveis que guardam os dados da conexão
    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;

    private static final Scanner scanner = new Scanner(System.in);


    // Função auxiliar que pergunta algo ao usuário
    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    // Essa função pega informações da conexão digitadas pelo usuário
    public static void configurarConexao() {
        if (!conexaoAberta) {

            System.out.println("Digite o tipo de conexão (ex: 1 para USB, 2 para serial, etc.): ");
            tipo = scanner.nextInt();

            System.out.println("Digite o modelo: ");
            modelo = scanner.next();

            System.out.println("Digite a conexao");
            conexao = scanner.next();

            System.out.println("Digite o parametro");
            parametro = scanner.nextInt();

            System.out.println("Dados salvos com sucesso!!!");
        }
    }

    // Função que abre a conexão com a impressora usando a DLL
    public static void abrirConexao () {

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

    // Função simples para imprimir um texto fixo (exemplo)
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

    // Função de corte de papel
    public static void Corte() {
        int retorno = ImpressoraDLL.INSTANCE.Corte(4);
        if(retorno == 0){
            System.out.println("Papel cortado.");
        } else{
            System.out.println("Erro " + retorno);
        }
    }

    // Impressão simples de QR Code
    public static void ImpressaoQRCode(){
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode("teste", 6, 4);

        if(retorno == 0){
            System.out.println("Impressão OK");
        }else{
            System.out.println("Erro" + retorno);
        }
    }

    // Impressão de código de barras
    public static void ImpressaoCodigoBarra(){
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, "{A012345678912", 100, 2, 3);

        if(retorno == 0){
            System.out.println("Código de barras impresso.");
        }else{
            System.out.println("Erro" + retorno);
        }
    }

    // Função que avança o papel da impressora
    public static void AvancaPapel(){
        int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(2);

        if(retorno == 0){
            System.out.println("Papel avançado.");
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
            System.out.println("Gaveta aberta.");
        }else{
            System.out.println("Erro" + retorno);
        }
    }

    public static void SinalSonoro(){
        int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(4,5,5);

        if(retorno == 0){
            System.out.println("Beep executado.");
        }else{
            System.out.println("Erro" + retorno);
        }
    }

    // Função principal com menu
    public static void main (String[]args){

        while (true) {

            // Exibe o menu sempre que o loop roda
            System.out.println("\n*************************************************");
            System.out.println("**************** MENU IMPRESSORA ****************");
            System.out.println("*************************************************\n");

            System.out.println("1  - Configurar Conexao");
            System.out.println("2  - Abrir Conexao");
            System.out.println("3  - Impressão Texto");
            System.out.println("4  - Impressão QRcode");
            System.out.println("5  - Impressão Cod Barras");
            System.out.println("6  - Impressão XML SAT");
            System.out.println("7  - Impressão XML Canc SAT");
            System.out.println("8  - Gaveta Elgin");
            System.out.println("9  - Abrir Gaveta");
            System.out.println("10 - Sinal Sonoro");
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
                    Corte();
                    break;

                case "4":
                    ImpressaoQRCode();
                    Corte();
                    break;

                case "5":
                    ImpressaoCodigoBarra();
                    Corte();
                    break;

                case "6":
                    // Lê arquivo XML e imprime
                    if (conexaoAberta) {

                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File("."));
                        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos XML", "xml"));

                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {

                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();

                            try {
                                String conteudoXML = lerArquivoComoString(path);

                                int retImpXMLSAT = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(conteudoXML, 0);

                                ImpressoraDLL.INSTANCE.Corte(5);

                                System.out.println(retImpXMLSAT == 0 ?
                                        "Impressão de XML realizada"
                                        : "Erro ao imprimir XML SAT! Código: " + retImpXMLSAT);

                            } catch (IOException e) {
                                System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
                            }
                        }
                    }
                    break;

                case "7":
                    // Impressão XML de cancelamento
                    if (conexaoAberta) {

                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File("."));
                        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos XML", "xml"));

                        String assQRCode = "Q5DLkpdRi... (hash grande)";

                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {

                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();

                            try {

                                String conteudoXML = lerArquivoComoString(path);

                                int ret = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(conteudoXML, assQRCode, 0);

                                ImpressoraDLL.INSTANCE.Corte(5);

                                System.out.println(ret == 0 ?
                                        "Impressão XML Cancelamento OK"
                                        : "Erro ao imprimir XML de cancelamento!");

                            } catch (IOException e) {
                                System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
                            }
                        }
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

    // Função que lê conteúdo de um arquivo e transforma em texto
    private static String lerArquivoComoString (String path) throws IOException {

        FileInputStream fis = new FileInputStream(path);
        byte[] data = fis.readAllBytes();
        fis.close();

        return new String(data, StandardCharsets.UTF_8);
    }
}
