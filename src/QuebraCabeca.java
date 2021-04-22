/**
 * IFPB - TSI/POO
 * Prof. Fausto Ayres
 * Período: 2019.1
 * 
 * 
 * Equipe: 
 * 		Bruno Macau 
 * 		Yane Lisset 
 * 
 */

import java.util.Random;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class QuebraCabeca extends JPanel {
	private int[][] matriz;					//matriz com numeros 
	private final int DIMENSAO;				//dimensao do grid
	private int totaljogadas;

	private JLabel[][] grid1;  				//exibe para usuario a ordem correta
	private JLabel[][] grid2;  				//exibe para usuario fora da ordem
	private final int TAMANHO=40;			//largura/altura de cada label em pixels
	private final int XINICIAL = 0;			//localização do grid1
	private final int YINICIAL = 0;			//localização do grid1
	private final int ESPACAMENTO =50; 		//espaço entre os dois grids
	private JLabel labelTotal;
	private JLabel labelResultado;
	private JLabel primeiroselecionado;		//referencia o primeiro label clicado
	private int totalselecionados;
	private String nomesdearquivo;
	
	
	private int numero;

	public QuebraCabeca (int dimensao, String nomes) throws Exception {
		if(dimensao>2 && dimensao<6) {
		this.DIMENSAO=dimensao;
		this.matriz = new int[dimensao][dimensao];
		this.setLayout(null);
		this.setSize(XINICIAL+ESPACAMENTO+2 * this.DIMENSAO *TAMANHO, YINICIAL+this.DIMENSAO*TAMANHO + 80 );//2*largura e altura do painel
		this.nomesdearquivo = nomes;
		}
		else { 
			throw new Exception("Dimensao precisa esta no intervalo de 3 a 5!");
		}
	}

	public void iniciar() {
		this.totaljogadas=0;		
		this.totalselecionados=0;
		this.removeAll();	
		this.grid1 = new JLabel[this.DIMENSAO][this.DIMENSAO];
		this.grid2 = new JLabel[this.DIMENSAO][this.DIMENSAO];
		this.labelTotal = new JLabel();
		this.labelTotal.setSize(200, 30);
		this.labelTotal.setLocation(XINICIAL, YINICIAL+DIMENSAO*TAMANHO+10);
		this.labelTotal.setText("Total de Jogadas:" + totaljogadas);
		add(this.labelTotal);
		this.labelResultado = new JLabel();
		this.labelResultado.setText("Selecione 2 celulas para trocar de posicao!");
		this.labelResultado.setSize(800, 30);
		this.labelResultado.setLocation(XINICIAL,YINICIAL+DIMENSAO*TAMANHO+40);

		add(this.labelResultado);
		this.repaint();		
		ordenarConteudo();
		desenharGridOrdenado();
		embaralharConteudo();
		desenharGridDesordenado();
	}

//3º MÉTODO: armazena a sequência 1,2,3,...,NxN na matriz;
	private void ordenarConteudo() {
	
		int contador=1;
		for(int linha=0; linha<this.DIMENSAO; linha++) {
			for(int coluna=0; coluna<this.DIMENSAO; coluna++) {
				this.matriz[linha][coluna] = contador;
				contador++;
			}
		}	
	}
	
//4º MÉTODO: altera aleatoriamente a sequência dos números da matriz;
	private void embaralharConteudo() {
		Random random = new Random();
		    for (int n = 0; n < this.DIMENSAO * this.DIMENSAO; n++) {
				int linha0 = random.nextInt(this.DIMENSAO);
				int coluna0 = random.nextInt(this.DIMENSAO);
				int linha1 = random.nextInt(this.DIMENSAO);
				int coluna1 = random.nextInt(this.DIMENSAO);
				//Trocando 2 posições 
				int aux = this.matriz[linha0][coluna0];
				this.matriz[linha0][coluna0] = this.matriz[linha1][coluna1];
				this.matriz[linha1][coluna1] = aux;
			}		
	}

	private void desenharGridOrdenado() {
		//desenhar o grid1 a partir do ponto (xinicial,yinicial)
		int cont = 0;
		for(int linha=0; linha < this.DIMENSAO; linha++){
			for(int coluna=0; coluna < this.DIMENSAO; coluna++){
				grid1[linha][coluna]=new JLabel(this.matriz[linha][coluna]+"");
				
				//Pegando as imagens da pasta 
				
				//grid1[linha][coluna].setIcon(new ImageIcon(QuebraCabeca.class.getResource(nomesdearquivo[cont])));
				ImageIcon icon = null;
				if(nomesdearquivo=="/imagens3x3/") {
				icon = new ImageIcon(QuebraCabeca.class.getResource("/imagens3x3/" + (cont+1) + ".jpg"));
				}
				else if(nomesdearquivo == "/imagens4x4/") {
				icon = new ImageIcon(QuebraCabeca.class.getResource("/imagens4x4/" + (cont+1) + ".jpg"));	
				}
				else {
				icon = new ImageIcon(QuebraCabeca.class.getResource("/imagens5x5/" + (cont+1) + ".jpg"));	
				}
				//ImageIcon icon =new ImageIcon(QuebraCabeca.class.getResource(nomesdearquivo[cont]));
				icon.setImage(icon.getImage().getScaledInstance(40 , 40 , Image.SCALE_DEFAULT) );
				grid1[linha][coluna].setIcon(icon);
				
				add(this.grid1[linha][coluna]);
				cont+=1;
				this.grid1[linha][coluna].setSize(TAMANHO, TAMANHO);			//width, height - TAMANHOxTAMANHO
				this.grid1[linha][coluna].setLocation(XINICIAL+coluna*TAMANHO, YINICIAL+linha*TAMANHO);	
				this.grid1[linha][coluna].setBackground(Color.WHITE);
				this.grid1[linha][coluna].setBorder(new LineBorder(Color.BLACK,1,true));
				this.grid1[linha][coluna].setOpaque(true);					
			}
		}
		
	}

	private void desenharGridDesordenado() {
		//coordenadas do inicio do grid2
		int xinicial2 = XINICIAL + this.DIMENSAO*TAMANHO + ESPACAMENTO; 	//espacamento entre os grids
		int yinicial2 = YINICIAL;									//o mesmo do grid1

		for(int linha=0; linha < this.DIMENSAO; linha++){
			for(int coluna=0; coluna < this.DIMENSAO; coluna++){
				this.grid2[linha][coluna]=new JLabel(getNumero(linha, coluna)+"");
				
				ImageIcon icon = null;
				numero=getNumero(linha, coluna);
				
				if(nomesdearquivo=="/imagens3x3/") {
				icon = new ImageIcon(QuebraCabeca.class.getResource("/imagens3x3/" + numero + ".jpg"));
					}
				else if(nomesdearquivo == "/imagens4x4/") {
				icon = new ImageIcon(QuebraCabeca.class.getResource("/imagens4x4/" + numero + ".jpg"));	
					}
				else {
				icon = new ImageIcon(QuebraCabeca.class.getResource("/imagens5x5/" + numero + ".jpg"));	
					}
				icon.setImage(icon.getImage().getScaledInstance(40 , 40 , 1) );
				
				grid2[linha][coluna].setIcon(icon);
				add(this.grid2[linha][coluna]);
				this.grid2[linha][coluna].setSize(TAMANHO, TAMANHO);			//width, height - TAMANHOxTAMANHO
				this.grid2[linha][coluna].setLocation(xinicial2+coluna*TAMANHO, yinicial2+linha*TAMANHO);	
				this.grid2[linha][coluna].setBackground(Color.WHITE);
				this.grid2[linha][coluna].setBorder(new LineBorder(Color.BLACK,1,true));
				this.grid2[linha][coluna].setOpaque(true);
				

				this.grid2[linha][coluna].addMouseListener(
						new  MouseAdapter(){
							private int lin1, col1, lin2, col2;
							public void mouseClicked(MouseEvent e){		//click no label 
								
								//obter o componente label que recebeu o click
								JLabel labelselecionado = (JLabel)e.getSource();
								labelselecionado.setBorder(new LineBorder(Color.BLUE,3,true));
								QuebraCabeca.this.totalselecionados++;

								if(QuebraCabeca.this.totalselecionados == 1) 	//PRIMEIRO LABEL SELECIONADO 
									QuebraCabeca.this.primeiroselecionado = labelselecionado;
								else
									if(QuebraCabeca.this.totalselecionados == 2) { //SEGUNDO LABEL SELECIONADO
										this.col1 = (labelselecionado.getX()-xinicial2)/TAMANHO ;
										this.lin1 = (labelselecionado.getY()-yinicial2)/TAMANHO ;
										this.col2 = (QuebraCabeca.this.primeiroselecionado.getX()-xinicial2)/TAMANHO;
										this.lin2 = (QuebraCabeca.this.primeiroselecionado.getY()-yinicial2)/TAMANHO;
										try {
											QuebraCabeca.this.jogar(lin1,col1,lin2,col2);
										} catch (Exception e1) {
											e1.printStackTrace();
										}
										QuebraCabeca.this.totalselecionados=0;
										QuebraCabeca.this.primeiroselecionado.setBorder(new LineBorder(Color.BLACK,1,true));
										labelselecionado.setBorder(new LineBorder(Color.BLACK,1,true));
									}
								if(QuebraCabeca.this.finalizado()) {
									QuebraCabeca.this.labelResultado.setText("Parabéns! - Reinicie");
									//remover os ouvintes MouseListener de cada label
									for(int linha=0; linha < QuebraCabeca.this.DIMENSAO; linha++){
										for(int coluna=0; coluna < QuebraCabeca.this.DIMENSAO; coluna++) {
											for(MouseListener m : QuebraCabeca.this.grid2[linha][coluna].getMouseListeners())
												QuebraCabeca.this.grid2[linha][coluna].removeMouseListener(m);
										}
									}
								}
							}
						});
			}
		}
	}
	
	public String toString() {
		String texto="";
		texto+="\n----------Quebra-Cabeça:-----------\n";
		texto+="    ";
		for(int x=0; x<DIMENSAO; x++) 	texto+="   " + x;
		texto+="\n    ";
		for(int x=0; x<DIMENSAO; x++) 	texto+="__"; 
		texto+="\n";

		//exibir a matriz
		String numeroformatado;
		for(int linha=0; linha<DIMENSAO; linha++) {
			texto+=linha+"  |";
			for(int coluna=0; coluna<DIMENSAO; coluna++) {
				numeroformatado = matriz[linha][coluna]+"";
				if(matriz[linha][coluna]<10) 
					numeroformatado=" "+numeroformatado;
				texto+="  "+ numeroformatado;
			}
			texto+="\n";
		}

		texto+="    ";
		for(int x=0; x<DIMENSAO; x++) texto+="----";
		texto+="\nTotal de Jogadas:"+this.getTotalJogadas()+"\n";

		return texto;
	}
	
//5º MÉTODO - verifica se os números da matriz estão na sequência 1,2,3,...,NxN;
	public boolean finalizado() {
		int contador = 1;
		for (int linha = 0; linha < this.DIMENSAO; linha++) {
			for (int coluna = 0; coluna < this.DIMENSAO; coluna++){
				if (this.matriz[linha][coluna] != contador) {
					return false;
				}
			contador++;
			}
		}
		return true;
	}
	
//6º MÉTODO: troca dois números da matriz e duas células do grid2 nas duas posições informadas.
	public void jogar(int linha1, int coluna1, int linha2, int coluna2) throws Exception {
		if(linha1 >=0  && coluna1 >=0 && linha2 >=0  && coluna2 >=0 && linha1<= this.DIMENSAO && linha2<=this.DIMENSAO && coluna1<=this.DIMENSAO && coluna2<=this.DIMENSAO) {
		
		totaljogadas++;
		labelTotal.setText("Total de Jogadas:"+totaljogadas);	
		
		//troca dois números da matriz
		int aux = this.matriz[linha1][coluna1];
		this.matriz[linha1][coluna1] = this.matriz[linha2][coluna2];
		this.matriz[linha2][coluna2] = aux;
		
	    //duas células do grid2 nas duas posições informadas
		String conteudo = this.grid2[linha1][coluna1].getText();
		Icon auxi = grid2[linha1][coluna1].getIcon();
		this.grid2[linha1][coluna1].setText(this.grid2[linha2][coluna2].getText());
		grid2[linha1][coluna1].setIcon(grid2[linha2][coluna2].getIcon());
		this.grid2[linha2][coluna2].setText(conteudo);
		grid2[linha2][coluna2].setIcon(auxi);
		}
		else { 
			throw new Exception("Fora do intervalo!");
		}
	}
	
	public int getTotalJogadas() {
		return totaljogadas;
	}

	public int getNumero(int linha, int coluna) {
		return matriz[linha][coluna] ;
	}

	public int getDimensao() {
		return DIMENSAO;
	}
}