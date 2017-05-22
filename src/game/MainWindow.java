package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import game.objects.Ball;
import game.objects.Barra;
import game.objects.GameObject;

import com.jogamp.opengl.util.FPSAnimator;


@SuppressWarnings("serial")
public class MainWindow extends GLCanvas implements GLEventListener, KeyListener{

	private GL2 gl;
	private GLU glu;
	private GLUT glut;
	private Game game;
	
	//Para definir as Coordenadas do sistema
	float xMin, xMax, yMin, yMax, zMin, zMax;
	
	float tx=0;
	float ty=0;
	float limiteEsq = -6;
	float limiteDir = 6;
	float limiteCim = 6;
	float limiteBai = -6;

	// Define constants for the top-level container
	private static String TITULO = "PongGL";
	private static final int CANVAS_LARGURA = 500; // largura do drawable
	private static final int CANVAS_ALTURA = 500; // altura do drawable
	private static final int FPS = 60; // define frames per second para a animacao

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Cria a janela de renderizacao OpenGL
				GLCanvas canvas = new MainWindow();
				canvas.setPreferredSize(new Dimension(CANVAS_LARGURA, CANVAS_ALTURA));
				final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
				final JFrame frame = new JFrame(); 
				
				frame.getContentPane().add(canvas);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						new Thread() {
							@Override
							public void run() {
								if (animator.isStarted())
									animator.stop();
								System.exit(0);
							}
						}.start();
					}
				});
				frame.setTitle(TITULO);
				frame.pack();
				frame.setLocationRelativeTo(null); //Center frame
				frame.setVisible(true);
				animator.start(); // inicia o loop de animacao
			}
		});
	}


	/** Construtor da classe */
	public MainWindow() {
		this.addGLEventListener(this);

		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
	}

	/**
	 *Chamado uma vez quando o contexto OpenGL eh criado
	 */
	@Override
	public void init(GLAutoDrawable drawable) {		
		gl = drawable.getGL().getGL2(); // obtem o contexto grafico OpenGL	
		glu = new GLU(); 
		
		// Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
		xMin = -10;
		xMax = 10;
		yMin = -10;
		yMax = 10;
		zMin = -10;
		zMax = 10;
		

		
		game = new Game(xMin, xMax, yMin, yMax);
		
		//Habilita o buffer de profundidade
		gl.glEnable(GL2.GL_DEPTH_TEST);
	}

	/**
	 * Chamado quando a janela eh redimensionada
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {		
		gl = drawable.getGL().getGL2(); // obtem o contexto grafico OpenGL

		// Ativa a matriz de projecao
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		// Projecao ortogonal 3D			
		gl.glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);


		// Ativa a matriz de modelagem
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		System.out.println("Reshape: " + width + " " + height);
	}

	/**
	 * Chamado para renderizar a imagem do GLCanvas pelo animator
	 */
	@Override
	public void display(GLAutoDrawable drawable) {		
		gl = drawable.getGL().getGL2(); // obtem o contexto grafico OpenGL		
		glut = new GLUT();
		
		// Especifica que a cor para limpar a janela de visualizacao eh preta
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// Limpa a janela de visualizacao com a cor de fundo especificada
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		
		//Redefine a matriz atual com a matriz "identidade"
		gl.glLoadIdentity();
		
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);

		
		game.renderAll(gl, glut);
		
				
//		gl.glColor3f(1,1,0);		
//		gl.glPushMatrix();
//			gl.glTranslatef(tx, ty, 0);
//			quadrado();
//		gl.glPopMatrix();
						
		emitirMensagemColisao();
		
		// Executa os comandos OpenGL
		gl.glFlush();
	}
	
	public void mensagem(String frase)
	{         
		gl.glColor3f(1,1,1);
		gl.glRasterPos2f(5f, 9.0f);		      
		glut.glutBitmapString(GLUT.BITMAP_8_BY_13, frase);		
	}  
	
	public void barraDireita(){
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2f(9,-5);
			gl.glVertex2f(8,-5);
			gl.glVertex2f(8,5);
			gl.glVertex2f(9,5);
		gl.glEnd();		
	}
	
	public void barraCima(){
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2f(-5, 9);
			gl.glVertex2f(-5, 8);
			gl.glVertex2f(5, 8);
			gl.glVertex2f(5, 9);
		gl.glEnd();		
	}
	
	public void barraBaixo(){
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2f(-5,-9);
			gl.glVertex2f(-5,-8);
			gl.glVertex2f(5, -8);
			gl.glVertex2f(5,-9);
		gl.glEnd();		
	}
	
	public void barraEsquerda(){
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2f(-9,-5);
			gl.glVertex2f(-8,-5);
			gl.glVertex2f(-8,5);
			gl.glVertex2f(-9,5);			
		gl.glEnd();		
	}

	public void quadrado(){
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2f(-2,-2);
			gl.glVertex2f(2,-2);
			gl.glVertex2f(2,2);
			gl.glVertex2f(-2,2);
		gl.glEnd();		
	}
	
	public void desenhaGrade(){
		int i;
		
		for(i=-10; i < 10; i++){
			gl.glBegin(GL2.GL_LINES);
				gl.glVertex2f(i, -10);
				gl.glVertex2f(i, 10);
				
				gl.glVertex2f(-10, i);
				gl.glVertex2f(10, i);
			gl.glEnd();
			
		}
	}
	
	public void emitirMensagemColisao(){
		if(tx == limiteDir || tx == limiteEsq){
			mensagem("Colidiu X !!");
		}
		if(ty == limiteCim || ty == limiteBai)
		{
			mensagem("Colidiu Y !!");
		}
	}
	
	
	public void updateLeft(){
		
		if(tx > limiteEsq)					
			tx--;
	}
	
	public void updateRight(){
		if(tx < limiteDir)
			tx += 1;
	}
	public void updateUp()
	{
		if(ty < limiteCim) ty += 1;
	}
	public void updateDown()
	{
		if(ty > limiteBai) ty --;
	}

	/**
	 * Chamado quando o contexto OpenGL eh destruido
	 */
	@Override
	public void dispose(GLAutoDrawable drawable) {
	}


	@Override
	public void keyPressed(KeyEvent e) {
		game.keyboardEvent(e);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
