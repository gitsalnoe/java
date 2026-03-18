
import java.awt.*;
import java.applet.*;

public class RekursiveFiguren extends Applet
{
			public void paint(Graphics g)	{
				quadrat(g, 300, 5, 5);
				kreis(g, 300, 350, 5);
				quadrate(g, 150, 5, 350);
				kreise(g, 100, 5, 550);
			}
			
			public void quadrat(Graphics g, int laenge, int x, int y)	{
				if (laenge <= 0) {
					return; 
				}
				else	{
					g.drawRect(x, y ,laenge ,laenge);
					quadrat(g, laenge - 10, x + 5, y + 5);
				}
			}
			
			public void kreis(Graphics g, int radius, int x, int y)	{
					if (radius <= 0)	{
						return;
					}
					else	{
						g.drawOval(x, y, radius, radius);
						kreis(g, radius - 10, x + 5, y + 5);
					}
			}
			
			public void quadrate(Graphics g, int laenge, int x, int y ) {
				if (laenge <= 50)	{
					return;
				}
				else	{
					g.drawRect(x, y, laenge, laenge);
					quadrate(g, laenge - 20, x + laenge + 5, y);
				}
			}
			
			public void kreise(Graphics g, int radius, int x, int y)	{
				if (x >= 500)	{
					return;
				}
				else	{
					g.drawOval(x, y, radius, radius);
					kreise(g, radius, x + 5, y);
				}
			}
			
}
