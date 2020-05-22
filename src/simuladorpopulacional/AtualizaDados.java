/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorpopulacional;

/**
 *
 * @author Bomrafinha
 */
public class AtualizaDados extends Thread
{
    private int segundo;
    private int minuto;
    private int hora;
    private boolean pausar;

    public void setPausar(boolean pausarLocal)
    {
        this.pausar = !pausarLocal;
    }
    
    public AtualizaDados(boolean pausar)
    {
        this.segundo = 0;
        this.minuto = 0;
        this.hora = 0;
        this.pausar = !pausar;
    }
    
    private void mudaTempo()
    {
        if(this.segundo <= 59)
        {
            segundo++;
        } else {
            segundo = 0;
            if (this.minuto <= 59)
            {
                minuto++;
            } else {
                minuto = 0;
                hora++;
            }
        }
        
    }
    
    private String formataTempo()
    {
        String retorno;
        retorno = "";
        try
        {
            retorno += String.format("%1$02d:%2$02d:%3$02d", this.hora, this.minuto, this.segundo);
            
            return retorno;
        } catch(Exception e)
        {
            return "00:00:00";
        }        
    }
    
    private void atualizar(boolean tempo)
    {
        SimuladorPopulacional.setjLabel5Text(Integer.toString(Globais.qtdMoscas));
        SimuladorPopulacional.setjLabel7Text(Integer.toString(Globais.qtdSapos));
        SimuladorPopulacional.setjLabel10Text(Integer.toString(Globais.qtdAcucar));
        if (Globais.iniciar != 2)
        {
            if ((Globais.qtdMoscas == 0) && (Globais.qtdSapos == 0))
            {
                Globais.iniciar = 2;
                SimuladorPopulacional.setjButton1Desliga();
                Globais.finalizador.release();
                return;
            }
        }
        if (tempo && (Globais.iniciar != 2))
        {
            mudaTempo();
            SimuladorPopulacional.setjLabel11Text(formataTempo());             
        }        
    }    
    
    public void run()
    {
        int cont;
        cont = 0;
        while (true)
        {
            if (pausar)
            {                
                cont++;
                if (cont >= 10)
                {
                    atualizar(true);
                    cont = 0;
                } else {
                    atualizar(false);                    
                }
            }
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException ex)
            {

            }
        }
    }
    
}
