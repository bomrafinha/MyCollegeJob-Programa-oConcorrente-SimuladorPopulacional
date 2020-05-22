package simuladorpopulacional;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Bomrafinha
 */
public class Globais
{
        // Dimensões da área do jogo.
    public static final int largura = 860;
    public static final int altura = 540;
    public static Dimension dimensao;
    
        // Velocidade do Jogo
    public static final int delayMs = 8;
    
        // Atributos
    public static List<Individuos> sapos = Collections.synchronizedList(new ArrayList<>());
    public static List<Individuos> moscas = Collections.synchronizedList(new ArrayList<>());
    public static List<Individuos> acucar = Collections.synchronizedList(new ArrayList<>());
        
    public static List<Semaphore> semaphoro_refresh_tela_draw_sapos = Collections.synchronizedList(new ArrayList<>());
    public static List<Semaphore> semaphoro_refresh_tela_move_sapos = Collections.synchronizedList(new ArrayList<>());
    public static List<Semaphore> semaphoro_refresh_tela_draw_moscas = Collections.synchronizedList(new ArrayList<>());
    public static List<Semaphore> semaphoro_refresh_tela_move_moscas = Collections.synchronizedList(new ArrayList<>());
    public static List<Semaphore> semaphoro_refresh_tela_draw_acucar = Collections.synchronizedList(new ArrayList<>());
    public static List<Semaphore> semaphoro_refresh_tela_move_acucar = Collections.synchronizedList(new ArrayList<>());
    
    public static Semaphore finalizador = new Semaphore(0);
    
    public static int iniciar;
    
    public static int qtdSapos = 0;
    public static int qtdMoscas = 0;
    public static int qtdAcucar = 0;   
    public static int qtdCalorias = 0; 
    
    public static int proxIDSapo = 0;
    public static int proxIDMosca = 0;
    public static int proxIDAcucar = 0;
}
