/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorpopulacional;

import java.awt.Dimension;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Bomrafinha
 */
public class Sapo extends Individuos
{
    
    public Sapo(Dimension a, int id)
    {
        super(a, "images/sapo_comum.png", "images/sapo_morrendo.png", "images/sapo_comendo.png", id);
        setCalorias(Globais.qtdCalorias);    
        Globais.proxIDSapo++;      
        Globais.semaphoro_refresh_tela_draw_sapos.add(new Semaphore(0));
        Globais.semaphoro_refresh_tela_move_sapos.add(new Semaphore(0));
    } 
    
    public void run()
    {
        try
        {
            while (true)
            {
                Globais.semaphoro_refresh_tela_move_sapos.get(this.getID()).acquire();
                if (!this.getAtivo())
                {
                    Globais.semaphoro_refresh_tela_draw_sapos.get(this.getID()).release();
                    this.morre();
                } else {
                    this.move();
                    if (this.getComer() > 0)
                    {
                        come(getComer());
                        setComer(0);
                    }
                    this.subtraiCaloria();
                    Globais.semaphoro_refresh_tela_draw_sapos.get(this.getID()).release();
                }
            }
        } catch (InterruptedException ex)
        {
            
        }
    }    
    
    @Override
    public void morre()
    {
        synchronized(Globais.sapos)
        {                
            if (!Globais.sapos.isEmpty())
            {     
                if (Globais.sapos.remove(this))
                {
                    Globais.qtdSapos--;
                }
            }
        }
    }

    @Override
    public boolean isNasceu()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
