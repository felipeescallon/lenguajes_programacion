import java.util.Date;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Reloj extends Applet implements ActionListener 
{

private Button bhora, bminutos, bsegundos, bdia, bmes, bano, bresetear, bmas, bmenos;
private ClaseReloj corredor;
private Animovimiento movimiento;

public void init()
{
setLayout(new BorderLayout());
Panel p = new Panel();	
corredor=new ClaseReloj();
add("Center", corredor);

bhora = new Button("     Hora     ");		
bminutos = new Button("  Minutos  ");
bsegundos = new Button("Segundos");
bdia = new Button("      Día      ");
bmes = new Button("      Mes      ");
bano = new Button("      Año      ");
bresetear = new Button("    Reset    ");
bmas = new Button("        +        ");
bmenos = new Button("         -         ");

setLayout(new GridLayout(3,3));
p.add(bhora);				
p.add(bminutos);
p.add(bsegundos);
p.add(bdia);
p.add(bmes);
p.add(bano);
p.add(bresetear);
p.add(bmas);
p.add(bmenos);

	
add("South", p);

bmas.setEnabled(false);
bmenos.setEnabled(false);
bresetear.setEnabled(false);

bhora.addActionListener(this);		
bminutos.addActionListener(this);
bsegundos.addActionListener(this);
bdia.addActionListener(this);	
bmes.addActionListener(this);	
bano.addActionListener(this);
bmas.addActionListener(this);	
bmenos.addActionListener(this);	
bresetear.addActionListener(this);		
}

public void actionPerformed(ActionEvent e)
{    	
	Object source = e.getSource();
	
	if(source==bhora)
		{
		if(bhora.getLabel().equals("     Hora     "))
			{
			bhora.setLabel("Aceptar");
			corredor.salvarhora();
			bminutos.setEnabled(false);
			bsegundos.setEnabled(false);
			bdia.setEnabled(false);	
			bmes.setEnabled(false);	
			bano.setEnabled(false);
			bresetear.setEnabled(true);
			bmas.setEnabled(true);
			bmenos.setEnabled(true);
			}
		else
			{
			bhora.setLabel("     Hora     ");
			bminutos.setEnabled(true);
			bsegundos.setEnabled(true);
			bdia.setEnabled(true);	
			bmes.setEnabled(true);	
			bano.setEnabled(true);
			bresetear.setEnabled(false);
			bmas.setEnabled(false);
			bmenos.setEnabled(false);
			}
	
		}
		
	if(source==bminutos)
		{
		if(bminutos.getLabel().equals("  Minutos  "))
			{
			bminutos.setLabel("Aceptar");
			corredor.salvarminutos();
			bhora.setEnabled(false);
			bsegundos.setEnabled(false);
			bdia.setEnabled(false);	
			bmes.setEnabled(false);	
			bano.setEnabled(false);
			bresetear.setEnabled(true);
			bmas.setEnabled(true);
			bmenos.setEnabled(true);
			}
		else
			{
			bminutos.setLabel("  Minutos  ");
			bhora.setEnabled(true);
			bsegundos.setEnabled(true);
			bdia.setEnabled(true);	
			bmes.setEnabled(true);	
			bano.setEnabled(true);
			bresetear.setEnabled(false);
			bmas.setEnabled(false);
			bmenos.setEnabled(false);
			}
	
		}

	if(source==bsegundos)
		{
		if(bsegundos.getLabel().equals("Segundos"))
			{
			corredor.salvarsegundos();	
			bsegundos.setLabel("Aceptar");
			bminutos.setEnabled(false);
			bhora.setEnabled(false);
			bdia.setEnabled(false);	
			bmes.setEnabled(false);	
			bano.setEnabled(false);
			bresetear.setEnabled(true);
			bmas.setEnabled(true);
			bmenos.setEnabled(true);
			}
		else
			{
			bsegundos.setLabel("Segundos");
			bminutos.setEnabled(true);
			bhora.setEnabled(true);
			bdia.setEnabled(true);	
			bmes.setEnabled(true);	
			bano.setEnabled(true);
			bresetear.setEnabled(false);
			bmas.setEnabled(false);
			bmenos.setEnabled(false);
			}
	
		}


	if(source==bdia)
		{
		if(bdia.getLabel().equals("      Día      "))
			{
			corredor.salvardia();
			bdia.setLabel("Aceptar");
			bminutos.setEnabled(false);
			bsegundos.setEnabled(false);
			bhora.setEnabled(false);	
			bmes.setEnabled(false);	
			bano.setEnabled(false);
			bresetear.setEnabled(true);
			bmas.setEnabled(true);
			bmenos.setEnabled(true);
			}
		else
			{
			bdia.setLabel("      Día      ");
			bminutos.setEnabled(true);
			bsegundos.setEnabled(true);
			bhora.setEnabled(true);	
			bmes.setEnabled(true);	
			bano.setEnabled(true);
			bresetear.setEnabled(false);
			bmas.setEnabled(false);
			bmenos.setEnabled(false);
			}
	
		}


	if(source==bmes)
		{
		if(bmes.getLabel().equals("      Mes      "))
			{
			corredor.salvarmes();
			bmes.setLabel("Aceptar");
			bminutos.setEnabled(false);
			bsegundos.setEnabled(false);
			bdia.setEnabled(false);	
			bhora.setEnabled(false);	
			bano.setEnabled(false);
			bresetear.setEnabled(true);
			bmas.setEnabled(true);
			bmenos.setEnabled(true);
			}
		else
			{
			bmes.setLabel("      Mes      ");
			bminutos.setEnabled(true);
			bsegundos.setEnabled(true);
			bdia.setEnabled(true);	
			bhora.setEnabled(true);	
			bano.setEnabled(true);
			bresetear.setEnabled(false);
			bmas.setEnabled(false);
			bmenos.setEnabled(false);
			}
	
		}


	if(source==bano)
		{
		if(bano.getLabel().equals("      Año      "))
			{
			corredor.salvarano();
			bano.setLabel("Aceptar");
			bminutos.setEnabled(false);
			bsegundos.setEnabled(false);
			bdia.setEnabled(false);	
			bmes.setEnabled(false);	
			bhora.setEnabled(false);
			bresetear.setEnabled(true);
			bmas.setEnabled(true);
			bmenos.setEnabled(true);
			}
		else
			{
			bano.setLabel("      Año      ");
			bminutos.setEnabled(true);
			bsegundos.setEnabled(true);
			bdia.setEnabled(true);	
			bmes.setEnabled(true);	
			bhora.setEnabled(true);
			bresetear.setEnabled(false);
			bmas.setEnabled(false);
			bmenos.setEnabled(false);
			}
	
		}

	if(source==bmas && bhora.getLabel().equals("Aceptar"))
		{
		corredor.modificarhora(1);
		}
		
	if(source==bmenos && bhora.getLabel().equals("Aceptar"))
		{
		corredor.modificarhora(0);
		}
	
	if(source==bresetear && bhora.getLabel().equals("Aceptar"))
		{
		corredor.resetearhora();
		}
	
	if(source==bmas && bminutos.getLabel().equals("Aceptar"))
		{
		corredor.modificarminutos(1);
		}

	if(source==bmenos && bminutos.getLabel().equals("Aceptar"))
		{
		corredor.modificarminutos(0);
		}
	
	if(source==bresetear && bminutos.getLabel().equals("Aceptar"))
		{
		corredor.resetearminutos();
		}

	if(source==bmas && bsegundos.getLabel().equals("Aceptar"))
		{
		corredor.modificarsegundos(1);
		}

	if(source==bmenos && bsegundos.getLabel().equals("Aceptar"))
		{
		corredor.modificarsegundos(0);
		}
	
	if(source==bresetear && bsegundos.getLabel().equals("Aceptar"))
		{
		corredor.resetearsegundos();
		}
	
	if(source==bmas && bdia.getLabel().equals("Aceptar"))
		{
		corredor.modificardia(1);
		}
		
	if(source==bmenos && bdia.getLabel().equals("Aceptar"))
		{
		corredor.modificardia(0);
		}
		
	if(source==bresetear && bdia.getLabel().equals("Aceptar"))
		{
		corredor.reseteardia();
		}
	
	if(source==bmas && bmes.getLabel().equals("Aceptar"))
		{
		corredor.modificarmes(1);
		}

	if(source==bmenos && bmes.getLabel().equals("Aceptar"))
		{
		corredor.modificarmes(0);
		}

	if(source==bresetear && bmes.getLabel().equals("Aceptar"))
		{
		corredor.resetearmes();
		}
	
	if(source==bmas && bano.getLabel().equals("Aceptar"))
		{
		corredor.modificarano(1);
		}

	if(source==bmenos && bano.getLabel().equals("Aceptar"))
		{
		corredor.modificarano(0);
		}
	
	if(source==bresetear && bano.getLabel().equals("Aceptar"))
		{
		corredor.resetearano();
		}
}

public void start()
{
movimiento= new Animovimiento(corredor);
}

public void stop()
{
movimiento.stop();
}		

}







































