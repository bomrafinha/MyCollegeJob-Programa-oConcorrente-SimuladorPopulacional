package simuladorpopulacional;

import java.awt.Dimension;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Bomrafinha
 */
public class Mosca extends Individuos
{
    private boolean nasceu;


    public Mosca(Dimension a, int id)
    {
        super(a, "images/mosca_comum.png", "images/mosca_morrendo.png", "images/mosca_comendo.png", id);
        this.nasceu = false;
        setCalorias(Globais.qtdCalorias); 
        Globais.proxIDMosca++; 
        Globais.semaphoro_refresh_tela_draw_moscas.add(new Semaphore(0));
        Globais.semaphoro_refresh_tela_move_moscas.add(new Semaphore(0));
    }
    
    public void run()
    {
        try
        {
            while (true)
            {
                Globais.semaphoro_refresh_tela_move_moscas.get(this.getID()).acquire();
                if (!this.getAtivo())
                {
                    Globais.semaphoro_refresh_tela_draw_moscas.get(this.getID()).release();
                    this.morre();
                } else {
                    try
                    {
                        if (!isNasceu())
                        {
                            nasceu = true;
                        }
                        this.move();
                        if (this.getComer() > 0)
                        {
                            come(getComer());
                            setComer(0);
                        }
                        this.subtraiCaloria();
                        Globais.semaphoro_refresh_tela_draw_moscas.get(this.getID()).release();
                    } catch (Exception e)
                    {                       
                        Globais.semaphoro_refresh_tela_draw_moscas.get(this.getID()).release(); 
                    }
                }
            }
        } catch (InterruptedException ex)
        {
            
        }
    }
    
    @Override
    public void come(int calorias)
    {
        super.come(calorias);
        if (this.getCalorias() > (Globais.qtdCalorias * 3))
        {
            duplicaMosca(this.getCalorias() / 2);
        }        
    }    
    
    public void duplicaMosca(int caloria)
    {
        int novaMosca;
        synchronized(Globais.moscas)
        {       
            novaMosca = Globais.proxIDMosca;
            try
            {
                Globais.moscas.add(new Mosca(Globais.dimensao, novaMosca)); 
                Globais.moscas.get(Globais.moscas.get(novaMosca).getID()).start();
                if (Globais.moscas.get(novaMosca).isAlive())
                {
                    Globais.qtdMoscas++;
                }
                Globais.moscas.get(this.getID()).setCalorias(caloria);
            } catch (Exception e){}
        }        
    }

    @Override
    public void morre()
    {
        try
        {
            synchronized(Globais.moscas)
            {                
                if (!Globais.moscas.isEmpty())
                {                      
                    if (Globais.moscas.remove(this))
                    {
                        Globais.qtdMoscas--;
                    }
                }
            }
        } catch (Exception e) {}
    }
    
    @Override
    public boolean isNasceu()
    {
        return nasceu;
    }
}
