/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorpopulacional;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author Bomrafinha
 */


@SuppressWarnings("serial")
public class SimulatorPanel extends JPanel implements Runnable
{    
    private Thread animator; 
    private boolean telaDesenhada;
    
        // Construtor, inicializa estruturas, registra interfaces, etc.
    public SimulatorPanel()
    {
        Globais.iniciar = 0;
        this.telaDesenhada = false;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Globais.largura, Globais.altura));
        Globais.dimensao = new Dimension(Globais.largura, Globais.altura);
        setFocusable(true);
        requestFocus();
    }
    
    @Override
    public void addNotify()
    {
        super.addNotify();
        startGame();
    }
    
    private void startGame()
    {
        if (this.animator == null)
        {
            this.animator = new Thread(this);
            this.animator.start();
        }    
    }    
       
        // Laço principal do jogo.
    @Override
    public void run()
    {        
        int acucar;
        while (Globais.iniciar == 0)
        {
            try
            {
                Thread.sleep(10);
            }catch (InterruptedException e)
            {
                
            }            
        }
        
        acucar = Globais.qtdSapos + Globais.qtdMoscas;
        acucar *= 3 ;
        
        Globais.qtdAcucar = (int)(Math.random() * acucar) + 1;
        
        for(int i = 0; i < Globais.qtdSapos; i++)
        {
            Globais.sapos.add(new Sapo(this.getPreferredSize(), i));   
        }
        
        for(int i = 0; i < Globais.qtdMoscas; i++)
        {
            Globais.moscas.add(new Mosca(this.getPreferredSize(), i));     
        }
        
        for(int i = 0; i < Globais.qtdAcucar; i++)
        {
            Globais.acucar.add(new Acucar(this.getPreferredSize(), i)); 
        }        
        
        for(Individuos i : Globais.sapos)
        {
            i.start();
            Globais.semaphoro_refresh_tela_move_sapos.get(i.getID()).release(); 
        }
        for(Individuos i : Globais.moscas)
        {
            i.start();
            Globais.semaphoro_refresh_tela_move_moscas.get(i.getID()).release();
        }
        for(Individuos i : Globais.acucar)
        {
            i.start();
            Globais.semaphoro_refresh_tela_move_acucar.get(i.getID()).release();
        }
        telaDesenhada = true;                
        while(Globais.iniciar != 2)
        {
            try
            {
                while (Globais.iniciar == 0)
                {
                    try
                    {
                        Thread.sleep(10);
                    }catch (InterruptedException e)
                    {

                    }            
                }
                repaint();
                Thread.sleep(Globais.delayMs);
            }catch (InterruptedException e)
            {
                
            }
        }
        try
        {
            this.finalizar();
        } catch (Throwable ex){}
    }    
        
    @Override
    // Desenhamos o componente (e seus elementos)
    protected void paintComponent(Graphics g)
    {
        if (!telaDesenhada)
        {
            super.paintComponent(g);            
        }
        if (Globais.iniciar == 1)
        {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, getWidth(), getHeight()); 
            try
            {
                RedesenhaTela mostraSapos;
                RedesenhaTela mostraMoscas;
                RedesenhaTela mostraAcucar;
                mostraAcucar = new RedesenhaTela(g, "ACUCAR");
                mostraSapos = new RedesenhaTela( g, "SAPO");
                mostraMoscas = new RedesenhaTela(g, "MOSCA");
                if (!Globais.acucar.isEmpty())
                {    
                    mostraAcucar.start();
                    mostraAcucar.join();
                }
                if (!Globais.moscas.isEmpty())
                {
                    mostraMoscas.start();
                    mostraMoscas.join();
                }
                if (!Globais.sapos.isEmpty())
                {
                    mostraSapos.start();
                    mostraSapos.join();
                }
            } catch (InterruptedException ex)
            {

            }
        }
    }
    
    private void finalizar() throws Throwable
    {
        try
        {
            Globais.finalizador.acquire();
            
            synchronized(Globais.acucar)
            {
                for (int i = 0; i >= Globais.acucar.size(); i++)                        
                {
                    Globais.acucar.get(i).destroy();
                }
                Globais.acucar.clear();
            }
            
            synchronized(Globais.moscas)
            {
                for (int i = 0; i >= Globais.moscas.size(); i++)                        
                {
                    Globais.moscas.get(i).destroy();
                }
                Globais.moscas.clear();
            }
            
            synchronized(Globais.sapos)
            {
                for (int i = 0; i >= Globais.sapos.size(); i++)                        
                {
                    Globais.sapos.get(i).destroy();
                }
                Globais.sapos.clear();
            }
            
            synchronized(Globais.semaphoro_refresh_tela_draw_acucar)
            {
                Globais.semaphoro_refresh_tela_draw_acucar.clear();                
            }
            
            synchronized(Globais.semaphoro_refresh_tela_draw_moscas)
            {
                Globais.semaphoro_refresh_tela_draw_moscas.clear();                
            }
            
            synchronized(Globais.semaphoro_refresh_tela_draw_sapos)
            {
                Globais.semaphoro_refresh_tela_draw_sapos.clear();                
            }
            
             synchronized(Globais.semaphoro_refresh_tela_move_acucar)
            {
                Globais.semaphoro_refresh_tela_move_acucar.clear();                
            }
            
            synchronized(Globais.semaphoro_refresh_tela_move_moscas)
            {
                Globais.semaphoro_refresh_tela_move_moscas.clear();                
            }
            
            synchronized(Globais.semaphoro_refresh_tela_move_sapos)
            {
                Globais.semaphoro_refresh_tela_move_sapos.clear();                
            }
            
            this.finalize();
            
        } catch (InterruptedException ex){}
        
    }
       
}