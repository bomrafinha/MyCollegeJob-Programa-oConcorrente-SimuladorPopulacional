package simuladorpopulacional;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Bomrafinha
 */
public abstract class Individuos extends Thread
{
        // Velocidade do Indivíduo em pixels.
    private int dx,dy;
    
        // Tamanho do Individuo em pixels.
    private int iw,ih;
        
        // Imagem do Individuo.
    private Image icon;
    
        // Área do painel do jogo (para controlar movimento).
    private Dimension area;
    
        // Imagens
    private String imgNormal;
    private String imgComendo;
    private String imgMorrendo;    
    
    //Atributos
    
    private boolean morrendo;
    private boolean ativo;
    private int comer;
    private int contador;
    private int ID;
    private int calorias;
    private int x;
    private int y;
    
    /**
     *
     */
    public abstract void morre() throws ConcurrentModificationException;
    public abstract boolean isNasceu();
    
    public void teste()
    {     
        int i;
        int j;
        if (this.getCalorias() == 1)
        {
            i = (int)(Math.random()*100);
            j = i % 2;
            if (j == 0)
            {
                come(i);
            }
        }
    }

    public int getComer()
    {
        return comer;
    }

    public void setComer(int comer)
    {
        this.comer = comer;
    }
    
    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }
    
    public boolean getAtivo()
    {
        return this.ativo;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getCalorias()
    {
        return calorias;
    }

    public void setCalorias(int calorias)
    {
        this.calorias = calorias;
    }

    public int getID()
    {
        return this.ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    public Individuos(Dimension a, String imgNormal, String imgMorrendo, String imgComendo, int ID)
    {
        int posicionar;
        this.area = a;
        this.ID = ID;
        this.imgNormal = imgNormal;
        this.imgMorrendo = imgMorrendo;
        this.imgComendo = imgComendo;
        this.icon = new ImageIcon(getClass().getResource(imgNormal)).getImage();
        this.iw = icon.getWidth(null);
        this.ih = icon.getHeight(null);
        
        
            // x e y calculados usando a área do jogo.
        
        this.x = (int)(this.iw / 2 + Math.random() * (a.width - this.iw));        
        try
        {            
            Thread.sleep((int)Math.random()*20);
        } catch (InterruptedException ex){}        
        this.y = (int)(this.ih / 2 + Math.random() * (a.height - this.ih));
        
        // dx e dy aleatórios.
        while(this.dx == 0 || this.dy == 0)
        {
            posicionar = ((int)Math.random() * 4);
            if (posicionar == 0)
            {
                this.dx = 3 - (int)(Math.random() * 6);
                this.dy = 2 - (int)(Math.random() * 4);
            } else if (posicionar == 1)
            {
                this.dx = 3 - (int)(Math.random() * 4);
                this.dy = 2 - (int)(Math.random() * 6);            
            } else if (posicionar == 2)
            {
                this.dx = 2 - (int)(Math.random() * 6);
                this.dy = 3 - (int)(Math.random() * 4);                
            }else if (posicionar == 3)
            {
                this.dx = 2 - (int)(Math.random() * 4);
                this.dy = 3 - (int)(Math.random() * 6);                
            }
        }
        this.morrendo = false;
        this.contador = 0;
        this.ativo = true;
    }    
    
    public void move()
    {
        int posicionar = ((int)Math.random()*100);
        if ((posicionar % 2) == 0)
        {
            this.x += this.dx;
            this.y += this.dy;
        } else {
            this.x -= this.dx;
            this.y -= this.dy;            
        }
        if (this.x < this.iw / 2)
        { 
            this.dx =- this.dx;
            this.x += this.dx;
        }
        
        if (this.y < this.ih / 2)
        {
            this.dy =- this.dy;
            this.y += this.dy;
        }
        
        if (this.x > this.area.width - this.iw / 2)
        {
            this.dx =- this.dx;
            this.x += this.dx;
        }
        
        if (this.y > this.area.height - 100 - this.ih / 2)
        {
            this.dy =- this.dy;
            this.y += this.dy;
        }
    }
    
    public void subtraiCaloria() 
    {
        if (this.contador >= 9)
        {
            this.contador = 0;
            this.calorias--;
            if (!morrendo)
            {
                if (this.calorias <= (Globais.qtdCalorias / 5))
                {
                    this.icon = new ImageIcon(getClass().getResource(imgMorrendo)).getImage();
                    this.morrendo = true;
                    this.dx +=1;
                    this.dy +=1;
                }
            } else {
                if (this.calorias > (Globais.qtdCalorias / 5))
                {
                    this.icon = new ImageIcon(getClass().getResource(imgNormal)).getImage();
                    this.morrendo = false;
                    this.dx -=1;
                    this.dy -=1;
                    
                } else if (this.calorias <= 0)                
                {
                    this.ativo = false;
                }
            }
        } 
        if (this.contador < 0)
        {
            if (this.contador <= -60)
            {
                this.icon = new ImageIcon(getClass().getResource(imgComendo)).getImage();
            }                
            if (this.contador == -1)
            {
                this.icon = new ImageIcon(getClass().getResource(imgNormal)).getImage();
            }            
        }
        this.contador++;
    }
    
    public void come(int calorias)
    {
        this.calorias += calorias;
        contador = -60;
    }
    
    // Método que desenha o Indiviiduo em um contexto gráfico.
    public void draw(Graphics g)
    {
        g.drawImage(this.icon, this.x - this.iw / 2, this.y - this.ih / 2, null);
    }       

    public int compareTo(Individuos o)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}