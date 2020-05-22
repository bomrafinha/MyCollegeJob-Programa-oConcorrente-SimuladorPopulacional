package simuladorpopulacional;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ConcurrentModificationException;

/**
 *
 * @author Bomrafinha
 */
public class RedesenhaTela extends Thread
{
    private List<Individuos> individuos = Collections.synchronizedList(new ArrayList<>());
    private final Graphics g;
    private final String tipo;
    
    public RedesenhaTela(Graphics g, String tipo)
    {
        this.g = g;
        this.tipo = tipo;        
    }

    public RedesenhaTela(List<Individuos> individuos, Graphics g, String tipo) throws ConcurrentModificationException
    {
        synchronized(this.individuos)
        {
            this.individuos = individuos;
            this.g = g;
            this.tipo = tipo;
        }
    }
    
    private void redesenhaAcucar() throws InterruptedException
    {
        synchronized(Globais.acucar)
        {
            for(Individuos i : Globais.acucar)
            {    
                Globais.semaphoro_refresh_tela_draw_acucar.get(i.getID()).acquire();
                if (i.getAtivo())
                {
                    i.draw(this.g);
                }
                Globais.semaphoro_refresh_tela_move_acucar.get(i.getID()).release();
            }
        }        
    }
    
    private void redesenhaMosca() throws InterruptedException
    {
        synchronized(Globais.moscas)
        {
            for(Individuos i : Globais.moscas)
            {    
                if (i.isNasceu())
                {                    
                    Globais.semaphoro_refresh_tela_draw_moscas.get(i.getID()).acquire();
                    if (i.getAtivo())
                    {   
                        i.draw(this.g);
                        synchronized(Globais.acucar)
                        {
                            for (int j = 0; j <= Globais.acucar.size() - 1; j++)
                            {
                                try
                                {
                                    if(Globais.acucar.get(j).getAtivo())
                                    {
                                        if (verificaCoordenadas(i.getX(), i.getY(), Globais.acucar.get(j).getX(), Globais.acucar.get(j).getY()))
                                        { 
                                            Globais.acucar.get(j).setAtivo(false);
                                            Globais.moscas.get(i.getID()).setComer(Globais.acucar.get(j).getCalorias());
                                            break;
                                        }
                                    }
                                } catch(Exception e) {
                                    break;                                                    
                                }                                                
                            }                                    
                        }         
                    }
                }
                Globais.semaphoro_refresh_tela_move_moscas.get(i.getID()).release();         
            }
        }
    }
    
    public void redesenhaSapo() throws InterruptedException
    {
        synchronized(Globais.sapos)
        {
            for(Individuos i : Globais.sapos)
            {    
                Globais.semaphoro_refresh_tela_draw_sapos.get(i.getID()).acquire();
                if (i.getAtivo())
                {
                    i.draw(this.g);
                    synchronized(Globais.moscas)
                    {
                        for (int j = 0; j <= Globais.moscas.size() - 1; j++)
                        {
                            try
                            {   
                                if (Globais.moscas.get(j).getAtivo())
                                {
                                    if (verificaCoordenadas(i.getX(), i.getY(), Globais.moscas.get(j).getX(), Globais.moscas.get(j).getY()))
                                    { 
                                        Globais.moscas.get(j).setAtivo(false);           
                                        Globais.sapos.get(i.getID()).setComer(Globais.moscas.get(j).getCalorias());
                                        break;
                                    }
                                }
                            } catch(Exception e) {
                                break;                                                    
                            }   
                        }                                    
                    }         
                }
                Globais.semaphoro_refresh_tela_move_sapos.get(i.getID()).release();
            }
        }        
    }
    
     public boolean verificaCoordenadas(int x1, int y1, int x2, int y2)
    {       
        
        boolean xOK;
        boolean yOK;
        int diferencaX;
        int diferencaY;
        
        diferencaX = Math.abs(x1 - x2);
        diferencaY = Math.abs(y1 - y2);
                
        xOK = diferencaX <= 15;
        
        yOK = diferencaY <= 15;
       
        
        return xOK && yOK;
    }
    
    @Override
    public void run()
    {   try
        {
        switch (tipo)
        {
            case "ACUCAR":
                redesenhaAcucar();
                break;
            case "MOSCA":
                redesenhaMosca();
                break;
            case "SAPO":
                redesenhaSapo();
                break;
        }
        }catch(InterruptedException e){}
    }
    
}