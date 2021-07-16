#include <stdio.h>
#include <conio.h>
#include <process.h>
#include "vgalib.h"

#define IMAGEN "c:\\toast.pcx"

int main(void)
{
	int r;
	t_paleta pal;
	BYTE *image;

	r=CargaPcx(IMAGEN, pal, &image);
	if(r!=1)
	{
		switch(r)
		{
			case  0: printf("\nNo se encontro el archivo %s\n", IMAGEN); break;
			case -1: printf("\nEl archivo %s no es ZSOFT\n", IMAGEN); break;
			case -2: printf("\nEl tama¤o de la imagen es muy grande\n"); break;
			case -3: printf("\nMemoria insuficiente al cargar la imagen\n"); break;
		}
		exit(1);
	}

	clrscr();
	printf("\nVisualizacion de una imagen peque¤a en formato PCX");
	printf("\n\nPresione cualquier tecla para continuar...");
	getch();

	SetMode(GRAFICO);

	DelPal(0);
	PutImageCenter(image, DIR_VGA);
	FadeIn(pal);

	getch();
	FadeOut();
	SetMode(TEXTO);
	free(image);

	return 0;
}