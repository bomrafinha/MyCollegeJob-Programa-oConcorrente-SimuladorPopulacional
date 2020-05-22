package simuladorpopulacional;

import java.awt.Dimension;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Bomrafinha
 */
public class Acucar extends Individuos
{
    public Acucar(Dimension a, int id)
    {
        super(a, "images/acucar.png", "images/acucar.png", "images/acucar.png", id);
        setCalorias(Globais.qtdCalorias);   
        Globais.proxIDAcucar++;
        Globais.semaphoro_refresh_tela_draw_acucar.add(new Semaphore(0));
        Globais.semaphoro_refresh_tela_move_acucar.add(new Semaphore(0));
    } 
    
    public void run()
    {
        try
        {
            while (true)
            {
                Globais.semaphoro_refresh_tela_move_acucar.get(this.getID()).acquire();
                if (!this.getAtivo())
                {
                    Globais.semaphoro_refresh_tela_draw_acucar.get(this.getID()).release();
                    this.morre();
                }else{
                    Globais.semaphoro_refresh_tela_draw_acucar.get(this.getID()).release();
                }
            }
        } catch (InterruptedException ex)
        {
            
        }
    }   

    @Override
    public void morre()
    {
        synchronized(Globais.acucar)
        {    
            if (!Globais.acucar.isEmpty())
            {            
                if (Globais.acucar.remove(this))
                {
                    Globais.qtdAcucar--;
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
